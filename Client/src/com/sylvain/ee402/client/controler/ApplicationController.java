package com.sylvain.ee402.client.controler;

import java.util.List;

import com.sylvain.ee402.common.model.Commands;
import com.sylvain.ee402.common.model.Message;
import com.sylvain.ee402.common.model.NetworkMessage;
import com.sylvain.ee402.common.model.SimplifiedContact;

/**
 * Controller of the Client
 * @author sylvain
 *
 */
public class ApplicationController {
	private static volatile ApplicationController _instance = null;
	private String _logInUserName = null;
	private Network _network = new Network();

	private ApplicationController() {

	}
	/**
	 * Singleton pattern
	 * @return
	 */
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

	public Boolean logIn(String parUserName, String parPassword) {

		_logInUserName = parUserName;
		SimplifiedContact locSimplifiedContact = new SimplifiedContact(parUserName, parPassword);
		NetworkMessage locNetworkMessage = new NetworkMessage(Commands.LOG_IN_OR_REGISTER, locSimplifiedContact);
		
		return (Boolean) _network.sentCommand(locNetworkMessage);

	}

	public String getLogInUserName() {
		return _logInUserName;
	}

	public List<String> getListContacts() {
		NetworkMessage locNetworkMessage = new NetworkMessage(
				Commands.GET_LIST_CONTACTS, null);
		return (List<String>) _network.sentCommand(locNetworkMessage);

	}

	public Boolean sentMessage(Message parMessage) {
		NetworkMessage locNetworkMessage = new NetworkMessage(
				Commands.SEND_MESSAGE, parMessage);
		return (Boolean) _network.sentCommand(locNetworkMessage);

	}

	public List<Message> getInboxMessages() {
		NetworkMessage locNetworkMessage = new NetworkMessage( Commands.GET_INBOX_MESSAGES, getLogInUserName());
		return (List<Message>) _network.sentCommand(locNetworkMessage);
	}

	public List<Message> getSentMessages() {
		NetworkMessage locNetworkMessage = new NetworkMessage( Commands.GET_SENTBOX_MESSAGES, getLogInUserName());
		return (List<Message>) _network.sentCommand(locNetworkMessage);		
	}

	public void closeConnexion() {
		NetworkMessage locNetworkMessage = new NetworkMessage(Commands.CLOSE_CONNEXION, getLogInUserName());
		_network.sentCommand(locNetworkMessage);
		_network.closeSocket();
		
	}

	public void logOut() {
		NetworkMessage locNetworkMessage = new NetworkMessage(Commands.LOG_OUT, getLogInUserName());
		_network.sentCommand(locNetworkMessage);
		
	}

}
