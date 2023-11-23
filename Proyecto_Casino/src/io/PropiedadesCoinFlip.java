package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropiedadesCoinFlip extends Properties {

	private static final long serialVersionUID = 1L;

	public PropiedadesCoinFlip() {

		super();

	}

	public void guardar() {

		// TODAS LAS PROPIEDADES AQUI
		setProperty("cara", "resources/images/caraCruz/cara.png");

		setProperty("cruz", "resources/images/caraCruz/cruz.png");
		
		setProperty("canto", "resources/images/caraCruz/canto.png");

		// ...

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
