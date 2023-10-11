package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class VentanaLogin extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public VentanaLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 400);
		setTitle("Log in");
		
		//creacion de J..
		JButton bInicioSecion = new JButton("Iniciar sesion");
		JButton bRecuperarContraseña = new JButton("Recuperar contaseña");
		
		
		JTextField tfUsuario = new JTextField(20);
	
		JPasswordField passContraseña = new JPasswordField(20);
		
		
		JLabel lIniciarSesion = new JLabel("Iniciar sesion");
		//lIniciarSesion.setBorder(new EmptyBorder(0,(550/2)/2 ,0, 0));

		JLabel lUsurario = new JLabel("Usuario");
		JLabel lContraseña = new JLabel("Contrseña");

		//creacion de paneles 
		JPanel pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(3,1));
	
		JPanel pText = new JPanel();
		pText.setLayout(new GridLayout(6,1));
		//pText.add(passContraseña, BorderLayout.CENTER);
		//pPrincipal.add(pText, BoxLayout.Y_AXIS);
		//pText.setBorder(new EmptyBorder(65,35,65,35));
		
		JPanel pBotones = new JPanel();
		//pPrincipal.add(pBotones, new BorderLayout(0,0));
		//pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS));
		//pPrincipal.add(pBotones, BorderLayout.CENTER);
	
		
		//añadir J.. a paneles 
		pPrincipal.add(lIniciarSesion);
		pPrincipal.add(pText);
		pPrincipal.add(pBotones);
		
		
		pText.add(lUsurario);
		pText.add(tfUsuario);
		pText.add(new JLabel());
		pText.add(lContraseña);
		pText.add(passContraseña);
		pText.add(new JLabel());
		
		pBotones.add(bRecuperarContraseña);
		pBotones.add(bInicioSecion);
		
		add(pPrincipal);
		
		setLocation(getWidth()/2,getHeight()/2);
		setVisible(true);
		
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				
				new VentanaLogin();
				
			}
		});
	}
}