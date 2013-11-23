package com.sylvain.ee402.server.controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.logging.Level;

import model.Carton;
import model.Data;
import model.Message;
import model.TypeMessage;
import model.TypePieceProduction;

import common.Erreur;
import common.Parametres;
import common.TestErreur;



/**
 * Controleur qui gère la fenetre, et les sockets commande/message
 * @author sylvain
 *
 */

public class GestionFenetre extends AbstractControler {

	private Data data; 
	private GestionCommande gestionCommande;
	private GestionMessage gestionMessage;
	 
	/**
	 * Lance également les deux threads de communication réseau
	 * @param data
	 */
	public GestionFenetre(Data data) {
		
		this.data = data;
		
		Integer portCommande = -1;
		Integer portMessage = -1;
		String ip = "";
		try {
			portCommande = Integer.valueOf(Parametres.getValeurParametre("portCommande"));
			portMessage = Integer.valueOf(Parametres.getValeurParametre("portMessage"));
			ip = Parametres.getValeurParametre("ipServeur");
			
		} catch (Erreur e) {
			logger.log(Level.SEVERE, "Impossible de charger le fichier de configuration");
			e.printStackTrace();
			return ;
		}
			
		try {
			gestionCommande = new GestionCommande(portCommande, ip);
			gestionMessage = new GestionMessage(portMessage,ip);
			
			Thread gestionMessageThread = new Thread(gestionMessage);
			Thread gestionCommandeThread = new Thread(gestionCommande);
			gestionMessageThread.start();
			gestionCommandeThread.start();
			
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Impossible d'instantier les controleurs de sockets");
			e.printStackTrace();
			return;
		}
		
		
	}
	
	/**
	 * Récupère le thread gestion commande
	 * @return gestionCommande
	 */
	public GestionCommande getGestionCommande() {
		return gestionCommande;
	}

	/**
	 * Récupère le thread gestion message
	 * @return gestionMessage
	 */
	public GestionMessage getGestionMessage() {
		return gestionMessage;
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
		 * Envoi dans le cadre du mode debug une commande d'erreur à linux
		 * @param erreur
		 */
		public void lancerCommandeDebug(TestErreur erreur){
			
			String machaine = "";
			
			if (erreur == TestErreur.ARRET_URGENCE)
			{
				machaine = "TEST ARRETURGENT";
			}
			else if (erreur == TestErreur.ERREUR_IMPRESSION)
			{
				machaine = "TEST IMPRESSION";
			}
			else if (erreur == TestErreur.MANQUE_PALETTE)
			{
				machaine = "TEST MANQUEPALETTE";
			}
			else if (erreur == TestErreur.MANQUE_PIECE)
			{
				machaine = "TEST TIMEOUT";
			}
			else if(erreur == TestErreur.PAS_CARTON_DISPO)
			{
				machaine = "TEST PASDECARTONS";
			}


			if (machaine != "")
			{
				gestionCommande.sendMessage(machaine);
			}
			else
			{
				logger.log(Level.WARNING,"Commande debug impossible à envoyer dans GestionFenetre");
			}
					
			
		}

		/**
		 * Envoi l'ensemble des paramètres à initialiser pour la partie linux
		 * @return OK INIT si l'initialisation s'est bien passée
		 * @return UNKNOWN si la réponse du serveur ne correspond à rien
		 */
		
		public String sendInitialisation() {

			String messageAEnvoyer = "INIT NA "
					+ data.getNombrePaletteADuJour() + " NB "
					+ data.getNombrePaletteBDuJour() + " SCA "
					+ data.getSeuilCritiqueA() + " SCB "
					+ data.getSeuilCritiqueB() + " DA " + data.getDimensionsA()
					+ " DB " + data.getDimensionsB() + " NBPCA "
					+ data.getNbPiecesParCartonsA() + " NBPCB "
					+ data.getNbPiecesParCartonsB() + " TOUT "
					+ data.getTempsTimeout() + " NBCPA "
					+ data.getNbCartonsParPaletteA() + " NBCPB "
					+ data.getNbCartonsParPaletteB();

			this.sendMessage(messageAEnvoyer);
			
			String messageRecu = this.recieveMessage();
			
			System.out.println("Reception init " + messageRecu);
			
			return this.proceedMessage(messageRecu);

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

		/**
		 * Traite le message reçu 
		 * @param message
		 * @return Message
		 */
		public Message traiterMessage(String message) {
			
			Message messageVue = new Message();
			
			//Serveur deconnecte
			if (message == null)
			{
				messageVue.setTypeMessage(TypeMessage.MESSAGE);
				messageVue.setMessage("Le serveur s'est deconnecte");
			}
			else if (message.startsWith("ERROR"))
			{
				messageVue.setTypeMessage(TypeMessage.ERROR);
				message = message.substring(6);
				String[] messageParse = message.split("\r\n");
				messageVue.setMessage(messageParse[0]);
				
			}
			else if (message.startsWith("INFO"))
			{
				messageVue.setTypeMessage(TypeMessage.MESSAGE);	
				message = message.substring(5);
				String[] messageParseVue = message.split("\r\n");
				messageVue.setMessage(messageParseVue[0]);
				
				if (message.startsWith("PRODUCTION END"))
				{
					data.setProductionTermine(true);
				}
				else if (message.startsWith("ENTREPOT"))
				{
					message = message.substring(9);
					String[] messageParse = message.split(" |\r\n");
					
					// Dans un premier temps on a le nb de palettes de A puis le nombre de palettes de B
					
					System.out.println("Entrepot A : "+messageParse[1]);
					System.out.println("Entrepot B : "+messageParse[3]);
					
					data.setPaletteAFabrique(Integer.valueOf(messageParse[1]));
					data.setPaletteBFabrique(Integer.valueOf(messageParse[3]));	 				
				}
				else if (message.startsWith("CARTONSPALETTE"))
				{
					message = message.substring(15);
					String[] messageParse = message.split(" |\r\n");
					
					TypePieceProduction typePieceProduction;
					
					if (messageParse[1].startsWith("A"))
					{
						typePieceProduction = TypePieceProduction.A;
					}
					else if(messageParse[1].startsWith("B"))
					{
						typePieceProduction = TypePieceProduction.B;
					}
					else
					{
						typePieceProduction = TypePieceProduction.UNKNOWN;
					}
					
					data.setTypePiecePaletteEnCours(typePieceProduction);
					data.setNbCartonsPaletteProduction(Integer.valueOf(messageParse[3]));
					
				}
				else if (message.startsWith("CARTON"))
				{
					message = message.substring(7);
					String[] messageParse = message.split(" |\r\n");
					
					TypePieceProduction typePieceProduction;
					
					if (messageParse[1].startsWith("A"))
					{
						typePieceProduction = TypePieceProduction.A;
					}
					else if(messageParse[1].startsWith("B"))
					{
						typePieceProduction = TypePieceProduction.B;
					}
					else
					{
						typePieceProduction = TypePieceProduction.UNKNOWN;
					}
					
					int numeroLot = Integer.valueOf(messageParse[3]);
					
					int nbPieces = Integer.valueOf(messageParse[5]);
					
					long date = Long.valueOf(messageParse[7]);
					  	
					Carton carton = new Carton(typePieceProduction, numeroLot, nbPieces, date);
				
					data.setCarton(carton);
				}
				
				
			}
			else if (message.startsWith("OK QUIT"))
			{
				try {
					socket.close();
				} catch (IOException e) {
					logger.log(Level.SEVERE,
							"Impossible de fermer le socket message");
					e.printStackTrace();
				}
				messageVue.setTypeMessage(TypeMessage.QUIT);				
			}
			else
			{
				messageVue.setTypeMessage(TypeMessage.UNKNOWN);
				return messageVue;
			}
			
			return messageVue;
			
		}
		

		public void envoiReponseOperateurErreur (boolean reponse)
		{
			if (reponse)
			{
				this.sendMessage("OK ERROR");
			}
			else
			{
				this.sendMessage("NOK ERROR");
			}
		}
		
		public void run() {
			
			while (!socket.isClosed())
			{
				
				String messageRecu = this.recieveMessage();
				
				System.out.println("Message recu de message : " + messageRecu);
				
				Message messagePourVue = this.traiterMessage(messageRecu);
				
				if (!(messagePourVue.getTypeMessage() == TypeMessage.MESSAGE))
				{
					data.setMessageRecu(messagePourVue);
				}

			}
			System.out.println("socket close pour message");
			
		}
	}
}
