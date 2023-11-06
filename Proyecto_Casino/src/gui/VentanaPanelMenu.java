package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

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
	private static final Logger logger = Logger.getLogger("VentanaPanelMenu");

	// Botones
	private JButton bLogin = new JButton ("Login");
	private JButton bSingUp = new JButton ("SingUp");
	private JButton bUsuario = new JButton ("Usuario");
	private JButton bJuegos = new JButton("Juegos");
	private JButton bCasino = new JButton ();
	// Color
	static Color colorPanel = new Color(71, 113, 72);
	// Balance
	static double balance = 10000;
	static JLabel lBalance = new JLabel("Balance: " + balance);	
	// Paneles
	public JPanel pMenu = new JPanel (new BorderLayout());
	private JPanel pMenuN = new JPanel (new BorderLayout());
	private JPanel pMenuN2 = new JPanel (new BorderLayout());
	private JPanel pMenuN3 = new JPanel(new BorderLayout());
	
	private JPanel pMenuN3Login = new JPanel(new BorderLayout());
	private JPanel pMenuN3NoLogin = new JPanel(new BorderLayout());
	
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
	
	private ImageIcon logoCasinoNegro = new ImageIcon("resources/images/iconos/logoNoEscasino.png");

//	private Estado estado;
	static int contadorVentanaBlackJack = 0;
	static int contadorVentanaCrash = 0;
	static int contadorVentanaRuelta = 0;
	static int contadorVentanaCoinFlip = 0;
	static int contadorVentanaInicial = 0;
	static int contadorVentanaPerfil = 0;
	static int contadorVentanaDepositar =0;

    private static int limiteVentanas = 1; // Establece el límite deseado
	
    // Menu superior

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
		
		pMenuN3.add(pMenuN3Login);
		pMenuN3.add(pMenuN3NoLogin);
		
		pMenuN3Login.add(pInicio, BorderLayout.EAST);
		pMenuN3Login.add(pRegistro, BorderLayout.WEST);
		pMenuN3NoLogin.add(pUsuario, BorderLayout.EAST);
		pMenuN3NoLogin.add(pBalance, BorderLayout.WEST);
		
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
		JButton mostrarLogin = new JButton();
		JButton mostrarNoLogin = new JButton();		
		
        // Color y espacios en el Menu superior
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
				logger.info("Has abierto una ventana 'Login'");
			}
		});
		bCasino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaInicial < limiteVentanas) {
                    new VentanaInicial();
                    contadorVentanaInicial++;
                    logger.info("Has abierto una ventana inicial");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Inicio.");
                }			
			}
		});
		
		bSingUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro();
				logger.info("Has abierto una ventana 'SingUp'");
			}
		});
		bJuegos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuJuegos.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
				logger.info("Has abierto el menu 'Juegos'");
			}
		});
		bUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuUsuario.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
				logger.info("Has abierto el menu 'Usuario'");
			}
		});
		
		menuItemBlackJack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaBlackJack < limiteVentanas) {
                    new VentanaBlackJack();
                    contadorVentanaBlackJack++;
                    logger.info("Has abierto una ventana 'Black Jack'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas BlackJack.");
                }	
			}
		});
		menuItemCrash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaCrash < limiteVentanas) {
                    new VentanaCrash();
                	contadorVentanaCrash++;
                	logger.info("Has abierto una ventana 'Crash'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Crash.");
                }
			}
		});
		menuItemRuleta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaRuelta < limiteVentanas) {
					new VentanaRuleta();
					contadorVentanaRuelta++;
					logger.info("Has abierto una ventana 'Ruleta'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Ruleta.");
                }
			}
		});
		menuItemCoinFlip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contadorVentanaCoinFlip < limiteVentanas) {
					new VentanaCoinFlip();
					contadorVentanaCoinFlip++;
					logger.info("Has abierto una ventana 'Coin-Flip'");
                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Coin-Flip.");
                }	
			}
		});
		menuItemPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(contadorVentanaPerfil < limiteVentanas) {
					new VentanaPerfil();
					contadorVentanaPerfil++;
					logger.info("Has abierto una ventana 'Perfil'");
				}else {
					JOptionPane.showMessageDialog(null, "Se alcanzo el limite de ventanas de Perfil");
				}
				
			}
		});
		menuItemDepositar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(contadorVentanaDepositar < limiteVentanas) {
							new VentanaDeposito();
							contadorVentanaDepositar++;
							logger.info("Has abierto una ventana 'Depositar'");
						}else {
							JOptionPane.showMessageDialog(null, "Se alcanzo el limite de ventanas de Perfil");
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
    
    // PanelApostar
	private JPanel pApostar = new JPanel(new BorderLayout());
	private JPanel pApostarW = new JPanel(new BorderLayout());
	private JPanel pApostarE = new JPanel(new BorderLayout());
	
	private JPanel pBotonApuesta = new JPanel(new BorderLayout());
	private JPanel pBotonEliminarApuesta = new JPanel(new BorderLayout());
	private JPanel pLabelApuesta = new JPanel(new BorderLayout());
	
	private JPanel p1y10 = new JPanel(new BorderLayout());
	private JPanel p25y50 = new JPanel(new BorderLayout());
	private JPanel p100y1000 = new JPanel(new BorderLayout());
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p10 = new JPanel(new BorderLayout());
	private JPanel p25 = new JPanel(new BorderLayout());
	private JPanel p50 = new JPanel(new BorderLayout());
	private JPanel p100 = new JPanel(new BorderLayout());
	private JPanel p1000 = new JPanel(new BorderLayout());

	static double apuesta;
	static JLabel lApuesta = new JLabel("Apuesta: " +  apuesta);
    static JButton bApostar = new JButton("Apostar");
    private JButton bEliminarApuesta = new JButton("Eliminar apuesta");
    // Fichas
    private JButton bficha1 = new JButton();
    private JButton bficha10 = new JButton();
    private JButton bficha25 = new JButton();
    private JButton bficha50 = new JButton();
    private JButton bficha100 = new JButton();
    private JButton bficha1000 = new JButton();
    
    // Menu inferior de apuesta
    public void enseñarApostar(JPanel panel) {
    	Color colorPanel = new Color(71, 113, 72);

    	// Imagenes fichas
        ImageIcon logoFicha1 = new ImageIcon("resources/images/fichas/ficha1.png");
        ImageIcon logoFicha10 = new ImageIcon("resources/images/fichas/ficha10.png");
        ImageIcon logoFicha25 = new ImageIcon("resources/images/fichas/ficha25.png");
        ImageIcon logoFicha50 = new ImageIcon("resources/images/fichas/ficha50.png");
        ImageIcon logoFicha100 = new ImageIcon("resources/images/fichas/ficha100.png");
        ImageIcon logoFicha1000 = new ImageIcon("resources/images/fichas/ficha1000.png");
		bficha1.setIcon(redimensionarIcono(logoFicha1, 50, 50));
		bficha1.setBorder(null);
		bficha10.setIcon(redimensionarIcono(logoFicha10, 50, 50));
		bficha10.setBorder(null);
		bficha25.setIcon(redimensionarIcono(logoFicha25, 50, 50));
		bficha25.setBorder(null);
		bficha50.setIcon(redimensionarIcono(logoFicha50, 50, 50));
		bficha50.setBorder(null);
		bficha100.setIcon(redimensionarIcono(logoFicha100, 50, 50));
		bficha100.setBorder(null);
		bficha1000.setIcon(redimensionarIcono(logoFicha1000, 50, 50));
		bficha1000.setBorder(null);
		//
		pApostarW.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
    	panel.add(pApostar);

        // PanelApostar
        pApostar.add(pApostarW, BorderLayout.WEST);
        pApostar.add(pApostarE, BorderLayout.EAST);
        
        // Panel pApostarW
        pApostarW.add(p1y10,BorderLayout.WEST);
        pApostarW.add(p25y50,BorderLayout.CENTER);
        pApostarW.add(p100y1000,BorderLayout.EAST);
        p1y10.add(p1,BorderLayout.WEST);
        p1y10.add(p10,BorderLayout.EAST);
        p25y50.add(p25,BorderLayout.WEST);
        p25y50.add(p50,BorderLayout.EAST);
        p100y1000.add(p100,BorderLayout.WEST);
        p100y1000.add(p1000,BorderLayout.EAST);
        p1.add(bficha1);
        p10.add(bficha10);
        p25.add(bficha25);
        p50.add(bficha50);
        p100.add(bficha100);
        p1000.add(bficha1000);
        p1.setBorder(new EmptyBorder(0,0,0,5)); p1.setBackground(colorPanel);
        p10.setBorder(new EmptyBorder(0,5,0,5)); p10.setBackground(colorPanel);
        p25.setBorder(new EmptyBorder(0,5,0,5)); p25.setBackground(colorPanel);
        p50.setBorder(new EmptyBorder(0,5,0,5)); p50.setBackground(colorPanel);
        p100.setBorder(new EmptyBorder(0,5,0,5)); p100.setBackground(colorPanel);
        p1000.setBorder(new EmptyBorder(0,5,0,5)); p1000.setBackground(colorPanel);
                
        // Panel pApostarE
        pApostarE.add(pLabelApuesta, BorderLayout.WEST);
        pApostarE.add(pBotonEliminarApuesta, BorderLayout.CENTER);
        pApostarE.add(pBotonApuesta, BorderLayout.EAST);
        pLabelApuesta.add(lApuesta);
        pBotonEliminarApuesta.add(bEliminarApuesta);
        pBotonApuesta.add(bApostar);
        
        pApostar.setBackground(colorPanel); pApostar.setBorder(new EmptyBorder(20, 20, 20, 20));
        pApostarW.setBackground(colorPanel);
        pApostarE.setBackground(colorPanel);
        pBotonApuesta.setBackground(colorPanel);
        pBotonEliminarApuesta.setBackground(colorPanel); pBotonEliminarApuesta.setBorder(new EmptyBorder(0, 0, 0, 10));
        pLabelApuesta.setBackground(colorPanel); pLabelApuesta.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        //
        //https://chat.openai.com/c/9efa7138-9668-45e2-83f6-0af11d881280
        // Aumenta el tamaño de la fuente de un JLabel
        Font fuente = lApuesta.getFont();
        Font nuevaFuente = new Font(fuente.getName(), Font.PLAIN, 18);
        lApuesta.setFont(nuevaFuente);
        lApuesta.setForeground(Color.yellow);
        lBalance.setForeground(Color.yellow);
        //
        
        bficha1.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 1;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has sumado '1' a tu apuesta");
			}
		});
        bficha10.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 10;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has sumado '10' a tu apuesta");
			}
		});
        bficha25.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 25;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has sumado '25' a tu apuesta");
			}
		});
        bficha50.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 50;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has sumado '50' a tu apuesta");
			}
		});
        bficha100.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 100;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has sumado '100' a tu apuesta");
			}
		});
        bficha1000.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 1000;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has sumado '1000' a tu apuesta");
			}
		});
        bEliminarApuesta.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta = 0;
				lApuesta.setText("Apuesta: " + apuesta);
				logger.info("Has eliminado tu apuesta");
			}
		});
        bApostar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (balance >= apuesta ) {
					balance -= apuesta;
					lBalance.setText("Balance: " + balance);
					lApuesta.setText("Apuesta: " + apuesta);
					logger.info("Has hecho tu apuesta");
				} else if (balance == 0 && apuesta == 0) {
					JOptionPane.showMessageDialog(null, "Balance Insuficiente");
				} else {
					JOptionPane.showMessageDialog(null, "Balance Insuficiente");
				}
				
			}
		});
    }
}