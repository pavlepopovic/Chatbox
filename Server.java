package chatbox;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private static List <Socket> sockets;
	private static ServerSocket server;
	private static final int port = 5555;
	
	
	
	
	public static synchronized void remove_socket (Socket socket) {
		sockets.remove(socket);
	}
	
	public static synchronized void update_socket_list (Socket newSocket) {
		sockets.add(newSocket);
	}
	
	
	public static synchronized void ChatBoxUpdate_NewMessage (String message) throws IOException {
		for (Socket socket : sockets) {
			if (socket != null) {
				PrintWriter output = new PrintWriter (socket.getOutputStream(),true);
				output.println(message);
			}
		}
	}
	
	public static void main (String [] args) {
		
		sockets = new ArrayList<>();
		
		
		
		try {
			server = new ServerSocket (port);
			System.out.println("Server started...");
			
			while (true) {
				
				Socket serverSide = server.accept();
				update_socket_list(serverSide);
				(new HandleClientCommunication(serverSide)).start();
				System.out.println("User connected");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			for (Socket s : sockets) {
				if (s != null)
					try {
						s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
