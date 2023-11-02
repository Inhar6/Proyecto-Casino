package gui;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import domain.Carta;

public class VentanaBlackJack extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("VentanaBlackJack");
	//Atributos
	private List<Carta> listaCartas = crearCarta(crearBaraja());
	


	public VentanaBlackJack() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Black Jack");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/iconos/favicon.png").getImage());
		
		// Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();			
		add(menuSuperior);
		setJMenuBar(menuBar1);
        // Añadir menuApostar
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);

        menuGeneral.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaBlackJack = 0; // Reiniciar el contador
            }
        });
        
        
        
       
     
      
       
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelTitulo = new JPanel(new GridLayout(1,3));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel paneldecartas = new JPanel((new GridLayout()));
        JPanel panelBotones = new JPanel(new GridLayout(1,3));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        
     
        add(panelPrincipal);
        panelPrincipal.add(panelTitulo,BorderLayout.NORTH);
        panelPrincipal.add(paneldecartas,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
        
        JLabel labelTitulo = new JLabel("BLACKJACK");
        Font fuente = new Font("Arial",Font.BOLD,40);
        labelTitulo.setFont(fuente);
        
        
        
        
        JTextArea JTextAreaCartas = new JTextArea();
        
        
     
 
        JButton botonPedirCarta = new JButton("Pedir una carta");
        JButton botonPlantarse = new JButton("Plantarse");
        JButton botonDoblar = new JButton("Doblar");
        
        JButton botonAyuda = new JButton(new ImageIcon("resources/images/iconos/favicon.png"));
        botonAyuda.setPreferredSize(new Dimension (30,30));
        
        panelTitulo.add(botonAyuda);
        panelTitulo.add(labelTitulo);
        
        paneldecartas.add(JTextAreaCartas);
       
        
        panelBotones.add(botonPedirCarta);
        panelBotones.add(botonPlantarse);
        panelBotones.add(botonDoblar);
        
        String ayuda = "A continuacion te explicaremos las reglas del juego:\n\n"
        		+ "1- Te enfrentaras al crupier de la mesa, y tendras que tratar de sumar 21 puntos sumando la puntuacion de las cartas(sin pasarte)\n\n"
        		+"2- El crupier se planta con 17 puntos\n\n"
        		+"3-Las cartas del 2-10 puntuaran su valor nominal\n\n"
        		+"4-Las cartas (J,Q,K) puntuaran 10 puntos\n\n"
        		+"5-La carta A sera el comodin, y podra ser usada tanto con el valor 1 o 11\n\n"
        		+"6-Tendras la opcion de doblar la aupesta durante la partida mediante el boton de doblar";
        
        
        botonAyuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, ayuda, "Reglas del juego", JOptionPane.INFORMATION_MESSAGE);
	
				
			}
		});
        
        
        
        botonPedirCarta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Has pedido una carta");
				
				
			}
		});
        
        botonPlantarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Has pulsado el boton plantarse");
				System.out.println(listaCartas);
				System.out.println(BarajarCartas(listaCartas));
				
			}
		});
        
        botonDoblar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Has doblado la apuesta");
				System.out.println(crearBaraja());
				
			//	System.out.println(crearCarta(CrearBaraja()));
				//System.out.println(BarajarCartas(crearCarta(CrearBaraja())));
				
				
			}
		});
       
        
        
       
        
	}
	
	
	
	
    //Metodo crear la baraja
    public Map<String,List<String>> crearBaraja() {
    	Map<String,List<String>> mapaCartas = new HashMap<>();
    	 List<Carta> baraja = new ArrayList<>();
    	 String[] numeros = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	 String[] palos = {"Corazones","Treboles","Picas","Diamantes"};
    	 
    	 mapaCartas.putIfAbsent("Corazones", new ArrayList<>());
    	 mapaCartas.putIfAbsent("Treboles", new ArrayList<>());
    	 mapaCartas.putIfAbsent("Picas", new ArrayList<>());
    	 mapaCartas.putIfAbsent("Diamantes", new ArrayList<>());
    	 for ( String numero : numeros) {
    		 mapaCartas.get("Corazones").add(numero);
    	 }
    	 
    	 mapaCartas.putIfAbsent("Treboles", new ArrayList<>());
    	 for ( String numero : numeros) {
    		 mapaCartas.get("Treboles").add(numero);
    	 }
    	 
    	 mapaCartas.putIfAbsent("Picas", new ArrayList<>());
    	 for ( String numero : numeros) {
    		 mapaCartas.get("Picas").add(numero);
    	 }
    	 
    	 mapaCartas.putIfAbsent("Diamantes", new ArrayList<>());
    	 for ( String numero : numeros) {
    		 mapaCartas.get("Diamantes").add(numero);
    	 }
    	 
		return mapaCartas;
   
    	 
    }
    
    public List<Carta> crearCarta(Map<String,List<String>> mapa) {
    	List<Carta> lista = new ArrayList<>();
    	for(String palo : mapa.keySet() ) {
    		for (String numero : mapa.get(palo)) {
    			lista.add(new Carta(numero,palo));
    		}
    	}
    	
		return lista;
    	
    }
    // Metodo barajear cartas
    public List<Carta> BarajarCartas(List<Carta> baraja) {
    	Collections.shuffle(baraja);
    	return baraja;
   
    }
    
	

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaBlackJack();				
			}
        });
        
        
	}
}
