package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ApostarSeguroTest {
	
	private ApostarSeguro apostarSeguro;
	
	
	
	@Before 
	public void setUp() {
		apostarSeguro = new ApostarSeguro("100","2000","6000","nick");
	}
	
	@Test
	public void testGetDiario() {
		assertEquals("100", apostarSeguro.getDiario());
	}
	
	@Test
	public void testSetDiario() {
	    apostarSeguro.setDiario("200");;
		assertEquals("200", apostarSeguro.getDiario());
	}
	
	@Test
	public void testGetSemanal() {
		assertEquals("2000", apostarSeguro.getSemanal());
	}
	
	@Test
	public void testSetSemanal() {
		apostarSeguro.setSemanal("3000");
		assertEquals("3000", apostarSeguro.getSemanal());
	}
	
	@Test
	public void testGetMensual() {
		assertEquals("6000", apostarSeguro.getMensual());
	}
	
	@Test
	public void testSetMensual() {
		apostarSeguro.setSemanal("7000");
		assertEquals("7000", apostarSeguro.getMensual());
	}
	
	/*@Test
	public void testtoString() {
		assertEquals(String.format("%s,%s,%s", apostarSeguro.getDiario(),apostarSeguro.getSemanal(),apostarSeguro.getMensual()));
		
	}*/


}
