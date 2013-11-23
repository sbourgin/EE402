package com.sylvain.ee402.server.controler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.sylvain.ee402.server.common.Erreur;

public class Serveur implements Runnable{

	private Socket connectionSocket;
	private  ServerSocket welcomeSocket;
	
	public Serveur(Integer port) throws Exception {
        
        welcomeSocket = new ServerSocket(port); 
       
	}

	@Override
	public void run() {
	       String clientSentence;
	       try {
			connectionSocket = welcomeSocket.accept();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	       
	       BufferedReader inFromClient = null;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		

			try {
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		           clientSentence = inFromClient.readLine();
		           System.out.println("Received: " + clientSentence);
		           	           
		           
		           String reponseServeur = "";
		           
		          
		        	   reponseServeur = clientSentence + " LOOOL \r\n";
		        	   
		          
		           
		           System.out.println("Envoi "+ reponseServeur);
		           outToClient.writeBytes(reponseServeur);
		           
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
	      
		 
		 System.out.println("Fin du serveur");
		
	}
}
