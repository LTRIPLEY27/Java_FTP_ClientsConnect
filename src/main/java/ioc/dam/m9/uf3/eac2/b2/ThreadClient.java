/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b2;

/**
 *
 * @author tomas
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class ThreadClient extends Thread {
    private Socket client;
    private Scanner in;
    private PrintWriter out;
    

    public ThreadClient(Socket client) {
        try {
            this.client = client;
            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    @Override
    public void run() {
        String msg;
        SoftCatalaClient soft = new SoftCatalaClient();
        String traduccio;
        
        while(( traduccio = in.nextLine()) != null) {
        	if("exit".equalsIgnoreCase(traduccio)) {
        		out.println("Exit");
        		break;
        	}
        	
        	msg = soft.translate(traduccio);
        	out.println(msg);
        }

        try {
            
            //IMPLEMENTA
            in.close();
            out.close();
            client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}