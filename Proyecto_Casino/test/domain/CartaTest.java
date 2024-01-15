package domain;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Carta;

public class CartaTest {
	private Carta c;
	
	@Before 
	public void setUp() {
		c = new Carta("9","Rombos");
	}
	
	@Test
	public void testGetNumeros() {
		assertEquals("9", c.getNumero());
	}
	
	@Test
	public void testSetNumeros() {
		c.setNumero("9");
		assertEquals("9", c.getNumero());
	}
	
	@Test
	public void testGetPalo() {
		assertEquals("Rombos", c.getPalo());
	}
	
	@Test
	public void testSetPalo() {
		c.setPalo("Rombos");
		assertEquals("Rombos", c.getPalo());
	}
	
	@Test
	public void testtoString() {
		assertEquals(String.format("%s-%s", c.getNumero(),c.getPalo()), c.toString());
	}

}
