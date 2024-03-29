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

import domain.Usuario;
import io.Propiedades;

public class VentanaPanelMenu {
	
	private static final Logger logger = Logger.getLogger("VentanaPanelMenu");
	public static Usuario user = new Usuario();
	// Botones
	private JButton bLogin = new JButton ("Login");
	private JButton bSignUp = new JButton ("Sign up");
	private JButton bUsuario = new JButton ("Usuario");
	private JButton bJuegos = new JButton("Juegos");
	private JButton bCasino = new JButton ();
	// Color
	static Color colorPanel = new Color(71, 113, 72);
	// Balance
	static double balance = 10000.0;
	static JLabel lBalance = new JLabel("Balance: " + balance);
	// Paneles
	public JPanel pMenu = new JPanel (new BorderLayout());
	private JPanel pMenuN = new JPanel (new BorderLayout());
	private JPanel pMenuN2 = new JPanel (new BorderLayout());
	private JPanel pMenuN3 = new JPanel(new BorderLayout());
	private JPanel pMenuBalanceLogin = new JPanel(new BorderLayout());
	private JPanel pMenuUsuarioSingUp = new JPanel(new BorderLayout());
	private JPanel pJuegos = new JPanel(new BorderLayout());
	private JPanel pCasino = new JPanel(new BorderLayout());

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
	private JMenuItem menuItemDepositar = new JMenuItem("Depositar");
	private JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar sesión");
	
	private ImageIcon logoCasinoNegro = new ImageIcon("resources/images/iconos/logoNoEscasino.png");

	static int contadorVentanaJuego = 0;
	static int contadorVentanaInicial = 0;
	static int contadorVentanaPerfil = 0;
	static int contadorVentanaDepositar = 0;

    static int limiteVentanas = 1; // Establece el límite deseado
	
    // Propiedades
    private Propiedades propiedades;
    private Propiedades getPropiedades() {
    	return propiedades;
    }
    
    // Menu superior
	public void enseñarMenu(JPanel panel, JMenu menu, Usuario u) {
		
		u=user;
		balance = user.getSaldo();
		
		lBalance = new JLabel("Balance: " + balance);
		
		panel.add(pMenu);

		propiedades = new Propiedades();
		propiedades.cargar();
		
		bCasino.setIcon(redimensionarIcono(logoCasinoNegro, 30, 30));
		bCasino.setBorder(null);
		bCasino.setBorder(new BevelBorder(BevelBorder.RAISED));

		pMenu.add(pMenuN, BorderLayout.NORTH);
		pMenuN.add(pMenuN2, BorderLayout.WEST);
		pMenuN.add(pMenuN3, BorderLayout.EAST);
		pMenuN2.add(pJuegos, BorderLayout.EAST);
		pMenuN2.add(pCasino, BorderLayout.WEST);
		pMenuN3.add(pMenuUsuarioSingUp, BorderLayout.EAST);
		pMenuN3.add(pMenuBalanceLogin, BorderLayout.WEST);
		pJuegos.add(bJuegos);
		pCasino.add(bCasino);
		
		mostrarUsuarioLogin();

        // Color y espacios en el Menu superior
		pMenuN.setBorder(new EmptyBorder(20, 25, 20, 25));
		pMenuN.setBackground(colorPanel);
		pMenuN2.setBorder(new EmptyBorder(0, 10, 0, 10));
		pMenuN2.setBackground(colorPanel);
		pJuegos.setBorder(new EmptyBorder(0, 10, 0, 10));
		pJuegos.setBackground(colorPanel);
		pCasino.setBorder(new EmptyBorder(0, 10, 0, 10));
		pCasino.setBackground(colorPanel);
		pMenuBalanceLogin.setBorder(new EmptyBorder(0, 10, 0, 10));
		pMenuBalanceLogin.setBackground(colorPanel);
		pMenuUsuarioSingUp.setBorder(new EmptyBorder(0, 10, 0, 10));
		pMenuUsuarioSingUp.setBackground(colorPanel);

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
		
		bSignUp.addActionListener(new ActionListener() {
			
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
				if (VentanaLogin.loged == true) {
					if (contadorVentanaJuego < limiteVentanas) {
	                    new VentanaBlackJack(user);
	                    contadorVentanaJuego++;
	                    logger.info("Has abierto una ventana 'Black Jack'");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas BlackJack.");
	                }
				} else {
					JOptionPane.showMessageDialog(null, "Inicie sesion para poder jugar.");
				}	
			}
		});
		
		menuItemCrash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaLogin.loged == true) {
					if (contadorVentanaJuego < limiteVentanas) {
	                    new VentanaCrash(user);
	                    contadorVentanaJuego++;
	                	logger.info("Has abierto una ventana 'Crash'");
	                } else {
                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Crash.");
	                }
				} else {
					JOptionPane.showMessageDialog(null, "Inicie sesion para poder jugar.");
				}	

			}
		});
		menuItemRuleta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaLogin.loged == true) {
					if (contadorVentanaJuego < limiteVentanas) {
						new VentanaRuleta(user);
						contadorVentanaJuego++;
						logger.info("Has abierto una ventana 'Ruleta'");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Ruleta.");
	                }
				} else {
					JOptionPane.showMessageDialog(null, "Inicie sesion para poder jugar.");
				}
			}
		});
		menuItemCoinFlip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaLogin.loged == true) {
					if (contadorVentanaJuego < limiteVentanas) {
						new VentanaCoinFlip(user);
						contadorVentanaJuego++;
						logger.info("Has abierto una ventana 'Coin-Flip'");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Se alcanzó el límite de ventanas Coin-Flip.");
	                }	
				} else {
					JOptionPane.showMessageDialog(null, "Inicie sesion para poder jugar.");
				}
			}
		});
		menuItemPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(contadorVentanaPerfil < limiteVentanas) {
					new VentanaPerfil(user);
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
							new VentanaDeposito(user);
							contadorVentanaDepositar++;
							logger.info("Has abierto una ventana 'Depositar'");
						}else {
							JOptionPane.showMessageDialog(null, "Se alcanzo el limite de ventanas de Perfil");
						}
						
					}
				});
		menuItemCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.loged = false;
				logger.info("Se ha cerrado la sesión");
			}
		});
	}

	public void mostrarUsuarioLogin() {
		if(VentanaLogin.loged == true) {
			pMenuUsuarioSingUp.add(bUsuario);
			pMenuBalanceLogin.add(lBalance);
		} else {
			pMenuUsuarioSingUp.add(bSignUp);
			pMenuBalanceLogin.add(bLogin);
		}
	}
	public static ImageIcon redimensionarIcono(ImageIcon imageIcon, int width, int height) {
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
	private JPanel p100y1000yMax = new JPanel(new BorderLayout());
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p10 = new JPanel(new BorderLayout());
	private JPanel p25 = new JPanel(new BorderLayout());
	private JPanel p50 = new JPanel(new BorderLayout());
	private JPanel p100 = new JPanel(new BorderLayout());
	private JPanel p1000 = new JPanel(new BorderLayout());
	private JPanel pMax = new JPanel(new BorderLayout());

	public static double apuesta;
	static JLabel lApuesta = new JLabel("Apuesta: " +  apuesta);
    private JButton bEliminarApuesta = new JButton("Eliminar apuesta");
    // Fichas
    private JButton bficha1 = new JButton();
    private JButton bficha10 = new JButton();
    private JButton bficha25 = new JButton();
    private JButton bficha50 = new JButton();
    private JButton bficha100 = new JButton();
    private JButton bficha1000 = new JButton();
    private JButton bfichaMax = new JButton();

    // Menu inferior de apuesta
    public void enseñarApostar(JPanel panel) {
    	Color colorPanel = new Color(71, 113, 72);
    	
    	propiedades = new Propiedades();
    	propiedades.cargar();
    	
    	// Imagenes fichas
		bficha1.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ficha1")), 50, 50));
		bficha10.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ficha10")), 50, 50));
		bficha25.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ficha25")), 50, 50));
		bficha50.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ficha50")), 50, 50));
		bficha100.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ficha100")), 50, 50));
		bficha1000.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("ficha1000")), 50, 50));
		bfichaMax.setIcon(redimensionarIcono(new ImageIcon(getPropiedades().getProperty("fichaMax")), 50, 50));
		bficha1.setBorder(null);
		bficha10.setBorder(null);
		bficha25.setBorder(null);
		bficha50.setBorder(null);
		bficha100.setBorder(null);
		bficha1000.setBorder(null);
		bfichaMax.setBorder(null);
		
		pApostarW.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
    	panel.add(pApostar);

        // PanelApostar
        pApostar.add(pApostarW, BorderLayout.WEST);
        pApostar.add(pApostarE, BorderLayout.EAST);
        
        // Panel pApostarW
        pApostarW.add(p1y10,BorderLayout.WEST);
        pApostarW.add(p25y50,BorderLayout.CENTER);
        pApostarW.add(p100y1000yMax,BorderLayout.EAST);
        p1y10.add(p1,BorderLayout.WEST);
        p1y10.add(p10,BorderLayout.EAST);
        p25y50.add(p25,BorderLayout.WEST);
        p25y50.add(p50,BorderLayout.EAST);
        p100y1000yMax.add(p100,BorderLayout.WEST);
        p100y1000yMax.add(p1000,BorderLayout.CENTER);
        p100y1000yMax.add(pMax, BorderLayout.EAST);
        p1.add(bficha1);
        p10.add(bficha10);
        p25.add(bficha25);
        p50.add(bficha50);
        p100.add(bficha100);
        p1000.add(bficha1000);
        pMax.add(bfichaMax);
        p1.setBorder(new EmptyBorder(0,0,0,5)); p1.setBackground(colorPanel);
        p10.setBorder(new EmptyBorder(0,5,0,5)); p10.setBackground(colorPanel);
        p25.setBorder(new EmptyBorder(0,5,0,5)); p25.setBackground(colorPanel);
        p50.setBorder(new EmptyBorder(0,5,0,5)); p50.setBackground(colorPanel);
        p100.setBorder(new EmptyBorder(0,5,0,5)); p100.setBackground(colorPanel);
        p1000.setBorder(new EmptyBorder(0,5,0,5)); p1000.setBackground(colorPanel);
        pMax.setBorder(new EmptyBorder(0,5,0,5)); pMax.setBackground(colorPanel);        
        // Panel pApostarE
        pApostarE.add(pLabelApuesta, BorderLayout.WEST);
        pApostarE.add(pBotonEliminarApuesta, BorderLayout.CENTER);
        pApostarE.add(pBotonApuesta, BorderLayout.EAST);
        pLabelApuesta.add(lApuesta);
        pBotonEliminarApuesta.add(bEliminarApuesta);
        
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
        lBalance.setForeground(Color.black);
        
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
        bfichaMax.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += balance;
		        lApuesta.setText("Apuesta: " + apuesta);
		        logger.info("Has hecho tu apuesta máxima");
			}
		});
    }
}