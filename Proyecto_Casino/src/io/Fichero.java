package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

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
	
	
}
