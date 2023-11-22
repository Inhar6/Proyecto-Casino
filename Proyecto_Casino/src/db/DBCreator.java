package db;

import java.sql.SQLException;

public class DBCreator {
	
	public static void main(String[] args) throws SQLException {
		
		DBManager db = new DBManager();
		System.out.println("Conectando con la base de datos...");
		db.connect(DBManager.URL);
		
		System.out.println("Creando tabla Usuario");
		db.crearTablaUsuario();
		/*
		System.out.println("Añadiendo Usuarios de ejemplo");
		db.añadirUsuariosEjemplo();
		*/
		System.out.println("Creando tabla Ruleta");
		db.crearTablaRuleta();
		
		/*
		System.out.println("Añadiendo Datos de ejemplo en ruleta");
		db.añadirRuletaEjemplo();
		*/
		
		System.out.println("Cerrando conexion...");
		db.disconnect();
		
	}
}
