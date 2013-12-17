package com.sylvain.ee402.server.controler;

import java.util.List;

import com.sylvain.ee402.server.model.Contact;


public class Services {

	private ContactsManager _contactsManager;
	
	public Services() {
		_contactsManager = new ContactsManager();

	}
	
	
	
	public List<String> getListContacts() {
		return _contactsManager.getListContacts();
	}
	
	
	
	
	
	
}
