package domain;

public class ApostarSeguro {
	
	private String Diario;
	private String Semanal;
	private String Menusal;
	
	
	
	public ApostarSeguro() {
		
	}



	public ApostarSeguro(String diario, String semanal, String menusal) {
		super();
		Diario = diario;
		Semanal = semanal;
		Menusal = menusal;
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



	public String getMenusal() {
		return Menusal;
	}



	public void setMenusal(String menusal) {
		Menusal = menusal;
	}
	
	
	

}
