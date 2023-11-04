package domain;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	
	//Atributos
	private String nombre;
	private String apellidos;
	private String DNI;
	private String nombreUsuario;
	private int numeroCuenta;
	private double saldo;
	//Mapas de historiales
	private Map<Integer, Map<Integer, Double>> mapaRuleta ;
	
	
	//Constructores
	public Usuario(String nombre, String apellidos, String dNI, String nombreUsuario, int numeroCuenta, double saldo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		DNI = dNI;
		this.nombreUsuario = nombreUsuario;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.mapaRuleta = new HashMap<>();
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

	//toString
	@Override
	public String toString() {
		return nombre + ", " + apellidos + ", " + DNI + ", "
				+ nombreUsuario ;
	}
	
	
	
}
