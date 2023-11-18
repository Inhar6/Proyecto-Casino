package domain;

import java.util.Objects;

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
		return numero + "-"+ palo;
	}



	@Override
	public int hashCode() {
		return Objects.hash(numero, palo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return Objects.equals(numero, other.numero) && Objects.equals(palo, other.palo);
	}
	
	
	

}
