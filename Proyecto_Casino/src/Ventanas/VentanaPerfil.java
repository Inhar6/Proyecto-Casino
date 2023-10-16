package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPerfil extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Elementos
	//JLabels
	private JLabel nombre;
	private JLabel nUsuario;
	private JLabel fechaNacimiento;
	private JLabel perfil;
	//JTextFields
	private JTextField txtNombre;
	private JTextField txtNUsuario;
	private JTextField txtFechaNacimiento;
	
	
	public VentanaPerfil() {
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
		setTitle("Perfil del Usuario");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		nombre = new JLabel("Nombre");
		nUsuario = new JLabel("Usuario");
		fechaNacimiento = new JLabel("Fecha de nacimiento");
		perfil = new JLabel("Perfil");
		
		txtNombre= new JTextField(15);
		txtNUsuario= new JTextField(15);
		txtFechaNacimiento= new JTextField(15);
		
		JPanel principal = new JPanel();
		JPanel perf = new JPanel();
		JPanel central = new JPanel();
		JPanel derecho = new JPanel();
			JPanel N = new JPanel();
			JPanel NU = new JPanel();
			JPanel F = new JPanel();
		JPanel izquierdo = new JPanel();
		
		/*  Para añadir el menu superior si se quiere
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu x = new VentanaPanelMenu();
		*/
		
		N.setLayout(new FlowLayout());
		N.add(nombre);
		N.add(txtNombre);
		NU.setLayout(new FlowLayout());
		NU.add(nUsuario);
		NU.add(txtNUsuario);
		F.setLayout(new FlowLayout());
		F.add(fechaNacimiento);
		F.add(txtFechaNacimiento);
		derecho.setLayout(new GridLayout(3,1));
		derecho.add(N);
		derecho.add(NU);
		derecho.add(F);
		izquierdo.setLayout(new GridLayout(2,1));
		///////
		//Añadir Historiales
		///////
		central.setLayout(new GridLayout(1,2));
		central.add(derecho);
		central.add(izquierdo);
		perf.setLayout(new FlowLayout());
		perf.add(perfil);
		
		principal.setLayout(new BorderLayout());
		principal.add(perf, BorderLayout.NORTH);
		principal.add(central,BorderLayout.CENTER);
		
		//setLayout(new GridLayout(2,1));
        //add(menuSuperior);
        add(principal);
        /*
        ///
        setJMenuBar(menuBar1);
        x.enseñarMenu(menuSuperior, menu);
		///
        */
		setVisible(true);
	}
	
}
