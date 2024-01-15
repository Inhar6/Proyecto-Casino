package gui;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gui.VentanaCoinFlip;

public class VentanaCoinFlipTest {

	@Test
	public void testObtenerCara(){
		assertEquals(true, VentanaCoinFlip.obtenerCara("Cara"));
	}
	
	@Test
	public void testObtenerCruz(){
		assertEquals(true, VentanaCoinFlip.obtenerCruz("Cruz"));
	}
	
	@Test
	public void testObtenerEnabledCara(){
		assertEquals(true, VentanaCoinFlip.obtenerEnabledCara(true));
	}
	
	@Test
	public void testObtenerEnabledCruz(){
		assertEquals(true, VentanaCoinFlip.obtenerEnabledCruz(true));
	}
}
