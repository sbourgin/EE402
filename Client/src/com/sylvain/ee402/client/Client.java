package com.sylvain.ee402.client;

import com.sylvain.ee402.client.controler.Network;

public class Client {
	public static void main(String[] args) {
		System.out.println("I'm the Client");
		
		Network network = new Network();
		network.createConnexion();
	}
}
