package com.sylvain.ee402.server.model;

import java.util.LinkedList;
import java.util.List;

import com.sylvain.ee402.common.model.Message;

/**
 * Class Contact, use by the server to define a user
 * @author sylvain
 *
 */
public class Contact {

	static private int id = 0;
	
	private String _userName;
	private String _password;
	private boolean _isLogged = false;
	private List<Message> _inbox = new LinkedList<Message>();
	private List<Message> _sendBox = new LinkedList<Message>();
	private int _ledId;
	
	public Contact(String _userName, String _password) {
		super();
		this._userName = _userName;
		this._password = _password;
		_ledId = id++;
	}

	public String get_password() {
		return _password;
	}

	public void setPassword(String _password) {
		this._password = _password;
	}

	public String getUserName() {
		return _userName;
	}
	
	public void addMessageToInbox(Message parMessage) {
		_inbox.add(_inbox.size(), parMessage);
	}
	
	public void addMessageToSendBox(Message parMessage) {
		_sendBox.add(_sendBox.size(), parMessage);
	}
	
	public List<Message> getAllMessagesInInbox() {
		return _inbox;
	}
	
	public List<Message> getAllMessagesInSendBox() {
		return _sendBox;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_password == null) ? 0 : _password.hashCode());
		result = prime * result
				+ ((_userName == null) ? 0 : _userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (_password == null) {
			if (other._password != null)
				return false;
		} else if (!_password.equals(other._password))
			return false;
		if (_userName == null) {
			if (other._userName != null)
				return false;
		} else if (!_userName.equals(other._userName))
			return false;
		return true;
	}

	public boolean isLogged() {
		return _isLogged;
	}

	public void setIsLogged(boolean _isLogged) {
		this._isLogged = _isLogged;
	}

	public int getLedId() {
		return _ledId;
	}

	
	
}
