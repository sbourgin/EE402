package com.sylvain.ee402.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener {

	private JButton _createMessageButton;
	private JButton _readMessages;

	public Menu() {

		super("What action do you want to do ?");

		_createMessageButton = new JButton("Create a message");
		_readMessages = new JButton("Read your messages");

		_createMessageButton.addActionListener(this);
		_readMessages.addActionListener(this);		
		this.getContentPane().add("North", _createMessageButton);
		this.getContentPane().add("South", _readMessages);		
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		if (e.getActionCommand().equals("Create a message")) {
			new CreateMessage(this);
		} else if (e.getActionCommand().equals("Read your messages")) {
			new ReadMessage(this);
		}
	}
}
