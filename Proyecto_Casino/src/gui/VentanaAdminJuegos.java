package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import db.DBManager;
import domain.Point;
import domain.Usuario;
import io.Fichero;
import main.Main;

public class VentanaAdminJuegos extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Logger
	private static final Logger logger = Logger.getLogger("Ventana Administrador Juegos");
	//Color del panel
	private Color colorPanel = new Color(71, 113, 72);
	//Elementos
	private List<Usuario> lstUsuarios = new ArrayList<>(); 
	private JLabel sup;
	private JButton btn;
	private JButton btnInformes;
	//Estadisticas
	//Estadisticas-->Ruleta
	private List<Point> puntosRuleta;
	private JLabel usuario;
	private JLabel txtUsuario;
	private JLabel numero;
	private JLabel txtNumero;
	private JLabel ganancia;
	private JLabel txtGanancia;
	private JLabel uso;
	private JLabel txtUso;
	private JLabel totalGanancias;
	private JLabel txtTotalGanancias;
	//Estadisticas-->Crash
	private List<Point> puntosCrash;
	private JLabel usuarioCrash;
	private JLabel txtUsuarioCrash;
	private JLabel gananciaCrash;
	private JLabel txtGananciaCrash;
	private JLabel usoCrash;
	private JLabel txtUsoCrash;
	private JLabel totalGananciasCrash;
	private JLabel txtTotalGananciasCrash;
	//Estadisticas-->CoinFlip
	
	//Estadisticas-->BlackJack
	
	public VentanaAdminJuegos() {
		setTitle("Estadisticas de los juegos");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(900,900);
		setLocationRelativeTo(null);
		
		sup = new JLabel("Estadisticas sobre los juegos");
		btn = new JButton("Leer Informe");
		btnInformes = new JButton("Crear Informe");
		
		//LLenar la lista
		lstUsuarios = Main.DBlstUsuarios;
		
		//Ruleta
		Border lineaRuleta = BorderFactory.createLineBorder(Color.RED);
		Border tituloRuleta = BorderFactory.createTitledBorder(lineaRuleta,"Ruleta");
		Border lineaBalanceRuleta = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloBalanceRuleta = BorderFactory.createTitledBorder(lineaBalanceRuleta,"Balance");
		//puntosRuleta = obtenerUsuarioConMayorNumeroDeTiradas(lstUsuarios).getLstBalance();
		puntosRuleta = DBManager.balanceRuleta();
		PanelGrafico grfRuleta = new PanelGrafico(puntosRuleta);
		usuario = new JLabel("Usuario con mayor ganacia: ");
		Usuario a = obtenerUsuarioConMayorGanancia(lstUsuarios);
		txtUsuario= new JLabel(""+ a.getNombreUsuario());
		txtUsuario.setForeground(Color.GRAY);
		numero= new JLabel("Numero mas repetido: ");
		txtNumero= new JLabel("23");
		txtNumero.setForeground(Color.GRAY);
		ganancia= new JLabel("Mayor ganancia en una tirada: ");
		Usuario c = obtenerUsuarioConMayorGanancia(lstUsuarios);
		txtGanancia= new JLabel(""+ c.getNombreUsuario());
		txtGanancia.setForeground(Color.GRAY);
		uso= new JLabel("Usuario con mayor numero de tiradas: ");
		Usuario d = obtenerUsuarioConMayorNumeroDeTiradas(lstUsuarios);
		txtUso= new JLabel(""+ d.getNombreUsuario());
		txtUso.setForeground(Color.GRAY);
		totalGanancias= new JLabel("Total ganancias/perdidas: ");
		txtTotalGanancias= new JLabel(""+obtenerTotalGanancias(lstUsuarios));
		txtTotalGanancias.setForeground(Color.GRAY);
		
		//Crash
		Border lineaCrash = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloCrash = BorderFactory.createTitledBorder(lineaCrash,"Crash");
		puntosCrash = puntosPrueba();
		PanelGrafico grfCrash = new PanelGrafico(puntosCrash);
		usuarioCrash = new JLabel("Usuario con mayor ganacia: ");
		Usuario crash0 = obtenerUsuarioConMayorGananciaCrash(lstUsuarios);
		txtUsuarioCrash= new JLabel(""+ crash0.getNombreUsuario());
		txtUsuarioCrash.setForeground(Color.GRAY);
		gananciaCrash= new JLabel("Mayor ganancia en una tirada: ");
		Usuario crash1 = obtenerUsuarioConMayorGananciaCrash(lstUsuarios);
		txtGananciaCrash= new JLabel(""+ crash1.getNombreUsuario());
		txtGananciaCrash.setForeground(Color.GRAY);
		usoCrash= new JLabel("Usuario con mayor numero de tiradas: ");
		Usuario crash2 = obtenerUsuarioConMayorNumeroDeTiradasCrash(lstUsuarios);
		txtUsoCrash= new JLabel(""+ crash2.getNombreUsuario());
		txtUsoCrash.setForeground(Color.GRAY);
		totalGananciasCrash= new JLabel("Total ganancias/perdidas: ");
		txtTotalGananciasCrash= new JLabel(""+obtenerTotalGananciasCrash(lstUsuarios));
		txtTotalGananciasCrash.setForeground(Color.GRAY);
		
		//CoinFlip
		Border lineaCoinFlip = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloCoinFlip = BorderFactory.createTitledBorder(lineaCoinFlip,"CoinFlip");
		
		//BlackJack
		Border lineaBlackJack = BorderFactory.createLineBorder(Color.RED);
		Border tituloBlackJack = BorderFactory.createTitledBorder(lineaBlackJack,"BlackJack");
		
		JPanel pSuperior = new JPanel(new FlowLayout());
			pSuperior.add(sup);
			pSuperior.setBackground(colorPanel);
		JPanel pBajo = new JPanel(new FlowLayout());
			pBajo.add(btn);
			pBajo.add(btnInformes);
			pBajo.setBackground(colorPanel);
		JPanel pJuegos = new JPanel(new GridLayout(2,2));
			JPanel pRuleta = new JPanel(new GridLayout(2,1));
				JPanel pUsuarioRuleta = new JPanel(new FlowLayout());
				JPanel pNumeroRuleta = new JPanel(new FlowLayout());
				JPanel pGananciaRuleta = new JPanel(new FlowLayout());
				JPanel pUsoRuleta = new JPanel(new FlowLayout());
				JPanel pTotalGananciasRuleta = new JPanel(new FlowLayout());
			JPanel pCrash = new JPanel(new GridLayout(2,1));
				JPanel pUsuarioCrash = new JPanel(new FlowLayout());
				//
				JPanel pGananciaCrash = new JPanel(new FlowLayout());
				JPanel pUsoCrash = new JPanel(new FlowLayout());
				JPanel pTotalGananciasCrash = new JPanel(new FlowLayout());
			JPanel pCoinFlip = new JPanel();
			JPanel pBlackJack = new JPanel();
		
		pRuleta.setBorder(tituloRuleta);
		pCrash.setBorder(tituloCrash);
		pCoinFlip.setBorder(tituloCoinFlip);
		pBlackJack.setBorder(tituloBlackJack);
			
		//Panel Ruleta
		pUsuarioRuleta.add(usuario);
		pUsuarioRuleta.add(txtUsuario);
		pNumeroRuleta.add(numero);
		pNumeroRuleta.add(txtNumero);
		pGananciaRuleta.add(ganancia);
		pGananciaRuleta.add(txtGanancia);
		pUsoRuleta.add(uso);
		pUsoRuleta.add(txtUso);
		pTotalGananciasRuleta.add(totalGanancias);
		pTotalGananciasRuleta.add(txtTotalGanancias);
		JPanel pEstadisticasRuleta = new JPanel(new GridLayout(3,2));
		pEstadisticasRuleta.add(pUsuarioRuleta);
		pEstadisticasRuleta.add(pNumeroRuleta);
		pEstadisticasRuleta.add(pGananciaRuleta);
		pEstadisticasRuleta.add(pUsoRuleta);
		pEstadisticasRuleta.add(pTotalGananciasRuleta);
		pEstadisticasRuleta.add(new JPanel());
		pRuleta.add(pEstadisticasRuleta);
		//Panel Crash
		pUsuarioCrash.add(usuarioCrash);
		pUsuarioCrash.add(txtUsuarioCrash);
		//	Añadir otro datos de crash
		//	Añadir otro datos de crash
		pGananciaCrash.add(gananciaCrash);
		pGananciaCrash.add(txtGananciaCrash);
		pUsoCrash.add(usoCrash);
		pUsoCrash.add(txtUsoCrash);
		pTotalGananciasCrash.add(totalGananciasCrash);
		pTotalGananciasCrash.add(txtTotalGananciasCrash);
		JPanel pEstadicticasCrash = new JPanel(new GridLayout(3,2));
		pEstadicticasCrash.add(pUsuarioCrash);
		pEstadicticasCrash.add(pGananciaCrash);
		pEstadicticasCrash.add(pUsoCrash);
		pEstadicticasCrash.add(pTotalGananciasCrash);
		pCrash.add(pEstadicticasCrash);
		//Grafico
		grfRuleta.setBorder(tituloBalanceRuleta);
		pRuleta.add(grfRuleta);
		pCrash.add(grfCrash);
		
		pJuegos.add(pRuleta);	
		pJuegos.add(pCrash);	
		pJuegos.add(pCoinFlip);	
		pJuegos.add(pBlackJack);	
		
		setLayout(new BorderLayout());
		add(pSuperior, BorderLayout.NORTH);
		add(pBajo, BorderLayout.SOUTH);
		add(pJuegos, BorderLayout.CENTER);
		
		btnInformes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				crearInforme();
				
			}
		});
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String rutaArchivo = "resources/data/Informe.txt";
				String contenido = Fichero.leerFichero(rutaArchivo);
				//Hacer que el tetxto aparezca en buenas condiciones
				JTextArea area = new JTextArea(contenido);
				area.setEditable(false);
				area.setLineWrap(true);
				area.setWrapStyleWord(true);
				//Meter un scroll para que el texto no tenga problemas de visualizacion
				JScrollPane scroll = new JScrollPane(area);
				scroll.setPreferredSize(new Dimension(400,300));
				JOptionPane.showMessageDialog(null, scroll, "Informe", JOptionPane.INFORMATION_MESSAGE);
				logger.info("Lectura de informe");
			}
		});
		
		setVisible(true);
	}
	
	public List<Point> puntosPrueba(){
		List<Point> lista = new ArrayList<>();
		lista.add(new Point(1, 2));
		lista.add(new Point(2, -3));
		lista.add(new Point(5, 6));
		return lista;
	}
	public void crearInforme(){
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/data/Informe.txt"))) {
	            // Escribe el contenido en el archivo
	            //Inicio del informe
			 	writer.write("El presente informe tiene como objetivo proporcionar un análisis estadístico detallado sobre el rendimiento y las tendencias observadas en el casino NoEscasino "
	            		+ ". Este análisis abordará varios aspectos clave, incluidos ingresos, participación de jugadores, juegos más populares y otros indicadores relevantes que ofrecerán una"
	            		+ " visión comprehensiva del estado actual del casino.\n");
	            writer.write("RULETA: \n"
	            		+ "\t Usuario con mayor ganancia: "+txtUsuario.getText()+"\n"
	            		+ "\t Numero mas repetido: "+txtNumero.getText()+"\n"
	            		+ "\t Mayor ganacia en una tirada: "+ txtGanancia.getText()+"\n"
	            		+ "\t Usuario con mayor numero de tiradas: "+txtUso.getText()+"\n"
	            		+ "\t Total de ganancias/perdidas: "+txtTotalGanancias.getText()+"\n");
	            writer.write("CRASH: \n"
	            		+ "\t Usuario con mayor ganancia: "+txtUsuario.getText()+"\n"
//	            		+ "\t Numero mas repetido: "+txtNumero.getText()+"\n"
	            		+ "\t Mayor ganacia en una tirada: "+ txtGanancia.getText()+"\n"
	            		+ "\t Usuario con mayor numero de tiradas: "+txtUso.getText()+"\n"
	            		+ "\t Total de ganancias/perdidas: "+txtTotalGanancias.getText()+"\n");
	            /*
	             * AÑADIR MAS INFORMACION DE OTROS JUEGOS
	             */
	            //Final del informe
	            writer.write("En conclusión, el presente informe destaca la dinámica y el rendimiento sólido observado en NoEscasino durante el período analizado. Los datos detallados revelan "
	            		+ "tendencias significativas en la participación de jugadores, los ingresos generados por diferentes juegos y la eficacia de estrategias de retención de clientes. Estos hallazgos"
	            		+ " proporcionan una base sólida para la toma de decisiones futuras y la implementación de mejoras específicas para maximizar la satisfacción del cliente y la rentabilidad. "
	            		+ "NoEscasino continúa siendo un actor destacado en la industria del entretenimiento y los juegos de azar, y este informe sienta las bases para abordar desafíos y aprovechar oportunidades "
	            		+ "emergentes en el panorama competitivo.");
	        	logger.info("Creacion de Informe");
	        } catch (IOException e) {
	        	logger.warning("Imposible crear informe");
	        }
	}
	
	/*
	 * RULETA
	 */
	//Funcion divide la lista de usuarios en un mapa <Usuario:Totalganancaias>
	public Map<Usuario, Double> obtenerMapaGananciasPorUsuarios(List<Usuario> usuarios){
		Map<Usuario, Double> mapa = new HashMap<>();
		for(Usuario user: usuarios) {
			mapa.putIfAbsent(user, 0.0);
			Map<Integer, Map<Integer, Double>> mapaRuleta = user.getMapaRuleta();
			for(Map<Integer, Double> apuesta : mapaRuleta.values()) {
				for(Double n : apuesta.values()) {
					mapa.put(user, mapa.get(user)+n);
				}
			}
		}
		return mapa;
	}
	//Funcion usuario con mayor ganancia
	public Usuario obtenerUsuarioConMayorGanancia(List<Usuario> usuarios) {
		Map<Usuario, Double> mapaUsuarios = obtenerMapaGananciasPorUsuarios(usuarios);
		double ganancia = 0;
		Usuario u = new Usuario();
		for(Entry<Usuario, Double> entry : mapaUsuarios.entrySet()) {
			if(entry.getValue()>ganancia) {
				ganancia = entry.getValue();
				u = entry.getKey();
			}
		}
		return u;
	}
	//Funcion Usuario con mayor numero de tiradas
	public Usuario obtenerUsuarioConMayorNumeroDeTiradas(List<Usuario> usuarios) {
		Usuario usuario = new Usuario();
		int tamaño = 0;
		for(Usuario user : usuarios) {
			Map<Integer, Map<Integer, Double>> mapa = user.getMapaRuleta();
			int tiradas = mapa.size();
			if(tiradas > tamaño) {
				usuario = user;
				tamaño = tiradas;
			}
		}
		return usuario;
	}
	//Total ganancias/perdidas
	public Double obtenerTotalGanancias(List<Usuario> usuarios) {
		double total= 0;
		Map<Usuario, Double> mapaUsuarios = obtenerMapaGananciasPorUsuarios(usuarios);
		for(Double t : mapaUsuarios.values()) {
			total += t;
		}
		return total;
		
	}
	/*
	 * CRASH
	 */
	//Funcion divide la lista de usuarios en un mapa <Usuario:Totalganancaias>
	public Map<Usuario, Double> obtenerMapaGananciasPorUsuariosCrash(List<Usuario> usuarios){
		Map<Usuario, Double> mapa = new HashMap<>();
		for(Usuario user: usuarios) {
			mapa.putIfAbsent(user, 0.0);
			Map<Integer, Map<String, Map<Double, Double>>> mapaCrash = user.getMapaCrash();
			for(Map<String, Map<Double, Double>> apuesta : mapaCrash.values()) {
				for(Map<Double, Double> apuesta1 : apuesta.values()) {
					for(Double c: apuesta1.values()) {
						mapa.put(user, mapa.get(user) + c) ;
					}
				}
			}
		}
		return mapa;
	}
	//Funcion usuario con mayor ganancia
	public Usuario obtenerUsuarioConMayorGananciaCrash(List<Usuario> usuarios) {
		Map<Usuario, Double> mapaUsuarios = obtenerMapaGananciasPorUsuariosCrash(usuarios);
		double ganancia = 0;
		Usuario u = new Usuario();
		for(Entry<Usuario, Double> entry : mapaUsuarios.entrySet()) {
			if(entry.getValue()>ganancia) {
				ganancia = entry.getValue();
				u = entry.getKey();
			}
		}
		return u;
	}
	//Funcion Usuario con mayor numero de tiradas
	public Usuario obtenerUsuarioConMayorNumeroDeTiradasCrash(List<Usuario> usuarios) {
		Usuario usuario = new Usuario();
		int tamaño = 0;
		for(Usuario user : usuarios) {
			Map<Integer, Map<String, Map<Double, Double>>> mapa = user.getMapaCrash();
			int tiradas = mapa.size();
			if(tiradas > tamaño) {
				usuario = user;
				tamaño = tiradas;
			}
		}
		return usuario;
	}
	//Total ganancias/perdidas
	public Double obtenerTotalGananciasCrash(List<Usuario> usuarios) {
		double total= 0;
		Map<Usuario, Double> mapaUsuarios = obtenerMapaGananciasPorUsuariosCrash(usuarios);
		for(Double t : mapaUsuarios.values()) {
			total += t;
		}
		return total;
		
	}
	
	//BLACKJACK
	public Map<Usuario, Double> obtenerMapaGananciasPorUsuariosBlackJack(List<Usuario> usuarios){
		Map<Usuario, Double> mapa = new HashMap<>();
		for(Usuario user: usuarios) {
			mapa.putIfAbsent(user, 0.0);
			Map<Integer, Map<String, Map<Integer, Double>>> mapaCrash = user.getMapaBlackJack();
			for(Map<String, Map<Integer, Double>> apuesta : mapaCrash.values()) {
				for(Map<Integer, Double> apuesta1 : apuesta.values()) {
					for(Double c: apuesta1.values()) {
						mapa.put(user, mapa.get(user) + c) ;
					}
				}
			}
		}
		return mapa;
	}
	
	public Usuario obtenerUsuarioConMayorGananciaBlackJack(List<Usuario> usuarios) {
		Map<Usuario, Double> mapaUsuarios = obtenerMapaGananciasPorUsuariosBlackJack(usuarios);
		double ganancia = 0;
		Usuario u = new Usuario();
		for(Entry<Usuario, Double> entry : mapaUsuarios.entrySet()) {
			if(entry.getValue()>ganancia) {
				ganancia = entry.getValue();
				u = entry.getKey();
			}
		}
		return u;
	}
	public Usuario obtenerUsuarioConMayorNumeroDeTiradasBlackJack(List<Usuario> usuarios) {
		Usuario usuario = new Usuario();
		int tamaño = 0;
		for(Usuario user : usuarios) {
			Map<Integer, Map<String, Map<Integer, Double>>> mapa = user.getMapaBlackJack();
			int tiradas = mapa.size();
			if(tiradas > tamaño) {
				usuario = user;
				tamaño = tiradas;
			}
		}
		return usuario;
	}
	
	public Double obtenerTotalGananciasBlackJack(List<Usuario> usuarios) {
		double total= 0;
		Map<Usuario, Double> mapaUsuarios = obtenerMapaGananciasPorUsuariosBlackJack(usuarios);
		for(Double t : mapaUsuarios.values()) {
			total += t;
		}
		return total;
		
	}
	
	
	 
	
	
	/*
	 * COIN-FLIP
	 */
}
