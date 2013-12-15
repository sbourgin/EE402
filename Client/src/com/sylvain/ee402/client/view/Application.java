package com.sylvain.ee402.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Application extends JFrame {

	private JTextField _signInText;
	private JTextField _passWord;
	private JButton _signInButton;

	public Application() {

		super("Sign In");

		_signInText = new JTextField("Enter your username");
		_passWord = new JTextField("Enter your password ");
		_signInButton = new JButton("Sign In");
		_signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Menu();
			}
		});

		this.getContentPane().add("North", _signInText);
		this.getContentPane().add("Center", _passWord);
		this.getContentPane().add("South", _signInButton);

		this.pack();
		this.setVisible(true);
	}

}
