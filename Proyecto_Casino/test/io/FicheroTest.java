package io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FicheroTest {
	
	@Test
	public void testLeer() {
		assertTrue(Fichero.leerFichero("resources/data/Informe.txt")!= null);
	}
}
