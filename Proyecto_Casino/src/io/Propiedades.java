package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades extends Properties {

	private static final long serialVersionUID = 1L;

	public Propiedades() {

		super();

	}

	public void guardar() {
		
		// COIN-FLIP 
		setProperty("cara", "resources/images/caraCruz/cara.png");
		setProperty("cruz", "resources/images/caraCruz/cruz.png");
		setProperty("canto", "resources/images/caraCruz/canto.png");
		
		// RULETA
		
		// BLACK JACK
		
		// MENUS
			// Iconos
			setProperty("icono", "resources/images/iconos/logoNoEscasino.png");
			setProperty ("favicon", "resources/images/iconos/favicon.png");
			// Fichas
			setProperty("ficha1", "resources/images/fichas/ficha1.png");
			setProperty("ficha10", "resources/images/fichas/ficha10.png");
			setProperty("ficha25", "resources/images/fichas/ficha25.png");
			setProperty("ficha50", "resources/images/fichas/ficha50.png");
			setProperty("ficha100", "resources/images/fichas/ficha100.png");
			setProperty("ficha1000", "resources/images/fichas/ficha1000.png");

			
		try (FileOutputStream output = new FileOutputStream("config.properties")) {
			store(output, "Configuración de la base de datos");
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void cargar() {
		try (FileInputStream input = new FileInputStream("config.properties")) {
			load(input);
			// Leer propiedades
			System.out.println("URL: " + getProperty("database.url"));
			System.out.println("Usuario: " + getProperty("database.usuario"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
