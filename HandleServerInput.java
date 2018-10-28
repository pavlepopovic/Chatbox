package chatbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class HandleServerInput extends Thread {
	
	Socket clientSide ;
	BufferedReader input;
	MainFrame clientGUI;
	
	public HandleServerInput (Socket clientSide, MainFrame clientGUI) {
		this.clientSide=clientSide;
		this.clientGUI = clientGUI;
		try {
			input = new BufferedReader (
						new InputStreamReader(
							clientSide.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
					
					String serverMsg = input.readLine();
					
					StringTokenizer msg_tokens = new StringTokenizer(serverMsg, "<!>");
					
					int msg_id = Integer.parseInt(msg_tokens.nextToken());
					
					switch (msg_id) {
						case Client.UPDATE_CHATBOX:
							clientGUI.update_ChatBox(msg_tokens.nextToken());
							//this message format will be  <!>msgID<!>msgText<!>
							break;
						case Client.CREATE_NEW_USER:
							//message format <!>Message_ID<!>user_name1<!>user_name2<!>...<!>user_nameN<!>
							String [] userName_array = new String [msg_tokens.countTokens()];
							for (int i = 0; i < userName_array.length ; i++) userName_array[i] = msg_tokens.nextToken();
							clientGUI.update_userList(userName_array);
							break;
						case Client.REMOVE_USER:
							//message format <!>Message_ID<!>user_name_1<!>user_name2<!>...<!>user_nameN<!>
							String [] userName_array_1 = new String [msg_tokens.countTokens()];
							for (int i = 0; i < userName_array_1.length ; i++) userName_array_1[i] = msg_tokens.nextToken();
							clientGUI.update_userList(userName_array_1);
							break;
							
					}
					
					
				
					
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
			
			
			
		}
		
		
		
	}
	
	
}
