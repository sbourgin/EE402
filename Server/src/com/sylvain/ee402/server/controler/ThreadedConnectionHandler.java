/* The Connection Handler Class - Written by Derek Molloy for the EE402 Module
* See: ee402.eeng.dcu.ie
*/

package com.sylvain.ee402.server.controler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.sylvain.ee402.server.model.Contact;

public class ThreadedConnectionHandler extends Thread
{
    private Socket clientSocket = null;                                // Client socket object
    private ObjectInputStream is = null;                        // Input stream
    private ObjectOutputStream os = null;                        // Output stream
    private Services _services;
    
        // The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        _services = new Services();
       
    }

    // Will eventually be the thread execution method - can't pass the exception back
    public void run() {
         try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            while (this.readCommand()) {}
         }
         catch (IOException e)
         {
                System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
         }
    }

    // Receive and process incoming string commands from client socket
    private boolean readCommand() {
        String s = null;
        try {
            s = (String) is.readObject();
        }
        catch (Exception e){ // catch a general exception
                this.closeSocket();
            return false;
        }
        System.out.println("01. <- Received a String object from the client (" + s + ").");
        
        // At this point there is a valid String object
        // invoke the appropriate function based on the command
        if (s.equalsIgnoreCase("GET_LIST_CONTACTS")){
            this.getListContacts();
        }
        else {
            this.sendError("Invalid command: " + s);
        }
        return true;
    }

    // Use our custom DateTimeService Class to get the date and time
    private void getListContacts() {        // use the date service to get the date
        List<Contact> locContacts = _services.getListContacts();
        this.send(locContacts);
    }

    // Send a generic object back to the client
    private void send(Object o) {
        try {
            System.out.println("02. -> Sending (" + o +") to the client.");
            this.os.writeObject(o);
            this.os.flush();
        }
        catch (Exception e) {
            System.out.println("XX." + e.getStackTrace());
        }
    }
    
    // Send a pre-formatted error message to the client
    public void sendError(String message) {
        this.send("Error:" + message);        //remember a String IS-A Object!
    }
    
    // Close the client socket
    public void closeSocket() { //gracefully close the socket connection
        try {
            this.os.close();
            this.is.close();
            this.clientSocket.close();
        }
        catch (Exception e) {
            System.out.println("XX. " + e.getStackTrace());
        }
    }
}