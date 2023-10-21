package Clases;

public class ApuestaRuleta {
	
	//Atributos
	private int numero;
	private String color;
	private int docena;
	private int fila;
	private int rango;
	private int par;
	
	
	//Constructores
	public ApuestaRuleta(int numero, String color, int docena, int fila, int rango, int par) {
		super();
		this.numero = numero;
		this.color = color;
		this.docena = docena;
		this.fila = fila;
		this.rango = rango;
		this.par = par;
	}
	
	public ApuestaRuleta() {
		super();
	}

	//Getters y Setters
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getDocena() {
		return docena;
	}
	public void setDocena(int docena) {
		this.docena = docena;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getRango() {
		return rango;
	}
	public void setRango(int rango) {
		this.rango = rango;
	}
	public int getPar() {
		return par;
	}
	public void setPar(int par) {
		this.par = par;
	}

	@Override
	public String toString() {
		return numero + " color=" + color + ", docena=" + docena + ", fila=" + fila
				+ ", rango=" + rango + ", par=" + par ;
	}
	
}
