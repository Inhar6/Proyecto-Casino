package Clases;

public class Carta {
	private String numero;
	private String palo;
	
	
	public Carta(String numero, String palo) {
		super();
		this.numero = numero;
		this.palo = palo;
	}

	@Override
	public String toString() {
		return "Carta [numero=" + numero + ", palo=" + palo + "]";
	}
	
	

}
