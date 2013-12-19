package com.sylvain.ee402.client.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sylvain.ee402.client.controler.ApplicationController;
import com.sylvain.ee402.common.model.Importance;
import com.sylvain.ee402.common.model.Message;

@SuppressWarnings("serial")
public class ReadMessage extends JFrame {

	private JFrame _JFrameBack;
	private JButton _inboxButton;
	private JButton _sendboxButton;
	private JTextArea _messagesArea;
	
	
	public ReadMessage(JFrame parJFrameBack) {
		_JFrameBack = parJFrameBack;

		JPanel locBox = new JPanel(new FlowLayout());
		_inboxButton = new JButton("Inbox");
		_sendboxButton = new JButton("Sendbox");
		
		_inboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_sendboxButton.setSelected(false);
				_inboxButton.setSelected(true);
				fillMessageArea(true);
			}

		});
		
		_sendboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_sendboxButton.setSelected(true);
				_inboxButton.setSelected(false);
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
		
		this.getContentPane().add("North", locBox); //Mettre est
		this.getContentPane().add("Center", locMessageViewing);
		
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
			_messagesArea.append("\n \n \n----------------------------------");
		}
		
	}
	

}

// Sur le côté les boutons choix box, en haut logout, compose message
// millieu textarea avec liste message
