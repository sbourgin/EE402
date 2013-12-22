package com.sylvain.ee402.server.controler;

import java.util.List;

import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.SimplifiedContact;

public class Services {

	private ContactsManager _contactsManager;
	private static volatile Services _instance = null;
	private LEDController _ledController;
	
	
	private Services() {
		_contactsManager = new ContactsManager();
		_ledController = new LEDController();
	}
	

	public final static Services getInstance() {
		if (_instance == null) {
			synchronized (Services.class) {
				if (_instance == null) {
					_instance = new Services();
				}
			}
		}
		return _instance;
	}

	public List<String> getListContacts() {
		return _contactsManager.getListContacts();
	}

	public void sendMessage(Message parMessage) {
		int locLedId = _contactsManager.getLedId(parMessage.get_destination());
		_ledController.startFlashLed(locLedId, parMessage.getImportance());
		_contactsManager.sendMessage(parMessage);

	}

	public Boolean logInOrRegister(SimplifiedContact parSimplifiedContact) {
		return _contactsManager.logInOrCreateUser(parSimplifiedContact);

	}

	public List<Message> getInboxMessages(String parUserName) {
		_ledController.stopFlashLed(_contactsManager.getLedId(parUserName));
		return _contactsManager.getInboxMessages(parUserName);
		
	}

	public List<Message> getSentMessages(String parUserName) {
		return _contactsManager.getSentMessages(parUserName);
	}

	public void logOutUser(String parUserName) {
		_contactsManager.logOutUser(parUserName);
		
	}

	


}
