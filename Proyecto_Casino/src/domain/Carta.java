package domain;

public class Carta {
	private String numero;
	private String palo;
	
	
	public Carta(String numero, String palo) {
		super();
		this.numero = numero;
		this.palo = palo;
	}
	
	

	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getPalo() {
		return palo;
	}



	public void setPalo(String palo) {
		this.palo = palo;
	}



	@Override
	public String toString() {
		return palo + "-" + numero;
	}
	
	
	

}
