package com.sylvain.ee402.common.model;

/**
 * Differents commands between the server and the client
 * @author sylvain
 *
 */
public enum Commands {
	GET_LIST_CONTACTS,
	SEND_MESSAGE,
	LOG_IN_OR_REGISTER,
	GET_INBOX_MESSAGES,
	GET_SENTBOX_MESSAGES, 
	CLOSE_CONNEXION,
	LOG_OUT;
}
