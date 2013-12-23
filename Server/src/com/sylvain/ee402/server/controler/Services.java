package com.sylvain.ee402.server.controler;

import java.io.IOException;
import java.util.List;

import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.SimplifiedContact;


/**
 * Service Layer, 
 * Use the services provided by the contact manager and the led controller
 * @author sylvain
 *
 */
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
		try {
			_ledController.startFlashLed(locLedId, parMessage.getImportance());
		} catch (IOException e) {
			System.out.println("Led " + locLedId + " fail to blink");
		}
		_contactsManager.sendMessage(parMessage);

	}

	public Boolean logInOrRegister(SimplifiedContact parSimplifiedContact) {
		return _contactsManager.logInOrCreateUser(parSimplifiedContact);

	}

	public List<Message> getInboxMessages(String parUserName) {
		try {
			_ledController.stopFlashLed(_contactsManager.getLedId(parUserName));
		} catch (IOException e) {
			System.out.println("Led " + _contactsManager.getLedId(parUserName) + " fail to stop blinking");
		}
		return _contactsManager.getInboxMessages(parUserName);
		
	}

	public List<Message> getSentMessages(String parUserName) {
		return _contactsManager.getSentMessages(parUserName);
	}

	public void logOutUser(String parUserName) {
		_contactsManager.logOutUser(parUserName);
		
	}	


}
