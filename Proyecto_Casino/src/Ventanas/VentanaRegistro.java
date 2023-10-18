package Ventanas;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] paises = {"España","Francia","Alemania","Japon","China","Estados Unidos","Belgica","Reino Unido"};
	
	//Elementos
	private JComboBox<String> jcbPais;
	
	private JLabel Registro;
	private JLabel Pais;
	private JLabel DNI;
	private JLabel Nombre;
	private JLabel Apellido;
	private JLabel FechaNacimiento;
	private JLabel Usuario;
	private JLabel Contraseña;
	
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFecha;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	
	private JButton btnRegistro;
	
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 500);
		setTitle("Sing Up");
		setLocationRelativeTo(null);
		setVisible(true);
		
		jcbPais= new JComboBox<String>(paises);
		
		Registro = new JLabel("Registro");
		Pais = new JLabel("Pais de Residencia");
		DNI = new JLabel            ("                                DNI :");
		Nombre = new JLabel         ("                        Nombre :");
		Apellido = new JLabel       ("                      Apellidos :");
		FechaNacimiento = new JLabel("Fecha de Nacimiento :");
		Usuario = new JLabel        ("     Nombre de Usuario:");
		Contraseña = new JLabel     ("                    Contraseña:");
		
		txtDNI = new JTextField(15);
		txtNombre= new JTextField(15);
		txtApellido= new JTextField(15);
		txtUsuario= new JTextField(15);
		txtContraseña= new JTextField(15);
		txtFecha= new JTextField(15);
		
		btnRegistro = new JButton("Registrarse");
		
		JCheckBox box = new JCheckBox("+ 18");
			box.setSelected(false);
		JCheckBox pol = new JCheckBox("He leido y acepto la politica de privacidad");
			pol.setSelected(false);
		
			
		JPanel central = new JPanel();
		JPanel pReg = new JPanel();
		JPanel pPais = new JPanel();
		JPanel pNombre = new JPanel();
		JPanel pApellido = new JPanel();
		JPanel pDNI = new JPanel();
		JPanel pFecha = new JPanel();
		JPanel pNombreU = new JPanel();
		JPanel pContraseña = new JPanel();
		JPanel pAvisos = new JPanel();
		JPanel pBoton = new JPanel();
		
		pReg.setLayout(new FlowLayout());
		pReg.add(Registro);
		
		pPais.setLayout(new FlowLayout());
		pPais.add(Pais);
		pPais.add(jcbPais);
		
		pDNI.setLayout(new FlowLayout());
		pDNI.add(DNI);
		pDNI.add(txtDNI);
		
		pNombre.setLayout(new FlowLayout());
		pNombre.add(Nombre);
		pNombre.add(txtNombre);
		
		pApellido.setLayout(new FlowLayout());
		pApellido.add(Apellido);
		pApellido.add(txtApellido);
		
		pFecha.setLayout(new FlowLayout());
		pFecha.add(FechaNacimiento);
		pFecha.add(txtFecha);
		
		pNombreU.setLayout(new FlowLayout());
		pNombreU.add(Usuario);
		pNombreU.add(txtUsuario);
		
		pContraseña.setLayout(new FlowLayout());
		pContraseña.add(Contraseña);
		pContraseña.add(txtContraseña);
		
		pAvisos.setLayout(new FlowLayout());
		pAvisos.add(box);
		pAvisos.add(pol);
		
		
		pBoton.setLayout(new FlowLayout());
		pBoton.add(btnRegistro);
		
		central.setLayout(new GridLayout(10, 1));
		central.add(pReg);
		central.add(pPais);
		central.add(pDNI);
		central.add(pNombre);
		central.add(pApellido);
		central.add(pFecha);
		central.add(pNombreU);
		central.add(pContraseña);
		central.add(pAvisos);
		central.add(pBoton);
		
		add(central);
		
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
	}
	
}
