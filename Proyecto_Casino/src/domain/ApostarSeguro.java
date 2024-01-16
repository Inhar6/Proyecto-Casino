package domain;

public class ApostarSeguro {
	
	private String Nick;
	private String Diario;
	private String Semanal;
	private String Mensual;
	
	
	
	public ApostarSeguro() {
		
	}



	public ApostarSeguro(String diario, String semanal, String mensual,String nick) {
		super();
		Diario = diario;
		Semanal = semanal;
		Mensual = mensual;
		Nick = nick;
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

	public String getNick() {
		return Nick;
	}



	public void setNick(String nick) {
		Nick = nick;
	}



	@Override
	public String toString() {
		return Diario +","+Semanal+","+Mensual+","+Nick;
	}



	
	
	
	

}
