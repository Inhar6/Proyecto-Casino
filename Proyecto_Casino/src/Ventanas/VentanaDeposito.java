package Ventanas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class VentanaDeposito extends JFrame{
	private Color colorPanel = new Color(71, 113, 72);
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
		deposito.setBackground(colorPanel);
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
		JTextField JTextfieldNumeroDeCuenta= new JTextField(20);
		numeroDeCuenta.add(JTextfieldNumeroDeCuenta);
		String StringNumeroDeCuenta = "Numero de cuenta";
		JTextfieldNumeroDeCuenta.setText(StringNumeroDeCuenta);
		JTextfieldNumeroDeCuenta.setForeground(Color.gray);
		
		//DIA MES 
		JTextField JTextfieldDiaYMes = new JTextField(10);
		diaMesCvc.add(JTextfieldDiaYMes);
		String StringDiaYMes = "Dia/Mes";
		JTextfieldDiaYMes.setText(StringDiaYMes);
		JTextfieldDiaYMes.setForeground(Color.gray);
		
		
		
		
		//CVC     
		JTextField JTextfieldCvc = new JTextField(10);
		diaMesCvc.add(JTextfieldCvc);
		String StringCvc = "CVC";
		JTextfieldCvc.setText(StringCvc);
		JTextfieldCvc.setForeground(Color.gray);
		
		//USUARIO
		JTextField JTextfieldUsuario = new JTextField(20);
		usuario.add(JTextfieldUsuario);
		String StringUsuario = "Usuario";
		JTextfieldUsuario.setText(StringUsuario);
		JTextfieldUsuario.setForeground(Color.gray);
		
		
		
		//CONTRASEÑA
		JPasswordField JTextfieldContrasea= new JPasswordField(20);
		contraseña.add(JTextfieldContrasea);
		String StringContrasea = "Contraseña";
		JTextfieldContrasea.setText(StringContrasea);
		JTextfieldContrasea.setForeground(Color.gray);
		
		//CANTIDAD DE DEPOSITO
		JTextField JTextfieldCantidadDeDeposito = new JTextField(20);
		cantidadDeDeposito.add(JTextfieldCantidadDeDeposito);
		String StringCantidadDeDeposito = "Cantidad De Deposito";
		JTextfieldCantidadDeDeposito.setText(StringCantidadDeDeposito);
		JTextfieldCantidadDeDeposito.setForeground(Color.gray);
		
		
		//DEPOSITAR
		JButton botonDepositar = new JButton("Depositar");
		depositar.add(botonDepositar);
		
		
		
		JTextfieldNumeroDeCuenta.addFocusListener( new FocusListener() {
					
					@Override
					public void focusGained(FocusEvent e) {
						if(JTextfieldNumeroDeCuenta.getText().equals(StringNumeroDeCuenta)) {
							JTextfieldNumeroDeCuenta.setText("");
							JTextfieldNumeroDeCuenta.setForeground(Color.black);
						}
					
						
					}
					
					
					@Override
					public void focusLost(FocusEvent e) {
						if(JTextfieldNumeroDeCuenta.getText().isEmpty()) {
							JTextfieldNumeroDeCuenta.setText(StringNumeroDeCuenta);
							JTextfieldNumeroDeCuenta.setForeground(Color.gray);
						}
					
						
					}
				});
		JTextfieldDiaYMes.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(JTextfieldDiaYMes.getText().equals(StringDiaYMes)) {
					JTextfieldDiaYMes.setText("");
					JTextfieldDiaYMes.setForeground(Color.black);
				}
				
			}
			
				@Override
				public void focusLost(FocusEvent e) {
					if(JTextfieldDiaYMes.getText().isEmpty()) {
						JTextfieldDiaYMes.setText(StringDiaYMes);
						JTextfieldDiaYMes.setForeground(Color.gray);
					}
				
					
				}
					
				
					
				
			});
		
		
		
		JTextfieldCvc.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(JTextfieldCvc.getText().equals(StringCvc)) {
					JTextfieldCvc.setText("");
					JTextfieldCvc.setForeground(Color.black);
				}
				
			}
			
				@Override
				public void focusLost(FocusEvent e) {
					if(JTextfieldCvc.getText().isEmpty()) {
						JTextfieldCvc.setText(StringCvc);
						JTextfieldCvc.setForeground(Color.gray);
					}
				
					
				}
					
				
					
				
			});
		
		
		
			
		JTextfieldUsuario.addFocusListener(new FocusListener() {
				
				@Override
				public void focusGained(FocusEvent e) {
					if(JTextfieldUsuario.getText().equals(StringUsuario)) {
						JTextfieldUsuario.setText("");
						JTextfieldUsuario.setForeground(Color.black);
					}
					
				}
				
					@Override
					public void focusLost(FocusEvent e) {
						if(JTextfieldUsuario.getText().isEmpty()) {
							JTextfieldUsuario.setText(StringUsuario);
							JTextfieldUsuario.setForeground(Color.gray);
						}
					
						
					}
						
					
						
					
				});
		
		JTextfieldContrasea.addFocusListener(new FocusListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(JTextfieldContrasea.getText().equals(StringContrasea)) {
					JTextfieldContrasea.setText("");
					JTextfieldContrasea.setForeground(Color.black);
				}
				
			}
			
				@SuppressWarnings("deprecation")
				@Override
				public void focusLost(FocusEvent e) {
					if(JTextfieldContrasea.getText().isEmpty()) {
						JTextfieldContrasea.setText(StringUsuario);
						JTextfieldContrasea.setForeground(Color.gray);
					}
				
					
				}
					
				
					
				
			});
		
		JTextfieldCantidadDeDeposito.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(JTextfieldCantidadDeDeposito.getText().equals(StringCantidadDeDeposito)) {
					JTextfieldCantidadDeDeposito.setText("");
					JTextfieldCantidadDeDeposito.setForeground(Color.black);
				}
				
			}
			
				@Override
				public void focusLost(FocusEvent e) {
					if(JTextfieldCantidadDeDeposito.getText().isEmpty()) {
						JTextfieldCantidadDeDeposito.setText(StringCantidadDeDeposito);
						JTextfieldCantidadDeDeposito.setForeground(Color.gray);
					}
				
					
				}
					
				
					
				
			});
		
		botonDepositar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JTextfieldNumeroDeCuenta.getForeground()== Color.gray|| JTextfieldDiaYMes.getForeground()== Color.gray|| JTextfieldCvc.getForeground()== Color.gray||
						JTextfieldUsuario.getForeground()== Color.gray||JTextfieldContrasea.getForeground()== Color.gray||JTextfieldCantidadDeDeposito.getForeground()== Color.gray) {
					
					
					JOptionPane.showMessageDialog(null, "Asegurate de rellenar todos los campos", "Error", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		
		
		
		
		
		
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
