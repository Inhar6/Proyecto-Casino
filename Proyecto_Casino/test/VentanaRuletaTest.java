import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gui.VentanaRuleta;

public class VentanaRuletaTest {
	
	@Test
	public void testObtenerDocena1() {
		assertEquals(1, VentanaRuleta.obtenerDocena(12));
	}
	@Test
	public void testObtenerDocena2() {
		assertEquals(2, VentanaRuleta.obtenerDocena(21));
	}
	@Test
	public void testObtenerDocena3() {
		assertEquals(3, VentanaRuleta.obtenerDocena(30));
	}
	@Test
	public void testObtenerFila3() {
		assertEquals(3, VentanaRuleta.obtenerFila(3));
	}
	@Test
	public void testObtenerFila2() {
		assertEquals(2, VentanaRuleta.obtenerFila(2));
	}
	@Test
	public void testObtenerFila1() {
		assertEquals(1, VentanaRuleta.obtenerFila(1));
	}
	@Test
	public void testObtenerRango1() {
		assertEquals(1, VentanaRuleta.obtenerRango(15));
	}
	@Test
	public void testObtenerRango2() {
		assertEquals(2, VentanaRuleta.obtenerRango(30));
	}
	@Test
	public void testObtenerRango0() {
		assertEquals(0, VentanaRuleta.obtenerRango(0));
	}
	@Test
	public void testObtenerPar1() {
		assertEquals(1, VentanaRuleta.obtenerPar(2));
	}
	@Test
	public void testObtenerPar2() {
		assertEquals(2, VentanaRuleta.obtenerPar(3));
	}
	@Test
	public void testObtenerPar0() {
		assertEquals(0, VentanaRuleta.obtenerPar(0));
	}
	//Colores con Integer
	@Test
	public void testObtenerColorVerde() {
		assertEquals(1, VentanaRuleta.obtenerColor2(0));
	}
	@Test
	public void testObtenerColorRojo() {
		assertEquals(2, VentanaRuleta.obtenerColor2(3));
	}
	@Test
	public void testObtenerColorNegro() {
		assertEquals(3, VentanaRuleta.obtenerColor2(2));
	}
	//Colores con String
	@Test
	public void testObtenerColorVerdeString() {
		assertEquals("verde", VentanaRuleta.obtenerColor(0));
	}
	@Test
	public void testObtenerColorRojoString() {
		assertEquals("rojo", VentanaRuleta.obtenerColor(3));
	}
	@Test
	public void testObtenerColorNegroString() {
		assertEquals("negro", VentanaRuleta.obtenerColor(2));
	}
	@Test
	public void testAvisoSaldo() {
		assertFalse(VentanaRuleta.avisoSaldo(1000));
		assertTrue(VentanaRuleta.avisoSaldo(-100));
	}
}
