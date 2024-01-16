package gui;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

public class Tablas {
	
	//Limpiado de tabla
	public static void limpiarTabla(DefaultTableModel dtmJuegos) {
		dtmJuegos.setRowCount(0);
		dtmJuegos.setColumnCount(0);
	}
	//Pintado de tabla Ruleta
	public static void pintadoRuleta(DefaultTableModel dtmJuegos ,Map<Integer, Map<Integer, Double>> mapa) {
		dtmJuegos.addColumn("Tirada");
		dtmJuegos.addColumn("Resultado");
		dtmJuegos.addColumn("Ganancia");
		for(Entry<Integer, Map<Integer, Double>> entry :mapa.entrySet()) {
			int tirada =entry.getKey();
			for(Integer i : entry.getValue().keySet()) {
				int resultado = i;
				double ganancia = entry.getValue().get(i);
				dtmJuegos.addRow(new Object[] {tirada,resultado,ganancia});
			}
			
		}
	}
	//Pintado de tabla Coin-flip
	public static void pintadoCoinFlip(DefaultTableModel dtmJuegos, Map<Integer, Map<String, String >> mapaCoinFlip ) {
		dtmJuegos.addColumn("Tirada");
		dtmJuegos.addColumn("Apuesta");
		dtmJuegos.addColumn("Resultado");
		for(Entry<Integer, Map<String, String>> entry : mapaCoinFlip.entrySet()) {
			int tirada = entry.getKey();
			for(String i: entry.getValue().keySet()) {
				String resultado = i;
				String apuesta = entry.getValue().get(i);
				dtmJuegos.addRow(new Object[] {tirada, apuesta ,resultado});
			}
		}
		
	}
	//Pintado de tabla Crash
	public static void pintadoCrash(DefaultTableModel dtmJuegos, Map<Integer, Map<String, Map<Double, Double>>> mapaCrash) {
		dtmJuegos.addColumn("Tirada");
		dtmJuegos.addColumn("Resultado");
		dtmJuegos.addColumn("Multiplicador");
		dtmJuegos.addColumn("Ganancia");
		for(Entry<Integer,Map<String, Map<Double, Double>>> entry: mapaCrash.entrySet()) {
			int tirada = entry.getKey();
			Map<String, Map<Double, Double>> detallesTirada = entry.getValue();
			for(Entry<String, Map<Double, Double>> entry2: detallesTirada.entrySet()) {
				String resultado = entry2.getKey();
				for(Double i: entry2.getValue().keySet()) {
					double muliplicador = i;
					double ganancia = entry2.getValue().get(i);
					dtmJuegos.addRow(new Object[] {tirada, resultado, muliplicador, ganancia});
				}
			}
		}
	}
	//Pintado de tabla BlackJack
	public static void pintadoBlackJack(DefaultTableModel dtmJuegos,Map<Integer, Map<String, Map<Integer, Double>>> mapaBlackJack) {
		dtmJuegos.addColumn("Partida");
		dtmJuegos.addColumn("Resultado");
		dtmJuegos.addColumn("Puntuacion");
		dtmJuegos.addColumn("Ganancia");
		for(Entry<Integer,Map<String, Map<Integer, Double>>> entry: mapaBlackJack.entrySet()) {
			int tirada = entry.getKey();
			Map<String, Map<Integer, Double>> detallesTirada = entry.getValue();
			for(Entry<String, Map<Integer, Double>> entry2: detallesTirada.entrySet()) {
				String resultado = entry2.getKey();
				for(int i: entry2.getValue().keySet()) {
					int muliplicador = i;
					double ganancia = entry2.getValue().get(i);
					dtmJuegos.addRow(new Object[] {tirada, resultado, muliplicador, ganancia});
				}
			}
		}
	}
	
}
