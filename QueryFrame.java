package chatbox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class QueryFrame extends JFrame {
	
	private Socket clientSide;
	
	
	public QueryFrame (Socket clientSide) {
		super("Window");
		//change the name -- it's stupid
		
		this.clientSide = clientSide;
		
		setSize(300,100);
		
		setLayout(new BorderLayout());
		
		add (new JLabel ("Username:"),BorderLayout.NORTH);
		
		JTextField userName = new JTextField();
		add (userName,BorderLayout.CENTER);
		
		JButton button = new JButton ("Next");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = userName.getText();
				if (name.isEmpty() == false) {
					try {
						MainFrame clientGUI = new MainFrame (name+"'s chatBox",name,clientSide);
						
						(new HandleServerInput(clientSide, clientGUI)).start();
						
						PrintWriter output = new PrintWriter (clientSide.getOutputStream(),true);
						
						String newUserMessage = "<!>"+Client.CREATE_NEW_USER+"<!>"+name+"<!>";
						output.println(newUserMessage);
						
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}
			}
		});
		
		add (button,BorderLayout.EAST);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					clientSide.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		
		
	}
}
