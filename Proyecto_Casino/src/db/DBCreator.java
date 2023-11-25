package db;

import java.sql.SQLException;


public class DBCreator {
	
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Conectando con la base de datos...");
		DBManager.connect(DBManager.URL);
		
		System.out.println("Creando tabla Usuario");
		DBManager.crearTablaUsuario();
		/*
		System.out.println("Añadiendo Usuarios de ejemplo");
		db.añadirUsuariosEjemplo();
		*/
		System.out.println("Creando tabla Ruleta");
		DBManager.crearTablaRuleta();
		
		System.out.println("Creando tabla Crash");
		DBManager.crearTablaCrash();
		/*
		System.out.println("Añadiendo Datos de ejemplo en ruleta");
		db.añadirRuletaEjemplo();
		*/
		/*
		System.out.println("Añadiendo Datos de ejemplo en Crash");
		DBManager.añadirCrashEjemplo();
		*/
		
		System.out.println("Cerrando conexion...");
		DBManager.disconnect();
		
	}
}
