package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.VentanaCoinFlip;
import io.Propiedades;

public class ControladorVentanaCoinFlip extends WindowAdapter implements ActionListener {

	@Override
	public void windowClosing(WindowEvent e) {

		VentanaCoinFlip v = (VentanaCoinFlip) e.getWindow();

		Propiedades x = v.getPropiedades();

		x.guardar();

		v.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String accion = e.getActionCommand();

		// tienes que saber que actionCommand esta llegando
		// hay que asignar el actionCommand a cada boton de la ventana
		if (accion.equals("Cara")) {
			//e.getSource(VentanaCoinFlip.bCara);
			
			System.out.println("salio cara");
		}
		if (accion.equals("Cruz")) {
			
			System.out.println("salio cruz");
		}
		if (accion.equals("Borrar seleccion")) {

			System.out.println("salio borrar seleccion");
		}
	}

}
