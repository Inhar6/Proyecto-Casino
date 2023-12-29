package gui;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.DBManager;
import domain.Carta;
import domain.Point;
import domain.Usuario;

public class VentanaBlackJack extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("VentanaBlackJack");
	//Atributos
	private List<Carta> listaCartas = crearBarajaCartas(crearMapaBaraja());
	private List<Carta> listaCartasBarajeada = BarajarCartas(listaCartas);
	private int contadorBoton = 0;
	private static final int limitePulsaciones = 3;
	
	private JTable tabla;
	private DefaultTableModel defaultTableModel;
	private JScrollPane scrollPane;
	
	private Map<Integer,Map<String,Map<Integer,Double>>> mapaBlackJack = new HashMap<>();
	
	
	private String ganador = "";
	
	private int contadorPartida = 1;
	
	//Apuesta
	private double ap = 0.0;
	
	// 
	private Usuario user = new Usuario();
	private int marca = 0;



	public VentanaBlackJack(Usuario u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Black Jack");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/iconos/favicon.png").getImage());
		
		user = u;
		marca = user.getLstBalance().size();
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
        menuGeneral.enseñarMenu(menuSuperior, menu, VentanaPanelMenu.user);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaJuego = 0; // Reiniciar el contador
            }
        });
        
       //Paneles
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelTitulo = new JPanel(new GridLayout(1,2));
        JPanel paneldecartas = new JPanel((new GridLayout(2,1)));
        JPanel panelCrupier = new JPanel(new GridLayout(2,1));
        JPanel panelJugador = new JPanel(new GridLayout(2,1));
        JPanel panelBotones = new JPanel(new GridLayout(1,4));
        JPanel panelHistorial = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //LabelTitulo
        JLabel labelTitulo = new JLabel("BLACKJACK");
        Font fuente = new Font("Arial",Font.BOLD,40);
        labelTitulo.setFont(fuente);
        
        //Label y TextAreas Crupier y Jugador
        JLabel labelCrupier = new JLabel("CRUPIER");
        JTextArea textAreaCrupier = new JTextArea();
        JLabel labelJugador = new JLabel("JUGADOR");
        JTextArea textAreaJugador = new JTextArea();
        
        //Botones
        JButton botonPedirCarta = new JButton("Pedir una carta");
        JButton botonPlantarse = new JButton("Plantarse");
        JButton botonDoblar = new JButton("Doblar");
        JButton botonAyuda = new JButton(new ImageIcon("resources/images/iconos/favicon.png"));
        botonAyuda.setPreferredSize(new Dimension (30,30));
        JButton botonHistorial = new JButton("Historial");
        
        //Añadir al panel Principal
        add(panelPrincipal);
        panelPrincipal.add(panelTitulo,BorderLayout.NORTH);
        panelPrincipal.add(paneldecartas,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
        
        //Añadir al panel de cartas
        paneldecartas.add(panelCrupier);
        paneldecartas.add(panelJugador);
       
        //Añadir al panelCrupier
        panelCrupier.add(labelCrupier);
        panelCrupier.add(textAreaCrupier);
        
        //Añadir al panelJugador
        panelJugador.add(labelJugador);
        panelJugador.add(textAreaJugador);
        
        //Añadir al panel Titulo
        panelTitulo.add(botonAyuda);
        panelTitulo.add(labelTitulo);
        panelTitulo.add(panelHistorial);
        
        //Historial JTextArea

        
      //Tabla historial
        defaultTableModel= new DefaultTableModel();
      	tabla = new JTable(defaultTableModel);
      	tabla.setEnabled(true);
      	scrollPane = new JScrollPane(tabla);
      	
    	defaultTableModel.addColumn("Partida");
    	defaultTableModel.addColumn("Resultado");
    	defaultTableModel.addColumn("Puntuacion");
    	defaultTableModel.addColumn("Ganancia/Perdida");
    	

      //	tabla.setDefaultRenderer(Object.class, new MyRender());
 
		
		
        panelBotones.add(botonPedirCarta);
        panelBotones.add(botonPlantarse);
        panelBotones.add(botonDoblar);
        panelBotones.add(botonHistorial);
        
        
        String ayuda = "A continuacion te explicaremos las reglas del juego:\n\n"
        		+ "1- Te enfrentaras al crupier de la mesa, y tendras que tratar de sumar 21 puntos sumando la puntuacion de las cartas(sin pasarte)\n\n"
        		+"2- El crupier se planta con 17 puntos\n\n"
        		+"3-Las cartas del 1-10 puntuaran su valor nominal\n\n"
        		+"4-Las cartas (J,Q,K) puntuaran 10 puntos\n\n"
        		+"5-Tendras la opcion de doblar la aupesta durante la partida mediante el boton de doblar";
        
        
        
        botonPedirCarta.setEnabled(false);
        botonPlantarse.setEnabled(false);
        botonDoblar.setEnabled(false);
        
       
        
        
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
				double ganancia =saberGanador(puntuacionCrupier,puntuacionJugador);
				VentanaPanelMenu.balance +=ganancia;
				VentanaPanelMenu.lBalance.setText("Balance: "+ VentanaPanelMenu.balance);
				
				
				saberGanadorActualizarTabla(puntuacionCrupier, puntuacionJugador);
				mapaHistorial(mapaBlackJack, puntuacionCrupier, puntuacionJugador);
				System.out.println(mapaBlackJack);
				
				if(ganador == "Crupier") {
					defaultTableModel.addRow(new Object[] {contadorPartida,"Crupier",puntuacionCrupier,"-"+ap});
				}else if(ganador == "Jugador") {
					defaultTableModel.addRow(new Object[] {contadorPartida,"Jugador",puntuacionJugador,"+"+ap});
				}else if(ganador == "Empate"){
					defaultTableModel.addRow(new Object[] {contadorPartida,"Empate","0"});
				}
				
				contadorPartida++;
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
				ap = VentanaPanelMenu.apuesta *2;
		
				
				
				
			}
		});

     VentanaPanelMenu.bApostar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(VentanaPanelMenu.apuesta == 0 ) {
				JOptionPane.showMessageDialog(null, "Debes introducir una cantidad para apostar", "Apuesta",JOptionPane.INFORMATION_MESSAGE);
			}else {
				ap = VentanaPanelMenu.apuesta;
				VentanaPanelMenu.balance -= VentanaPanelMenu.apuesta;

				reiniciarJuego( botonPlantarse, botonPedirCarta, botonDoblar, textAreaCrupier, textAreaJugador);
				ImprimirCartasCrupier(textAreaCrupier, listaCartasBarajeada);
				ImprimirCartasJugador(textAreaJugador, listaCartasBarajeada);
				ImprimirCartasJugador(textAreaJugador, listaCartasBarajeada);
				
				botonPedirCarta.setEnabled(true);
				botonPlantarse.setEnabled(true);
				botonDoblar.setEnabled(true);
			}
			
	
			
		}
	});
     
     botonHistorial.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			mostrarTabla();
		
			
		}
	});

        
	}
	
	

	
	
	public void mostrarTabla() {
		JOptionPane.showMessageDialog(null, scrollPane, "Tabla", JOptionPane.PLAIN_MESSAGE);
		
	}

	
	public class MyRender extends JLabel implements TableCellRenderer {

	    private static final long serialVersionUID = 1L;
	    
	    
	    public MyRender(){
	    	
	    }
	    
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
				
		setText(value.toString());
		setOpaque(false);
			
		if("Jugador".equals(value) && column==1) {
			setBackground(Color.GREEN);
			setOpaque(true);
		}else if ("Crupier".equals(value)&& column == 1) {
			setBackground(Color.red);
			setOpaque(true);
				
		}else {
			setBackground(table.getBackground());
			}
			
			return this;
		}


	    }
	public void mapaHistorial(Map<Integer,Map<String,Map<Integer,Double>>> mapaHistorial ,int puntuacionCrupier, int puntuacionJugador){
		saberGanadorActualizarTabla(puntuacionCrupier, puntuacionJugador);
		
		int contador = contadorPartida ;
		String nombre = "";
		int a = 0;
		double b = 0.0;
		
		if (ganador == "Crupier") {
		    mapaHistorial.put(contadorPartida, new HashMap<>());
		    mapaHistorial.get(contadorPartida).put("Crupier",new HashMap<>());
		    nombre = "Crupier";
		    mapaHistorial.get(contadorPartida).get("Crupier").put(puntuacionCrupier, -ap);
		    a = puntuacionCrupier;
		    b = -ap;
		    
		    
	    } else if(ganador == "Jugador"){
	        mapaHistorial.put(contadorPartida, new HashMap<>());
		    mapaHistorial.get(contadorPartida).put("Jugador",new HashMap<>());
		    nombre = "Jugador";
		    mapaHistorial.get(contadorPartida).get("Jugador").put(puntuacionJugador, ap);
		    a = puntuacionJugador;
		    b = ap;
	    }else if (ganador =="Empate") {
	        mapaHistorial.put(contadorPartida, new HashMap<>());
		    mapaHistorial.get(contadorPartida).put("Empate",new HashMap<>());
		    nombre = "Empate";
		    mapaHistorial.get(contadorPartida).get("Empate").put(0, 0.0);
		    a = 0;
		    b = 0.0;
	    }
		
		user.addMapaBlackJack(contador, nombre, a, b);
		System.out.println(user.getMapaBlackJack());
		DBManager.addTiradaBlackJack(contador, nombre, a, b, user);
		//Grafico balance
		int x = (int) b /100;
		DBManager.addPuntoBalance(marca, x, user);
		user.addListaBalance(new Point(marca, x));
	}  

	
    //Metodo crear la baraja
    public Map<String,List<String>> crearMapaBaraja() {
    	Map<String,List<String>> mapaCartas = new HashMap<>();
    	 //List<Carta> baraja = new ArrayList<>();
    	 String[] numeros = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	 //String[] palos = {"Corazones","Treboles","Picas","Diamantes"};
    	 
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
    
    public List<Carta> crearBarajaCartas(Map<String,List<String>> mapa) {
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
    	Carta cartaDevolver = listaCartasBarajeada.get(numeroRandom);
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
    			
    			String numeroCarta = separacionCarta[0].trim();
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
    			
    			String numeroCarta = separacionCarta[0].trim();
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
    
    
    //Saber ganador para que aparezca el JoptionPane y para saber si sumarle o restarle a el saldo del jugador la apuesta hecha
    public Double saberGanador(int puntuacionCrupier, int puntuacionJugador) {
    	if(puntuacionCrupier>21 & puntuacionJugador>21) {
    		JOptionPane.showMessageDialog(null, "Has empatado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    		
    	
    		return ap;
    		
    	}else if(puntuacionCrupier>21 && puntuacionJugador<=21) {
    		JOptionPane.showMessageDialog(null, "Has ganado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
			
			return ap *2;
    		
    	}else if(puntuacionCrupier<=21 && puntuacionJugador>21){
    		JOptionPane.showMessageDialog(null, "Has perdido", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    		
    		return 0.0;
    	}
    	else {
    		int jugadorGanador = (Math.abs(21-puntuacionCrupier)<Math.abs(21-puntuacionJugador))?1:2;
    		 if (Math.abs(21 - puntuacionCrupier) == Math.abs(21 - puntuacionJugador)) {
    			 JOptionPane.showMessageDialog(null, "Has empatado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
    			
    			 return ap;
             }else if(jugadorGanador == 1) {
            	 JOptionPane.showMessageDialog(null, "Has perdido", "Resultado",JOptionPane.INFORMATION_MESSAGE);
            	 
            	 return 0.0;
             }else if(jugadorGanador == 2) {
            	 
            	 JOptionPane.showMessageDialog(null, "Has Ganado", "Resultado",JOptionPane.INFORMATION_MESSAGE);
            	 
            	
            	 return ap *2;
             }
    	}
    	return ap;
    }
    //Saber quien ha ganado para actualizar la tabla
    public void saberGanadorActualizarTabla(int puntuacionCrupier, int puntuacionJugador) {
    	
    	if(puntuacionCrupier>21 & puntuacionJugador>21) {
    		ganador = "Empate";
    		
    	}else if(puntuacionCrupier>21 && puntuacionJugador<=21) {
			ganador = "Jugador";
			
    		
    	}else if(puntuacionCrupier<=21 && puntuacionJugador>21){
    		ganador = "Crupier";
    	}
    	else {
    		int jugadorGanador = (Math.abs(21-puntuacionCrupier)<Math.abs(21-puntuacionJugador))?1:2;
    		 if (Math.abs(21 - puntuacionCrupier) == Math.abs(21 - puntuacionJugador)) {
    			ganador = "Empate";
    			
             }else if(jugadorGanador == 1) {
            	ganador = "Crupier";
            
             }else if(jugadorGanador == 2) {
            	ganador = "Jugador";
            
             }
    	}
    	
    }
    
    public void reiniciarJuego(JButton botonPlantarse, JButton botonPedirCarta, JButton botonDoblar, JTextArea textAreaCrupier, JTextArea textAreaJugador) {
    	contadorBoton = 0;
    	
    	botonPlantarse.setEnabled(false);
		botonPedirCarta.setEnabled(false);
		botonDoblar.setEnabled(false);
		
		textAreaCrupier.setText("");
		textAreaJugador.setText("");
		VentanaPanelMenu.apuesta = 0;
		
		
		  }
    
    
    
    
	


        
}


