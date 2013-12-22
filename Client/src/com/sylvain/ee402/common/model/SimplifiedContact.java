package com.sylvain.ee402.common.model;

import java.io.Serializable;

/**
 * Simplified contact with only a userName and a password (send over the network)
 * @author sylvain
 *
 */
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
