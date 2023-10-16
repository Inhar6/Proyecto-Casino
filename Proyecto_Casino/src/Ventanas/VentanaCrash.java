package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new BorderLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
        add(menuSuperior, BorderLayout.NORTH);
        setJMenuBar(menuBar1);
        menuGeneral.abrirNuevaVentanaCrash();
        //
        // Añadir menuApostar
        VentanaPanelApostar menuApostar = new VentanaPanelApostar();
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);
        //
        menuApostar.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
        //
        
	}
	public static void main(String[] args) {
		new VentanaCrash();
	}
}
