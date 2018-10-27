package chatbox;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Client {
	
	private static final int port = 5555;
	private static final String serverIP = "192.168.0.23";
	
	public static void main (String [] args) throws UnknownHostException, IOException {
		
		
		
		Socket clientSide = new Socket (serverIP, port);
		
		
		
		
		SwingUtilities.invokeLater(new  Runnable() {
			public void run() {
				 JFrame frame = new QueryFrame(clientSide);
			}
		});
		
		
	
		
		
		
		
	}
}
