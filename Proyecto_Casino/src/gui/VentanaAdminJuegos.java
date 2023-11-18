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
import java.util.List;
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

import domain.Point;
import io.Fichero;

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
		
		//Ruleta
		Border lineaRuleta = BorderFactory.createLineBorder(Color.RED);
		Border tituloRuleta = BorderFactory.createTitledBorder(lineaRuleta,"Ruleta");
		puntosRuleta=puntosPrueba();
		PanelGrafico grfRuleta = new PanelGrafico(puntosRuleta);
		usuario = new JLabel("Usuario con mayor ganacia: ");
		txtUsuario= new JLabel("Manolo");
		txtUsuario.setForeground(Color.GRAY);
		numero= new JLabel("Numero mas repetido: ");
		txtNumero= new JLabel("21");
		txtNumero.setForeground(Color.GRAY);
		ganancia= new JLabel("Mayor ganancia en una tirada: ");
		txtGanancia= new JLabel("30.000");
		txtGanancia.setForeground(Color.GRAY);
		uso= new JLabel("Usuario con mayor numero de tiradas: ");
		txtUso= new JLabel("Manolo");
		txtUso.setForeground(Color.GRAY);
		totalGanancias= new JLabel("Total ganancias/perdidas: ");
		txtTotalGanancias= new JLabel("100.000");
		txtTotalGanancias.setForeground(Color.GRAY);
		//Crash
		Border lineaCrash = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloCrash = BorderFactory.createTitledBorder(lineaCrash,"Crash");
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
			JPanel pCrash = new JPanel();
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
		//Grafico
		pRuleta.add(grfRuleta);
		
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
}
