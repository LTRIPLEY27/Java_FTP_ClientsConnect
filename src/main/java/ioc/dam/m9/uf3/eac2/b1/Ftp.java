/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf3.eac2.b1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.net.finger.FingerClient;
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

	public static final String IP = "127.0.0.1";
	public static final int PORT = 21;
	public static boolean isConnected = false;
	public static String user;
	public static String pass;
	public static Scanner ask = new Scanner(System.in);
	
	public static void main(String args[]) {
		FTPClient clientFtp = new FTPClient(); // nueva instancia ftpclient para inicializar la conexión
		
		try {
			/*
			 *  En el mode passiu sempre és el client qui obra les connexions
			 */
          
            do {
            	 // CONEXIÓN APERTURA   
                System.out.println("Introdueix usuari");
        		user = ask.next();
        		System.out.println("Introdueix password");
        		pass = ask.next();
        		
        		clientFtp.connect(IP, PORT); 
        		clientFtp.login(user, pass); // PARÁMETROS A AUTENTICAR
        		int respuesta = clientFtp.getReplyCode();
        		System.out.println("Resposta rebuda de connexio FTP: " + respuesta);
        		
        		if(FTPReply.isPositiveCompletion(respuesta)) {
        			System.out.println("Ens hem conectat satisfactoriament");
                    isConnected = true;
        		} else {
        			System.out.println("Tenim problemes de connexió!!!");
                    System.out.println("Probablement tens el password o user incorrecte");
        		}	
            }while(!isConnected);
            
            clientFtp.changeWorkingDirectory("/");
            System.out.println("S'ha fet el canvi satisfactoriament");
            clientFtp.enterLocalPassiveMode();     
            clientFtp.setFileType(FTPClient.BINARY_FILE_TYPE);
			//IMPLEMENTA
            // DISPARO DEL MENÚ PARA VERIFICAR FICHEROS 
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
        	System.out.println("Llistant el directorio arrel del servidor . . \n");

        	FTPFile[] files = clientFtp.listFiles();
        	for  ( FTPFile file : files ) {
        		System.out.println(file.getName());
        	}

        	clientFtp.enterLocalPassiveMode();;
    		menu(clientFtp);
            
        } 
        
        
        public static void DescarregarFitxer(FTPClient clientFtp) throws IOException{
        
        // Fixa els fitxers remots i local
			
        //IMPLEMENTA
        
			// Descarrega un fitxer del servidor FTP
			
        	Boolean isOk = false;
        	String pathFile = "C:\\Users\\isabe\\Documents\\IOC\\M09\\UF3\\DAM_M09B0_EAC2_part2_Calzadilla_C";//DIRECTORIO DONDE SE ALOJARÁ EL NUEVO ARCHIVO
        	OutputStream output = null;//SALIDAS INPUT Y OUTPUT PARA TRANSFERENCIA DEL ARCHIVO
        	InputStream imput = null;
        	
        	System.out.println("Indique el archivo a transferir?");
        	String remote = ask.next();
        	System.out.println("Indique el nombre del nuevo archivo archivo");
        	String file = ask.next();

        	while(!isOk) {
        		try {
        			File down = new File(pathFile + file);
        			output = new BufferedOutputStream(new FileOutputStream(down));
        			imput = clientFtp.retrieveFileStream(remote); // LLAMA AL ARCHIVO DEL SERVER
        			System.out.println("Descarregant fitxer " + "'" + remote + "'");
        			byte[] byt = new byte [4096];//arrays de bytes para reescritura del archivo
                	int x = -1;
                	
                    while((x = imput.read(byt)) != -1) {
                    	output.write(byt, 0, x);
                    	System.out.println("Descargando");
                    }
        			
        			isOk = clientFtp.completePendingCommand(); //RECIBE EL BOOLEAN CON EL VALOR DE LA OPERACIÓN.
        			if (isOk) {
        				System.out.println("Transferencia realitzada!!!");
                    }
        			
        			output.close();
        			imput.close();
            	} catch(Exception e) {
            		System.out.println(e.getMessage());
                    System.out.println("La ruta no existeix, torna-la a introduir");
                    isOk = false;
            	}
        	}
        	
        	clientFtp.enterLocalPassiveMode();
     
        	menu(clientFtp);
  }
        
        
         public static void esborrarFitxerFTP(FTPClient ftpClient) throws IOException{
        
        	 String archivo = "C:\\Users\\isabe\\Documents\\IOC\\M09\\UF3\\DAM_M09B0_EAC2_part2_Calzadilla_C";
        	 boolean isOk = false;
        	 System.out.println("Indique el nombre del archivo");
         	 String file = ask.next();
         	 
        	 try {
        		 isOk = ftpClient.deleteFile(archivo + file);
            	 if(isOk) {
            		 System.out.println("Hasta la vista baby");
            	 } 
        	 } catch(Exception e) {
         		System.out.println(e.getMessage());
         		System.out.println("Verifica el directorio");
         	}
        	 
        	 menu(ftpClient);
    }
    
        
        
}


