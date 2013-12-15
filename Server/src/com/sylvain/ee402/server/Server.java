package com.sylvain.ee402.server;

import com.sylvain.ee402.server.controler.Serveur;

public class Server {

	private final static Integer PORT_CONNEXION = 9000; 
	
	public static void main(String[] args) {
		System.out.println("I'm the server");
		
		Thread serveurListeningThread = null;
		try {
			serveurListeningThread = new Thread(new Serveur(PORT_CONNEXION));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		serveurListeningThread.start();
		
		
	}

}


//Concept mail box, donc ça va bien dans les deux mails box (destinataire et emmeteur)
// Sérialiser objet, pas de string comme ça


//Creer par défaut des users avec MDP