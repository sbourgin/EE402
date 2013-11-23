package com.sylvain.ee402.client.controler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Network {

	private final static Integer PORT_CONNEXION = 9000; 
	private final static String SERVER_IP = "127.0.0.1";
	
	public void createConnexion() {

		String sentence;
		String modifiedSentence;
		
		
	
		try {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		Socket clientSocket = new Socket(SERVER_IP, PORT_CONNEXION);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();	
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	
	}

}
