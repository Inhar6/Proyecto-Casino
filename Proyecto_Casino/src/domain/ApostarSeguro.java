package domain;

public class ApostarSeguro {
	
	private String Diario;
	private String Semanal;
	private String Mensual;
	
	
	
	public ApostarSeguro() {
		
	}



	public ApostarSeguro(String diario, String semanal, String mensual) {
		super();
		Diario = diario;
		Semanal = semanal;
		Mensual = mensual;
	}



	public String getDiario() {
		return Diario;
	}



	public void setDiario(String diario) {
		Diario = diario;
	}



	public String getSemanal() {
		return Semanal;
	}



	public void setSemanal(String semanal) {
		Semanal = semanal;
	}



	public String getMensual() {
		return Mensual;
	}



	public void setMenusal(String mensual) {
		Mensual = mensual;
	}



	@Override
	public String toString() {
		return Diario +","+Semanal+","+Mensual;
	}



	
	
	
	

}
