package com.sylvain.ee402.client.controler;

/* The Client Class - Written by Derek Molloy for the EE402 Module
* See: ee402.eeng.dcu.ie
*
*
*/


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.sylvain.ee402.common.model.Commands;
import com.sylvain.ee402.common.model.NetworkMessage;

public class Network {
        
    private static int portNumber = 5050;
    private String serverIp = "127.0.0.1"; //TODO faire ça dynamique
    private Socket socket = null;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;

        // the constructor expects the IP address of the server - the port is fixed
    public Network() {
            if (!connectToServer()) {
                    System.out.println("XX. Failed to open socket connection to: " + serverIp);
            }
    }

    private boolean connectToServer() {
            try { // open a new socket to the server
                    this.socket = new Socket(serverIp,portNumber);
                    this.os = new ObjectOutputStream(this.socket.getOutputStream());
                    this.is = new ObjectInputStream(this.socket.getInputStream());
                    System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress()
                                    + " on port: " + this.socket.getPort());
                    System.out.println(" -> from local address: " + this.socket.getLocalAddress()
                                    + " and port: " + this.socket.getLocalPort());
            }
        catch (Exception e) {
                System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
                System.out.println(" Exception: " + e.toString());        
                return false;
        }
                return true;
    }

    public Object sentCommand(NetworkMessage parNetworkMessage) {
    	Object response = null;
            System.out.println("01. -> Sending Command (" + parNetworkMessage + ") to the server...");
            this.send(parNetworkMessage);
            
            try{
            		response = receive();
                    System.out.println("05. <- The Server responded with: ");
                    System.out.println(" <- " + response);
            }
            catch (Exception e){
                    System.out.println("XX. There was an invalid object sent back from the server");
            }
            
            return response;
            
    }
        
    // method to send a generic object.
    private void send(Object o) {
                try {
                 System.out.println("02. -> Sending an object...");
                 os.writeObject(o);
                 os.flush();
                }
         catch (Exception e) {
                 System.out.println("XX. Exception Occurred on Sending:" + e.toString());
                }
    }

    // method to receive a generic object.
    private Object receive()
    {
                Object o = null;
                try {
                        System.out.println("03. -- About to receive an object...");
                 o = is.readObject();
                 System.out.println("04. <- Object received...");
                }
         catch (Exception e) {
                 System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
                }
                return o;
    }
    
    public void closeSocket() {
    	try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


}
