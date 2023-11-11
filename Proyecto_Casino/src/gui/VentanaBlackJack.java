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
import java.util.Random;
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
	private List<Carta> listaCartasBarajeada = BarajarCartas(listaCartas);
	private int contadorBoton = 0;
	private static final int limitePulsaciones = 5;


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
        JPanel paneldecartas = new JPanel((new GridLayout(2,1)));
        JPanel panelCrupier = new JPanel(new GridLayout(2,1));
        JPanel panelJugador = new JPanel(new GridLayout(2,1));
        JPanel panelBotones = new JPanel(new GridLayout(1,3));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        
     
        add(panelPrincipal);
        panelPrincipal.add(panelTitulo,BorderLayout.NORTH);
        panelPrincipal.add(paneldecartas,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
        
        paneldecartas.add(panelCrupier);
        paneldecartas.add(panelJugador);
        
        JLabel labelTitulo = new JLabel("BLACKJACK");
        Font fuente = new Font("Arial",Font.BOLD,40);
        labelTitulo.setFont(fuente);
        
        JLabel labelCrupier = new JLabel("CRUPIER");
        JTextArea textAreaCrupier = new JTextArea();
        JLabel labelJugador = new JLabel("JUGADOR");
        JTextArea textAreaJugador = new JTextArea();
        
        panelCrupier.add(labelCrupier);
        panelCrupier.add(textAreaCrupier);
        panelJugador.add(labelJugador);
        panelJugador.add(textAreaJugador);
     
 
        JButton botonPedirCarta = new JButton("Pedir una carta");
        JButton botonPlantarse = new JButton("Plantarse");
        JButton botonDoblar = new JButton("Doblar");
        
        JButton botonAyuda = new JButton(new ImageIcon("resources/images/iconos/favicon.png"));
        botonAyuda.setPreferredSize(new Dimension (30,30));
        
        panelTitulo.add(botonAyuda);
        panelTitulo.add(labelTitulo);
        
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
				ImprimirCartasCrupier(textAreaCrupier, listaCartasBarajeada);
				logger.info("Has pedido una carta");
				if(contadorBoton< limitePulsaciones) {
					ImprimirCartasJugador(textAreaJugador, listaCartasBarajeada);
					contadorBoton++;
				}else if (contadorBoton>= limitePulsaciones) {
					botonPedirCarta.setEnabled(false);
					JOptionPane.showMessageDialog(null,"Has alcanzado el limite de cartas","Limite de cartas",JOptionPane.INFORMATION_MESSAGE);
					logger.info("Has alcanzado el limite de cartas");

				}
				
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
    
    public Carta RepartirCarta(List<Carta> listaCartasBarajeada){
    	Random random = new Random();
    	int numeroRandom = random.nextInt(listaCartasBarajeada.size());
    	Carta cartaDevolver = listaCartas.get(numeroRandom);
    	listaCartasBarajeada.remove(numeroRandom);
    	return cartaDevolver;
    	
    }
    
    public void ImprimirCartasJugador(JTextArea jtextarea ,List<Carta> listaCartas) {
    	
    	Carta carta = RepartirCarta(listaCartasBarajeada);
    	
    	if(carta!= null) {
    		String textoActual =jtextarea.getText();
    		if (!textoActual.isEmpty()) {
                textoActual += ", "; // 
            }
            String stringCarta = carta.toString();
            textoActual += stringCarta;
            jtextarea.setText(textoActual);
    	}
    }
    
    public void ImprimirCartasCrupier(JTextArea jtextarea ,List<Carta> listaCartas) {
    	
    	
    	if(jtextarea.getText().isEmpty()) {
    		Carta carta = RepartirCarta(listaCartasBarajeada);
        	Carta carta2 = RepartirCarta(listaCartasBarajeada);
        	String cartaString = carta.toString();
        	String cartaString2 = carta2.toString();
        	String textoActual = jtextarea.getText();
        	String imprimir = textoActual + cartaString+","+cartaString2;
        	jtextarea.setText(imprimir);
    	}
    	
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
