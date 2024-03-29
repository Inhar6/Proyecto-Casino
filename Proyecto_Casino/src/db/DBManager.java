package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.CuentaBancaria;
import domain.Point;
import domain.Usuario;

public class DBManager {
	
	//private static Connection conn = null;
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
	 
	/*
	 * Conexion con la BD
	 */
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
    /*
     * Funciones
     */
    // Metodo para llenar una lista con todos los usuarios
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
			//Añadir la tabla CoinFlip al usuario
			ResultSet rsCoinFlip = stmt.executeQuery("SELECT * FROM Usuario u, CoinFLip cf WHERE u.nombre_usuario = cf.nombre_usuario");
			while(rsCoinFlip.next()) {
				for(Usuario user: lstUsuarios) {
					if(user.getNombreUsuario().equals(rsRuleta.getString("nombre_usuario"))) {
						user.addMapaCoinFlip(rs.getInt("tirada"), rs.getString("Apuesta"), rs.getString("Resultado"));
					}
				}
			}
			//Añadir la tabla CuentaBancaria al usuario
			ResultSet rsCuentaBancaria = stmt.executeQuery("SELECT * FROM Usuario u, CuentaBancaria c WHERE u.nombre_usuario = c.nombre_usuario");
			while(rsCuentaBancaria.next()) {
				for(Usuario user: lstUsuarios) {
					if(user.getNombreUsuario().equals(rsCuentaBancaria.getString("nombre_usuario"))) {
						user.addMapaCuentaBancaria(rsCuentaBancaria.getString("titular"), rsCuentaBancaria.getDouble("dinero"), rsCuentaBancaria.getInt("numero_cuenta"), rsCuentaBancaria.getInt("cvc"), rsCuentaBancaria.getInt("mes"), rsCuentaBancaria.getInt("ano"));
						//user.addSaldo(rsCuentaBancaria.getDouble("dinero"));
					}
				}
			}
			//Añadir la lista Balance al usuario
			ResultSet rsBalance = stmt.executeQuery("SELECT * FROM Usuario u, Balance b WHERE u.nombre_usuario = b.nombre_usuario");
			while(rsBalance.next()) {
				for(Usuario user : lstUsuarios) {
					if(user.getNombreUsuario().equals(rsBalance.getString("nombre_usuario"))) {
						user.addListaBalance(new Point(rsBalance.getInt("sesion"), (int) rsBalance.getDouble("total")));
					}
				}
			}
			return lstUsuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsuarios;
	}
    //Verificar existencia de usuario
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
	//Verificar existencia de usuario ( Login )
	public static boolean existeUsuarioLogin(String nombreU, String contraseña) {
		String sql= "SELECT * FROM Usuario WHERE nombre_Usuario = ? AND contrasena = ?";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, nombreU);
			pstmt.setString(2, contraseña);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Verificar existencia de usuario ( CB )
	public static boolean existeUsuarioCuentaBancaria(String nombreU, String numero_cuenta) {
		String sql= "SELECT * FROM Usuario WHERE nombre_usuario = ? AND numero_cuenta = ?";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, nombreU);
			pstmt.setString(2, numero_cuenta);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Añadir usuario
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
		String sql = "UPDATE Usuario SET nombre = ?, contrasena = ?, numero_cuenta = ? WHERE nombre_usuario = ?;";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getNombre());
			pstmt.setString(2, user.getContraseña());
			pstmt.setInt(3, user.getNumeroCuenta());
			pstmt.setString(4, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Editar saldo del usuario
	public static void guardarSaldo(Usuario user, double saldo) {
		String sql = "UPDATE Usuario SET saldo = ? WHERE nombre_usuario = ? ;";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setDouble(1, saldo);
			pstmt.setString(2, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Metodos de añadir
	 */
	//Añadir saldo al usuario ( mediante CuentaBancaria )
	public static void añadirSaldoCB(Usuario user, CuentaBancaria cb) {
		String sql = "UPDATE Usuario SET saldo = ? , numero_cuenta = ? WHERE nombre_usuario = ?;";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setDouble(1, user.getSaldo() +cb.getSaldo());
			pstmt.setString(2, cb.getNumero_cuenta());
			pstmt.setString(3, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Añadir cuenta Bancaria
	public static void añadirCuentaBancaria(CuentaBancaria cb,Usuario user) {
		String sql = "INSERT INTO CuentaBancaria (titular, dinero, numero_cuenta, cvc, mes, ano, nombre_usuario) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, cb.getTitular());
			pstmt.setDouble(2, cb.getSaldo());
			pstmt.setString(3, cb.getNumero_cuenta());
			pstmt.setInt(4, cb.getCvc());
			pstmt.setInt(5, cb.getMes());
			pstmt.setInt(6, cb.getAno());
			pstmt.setString(7, user.getNombreUsuario());
			pstmt.executeUpdate();
			añadirSaldoCB(user, cb);
			System.out.println("Guardados los datos de la cuenta bancaria");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	//Añadir tirada de la ruleta 
	public static void addTiradaRuleta( int tirada, int numero, double ganancia, Usuario user) {
		String sql = "INSERT INTO Ruleta (numero, ganancia, tirada, nombre_usuario) VALUES (?, ?, ?, ?);";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, numero);
			pstmt.setDouble(2, ganancia);
			pstmt.setInt(3, tirada);
			pstmt.setString(4, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Añadir tirada de Coin-Flip
	public static void addTiradaCoinFlip(int tirada, String apuesta, String resultado, Usuario user) {
		String sql = "INSERT INTO CoinFLip(tirada, apuesta, resultado, nombre_usuario) VALUES ( ?, ? , ? , ?);";
		try(Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, tirada);
			pstmt.setString(2, apuesta);
			pstmt.setString(3, resultado);
			pstmt.setString(4, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Añadir tirada de Crash
	public static void addTiradaCrash(int tirada, String resultado, double multiplicador, double ganancia, Usuario user) {
		String sql= "INSERT INTO Crash (tirada, resultado, multiplicador, ganancia, nombre_usuario) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, tirada);
			pstmt.setString(2, resultado);
			pstmt.setDouble(3, multiplicador);
			pstmt.setDouble(4, ganancia);
			pstmt.setString(5, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Añadir tirada de BlackJack
	public static void addTiradaBlackJack( int partida, String ganador,int resultado ,double ganancia, Usuario user) {
		String sql = "INSERT INTO BlackJack(partida, ganador, puntuacion,ganancia, nombre_usuario) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, partida);
			pstmt.setString(2, ganador);
			pstmt.setInt(3, resultado);
			pstmt.setDouble(4, ganancia);
			pstmt.setString(5, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Añadir Puntos del balance
	public static void addPuntoBalance(int sesion, double saldo, Usuario user) {
		String sql = "INSERT INTO Balance (sesion, total, nombre_usuario) VALUES (?, ?, ?)";
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, sesion);
			pstmt.setDouble(2, saldo);
			pstmt.setString(3, user.getNombreUsuario());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Extras
	 */
	public static List<Point> balanceRuleta(){
		List<Point> lst = new ArrayList<>();
		String sql = "SELECT * FROM Ruleta";
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			int x = 0;
			while(rs.next()) {
				int y = (int)rs.getInt("ganancia")/100;
				Point p = new Point(x, y);
				lst.add(p);
				x++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	public static List<Point> balanceCrash(){
		List<Point> lst = new ArrayList<>();
		String sql = "SELECT * FROM Crash";
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			int x = 0;
			while(rs.next()) {
				int y = (int)rs.getInt("ganancia")/100;
				Point p = new Point(x, y);
				lst.add(p);
				x++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	public static List<Point> balanceBlackJack(){
		List<Point> lst = new ArrayList<>();
		String sql = "SELECT * FROM BlackJack";
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			int x = 0;
			while(rs.next()) {
				int y = (int)rs.getInt("ganancia")/100;
				Point p = new Point(x, y);
				lst.add(p);
				x++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	/*
	 * Creacion de Tablas
	 */
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
	public static void crearTablaCuentaBancaria() {
		try (Connection conn =  obtenerConexion();
				Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CuentaBancaria (\n"
				    + "     titular VARCHAR(30),\n"
				    + "     dinero DOUBLE,\n"    
				    + "     numero_cuenta VARCHAR(30),\n"
				    + "     cvc VARCHAR(4),\n"
				    + "     mes VARCHAR(4),\n"
				    + "     ano VARCHAR(4),\n"
					+ "    	nombre_usuario VARCHAR(50),\n"
					+ " 	FOREIGN KEY (nombre_usuario) REFERENCES Usuario(nombre_usuario));");
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
	public static void crearTablaCoinFlip() {
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate(" CREATE TABLE IF NOT EXISTS CoinFLip (\n"
					+ "    	tirada INTEGER,\n"
					+ "    	apuesta VARCHAR,\n"
					+ "    	resultado VARCHAR,\n"
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
	public static void crearTablaBalance() {
		try (Connection conn = obtenerConexion();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate(" CREATE TABLE IF NOT EXISTS Balance (\n"
					+ "		sesion INTEGER,\n"
					+ "		total DOUBLE,\n"
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
				+ "			('Nombre9', 'Apellido9', '99999999I', 80000.0, 19876, 'contrasena9', 'usuario9'),\r\n"
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
	public static void añadirCuentaBancariaEjemplo() {
		try (Connection conn = obtenerConexion();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CuentaBancaria (titular, dinero, numero_cuenta, cvc, mes, ano, nombre_usuario) VALUES\r\n"
					+ "			('Nombre1 Apellido1', 1000.0, '12345', '123', '01', '26', 'usuario1'),\r\n"
					+ "			('Nombre2 Apellido2', 1500.0, '67890', '235', '07', '25', 'usuario2'),\r\n"
					+ "			('Nombre3 Apellido3', 75000.0, '54321', '345', '04', '27', 'usuario3'),\r\n"
					+ "			('Nombre4 Apellido4', 20000.0, '98765', '654', '01', '27', 'usuario4'),\r\n"
					+ "			('Nombre5 Apellido5', 300.0, '24680', '436', '01', '24', 'usuario5'),\r\n"
					+ "			('Nombre6 Apellido6', 450000.0, '11223', '875', '06', '28', 'usuario6'),\r\n"
					+ "			('Nombre7 Apellido7', 55000.0, '33221', '013', '04', '30', 'usuario7'),\r\n"
					+ "			('Nombre8 Apellido8', 1200.0, '76543', '356', '07', '24', 'usuario8'),\r\n"
					+ "			('Nombre9 Apellido9', 80000.0, '19876', '786', '10', '27', 'usuario9'),\r\n"
					+ "			('Nombre10 Apellido10', 2200.0, '23456', '894', '08', '26', 'usuario10'),\r\n"
					+ "			('Nombre11 Apellido11', 170000.0, '90876', '845', '12', '25', 'usuario11'),\r\n"
					+ "			('Nombre12 Apellido12', 1600.0, '65432', '023', '07', '27', 'usuario12'),\r\n"
					+ "			('Nombre13 Apellido13', 1800.0, '56789', '237', '04', '25', 'usuario13'),\r\n"
					+ "			('Nombre14 Apellido14', 4000.0, '11234', '375', '03', '29', 'usuario14'),\r\n"
					+ "			('Nombre15 Apellido15', 300000.0, '98765', '985', '04', '31', 'usuario15'); ")){
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
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Crash (tirada, resultado, multiplicador, ganancia, nombre_usuario) VALUES\r\n"
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
	public static void añadirBalanceEjemplo() {
		try (Connection conn = obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Balance (sesion, total, nombre_usuario)\r\n"
						+ "VALUES\n"
						+ "    (1, 1500.0, 'usuario1'),\n"
						+ "    (2, 2000.0, 'usuario2'),\n"
						+ "    (3, 1000.0, 'usuario3'),\n"
						+ "    (4, 2500.0, 'usuario4'),\n"
						+ "    (5, 1800.0, 'usuario5'),\n"
						+ "    (6, 3000.0, 'usuario6'),\n"
						+ "    (7, 1200.0, 'usuario7'),\n"
						+ "    (8, 2200.0, 'usuario8'),\n"
						+ "    (9, 1700.0, 'usuario9'),\n"
						+ "    (10, 2800.0, 'usuario10'),\n"
						+ "    (11, 1900.0, 'usuario11'),\n"
						+ "    (12, 2300.0, 'usuario12'),\n"
						+ "    (13, 2700.0, 'usuario13'),\n"
						+ "    (14, 1600.0, 'usuario14'),\n"
						+ "    (15, 2100.0, 'usuario15');")){
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
