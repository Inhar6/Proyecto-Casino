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

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Usuario;

public class VentanaAdminUsuarios extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] listaJuegos = {"Ruleta", "Crash", "BlackJack", "Coin Flip"};
	//Elementos
	private JLabel buscador;
	private JTextField txtBuscador;
	private JLabel nombre;
	private JButton btnBaja;
	//JList
	private DefaultListModel<Usuario> dlmUsuarios;
	private JList<Usuario> lstUsuarios;
	private JScrollPane scrollLst;
	//JComboBox
	private JComboBox<String> jcbJuegos;
	//JTable-->Juegos
	private DefaultTableModel dtmJuegos;
	private JTable tJuegos;
	private JScrollPane scrollJuegos;
	//JTable-->Balance
	private DefaultTableModel dtmBalance;
	private JTable tBalance;
	private JScrollPane scrollBalance;
	//Usuario
	private Usuario user = new Usuario();
	private Usuario Usuario1 = new Usuario("Usuario1", "Apellido1", "11111111A", "user1","", 12345, 1000.0);
	private Usuario Usuario2 = new Usuario("Usuario2", "Apellido2", "22222222B", "user2","", 67890, 1500.0);
	public static List<Usuario> listaUsuarios = new ArrayList<>();
	
	public VentanaAdminUsuarios() {
		setTitle("Datos de los Usuarios");
		setSize(800,700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Datos de prueba
		////
		Usuario1.addMapaRuleta(1, 23, 5000);
		Usuario1.addMapaRuleta(2, 20, 50000);
		Usuario1.addMapaRuleta(3, 12, 85000);
		Usuario1.addMapaRuleta(4, 5, 75000);
		////
		Usuario2.addMapaRuleta(1, 23, 4356);
		Usuario2.addMapaRuleta(2, 3, 5600);
		Usuario2.addMapaRuleta(3, 36, 256);
		Usuario2.addMapaRuleta(4, 0, 1000);
		////
		
		
		buscador = new JLabel("Filtrar por nombre: ");
		txtBuscador= new JTextField(15);
		nombre = new JLabel("Nombre del usuario seleccionado");
		btnBaja = new JButton("Dar de baja");
		//Lista Usuarios
		dlmUsuarios = new DefaultListModel<>();
		lstUsuarios = new JList<Usuario>(dlmUsuarios);
		//Aplicamos el render
		lstUsuarios.setCellRenderer(new MyListCellRender());
		scrollLst = new JScrollPane(lstUsuarios);
		//ComboBox
		jcbJuegos= new JComboBox<String>(listaJuegos);
		//Tabla Juegos
		dtmJuegos = new DefaultTableModel();
		tJuegos = new JTable(dtmJuegos);
		tJuegos.setDefaultRenderer(Object.class, new MyTableCellRender());
		scrollJuegos = new JScrollPane(tJuegos);
		
		//Tabla Balance
		dtmBalance = new DefaultTableModel();
		dtmBalance.addColumn("Saldo");
		tBalance = new JTable(dtmBalance);
		scrollBalance = new JScrollPane(tBalance);
		
		//Rellena la lista con usuarios de prueba
		listaUsuarios = rellenarListaEjemplo();
		dlmUsuarios.addAll(listaUsuarios);
		
		JPanel pBuscador = new JPanel(new FlowLayout());
		pBuscador.add(buscador);
		pBuscador.add(txtBuscador);
		JPanel pNombre = new JPanel(new FlowLayout());
		pNombre.add(nombre);
		pNombre.add(btnBaja);
		JPanel pUsuarios = new JPanel();
		pUsuarios.setLayout(new BorderLayout());
		pUsuarios.add(pBuscador, BorderLayout.NORTH);
		pUsuarios.add(scrollLst, BorderLayout.CENTER);
		pUsuarios.add(new JPanel(), BorderLayout.WEST);
		pUsuarios.add(new JPanel(), BorderLayout.EAST);
		pUsuarios.add(pNombre, BorderLayout.SOUTH);
		Border lineaJuegos = BorderFactory.createLineBorder(Color.RED);
		Border tituloJuegos = BorderFactory.createTitledBorder(lineaJuegos,"Historial");
		JPanel pJuegos = new JPanel(new BorderLayout());
		pJuegos.setBorder(tituloJuegos);
		pJuegos.add(jcbJuegos, BorderLayout.NORTH);
		pJuegos.add(scrollJuegos,BorderLayout.CENTER);
		Border lineaBalance = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloBalance = BorderFactory.createTitledBorder(lineaBalance,"Balance");
		JPanel pBalance = new JPanel(new BorderLayout());
		pBalance.setBorder(tituloBalance);
		pBalance.add(scrollBalance, BorderLayout.CENTER);
		JPanel pHistoriales = new JPanel(new GridLayout(1,2));
		pHistoriales.add(pJuegos);
		pHistoriales.add(pBalance);
		
		setLayout(new GridLayout(2,1));
		add(pUsuarios);
		add(pHistoriales);
		/*
		txtBuscador.getDocument().addDocumentListener(new DocumentListener() {	
			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarLista();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarLista();			
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				actualizarLista();
			}
		});
		*/
		
		jcbJuegos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String juegos = (String)jcbJuegos.getSelectedItem();
				if(juegos.equals("Ruleta")) {
					limpiarTabla();
					pintadoRuleta(user.getMapaRuleta());
				}else if(juegos.equals("Crash")) {
					limpiarTabla();
					pintadoCrash();				
				}else if(juegos.equals("Coin Flip")) {
					limpiarTabla();
					pintadoCoinFlip();
				}else {
					limpiarTabla();
					pintadoBlackJack();
				}
				
			}
		});
		btnBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user != null) {
					listaUsuarios.remove(user);
					dlmUsuarios.removeAllElements();
					dlmUsuarios.addAll(listaUsuarios);
					lstUsuarios.repaint();
					user = null; 
				}
			}
		});
		
		setVisible(true);
	}

	
	public List<Usuario> rellenarListaEjemplo() {
		List<Usuario> listaDeUsuarios = new ArrayList<>();
		listaDeUsuarios.add(Usuario1);
        listaDeUsuarios.add(Usuario2);
        listaDeUsuarios.add(new Usuario("Usuario3", "Apellido3", "33333333C", "user3","", 54321, 75000.0));
        listaDeUsuarios.add(new Usuario("Usuario4", "Apellido4", "44444444D", "user4", "",98765, 20000.0));
        listaDeUsuarios.add(new Usuario("Usuario5", "Apellido5", "55555555E", "user5","", 24680, 300.0));
        listaDeUsuarios.add(new Usuario("Usuario6", "Apellido6", "66666666F", "user6", "",11223, 450000.0));
        listaDeUsuarios.add(new Usuario("Usuario7", "Apellido7", "77777777G", "user7", "",33221, 55000.0));
        listaDeUsuarios.add(new Usuario("Usuario8", "Apellido8", "88888888H", "user8", "",76543, 1200.0));
        listaDeUsuarios.add(new Usuario("Usuario9", "Apellido9", "99999999I", "user9", "",19876, 800.0));
        listaDeUsuarios.add(new Usuario("Usuario10", "Apellido10", "10101010J", "user10","", 23456, 2200.0));
        listaDeUsuarios.add(new Usuario("Usuario11", "Apellido11", "11111111K", "user11", "",90876, 170000.0));
        listaDeUsuarios.add(new Usuario("Usuario12", "Apellido12", "12121212L", "user12","", 65432, 1600.0));
        listaDeUsuarios.add(new Usuario("Usuario13", "Apellido13", "13131313M", "user13","", 56789, 1800.0));
        listaDeUsuarios.add(new Usuario("Usuario14", "Apellido14", "14141414N", "user14", "",11234, 4000.0));
        listaDeUsuarios.add(new Usuario("User", "Apellido", "15151515O", "user15","", 98765, 300000.0));
		return listaDeUsuarios;
	}
	
	public void limpiarTabla() {
		dtmJuegos.setRowCount(0);
		dtmJuegos.setColumnCount(0);
	}
	
	public void pintadoRuleta(Map<Integer, Map<Integer, Double>> mapa) {
		dtmJuegos.addColumn("Tirada");
		dtmJuegos.addColumn("Resultado");
		dtmJuegos.addColumn("Ganancia");
		for(Entry<Integer, Map<Integer, Double>> entry :mapa.entrySet()) {
			int tirada =entry.getKey();
			for(Integer i : entry.getValue().keySet()) {
				int resultado = i;
				double ganancia = entry.getValue().get(i);
				dtmJuegos.addRow(new Object[] {tirada,resultado,ganancia});
			}
			
		}
	}
	public void pintadoCoinFlip() {
			
	}
	public void pintadoCrash() {
		
	}
	public void pintadoBlackJack() {
		
	}
	
	/*
	public void actualizarLista() {
		String filtro = txtBuscador.getText().toLowerCase();
		List<Usuario> lstFiltro = new ArrayList<>();
		for(int i = 0; i<dlmUsuarios.size();i++ ) {
			Usuario u = dlmUsuarios.getElementAt(i);
			if(u.getNombre().toLowerCase().startsWith(filtro)) {
				lstFiltro.add(u);
			}
		}
		lstUsuarios.setModel(new DefaultListModel<>());
		for(Usuario user:lstFiltro) {
			((DefaultListModel<Usuario>)lstUsuarios.getModel()).addElement(user);
		}
	}
	*/
	//Render para la lista de arriba
	class MyListCellRender extends JLabel implements ListCellRenderer<Usuario>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			//Mostrar la gente que tiene un saldo superior a 50.000
			if(value.getSaldo()>= 50000) {
				setBackground(Color.YELLOW);
			}else {
				setBackground(Color.WHITE);
			}
			if(isSelected) {
				setBackground(Color.CYAN);
				nombre.setText(value.toString());
				user = (Usuario)value;
				
			}
			/*
			String cellText = value.getNombre();
			if (!txtBuscador.getText().isBlank() && cellText.startsWith(txtBuscador.getText())) {
				setText(String.format("<html><b>%s</b>%s</html>", 
					txtBuscador.getText(),
					cellText.substring(txtBuscador.getText().length())
				));
			} else {
				setText(cellText);
			}
			*/
			return this;
			}
		}
	class MyTableCellRender extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyTableCellRender() {
			setOpaque(true);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			setHorizontalAlignment(CENTER);
			
			if(row % 2 ==0) {
				setBackground(Color.LIGHT_GRAY);
			}else{
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
}
	
