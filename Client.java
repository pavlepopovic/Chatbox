package chatbox;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Client {
	public static void main (String [] args) {
		
		
		SwingUtilities.invokeLater(new  Runnable() {
			public void run() {
				JFrame frame = new QueryFrame();
			}
		});
		
		
		
	}
}
