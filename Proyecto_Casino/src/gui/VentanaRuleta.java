package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
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
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import db.DBManager;
import domain.ApuestaRuleta;
import domain.Point;
import domain.Usuario;
import io.Propiedades;


public class VentanaRuleta extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static int[] rojo = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
	//Apuesta unica
	private Map<String, Map<Integer, Double>> mapaApuestas = new HashMap<>();
	//Logger
	private static final Logger logger = Logger.getLogger("Ventana Ruleta");
	//Elementos
	//Botones
	private JButton btnVerde;
	private JButton btnRojo;
	private JButton btnNegro;
	private JButton btn1Docena;
	private JButton btn2Docena;
	private JButton btn3Docena;
	private JButton btn1a18;
	private JButton btn19a36;
	private JButton btnPar;
	private JButton btnImpar;
	private JButton btn1Fila;
	private JButton btn2Fila;
	private JButton btn3Fila;
	private JButton btnJugar;
	private JButton btnBorrarApuesta;
	//JList
	private JList<Integer> lstHistorial;
	private DefaultListModel<Integer> dlmHistorial;
	private JScrollPane scroll;
	//Random
	Random random = new Random();
	//Apuestas
	private double dineroTotalInicial;
	private double dineroTotal = 0;
	private double dineroApostadoTotal=0;
	private double dineroApostado=0;
	private JLabel saldo;
	private JLabel saldoApostado;
	private JButton btnSacarDinero;
	private JButton btnapuesta1;
	private JButton btnapuesta10;
	private JButton btnapuesta20;
	private JButton btnapuesta50;
	private JButton btnapuesta100;
	private JButton btnapuesta1k;
	//Usuario
	private Usuario u = new Usuario();
	//Historial de balance
	public static Map<Integer, Map<Integer, Double>> mapaTiradas = new HashMap<>();
	private int tirada = u.getMapaRuleta().size() + 1;
	//Lista Balance
	public static List<Point> lstBalance = new ArrayList<>();
	private int marca = 0;
	//Propiedades
	private Propiedades propiedades;
	public Propiedades getPropiedades() {
		return propiedades;
	}
	//Imagen
	private JLabel etiquetaImagen;
					
	public VentanaRuleta(Usuario user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 700);
		setTitle("Ruleta");
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("resources/images/iconos/favicon.png").getImage());
		
		//Propiedades
		propiedades= new Propiedades();
		propiedades.cargar();
		//Usuario
		System.err.println(user);
		u= user;
		//Botones
		btnVerde = new JButton("Verde");
			btnVerde.setBackground(Color.green);
			btnVerde.setForeground(Color.white);
		btnRojo = new JButton("Rojo");
			btnRojo.setBackground(Color.red);
			btnRojo.setForeground(Color.white);
		btnNegro = new JButton("Negro");
			btnNegro.setBackground(Color.black);
			btnNegro.setForeground(Color.white);
		btn1Docena = new JButton("1º 12");
		btn2Docena = new JButton("2º 12");
		btn3Docena = new JButton("3º 12");
		btn1a18 = new JButton("1-18");
		btn19a36 = new JButton("19-36");
		btnPar = new JButton("Par");
		btnImpar = new JButton("Impar");
		btn1Fila = new JButton("2a1");
		btn2Fila = new JButton("2a1");
		btn3Fila = new JButton("2a1");
		btnJugar = new JButton("Jugar");
		btnBorrarApuesta= new JButton("Borrar apuesta");
		//Apuesta
		saldo = new JLabel("---- " + dineroTotal + " ----");
		saldoApostado= new JLabel("---- " + saldoApostado + " ----");
		Border lineaApuesta = BorderFactory.createLineBorder(Color.red);
		Border tituloAPuesta= BorderFactory.createTitledBorder(lineaApuesta,"Saldo de Ruleta");
		Border lineaSaldo = BorderFactory.createLineBorder(Color.black);
		Border tituloSaldo= BorderFactory.createTitledBorder(lineaSaldo,"Saldo Apostado");
		btnSacarDinero = new JButton("Recuperar saldo");
		btnapuesta1 = new JButton("1");
		btnapuesta10= new JButton("10");
		btnapuesta20= new JButton("20");
		btnapuesta50= new JButton("50");
		btnapuesta100= new JButton("100");
		btnapuesta1k= new JButton("1K");
		Border lineaFichas = BorderFactory.createLineBorder(Color.black);
		Border tituloFichas= BorderFactory.createTitledBorder(lineaFichas,"Fichas");
		//Apuesta unica
		mapaApuestas.put("Color", new HashMap<>());
		mapaApuestas.put("Docena", new HashMap<>());
		mapaApuestas.put("Rango", new HashMap<>());
		mapaApuestas.put("Par", new HashMap<>());
		mapaApuestas.put("Fila", new HashMap<>());
		//IMAGEN
		etiquetaImagen = new JLabel(new ImageIcon(getPropiedades().getProperty("mesaRuleta")));
		//Parte del Historial
		dlmHistorial = new DefaultListModel<>();
		lstHistorial = new JList<>(dlmHistorial);
		//Para dar colores a los numeros de la lista
		lstHistorial.setCellRenderer(new MyCellRender());
		//Para que la lista se visualize de manera horizontal
		lstHistorial.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		lstHistorial.setVisibleRowCount(1);
		//lstHistorial.setFont(new Font("Arial", Font.BOLD,16));
		scroll=new JScrollPane(lstHistorial);
		
		JPanel central = new JPanel();
		JPanel inferior = new JPanel();
		JPanel inferior1 = new JPanel();
		JPanel inferior2 = new JPanel();
		JPanel derecho = new JPanel();
		JPanel histo = new JPanel();
		JPanel resultados = new JPanel();
		JPanel pBalance = new JPanel();	
			JPanel pDinero = new JPanel();
			JPanel pSaldo = new JPanel();
			JPanel pRecuperar = new JPanel();
		JPanel pFichas = new JPanel();
			JPanel fichas = new JPanel();
		JPanel juego = new JPanel();
		
		////////////////////////////
		//Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
        // Añadir menuApostar
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuSuperior,BorderLayout.NORTH);
        add(menuInferior, BorderLayout.SOUTH);
		///////////////////////////////
        //RULETA
		inferior1.setLayout(new GridLayout(1,3));
		inferior1.add(btn1Docena);
		inferior1.add(btn2Docena);
		inferior1.add(btn3Docena);
		inferior2.setLayout(new GridLayout(1,6));
		inferior2.add(btn1a18);
		inferior2.add(btnPar);
		inferior2.add(btnRojo);
		inferior2.add(btnNegro);
		inferior2.add(btnImpar);
		inferior2.add(btn19a36);
		inferior.setLayout(new GridLayout(2, 1));
		inferior.add(inferior1);
		inferior.add(inferior2);
		derecho.setLayout(new GridLayout(3,1));
		derecho.add(btn3Fila);
		derecho.add(btn2Fila);
		derecho.add(btn1Fila);
		central.setLayout(new BorderLayout());
		central.add(btnVerde, BorderLayout.WEST);
		central.add(inferior,BorderLayout.SOUTH);
		central.add(derecho,BorderLayout.EAST);
		//IMAGEN
		central.add(etiquetaImagen,BorderLayout.CENTER);
		
		juego.setLayout(new FlowLayout());
		juego.add(btnJugar);
		juego.add(btnBorrarApuesta);
		
		//Apuesta
		pDinero.setLayout(new BorderLayout());
		pDinero.setBorder(tituloAPuesta);
		pDinero.add(saldo, BorderLayout.CENTER);
		pSaldo.setLayout(new BorderLayout());
		pSaldo.setBorder(tituloSaldo);
		pSaldo.add(saldoApostado,BorderLayout.CENTER);
		pRecuperar.setLayout(new BorderLayout());
		pRecuperar.add(btnSacarDinero, BorderLayout.SOUTH);
		pBalance.setLayout(new GridLayout(1,3));
		pBalance.add(pDinero);
		pBalance.add(pSaldo);
		pBalance.add(pRecuperar);
		fichas.setLayout(new FlowLayout());
		fichas.add(btnapuesta1);
		fichas.add(btnapuesta10);
		fichas.add(btnapuesta20);
		fichas.add(btnapuesta50);
		fichas.add(btnapuesta100);
		fichas.add(btnapuesta1k);
		pFichas.setLayout(new BorderLayout());
		pFichas.setBorder(tituloFichas);
		pFichas.add(fichas, BorderLayout.NORTH);
		resultados.setLayout(new GridLayout(2,1));
		resultados.add(new JPanel());
		resultados.add(scroll);
		
		histo.setLayout(new GridLayout(3,2));
		histo.add(pBalance);
		histo.add(new JPanel());
		histo.add(resultados);
		histo.add(pFichas);
		histo.add(new JPanel());
		histo.add(juego);

		JPanel ruleta = new JPanel();
		ruleta.setLayout(new GridLayout(2, 1));
		ruleta.add(histo);
		ruleta.add(central);
		
		////////////////////////
        setJMenuBar(menuBar1);
        menuGeneral.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaJuego = 0; // Reiniciar el contador
            }
        });
		////////////////////////
		
		add(ruleta);

		//pack();
		
		//Color verde
		btnVerde.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					btnVerde.setEnabled(false);
					//Apuesta unica
					//Verde == 1
					mapaApuestas.get("Color").put(1, dineroApostado);
					//Sumar dinero al total apostado
					dineroApostadoTotal+=dineroApostado;
					actualizarSaldos();
			}
		});
		//Color Rojo
		btnRojo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnRojo.setEnabled(false);
				//Apuesta unica
				//Rojo == 2
				mapaApuestas.get("Color").put(2, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Color Negro
		btnNegro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNegro.setEnabled(false);
				//Apuesta unica
				//Negro == 3
				mapaApuestas.get("Color").put(3, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Numero Par
		btnPar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPar.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Par").put(1, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Numero Impar
		btnImpar.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				btnImpar.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Par").put(2, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Rango del 1 al 18
		btn1a18.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1a18.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Rango").put(1, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Rango del 19 al 36
		btn19a36.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn19a36.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Rango").put(2, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Primera docena
		btn1Docena.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1Docena.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Docena").put(1, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Segunda docena
		btn2Docena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn2Docena.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Docena").put(2, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Tercera docena
		btn3Docena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn3Docena.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Docena").put(3, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Primera fila
		btn1Fila.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1Fila.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Fila").put(1, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Segunda fila
		btn2Fila.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn2Fila.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Fila").put(2, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		//Tercera fila
		btn3Fila.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn3Fila.setEnabled(false);
				//Apuesta unica
				mapaApuestas.get("Fila").put(3, dineroApostado);
				//Sumar dinero al total apostado
				dineroApostadoTotal+=dineroApostado;
				actualizarSaldos();
			}
		});
		
		avisoSaldo(dineroTotal);
		//Botones de las fichas
		//Ficha de 1
		btnapuesta1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dineroApostado = 1;
				
			}
		});
		//Ficha de 10
		btnapuesta10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dineroApostado = 10;

			}
		});
		//Ficha de 20
		btnapuesta20.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				dineroApostado = 20;
				
			}
		});
		//Ficha de 50
		btnapuesta50.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dineroApostado = 50;
				
			}
		});
		//Ficha de 100
		btnapuesta100.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dineroApostado = 100;
				
			}
		});
		//Ficha de 1k
		btnapuesta1k.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dineroApostado = 1000;
				
			}
		});
		//Meter dinero dentro de la ruleta
		VentanaPanelMenu.bApostar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(VentanaPanelMenu.apuesta != 0) {
					//Metemos el dinero total como el saldo que se introuce en la apuesta
					dineroTotal += VentanaPanelMenu.apuesta;
					dineroTotalInicial += VentanaPanelMenu.apuesta;
					VentanaPanelMenu.balance-=dineroTotal;
					VentanaPanelMenu.lBalance.setText("Balance: "+ VentanaPanelMenu.balance);
					saldo.setText("---- " + dineroTotal + " ----");
					logger.info("Añadido a la ventana ruleta "+ dineroTotal+ " del Balance");
				}else {
					JOptionPane.showMessageDialog(null, "Haga su apuesta");
				}
				
				
			}
		});
		//Sacar el dinero para meterlo dentro del balance
		btnSacarDinero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Boton sacar dinero pulsado");
				if(dineroTotal==0) {
					JOptionPane.showMessageDialog(null, "No se puede sacar un resultado inferior o igual a 0");
					//btnSacarDinero.setEnabled(false);
				}
				VentanaPanelMenu.balance+=dineroTotal;
				VentanaPanelMenu.lBalance.setText("Balance: "+ VentanaPanelMenu.balance);
				//Añadido al grafico del balance
				u.addListaBalance(new Point(marca,(int)VentanaPanelMenu.balance/1000));
				//lstBalance.add(new Point(marca,(int)VentanaPanelMenu.balance/1000));
				marca+=1;
				dineroTotal = 0;
				dineroTotalInicial = 0;
				saldo.setText("---- " + dineroTotal + " ----");
			}
		});
		//Borrar apuesta
		btnBorrarApuesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Boton borrar apuesta pulsado");
				//Se vacian todas las listas
				dineroTotal=dineroTotalInicial;
				//reinicio(colorApuesta,docenaApuesta,filaApuesta,rangoApuesta,parApuesta);
				//Apuesta unica
				//Se vacia el mapa
				reinicio2(mapaApuestas);
			}
		});
		//Jugar
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//juego(colorApuesta,docenaApuesta,filaApuesta,rangoApuesta,parApuesta,dineroApostadoTotal,dineroTotal);
				logger.info("Boton jugar pulsado");
				System.out.println(mapaApuestas);
				juego2(mapaApuestas, dineroTotal);
				System.out.println(mapaApuestas);
				
			}
		});
	}
	
	class MyCellRender extends JLabel implements ListCellRenderer<Integer>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<? extends Integer> list, Integer value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			if(value == 0) {
				setBackground(Color.green);
				setForeground(Color.white);
			}else if(esRojo(value)) {
				setBackground(Color.red);
				setForeground(Color.white);
			}else {
				setBackground(Color.BLACK);
				setForeground(Color.white);
			}
			
			return this;
		}
		
	}
	
	//Funcion para saber si el valor que se muestra es rojo
	public static boolean esRojo(Integer i) {
		for(Integer num : rojo) {
			if(i.equals(num)) {
				return true;
			}
		}
		return false;
	}

	public void activarBotones() {
		btnVerde.setEnabled(true);
		btnRojo.setEnabled(true);
		btnNegro.setEnabled(true);
		btnVerde.setEnabled(true);
			btnVerde.setBackground(Color.green);
			btnVerde.setForeground(Color.white);
		btnRojo.setEnabled(true);
			btnRojo.setBackground(Color.red);
			btnRojo.setForeground(Color.white);
		btnNegro.setEnabled(true);
			btnNegro.setBackground(Color.black);
			btnNegro.setForeground(Color.white);
		btn1Docena.setEnabled(true);
		btn2Docena.setEnabled(true);
		btn3Docena.setEnabled(true);
		btn1a18.setEnabled(true);
		btn19a36.setEnabled(true);
		btnPar.setEnabled(true);
		btnImpar.setEnabled(true);
		btn1Fila.setEnabled(true);
		btn2Fila.setEnabled(true);
		btn3Fila.setEnabled(true);
		
	}
	public void actualizarSaldos() {
		dineroTotal-=dineroApostado;
		saldo.setText("---- " + dineroTotal + " ----");
		saldoApostado.setText("---- " + dineroApostadoTotal + " ----");
		avisoSaldo(dineroTotal);
	}
	//Obtener color con String
	public static String obtenerColor(int resultado) {
		if(resultado==0) {
			return "verde";
		}else if(esRojo(resultado)) {
			return "rojo";
		}else {
			return "negro";
		}
	}
	//Obtener color con Integer
	public static int obtenerColor2(int resultado) {
		if(resultado==0) {
			return 1;
		}else if(esRojo(resultado)) {
			return 2;
		}else {
			return 3;
		}
	}
	public static int obtenerDocena(int resultado) {
		if(resultado >= 1 && resultado <= 12) {
			return 1;
		}else if(resultado >= 13 && resultado <= 24) {
			return 2;
		}else{
			return 3;
		}
	}
	public static int obtenerFila(int resultado) {
		if(resultado % 3 == 1) {
			return 1;
		}else if (resultado % 3 ==2) {
			return 2;
		}else {
			return 3;
		}
	}
	public static int obtenerRango(int resultado) {
		if(resultado >= 1 && resultado <= 18) {
			return 1;
		}else if(resultado >= 19 && resultado <= 36) {
			return 2;
		}else{
			return 0;
		}
	}
	public static int obtenerPar(int resultado) {
		if(resultado==0) {
			return 0;
		}else if(resultado%2 ==0) {
			return 1;
		}else{
			return 2;
		}
	}
	
	public void reinicio(List<String> coloresApostadas,List<Integer> docenasApostadas,List<Integer> filasApostadas,List<Integer> rangoApostadas,List<Integer> parApostadas) {
		//Activa los botones para otra partida
		activarBotones();
		//Quitar los valores anteriores de la lista
		coloresApostadas.clear();
		docenasApostadas.clear();
		filasApostadas.clear();
		rangoApostadas.clear();
		parApostadas.clear();
		//Dinero apostado
		dineroApostado=0;
		dineroApostadoTotal=0;
		actualizarSaldos();
		
	}
	public void result(double gano, int r) {
		ApuestaRuleta t = new ApuestaRuleta(r,obtenerColor(r),obtenerDocena(r),obtenerFila(r),obtenerRango(r),obtenerPar(r));
		JOptionPane.showMessageDialog(null, t.toString());
	}

	public static boolean avisoSaldo(double dinero) {
		if(dinero < 0) {
			JOptionPane.showMessageDialog(null,"Saldo insuficiente");
			return true;
		}
		return false;
	}
	//Apuesta unica
	public double verificar(Map<String, Map<Integer, Double>> mapa,int resultado) {
		//Premios por cada posible resultado
		double colorValor = 2.0;
		double docenaValor = 3.0;
		double filaValor = 3.0;
		double rangoValor = 2.0;
		double parValor = 2.0;
		//Variables unicas
		double premioColor=0.0;
		double premioTotalColor=0.0;
		double premioDocena=0.0;
		double premioTotalDocena=0.0;
		double premioFila=0.0;
		double premioTotalFila=0.0;
		double premioRango=0.0;
		double premioTotalRango=0.0;
		double premioPar=0.0;
		double premioTotalPar=0.0;
		
		
		//Separamos en apartados para saber si han sido premiados
		//Verificamos cada docena
		for(Integer color:mapa.get("Color").keySet()) {
			 if(color.equals(obtenerColor2(resultado))) {
				 premioColor = mapa.get("Color").get(color) * colorValor;
				 premioTotalColor += premioColor;
			 }
		}
		//Verificamos el color
		/*
		for(String color : coloresApostadas) {
			if(color.equals(obtenerColor(resultado))) {
				premioTotal*=colorValor;
			}
		}*/
		//Verificamos cada docena
		for(Integer docena:mapa.get("Docena").keySet()) {
			 if(docena.equals(obtenerDocena(resultado))) {
				 premioDocena = mapa.get("Docena").get(docena) * docenaValor;
				 premioTotalDocena += premioDocena;
			 }
		}
		/*
		//Verificamos la docena
		for(Integer docena : docenasApostadas) {
			if(docena.equals(obtenerDocena(resultado))) {
				premioTotal*=docenaValor;
			}
		}
		*/
		//Verificamos cada fila
		for(Integer fila :mapa.get("Fila").keySet()) {
			 if(fila.equals(obtenerFila(resultado))) {
				 premioFila = mapa.get("Fila").get(fila) * filaValor;
				 premioTotalFila += premioFila;
			 }
		}
		/*
		//Verificamos la fila
		 for(Integer fila : filasApostadas) {
			if(fila.equals(obtenerFila(resultado))) {
				premioTotal*=filaValor;
			}
		}
		*/
		//Verificamos cada rango
		for(Integer rango :mapa.get("Rango").keySet()) {
			 if(rango.equals(obtenerRango(resultado))) {
				 premioRango = mapa.get("Rango").get(rango) * rangoValor;
				 premioTotalRango += premioRango;
			 }
		}
		/*
		//Verificamos el rango
		 for(Integer rango : rangoApostadas) {
			if(rango.equals(obtenerRango(resultado))) {
				premioTotal*=rangoValor;
			}
		}
		*/
		//Verificamos cada rango
		for(Integer par :mapa.get("Par").keySet()) {
			 if(par.equals(obtenerPar(resultado))) {
				 premioPar = mapa.get("Par").get(par) * parValor;
				 premioTotalPar += premioPar;
			 }
		}
		/*
		//Verificamos si es par o impar
	  	for(Integer par : rangoApostadas) {
				if(par.equals(obtenerPar(resultado))) {
					premioTotal*=parValor;
				}
			}
		*/
		double premio = premioTotalColor+premioTotalDocena+premioTotalFila+premioTotalPar+premioTotalRango;
		return premio;
	}
	
	public void reinicio2(Map<String, Map<Integer, Double>> mapa) {
		//Activa los botones para otra partida
		activarBotones();
		//Quitar los valores anteriores del mapa
		for (Map<Integer, Double> mapas :mapa.values()) {
			mapas.clear();
		}
		//Dinero apostado
		dineroApostado=0;
		dineroApostadoTotal=0;
		actualizarSaldos();

		
	}
	
	private void juego2( Map<String, Map<Integer, Double>> mapa , double dineroSaldo) {
		if(avisoSaldo(dineroSaldo)) {
			btnBorrarApuesta.doClick();
		}else {
		//Saca un numero random del 0 al 36
		int num = random.nextInt(36);
		//Verificar si ha tocado para las casillas seleccionadas
		double ganancia = verificar(mapa, num);
		//Pone el numero en el historial-lista
		dlmHistorial.addElement(num);
		//Mostrar si se ha ganado o no
		result(ganancia,num);
		//Lista Balance
		mapaTiradas.putIfAbsent(tirada, new HashMap<>());
		mapaTiradas.get(tirada).put(num, ganancia - dineroApostadoTotal);
		//Usuario
		u.addMapaRuleta(tirada, num, ganancia);
		DBManager.addTiradaRuleta(tirada, num, ganancia, u);
		System.out.println(mapaTiradas);
		saldo.setText("---- "+ dineroTotal +" ----");
		dineroTotalInicial=dineroTotal + ganancia;
		logger.info("Todas las acciones del juego terminadas");
		tirada++;
		//Actualizar saldo
		dineroTotal +=ganancia;
		//Reiniciar
		reinicio2(mapa);
		logger.info("Vaciado el mapa de apuestas");
		}
	}
}