package mundo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisparoTest {

	private Disparo disparo;
	private NaveJugador nave;
	private Enemigo enemigo;
	
	// GOLPEA ENEMIGO
	private void setUpEscenario1 () {
		nave = new NaveJugador("JugadorPrueba1", "Test1");
		enemigo = (InvasorCangrejo) new InvasorCangrejo(0, 100, 100, 0, 12, 0, 0, "", "");
		disparo = new Disparo(100, 100);
		nave.setDisparoUno(disparo);
	}
	
	// NO GOLPEA ENEMIGO
	private void setUpEscenario2 () {
		nave = new NaveJugador("JugadorPrueba2", "Test2");
		enemigo = (InvasorCangrejo) new InvasorCangrejo(0, 200, 200, 0, 12, 0, 0, "", "");
		disparo = new Disparo(100, 100);
		nave.setDisparoUno(disparo);
	}

	private void setUpEscenario3 () {
		enemigo = (InvasorPulpo) new InvasorPulpo(0, 190, 0, 0, 12, 0, 0, "", "");
		disparo = new Disparo(190, 0);
		enemigo.setDisparoUno(disparo);
		
		nave = new NaveJugador("JugadorPrueba3", "Test3");
		nave.setPosInicialX(190);
		nave.setPosInicialY(410);
	}

	@Test
	void testHitEnemigoNotNull () {
		setUpEscenario1();
		assertTrue(disparo.hitsEnemigo(enemigo));
	}

	@Test
	void testHitEnemigoNull () {
		setUpEscenario1();
		assertFalse(disparo.hitsEnemigo(null));
	}

	@Test
	void testNoHitEnemigo () {
		setUpEscenario2();
		assertFalse(disparo.hitsEnemigo(enemigo));
	}

	@Test
	void testHitJugadorReturnTrueAllConditions () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosX(0);
		nave.setPosInicialX( 0 );
		disparo.setPosY(0);
		nave.setPosInicialY( 0 );
		assertTrue(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition1 () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY(6);
		nave.setPosInicialY( 6 );
		disparo.setPosX(40);
		nave.setPosInicialX( 6 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition2 () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 0);
		nave.setPosInicialY( 1 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( 0 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition1y2 () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 0);
		nave.setPosInicialY( 1 );
		disparo.setPosX( 40 );
		nave.setPosInicialX( 6 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition3a () {
		setUpEscenario3();

		nave.setAncho( 37 );
		disparo.setPosY( 0);
		nave.setPosInicialY( 0 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( -35 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition3b () {
		setUpEscenario3();

		nave.setAncho( 38 );
		disparo.setPosY( 0);
		nave.setPosInicialY( 0 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( 10 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition1y3a () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 6 );
		nave.setPosInicialY( 6 );
		disparo.setPosX( 50 );
		nave.setPosInicialX( 6 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalseCondition2y3a () {
		setUpEscenario3();

		nave.setAncho( 37 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 1 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( -35 );
		assertFalse(disparo.hitsJugador(nave));
	}

	@Test
	void testHitJugadorReturnFalse () {
		setUpEscenario3();

		disparo.setPosY(410);
		assertFalse ( disparo.hitsJugador ( null ) );
	}

	@Test
	void testNoHitJugador () {
		setUpEscenario3();
		
		disparo.setPosY(100);
		assertFalse(disparo.hitsJugador(nave));
	}
	
}
