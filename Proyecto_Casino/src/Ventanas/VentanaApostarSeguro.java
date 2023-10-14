package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaApostarSeguro extends JFrame{
	
	public VentanaApostarSeguro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 300);
		setTitle("ApostarSeguo");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
		
		//PANELES
		JPanel panelPrincipal = new JPanel(new GridLayout(6,1));
		JPanel limiteDeDeposito = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel diario = new JPanel();
		JPanel mensual = new JPanel();
		JPanel anual = new JPanel();
		JPanel preguntaAlUsuario = new JPanel();
		JPanel panelBoton = new JPanel();
		    
		
		//AÑADIR PANEL PRINCIPAL A LA VENTANA Y LOS DEMAS PANELES A EL PRINCIPAL
		add(panelPrincipal);
		panelPrincipal.add(limiteDeDeposito);
		panelPrincipal.add(diario);
		panelPrincipal.add(mensual);
		panelPrincipal.add(anual);
		panelPrincipal.add(preguntaAlUsuario);
		panelPrincipal.add(panelBoton);
		
		//JLABEL DE LIMITE DE DEPOSITO
		JLabel limiteDeDepositoJLabel = new JLabel("Limite de deposito");
		limiteDeDeposito.add(limiteDeDepositoJLabel);
		
		//JLABEL Y TEXTFIELD DE LIMITE DIARIO
		JLabel diarioJLabel = new JLabel("Diarios");
		JTextField diarioJTextfield = new JTextField(10);
		diario.add(diarioJLabel);
		diario.add(diarioJTextfield);
		//JLABEL Y TEXTFIELD DE LIMITE MENSUAL
		JLabel mensualJLabel = new JLabel("Mensuales");
		JTextField menusalJTextfield = new JTextField(10);
		mensual.add(mensualJLabel);
		mensual.add(menusalJTextfield);	
		
		//JLABEL Y TEXTFIELD DE LIMITE ANUAL
		JLabel anualJLabel = new JLabel("Anuales");
		JTextField anualJTextfield = new JTextField(10);
		anual.add(anualJLabel);
		anual.add(anualJTextfield);	
		
		//CHECKBOX PARA ACEPTAR LOS LIMITES
		JCheckBox casilla = new JCheckBox("¿Seguro que quieres aceptar los limites?");
		preguntaAlUsuario.add(casilla);
		
		//JBUTTON PARA GUARDAR LOS LIMITES
		JButton botonaceptarLimites = new JButton("Aceptar Limites");
		panelBoton.add(botonaceptarLimites);
		
		botonaceptarLimites.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(casilla.isSelected()) {
					
				}else {
					JOptionPane.showMessageDialog(null, "No has aceptado los terminos de los limites de apuesta","Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
			
			});
			
		
		
		
		
	}
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaApostarSeguro();				
			}
        });
	}

}