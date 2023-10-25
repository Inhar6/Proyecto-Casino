package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
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

public class VentanaCoinFlip extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//botones
	private JButton bCara;
	private JButton bCruz;
  	
	private JButton bBorrarSelec;
	private JButton b1_2 ;
	private JButton bx2 ;
	private JButton bMax ;
  		
	private JButton bJugar;
	
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
	
	public VentanaCoinFlip() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Coin-Flip");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());

        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
        add(menuSuperior);
        setJMenuBar(menuBar1);
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaBlackJack = 0; // Reiniciar el contador
            }
        });
        
        
      //botones 
      	bCara = new JButton("Cara");
      	bCruz = new JButton("Cruz");
      	
      	bBorrarSelec = new JButton("Borrar Seleccion");
      	b1_2 = new JButton("1/2");
      	bx2 = new JButton("x2");
      	bMax = new JButton("Max");
      		
      	bJugar = new JButton("Jugar");
      		
      	//lista
      	DefaultListModel<String> dlmHistorial = new DefaultListModel<>();
      	JList<String> lstHistorial = new JList<>(dlmHistorial);
      	JScrollPane scroll = new JScrollPane(lstHistorial);
      		
      	//paneles
      	JPanel pInferior = new JPanel();
      	JPanel pInferior1 = new JPanel();
      	JPanel pInferior2 = new JPanel();
      	JPanel pJuego = new JPanel();
      	JPanel pCentro = new JPanel();
      	JPanel pHisto = new JPanel();
      	JPanel pIzquierda = new JPanel();
      	/////////////////////////////////////////
      		
      	pInferior1.setLayout(new GridLayout(1,2));
      	pInferior1.add(bCara);
      	pInferior1.add(bCruz);
      		
      	pInferior2.setLayout(new GridLayout(1,4));
      	pInferior2.add(bBorrarSelec);
      	pInferior2.add(b1_2);
      	pInferior2.add(bx2);
      	pInferior2.add(bMax);
      		
      	pInferior.setLayout(new GridLayout(2, 1));
      	pInferior.add(pInferior1);
      	pInferior.add(pInferior2);
      	
      	lFotos = new JLabel();
      	pIzquierda.setLayout(new BorderLayout());
      	
      	pIzquierda.add(lFotos, BorderLayout.NORTH); 

      	pIzquierda.setBackground(Color.white);

      	pCentro.setLayout(new BorderLayout());
      	pCentro.add(pInferior,BorderLayout.SOUTH);
      	pCentro.add(pIzquierda, BorderLayout.WEST);	
      	
//      pJuego.setLayout(new FlowLayout());
      	pJuego.add(bJugar);
      		
      	pHisto.setLayout(new GridLayout(2,1));
      	pHisto.add(new JPanel());
      	pHisto.add(new JPanel());
      	pHisto.add(scroll, BorderLayout.EAST);
      	pHisto.add(new JPanel());
      	pHisto.add(new JPanel());
      	pHisto.add(pJuego, BorderLayout.SOUTH);
     
      	
      	JPanel menuHisto = new JPanel();
      	menuHisto.setLayout(new GridLayout(2,1));
      	menuHisto.add(menuSuperior);
      	menuHisto.add(pHisto);
      		
      	JPanel coin = new JPanel();
      	coin.setLayout(new GridLayout(2, 1));
      	coin.add(menuHisto);
      	coin.add(pCentro);
      		
      	add(coin);

        menuGeneral.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
        
        //listeners
        bCara.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				bCara.setEnabled(false);
				bCruz.setEnabled(true);
				caraCruz = "Cara";
			}
		});
        
        bCruz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bCara.setEnabled(true);
				bCruz.setEnabled(false);
				caraCruz = "Cruz";
			}
		});
        
        bBorrarSelec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bCara.setEnabled(true);
				bCruz.setEnabled(true);
				
			}
		});
        
        b1_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        bx2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
        
        bMax.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
        
        bJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
							System.out.println("Cara" );
							lFotos.setIcon( new ImageIcon ("foto/cara.png"));
						}
						else if ( cont == 2 )
						{
							//cruz
							System.out.println("Cruz");
							lFotos.setIcon( new ImageIcon ("foto/cruz.png"));
						}
						else
						{
							//canto
							System.out.println("Canto");
							lFotos.setIcon( new ImageIcon ("foto/canto.png"));
					
						}
						cont++;
						if ( cont == 3 )
							cont = 0;
					}			
					
					if ( cont == 1 ){
						for (int i = 0; i < Math.random()*2; i++) {
							
							if (i == 0) {
								resultado = "Cara";
								lFotos.setIcon( new ImageIcon ("foto/cara.png"));
							}else {
								resultado = "Cruz";
								lFotos.setIcon( new ImageIcon ("foto/cruz.png"));
							}
						}
					}else if(cont == 2) {
						resultado = "Cara";
					}else {
						resultado = "Cruz";
					}
					dlmHistorial.addElement(resultado);
					//JOptionPane.showMessageDialog(null, resultado);
					
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

}
	