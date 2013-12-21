package com.sylvain.ee402.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sylvain.ee402.client.controler.ApplicationController;
import com.sylvain.ee402.common.model.Message;

@SuppressWarnings("serial")
public class ReadMessage extends JFrame implements ActionListener{

	private JFrame _JFrameBack;
	private JButton _inboxButton;
	private JButton _sendboxButton;
	private JTextArea _messagesArea;
	private JLabel _message;
	private JButton _composeMessageButton;
	private JButton _logOutButton;
	private JButton _refreshButton;
	
	public ReadMessage(JFrame parJFrameBack) {
		_JFrameBack = parJFrameBack;

		
		_message = new JLabel("Inbox of " + ApplicationController.getInstance().getLogInUserName());
		_refreshButton = new JButton("Refresh");
		_refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(_inboxButton.isSelected()) {
					fillMessageArea(true);
				} else {
					fillMessageArea(false);
				}
			}
			
		});
		
		
		_composeMessageButton = new JButton("Compose a new message");
		_composeMessageButton.addActionListener(this);
		_logOutButton = new JButton("Log Out");
		_logOutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ApplicationController.getInstance().logOut();
				setVisible(false);
				_JFrameBack.setVisible(true);
			}
			
		});
		
		JPanel locNorth = new JPanel(new FlowLayout());
		locNorth.add(_message);
		locNorth.add(_refreshButton);
		locNorth.add(_composeMessageButton);
		locNorth.add(_logOutButton);
		
		JPanel locBox = new JPanel(new GridLayout(2,1));
		_inboxButton = new JButton("Inbox");
		_sendboxButton = new JButton("Sendbox");
		
		_inboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_sendboxButton.setSelected(false);
				_inboxButton.setSelected(true);
				_message.setText("Inbox of " + ApplicationController.getInstance().getLogInUserName());
				fillMessageArea(true);
			}

		});
		
		_sendboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_sendboxButton.setSelected(true);
				_inboxButton.setSelected(false);
				_message.setText("Sendbox of " + ApplicationController.getInstance().getLogInUserName());
				fillMessageArea(false);
			}

		});
		
		locBox.add(_inboxButton);
		locBox.add(_sendboxButton);
		
		
		
		_messagesArea = new JTextArea(20, 40);
		JScrollPane locMessageViewing = new JScrollPane(_messagesArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		_messagesArea.setWrapStyleWord(true);
		_messagesArea.setEditable(false);
		
		
		_inboxButton.setSelected(true);		
		fillMessageArea(true);
		BorderLayout locLayout = new BorderLayout(2,2);
		
		this.setLayout(locLayout);
		this.getContentPane().add(locNorth, BorderLayout.NORTH);
		this.getContentPane().add(locBox, BorderLayout.WEST);
		this.getContentPane().add(locMessageViewing, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				ApplicationController.getInstance().closeConnexion();
				System.exit(0);
			}
		});
		
		this.pack();
		this.setVisible(true);
		
	}
	
	
	private void fillMessageArea(boolean isInboxMessages) {
		
		List<Message> locMessages;
		if(isInboxMessages) { 		
			locMessages = ApplicationController.getInstance().getInboxMessages();
		} else {
			locMessages = ApplicationController.getInstance().getSentMessages();
		}
		
		_messagesArea.setText("");
		
		for(Message locMessage:locMessages) {
			_messagesArea.append(locMessage.toString());
			_messagesArea.append("\n \n \n----------------------------------\n");
		}
		
		_messagesArea.setLineWrap(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		new CreateMessage(this);
	}
	

}

// Sur le côté les boutons choix box, en haut logout, compose message
// millieu textarea avec liste message
