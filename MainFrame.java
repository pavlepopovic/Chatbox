package chatbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame {
	
	private JTextArea chatBox, msgBuffer;
	private JList clients;
	private DimensionPanel west, center, south;
	private JButton sendMsg;
	private String userName;
	
	
	public void setBorders() {
		
		/*
		 * This method divides the frame into 3 different parts
		 * 		1) west - where user list will be
		 * 		2) center - where chatbox will be
		 * 		3) south - where message box will be
		 */
		
		
		
		Container c = this.getContentPane();
		
		
		west = new DimensionPanel(150, 500);
		DimensionPanel east = new DimensionPanel (850,500);
		
		
		//east.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 5), "USERS"));
		
		west.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		//rightSide.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		
		c.add(west, BorderLayout.WEST);
		c.add(east,BorderLayout.CENTER);
		
		
		east.setLayout(new BorderLayout());
		
		
		center = new DimensionPanel(850, 400);
		south = new DimensionPanel (850,100);
		
		
		
		center.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		south.setBorder(BorderFactory.createLineBorder(Color.RED,5));
		
		east.add(center,BorderLayout.CENTER);
		east.add(south,BorderLayout.SOUTH);
		
		
		west.setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());
		
		
		
		
		
		
	}
	
	public void createElements() {
		
		
		JLabel westLabel = new JLabel ("Online users:");
		westLabel.setFont(new Font("Serif",Font.BOLD,20));
		westLabel.setBackground(Color.CYAN);
		
		west.add(westLabel, BorderLayout.NORTH);
		
		
		JLabel centerLabel = new JLabel ("GROUP CHAT:");
		centerLabel.setFont(new Font ("Serif",Font.BOLD,20));
		centerLabel.setBackground(Color.GRAY);
		
		center.add(centerLabel,BorderLayout.NORTH);
		
		
		JLabel southLabel = new JLabel ("Your message:");
		southLabel.setFont(new Font ("Serif",Font.BOLD,20));
		southLabel.setBackground(Color.BLUE);
		
		
		south.add(southLabel,BorderLayout.NORTH);
		
		
		
		
		
		
		chatBox = new JTextArea();
		chatBox.setLineWrap(true);
		chatBox.setEditable(false);
		
		JScrollPane chatBoxScroll = new JScrollPane(chatBox);
		
		
		msgBuffer = new JTextArea();
		msgBuffer.setLineWrap(true);
		JScrollPane msgBufferScroll = new JScrollPane (msgBuffer);
		
		
		
		
		clients = new JList <> ();
		
		
		west.add(clients,BorderLayout.CENTER);
		center.add(chatBoxScroll,BorderLayout.CENTER);
		south.add(msgBufferScroll,BorderLayout.CENTER);
		
		String test [] = { "Pavle Popovic", "Djordje Ponjavic", "Radisa Mitroivc", "Nenad Rakonjac"};
		
		
		
		clients.setListData(test);
		
		
		// disables selection from JList elements
		
		clients.setSelectionModel(new DefaultListSelectionModel() {
			/*
			 * disables list item selection
			 */
			
			
			
			@Override
			   public void setAnchorSelectionIndex(final int anchorIndex) {}

			   @Override
			   public void setLeadAnchorNotificationEnabled(final boolean flag) {}

			   @Override
			   public void setLeadSelectionIndex(final int leadIndex) {}

			   @Override
			   public void setSelectionInterval(final int index0, final int index1) { }
			 
		});
	
		sendMsg = new JButton("SEND");
		sendMsg.setFont(new Font ("Serif",Font.BOLD,20));
		
		sendMsg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (msgBuffer.getText().isEmpty() == false) {
					// nothing to do if user didn't write a message
					
					chatBox.append(userName+": ");
					String messageText = msgBuffer.getText();
					chatBox.append(messageText);
					//if user didn't put a new line, we should do it
					if (messageText.charAt(messageText.length()-1) != '\n') {
						chatBox.append("\n");
					}
					
					msgBuffer.setText(null);
				}
			}
		});
		
		south.add(sendMsg, BorderLayout.EAST);
	
	}
	
	
	
	
	
	
	public MainFrame(String title, String userName) {
		super(title);
		this.userName = userName;
		
		setLayout(new BorderLayout());
		
		setSize(1000,500);
		
		
		setBorders();
		createElements();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
