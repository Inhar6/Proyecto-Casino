package Ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
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

public class VentanaCoinFlip extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaCoinFlip() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Coin-Flip");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());

        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new GridLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu();
        add(menuSuperior);
        setJMenuBar(menuBar1);
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaBlackJack = 0; // Reiniciar el contador
            }
        });
      //botones 
      	JButton bCara = new JButton("Cara");
      	JButton bCruz = new JButton("Cruz");
      	
      	JButton bBorrarSelec = new JButton("Borrar Seleccion");
      	JButton b1_2 = new JButton("1/2");
      	JButton bx2 = new JButton("x2");
      	JButton bMax = new JButton("Max");
      		
      	JButton bJugar = new JButton("Jugar");
      		
      	//lista
      	DefaultListModel<String> dlmHistorial = new DefaultListModel<>();
      	JList<String> lstHistorial = new JList<>(dlmHistorial);
      	JScrollPane scroll = new JScrollPane(lstHistorial);
      		
      	//paneles
      	JPanel pInferior = new JPanel();
      	JPanel pInferior1 = new JPanel();
      	JPanel pInferior2 = new JPanel();
      	JPanel pJuego = new JPanel();
      	JPanel pCentro = new JPanel();
      	JPanel pHisto = new JPanel();
      		
      	/////////////////////////////////////////
      		
      	pInferior1.setLayout(new GridLayout(1,2));
      	pInferior1.add(bCara);
      	pInferior1.add(bCruz);
      		
      	pInferior2.setLayout(new GridLayout(1,4));
      	pInferior2.add(bBorrarSelec);
      	pInferior2.add(b1_2);
      	pInferior2.add(bx2);
      	pInferior2.add(bMax);
      		
      	pInferior.setLayout(new GridLayout(2, 1));
      	pInferior.add(pInferior1);
      	pInferior.add(pInferior2);
      		
      	pCentro.setLayout(new BorderLayout());
      	pCentro.add(pInferior,BorderLayout.SOUTH);
      		
      	pJuego.setLayout(new FlowLayout());
      	pJuego.add(bJugar);
      		
      	pHisto.setLayout(new GridLayout(2,1));
      	pHisto.add(new JPanel());
      	pHisto.add(new JPanel());
      	pHisto.add(scroll);
      	pHisto.add(new JPanel());
      	pHisto.add(new JPanel());
      	pHisto.add(pJuego);
      		
      	JPanel menuHisto = new JPanel();
      	menuHisto.setLayout(new GridLayout(2,1));
      	menuHisto.add(menuSuperior);
      	menuHisto.add(pHisto);
      		
      	JPanel coin = new JPanel();
      	coin.setLayout(new GridLayout(2, 1));
      	coin.add(menuHisto);
      	coin.add(pCentro);
      		
      	add(coin);

        menuGeneral.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
	}
}
