package com.sylvain.ee402.server.controler;

import com.sylvain.ee402.server.model.Contact;

public class Services {

	private ContactsManager _contactsManager;
	
	
	public boolean logonUser(Contact parContact) {
		
		return _contactsManager.logonContact(parContact);
		
	}
	
	public Contact loginContact (Contact parContact) {
		return _contactsManager.loginContact(parContact);
	}
	
	
}
