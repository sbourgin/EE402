package com.sylvain.ee402.client.controler;

import java.util.List;

import com.sylvain.ee402.client.common.Commands;
import com.sylvain.ee402.client.model.Contact;

public class ApplicationController {
	private static volatile ApplicationController _instance = null;
	private Contact _logInContact;
	private Network _network = new Network();

	private ApplicationController() {

	}

	public final static ApplicationController getInstance() {
		if (_instance == null) {
			synchronized (ApplicationController.class) {
				if (_instance == null) {
					_instance = new ApplicationController();
				}
			}
		}
		return _instance;
	}

	// TODO
	public boolean logIn(Contact parContact) {

		// TODO Appel WebService pour checker si ok Contact logInOrCreateUser
		return true;
	}

	public Contact getLogInContact() {
		return _logInContact;
	}

	public List<Contact> getListContacts() {

		return (List<Contact>) _network.sentCommand(Commands.GET_LIST_CONTACTS);
	}

}
