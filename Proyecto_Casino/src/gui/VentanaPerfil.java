package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Point;
import domain.Usuario;

public class VentanaPerfil extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Color del panel
	private Color colorPanel = new Color(71, 113, 72);
	
	private String[] listaJuegos = {"Ruleta", "Crash", "BlackJack", "Coin Flip"};
	//Logger
	private static final Logger logger = Logger.getLogger("Ventana Perfil");
	//Elementos
	//JLabels
	private JLabel nombre;
	private JLabel nUsuario;
	private JLabel fechaNacimiento;
	private JLabel perfil;
	//JTextFields
	private JTextField txtNombre;
	private JTextField txtNUsuario;
	private JTextField txtContraseña;
	//JComboBox
	private JComboBox<String> jcbJuegos;
	//JTable - Historial
	private JTable tabla;
	private DefaultTableModel dtmTabla;
	private JScrollPane scroll;
	//JButton
	private JButton btnEditar;
	private JButton btnGuardar;
	//Usuario
	private Usuario user;
	//Grafico
	private List<Point> GraficoBalance;
	
	
	
	public VentanaPerfil() {
		setIconImage(new ImageIcon("resources/images/iconos/favicon.png").getImage());
		setTitle("Perfil del Usuario");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		//Usuario
		user = new Usuario("Usuario1", "Apellido1", "11111111A", "user1","contraseña1", 12345, 1000.0);
		//JLabel
		nombre = new JLabel("Nombre:               ");
		nUsuario = new JLabel("Usuario:             ");
		fechaNacimiento = new JLabel("Contraseña:         ");
		perfil = new JLabel("Perfil");
		//JTextField
		txtNombre= new JTextField(15);
		txtNombre.setText(user.getNombre());
		txtNUsuario= new JTextField(15);
		txtNUsuario.setText(user.getNombreUsuario());
		txtContraseña= new JTextField(15);
		txtContraseña.setText(user.getContraseña());
		//Borde
		Border lineaDatos = BorderFactory.createLineBorder(colorPanel);
		Border tituloDatos = BorderFactory.createTitledBorder(lineaDatos,"Datos");
		//JComboBox
		jcbJuegos= new JComboBox<String>(listaJuegos);
		//JTable - Historial
		dtmTabla= new DefaultTableModel();
		tabla = new JTable(dtmTabla);
		tabla.setEnabled(false);
		tabla.setDefaultRenderer(Object.class, new MyRender());
		scroll = new JScrollPane(tabla);
		//Bordes
		Border lineaHistorial = BorderFactory.createLineBorder(colorPanel);
		Border tituloHistorial = BorderFactory.createTitledBorder(lineaHistorial,"Historial");
		Border lineaBalance = BorderFactory.createLineBorder(colorPanel);
		Border tituloBalance = BorderFactory.createTitledBorder(lineaBalance,"Balance");
		//JButton
		btnEditar= new JButton("Editar");
		btnGuardar = new JButton("Guardar");
		
		//Paneles
		JPanel principal = new JPanel();
		JPanel perf = new JPanel();
		JPanel central = new JPanel();
		JPanel izquierdo = new JPanel();
			JPanel N = new JPanel();
			JPanel NU = new JPanel();
			JPanel F = new JPanel();
		JPanel derecho = new JPanel();
			JPanel juegos = new JPanel();
				JPanel box = new JPanel();
			JPanel balance = new JPanel ();
		JPanel editar = new JPanel();
		
		N.setLayout(new FlowLayout());
		N.add(nombre);
		N.add(txtNombre);
		NU.setLayout(new FlowLayout());
		NU.add(nUsuario);
		NU.add(txtNUsuario);
		F.setLayout(new FlowLayout());
		F.add(fechaNacimiento);
		F.add(txtContraseña);
		izquierdo.setLayout(new GridLayout(5,1));
		izquierdo.add(new JPanel());
		izquierdo.add(N);
		izquierdo.add(NU);
		izquierdo.add(F);
		izquierdo.add(new JPanel());
		izquierdo.setBorder(tituloDatos);
		juegos.setLayout(new BorderLayout());
			box.setLayout(new FlowLayout());
			box.add(jcbJuegos);
		juegos.add(box, BorderLayout.NORTH);
		juegos.add(scroll, BorderLayout.CENTER);
		juegos.setBorder(tituloHistorial);
		///
		//GRAFICO
		///
		balance.setLayout(new FlowLayout());
		//GRAFICO
		GraficoBalance = puntosPrueba();
		PanelGrafico chartPanel = new PanelGrafico(GraficoBalance);
		chartPanel.setBorder(tituloBalance);
		///
		derecho.setLayout(new GridLayout(2,1));
		derecho.add(juegos);
		//derecho.add(balance);
		derecho.add(chartPanel);
		central.setLayout(new GridLayout(1,2));
		central.add(izquierdo);
		central.add(derecho);
		perf.setLayout(new FlowLayout());
		perf.add(perfil);
		perf.setBackground(colorPanel);
		editar.add(btnEditar);
		editar.add(btnGuardar);
		principal.setLayout(new BorderLayout());
		principal.add(perf, BorderLayout.NORTH);
		principal.add(central,BorderLayout.CENTER);
		principal.add(editar, BorderLayout.SOUTH);
		
		add(principal);
		
		//Que empiezen los JTextField desactivados y el boton de guardar
		txtContraseña.setEditable(false);
		txtNombre.setEditable(false);
		txtNUsuario.setEditable(false);
		btnGuardar.setEnabled(false);
		
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtContraseña.setEditable(true);
				txtNombre.setEditable(true);
				txtNUsuario.setEditable(true);
				btnGuardar.setEnabled(true);
				logger.info("Edicion activada");
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿ Desea guardar los cambios ?");
				if( respuesta == JOptionPane.YES_OPTION) {
					txtContraseña.setEditable(false);
					txtNombre.setEditable(false);
					txtNUsuario.setEditable(false);
					btnGuardar.setEnabled(false);
					logger.info("Cambio de datos a " + txtNombre.getText()+"-"+txtNUsuario.getText()+"-"+txtContraseña.getText());
				}else {
					//No hace nada, deberia de guardar los datos del usuario
					user.setNombre(txtNombre.getText());
					user.setNombreUsuario(txtNUsuario.getText());
					user.setContraseña(txtContraseña.getText());
				}
				
			}
		});
		
		jcbJuegos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String juego = (String)jcbJuegos.getSelectedItem();
				if(juego.equals("Ruleta")) {
					logger.info("Muestra de datos en la tabla Ruleta");
					limpiarTabla();
					pintarDatosRuleta();
				}else if(juego.equals("Coin Flip")) {
					logger.info("Muestra de datos en la tabla Coin Flip");
					limpiarTabla();
					pintarDatosCoinFlip();
				}else if(juego.equals("Crash")) {
					logger.info("Muestra de datos en la tabla Crash");
					limpiarTabla();
					pintarDatosCrash();
				}else {
					logger.info("Muestra de datos en la tabla Black Jack");
					limpiarTabla();
					pintarDatosBlackJack();
				}
			}
		});

     
		setVisible(true);
		
	}
	public void limpiarTabla() {
		dtmTabla.setRowCount(0);
		dtmTabla.setColumnCount(0);
	}
	//Datos de prueba
	public void pintarDatosRuleta() {
		dtmTabla.addColumn("Tirada");
		dtmTabla.addColumn("Resultado");
		dtmTabla.addColumn("Ganancia");
		for(Entry<Integer, Map<Integer, Double>> entry :VentanaRuleta.mapaBalance.entrySet()) {
			int tirada =entry.getKey();
			for(Integer i : entry.getValue().keySet()) {
				int resultado = i;
				double ganancia = entry.getValue().get(i);
				dtmTabla.addRow(new Object[] {tirada,resultado,ganancia});
			}
			
		}
	}
	public void pintarDatosCoinFlip() {
		dtmTabla.addColumn("Tirada");
		dtmTabla.addColumn("Resultado");
		dtmTabla.addColumn("Ganancia");
		dtmTabla.addRow(new Object[] {1,"Cara",+20});
		dtmTabla.addRow(new Object[] {2,"Cara",-30});
		dtmTabla.addRow(new Object[] {3,"Cruz",+150});
	}
	public void pintarDatosCrash() {
		dtmTabla.addColumn("Tirada");
		dtmTabla.addColumn("Resultado");
		dtmTabla.addColumn("Multiplicador");
		dtmTabla.addColumn("Ganancia");
		dtmTabla.addRow(new Object[] {1,"WIN","x3.46",+200});
		dtmTabla.addRow(new Object[] {2,"LOSE","x0.0",-30});
		dtmTabla.addRow(new Object[] {3,"WIN","x1.25",+150});
	}
	public void pintarDatosBlackJack() {

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
			setHorizontalAlignment(CENTER);
			if(row%2 ==0) {
				setBackground(Color.LIGHT_GRAY);
			}else {
				setBackground(Color.white);
			}

			return this;
		}
		
	}
	
	public List<Point> puntosPrueba(){
		List<Point> lista = new ArrayList<>();
		lista.add(new Point(1, 2));
		lista.add(new Point(2, 3));
		lista.add(new Point(3, 4));
		lista.add(new Point(4, 2));
		lista.add(new Point(5, 6));
		return lista;
	}
}
