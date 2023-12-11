package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.DBManager;
import domain.Point;
import domain.Usuario;
import main.Main;

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
	//Grafico Balance
	private List<Point> lstBalance;
	private PanelGrafico grfBalance;
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
		Usuario1.addListaBalance(new Point(5,5));
		Usuario1.addListaBalance(new Point(6,4));
		Usuario1.addListaBalance(new Point(7,3));
		Usuario1.addListaBalance(new Point(1,2));
		////
		Usuario2.addMapaRuleta(1, 23, 4356);
		Usuario2.addMapaRuleta(2, 3, 5600);
		Usuario2.addMapaRuleta(3, 36, 256);
		Usuario2.addMapaRuleta(4, 0, 1000);
		Usuario2.addListaBalance(new Point(3,1));
		Usuario2.addListaBalance(new Point(4,6));
		Usuario2.addListaBalance(new Point(6,4));
		Usuario2.addListaBalance(new Point(7,3));
		Usuario2.addListaBalance(new Point(8,2));
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
		
		//Grafico Balance
		lstBalance=puntosPrueba();
		grfBalance = new PanelGrafico(lstBalance);
		
		//Rellena la lista con usuarios de prueba
		listaUsuarios = Main.DBlstUsuarios;
		Collections.sort(listaUsuarios, new MyComparator());
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
		pBalance.add(grfBalance, BorderLayout.CENTER);
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
		lstUsuarios.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				 if (!e.getValueIsAdjusting()) {
			            user = lstUsuarios.getSelectedValue();
			            if (user != null) {
			                // Actualizar la tabla
			                if ("Ruleta".equals(jcbJuegos.getSelectedItem())) {
			                    /*
			                	limpiarTabla();
			                    pintadoRuleta(user.getMapaRuleta());
			                    */
			                	Tablas.limpiarTabla(dtmJuegos);
			                	Tablas.pintadoRuleta(dtmJuegos, user.getMapaRuleta());
			                } else if ("Crash".equals(jcbJuegos.getSelectedItem())) {
			                    /*
			                	limpiarTabla();
			                    pintadoCrash(user.getMapaCrash());
			                	*/
			                	Tablas.limpiarTabla(dtmJuegos);
			                	Tablas.pintadoCrash(dtmJuegos, user.getMapaCrash());
			                } else if ("Coin Flip".equals(jcbJuegos.getSelectedItem())) {
			                    Tablas.limpiarTabla(dtmJuegos);
			                    Tablas.pintadoCoinFlip(dtmJuegos, user.getMapaCoinFlip());
			                } else {
			                    Tablas.limpiarTabla(dtmJuegos);
			                    Tablas.pintadoBlackJack(dtmJuegos,user.getMapaBlackJack());
			                }

			                // Actualizar el gráfico
			                grfBalance.setDataPoints(user.getLstBalance());
			                grfBalance.repaint();

			                // Actualizar la etiqueta con el nombre del usuario
			                nombre.setText(user.toString());
			            }
				 }
			}
		});
		btnBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user != null) {
					int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres dar de baja al usuario [" + user + "]");
					if(opcion == JOptionPane.YES_OPTION) {
						DBManager.eliminarUsuario(user);
						listaUsuarios.remove(user);
						dlmUsuarios.removeAllElements();
						dlmUsuarios.addAll(listaUsuarios);
						lstUsuarios.repaint();
						user = null;
					}else {
						
					}
					 
				}
			}
		});
		
		setVisible(true);
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
				tJuegos.repaint();
				//Pintado del grafico
				grfBalance.setDataPoints(user.getLstBalance());
			    grfBalance.repaint();
				
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
	class MyComparator implements Comparator<Usuario>{

		@Override
		public int compare(Usuario o1, Usuario o2) {
			return (int) (o2.getSaldo()-o1.getSaldo());
		}
		
	}
	
}
	
