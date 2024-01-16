package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.DBManager;
import domain.Usuario;
import io.Propiedades;
import main.Main;

public class VentanaLogin extends JFrame{
	
	static boolean loged = false;
	
	private static final long serialVersionUID = 1L;
	//Color del panel
	private Color colorPanel = new Color(71, 113, 72);
	private Propiedades propiedades;
	public Propiedades getPropiedades() {
		return propiedades;
	}
	
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setTitle("Login");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		//Propiedades
		propiedades= new Propiedades();
		propiedades.cargar();
		//Icono de la ventana
		setIconImage(new ImageIcon(getPropiedades().getProperty("favicon")).getImage());
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
//			
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// JButton
		JButton bInicioSecion = new JButton("Iniciar sesión");
		
		// Textos Usuario/Contraseña
		JTextField tfUsuario = new JTextField(20);
		JPasswordField passContraseña = new JPasswordField(20);
		
		// JPanel
		JPanel pIniciarSesion  = new JPanel();
		JPanel pBotones = new JPanel();
		JPanel userPass = new JPanel();
		JPanel pText1 = new JPanel();
		JPanel pText2 = new JPanel();
		userPass.setLayout(new GridLayout(2,1));
		
		// JLabel Usuario/Contraseña
		JLabel lIniciarSesion = new JLabel("Iniciar sesion");
		JLabel lUsurario = new JLabel("Usuario       ");
		JLabel lContraseña = new JLabel("Contraseña");
		
		//añadir J.. a paneles 
		add(pIniciarSesion, BorderLayout.NORTH);
		pIniciarSesion.add(lIniciarSesion, BorderLayout.CENTER);
		pIniciarSesion.setBackground(colorPanel);
		add(userPass,BorderLayout.CENTER);
		userPass.add(pText1);
		userPass.add(pText2);
		add(pBotones,BorderLayout.SOUTH);
		
		// Panel Usuario/Contraseña
		pText1.add(lUsurario);
		pText1.add(tfUsuario);
		pText2.add(lContraseña);
		pText2.add(passContraseña);
		
		// Panel botones
		pBotones.add(bInicioSecion);
		
		bInicioSecion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String contra = new String(passContraseña.getPassword());
				if("Admin".equals(tfUsuario.getText()) && "Admin".equals(new String(passContraseña.getPassword()))) {
					new VentanaAdmin();
					loged = true;
					new VentanaInicial();
					dispose();
					
				}else if(DBManager.existeUsuarioLogin(tfUsuario.getText(), contra)) {
					System.out.println("Registro exitoso");
					Usuario u = new Usuario();
					for(Usuario user: Main.DBlstUsuarios) {
						if(tfUsuario.getText().equals(user.getNombreUsuario())) {
							u = user;
						}
					}
					System.out.println(u);
					//Mandar el usuario a las ventanas
					VentanaPanelMenu.user = u;
					VentanaPanelMenu.balance = u.getSaldo();
					loged = true;
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
				}
				
		});
	}
}

