package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaInicial extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private JButton bLogin = new JButton ("Login");
	
	
	private JPanel pMenu = new JPanel (new BorderLayout());
	
	
	
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("Casino");
		setVisible(true);
		
		
		pMenu.add(bLogin, BorderLayout.NORTH);
		add(pMenu);
		
		bLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vl = new VentanaLogin();
			}
		});
	}
	
	public static void main(String[] args) {
		new VentanaInicial();
	}
}
