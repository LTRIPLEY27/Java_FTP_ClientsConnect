/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b2;

import java.io.BufferedReader;
/**
 *
 * @author tomas
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainClient {
    public static void main(String[] args) {
    	//SERVIDORES
    	Scanner ask = new Scanner(System.in);
    	String huesped = "localhost";
    	int puerto = 8888;
    	PrintWriter write;
    	BufferedReader read;
    	boolean respuesta = false;
    	String palabra = "";
    	
        try {
            //IMPLEMENTA
            //Llegeix del servidor
            //escriu al servidor
            
           //llegeix del teclat
        	Socket sockets = new Socket(huesped, puerto); // CONEXIÓN SOCKET
        	//PrintWrites para leer
        	write = new PrintWriter(sockets.getOutputStream(), true);
        	read = new BufferedReader(new InputStreamReader(sockets.getInputStream()));
            
        	System.out.println("Escribe la palabra a traducir");
        	System.out.println("Conectado al servidor. Finalice con EXIT !!!");
			
			while(!respuesta) {
				System.out.println("Escribe la palabra a traducir");
				palabra = ask.next();
				
				if(palabra.equals("") || "exit".equalsIgnoreCase(palabra)) {
					System.out.println("SALIENDO");
					respuesta = true;
					write.println("Saliendo");	
				} 
				System.out.println(palabra);
				write.println(palabra);
				read.readLine();
					
			}
			
			read.close();
       
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}