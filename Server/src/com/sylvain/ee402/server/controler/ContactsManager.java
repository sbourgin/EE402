package com.sylvain.ee402.server.controler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sylvain.ee402.server.model.Contact;
import com.sylvain.ee402.server.model.Message;

public class ContactsManager {

	private Map<String, Contact> _registerContacts = new HashMap<String, Contact>();

	public void logInOrCreateUser(Contact parContact) {

		if (_registerContacts.containsKey(parContact.getUserName())) {
			_registerContacts.get(parContact).setIsLogged(true);
			return;
		}

		
		// The user doesn't exist
		
		_registerContacts.put(parContact.getUserName(), parContact); // TODO Attention pas le même type de
											// contact
		return;

	}

	public List<Contact> getListContacts() {
		List<Contact> locList = new LinkedList<Contact>(_registerContacts.values());
		return locList;
	}

	public void sendMessage(Message parMessage) {
		
		Contact locSender = _registerContacts.get(parMessage.get_sender().getUserName());
		locSender.addMessageToSendBox(parMessage);
		
		Contact locReceiver = _registerContacts.get(parMessage.get_destination().getUserName());
		locReceiver.addMessageToInbox(parMessage);//TODO Ajouter qqpart la gestion des leds
		
	}

	/*
	 * public boolean logonContact(Contact parContact) { // check si on utilise
	 * bien l'equal car sinon les deux objets sont // différents
	 * 
	 * if(_registerContacts.contains(parContact)) { int locIndex =
	 * _registerContacts.indexOf(parContact); Contact locContact =
	 * _registerContacts.get(locIndex); locContact.setIsLogged(true); }
	 * 
	 * return (_registerContacts.contains(parContact));
	 * 
	 * }
	 * 
	 * public Contact loginContact(Contact parContact) { if
	 * (_registerContacts.add(parContact)) { return parContact; } else { return
	 * null; } }
	 * 
	 * public void logOutContact(Contact parContact) {
	 * if(_registerContacts.contains(parContact)) { int locIndex =
	 * _registerContacts.indexOf(parContact); Contact locContact =
	 * _registerContacts.get(locIndex); locContact.setIsLogged(false); }
	 * 
	 * 
	 * }
	 */

}
