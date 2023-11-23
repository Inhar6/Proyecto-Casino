package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import db.DBManager;
import domain.Point;
import domain.Usuario;
import gui.VentanaInicial;

public class Main {
	
	public static List<Usuario> DBlstUsuarios = new ArrayList<>(); 
	public static void main(String[] args) {
		
		
		DBManager.connect(DBManager.URL);
		
		System.out.println("Rellenando la lista de usuarios");
		DBlstUsuarios = DBManager.obtenerTodosLosUsuarios();
		
		for(Usuario user : DBlstUsuarios) {
			user.addListaBalance(new Point(2, 3));
		}
		
		System.out.println(DBlstUsuarios);
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				new VentanaInicial();				
			}
        });
        
        DBManager.disconnect();
	}
}
