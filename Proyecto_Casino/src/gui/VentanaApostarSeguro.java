package gui;


import java.awt.Color;
import java.awt.FlowLayout;

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

import domain.Usuario;

import java.util.logging.Logger;


public class VentanaApostarSeguro extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorPanel = new Color(71, 113, 72);
	private static final Logger logger = Logger.getLogger("VentanaApostarSeguro");
	
	
	public VentanaApostarSeguro(Usuario u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 300);
		setTitle("ApostarSeguo");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/iconos/favicon.png").getImage());
		
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
		limiteDeDeposito.setBackground(colorPanel);
		
		//JLABEL Y TEXTFIELD DE LIMITE DIARIO
		JLabel diarioJLabel = new JLabel( "       Diarios:");
		JTextField JTextfieldDiario = new JTextField(10);
		diario.add(diarioJLabel);
		diario.add(JTextfieldDiario);
		//JLABEL Y TEXTFIELD DE LIMITE MENSUAL
		JLabel mensualJLabel = new JLabel("Mensuales:");
		JTextField JTextfieldMensual = new JTextField(10);
		mensual.add(mensualJLabel);
		mensual.add(JTextfieldMensual);	
		
		//JLABEL Y TEXTFIELD DE LIMITE ANUAL
		JLabel anualJLabel = new JLabel("      Anuales:");
		JTextField JTextfieldAnual = new JTextField(10);
		anual.add(anualJLabel);
		anual.add(JTextfieldAnual);	
		
		//CHECKBOX PARA ACEPTAR LOS LIMITES
		JCheckBox casilla = new JCheckBox("  ¿Seguro que quieres aceptar los limites?");
		preguntaAlUsuario.add(casilla);
		
		//JBUTTON PARA GUARDAR LOS LIMITES
		JButton botonAceptarLimites = new JButton("Aceptar Limites");
		panelBoton.add(botonAceptarLimites);
		
		
		
		botonAceptarLimites.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(JTextfieldDiario.getText().isEmpty()||JTextfieldMensual.getText().isEmpty()||JTextfieldAnual.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has introducido todos los limites","Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					
				}
				
				
			}
			
			});
		
		botonAceptarLimites.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoDiario = JTextfieldDiario.getText();
				if(textoDiario.length()<=4) {
					
				}else {
					JOptionPane.showMessageDialog(null,"No puedes establecer cantidades superiores a 4 digitos", "Diario", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		botonAceptarLimites.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoMensual = JTextfieldMensual.getText();
				
				if(textoMensual.length()<=5) {
					
				}else {
					JOptionPane.showMessageDialog(null,"No puedes establecer cantidades superiores a 5 digitos", "Mensual", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		botonAceptarLimites.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoAnual = JTextfieldAnual.getText();
				if( textoAnual.length()<=8) {
					
				}else {
					JOptionPane.showMessageDialog(null,"No puedes establecer cantidades superiores a 8 digitos", "Anual", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		botonAceptarLimites.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(casilla.isSelected()) {
					logger.info("Has establecido con exito tus limites de apuesta");
					
				}else {
					JOptionPane.showMessageDialog(null, "Debes clickar la casilla", "Acepatr los limites", JOptionPane.INFORMATION_MESSAGE);
				}
					
				
				
				
			}
			
			});
		
		botonAceptarLimites.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				String textoDiario = JTextfieldDiario.getText();
				String textoMensual = JTextfieldMensual.getText();
				String textoAnual = JTextfieldAnual.getText();
				if(casilla.isSelected() & textoDiario.length()<=4 & textoMensual.length()<=5 & textoAnual.length()<=8) {
					logger.info("Has establecido con exito tus limites de apuesta");
					
				}
					
				
				
				
			}
			
			});
			
		
		
		
		
	}
}