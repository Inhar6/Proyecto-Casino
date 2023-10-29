package Ventanas;

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
import java.util.Random;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class VentanaCrash extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("Ventana Crah");

	private JPanel pCrash = new JPanel(new GridLayout(3,1));
    private JPanel pDatos  = new JPanel (new BorderLayout());
	private JPanel pHistorial = new JPanel(new BorderLayout());
    private JPanel pDatos2 = new JPanel (new BorderLayout());
	private JPanel pBar = new JPanel(new BorderLayout());
	private JPanel pSacar = new JPanel(new GridLayout());
	
	private boolean win;
	private boolean lose;
	private int segundos; // numero decimal
	private int numeroX = 1; // numero entero ej: X.324423
	private double numeroFinal; // numero final que saca el usuario
	private double nuevoBalance; // numero nuevo balance
	private double numeroCrono; // numero por el que va el timer
	private Timer timer;
	
	private double ganado;
	
	private JLabel lEtiquetaTiempo = new JLabel("Tiempo: x1");
	private JLabel lApostado = new JLabel("Apostado: ");
	private JLabel lNumeroRandom = new JLabel("Numero random: ");
	private JLabel lGanado = new JLabel("Ganado: ");
	private JLabel lNumeroFinal = new JLabel("Numero final: " + numeroFinal);
	private JLabel lNumeroCrono = new JLabel("Numero crono: " + numeroCrono);	
	
	private JButton bSacar = new JButton("Sacar");
	
	private Random random = new Random();
	
	//JTable - Historial
	private JTable tabla;
	private DefaultTableModel dtmTabla;
	private JScrollPane scroll;
	
	// PorgressBar
	private JProgressBar progressBar = new JProgressBar(100, 500);
	private int valor = 100;
    private int contadorApostar;
    private int numeroRangoProb;

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
                VentanaPanelMenu.contadorVentanaCrash = 0; // Reiniciar el contador
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
        pSacar.add(lNumeroFinal);
        pSacar.add(lNumeroCrono);
        
        VentanaPanelMenu.bApostar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPanelMenu.apuesta != 0) {
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
//		                      	System.out.println(valor);
		                        actualizarEtiquetaTiempo();
		                        if(segundos == 99) {
		                        	segundos = 0;
		                        	numeroX++;
		                        }
		                	} else {
		                		lose = true;
		                		pintarDatosCrash();
		                		ventanaLose();
		                    	timer.stop();
		                	}
		                }
		            });
		        	timer.start();
			        bSacar.setEnabled(true);
			        VentanaPanelMenu.bApostar.setEnabled(false);
			        contadorApostar++;
			        lApostado.setText("Apostado: " + VentanaPanelMenu.apuesta);
			        lNumeroRandom.setText("Numero random: " + Math.round(numeroRandom * 100.0) / 100.0);
		        } else {
		        	JOptionPane.showMessageDialog(null, "Haga su apuesta");
		        	contadorApostar--;
		        }
			}
		});
        
        bSacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Has sacado tu aspuesta");
				timer.stop();
		        bSacar.setEnabled(false);
				numeroFinal =  numeroX + (segundos/100.0);
		        lNumeroFinal.setText("Numero final: " + numeroFinal);
				ganado = numeroFinal * VentanaPanelMenu.apuesta;
				lGanado.setText("Ganado: " + Math.round(ganado * 100.0) / 100.0);
				nuevoBalance = VentanaPanelMenu.balance + ganado;
				VentanaPanelMenu.lBalance.setText("Balance: " + nuevoBalance);
				win = true;
				ventanaWin();
				pintarDatosCrash();
			}
		});

	    // Mostrar VentanaMenuPanel
	    menuGeneral.enseñarApostar(menuInferior);
	    menuGeneral.enseñarMenu(menuSuperior, menu);
	    //
	}
    private void ventanaWin() {
        JFrame ventanaWin = new JFrame();
        ventanaWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaWin.setSize(300, 250);
		// Centra la ventana en el centro de la pantlla
        ventanaWin.setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 350) / 2),  
								(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 350) / 2));
        ventanaWin.setVisible(true);
        ventanaWin.setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
        
        JPanel pWinN = new JPanel();
		JPanel pWinC = new JPanel();
		JPanel pWinS = new JPanel();

		
		JLabel lWin = new JLabel("WIN");
		JLabel lMultiplicador = new JLabel("x" + numeroFinal);
		JLabel lGanado = new JLabel("+ " + ganado);
		
		ventanaWin.add(pWinN, BorderLayout.NORTH);
		ventanaWin.add(pWinC, BorderLayout.CENTER);
		ventanaWin.add(pWinS, BorderLayout.SOUTH);
		pWinN.add(lWin);
		pWinC.add(lMultiplicador);
		pWinS.add(lGanado);
		
		pWinC.setBorder(new EmptyBorder(20, 0 , 0, 0));
		pWinS.setBorder(new EmptyBorder(0, 0 , 20, 0));
		pWinN.setBackground(VentanaPanelMenu.colorPanel);
		pWinC.setBackground(VentanaPanelMenu.colorPanel);
		pWinS.setBackground(VentanaPanelMenu.colorPanel);

		lWin.setForeground(Color.GREEN);
		lMultiplicador.setForeground(Color.WHITE);
		lGanado.setForeground(Color.WHITE);

		Font fuenteA = new Font(lWin.getFont().getName(), Font.BOLD, 30);
		Font fuenteB = new Font(lApostado.getFont().getName(), Font.PLAIN, 25);
		lWin.setFont(fuenteA);
		lMultiplicador.setFont(fuenteB);
		lGanado.setFont(fuenteB);
	    
        ActionListener cerrarVentana = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ventanaWin.dispose();
            	resetJuego();			        
            }
        };
        Timer timer = new Timer(5000, cerrarVentana);
        ventanaWin.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowOpened(WindowEvent e) {
                timer.start();
            	bSacar.setEnabled(false);
        	}
        	@Override
        	public void windowClosing(WindowEvent e) {
            	ventanaWin.dispose();
        		resetJuego();
	        	timer.stop();
        	}
		});
        timer.setRepeats(false);        
    }
    private void ventanaLose() {
        JFrame ventanaLose = new JFrame();
        ventanaLose.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaLose.setSize(300, 250);
		// Centra la ventana en el centro de la pantlla
        ventanaLose.setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 350) / 2),  
								(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 350) / 2));
        ventanaLose.setVisible(true);
        ventanaLose.setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
        
        
        JPanel pLoseN = new JPanel();
		JPanel pLoseC = new JPanel();
		JPanel pLoseS = new JPanel();
		
		JLabel lLose = new JLabel("LOSE");
		JLabel lMultiplicador = new JLabel("x" + numeroFinal);
		JLabel lApostado = new JLabel("- " + VentanaPanelMenu.apuesta);

		ventanaLose.add(pLoseN, BorderLayout.NORTH);
		ventanaLose.add(pLoseC, BorderLayout.CENTER);
		ventanaLose.add(pLoseS, BorderLayout.SOUTH);
		pLoseN.add(lLose);
		pLoseC.add(lMultiplicador);
		pLoseS.add(lApostado);
		
		pLoseC.setBorder(new EmptyBorder(20, 0 , 0, 0));
		pLoseS.setBorder(new EmptyBorder(0, 0 , 20, 0));
		pLoseN.setBackground(new Color(213, 48, 50));
		pLoseC.setBackground(new Color(213, 48, 50));
		pLoseS.setBackground(new Color(213, 48, 50));
		
		lLose.setForeground(Color.BLACK);
		lApostado.setForeground(Color.BLACK);
		lMultiplicador.setForeground(Color.BLACK);

		Font fuenteA = new Font(lLose.getFont().getName(), Font.BOLD, 30);
		Font fuenteB = new Font(lApostado.getFont().getName(), Font.PLAIN, 25);
		lLose.setFont(fuenteA);
		lMultiplicador.setFont(fuenteB);
		lApostado.setFont(fuenteB);
       
        ActionListener cerrarVentana = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ventanaLose.dispose();
            	resetJuego();			        
            }
        };
        Timer timer = new Timer(5000, cerrarVentana);
        ventanaLose.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowOpened(WindowEvent e) {
                timer.start();
            	bSacar.setEnabled(false);
        	}
        	@Override
        	public void windowClosing(WindowEvent e) {
            	ventanaLose.dispose();
        		resetJuego();
	        	timer.stop();
        	}
		});
        timer.setRepeats(false);
    }

    private void resetJuego() {
    	VentanaPanelMenu.bApostar.setEnabled(true);
    	progressBar.setValue(valor = 100);
    	progressBar.setString("x" + (numeroX = 1) + "." + (segundos = 0));
    	lGanado.setText("Ganado: " + (ganado = 0));
    	lApostado.setText("Apostado: " + (VentanaPanelMenu.apuesta = 0));
    	VentanaPanelMenu.lApuesta.setText("Apuesta: " + (VentanaPanelMenu.apuesta = 0));
    	win = false;
    	lose = false;
    	logger.info("Se ha reseteado el juego (Crash)");
    }
	private void actualizarEtiquetaTiempo() {
        lEtiquetaTiempo.setText("Tiempo: x" + numeroX + "." + segundos);
        lNumeroCrono.setText("Nuemero crono: " + Math.round(numeroCrono * 100.0) / 100.0);
    }
	class MyRender extends JLabel implements TableCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyRender() {
			setOpaque(true);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			if (win == true) {
				setBackground(Color.GREEN);
			} else {
				setBackground(Color.RED);
			}
			return this;
		}
	}
	public void pintarDatosCrash() {
		if (win == true) {
			Object[] jugadaWin = new Object[] {contadorApostar, "WIN", "x" + numeroX + "." + segundos, "+" + ganado};
			dtmTabla.addRow(jugadaWin);
		}
		if(lose == true) {
			Object[] jugadaLose = new Object[] {contadorApostar, "LOSE", "x" + numeroX + "." + segundos, "-" + VentanaPanelMenu.apuesta};
			dtmTabla.addRow(jugadaLose);
		}	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaCrash();

			}
		});
	}
}