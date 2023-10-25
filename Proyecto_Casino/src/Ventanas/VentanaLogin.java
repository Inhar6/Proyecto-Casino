package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class VentanaLogin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	//Color del panel
	private Color colorPanel = new Color(71, 113, 72);
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setTitle("Login");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
		
		// JButton
		JButton bInicioSecion = new JButton("Iniciar sesión");
		JButton bRecuperarContraseña = new JButton("Recuperar contaseña");
		
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
		pBotones.add(bRecuperarContraseña);
		pBotones.add(bInicioSecion);
		
	}
}

