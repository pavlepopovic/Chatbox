package chatbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
	private Socket clientSide;
	private PrintWriter output;
	
	
	public void update_ChatBox (String newText) {
		chatBox.append(newText);
		chatBox.append("\n");
	}
	
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
	
	
	public void add_sendButton_Function () {
		sendMsg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (msgBuffer.getText().isEmpty() == false) {
					// nothing to do if user didn't write a message
					
					
					String client_message = userName + ": " + msgBuffer.getText();
					
					output.println(client_message);
					
					msgBuffer.setText(null);
				}
			}
		});
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
		
		
		
		south.add(sendMsg, BorderLayout.EAST);
	
	}
	
	
	
	
	
	
	public MainFrame(String title, String userName, Socket clientSide) throws IOException {
		super(title);
		this.userName = userName;
		this.clientSide = clientSide;
		
		output = new PrintWriter (clientSide.getOutputStream(), true);
		
		setLayout(new BorderLayout());
		
		setSize(1000,500);
		
		
		setBorders();
		createElements();
		add_sendButton_Function();
		
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
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
