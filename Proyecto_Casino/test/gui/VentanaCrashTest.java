package gui;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class VentanaCrashTest {

	@Test
	public void testCalcularGanado(){
		double numeroFinal = 2.5;
		double apuesta = 10;
		
		double resultadoEsperado = numeroFinal * apuesta;
		double resultadoReal = VentanaCrash.calcularGanado(numeroFinal, apuesta);
		
		assertEquals(resultadoEsperado, resultadoReal, 0.01);
	}
	
	@Test
	public void testObtenerResultadoWin() {
		assertEquals(true, VentanaCrash.obtenerResultado(true, false, 2.4));
	}	@Test
	public void testObtenerResultadoLose() {
		assertEquals(true, VentanaCrash.obtenerResultado(false, true, 0.0));
	}
	
	@Test
	public void testObetenerMultiplicador() {
		int numeroX = 3;
		int segundos = 56;
		
		double multiEsperado = numeroX + (segundos/100.0);
		double multiReal = VentanaCrash.obtenerMultiplicador(numeroX, segundos);
		assertEquals(multiEsperado, multiReal, 0.01);
	}
	
}
