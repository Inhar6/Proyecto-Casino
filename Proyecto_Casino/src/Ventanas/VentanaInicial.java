package Ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaInicial extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setTitle("Casino");
		setVisible(true);
		
		JPanel myPanel = new JPanel();
		add(myPanel);
		
		JButton Juegos = new JButton("Juegos");
		myPanel.add(Juegos);
		JButton Registro = new JButton("Registro");
		myPanel.add(Registro);
		JButton Login = new JButton("Login");
		myPanel.add(Login);
	}
	
	public static void main(String[] args) {
		new VentanaInicial();
	}
}
