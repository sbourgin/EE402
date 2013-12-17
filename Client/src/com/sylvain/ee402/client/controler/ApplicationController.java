package com.sylvain.ee402.client.controler;

import java.util.List;

import com.sylvain.ee402.common.model.Commands;
import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.NetworkMessage;

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
		//_logInUserName = parContactUserName; //TODO remettre
		_logInUserName = "Cil";//TODO enlever
		return true;
	}
	
	
	public String getLogInUserName() {
		return _logInUserName;
	}
	

	public List<String> getListContacts() {
		NetworkMessage locNetworkMessage = new NetworkMessage(Commands.GET_LIST_CONTACTS, null);
		return (List<String>) _network.sentCommand(locNetworkMessage);
		
	}
	
	public Boolean sentMessage(Message parMessage) {
		NetworkMessage locNetworkMessage = new NetworkMessage(Commands.SEND_MESSAGE, parMessage);
		return  (Boolean) _network.sentCommand(locNetworkMessage);
		
	}

}
