package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaCoinFlip extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("VentanaCoinFlip");
	
	//botones
	private JButton bCara;
	private JButton bCruz;
  	
	private JButton bBorrarSelec;
  		
	//contador
	private int cont = 0;
	
	//hilo
	private Thread hilo;
	
	//resultado moneda
	private String resultado;
	
	//label imagenes 
	private JLabel lFotos;
	
	//Cara o Cruz boton clicado 
	private String caraCruz;
	
	private JPanel contentPane;
	
	
	public VentanaCoinFlip() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		setSize(800, 600);
		setTitle("Coin-Flip");
		// Centra la ventana en el centro de la pantlla
		setLocationRelativeTo(null);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaJuego = 0; // Reiniciar el contador
            }
        });
		setVisible(true);
			
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
	    setJMenuBar(menuBar1);
		
     	 //botones 
     	bCara = new JButton("Cara");
     	bCara.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     	bCara.setMargin(new Insets(10, 30, 10, 20));
     	
     	bCruz = new JButton("Cruz");
     	bCruz.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     	bCruz.setMargin(new Insets(10, 30, 10, 20));
     	
     	bBorrarSelec = new JButton("Borrar seleccion");
     	bBorrarSelec.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     	bBorrarSelec.setMargin(new Insets(10, 30, 10, 20));
     	
	   	//lista
      	DefaultListModel<String> dlmHistorial = new DefaultListModel<>();
      	JList<String> lstHistorial = new JList<>(dlmHistorial);
      	JScrollPane scroll = new JScrollPane(lstHistorial);
      	 
      	/////
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
			
		JPanel panelMenuSuperior = new JPanel();
		panelMenuSuperior.setLayout( new GridLayout() );
		contentPane.add(panelMenuSuperior, BorderLayout.NORTH);
		
		JPanel panelMenuInferior = new JPanel();
		panelMenuInferior.setLayout(new GridLayout());
		contentPane.add(panelMenuInferior, BorderLayout.SOUTH);
	
		JPanel panelPrincipal = new JPanel();
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
			
		JPanel panelBotonIferior = new JPanel();
		panelBotonIferior.setLayout(new GridLayout(1,2));
		panelBotonIferior.add(bCara);
		panelBotonIferior.add(bBorrarSelec);
		panelBotonIferior.add(bCruz);
		panelPrincipal.add(panelBotonIferior, BorderLayout.SOUTH);
			
		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
			
		JPanel panelMoneda = new JPanel();
		lFotos = new JLabel();
		panelMoneda.setBackground(Color.white);
		panelMoneda.add(lFotos);
		panelMoneda.add(new JLabel(new ImageIcon("resources/images/caraCruz/cara.png")));
			
		JPanel panelHistorico = new JPanel();
		panelHistorico.setLayout(new GridLayout(2, 1));
		panelHistorico.add(new JLabel("Historial de juego"));
		panelHistorico.add(scroll);
			
		JPanel menuHisto = new JPanel();
		menuHisto.setLayout(new GridLayout());
      	menuHisto.add(panelHistorico);

		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup().addGap(100)
						.addComponent(panelMoneda, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
						.addComponent(panelHistorico, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(75)));
		
		gl_panelCentral.setVerticalGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup().addGap(100)
						.addComponent(panelMoneda, GroupLayout.PREFERRED_SIZE, 230,GroupLayout.PREFERRED_SIZE)
						.addContainerGap(85, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
						.addContainerGap(110, Short.MAX_VALUE)
						.addComponent(panelHistorico,GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addGap(125)));
			panelCentral.setLayout(gl_panelCentral);
		
		
	  menuGeneral.enseñarApostar(panelMenuInferior);
	  menuGeneral.enseñarMenu(panelMenuSuperior, menu);
		      
        //listeners
        bCara.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				bCara.setEnabled(false);
				bCruz.setEnabled(true);
				caraCruz = "Cara";
				logger.info("Has seleccionado cara");
			}
		});
        
        bCruz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bCara.setEnabled(true);
				bCruz.setEnabled(false);
				caraCruz = "Cruz";
				logger.info("Has seleccionado cruz");
			}
		});
        
        bBorrarSelec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bCara.setEnabled(true);
				bCruz.setEnabled(true);
				logger.info("Has eliminado seleccionado");
			}
		});
        
        VentanaPanelMenu.bApostar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Ha empezado la apuesta");
			    hilo = new Thread() {	
				public void run()
				{
					for( int i = 1 ; i <= Math.random()*10+10; i++)
					{
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (cont == 1 )
						{
							//cara
							lFotos.setIcon( new ImageIcon ("resources/images/caraCruz/cara.png"));
						}
						else if ( cont == 2 )
						{
							//cruz
							lFotos.setIcon( new ImageIcon ("resources/images/caraCruz/cruz.png"));
						}
						else
						{
							//canto
							lFotos.setIcon( new ImageIcon ("resources/images/caraCruz/canto.png"));
					
						}
						cont++;
						if ( cont == 3 )
							cont = 0;
					}			
					
					if ( cont == 1 ){
						for (int i = 0; i < Math.random()*2; i++) {
							
							if (i == 0) {
								resultado = "Cara";
								lFotos.setIcon( new ImageIcon ("resources/caraCruz/images/cara.png"));
							}else {
								resultado = "Cruz";
								lFotos.setIcon( new ImageIcon ("resources/caraCruz/images/cruz.png"));
							}
						}
					}else if(cont == 2) {
						resultado = "Cara";
					}else {
						resultado = "Cruz";
					}
					dlmHistorial.addElement(resultado);
					logger.info("Ha salido" + resultado );
					
					 if (resultado == caraCruz) {
				            JOptionPane.showMessageDialog(
				                null,
				                resultado+ " \n¡Has ganado! ¡Felicidades!",
				                "Resultado",
				                JOptionPane.INFORMATION_MESSAGE
				            );
				        } else {
				            JOptionPane.showMessageDialog(
				                null,
				                resultado+ ".\n Has perdido. Mejor suerte la próxima vez.",
				                "Resultado", JOptionPane.ERROR_MESSAGE
				            );
				        }
					
					//reiniciar botones
					bCara.setEnabled(true);
					bCruz.setEnabled(true);
					
					hilo.stop();
					}
				
			};	
			hilo.start();
				
				
			}
		}); 
        
	}
	 
  	public static Boolean obtenerCara(String resultado) {
  		
  		if (resultado == "Cara") {
  			return true;
  		}else {
  			return false;
  	  	}
  	}
  			
  	
  	public static boolean obtenerCruz(String resultado) {
  		
  		if (resultado == "Cruz") {
  			return true;
  		}else {
  			return false;
  		}	
  	}
  	
  	public static boolean obtenerEnabledCara(boolean getEnabled) {
  		
  		if (getEnabled == true) {
  			return true;
  		}else {
  			return false;
  		}
  	}
  	
  	public static boolean obtenerEnabledCruz(boolean getEnabled) {
  		
  		if (getEnabled == true) {
  			return true;
  		}else {
  			return false;
  		}
  	}

}
	