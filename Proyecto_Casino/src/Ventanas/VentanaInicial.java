package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class VentanaInicial extends JFrame{
	
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
	// Paneles
	private JPanel pMenu = new JPanel (new BorderLayout());
	private JPanel pMenuN = new JPanel (new BorderLayout());
	private JPanel pMenuN2 = new JPanel (new BorderLayout());
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

	private static int limiteVentanas = 1;
	
	private ImageIcon logoCasinoNegro = new ImageIcon("foto/iconos/logoNoEscasino.png");
	private ImageIcon favicon = new ImageIcon("foto/iconos/favicon.png");
		
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("NoEscasino");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage((favicon).getImage());

		add(pMenu);
		
		bCasino.setIcon(redimensionarIcono(logoCasinoNegro, 30, 30));
		bCasino.setBorder(null);
		bCasino.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		pMenu.add(pMenuN, BorderLayout.NORTH);
	
		pMenuN.add(bLogin, BorderLayout.EAST);
		pMenuN.add(bUsuario, BorderLayout.EAST);
		pMenuN.add(pMenuN2, BorderLayout.CENTER);
		pMenuN.add(bCasino, BorderLayout.WEST);
		pMenuN2.add(bSingUp, BorderLayout.EAST);
		pMenuN2.add(bJuegos,BorderLayout.WEST);
        // Color y espacios en el Menu superior
		Color colorPanel = new Color(71, 113, 72);
		pMenuN.setBorder(new EmptyBorder(20, 25, 15, 25));
		pMenuN.setBackground(colorPanel);
		pMenuN2.setBorder(new EmptyBorder(0, 5, 0, 5));
		pMenuN2.setBackground(colorPanel);
		// Barra de menus
		setJMenuBar(menuBar);
		menuBar.setVisible(false);
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

		// Añadir JButton Usuario y balance
		bLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaLogin();

			}
		});
		bCasino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click en bCasino");			
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
				new VentanaBlackJack(pMenu, menuJuegos, menuBar);
				
			}
		});
		menuItemCrash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrash(pMenu, menuJuegos, menuBar);
			}
		});
		menuItemRuleta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRuleta(pMenu, menuJuegos, menuBar);
			}
		});
		menuItemCoinFlip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCoinFlip(pMenu, menuJuegos, menuBar);	
			}
		});
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
