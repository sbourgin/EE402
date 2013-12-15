package com.sylvain.ee402.server.model;

import java.sql.Timestamp;

import com.sylvain.ee402.server.model.Importance;

public class Message {

	private String _message;
	private Timestamp _sendTime;
	private Contact _sender;
	private Contact _destination;
	private Importance _importance;
	
	public Message(String _message, Timestamp _sendTime, Contact _sender,
			Contact _destination, Importance parImportance) {
		super();
		this._message = _message;
		this._sendTime = _sendTime;
		this._sender = _sender;
		this._destination = _destination;
		_importance = parImportance;
	}

	public String get_message() {
		return _message;
	}

	public Timestamp get_sendTime() {
		return _sendTime;
	}

	public Contact get_sender() {
		return _sender;
	}

	public Contact get_destination() {
		return _destination;
	}
	
	public Importance getImportance() {
		return _importance;
	}

}
