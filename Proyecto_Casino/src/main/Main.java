package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import db.DBManager;
import domain.CuentaBancaria;
import domain.Point;
import domain.Usuario;
import gui.VentanaInicial;

public class Main {
	
	public static List<Usuario> DBlstUsuarios = new ArrayList<>(); 
	public static List<CuentaBancaria> DBlstCuentaBancaria = new ArrayList<>(); 

	public static void main(String[] args) {
		
		
		
		System.out.println("Rellenando la lista de usuarios");
		System.out.println("Rellenando la lista de cuentas bancarias");
		DBlstUsuarios = DBManager.obtenerTodosLosUsuarios();
		DBlstCuentaBancaria = DBManager.obtenerTodasLasCuentasBancarias();
		System.out.println(DBManager.obtenerTodosLosUsuarios());
		System.out.println(DBManager.obtenerTodasLasCuentasBancarias());
		for(Usuario user : DBlstUsuarios) {
			user.addListaBalance(new Point(2, 3));
		}
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				new VentanaInicial();				
			}
        });
        
        System.out.println("Final del programa");
	}
}
