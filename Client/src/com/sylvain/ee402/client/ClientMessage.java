package com.sylvain.ee402.client;

import com.sylvain.ee402.client.view.Application;

public class ClientMessage {
	public static void main(String[] args) {
		System.out.println("I'm the Client");
		
		Application locApplication = new Application();
		
	}
}
//TODO check si plusieurs clients peuvent se connecter à la fois
//Add refresh inbox quand on revient du compose message