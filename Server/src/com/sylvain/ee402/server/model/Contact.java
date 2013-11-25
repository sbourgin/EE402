package com.sylvain.ee402.server.model;

public class Contact {

	private String _userName;
	private String _password;

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

}
