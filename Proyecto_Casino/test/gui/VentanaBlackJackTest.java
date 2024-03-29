package gui;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import domain.Carta;
import domain.Usuario;

public class VentanaBlackJackTest {
	private VentanaBlackJack vBlack;
	private Usuario user = new Usuario();

	@Before
	public void setUp(){
		vBlack = new VentanaBlackJack(user);
		
	}
	
	@Test
	public void testCrearBaraja() {
		Map<String,List<String>> mapaCartas = vBlack.crearMapaBaraja();
		
		assertEquals(mapaCartas, vBlack.crearMapaBaraja());
	}
	
	
	@Test
	public void testCrearBarajaCartas() {
		Map<String,List<String>> mapaCartas = vBlack.crearMapaBaraja();
		List<Carta> barajaCartas = vBlack.crearBarajaCartas(mapaCartas);
		
		assertEquals(barajaCartas, vBlack.crearBarajaCartas(mapaCartas));
	}
	
	@Test
	public void testBarajarCartas() {
		Map<String,List<String>> mapaCartas = vBlack.crearMapaBaraja();
		List<Carta> barajaCartas = vBlack.crearBarajaCartas(mapaCartas);
		List<Carta> listaEsperada = vBlack.BarajarCartas(barajaCartas);
		
		assertEquals(listaEsperada.size(), vBlack.crearBarajaCartas(mapaCartas).size());
	}
	@Test
	public void testRepartirCarta() {
		Map<String,List<String>> mapaCartas = vBlack.crearMapaBaraja();
		List<Carta> barajaCartas = vBlack.crearBarajaCartas(mapaCartas);
		List<Carta> cartasBarajadas = vBlack.BarajarCartas(barajaCartas);
		int tamañoOriginal = cartasBarajadas.size();
		Carta carta = vBlack.RepartirCarta(cartasBarajadas);
		
		assertEquals(tamañoOriginal-1,cartasBarajadas.size());
		assertFalse(cartasBarajadas.contains(carta));
		
		
	}
	
	
	
	


}
