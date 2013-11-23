package com.sylvain.ee402.server.controler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
		
		
		//TODO Debut test
		/*
		DataOutputStream outToClient = null;
		try {
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true)
		{
			
			String reponseServeur = "A";
			try {
				outToClient.writeBytes(reponseServeur);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			reponseServeur += "A";
			
			System.out.println("msg envoyé" + reponseServeur);
			
			
		}
	       
		*/
		boolean clientIsConnected = true;
		
		 while(clientIsConnected)
	        {
	           System.out.println("Un client connecté Commande");
	           
			try {
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		           clientSentence = inFromClient.readLine();
		           System.out.println("Received: " + clientSentence);
		           	           
		           
		           String reponseServeur = "";
		           
		           if (clientSentence.startsWith("COMMAND"))
		           {
		        	   reponseServeur = "OK COMMAND\r\n";
		        	   
		           }
		           else if(clientSentence.startsWith("INIT"))
		           {
		        	   reponseServeur = "OK INIT\r\n";
		           }
		           else if(clientSentence.startsWith("QUIT"))
		           {
		        	   reponseServeur = "OK QUIT\r\n";
		        	   outToClient.writeBytes(reponseServeur);
		        	   throw new Erreur("client deconnecte");
		           }
		           else
		           {
		        	   reponseServeur = "UNKNOWN\r\n";
		           }
		           
		           System.out.println("Envoi "+ reponseServeur);
		           outToClient.writeBytes(reponseServeur);
		           
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (Erreur e) {
				// TODO Auto-generated catch block
				clientIsConnected = false;
				
			}
	           
	      }
		 
		 System.out.println("Fin du serveur");
		
	}
}
