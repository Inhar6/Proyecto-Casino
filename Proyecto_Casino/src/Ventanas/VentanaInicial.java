package Ventanas;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaInicial extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("NoEscasino");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setIconImage((new ImageIcon("foto/iconos/favicon.png")).getImage());
		
        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu x = new VentanaPanelMenu();
        add(menuSuperior);
        setJMenuBar(menuBar1);
        x.enseñarMenu(menuSuperior, menu);
		setVisible(true);
	}

	public ImageIcon redimensionarIcono(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // escalar la imagen
		
		imageIcon = new ImageIcon(newimg);
		
		return(imageIcon);
	}
		
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaInicial();				
			}
        });
	}
}
