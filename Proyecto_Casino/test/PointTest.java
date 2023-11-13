import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Point;

public class PointTest {
	
	private Point p;
	@Before
	public void setUp() {
		p = new Point(1, 2);
	}
	@Test
	public void testGetX() {
		assertEquals(1, p.getX());
	}
	@Test
	public void testGetY() {
		assertEquals(2, p.getY());
	}
}
