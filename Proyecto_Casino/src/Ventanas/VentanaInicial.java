package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaInicial extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Botones
	private JButton bLogin = new JButton ("Login");
	private JButton bSingUp = new JButton ("SingUp");
	private JButton bCasino = new JButton ();
	// Paneles
	private JPanel pMenu = new JPanel (new BorderLayout());
	private JPanel pMenuN = new JPanel (new BorderLayout());
	private JPanel pMenuN2 = new JPanel (new BorderLayout());
	// Elementos JMenu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuJuegos = new JMenu("Juegos");
	private JMenuItem menuItemBlackJack = new JMenuItem("Back Jack");
	private JMenuItem menuItemCrash = new JMenuItem("Crash");
	private JMenuItem menuItemRuleta = new JMenuItem("Ruleta");
	private JMenuItem menuItemCoinFlip = new JMenuItem("Coin-Flip");

	private ImageIcon logoCasinoNegro = new ImageIcon("foto/iconos/logoCasinoNegro.png");
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
		
		bCasino.setIcon(redimensionarIcono(logoCasinoNegro, 25, 25));
		bCasino.setBorder(null);
		
		pMenu.add(pMenuN, BorderLayout.NORTH);
		pMenuN.add(bLogin, BorderLayout.EAST);
		pMenuN.add(pMenuN2, BorderLayout.CENTER);
		pMenuN.add(bCasino, BorderLayout.WEST);
		pMenuN2.add(bSingUp, BorderLayout.EAST);
        // Color y espacios en el Menu superior
		pMenuN.setBorder(new EmptyBorder(20, 25, 15, 25));
		pMenuN.setBackground(Color.LIGHT_GRAY);
		pMenuN2.setBorder(new EmptyBorder(0, 5, 0, 5));
		pMenuN2.setBackground(Color.LIGHT_GRAY);
		// Configuración JMenu
		setJMenuBar(menuBar);
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

		bLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vLogin = new VentanaLogin();

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
				VentanaRegistro vSingUp = new VentanaRegistro();
				
			}
		});
		
		menuItemBlackJack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaBlackJack vBlackJack = new VentanaBlackJack(pMenu, menuJuegos, menuBar);
				dispose();
			}
		});
		menuItemCrash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCrash vCrash = new VentanaCrash(pMenu, menuJuegos, menuBar);
			}
		});
		menuItemRuleta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRuleta vRuleta = new VentanaRuleta(pMenu, menuJuegos, menuBar);
			}
		});
		menuItemCoinFlip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCoinFlip vCoinFlip = new VentanaCoinFlip(pMenu, menuJuegos, menuBar);
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
