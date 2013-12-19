package com.sylvain.ee402.server.controler;

import java.util.List;

import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.SimplifiedContact;

public class Services {

	private ContactsManager _contactsManager;

	public Services() {
		_contactsManager = new ContactsManager();

	}

	public List<String> getListContacts() {
		return _contactsManager.getListContacts();
	}

	public void sendMessage(Message parMessage) {
		// TODO Ajouter qqpart la gestion des leds
		_contactsManager.sendMessage(parMessage);

	}

	public Boolean logInOrRegister(SimplifiedContact parSimplifiedContact) {
		return _contactsManager.logInOrCreateUser(parSimplifiedContact);

	}

	public List<Message> getInboxMessages(String parUserName) {
		return _contactsManager.getInboxMessages(parUserName);
	}

	public List<Message> getSentMessages(String parUserName) {
		return _contactsManager.getSentMessages(parUserName);
	}

}
