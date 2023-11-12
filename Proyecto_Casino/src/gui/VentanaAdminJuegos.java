package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaAdminJuegos extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Color del panel
	private Color colorPanel = new Color(71, 113, 72);
	//Elementos
	private JLabel sup;
	private JButton btn;
	private JButton btnInformes;
	//Estadisticas
	//Estadisticas-->Ruleta
	
	//Estadisticas-->Crash
	
	//Estadisticas-->CoinFlip
	
	//Estadisticas-->BlackJack
	
	public VentanaAdminJuegos() {
		setTitle("Estadisticas de los juegos");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,700);
		setLocationRelativeTo(null);
		
		sup = new JLabel("Estadisticas sobre los juegos");
		btn = new JButton("Boton");
		btnInformes = new JButton("Crear Informe");
		
		//Ruleta
		Border lineaRuleta = BorderFactory.createLineBorder(Color.RED);
		Border tituloRuleta = BorderFactory.createTitledBorder(lineaRuleta,"Ruleta");
		//Crash
		Border lineaCrash = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloCrash = BorderFactory.createTitledBorder(lineaCrash,"Crash");
		//CoinFlip
		Border lineaCoinFlip = BorderFactory.createLineBorder(Color.BLACK);
		Border tituloCoinFlip = BorderFactory.createTitledBorder(lineaCoinFlip,"CoinFlip");
		//BlackJack
		Border lineaBlackJack = BorderFactory.createLineBorder(Color.RED);
		Border tituloBlackJack = BorderFactory.createTitledBorder(lineaBlackJack,"BlackJack");
		
		JPanel pSuperior = new JPanel(new FlowLayout());
			pSuperior.add(sup);
			pSuperior.setBackground(colorPanel);
		JPanel pBajo = new JPanel(new FlowLayout());
			pBajo.add(btn);
			pBajo.add(btnInformes);
			pBajo.setBackground(colorPanel);
		JPanel pJuegos = new JPanel(new GridLayout(2,2));
			JPanel pRuleta = new JPanel();
			JPanel pCrash = new JPanel();
			JPanel pCoinFlip = new JPanel();
			JPanel pBlackJack = new JPanel();
		
		pRuleta.setBorder(tituloRuleta);
		
		pCrash.setBorder(tituloCrash);
		
		pCoinFlip.setBorder(tituloCoinFlip);
		
		pBlackJack.setBorder(tituloBlackJack);
			
		pJuegos.add(pRuleta);	
		pJuegos.add(pCrash);	
		pJuegos.add(pCoinFlip);	
		pJuegos.add(pBlackJack);	
		
		setLayout(new BorderLayout());
		add(pSuperior, BorderLayout.NORTH);
		add(pBajo, BorderLayout.SOUTH);
		add(pJuegos, BorderLayout.CENTER);
		
		setVisible(true);
	}
}
