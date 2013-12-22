package com.sylvain.ee402.common.model;

import java.io.Serializable;

/**
 * Message send over a socket between a client and a server
 */
public class NetworkMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3721328881253197529L;
	private Commands _command;
	private Object _message;
	
	public NetworkMessage(Commands parCommand, Object parMessage) {
		_command = parCommand;
		_message = parMessage;
	}

	public Commands getCommand() {
		return _command;
	}

	public Object getMessage() {
		return _message;
	}
	
	
	
}
