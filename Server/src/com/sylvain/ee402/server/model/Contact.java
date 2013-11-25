package com.sylvain.ee402.server.model;

public class Contact {

	private String _userName;
	private String _password;
	private boolean _isLogged;

	public Contact(String _userName, String _password) {
		super();
		this._userName = _userName;
		this._password = _password;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_userName() {
		return _userName;
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

	
	
}
