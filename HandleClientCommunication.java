package chatbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleClientCommunication extends Thread {
	
	
	private Socket serverSide;

	private BufferedReader input;
	
	
	public HandleClientCommunication(Socket serverSide) throws IOException {
		this.serverSide=serverSide;
		input = new BufferedReader (new InputStreamReader (serverSide.getInputStream()));
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				
				
			String clientMsg = input.readLine();
			// what happens with messages that have new lines?
			
			//parse this message
			
			
			if (clientMsg == null) break;
			//it will be null when the client closes application
			
			
			
			Server.ChatBoxUpdate_NewMessage(clientMsg);
			
			//send reply to all users
			
			//if it is turning down, you may need to break out of here
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Server.remove_socket(serverSide);
			serverSide.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
