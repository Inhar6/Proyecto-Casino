package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import db.DBManager;
import domain.ApostarSeguro;
import domain.Usuario;
import gui.VentanaInicial;
import io.Fichero;

public class Main {
	
	public static List<Usuario> DBlstUsuarios = new ArrayList<>(); 
	public static List<ApostarSeguro> lstApostarSeguro = new ArrayList<>();
	
	public Main() {
		DBlstUsuarios = DBManager.obtenerTodosLosUsuarios();
		lstApostarSeguro = Fichero.leerLimitesJugadores();
		System.out.println(lstApostarSeguro);
		for(Usuario user:DBlstUsuarios) {
			for(ApostarSeguro as : lstApostarSeguro) {
				if(user.getNombreUsuario() == as.getNick()) {
					user.setAs(as);
				}
			}
		}
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
