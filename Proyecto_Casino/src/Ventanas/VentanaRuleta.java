package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;


public class VentanaRuleta extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int[] rojo = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
	/*
	//Docenas
	private int [] docenaP = {1,2,3,4,5,6,7,8,9,10,11,12};
	private int [] docenaS= {13,14,15,16,17,18,19,20,21,22,23,24};
	private int [] docenaT= {24,26,27,28,29,30,31,32,33,34,35,36};
	//Filas
	private int [] filaP = {};
	private int[] filaS = {};
	private int[] filaT = {};
	*/
	private List<Integer> apuesta = new ArrayList<Integer>();
	
	
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
	private JButton btnJugar;
	//JList
	private JList<Integer> lstHistorial;
	private DefaultListModel<Integer> dlmHistorial;
	private JScrollPane scroll;
	//Random
	Random random = new Random();
	
	public VentanaRuleta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Ruleta");
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());
		
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
		btnJugar = new JButton("Jugar");
		
		//Parte del Historial
		dlmHistorial = new DefaultListModel<>();
		lstHistorial = new JList<>(dlmHistorial);
		//Para dar colores a los numeros de la lista
		lstHistorial.setCellRenderer(new MyCellRender());
		//Para que la lista se visualize de manera horizontal
		lstHistorial.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		lstHistorial.setVisibleRowCount(1);
		//lstHistorial.setFont(new Font("Arial", Font.BOLD,16));
		scroll=new JScrollPane(lstHistorial);
		
		JPanel central = new JPanel();
		JPanel inferior = new JPanel();
		JPanel inferior1 = new JPanel();
		JPanel inferior2 = new JPanel();
		JPanel derecho = new JPanel();
		JPanel histo = new JPanel();
		JPanel juego = new JPanel();
		
		////////////////////////////
		//Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
		menuGeneral.abrirNuevaVentanaRuleta();
        // Añadir menuApostar
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);
		///////////////////////////////
        //RULETA
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
		
		juego.setLayout(new FlowLayout());
		juego.add(btnJugar);
		
		histo.setLayout(new GridLayout(3,2));
		histo.add(new JPanel());
		histo.add(new JPanel());
		histo.add(scroll);
		histo.add(new JPanel());
		histo.add(new JPanel());
		histo.add(juego);
		
		JPanel menuHisto = new JPanel();
		menuHisto.setLayout(new GridLayout(2,1));
		menuHisto.add(menuSuperior);
		menuHisto.add(histo);
		
		JPanel ruleta = new JPanel();
		ruleta.setLayout(new GridLayout(2, 1));
		ruleta.add(menuHisto);
		ruleta.add(central);
		
		////////////////////////
        setJMenuBar(menuBar1);
        menuGeneral.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
		////////////////////////
		
		add(ruleta);

		//pack();
		
		//Prueba para saber si el render funciona pulsando el boton verde
		btnVerde.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*	
				//Imprime en la lista valores del 0 al 10
					for(int i = 0;i<=10;i++) {
						dlmHistorial.addElement(i);
					}
				*/
					btnVerde.setEnabled(false);
					apuesta.add(0);
				
			}
		});
		btnRojo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnRojo.setEnabled(false);
				
			}
		});
		btnNegro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNegro.setEnabled(false);
				
			}
		});
		btnPar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPar.setEnabled(false);
				
			}
		});
		btnImpar.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				btnImpar.setEnabled(false);
			
			}
		});
		btn1a18.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1a18.setEnabled(false);
			
			}
		});
		btn19a36.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn19a36.setEnabled(false);
			
			}
		});
		btn1Docena.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1Docena.setEnabled(false);
			
			}
		});
		btn2Docena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn2Docena.setEnabled(false);
			
			}
		});
		btn3Docena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn3Docena.setEnabled(false);
			
			}
		});

		btn1Fila.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1Fila.setEnabled(false);
			
			}
		});
		btn2Fila.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn2Fila.setEnabled(false);
			
			}
		});
		btn3Fila.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn3Fila.setEnabled(false);
			
			}
		});
		
		
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = random.nextInt(36);
				dlmHistorial.addElement(num);
				//double resultado=juego(num, apuesta);
				//System.out.println(resultado);
				activarBotones();
				
			}
		});
	}
	
	class MyCellRender extends JLabel implements ListCellRenderer<Integer>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<? extends Integer> list, Integer value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());
			setOpaque(true);
			if(value == 0) {
				setBackground(Color.green);
				setForeground(Color.white);
			}else if(esRojo(value)) {
				setBackground(Color.red);
				setForeground(Color.white);
			}else {
				setBackground(Color.BLACK);
				setForeground(Color.white);
			}
			
			return this;
		}
		
	}
	
	//Funcion para saber si el valor que se muestra es rojo
	public boolean esRojo(Integer i) {
		for(Integer num : rojo) {
			if(i.equals(num)) {
				return true;
			}
		}
		return false;
	}

	public void activarBotones() {
		btnVerde.setEnabled(true);
		btnRojo.setEnabled(true);
		btnNegro.setEnabled(true);
		btnVerde.setEnabled(true);
			btnVerde.setBackground(Color.green);
			btnVerde.setForeground(Color.white);
		btnRojo.setEnabled(true);
			btnRojo.setBackground(Color.red);
			btnRojo.setForeground(Color.white);
		btnNegro.setEnabled(true);
			btnNegro.setBackground(Color.black);
			btnNegro.setForeground(Color.white);
		btn1Docena.setEnabled(true);
		btn2Docena.setEnabled(true);
		btn3Docena.setEnabled(true);
		btn1a18.setEnabled(true);
		btn19a36.setEnabled(true);
		btnPar.setEnabled(true);
		btnImpar.setEnabled(true);
		btn1Fila.setEnabled(true);
		btn2Fila.setEnabled(true);
		btn3Fila.setEnabled(true);
	}
	
	
	public double juego(int num, List<Integer> apuesta) {
		double resultado =0;
		if(apuesta.contains(num)) {
			
		}else {
			resultado=0;
		}
		return resultado;
	}
}