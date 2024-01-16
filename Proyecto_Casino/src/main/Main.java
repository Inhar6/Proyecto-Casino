package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import db.DBManager;
import domain.Usuario;
import gui.VentanaInicial;


public class Main {
	
	public static List<Usuario> DBlstUsuarios = new ArrayList<>(); 
	
	public Main() {
		DBlstUsuarios = DBManager.obtenerTodosLosUsuarios();
		new VentanaInicial();
       
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Main();
				
			}
		});
	}
	
	
}
