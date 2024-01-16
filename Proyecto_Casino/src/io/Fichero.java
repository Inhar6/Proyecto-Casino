package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import domain.ApostarSeguro;

public class Fichero {
	
	private static final Logger logger = Logger.getLogger("Ventana Administrador Juegos");
	
	//Lectura de ficheros
	public static String leerFichero(String ruta) {
		 StringBuilder texto = new StringBuilder();
	        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	            	texto.append(linea).append("\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            logger.info("Fallo en la lectura del fichero");
	            JOptionPane.showMessageDialog(null, "Imposible acceder al fichero seleccionado");
	       }
	        return texto.toString();
	}
	
	
	
	public void leerLimitesJugadores(File fichero) {
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
			
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void escribirLimitesJugadores(ApostarSeguro ap) {
		try {
		     ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("LimitesUsuario.dat"));
		     os.writeObject(ap);
			logger.info("Has escrito en el fichero los limites con exito");
			os.close();
			
		}catch(IOException e) {
			e.getStackTrace();
			logger.warning(String.format("Se a producido un error a la hora d escribir en el fichero los limites : %s", e.getMessage()));
		}
		
		
	}
	
	
	
	
}
