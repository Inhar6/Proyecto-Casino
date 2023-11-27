package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Usuario;

public class DBManager {
	
	private static Connection conn = null;
	//public static final String URL = "resources/db/NoEscasino.db";
	/*
	public static void connect(String dbPath) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	 private static final String URL = "jdbc:sqlite:resources/db/NoEscasino.db";

	    // Método para obtener una conexión a la base de datos
	    public static Connection obtenerConexion() throws SQLException {
	        return DriverManager.getConnection(URL);
	    }

	    // Método para cerrar una conexión
	    public static void cerrarConexion(Connection conexion) {
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	public static List<Usuario> obtenerTodosLosUsuarios(){
		List<Usuario> lstUsuarios = new ArrayList<>();
		try(Connection conn = obtenerConexion();
			Statement stmt = conn.createStatement()){
			ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario");
			while(rs.next()) {
				Usuario user = new Usuario();
				//Usuario
				user.setNombre(rs.getString("nombre"));
				user.setApellidos(rs.getString("apellidos"));
				user.setDNI(rs.getString("dni"));
				user.setNombreUsuario(rs.getString("nombre_usuario"));
				user.setContraseña(rs.getString("contrasena"));
				user.setSaldo(rs.getDouble("saldo"));
				user.setNumeroCuenta(rs.getInt("numero_cuenta"));
				//Añadir a la lista el usuario
				lstUsuarios.add(user);
			}
			//Añadir la tabla Ruleta al usuario
			ResultSet rsRuleta = stmt.executeQuery("SELECT * FROM Usuario u, Ruleta r WHERE u.nombre_usuario = r.nombre_usuario");
			while(rsRuleta.next()) {
				for(Usuario user: lstUsuarios) {
					if(user.getNombreUsuario().equals(rsRuleta.getString("nombre_usuario"))) {
						user.addMapaRuleta(rs.getInt("tirada"), rs.getInt("numero"), rs.getDouble("ganancia"));
					}
				}
			}
			//Añadir la tabla Crash al usuario
			ResultSet rsCrash = stmt.executeQuery("SELECT * FROM Usuario u, Crash c WHERE u.nombre_usuario = c.nombre_usuario ");
			while(rsCrash.next()) {
				for(Usuario user :lstUsuarios) {
					if(user.getNombreUsuario().equals(rsCrash.getString("nombre_usuario"))) {
						user.addMapaCrash(rsCrash.getInt("tirada"), rsCrash.getString("resultado"), rsCrash.getDouble("multiplicador"), rsCrash.getDouble("ganancia"));
					}
				}	
			}
			//Añadir la tabla BlackJack al usuario
			ResultSet rsBlackJack = stmt.executeQuery("SELECT * FROM Usuario u, BlackJack b WHERE u.nombre_usuario = b.nombre_usuario ");
			while(rsBlackJack.next()) {
				for(Usuario user :lstUsuarios) {
					if(user.getNombreUsuario().equals(rsBlackJack.getString("nombre_usuario"))) {
						user.addMapaBlackJack(rsBlackJack.getInt("partida"), rsBlackJack.getString("ganador"), rsBlackJack.getInt("puntuacion"), rsBlackJack.getDouble("ganancia"));
					}
				}	
			}
			return lstUsuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsuarios;
	}
	
	public static boolean existeUsuario(String nombreU) {
		String sql= "SELECT * FROM Usuario WHERE nombre_Usuario = ?";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, nombreU);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Añdir usuario
	public static void añadirUsuario(Usuario user) {
		if(!existeUsuario(user.getNombreUsuario())) {
			//Añadir un existe usuario
			String sql = "INSERT INTO Usuario (nombre, apellidos, dni, saldo, numero_cuenta, contrasena, nombre_usuario) VALUES (?, ?, ?, 0, 0, ?, ?);";
			try (Connection conn = obtenerConexion();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, user.getNombre());
				pstmt.setString(2, user.getApellidos());
				pstmt.setString(3, user.getDNI());
				pstmt.setString(4, user.getContraseña());
				pstmt.setString(5, user.getNombreUsuario());
				pstmt.executeUpdate();
				System.out.println("Registro exitoso");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("El usuario ya existe. No se puede añadir.");
		}
		
	}
	//Eliminar un usuario
	public static void eliminarUsuario(Usuario user) {
		String sql = "DELETE FROM Usuario WHERE nombre_usuario = ?;";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getNombreUsuario());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Edita el nombre y la contraseña
	public static void editarUsuario(Usuario user) {
		String sql = "UPDATE Usuario SET nombre = ?, contrasena = ? WHERE nombre_usuario = ?;";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getNombre());
			pstmt.setString(2, user.getContraseña());
			pstmt.setString(3, user.getNombreUsuario());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearTablaUsuario() {
		try(Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Usuario (\n"
					+ "    	nombre VARCHAR(255),\n"
					+ "    	apellidos VARCHAR(255),\n"
					+ "    	dni VARCHAR(20),\n"
					+ "    	saldo DOUBLE,\n"
					+ "    	numero_cuenta INT,\n"
					+ "    	contrasena VARCHAR(255),\n"
					+ "    	nombre_usuario VARCHAR(50) PRIMARY KEY ); ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearTablaRuleta() {
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate(" CREATE TABLE IF NOT EXISTS Ruleta (\n"
					+ "    	numero INTEGER,\n"
					+ "    	ganancia DOUBLE,\n"
					+ "    	tirada INTEGER,\n"
					+ "    	nombre_usuario VARCHAR(50),\n"
					+ "     FOREIGN KEY (nombre_usuario) REFERENCES Usuario(nombre_usuario));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void crearTablaCrash() {
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(" CREATE TABLE IF NOT EXISTS Crash (\n"
					+ "		tirada INTEGER,\n"
					+ "		resultado VARCHAR(10),\n"
					+ "		multiplicador DOUBLE,\n"
					+ "		ganancia DOUBLE,\n"
					+ "		nombre_usuario VARCHAR(50),\n"
					+ " 	FOREIGN KEY (nombre_usuario) REFERENCES Usuario(nombre_usuario));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearTablaBlackJack() {
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(" CREATE TABLE IF NOT EXISTS BlackJack (\n"
					+ "		partida INTEGER,\n"
					+ "		ganador VARCHAR(10),\n"
					+ "		puntuacion DOUBLE,\n"
					+ "		ganancia DOUBLE,\n"
					+ "		nombre_usuario VARCHAR(50),\n"
					+ " 	FOREIGN KEY (nombre_usuario) REFERENCES Usuario(nombre_usuario));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * AÑADIR DATOS DE EJEMPLO
	 */
	public static void añadirUsuariosEjemplo() {
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Usuario (nombre, apellidos, dni, saldo, numero_cuenta, contrasena, nombre_usuario) VALUES\r\n"
				+ "    		('Nombre1', 'Apellido1', '11111111A', 1000.0, 12345, 'contrasena1', 'usuario1'),\r\n"
				+ "    		('Nombre2', 'Apellido2', '22222222B', 1500.0, 67890, 'contrasena2', 'usuario2'),\r\n"
				+ "			('Nombre3', 'Apellido3', '33333333C', 75000.0, 54321, 'contrasena3', 'usuario3'),\r\n"
				+ "			('Nombre4', 'Apellido4', '44444444D', 20000.0, 98765, 'contrasena4', 'usuario4'),\r\n"
				+ "			('Nombre5', 'Apellido5', '55555555E', 300.0, 24680, 'contrasena5', 'usuario5'),\r\n"
				+ "			('Nombre6', 'Apellido6', '66666666F', 450000.0, 11223, 'contrasena6', 'usuario6'),\r\n"
				+ "			('Nombre7', 'Apellido7', '77777777G', 55000.0, 33221, 'contrasena7', 'usuario7'),\r\n"
				+ "			('Nombre8', 'Apellido8', '88888888H', 1200.0, 76543, 'contrasena8', 'usuario8'),\r\n"
				+ "			('Nombre9', 'Apellido9', '99999999I', 800.0, 19876, 'contrasena9', 'usuario9'),\r\n"
				+ "			('Nombre10', 'Apellido10', '10101010J', 2200.0, 23456, 'contrasena10', 'usuario10'),\r\n"
				+ "			('Nombre11', 'Apellido11', '11111111K', 170000.0, 90876, 'contrasena11', 'usuario11'),\r\n"
				+ "			('Nombre12', 'Apellido12', '12121212L', 1600.0, 65432, 'contrasena12', 'usuario12'),\r\n"
				+ "			('Nombre13', 'Apellido13', '13131313M', 1800.0, 56789, 'contrasena13', 'usuario13'),\r\n"
				+ "			('Nombre14', 'Apellido14', '14141414N', 4000.0, 11234, 'contrasena14', 'usuario14'),\r\n"
				+ "			('Nombre15', 'Apellido15', '15151515O', 300000.0, 98765, 'contrasena15', 'usuario15');	")){
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void añadirRuletaEjemplo() {
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Ruleta (numero, ganancia, tirada, nombre_usuario) VALUES\r\n"
				+ "	    (1, 100.0, 10, 'usuario1'),\r\n"
				+ "	    (2, 150.0, 15, 'usuario2'),\r\n"
				+ "	    (3, 80.0, 8, 'usuario3'),\r\n"
				+ "	    (4, 200.0, 20, 'usuario4'),\r\n"
				+ "	    (5, 120.0, 12, 'usuario5'),\r\n"
				+ "	    (6, 90.0, 9, 'usuario6'),\r\n"
				+ "	    (7, 180.0, 18, 'usuario7'),\r\n"
				+ "	    (8, 60.0, 6, 'usuario8'),\r\n"
				+ "	    (9, 130.0, 13, 'usuario9'),\r\n"
				+ "	    (10, 110.0, 11, 'usuario10'),\r\n"
				+ "	    (11, 170.0, 17, 'usuario11'),\r\n"
				+ "	    (12, 50.0, 5, 'usuario12'),\r\n"
				+ "	    (13, 140.0, 14, 'usuario13'),\r\n"
				+ "	    (14, 95.0, 9, 'usuario14'),\r\n"
				+ "	    (15, 160.0, 16, 'usuario15');")){
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void añadirCrashEjemplo() {
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Crash (tirada, resultado, multiplicador, ganancia, nombre_usuario) VALUES\r\n"
				+ "	    (1, 'WIN', 1.13, 1123.0 ,'usuario1'),\r\n"
				+ "	    (2, 'WIN', 1.45, 9954.0 ,'usuario2'),\r\n"
				+ "	    (3, 'LOSE', 2.13, 2345.0 ,'usuario3'),\r\n"
				+ "	    (4, 'LOSE', 3.23, 1245.0 ,'usuario4'),\r\n"
				+ "	    (5, 'WIN', 1.23, 854.0 ,'usuario5'),\r\n"
				+ "	    (6, 'LOSE', 1.65, 3567.0 ,'usuario6'),\r\n"
				+ "	    (7, 'WIN', 4.12, 346.0 ,'usuario7'),\r\n"
				+ "	    (8, 'LOSE', 4.13, 3467.0 ,'usuario8'),\r\n"
				+ "	    (9, 'WIN', 1.65, 4576.0 ,'usuario9'),\r\n"
				+ "	    (10, 'WIN', 1.34, 1123.0 ,'usuario10'),\r\n"
				+ "	    (11, 'LOSE', 2.45, 453.0 ,'usuario11'),\r\n"
				+ "	    (12, 'LOSE', 5.13, 5688.0 ,'usuario12'),\r\n"
				+ "	    (13, 'WIN', 4.13, 2355.0 ,'usuario13'),\r\n"
				+ "	    (14, 'LOSE', 2.23, 1245.0 ,'usuario14'),\r\n"
				+ "	    (15, 'WIN', 5.34, 8775.0 ,'usuario15');")){
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void añadirBlackJackEjemplo() {
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BlackJack (partida, ganador, puntuacion, ganancia, nombre_usuario) VALUES\r\n"
				+ "	    (1, 'Crupier', 20 , 1123.0 ,'usuario1'),\r\n"
				+ "	    (2, 'Jugador', 18, 9954.0 ,'usuario2'),\r\n"
				+ "	    (3, 'Jugador', 21, 2345.0 ,'usuario3'),\r\n"
				+ "	    (4, 'Crupier', 17, 1245.0 ,'usuario4'),\r\n"
				+ "	    (5, 'Jugador', 19, 854.0 ,'usuario5'),\r\n"
				+ "	    (6, 'Crupier', 20, 3567.0 ,'usuario6'),\r\n"
				+ "	    (7, 'Crupier', 21, 346.0 ,'usuario7'),\r\n"
				+ "	    (8, 'Crupier', 17, 3467.0 ,'usuario8'),\r\n"
				+ "	    (9, 'Jugador', 18, 4576.0 ,'usuario9'),\r\n"
				+ "	    (10, 'Jugador', 19, 1123.0 ,'usuario10'),\r\n"
				+ "	    (11, 'Jugador', 21, 453.0 ,'usuario11'),\r\n"
				+ "	    (12, 'Jugador', 21, 5688.0 ,'usuario12'),\r\n"
				+ "	    (13, 'Crupier', 20, 2355.0 ,'usuario13'),\r\n"
				+ "	    (14, 'Crupier', 19, 1245.0 ,'usuario14'),\r\n"
				+ "	    (15, 'Jugador', 21, 8775.0 ,'usuario15');")){
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	/*
	 * TABLA USUARIOS
	CREATE TABLE IF NOT EXISTS Usuario (
    	nombre VARCHAR(255),
    	apellidos VARCHAR(255),
    	dni VARCHAR(20),
    	saldo DOUBLE,
    	numero_cuenta INT,
    	contrasena VARCHAR(255),
    	nombre_usuario VARCHAR(50) PRIMARY KEY
	); 
	
		DATOS PARA INSERTAR EN LA TABLA Usuarios
		INSERT INTO Usuario (nombre, apellidos, dni, saldo, numero_cuenta, contrasena, nombre_usuario) VALUES
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
    FOREIGN KEY (nombre_usuario) REFERENCES Usuario(nombre_usuario)
	);
	
	DATOS A INSERTAR EN LA TABLA Ruleta
	INSERT INTO Ruleta (numero, ganancia, tirada, nombre_usuario) VALUES
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
