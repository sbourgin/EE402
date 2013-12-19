package com.sylvain.ee402.client.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sylvain.ee402.client.controler.ApplicationController;
import com.sylvain.ee402.common.model.Commands;
import com.sylvain.ee402.common.model.Importance;
import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.NetworkMessage;

@SuppressWarnings("serial")
public class CreateMessage extends JFrame {

	/**
	 * private String _message; private Contact _destination;
	 */

	private JFrame _JFrameBack;
	private JButton _submitButton;
	private JTextArea _textArea;

	public CreateMessage(JFrame parJFrameBack) {
		_JFrameBack = parJFrameBack;

		JLabel _to = new JLabel("To");
		final JComboBox _contacts = new JComboBox(ApplicationController
				.getInstance().getListContacts().toArray()); 
		JLabel _importanceLabel = new JLabel("Importance");

		final JComboBox _importanceSelector = new JComboBox(Importance.values());

		// TODO ajouter bouton revenir en arrière si on veut plus envoyer
		// message (cancell)
		JPanel locControls = new JPanel(new FlowLayout());
		locControls.add(_to);
		locControls.add(_contacts);
		locControls.add(_importanceLabel);
		locControls.add(_importanceSelector);

		_textArea = new JTextArea(20, 40);
		JScrollPane locMessageWriting = new JScrollPane(_textArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		_textArea.setWrapStyleWord(true);
		_textArea.setLineWrap(true);

		_submitButton = new JButton("Submit");
		_submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_JFrameBack.setVisible(true);

				Message locMessage = new Message(_textArea.getText(), System
						.currentTimeMillis(), ApplicationController
						.getInstance().getLogInUserName(), (String) _contacts
						.getSelectedItem(), (Importance) _importanceSelector
						.getSelectedItem());

				ApplicationController.getInstance().sentMessage(locMessage);

			}

		});

		this.getContentPane().add("North", locControls);
		this.getContentPane().add("Center", locMessageWriting);
		this.getContentPane().add("South", _submitButton);

		this.pack();
		this.setVisible(true);
	}

}
