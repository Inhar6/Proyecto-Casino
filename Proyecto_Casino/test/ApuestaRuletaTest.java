import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.ApuestaRuleta;

public class ApuestaRuletaTest {

	private ApuestaRuleta a;
	private ApuestaRuleta b;
	@Before
	public void setUp() {
		a = new ApuestaRuleta(1,"rojo",1,1,1,1);
		b = new ApuestaRuleta();
	}
	
	@Test
	public void testConstructorVacio() {
		assertEquals(0, b.getNumero());
	}
	@Test
	public void testGetNumero() {
		assertEquals(1, a.getNumero());
	}
	@Test
	public void testSetNumero() {
		a.setNumero(2);
		assertEquals(2, a.getNumero());
	}
	@Test
	public void testGetColor() {
		assertEquals("rojo", a.getColor());
	}
	@Test
	public void testSetColor() {
		a.setColor("verde");
		assertEquals("verde", a.getColor());
	}
	@Test
	public void testGetDocena() {
		assertEquals(1, a.getDocena());
	}
	@Test
	public void testSetDocena() {
		a.setDocena(2);
		assertEquals(2, a.getDocena());
	}
	@Test
	public void testGetFila() {
		assertEquals(1, a.getFila());
	}
	@Test
	public void testSetFila() {
		a.setFila(2);
		assertEquals(2, a.getFila());
	}
	@Test
	public void testGetRango() {
		assertEquals(1, a.getRango());
	}
	@Test
	public void testSetRango() {
		a.setRango(2);
		assertEquals(2, a.getRango());
	}
	@Test
	public void testGetPar() {
		assertEquals(1, a.getPar());
	}
	@Test
	public void testSetPar() {
		a.setPar(2);
		assertEquals(2, a.getPar());
	}
	@Test
	public void testToString() {
		assertEquals(String.format("%d color=%s, docena=%d, fila=%d, rango=%d, par=%d",1,"rojo",1,1,1,1), a.toString());
	}
}
