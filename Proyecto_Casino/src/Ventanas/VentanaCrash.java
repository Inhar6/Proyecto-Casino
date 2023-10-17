package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class VentanaCrash extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pCrash = new JPanel(new GridLayout());
	
	public VentanaCrash() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Crash");
		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		setIconImage(new ImageIcon("foto/iconos/favicon.png").getImage());

        // Añadir menuSuperior
		JPanel menuSuperior = new JPanel(new BorderLayout());
		JMenuBar menuBar1 = new JMenuBar();
		JMenu menu = new JMenu();
		VentanaPanelMenu menuGeneral = new VentanaPanelMenu(); // Crea una ventana "VenatanaPanelMenu"
        add(menuSuperior, BorderLayout.NORTH);
        setJMenuBar(menuBar1);
        // Añadir menuApostar
        JPanel menuInferior = new JPanel(new BorderLayout());
        add(menuInferior, BorderLayout.SOUTH);
        // Panel Crash
        add(pCrash, BorderLayout.CENTER);
    	
        JButton prueba = new JButton("HOLAAA");
        int numeroRandom = (int) (Math.random() * 100);
    	JProgressBar progressBar = new JProgressBar(0, numeroRandom);
    	pCrash.add(progressBar);
        pCrash.add(prueba, BorderLayout.SOUTH);
        // Crear una ProgressBar
        progressBar.setValue(0);
    	progressBar.setStringPainted(true);
        // Crear un hilo para simular el progreso
//        Thread progressThread = new Thread(() -> {
//            for (int i = 0; i <= numeroRandom; i++) {
//                try {
//                    Thread.sleep(numeroRandom); // Simular una operación en segundo plano
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                progressBar.setValue(i); // Actualizar el valor de progreso
//            }
//        });
//        Timer timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int randomValue = (int) (Math.random() * 101);
//                progressBar.setValue(randomValue);
//            }
//        });    
//        timer.start();

        // Iniciar el hilo
        Thread thread = new Thread(new Runnable() {
        	@Override
        	public void run() {
        		Random random = new Random();
        		int targetPercentage = random.nextInt(101);

        		for (int i = 0; i <= targetPercentage; i++) {
        			try {
        				Thread.sleep(100);
        			} catch (InterruptedException ex) {
        				ex.printStackTrace();
        			}
        			progressBar.setValue(i);
        		}

        		prueba.setEnabled(true);
        	}
        });
        prueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread.start();
            	startProgressBar();
            	
            }
        });

        // Mostrar VentanaMenuPanel
        menuGeneral.enseñarApostar(menuInferior);
        menuGeneral.enseñarMenu(menuSuperior, menu);
        // Contador ventanas abiertas
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                VentanaPanelMenu.contadorVentanaCrash = 0; // Reiniciar el contador
            }
        });
	}
	
	
	private void startProgressBar() {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args) {
		new VentanaCrash();
	}
}
