package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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

import io.Propiedades;

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
	
	JButton bJuego1 = new JButton("");
	JButton bJuego2 = new JButton("");
	JButton bJuego3 = new JButton("");
	JButton bJuego4 = new JButton("");
	
	//Propiedades
	private Propiedades propiedades;
	private Propiedades getPropiedades() {
		return propiedades;
	}
	
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("NoEscasino");
		propiedades = new Propiedades();
		propiedades.cargar();
		
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
	    setVisible(true);
		setIconImage((new ImageIcon(getPropiedades().getProperty("favicon"))).getImage());
		
		//BlackJack
		bJuego1.setIcon(VentanaPanelMenu.redimensionarIcono(new ImageIcon(getPropiedades().getProperty("blackJack")), 225, 225));
		bJuego1.setBorder(null);
		bJuego1.setBackground(Color.WHITE);
		//Crash
		bJuego2.setIcon(VentanaPanelMenu.redimensionarIcono(new ImageIcon(getPropiedades().getProperty("crash")), 225, 225));
		bJuego2.setBorder(null);
		bJuego2.setBackground(Color.WHITE);
		//Ruleta
		bJuego3.setIcon(VentanaPanelMenu.redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ruleta")), 225, 225));
		bJuego3.setBorder(null);
		bJuego3.setBackground(Color.WHITE);
		//CoinFlip
		bJuego4.setIcon(VentanaPanelMenu.redimensionarIcono(new ImageIcon(getPropiedades().getProperty("coinFlip")), 200, 200));
		bJuego4.setBorder(null);
		bJuego4.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		
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
        pJuego1.setBackground(Color.white);
        pJuego2.add(bJuego2);
        pJuego2.setBackground(Color.white);
        pJuego3.add(bJuego3);
        pJuego3.setBackground(Color.white);
        pJuego4.add(bJuego4);
        pJuego4.setBackground(Color.white);

        bJuego1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.contadorVentanaJuego < VentanaPanelMenu.limiteVentanas) {
                    new VentanaBlackJack(VentanaPanelMenu.user);
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
                    new VentanaCrash(VentanaPanelMenu.user);
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
					new VentanaCoinFlip(VentanaPanelMenu.user);
					VentanaPanelMenu.contadorVentanaJuego++;
					logger.info("Has abierto una ventana 'Coin-Flip'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Coin-Flip.");
                }	
			}
		});
        // Mostrar VentanaMenuPanel
        menuGeneral.enseñarMenu(menuSuperior, menu, VentanaPanelMenu.user);
	}
}
