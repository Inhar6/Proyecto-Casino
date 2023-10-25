package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Clases.Usuario;

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
	
	public VentanaAdminUsuarios() {
		setTitle("Datos de los Usuarios");
		setSize(800,700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		buscador = new JLabel("Filtrar por nombre: ");
		txtBuscador= new JTextField(15);
		nombre = new JLabel("Nombre del usuario seleccionado");
		//Lista Usuarios
		dlmUsuarios = new DefaultListModel<>();
		lstUsuarios = new JList<Usuario>(dlmUsuarios);
		scrollLst = new JScrollPane(lstUsuarios);
		//ComboBox
		jcbJuegos= new JComboBox<String>(listaJuegos);
		//Tabla Juegos
		dtmJuegos = new DefaultTableModel();
		tJuegos = new JTable(dtmJuegos);
		scrollJuegos = new JScrollPane(tJuegos);
		//Tabla Balance
		dtmBalance = new DefaultTableModel();
		tBalance = new JTable(dtmBalance);
		scrollBalance = new JScrollPane(tBalance);
		
		
		JPanel pBuscador = new JPanel(new FlowLayout());
		pBuscador.add(buscador);
		pBuscador.add(txtBuscador);
		JPanel pNombre = new JPanel(new FlowLayout());
		pNombre.add(nombre);
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
		
		setVisible(true);
	}

}
