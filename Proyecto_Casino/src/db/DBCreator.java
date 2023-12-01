package db;

import java.sql.SQLException;


public class DBCreator {
	
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Conectando con la base de datos...");
		
		
		System.out.println("Creando tabla Usuario");
		DBManager.crearTablaUsuario();
		/*
		System.out.println("Añadiendo Usuarios de ejemplo");
		db.añadirUsuariosEjemplo();
		*/
		
		System.out.println("Creando tabla CuentaBancaria");
		DBManager.crearTablaCuentaBancaria();
		
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
		System.out.println("Creando tabla Black Jack");
		DBManager.crearTablaBlackJack();
		/*
		System.out.println("Añadiendo Datos de ejemplo en Black Jack");
		DBManager.añadirBlackJackEjemplo();
		*/
		System.out.println("Creando tabla Balance");
		DBManager.crearTablaBalance();
		/*
		System.out.println("Añadiendo Datos de ejemplo en Balance");
		DBManager.añadirBalanceEjemplo();
		*/
		System.out.println("Cerrando conexion...");
	}
}
