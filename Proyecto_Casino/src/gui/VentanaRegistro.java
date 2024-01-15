package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import db.DBManager;
import domain.Usuario;
import io.Fichero;
import io.Propiedades;
import main.Main;

public class VentanaRegistro extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Logger
	private static final Logger logger = Logger.getLogger("Ventana Perfil");
	
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
	
	private JCheckBox box;
	private JCheckBox pol;
	
	private JButton PoliticaDePrivacidad;
	private JButton btnRegistro;
	//Color del panel
	private Color colorPanel = new Color(71, 113, 72);
	//Propiedades
	private Propiedades propiedades;
	public Propiedades getPropiedades() {
		return propiedades;
	}
	
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
		PoliticaDePrivacidad = new JButton("--Politica de privacidad--");
			PoliticaDePrivacidad.setForeground(Color.BLUE);
			PoliticaDePrivacidad.setBackground(Color.WHITE);
			
		box = new JCheckBox("+ 18");
			box.setSelected(false);
			box.setEnabled(false);
		pol = new JCheckBox("He leido y acepto la politica de privacidad");
			pol.setSelected(false);
			pol.setEnabled(false);
			
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
		JPanel pPrivacidad = new JPanel();
		JPanel pBoton = new JPanel();
		
		pReg.setLayout(new FlowLayout());
		pReg.add(Registro);
		pReg.setBackground(colorPanel);
		
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
		
		pPrivacidad.setLayout(new FlowLayout());
		pPrivacidad.add(PoliticaDePrivacidad);
		
		pBoton.setLayout(new FlowLayout());
		pBoton.add(btnRegistro);
		
		central.setLayout(new GridLayout(11, 1));
		central.add(pReg);
		central.add(pPais);
		central.add(pDNI);
		central.add(pNombre);
		central.add(pApellido);
		central.add(pFecha);
		central.add(pNombreU);
		central.add(pContraseña);
		central.add(pAvisos);
		central.add(pPrivacidad);
		central.add(pBoton);
		
		add(central);
		
		//Propiedades
		propiedades= new Propiedades();
		propiedades.cargar();
		//Icono de la ventana
		setIconImage(new ImageIcon(getPropiedades().getProperty("favicon")).getImage());
							
		btnRegistro.setEnabled(false);
		String fecha= "yyyy-MM-dd";
		txtFecha.setText(fecha);
		
		
		//Para mostrar al usuaro la manera de introducir los datos
		txtFecha.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(txtFecha.getText().equals(fecha)) {
					txtFecha.setText("");
					txtFecha.setForeground(Color.black);
				}
				
			}
			
				@Override
				public void focusLost(FocusEvent e) {
					if(txtFecha.getText().isEmpty()) {
						txtFecha.setText(fecha);
						txtFecha.setForeground(Color.gray);
					}
				
					
				}	
			});
		//I la fecha no es valida el boton de registro no esta activo
		txtFecha.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarFecha();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarFecha();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validarFecha();
				
			}
		});
		
		PoliticaDePrivacidad.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Politica de privacidad abierta");
				String rutaArchivo = "resources/data/PoliticaDePrivacidad.txt";
				String datos = Fichero.leerFichero(rutaArchivo);
				//Hacer que el tetxto aparezca en buenas condiciones
				JTextArea area = new JTextArea(datos);
				area.setEditable(false);
				area.setLineWrap(true);
				area.setWrapStyleWord(true);
				//Meter un scroll para que el texto no tenga problemas de visualizacion
				JScrollPane scroll = new JScrollPane(area);
				scroll.setPreferredSize(new Dimension(400,300));
				JOptionPane.showMessageDialog(null, scroll, "Reglas del juego", JOptionPane.INFORMATION_MESSAGE);
				pol.setSelected(true);
				
			}
		});
		
		btnRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (camposCompletos()) {
					Usuario u = new Usuario(txtNombre.getText(), txtApellido.getText(), txtDNI.getText(),
							txtUsuario.getText(), txtContraseña.getText(), 0, 0);
					Main.DBlstUsuarios.add(u);
					DBManager.añadirUsuario(u);
					logger.info("Nuevo usuario creado");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Se deben llenar todos los campos", "Error",
							JOptionPane.ERROR_MESSAGE);
					logger.info("Datos mal introducidos");
				}
			}
		});
	}
	
	public void validarFecha() {
        // Intentar analizar la fecha
		String fechaTexto = txtFecha.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fecha = LocalDate.parse(fechaTexto, formatter);
            int anyo = fecha.getYear();
            if(2023-anyo >=18) {
            	box.setSelected(true);
            	btnRegistro.setEnabled(true);
            }
        } catch (Exception e) {
           // logger.info("Error al analizar la fecha. Asegúrate de que está en el formato correcto.");
        }
    }
	//Comprobar si todos los datos estan rellenados correctamente
	private boolean camposCompletos() {
	    return !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && !txtDNI.getText().isEmpty()
	            && !txtUsuario.getText().isEmpty() && !txtContraseña.getText().isEmpty();
	}
	
}
