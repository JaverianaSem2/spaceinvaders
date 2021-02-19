package mundo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemigoTest {

	private Enemigo enemigo;
	
	// MOVER - MOVER ABAJO - BORDE(EDGE)
	private void setUpEscenario1 () {
		enemigo = (InvasorCalamar) new InvasorCalamar(5, 300, 210, 0, 0, 0, 0, "", "");
	}

	@Test
	void testMover () {
		setUpEscenario1();
		assertEquals( 300, enemigo.getPosX() );
		
		// Mover hacia la derecha
		enemigo.mover(1);
		assertTrue(enemigo.getPosX() > 300);
		
		// Mover hacia la izquierda
		enemigo.mover(-1);
		assertTrue(enemigo.getPosX() <= 300);
		
		enemigo.mover(-1);
		assertTrue(enemigo.getPosX() < 300);
	}

	@Test
	void testMoverAbajo () {
		setUpEscenario1();
		assertEquals( 210, enemigo.getPosY() );
		
		enemigo.moverAbajo(1);
		assertTrue(enemigo.getPosY() > 210);
		
		enemigo.moverAbajo(1);
		assertEquals( 230, enemigo.getPosY() );
	}

	@Test
	void testEdge () {
		setUpEscenario1();
		
		enemigo.setPosX(600);
		assertTrue(enemigo.edge());
		
		enemigo.setPosX(100);
		assertFalse(enemigo.edge());

		enemigo.setPosX(-1);
		assertTrue(enemigo.edge());
	}
	
}
