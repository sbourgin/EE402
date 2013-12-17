package com.sylvain.ee402.common.model;

import java.io.Serializable;


public class SimplifiedContact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8184629707075333033L;
	private String _userName;
	private String _password;
	
	public SimplifiedContact(String parUserName, String parPassword) {
		this._userName = parUserName;
		this._password = parPassword;
	}

	public String getUserName() {
		return _userName;
	}

	public String getPassword() {
		return _password;
	}
	
	
}
