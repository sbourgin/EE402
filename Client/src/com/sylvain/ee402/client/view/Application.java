package com.sylvain.ee402.client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sylvain.ee402.client.controler.ApplicationController;

@SuppressWarnings("serial")
public class Application extends JFrame implements ActionListener{

	private JTextField _userName;
	private JTextField _passWord;
	private JButton _signInButton;
	private JLabel _message;

	public Application() {

		super("Sign In");
		_message = new JLabel("Please LogIn, if your user doesn't exist it will be created");
		_userName = new JTextField("Enter your username");
		_passWord = new JTextField("Enter your password ");
		_signInButton = new JButton("Sign In");
		_signInButton.addActionListener(this);
		
		this.setLayout(new GridLayout(4, 1));
		this.getContentPane().add(_message);
		this.getContentPane().add(_userName);
		this.getContentPane().add(_passWord);
		this.getContentPane().add(_signInButton);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(ApplicationController.getInstance().logIn(_userName.getText(), _passWord.getText())) {
			setVisible(false);
			new ReadMessage(this);
		}
		_message.setText("Your login/password is incorrect");
		
	}

}
