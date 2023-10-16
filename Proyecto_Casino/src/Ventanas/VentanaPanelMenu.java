package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class VentanaPanelMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Botones
	private JButton bLogin = new JButton ("Login");
	private JButton bSingUp = new JButton ("SingUp");
	private JButton bUsuario = new JButton ("Usuario");
	private JButton bJuegos = new JButton("Juegos");
	private JButton bCasino = new JButton ();
	// Balance
	private int balance;
	private JLabel lBalance = new JLabel("Balance: " + balance);	
	// Paneles
	public JPanel pMenu = new JPanel (new BorderLayout());
	private JPanel pMenuN = new JPanel (new BorderLayout());
	private JPanel pMenuN2 = new JPanel (new BorderLayout());
	private JPanel pMenuN3 = new JPanel(new BorderLayout());
	private JPanel pJuegos = new JPanel(new BorderLayout());
	private JPanel pCasino = new JPanel(new BorderLayout());
	private JPanel pInicio = new JPanel(new BorderLayout());
	private JPanel pRegistro = new JPanel(new BorderLayout());
	private JPanel pBalance = new JPanel(new BorderLayout());
	private JPanel pUsuario = new JPanel(new BorderLayout());
	// Barra de menus
	private JMenuBar menuBar = new JMenuBar();
	// Elementos JMenuJuegos
	private JMenu menuJuegos = new JMenu("Juegos");
	private JMenuItem menuItemBlackJack = new JMenuItem("Back Jack");
	private JMenuItem menuItemCrash = new JMenuItem("Crash");
	private JMenuItem menuItemRuleta = new JMenuItem("Ruleta");
	private JMenuItem menuItemCoinFlip = new JMenuItem("Coin-Flip");
	// Elementos JMenuUsuario
	private JMenu menuUsuario = new JMenu("Usuario");
	private JMenuItem menuItemPerfil = new JMenuItem("Perfil");
	private JMenuItem menuItemDepositar = new JMenuItem("Depostar");
	private JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar sesión");
	
	private ImageIcon logoCasinoNegro = new ImageIcon("foto/iconos/logoNoEscasino.png");
	private ImageIcon favicon = new ImageIcon("foto/iconos/favicon.png");

	private static int contadorVentanaBlackJack = 0;
	private static int contadorVentanaCrash = 0;
	private static int contadorVentanaRuelta = 0;
	private static int contadorVentanaCoinFlip = 0;
	private static int contadorVentanaInicial = 0;

    private static int limiteVentanas = 1; // Establece el límite deseado
	
	public void enseñarMenu(JPanel panel, JMenu menu) {

		panel.add(pMenu);
		
		bCasino.setIcon(redimensionarIcono(logoCasinoNegro, 30, 30));
		bCasino.setBorder(null);
		bCasino.setBorder(new BevelBorder(BevelBorder.RAISED));

		pMenu.add(pMenuN, BorderLayout.NORTH);
		pMenuN.add(pMenuN2, BorderLayout.WEST);
		pMenuN.add(pMenuN3, BorderLayout.EAST);
		pMenuN2.add(pJuegos, BorderLayout.EAST);
		pMenuN2.add(pCasino, BorderLayout.WEST);
		pJuegos.add(bJuegos);
		pCasino.add(bCasino);
		pMenuN3.add(pInicio, BorderLayout.EAST);
		pMenuN3.add(pRegistro, BorderLayout.WEST);
		pMenuN3.add(pUsuario, BorderLayout.EAST);
		pMenuN3.add(pBalance, BorderLayout.WEST);
		pInicio.add(bLogin);
		pRegistro.add(bSingUp);
		pUsuario.add(bUsuario);
		pBalance.add(lBalance);
		
		// Temporal hasta Añadir mas funciones : 
		// Mostrar InicioRegistro / BalanceUsuario
		JPanel mostrarBotones = new JPanel();
		pMenuN.add(mostrarBotones, BorderLayout.CENTER);
		mostrarBotones.add(bLogin);
		mostrarBotones.add(bSingUp);
		
        // Color y espacios en el Menu superior
		Color colorPanel = new Color(71, 113, 72);
		pMenuN.setBorder(new EmptyBorder(20, 25, 20, 25));
		pMenuN.setBackground(colorPanel);
		pMenuN2.setBorder(new EmptyBorder(0, 10, 0, 10));
		pMenuN2.setBackground(colorPanel);
		pJuegos.setBorder(new EmptyBorder(0, 10, 0, 10));
		pJuegos.setBackground(colorPanel);
		pCasino.setBorder(new EmptyBorder(0, 10, 0, 10));
		pCasino.setBackground(colorPanel);
		pInicio.setBorder(new EmptyBorder(0, 10, 0, 10));
		pInicio.setBackground(colorPanel);
		pRegistro.setBorder(new EmptyBorder(0, 10, 0, 10));
		pRegistro.setBackground(colorPanel);
		pBalance.setBorder(new EmptyBorder(0, 10, 0, 10));
		pBalance.setBackground(colorPanel);
		pUsuario.setBorder(new EmptyBorder(0, 10, 0, 10));
		pUsuario.setBackground(colorPanel);	
		// Barra de menus
		menuBar.setVisible(false); // Oculta la barra de menu, se abre con botones Juegos/Usuarios
		// Configuración JMenuJuegos
		menuBar.add(menuJuegos);
        menuJuegos.setMnemonic(KeyEvent.VK_F);
        // Items del menu "Juegos"
		menuJuegos.add(menuItemBlackJack);
		menuItemBlackJack.setMnemonic(KeyEvent.VK_S);
		menuJuegos.addSeparator();
		menuJuegos.add(menuItemCrash);
		menuItemCrash.setMnemonic(KeyEvent.VK_S);
		menuJuegos.addSeparator();
		menuJuegos.add(menuItemRuleta);
		menuItemRuleta.setMnemonic(KeyEvent.VK_S);
		menuJuegos.addSeparator();
		menuJuegos.add(menuItemCoinFlip);
		menuItemCoinFlip.setMnemonic(KeyEvent.VK_S);
		// Configuración JMenuUsuario
		menuBar.add(menuUsuario);
		menuUsuario.setMnemonic(KeyEvent.VK_F);
		// Items del menu "Usuario"
		menuUsuario.add(menuItemPerfil);
		menuItemPerfil.setMnemonic(KeyEvent.VK_S);
		menuUsuario.addSeparator();
		menuUsuario.add(menuItemDepositar);
		menuItemDepositar.setMnemonic(KeyEvent.VK_S);
		menuUsuario.addSeparator();
		menuUsuario.add(menuItemCerrarSesion);
		menuItemCerrarSesion.setMnemonic(KeyEvent.VK_S);

		bLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaLogin();

			}
		});
		bCasino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaInicial < limiteVentanas) {
                    new VentanaInicial();
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Inicio.");
                    contadorVentanaInicial--;
                }			
			}
		});
		
		bSingUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro();
				
			}
		});
		bJuegos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuJuegos.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
			}
		});
		bUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuUsuario.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
			}
		});
		
		menuItemBlackJack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaBlackJack < limiteVentanas) {
                    new VentanaBlackJack();
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas BlackJack.");
                    contadorVentanaBlackJack--;
                }	
			}
		});
		menuItemCrash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaCrash < limiteVentanas) {
                    new VentanaCrash();
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Crash.");
                    contadorVentanaCrash--;
                }
			}
		});
		menuItemRuleta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaRuelta < limiteVentanas) {
					new VentanaRuleta();
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Ruleta.");
                    contadorVentanaRuelta--;
                }
			}
		});
		menuItemCoinFlip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaCoinFlip < limiteVentanas) {
					new VentanaCoinFlip();
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Coin-Flip.");
                    contadorVentanaCoinFlip--;
                }	
			}
		});
	}

	public ImageIcon redimensionarIcono(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // escalar la imagen
		
		imageIcon = new ImageIcon(newimg);
		
		return(imageIcon);
	}
    public void abrirNuevaVentanaBlackJack() {
    	contadorVentanaBlackJack++;
    }
    public void abrirNuevaVentanaCrash() {
    	contadorVentanaCrash++;
    }
    public void abrirNuevaVentanaRuleta() {
    	contadorVentanaRuelta++;
    }
    public void abrirNuevaVentanaCoinFlip() {
    	contadorVentanaCoinFlip++;
    }
    public void abrirNuevaVentanaInicial() {
    	contadorVentanaInicial++;
    }
}
