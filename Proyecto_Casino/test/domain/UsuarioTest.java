package domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class UsuarioTest {

	private Usuario u;
	private Usuario p;
	
	@Before
	public void setUp() {
		u = new Usuario("Manolo","Sainz", "123456789A","ManoSa","Contra",123456789,2000);
		p = new Usuario();
	}
	
	@Test
	public void testUsuarioVacio() {
		assertEquals(null, p.getNombre());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Manolo",u.getNombre() );
	}
	@Test
	public void testSetNombre() {
		u.setNombre("Juan");
		assertEquals("Juan",u.getNombre() );
	}
	@Test
	public void testGetApellido() {
		assertEquals("Sainz",u.getApellidos() );
	}
	@Test
	public void testSetApellido() {
		u.setApellidos("Lekue");
		assertEquals("Lekue",u.getApellidos() );
	}
	@Test
	public void testGetDNI() {
		assertEquals("123456789A",u.getDNI() );
	}
	@Test
	public void testSetDNI() {
		u.setDNI("1020304050Z");
		assertEquals("1020304050Z",u.getDNI() );
	}
	@Test
	public void testGetNombreUsuario() {
		assertEquals("ManoSa",u.getNombreUsuario() );
	}
	@Test
	public void testSetNombreUsuario() {
		u.setNombreUsuario("Manolete");
		assertEquals("Manolete",u.getNombreUsuario() );
	}
	@Test
	public void testGetContraseña() {
		assertEquals("Contra",u.getContraseña() );
	}
	@Test
	public void testSetContraseña() {
		u.setContraseña("ContraDos");
		assertEquals("ContraDos",u.getContraseña() );
	}
	@Test
	public void testGetNumeroCuenta() {
		assertEquals(123456789,u.getNumeroCuenta() );
	}
	@Test
	public void testSetNumeroCuenta() {
		u.setNumeroCuenta(102030405);
		assertEquals(102030405,u.getNumeroCuenta() );
	}
	@Test
	public void testGetSaldo() {
		assertEquals(2000,u.getSaldo(),1.0 );
	}
	@Test
	public void testSetSaldo() {
		u.setSaldo(12000);
		assertEquals(12000,u.getSaldo(),1.0 );
	}
	@Test
	public void testAddSaldo() {
		u.setSaldo(12000);
		u.addSaldo(3000.0);
		assertEquals(15000,u.getSaldo(),1.0 );
	}
	@Test 
	public void testToString() {
		assertEquals(String.format("%s, %s, %s, %s", u.getNombre(),u.getApellidos(), u.getDNI(), u.getNombreUsuario()), u.toString());
	}
	@Test
	public void testGetMapaRuleta() {
		assertTrue(u.getMapaRuleta().isEmpty());
	}
	@Test
	public void testSetMapaRuleta() {
		Map<Integer, Map<Integer,Double>> mapa = new HashMap<>();
		u.setMapaRuleta(mapa);
		assertEquals(mapa,u.getMapaRuleta() );
	}
	@Test
	public void testAddMapaRuleta() {
		u.addMapaRuleta(1, 2, 600);
		assertFalse(u.getMapaRuleta().isEmpty());
	}
	
	@Test
	public void testGetMapaCrash() {
		assertTrue(u.getMapaCrash().isEmpty());
	}
	@Test
	public void testSetMapaCrash() {
	 Map<Integer,Map<String, Map<Double, Double>>> mapaCrash = new HashMap<>();
		u.setMapaCrash(mapaCrash);
		assertEquals(mapaCrash,u.getMapaCrash() );
	}
	@Test
	public void testAddMapaCrash() {
		u.addMapaCrash(1, "2", 600,300);
		assertFalse(u.getMapaCrash().isEmpty());
	}
	
	@Test
	public void testGetMapaBlackJack() {
		assertTrue(u.getMapaBlackJack().isEmpty());
	}
	
	@Test
	public void testSetMapaBlackJack() {
	    Map<Integer,Map<String,Map<Integer,Double>>> mapaBlackJack = new HashMap<>();
		u.setMapaBlackJack(mapaBlackJack);
		assertEquals(mapaBlackJack,u.getMapaBlackJack() );
	}
	
	@Test
	public void testAddMapaBlackJack() {
		u.addMapaBlackJack(1, "2", 600,600);
		assertFalse(u.getMapaBlackJack().isEmpty());
	}
	
	
	@Test
	public void testGetMapaCoinFlip() {
		assertTrue(u.getMapaCoinFlip().isEmpty());
	}
	
	
	@Test
	public void testSetMapaCoinFlip() {
	    Map<Integer, Map<String, String >> mapaCoinFlip = new HashMap<>();
		u.setMapaCoinFlip(mapaCoinFlip);
		assertEquals(mapaCoinFlip,u.getMapaCoinFlip() );
	}
	
	
	@Test
	public void testAddMapaCoinFlip() {
		u.addMapaCoinFlip(1, "2", "600");
		assertFalse(u.getMapaCoinFlip().isEmpty());
	}
	
	
	@Test
	public void testGetLstBalance() {
		assertTrue(u.getLstBalance().isEmpty());
	}
	
	
	@Test
	public void testSetLstBalance() {
		List<Point> lstPuntos = new ArrayList<>();
		u.setLstBalance(lstPuntos);
		assertEquals(lstPuntos,u.getLstBalance() );
	}
	@Test
	public void testAddLstBalance() {
		u.addListaBalance(new Point(2, 4));
		assertFalse(u.getLstBalance().isEmpty());
	}
}
