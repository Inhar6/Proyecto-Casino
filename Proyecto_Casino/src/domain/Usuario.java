package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Usuario implements Comparable<Usuario>{
	
	//Atributos
	private String nombre;
	private String apellidos;
	private String DNI;
	private String nombreUsuario;
	private String contraseña;
	private int numeroCuenta;
	private double saldo;
	//Mapas de historiales
	private Map<Integer, Map<Integer, Double>> mapaRuleta ;
	//Lista balance
	private List<Point> lstBalance;
	
	
	//Constructores
	public Usuario(String nombre, String apellidos, String dNI, String nombreUsuario, String contraseña, int numeroCuenta, double saldo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		DNI = dNI;
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.mapaRuleta = new HashMap<>();
		this.lstBalance = new ArrayList<>();
	}
	
	//Constructor vacio
	public Usuario() {
		super();
		
	}
	
	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Map<Integer, Map<Integer, Double>> getMapaRuleta() {
		return mapaRuleta;
	}

	public void setMapaRuleta(Map<Integer, Map<Integer, Double>> mapaRuleta) {
		this.mapaRuleta = mapaRuleta;
	}
	//Añadirle cosas al mapa del usuario
	public void addMapaRuleta(int tirada, int resultado, double ganancia) {
		mapaRuleta.putIfAbsent(tirada, new HashMap<>());
		mapaRuleta.get(tirada).put(resultado, ganancia);
	}
	public List<Point> getLstBalance() {
		return lstBalance;
	}

	public void setLstBalance(List<Point> lstBalance) {
		this.lstBalance = lstBalance;
	}
	//Añadirle cosas a la lista de puntos
	public void addListaBalance(Point p) {
		lstBalance.add(p);
	}

	//toString
	@Override
	public String toString() {
		return nombre + ", " + apellidos + ", " + DNI + ", "
				+ nombreUsuario ;
	}
	
	//HashCode
	@Override
	public int hashCode() {
		return Objects.hash(DNI, apellidos, contraseña, lstBalance, mapaRuleta, nombre, nombreUsuario, numeroCuenta,
				saldo);
	}
	
	//Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(DNI, other.DNI) && Objects.equals(apellidos, other.apellidos)
				&& Objects.equals(contraseña, other.contraseña) && Objects.equals(lstBalance, other.lstBalance)
				&& Objects.equals(mapaRuleta, other.mapaRuleta) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nombreUsuario, other.nombreUsuario) && numeroCuenta == other.numeroCuenta
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo);
	}
	
	//Comparador
	@Override
	public int compareTo(Usuario o) {
		return this.nombre.compareTo(o.nombre);
	}
	
	
	
}
