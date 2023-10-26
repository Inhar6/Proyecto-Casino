package Ventanas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaDeposito extends JFrame{
	
	public VentanaDeposito() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setTitle("Deposito");
		setVisible(true);
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
		
		JPanel panelPrincipal = new JPanel(new GridLayout(7,1));
		JPanel deposito = new JPanel();
		JPanel numeroDeCuenta = new JPanel();
		JPanel diaMesCvc = new JPanel();
		JPanel usuario = new JPanel();
		JPanel contraseña = new JPanel();
		JPanel cantidadDeDeposito = new JPanel();
		JPanel depositar = new JPanel();
		
		add(panelPrincipal);
		panelPrincipal.add(deposito);
		panelPrincipal.add(numeroDeCuenta);
		panelPrincipal.add(diaMesCvc);
		panelPrincipal.add(usuario);
		panelPrincipal.add(contraseña);
		panelPrincipal.add(cantidadDeDeposito);
		panelPrincipal.add(depositar);
		
		//DEPOSITO
		JLabel JLabelDeposito = new JLabel("Deposito");
		deposito.add(JLabelDeposito);
		
		//NUMERO DE CUENTA
		JTextField JLabelnumeroDeCuenta = new JTextField("                Numero de cuenta",20);
		numeroDeCuenta.add(JLabelnumeroDeCuenta);
		
		
		//DIA MES Y CVC
		JTextField diaYmesJTextfield = new JTextField("       Dia / Mes " ,9);
		diaMesCvc.add(diaYmesJTextfield);
		JTextField CvcJTextfield = new JTextField("         CVC  " ,9);
		diaMesCvc.add(CvcJTextfield);
		
		//USUARIO
		JTextField usuarioJTextfield = new JTextField("                        Usuario",20);
		usuario.add(usuarioJTextfield);
		
		
		
		//CONTRASEÑA
		JTextField cntraseñaJTextfield= new JTextField("                     Contraseña",20);
		contraseña.add(cntraseñaJTextfield);
		
		//CANTIDAD DE DEPOSITO
		JTextField cantidadDeDepositoJTextfield = new JTextField("              Cantidad de deposito",20);
		cantidadDeDeposito.add(cantidadDeDepositoJTextfield);
		
		
		//DEPOSITAR
		JButton botonDepositar = new JButton("Depositar");
		depositar.add(botonDepositar);
		
		
		
	}
	
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaDeposito();				
			}
        });
	}
	
}
