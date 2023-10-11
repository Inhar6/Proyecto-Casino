package Ventanas;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VentanaCrash extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaCrash() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Crash");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
	}
}
