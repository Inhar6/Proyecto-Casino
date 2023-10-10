package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class VentanaInicial extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private JButton bLogin = new JButton ("Login");
	private JButton bSingUp = new JButton ("SingUp");
	private String[] juegos = {"","Black Jack", "Crash", "Ruleta", "Coin-Flip"};
	private JComboBox<String> comboJuegos = new JComboBox<>(juegos);
	
	private ImageIcon logoCasino = new ImageIcon("foto/iconos/logo-no-background.png");
	private ImageIcon logoCasinoPequeño = new ImageIcon("foto/iconos/logoCasinoPequeño.png");

	private JButton bCasino = new JButton ();
	
	private JPanel pMenu = new JPanel (new BorderLayout());
	private JPanel pMenuN = new JPanel (new BorderLayout());
	private JPanel pMenuN2 = new JPanel (new BorderLayout());
	private JPanel pMenuN3 = new JPanel (new BorderLayout());
	private JPanel pMenuC = new JPanel (new BorderLayout());
	private JLabel label = new JLabel();
	
	
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("Casino");
		setVisible(true);

		add(pMenu);
		
		label.setIcon(redimensionarIcono(logoCasino, 300, 300));
//		bCasino.setIcon(logoCasinoPequeño);
//		bCasino.setBorder(null);
		
		pMenuC.setBorder(new EmptyBorder(0,250,100,0));
		pMenu.add(pMenuC,BorderLayout.CENTER);
		pMenuC.add(label, BorderLayout.SOUTH);
		
		pMenu.add(pMenuN, BorderLayout.NORTH);
		
		pMenuN.add(bLogin, BorderLayout.EAST);
		pMenuN.add(pMenuN2, BorderLayout.CENTER);
		pMenuN.add(bCasino, BorderLayout.WEST);
        //
		pMenuN.setBorder(new EmptyBorder(20, 25, 15, 25));
		pMenuN.setBackground(Color.LIGHT_GRAY);

		pMenuN2.setBorder(new EmptyBorder(0, 5, 0, 5));
		pMenuN2.setBackground(Color.LIGHT_GRAY);
		
		pMenuN3.setBorder(new EmptyBorder(0, 5, 0, 5));
		pMenuN3.setBackground(Color.LIGHT_GRAY);
		//
		pMenuN2.add(new JLabel("Juegos:"),BorderLayout.WEST);
		pMenuN2.add(bSingUp, BorderLayout.EAST);
		
		pMenuN2.add(pMenuN3,BorderLayout.CENTER);
		pMenuN3.add(comboJuegos,BorderLayout.WEST);

		bLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vl = new VentanaLogin();
			}
		});
	}
	public ImageIcon redimensionarIcono(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // escalar la imagen
		
		imageIcon = new ImageIcon(newimg);
		
		return(imageIcon);
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaInicial();				
			}
        });
	}
}
