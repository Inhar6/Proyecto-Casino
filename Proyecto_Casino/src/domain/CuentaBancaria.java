package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CuentaBancaria {

	private String titular;
	private String numero_cuenta;
	private int cvc;
	private int mes;
	private int ano;
	private double saldo;
	private Map<String, Map<Double, Map<Integer, Map<Integer, Map<Integer,Integer>>>>> mapaCuentaBancaria = new HashMap<>();
	
	public CuentaBancaria(String titular, String numero_cuenta, int cvc, int mes, int ano, double saldo,
			Map<String, Map<Double, Map<Integer, Map<Integer, Map<Integer, Integer>>>>> mapaCuentaBancaria) {
		super();
		this.titular = titular;
		this.numero_cuenta = numero_cuenta;
		this.cvc = cvc;
		this.mes = mes;
		this.ano = ano;
		this.saldo = saldo;
		this.mapaCuentaBancaria = mapaCuentaBancaria;
	}
	public CuentaBancaria(String titular, String numero_cuenta, int cvc, int mes, int ano, double saldo) {
		super();
		this.titular = titular;
		this.numero_cuenta = numero_cuenta;
		this.cvc = cvc;
		this.mes = mes;
		this.ano = ano;
		this.saldo = saldo;
	
	}

	// Constructor vacio
	public CuentaBancaria() {
		super();
	}

	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public int getCvc() {
		return cvc;
	}
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Map<String, Map<Double, Map<Integer, Map<Integer, Map<Integer, Integer>>>>> getMapaCuentaBancaria() {
		return mapaCuentaBancaria;
	}
	public void setMapaCuentaBancaria(Map<String, Map<Double, Map<Integer, Map<Integer, Map<Integer, Integer>>>>> mapaCuentaBancaria) {
		this.mapaCuentaBancaria = mapaCuentaBancaria;
	}

	public void addMapaCuentaBancaria(String titular, double saldo, int numero_cuenta, int cvc, int ano, int mes) {
		Map<Double, Map<Integer, Map<Integer, Map<Integer, Integer>>>> saldoCB = new HashMap<>();
		saldoCB.putIfAbsent(saldo, new HashMap<>());
		Map<Integer, Map<Integer, Integer>> detallesCuentaBancaria = new HashMap<>();
		saldoCB.get(saldo).put(numero_cuenta, detallesCuentaBancaria);
		detallesCuentaBancaria.putIfAbsent(cvc, new HashMap<>());
		detallesCuentaBancaria.get(cvc).put(mes, ano);
		mapaCuentaBancaria.put(titular, saldoCB);
	}

	@Override
	public String toString() {
		return "CuentaBancaria [numero_cuenta=" + numero_cuenta + ", cvc=" + cvc + ", mes=" + mes + ", ano=" + ano
				+ ", saldo=" + saldo + ", mapaCuentaBancaria=" + mapaCuentaBancaria + "]";
	}
	//HashCode
	@Override
	public int hashCode() {
		return Objects.hash(titular, numero_cuenta, cvc, mes, ano, saldo, mapaCuentaBancaria);
	}
	//Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CuentaBancaria))
			return false;
		CuentaBancaria other = (CuentaBancaria) obj;
		return Objects.equals(titular, other.titular) && Objects.equals(numero_cuenta, other.numero_cuenta)
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo);
	}
	//Comparador
	public int compareTo(CuentaBancaria o) {
		return this.numero_cuenta.compareTo(o.numero_cuenta);
	}
}
