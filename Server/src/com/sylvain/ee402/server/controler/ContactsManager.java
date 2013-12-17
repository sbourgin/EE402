package com.sylvain.ee402.server.controler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.SimplifiedContact;
import com.sylvain.ee402.server.model.Contact;

public class ContactsManager {

	private Map<String, Contact> _registerContacts = new HashMap<String, Contact>();

	
	public ContactsManager() {
		//initialization 		
		_registerContacts.put("John", new Contact("John", "aaa"));
		_registerContacts.put("Paul", new Contact("Paul", "aaa"));
		_registerContacts.put("Cil", new Contact("Cil", "aaa"));
	}
	
	public void logInOrCreateUser(SimplifiedContact parSimplifiedContact) {

		if (_registerContacts.containsKey(parSimplifiedContact.getUserName())) {
			_registerContacts.get(parSimplifiedContact).setIsLogged(true);
			return;
		}

		// The user doesn't exist		
		_registerContacts.put(parSimplifiedContact.getUserName(), new Contact(parSimplifiedContact.getUserName(), parSimplifiedContact.getPassword())); // TODO Attention pas le même type de
											// contact
		return;

	}

	public List<String> getListContacts() {
		List<String> locList = new LinkedList<String>(_registerContacts.keySet());
		return locList;
	}

	public void sendMessage(Message parMessage) {
		
		Contact locSender = _registerContacts.get(parMessage.get_sender());
		locSender.addMessageToSendBox(parMessage);
		
		Contact locReceiver = _registerContacts.get(parMessage.get_destination());
		locReceiver.addMessageToInbox(parMessage);
		
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
