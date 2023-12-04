package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaInicial extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("VentanaInicial");

	JPanel pInicial = new JPanel(new GridLayout(2,2));
	JPanel pJuego1 = new JPanel(new BorderLayout());
	JPanel pJuego2 = new JPanel(new BorderLayout());
	JPanel pJuego3 = new JPanel(new BorderLayout());
	JPanel pJuego4 = new JPanel(new BorderLayout());
	
	JButton bJuego1 = new JButton("Black Jack");
	JButton bJuego2 = new JButton("Crash");
	JButton bJuego3 = new JButton("Ruleta");
	JButton bJuego4 = new JButton("Coin-Flip");
	
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("NoEscasino");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
	    setVisible(true);
		setIconImage((new ImageIcon("resources/images/iconos/favicon.png")).getImage());
		
        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
        add(menuSuperior,BorderLayout.NORTH);
        setJMenuBar(menuBar1);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaInicial = 0; // Reiniciar el contador
            }
            @Override
            public void windowOpened(WindowEvent e) {
            	VentanaPanelMenu.contadorVentanaInicial = 1;
            }
        });
       
        add(pInicial);
        pInicial.add(pJuego1);
        pInicial.add(pJuego2);
        pInicial.add(pJuego3);
        pInicial.add(pJuego4);
        
        pInicial.setBorder(new EmptyBorder(10,10,10,10));
        pInicial.setBackground(VentanaPanelMenu.colorPanel);
        pJuego1.setBorder(new EmptyBorder(10,10,10,10));
        pJuego2.setBorder(new EmptyBorder(10,10,10,10));
        pJuego3.setBorder(new EmptyBorder(10,10,10,10));
        pJuego4.setBorder(new EmptyBorder(10,10,10,10));

        pJuego1.add(bJuego1);
        pJuego2.add(bJuego2);
        pJuego3.add(bJuego3);
        pJuego4.add(bJuego4);

        bJuego1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.contadorVentanaJuego < VentanaPanelMenu.limiteVentanas) {
                    new VentanaBlackJack();
                    VentanaPanelMenu.contadorVentanaJuego++;
                    logger.info("Has abierto una ventana 'Black Jack'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Black Jack.");
                }	
			}
		});
        bJuego2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.contadorVentanaJuego < VentanaPanelMenu.limiteVentanas) {
                    new VentanaCrash();
                    VentanaPanelMenu.contadorVentanaJuego++;
                	logger.info("Has abierto una ventana 'Crash'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Crash.");
                }
			}
		});
        bJuego3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.contadorVentanaJuego < VentanaPanelMenu.limiteVentanas) {
					new VentanaRuleta(VentanaPanelMenu.user);
					VentanaPanelMenu.contadorVentanaJuego++;
					logger.info("Has abierto una ventana 'Ruleta'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Ruleta.");
                }
			}
		});
        bJuego4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.contadorVentanaJuego < VentanaPanelMenu.limiteVentanas) {
					new VentanaCoinFlip();
					VentanaPanelMenu.contadorVentanaJuego++;
					logger.info("Has abierto una ventana 'Coin-Flip'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Coin-Flip.");
                }	
			}
		});
        // Mostrar VentanaMenuPanel
        menuGeneral.enseñarMenu(menuSuperior, menu);
	}
}
