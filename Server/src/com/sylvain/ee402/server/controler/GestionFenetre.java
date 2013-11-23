package com.sylvain.ee402.server.controler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;



/**
 * Controleur qui gère la fenetre, et les sockets commande/message
 * @author sylvain
 *
 */

public class GestionFenetre extends AbstractControler {

	private GestionCommande gestionCommande;
	private GestionMessage gestionMessage;
	 
	/**
	 * Lance également les deux threads de communication réseau
	 * @param data
	 */
	public GestionFenetre() {
		

		
		
	}
	

	
	/**
	 * Ferme les sockets reseau de commande et message
	 */
	public void closeConnexion()
	{
		
		try {
			gestionCommande.closeConnexion();
			gestionMessage.closeConnexion();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Thread du socket réseau Commande
	 * @author sylvain
	 *
	 */
	public class GestionCommande extends AbstractControlerSocket {
		

		public GestionCommande(int port, String ip)
				throws UnknownHostException, IOException {
			super(port, ip);

			
			
		}

		public String proceedMessage(String message) {

			if (message.startsWith("OK COMMAND")) {
				return "OK COMMAND";
			} else if (message.startsWith("NOK COMMAND")) {
				return "NOK COMMAND";
			} 
			else if (message.startsWith("OK INIT")) {
				return "OK INIT";
			}
			else {
				logger.log(Level.WARNING,
						"Gestion Message Reception message inconnu");
				return "UNKNOWN";
			}

		}		
		
	


		/**
		 * Envoi une commande
		 * 
		 * @param nbPaletteA
		 * @param nbPaletteB
		 * @return OK si c'est possible, NOK si c'est pas possible, UNKNOWN si
		 *         la réponse du serveur n'a pas été comprise
		 */

		public String sendCommande(int nbPaletteA, int nbPaletteB) {

			String messageAEnvoyer = "COMMAND NA " + nbPaletteA + " NB "
					+ nbPaletteB;

			this.sendMessage(messageAEnvoyer);

			String messageReçu = this.recieveMessage();

			return proceedMessage(messageReçu);

		}
		
		
		
		public void closeConnexion() throws IOException{
			
			super.closeConnexion();
			
			if (this.recieveMessage().startsWith("OK QUIT"))
			{
				socket.close();
			}
			
		}
		

	
		

	}

	/**
	 * Thread du socket réseau Commande
	 * @author sylvain
	 *
	 */
	
	public class GestionMessage extends AbstractControlerSocket {

		
		public GestionMessage(int port, String ip) throws UnknownHostException,
				IOException {
			super(port, ip);

		}

	
		public void run() {
			
			while (!socket.isClosed())
			{
				
				String messageRecu = this.recieveMessage();
				
				System.out.println("Message recu de message : " + messageRecu);
				

			}
			System.out.println("socket close pour message");
			
		}
	}
}
