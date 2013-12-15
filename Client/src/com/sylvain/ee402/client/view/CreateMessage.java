package com.sylvain.ee402.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateMessage extends JFrame {

	private JFrame _JFrameBack;
	private JTextField _LOL;
	private JButton _signInButton;

	public CreateMessage(JFrame parJFrameBack) {
		_JFrameBack = parJFrameBack;

		_LOL = new JTextField("LOL");
		_signInButton = new JButton("Back");
		_signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_JFrameBack.setVisible(true);
			}

		});
		
		
		this.getContentPane().add(_LOL);
		this.getContentPane().add(_signInButton);
		
		this.pack();
		this.setVisible(true);
	}

}
