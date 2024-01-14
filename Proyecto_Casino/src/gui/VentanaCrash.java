package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.DBManager;
import domain.Usuario;

public class VentanaCrash extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("Ventana Crah");

	// Paneles Crash
	private JPanel pCrash = new JPanel(new GridLayout(3,1));
    private JPanel pDatos  = new JPanel (new BorderLayout());
	private JPanel pHistorial = new JPanel(new BorderLayout());
    private JPanel pDatos2 = new JPanel (new BorderLayout());
	private JPanel pBar = new JPanel(new BorderLayout());
	private JPanel pSacar = new JPanel(new GridLayout());
	
	private boolean resultado;
	private int segundos; // numero decimal
	private int numeroX = 1; // numero entero ej: X.324423
	private double multiplicador; // numero final que saca el usuario
	private double nuevoBalance; // numero nuevo balance
	private double numeroCrono; // numero por el que va el timer
	private double ganado;
	private Timer timer;
	private Random random = new Random();
	
	private JLabel lApostado = new JLabel("Apostado: ");
//	private JLabel lNumeroRandom = new JLabel("Numero random: ");
	private JLabel lGanado = new JLabel("Ganado: ");
	private JLabel lMultiplicador = new JLabel("Multiplicador: " + multiplicador);
//	private JLabel lNumeroCrono = new JLabel("Numero crono: " + numeroCrono);	
	
	private JButton bSacar = new JButton("Sacar");
	
	
	//JTable - Historial
	private JTable tabla;
	private DefaultTableModel dtmTabla;
	private JScrollPane scroll;
	public static Map<Integer,Map<Boolean, Map<Double, Double>>> mapaTiradaCrash = new TreeMap<>();
	
	// PorgressBar
	private JProgressBar progressBar = new JProgressBar(100, 500);
	private int valor = 100;
    private int tirada = 0;
    //private int numeroRangoProb;
    //Usuario
    private Usuario user = new Usuario();
    //Balance - Grafica
    private int marca = 0;
    
    public VentanaCrash(Usuario u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Crash");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/iconos/favicon.png").getImage());
		
		//Usuario
		user = u;
		//Tirada
		tirada = u.getMapaCrash().size();
		//Balance - Grafica
		marca = u.getLstBalance().size();
        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new BorderLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu(); // Crea una ventana "VenatanaPanelMenu"
        add(menuSuperior, BorderLayout.NORTH);
        setJMenuBar(menuBar1);
        // Añadir menuApostar
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaJuego = 0; // Reiniciar el contador
            }
        });
        
        //JTable - Historial
      	dtmTabla= new DefaultTableModel();
      	tabla = new JTable(dtmTabla);
      	tabla.setEnabled(false);
      	tabla.setDefaultRenderer(Object.class, new MyRender());
      	scroll = new JScrollPane(tabla);
      	Border lineaHistorial = BorderFactory.createLineBorder(VentanaPanelMenu.colorPanel);
      	Border tituloHistorial = BorderFactory.createTitledBorder(lineaHistorial,"Historial");
    	pHistorial.setBorder(tituloHistorial);
    	dtmTabla.addColumn("Tirada");
		dtmTabla.addColumn("Resultado");
		dtmTabla.addColumn("Multiplicador");
		dtmTabla.addColumn("Ganancia/Perdida");
        
      	// Panel Crash
        add(pCrash);
        
		pCrash.add(pDatos, BorderLayout.NORTH);
        pDatos.setBorder(new EmptyBorder(0, 0, 20, 0));
        pCrash.add(pBar, BorderLayout.CENTER);
        pCrash.add(pSacar,BorderLayout.SOUTH);
        pSacar.setBorder(new EmptyBorder(90, 0, 0, 0));
        
        //Panel datos
		pDatos.add(pDatos2, BorderLayout.WEST);
		pDatos2.add(lApostado);
		pDatos.add(pHistorial, BorderLayout.EAST);
        pHistorial.add(scroll);

        // ProgressBar
        pBar.add(progressBar);
        progressBar.setStringPainted(true); // Muestra el porcentaje completo
        progressBar.setString("x" + numeroX + "." + segundos);
        progressBar.setBackground(Color.LIGHT_GRAY);
        progressBar.setForeground(Color.DARK_GRAY);
        
        // Panel sacar
        pSacar.add(bSacar);
        bSacar.setEnabled(false);
        pSacar.add(lGanado);
        pSacar.add(lMultiplicador);
//      pSacar.add(lNumeroCrono);
        
        VentanaPanelMenu.bApostar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.apuesta != 0 && VentanaPanelMenu.balance >= VentanaPanelMenu.apuesta) {
					
					VentanaPanelMenu.balance -= VentanaPanelMenu.apuesta;
					
					VentanaPanelMenu.lBalance.setText("Balance: " + VentanaPanelMenu.balance);
					VentanaPanelMenu.lApuesta.setText("Apuesta: " + VentanaPanelMenu.apuesta);
					
					double numeroRandom = random.nextDouble(1, 5);
		            // https://chat.openai.com/c/b0d0f57d-6616-4fe0-833b-1bd6ac8e7d5d
		            timer = new Timer(10, new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	numeroCrono = numeroX + (segundos/100.0);
		                	if(numeroCrono < numeroRandom) {
		                    	segundos++;
		                    	valor++;
		                        progressBar.setString("x" + numeroX + "." + segundos);
		                      	progressBar.setValue(valor);
//		                        actualizarEtiquetaTiempo();
		                        if(segundos == 99) {
		                        	segundos = 0;
		                        	numeroX++;
		                        }
		                	} else {
		                		lose();
		                    	timer.stop();
		                	}
		                }
		            });
		        	timer.start();
			        bSacar.setEnabled(true);
			        VentanaPanelMenu.bApostar.setEnabled(false);
			        tirada++;
//			        nuevoBalance = VentanaPanelMenu.balance - VentanaPanelMenu.apuesta;
//			        VentanaPanelMenu.lBalance.setText("Balance: " + nuevoBalance);
			        lApostado.setText("Apostado: " + VentanaPanelMenu.apuesta);
//			        lNumeroRandom.setText("Numero random: " + Math.round(numeroRandom * 100.0) / 100.0);
		        } else {
		        	JOptionPane.showMessageDialog(null, "Haga su apuesta");
		        	tirada--;
		        }
			}
		});
        
        bSacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Has sacado tu aspuesta");
				timer.stop();
		        bSacar.setEnabled(false);
				multiplicador =  obtenerMultiplicador(numeroX, segundos);
		        lMultiplicador.setText("Multiplicador: " + multiplicador);
				ganado = multiplicador * VentanaPanelMenu.apuesta;
				lGanado.setText("Ganado: " + Math.round(ganado * 100.0) / 100.0);
				nuevoBalance = VentanaPanelMenu.balance + ganado;
				VentanaPanelMenu.balance = nuevoBalance;
				VentanaPanelMenu.lBalance.setText("Balance: " + nuevoBalance);
				win();
			}
		});
      
      //Accion al cerrar la ventana	
      		addWindowListener(new WindowListener() {
      			@Override
      			public void windowOpened(WindowEvent e) {
      				// TODO Auto-generated method stub	
      			}
      			@Override
      			public void windowIconified(WindowEvent e) {
      				// TODO Auto-generated method stub	
      			}
      			@Override
      			public void windowDeiconified(WindowEvent e) {
      				// TODO Auto-generated method stub
      			}
      			@Override
      			public void windowDeactivated(WindowEvent e) {
      				// TODO Auto-generated method stub	
      			}
      			@Override
      			public void windowClosing(WindowEvent e) {
      				//Guardar el saldo del usuario
      				DBManager.guardarSaldo(user, VentanaPanelMenu.balance );	
      				logger.info("Datos guardados: " + VentanaPanelMenu.balance);
      			}
      			@Override
      			public void windowClosed(WindowEvent e) {
      				// TODO Auto-generated method stub	
      			}
      			@Override
      			public void windowActivated(WindowEvent e) {
      				// TODO Auto-generated method stub	
      			}
      		});
        
	    // Mostrar VentanaMenuPanel
	    menuGeneral.enseñarApostar(menuInferior);
	    menuGeneral.enseñarMenu(menuSuperior, menu, VentanaPanelMenu.user);
	    //
	}
    public void resetJuego() {
    	VentanaPanelMenu.bApostar.setEnabled(true);
    	progressBar.setValue(valor = 100);
    	progressBar.setString("x" + (numeroX = 1) + "." + (segundos = 0));
    	lGanado.setText("Ganado: " + (ganado = 0));
    	lApostado.setText("Apostado: " + (VentanaPanelMenu.apuesta = 0));
    	VentanaPanelMenu.lApuesta.setText("Apuesta: " + (VentanaPanelMenu.apuesta = 0));
//    	resultado = false;
    	logger.info("Se ha reseteado el juego (Crash)");
    }
//	private void actualizarEtiquetaTiempo() {
////        lEtiquetaTiempo.setText("Tiempo: x" + numeroX + "." + segundos);
////        lNumeroCrono.setText("Nuemero crono: " + Math.round(numeroCrono * 100.0) / 100.0);
//    }
	

	public class MyRender extends JLabel implements TableCellRenderer {

	    private static final long serialVersionUID = 1L;

	    public MyRender() {
	        setOpaque(true);
	        setFont(new Font("Arial", Font.PLAIN, 14));
	    }
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setText(value.toString());

	        if ("WIN".equals(value) && column == 1) {
	            setBackground(Color.GREEN);
	        } else if ("LOSE".equals(value) && column == 1) {
	            setBackground(Color.RED);
	        } else {
	            setBackground(table.getBackground());
	        }
	        return this; // Devuelve el propio JLabel modificado.
	    }
	}
	public void win() {
		resultado = true;
    	bSacar.setEnabled(false);
		JOptionPane.showMessageDialog(null, "WIN\n +" + ganado + "     x" + multiplicador);
        jugadaCrash(mapaTiradaCrash);
		pintarDatosCrash(mapaTiradaCrash);
	    System.out.println(mapaTiradaCrash);
		resetJuego();
	}
	public void lose() {
		resultado = false;
    	bSacar.setEnabled(false);
		JOptionPane.showMessageDialog(null , "LOSE\n -" + VentanaPanelMenu.apuesta + "     x" + numeroCrono);
        jugadaCrash(mapaTiradaCrash);
		pintarDatosCrash(mapaTiradaCrash);
	    System.out.println(mapaTiradaCrash);
		resetJuego(); 
	}
	public void pintarDatosCrash(Map<Integer,Map<Boolean, Map<Double, Double>>> mapa) {
		if (resultado == true) {
			Object[] jugadaWin = new Object[] {tirada, "WIN", "x" + multiplicador, "+" + ganado};
			dtmTabla.addRow(jugadaWin);
		}
		if(resultado == false) {
			Object[] jugadaLose = new Object[] {tirada, "LOSE", "x" + numeroCrono, "-" + VentanaPanelMenu.apuesta};
			dtmTabla.addRow(jugadaLose);
		}	
	}
	
	public void jugadaCrash(Map<Integer,Map<Boolean, Map<Double, Double>>> map) {
	    Map<Boolean, Map<Double, Double>> detallesTirada = new HashMap<>();
	    if (resultado == true) {
		    detallesTirada.put(resultado, new HashMap<>());
		    detallesTirada.get(resultado).put(multiplicador, ganado);
		    mapaTiradaCrash.put(tirada, detallesTirada);
		    //Base de datos
		    String result = ""+resultado;
		    DBManager.addTiradaCrash(tirada, result.toUpperCase(), multiplicador, ganado, user);
		    //Balance - Grafica
		    int x = (int)ganado / 100;
		    DBManager.addPuntoBalance(marca, x, user);
		    marca++;
		    //Mapa
		    user.addMapaCrash(tirada, result, multiplicador, ganado);
	    } else {
		    detallesTirada.put(resultado, new HashMap<>());
		    detallesTirada.get(resultado).put(numeroCrono, VentanaPanelMenu.apuesta);
		    mapaTiradaCrash.put(tirada, detallesTirada);
		    //Base de datos
		    String result = ""+resultado;
		    DBManager.addTiradaCrash(tirada, result.toUpperCase(), multiplicador, ganado, user);
		    //Balance - Grafica
		    int x = (int)ganado / 100;
		    DBManager.addPuntoBalance(marca, x, user);
		    marca ++;
		    //Mapa
		    user.addMapaCrash(tirada, result, multiplicador, ganado);
	    }
	}
	//
	public static double calcularGanado(double multiplicador, double apuesta) {
		return multiplicador * apuesta;
	}
	public static boolean obtenerResultado(boolean win, boolean lose, double multiplicador) {
		if (multiplicador > 0.0) {
			return win;
		} else {
			return lose;
		}
	}
	public static double obtenerMultiplicador(int numeroX, int segundos) {
		return numeroX + (segundos/100.0);
	}
	
}