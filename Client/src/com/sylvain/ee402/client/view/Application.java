package com.sylvain.ee402.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sylvain.ee402.client.controler.ApplicationController;

@SuppressWarnings("serial")
public class Application extends JFrame {

	private JTextField _userName;
	private JTextField _passWord;
	private JButton _signInButton;

	public Application() {

		super("Sign In");

		_userName = new JTextField("Enter your username");
		_passWord = new JTextField("Enter your password ");
		_signInButton = new JButton("Sign In");
		_signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ApplicationController.getInstance().logIn(_userName.getText(), _passWord.getText())) {
					setVisible(false);
					new Menu();
				}
				_passWord.setText("Your login/password is incorrect");
			}
		});

		this.getContentPane().add("North", _userName);
		this.getContentPane().add("Center", _passWord);
		this.getContentPane().add("South", _signInButton);

		this.pack();
		this.setVisible(true);
	}

}
