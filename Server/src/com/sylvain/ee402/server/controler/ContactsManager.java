package com.sylvain.ee402.server.controler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.SimplifiedContact;
import com.sylvain.ee402.server.model.Contact;

/**
 * Manage the users for the server
 * @author sylvain
 *
 */
public class ContactsManager {

	private Map<String, Contact> _registerContacts = new HashMap<String, Contact>();
	
	public ContactsManager() {
		//initialization 		
		_registerContacts.put("John", new Contact("John", "aaa"));
		_registerContacts.put("Paul", new Contact("Paul", "aaa"));
		_registerContacts.put("Cil", new Contact("Cil", "aaa"));
	}
	
	public Boolean logInOrCreateUser(SimplifiedContact parSimplifiedContact) {

		if (_registerContacts.containsKey(parSimplifiedContact.getUserName())) {
			if(_registerContacts.get(parSimplifiedContact.getUserName()).get_password().equals(parSimplifiedContact.getPassword())) {
				_registerContacts.get(parSimplifiedContact.getUserName()).setIsLogged(true);
				return (new Boolean(true));
			}
			return (new Boolean(false));
			
		}

		// The user doesn't exist		
		_registerContacts.put(parSimplifiedContact.getUserName(), new Contact(parSimplifiedContact.getUserName(), parSimplifiedContact.getPassword()));
											
		return (new Boolean(true));

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

	public List<Message> getInboxMessages(String parUserName) {
		return _registerContacts.get(parUserName).getAllMessagesInInbox();
	}

	public List<Message> getSentMessages(String parUserName) {
		return _registerContacts.get(parUserName).getAllMessagesInSendBox();
	}

	public void logOutUser(String parUserName) {
		if(_registerContacts.containsKey(parUserName)) {
			_registerContacts.get(parUserName).setIsLogged(false);
		}
		
	}
	
	public int getLedId(String parUserName) {
		return _registerContacts.get(parUserName).getLedId();
	}

}
