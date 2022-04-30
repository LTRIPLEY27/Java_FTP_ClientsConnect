/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTP;  
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Client FTP Java que connecta amb un servidor FTP
 * Llista el contingut del directori arrel i descarrega un fitxer
 * 
 * S'ha utilitzat la llibreria Apache commons net que proporciona llibreries
 * i un API per trabajar amb diferents protocolos des de Java
 * http://commons.apache.org/proper/commons-net/
 * 
 *
 */

public class Ftp {

	public static final String IP = "localhost";
	public static final int PORT = 21;
	public static final String USUARI = "tomas";
	public static final String PASSWORD = "1234";
	
	public static void main(String args[]) {
		
		FTPClient clientFtp = new FTPClient();
	
		try {
			// Connecta amb el servidor FTP i inicia sessió
			System.out.println("Connectant i iniciant sessió . . .");
			
                        //IMPLEMENTA
			
			/*
			 *  En el mode passiu sempre és el client qui obra les connexions
			 */

            clientFtp.connect(IP, PORT);  // CONEXIÓN APERTURA
            clientFtp.login(IP, PASSWORD); // PARÁMETROS A AUTENTICAR
            
                        
			//IMPLEMENTA
			
                        
                        menu(clientFtp);


		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			/*
			 * Tanca la sessió i es desconnecta del servidor FTP
			 */
			if (clientFtp != null)
				try {
					System.out.println("Cerrando conexión y desconnectando del servidor . . .");
					if (clientFtp.isConnected()) {
						clientFtp.logout();
						clientFtp.disconnect();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}
	}
        
        
        
        public static void menu(FTPClient clientFtp) throws IOException{
        boolean sortir = false;
        Scanner lector = new Scanner(System.in);
            System.out.println("----MENU----");
            System.out.println("");
            System.out.println("Premeu 1 si voleu mostrar els directoris i fitxers de la carpeta compartida al FTP");
            System.out.println("Premeu 2 si voleu descarregar un fitxer");
            System.out.println("Premeu 3 si voleu borrar un fitxer");
            System.out.println("Premeu 4 per sortir");
            System.out.println("");
        int opcio=lector.nextInt();
        switch (opcio){
            case 1: 
                
                    mostrarDirectori(clientFtp);                    
            break;
            case 2: 
                    DescarregarFitxer(clientFtp);
                    
            break;
            case 3: 
                    esborrarFitxerFTP(clientFtp);
                break;
                
            case 4:
                break;
                
            default: System.out.println("Opció incorrecta, torneu a entrar el número");
            
        }
        
        }
        public static void mostrarDirectori(FTPClient clientFtp) throws IOException{
        
            
            //IMPLEMENTAR
            
        } 
        
        
        public static void DescarregarFitxer(FTPClient clientFtp) throws IOException{
        
        // Fixa els fitxers remots i local
			
        //IMPLEMENTA
        
			// Descarrega un fitxer del servidor FTP
			
        
  }
        
        
         public static void esborrarFitxerFTP(FTPClient ftpClient) throws IOException{
        
        
             //IMPLEMENTACIÓ
             
    }
    
        
        
}


