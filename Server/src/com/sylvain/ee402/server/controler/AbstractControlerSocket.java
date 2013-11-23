package com.sylvain.ee402.server.controler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractControlerSocket implements Runnable {

	Logger logger = Logger.getLogger("logger");
	
	protected Socket socket;
	
	protected DataOutputStream outToServer;
	
	protected BufferedReader inFromServer;
	

	/**
	 * Creation d'un socket réseau
	 * @param port
	 * @param ip
	 */
	public AbstractControlerSocket(int port, String ip) {
		
		try {
			socket = new Socket(ip, port);
			outToServer = new DataOutputStream(socket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			logger.log(Level.SEVERE, "AbstractControlerSocket : UnknowHostException");
			e.printStackTrace();
			
		} catch (IOException e) {
			logger.log(Level.SEVERE, "AbstractControlerSocket : IOException");
			e.printStackTrace();
			
		}
		

	}
	
	/**
	 * Envoi d'un message via le socket, ajout d'un \r\n automatique à la fin du message
	 * @param message
	 */
	public void sendMessage(String message) {		
		try {
			outToServer.writeBytes(message+"\r\n");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception dans l'envoi d'un message de la classe AbstractControlerSocket");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Recois un message se terminant pour \n ou \r ou \r\n
	 * @return la string recue
	 * @return string vide si une IOException a été soulevée
	 * @throws IOException
	 */
	public String recieveMessage() {
		
		String messageRecu = "";		
				
		try {
			messageRecu = inFromServer.readLine();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception dans la reception d'un message de la classe AbstractControlerSocket");
			e.printStackTrace();
		}
		
		return messageRecu;
		
	}
	
	/**
	 * Traite le message recu
	 * @param message
	 * @return
	 */
	public String proceedMessage(String message) {
		return message;	
	}
	
	/**
	 * Ferme la connexion du socket
	 * @throws IOException
	 */
	public void closeConnexion() throws IOException{
		
		this.sendMessage("QUIT");
		
	}
	
	/**
	 * Run du thread
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
