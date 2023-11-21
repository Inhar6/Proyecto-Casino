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
}
