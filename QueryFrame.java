package chatbox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class QueryFrame extends JFrame {
	public QueryFrame () {
		super("Window");
		//change the name -- it's stupid
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
					new MainFrame ("ChatBox",name);
					dispose();
				}
			}
		});
		
		add (button,BorderLayout.EAST);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
