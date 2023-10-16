package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaPanelApostar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
    // PanelApostar
	private JPanel pApostar = new JPanel(new BorderLayout());
	private JPanel pApostarW = new JPanel(new BorderLayout());
	private JPanel pApostarE = new JPanel(new BorderLayout());
	
	private JPanel pBotonApuesta = new JPanel(new BorderLayout());
	private JPanel pBotonEliminarApuesta = new JPanel(new BorderLayout());
	private JPanel pLabelApuesta = new JPanel(new BorderLayout());
	
	private JPanel p1y10 = new JPanel(new BorderLayout());
	private JPanel p25y50 = new JPanel(new BorderLayout());
	private JPanel p100y1000 = new JPanel(new BorderLayout());
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p10 = new JPanel(new BorderLayout());
	private JPanel p25 = new JPanel(new BorderLayout());
	private JPanel p50 = new JPanel(new BorderLayout());
	private JPanel p100 = new JPanel(new BorderLayout());
	private JPanel p1000 = new JPanel(new BorderLayout());

	private int apuesta;
	private JLabel lApuesta = new JLabel("Apuesta: " +  apuesta);
    private JButton bApostar = new JButton("Apostar");
    private JButton bEliminarApuesta = new JButton("Eliminar apuesta");
    // Fichas
    private JButton ficha1 = new JButton("1");
    private JButton ficha10 = new JButton("10");
    private JButton ficha25 = new JButton("20");
    private JButton ficha50 = new JButton("50");
    private JButton ficha100 = new JButton("100");
    private JButton ficha1000 = new JButton("1K");

    public void enseñarApostar(JPanel panel) {
    	Color colorPanel = new Color(71, 113, 72);

    	panel.add(pApostar);

        // PanelApostar
        pApostar.add(pApostarW, BorderLayout.WEST);
        pApostar.add(pApostarE, BorderLayout.EAST);
        
        // Panel pApostarW
        pApostarW.add(p1y10,BorderLayout.WEST);
        pApostarW.add(p25y50,BorderLayout.CENTER);
        pApostarW.add(p100y1000,BorderLayout.EAST);
        p1y10.add(p1,BorderLayout.WEST);
        p1y10.add(p10,BorderLayout.EAST);
        p25y50.add(p25,BorderLayout.WEST);
        p25y50.add(p50,BorderLayout.EAST);
        p100y1000.add(p100,BorderLayout.WEST);
        p100y1000.add(p1000,BorderLayout.EAST);
        p1.add(ficha1);
        p10.add(ficha10);
        p25.add(ficha25);
        p50.add(ficha50);
        p100.add(ficha100);
        p1000.add(ficha1000);
        p1.setBorder(new EmptyBorder(0,0,0,10)); p1.setBackground(colorPanel);
        p10.setBorder(new EmptyBorder(0,10,0,10)); p10.setBackground(colorPanel);
        p25.setBorder(new EmptyBorder(0,10,0,10)); p25.setBackground(colorPanel);
        p50.setBorder(new EmptyBorder(0,10,0,10)); p50.setBackground(colorPanel);
        p100.setBorder(new EmptyBorder(0,10,0,10)); p100.setBackground(colorPanel);
        p1000.setBorder(new EmptyBorder(0,10,0,10)); p1000.setBackground(colorPanel);
        

        // Panel pApostarE
        pApostarE.add(pLabelApuesta, BorderLayout.WEST);
        pApostarE.add(pBotonEliminarApuesta, BorderLayout.CENTER);
        pApostarE.add(pBotonApuesta, BorderLayout.EAST);
        pLabelApuesta.add(lApuesta);
        pBotonEliminarApuesta.add(bEliminarApuesta);
        pBotonApuesta.add(bApostar);
        
        pApostar.setBackground(colorPanel); pApostar.setBorder(new EmptyBorder(20, 20, 20, 20));
        pApostarW.setBackground(colorPanel);
        pApostarE.setBackground(colorPanel);
        pBotonApuesta.setBackground(colorPanel);
        pBotonEliminarApuesta.setBackground(colorPanel); pBotonEliminarApuesta.setBorder(new EmptyBorder(0, 0, 0, 10));
        pLabelApuesta.setBackground(colorPanel); pLabelApuesta.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        // Aumenta el tamaño de la fuente de un JLabel
        Font fuente = lApuesta.getFont();
        Font nuevaFuente = new Font(fuente.getName(), Font.PLAIN, 18);
        lApuesta.setFont(nuevaFuente);
        
        ficha1.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 1;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
        ficha10.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 10;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
        ficha25.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 25;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
        ficha50.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 50;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
        ficha100.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 100;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
        ficha1000.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta += 1000;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
        bEliminarApuesta.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				apuesta = 0;
				lApuesta.setText("Apuesta: " + apuesta);
			}
		});
    }
    
    
}
