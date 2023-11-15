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
	private static final int limitePulsaciones = 3;


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
		JPanel menuSuperior = new JPanel(new BorderLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu(); // Crea una ventana "VenatanaPanelMenu"
        add(menuSuperior, BorderLayout.NORTH);
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
                VentanaPanelMenu.contadorVentanaJuego = 0; // Reiniciar el contador
            }
        });
        
       
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelTitulo = new JPanel(new GridLayout(1,3));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel paneldecartas = new JPanel((new GridLayout(2,1)));
        JPanel panelCrupier = new JPanel(new GridLayout(2,1));
        JPanel panelJugador = new JPanel(new GridLayout(2,1));
        JPanel panelBotones = new JPanel(new GridLayout(1,4));
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
        
     
        JButton botonJugar = new JButton("Jugar");
        JButton botonPedirCarta = new JButton("Pedir una carta");
        JButton botonPlantarse = new JButton("Plantarse");
        JButton botonDoblar = new JButton("Doblar");
        
        JButton botonAyuda = new JButton(new ImageIcon("resources/images/iconos/favicon.png"));
        botonAyuda.setPreferredSize(new Dimension (30,30));
        
        panelTitulo.add(botonAyuda);
        panelTitulo.add(labelTitulo);
        
        panelBotones.add(botonJugar);
        panelBotones.add(botonPedirCarta);
        panelBotones.add(botonPlantarse);
        panelBotones.add(botonDoblar);
        
        String ayuda = "A continuacion te explicaremos las reglas del juego:\n\n"
        		+ "1- Te enfrentaras al crupier de la mesa, y tendras que tratar de sumar 21 puntos sumando la puntuacion de las cartas(sin pasarte)\n\n"
        		+"2- El crupier se planta con 17 puntos\n\n"
        		+"3-Las cartas del 1-10 puntuaran su valor nominal\n\n"
        		+"4-Las cartas (J,Q,K) puntuaran 10 puntos\n\n"
        		+"5-Tendras la opcion de doblar la aupesta durante la partida mediante el boton de doblar";
        
        
        
        
        botonPedirCarta.setEnabled(false);
        botonPlantarse.setEnabled(false);
        botonDoblar.setEnabled(false);
        
        botonJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  botonPedirCarta.setEnabled(true);
			      botonPlantarse.setEnabled(true);
			      botonDoblar.setEnabled(true);
			      ImprimirCartasCrupier(textAreaCrupier, listaCartasBarajeada);
			      ImprimirCartasCrupier(textAreaCrupier, listaCartasBarajeada);
			      ImprimirCartasJugador(textAreaJugador, listaCartasBarajeada);
			      ImprimirCartasJugador(textAreaJugador, listaCartasBarajeada);
			      botonJugar.setEnabled(false);
				
			}
		});
        
        
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
				
				String textoActualCrupier = textAreaCrupier.getText();
				String textoActualJugador = textAreaJugador.getText();
				
				int puntuacionCrupier = ContadorPuntuacionCrupier(textoActualCrupier);
				int puntuacionJugador = ContadorPuntuacionJugador(textoActualJugador);
				
				textAreaJugador.setText(textoActualJugador+ "\n"+ "Puntuacion:" + puntuacionJugador);
				while(puntuacionCrupier<18) {
					ImprimirCartasCrupier(textAreaCrupier, listaCartasBarajeada);
					
					puntuacionCrupier = ContadorPuntuacionCrupier(textoActualCrupier);
					textoActualCrupier = textAreaCrupier.getText();
				}
				textAreaCrupier.setText(textoActualCrupier+ "\n"+ "Puntuacion:" + puntuacionCrupier);
				saberGanador(puntuacionCrupier,puntuacionJugador);
			}
		});
        
        botonPlantarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				botonPlantarse.setEnabled(false);
				botonPedirCarta.setEnabled(false);
				botonDoblar.setEnabled(false);
				
			}
		});
        
        botonDoblar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Has doblado la apuesta");
				
				
				
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
                textoActual += ";"; 
            }
            String stringCarta = carta.toString();
            textoActual += stringCarta;
            jtextarea.setText(textoActual);
    	}
    
    }
    
    public void ImprimirCartasCrupier(JTextArea jtextarea ,List<Carta> listaCartas) {

    	
    	Carta carta = RepartirCarta(listaCartasBarajeada);
    	if(carta!= null) {
    		 String textoActual =jtextarea.getText();
    		if (!textoActual.isEmpty()) {
                textoActual += ";"; 
            }
            String stringCarta = carta.toString();
            textoActual += stringCarta;
            jtextarea.setText(textoActual);
    	}
    	
    }
    
    public int ContadorPuntuacionCrupier(String textoCartas) {
    	String[] separacionCartas = textoCartas.split(";");
    	int sumaDePuntuacion = 0;
    	for (String c : separacionCartas) {
    		String[] separacionCarta = c.split("-");
    		if(separacionCarta.length >=2) {
    			
    			String numeroCarta = separacionCarta[1].trim();
    			try {
    			if(numeroCarta.equals("A")){
    				sumaDePuntuacion+=1;
    				
    				}else if(numeroCarta.equals("J")||numeroCarta.equals("Q")||numeroCarta.equals("K")) {
    					sumaDePuntuacion+= 10;
    					
    				}else {
    					sumaDePuntuacion+= Integer.parseInt(numeroCarta);
    				}}
    			catch(NumberFormatException e){
    					e.printStackTrace();
    				}
    			}
     	}
    	return sumaDePuntuacion;
    }
    
    public int ContadorPuntuacionJugador(String textoCartas) {
    	String[] separacionCartas = textoCartas.split(";");
    	int sumaDePuntuacion = 0;
    	for (String c : separacionCartas) {
    		String[] separacionCarta = c.split("-");
    		if(separacionCarta.length >=2) {
    			
    			String numeroCarta = separacionCarta[1].trim();
    			try {
    			if(numeroCarta.equals("A")){
    				sumaDePuntuacion+=1;
    				
    				}else if(numeroCarta.equals("J")||numeroCarta.equals("Q")||numeroCarta.equals("K")) {
    					sumaDePuntuacion+= 10;
    					
    				}else {
    					sumaDePuntuacion+= Integer.parseInt(numeroCarta);
    				}}
    			catch(NumberFormatException e){
    					e.printStackTrace();
    				}
    			}
     	}
    	return sumaDePuntuacion;
    }
    
    public void saberGanador(int puntuacionCrupier, int puntuacionJugador) {
    	if(puntuacionCrupier>21 & puntuacionJugador>21) {
    		JOptionPane.showMessageDialog(null, "Has empatado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    		
    	}else if(puntuacionCrupier>21 && puntuacionJugador<=21) {
    		JOptionPane.showMessageDialog(null, "Has ganado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    		
    	}else if(puntuacionCrupier<=21 && puntuacionJugador>21){
    		JOptionPane.showMessageDialog(null, "Has perdido", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    	}else {
    		int jugadorGanador = (Math.abs(21-puntuacionCrupier)<Math.abs(21-puntuacionJugador))?1:2;
    		 if (Math.abs(21 - puntuacionCrupier) == Math.abs(21 - puntuacionJugador)) {
    			 JOptionPane.showMessageDialog(null, "Has empatado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    			 
             }else if(jugadorGanador == 1) {
            	 JOptionPane.showMessageDialog(null, "Has perdido", "Resultado",JOptionPane.INFORMATION_MESSAGE);
             }else if(jugadorGanador == 2) {
            	 JOptionPane.showMessageDialog(null, "Has Ganado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
             }
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
