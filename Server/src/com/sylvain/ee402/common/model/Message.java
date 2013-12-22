package com.sylvain.ee402.common.model;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * Message (send between contacts)
	 */
	private static final long serialVersionUID = -4442908046273373672L;
	private String _message;
	private Long _sendTime;
	private String _sender;
	private String _destination;
	private Importance _importance;

	public Message(String _message, Long _sendTime, String _sender,
			String _destination, Importance parImportance) {
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

	public Long get_sendTime() {
		return _sendTime;
	}

	public String get_sender() {
		return _sender;
	}

	public String get_destination() {
		return _destination;
	}
	
	public Importance getImportance() {
		return _importance;
	}

}
