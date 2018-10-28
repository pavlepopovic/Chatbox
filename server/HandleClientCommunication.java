package chatbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class HandleClientCommunication extends Thread {
	
	
	private Socket serverSide;

	private BufferedReader input;
	
	
	public HandleClientCommunication(Socket serverSide) throws IOException {
		this.serverSide=serverSide;
		input = new BufferedReader (new InputStreamReader (serverSide.getInputStream()));
	}
	
	
	@Override
	public void run() {
		
		/*
		 * Client message format:
		 *   <!>messageID<!>messageText<!>
		 *   
		 *   messageID could be:
		 *   	1) Server.CREATE_NEW_USER
		 *   	2) Server.UPDATE_CHATBOX
		 *   	3) Server.DELETE_USER
		 *   
		 *   
		 */
		
		
		
		
		
		try {
			while (true) {
				
				
			String clientMsg = input.readLine();
			// what happens with messages that have new lines?
			
			//parse this message
			
			
			if (clientMsg == null) break;
			//it will be null when the client closes application
			
			
			
			StringTokenizer msg_tokens = new StringTokenizer(clientMsg, "<!>");
			int msg_id = Integer.parseInt(msg_tokens.nextToken());
			
			switch (msg_id) {
				case Server.UPDATE_CHATBOX:
					// message format: <!>message_ID<!>message_text<!>
					Server.ChatBoxUpdate_NewMessage(msg_tokens.nextToken());
					break;
				case Server.CREATE_NEW_USER:
					Server.addUser(msg_tokens.nextToken());
					Server.userList_update();
					//message format: <!>message_ID<!>new_userName<!>
					break;
				case Server.REMOVE_USER:
					//message format: <!>message_ID<!>userName<!>
					Server.removeUser(msg_tokens.nextToken());
					Server.userList_update();
					break;
			}
			
			//send reply to all users
			
			//if it is turning down, you may need to break out of here
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
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
