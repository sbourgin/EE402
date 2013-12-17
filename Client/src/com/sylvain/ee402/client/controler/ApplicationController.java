package com.sylvain.ee402.client.controler;

import java.util.List;

import com.sylvain.ee402.client.common.Commands;

public class ApplicationController {
	private static volatile ApplicationController _instance = null;
	private String _logInUserName;
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
	public boolean logIn(String parContactUserName, String parPassword) {

		// TODO Appel WebService pour checker si ok Contact logInOrCreateUser
		return true;
	}
	
	
	public String getLogInUserName() {
		return _logInUserName;
	}
	

	public List<String> getListContacts() {
		List<String> locContacts = (List<String>) _network.sentCommand(Commands.GET_LIST_CONTACTS);
		return locContacts;
	}

}
