package chatbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
				
				if (clientSide.isClosed() == false) {
				
					
					
					String newText = input.readLine();
					
				
					clientGUI.update_ChatBox(newText);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
	}
	
	
}
