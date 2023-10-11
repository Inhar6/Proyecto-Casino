package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


public class VentanaRuleta extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Elementos
	//Botones
	private JButton btnVerde;
	private JButton btnRojo;
	private JButton btnNegro;
	private JButton btn1Docena;
	private JButton btn2Docena;
	private JButton btn3Docena;
	private JButton btn1a18;
	private JButton btn19a36;
	private JButton btnPar;
	private JButton btnImpar;
	private JButton btn1Fila;
	private JButton btn2Fila;
	private JButton btn3Fila;
	//JList
	private JList<Integer> lstHistorial;
	private DefaultListModel<Integer> dlmHistorial;
	private JScrollPane scroll;
	
	public VentanaRuleta(JPanel pMenu, JMenu menuJuegos, JMenuBar menuBar) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Ruleta");
		setVisible(true);
		setLocationRelativeTo(null);
		
		//Botones
		btnVerde = new JButton("Verde");
			btnVerde.setBackground(Color.green);
			btnVerde.setForeground(Color.white);
		btnRojo = new JButton("Rojo");
			btnRojo.setBackground(Color.red);
			btnRojo.setForeground(Color.white);
		btnNegro = new JButton("Negro");
			btnNegro.setBackground(Color.black);
			btnNegro.setForeground(Color.white);
		btn1Docena = new JButton("1º 12");
		btn2Docena = new JButton("2º 12");
		btn3Docena = new JButton("3º 12");
		btn1a18 = new JButton("1-18");
		btn19a36 = new JButton("19-36");
		btnPar = new JButton("Par");
		btnImpar = new JButton("Impar");
		btn1Fila = new JButton("2a1");
		btn2Fila = new JButton("2a1");
		btn3Fila = new JButton("2a1");
		
		//Parte del Historial
		dlmHistorial = new DefaultListModel<>();
		lstHistorial = new JList<>(dlmHistorial);
		//Para que la lista se visualize de manera horizontal
		lstHistorial.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		scroll=new JScrollPane(lstHistorial);
		
		// Añadir esto para el menu superior
		
//		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
//		add(pMenu);
//		setJMenuBar(menuBar);
//		menuBar.add(menuJuegos);
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				VentanaInicial ventanaInicial = new VentanaInicial();
//				ventanaInicial.setVisible(true);
//			}
//		});
		
		JPanel central = new JPanel();
		JPanel inferior = new JPanel();
		JPanel inferior1 = new JPanel();
		JPanel inferior2 = new JPanel();
		JPanel derecho = new JPanel();
		
		inferior1.setLayout(new GridLayout(1,3));
		inferior1.add(btn1Docena);
		inferior1.add(btn2Docena);
		inferior1.add(btn3Docena);
		
		inferior2.setLayout(new GridLayout(1,6));
		inferior2.add(btn1a18);
		inferior2.add(btnPar);
		inferior2.add(btnRojo);
		inferior2.add(btnNegro);
		inferior2.add(btnImpar);
		inferior2.add(btn19a36);
		
		inferior.setLayout(new GridLayout(2, 1));
		inferior.add(inferior1);
		inferior.add(inferior2);
		
		derecho.setLayout(new GridLayout(3,1));
		derecho.add(btn1Fila);
		derecho.add(btn2Fila);
		derecho.add(btn3Fila);
		
		central.setLayout(new BorderLayout());
		central.add(btnVerde, BorderLayout.WEST);
		central.add(inferior,BorderLayout.SOUTH);
		central.add(derecho,BorderLayout.EAST);
	
		setLayout(new GridLayout(3,1));
		add(new JPanel());
		// Cambiar por pMenu.add(central);
		add(central);
		add(new JPanel());
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VentanaRuleta(null, null, null);
				
			}
		});
	}
}
