package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Usuario;

public class DBManager {
	
	private Connection conn = null;
	
	public void connect(String dbPath) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Usuario> obtenerTodosLosUsuarios(){
		List<Usuario> lstUsuarios = new ArrayList<>();
		try(Statement stmt = conn.createStatement()){
			ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios");
			
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setNombre(rs.getString("nombre"));
				user.setApellidos(rs.getString("apellido"));
				user.setDNI(rs.getString("dni"));
				user.setNombreUsuario(rs.getString("nombreU"));
				user.setContraseña(rs.getString("contraseña"));
				user.setSaldo(rs.getDouble("saldo"));
				user.setNumeroCuenta(rs.getInt("NCuenta"));
				lstUsuarios.add(user);
			}
			return lstUsuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsuarios;
	}
	/*
	 * TABLA USUARIOS
	CREATE TABLE IF NOT EXISTS Usuarios (
    	nombre VARCHAR(255),
    	apellidos VARCHAR(255),
    	dni VARCHAR(20),
    	saldo DOUBLE,
    	numero_cuenta INT,
    	contrasena VARCHAR(255),
    	nombre_usuario VARCHAR(50) PRIMARY KEY
	); 
	
		DATOS PARA INSERTAR EN LA TABLA Usuarios
		INSERT INTO Usuarios (nombre, apellidos, dni, saldo, numero_cuenta, contrasena, nombre_usuario) VALUES
    		('Nombre1', 'Apellido1', '11111111A', 1000.0, 12345, 'contrasena1', 'usuario1'),
    		('Nombre2', 'Apellido2', '22222222B', 1500.0, 67890, 'contrasena2', 'usuario2'),
			('Nombre3', 'Apellido3', '33333333C', 75000.0, 54321, 'contrasena3', 'usuario3'),
			('Nombre4', 'Apellido4', '44444444D', 20000.0, 98765, 'contrasena4', 'usuario4'),
			('Nombre5', 'Apellido5', '55555555E', 300.0, 24680, 'contrasena5', 'usuario5'),
			('Nombre6', 'Apellido6', '66666666F', 450000.0, 11223, 'contrasena6', 'usuario6'),
			('Nombre7', 'Apellido7', '77777777G', 55000.0, 33221, 'contrasena7', 'usuario7'),
			('Nombre8', 'Apellido8', '88888888H', 1200.0, 76543, 'contrasena8', 'usuario8'),
			('Nombre9', 'Apellido9', '99999999I', 800.0, 19876, 'contrasena9', 'usuario9'),
			('Nombre10', 'Apellido10', '10101010J', 2200.0, 23456, 'contrasena10', 'usuario10'),
			('Nombre11', 'Apellido11', '11111111K', 170000.0, 90876, 'contrasena11', 'usuario11'),
			('Nombre12', 'Apellido12', '12121212L', 1600.0, 65432, 'contrasena12', 'usuario12'),
			('Nombre13', 'Apellido13', '13131313M', 1800.0, 56789, 'contrasena13', 'usuario13'),
			('Nombre14', 'Apellido14', '14141414N', 4000.0, 11234, 'contrasena14', 'usuario14'),
			('Nombre15', 'Apellido15', '15151515O', 300000.0, 98765, 'contrasena15', 'usuario15');	
	 */
	
	/*
	 * TABLA RULETA
	 CREATE TABLE Ruleta (
    	numero INTEGER,
    	ganancia DOUBLE,
    	tirada INTEGER,
    	nombre_usuario VARCHAR(50),
    FOREIGN KEY (nombre_usuario) REFERENCES Usuarios(nombre_usuario)
	);
	
	DATOS A INSERTAR EN LA TABLA Ruleta
	INSERT INTO Juegos (numero, ganancia, tirada, nombre_usuario) VALUES
	    (1, 100.0, 10, 'usuario1'),
	    (2, 150.0, 15, 'usuario2'),
	    (3, 80.0, 8, 'usuario3'),
	    (4, 200.0, 20, 'usuario4'),
	    (5, 120.0, 12, 'usuario5'),
	    (6, 90.0, 9, 'usuario6'),
	    (7, 180.0, 18, 'usuario7'),
	    (8, 60.0, 6, 'usuario8'),
	    (9, 130.0, 13, 'usuario9'),
	    (10, 110.0, 11, 'usuario10'),
	    (11, 170.0, 17, 'usuario11'),
	    (12, 50.0, 5, 'usuario12'),
	    (13, 140.0, 14, 'usuario13'),
	    (14, 95.0, 9, 'usuario14'),
	    (15, 160.0, 16, 'usuario15');
	 */
}
