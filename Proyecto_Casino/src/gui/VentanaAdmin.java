package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaAdmin extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Elementos
	private JLabel admin;
	
	private JButton btnJuegos;
	private JButton btnUsuarios;
	
	
	public VentanaAdmin() {
		setTitle("Ventana del Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,700);
		setLocationRelativeTo(null);
		
		admin = new JLabel("                          Vista de administrador");
		btnJuegos= new JButton("Estadisticas de los juegos");
		btnUsuarios= new JButton("Datos de los usuarios");
		
		JPanel pPrincipal = new JPanel();
		
		
		pPrincipal.setLayout(new FlowLayout());
		pPrincipal.add(btnJuegos);
		pPrincipal.add(btnUsuarios);
		
		setLayout(new BorderLayout());
		add(admin, BorderLayout.NORTH);
		add(pPrincipal,BorderLayout.CENTER);
		
		
		btnUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAdminUsuarios();
				
			}
		});
		btnJuegos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAdminJuegos();
				
			}
		});
		setVisible(true);
		pack();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VentanaAdmin();
			}
		});
	}
}
