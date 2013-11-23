package com.sylvain.ee402.server.common;

@SuppressWarnings("serial")
/**
 * Classe Erreur pour lever nos propres exceptions
 * @author sylvain
 *
 */
public class Erreur extends Exception {

	private String mMessage;

	public Erreur(String msg)
	{
		mMessage = msg;
		
	}
	public void setMessage(String msg)
	{
		mMessage = msg;
		
	}
	public String getMessage()
	{
		return mMessage;
	}
}