package com.sylvain.ee402.server.controler;

import java.util.LinkedList;
import java.util.List;

import com.sylvain.ee402.server.model.Contact;

public class ContactsManager {

	private List<Contact> _registerContacts = new LinkedList<Contact>();

	public boolean logonContact(Contact parContact) {
		// check si on utilise bien l'equal car sinon les deux objets sont
		// différents
		
		if(_registerContacts.contains(parContact)) {
			int locIndex = _registerContacts.indexOf(parContact);
			Contact locContact = _registerContacts.get(locIndex);
			locContact.setIsLogged(true);
		}
		
		return (_registerContacts.contains(parContact));

	}
	
	public Contact loginContact(Contact parContact) {
		if (_registerContacts.add(parContact)) {
			return parContact;
		} else {
			return null;
		}
	}
	
	public void logOutContact(Contact parContact) {
		if(_registerContacts.contains(parContact)) {
			int locIndex = _registerContacts.indexOf(parContact);
			Contact locContact = _registerContacts.get(locIndex);
			locContact.setIsLogged(false);
		}
		
		
	}
 
}
