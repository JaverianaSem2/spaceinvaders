package mundo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisparoTest {

	private Disparo     disparo;
	private NaveJugador nave;
	private Enemigo     enemigo;
  private Partida     partida;

	// GOLPEA ENEMIGO
	private void setUpEscenario1 () {
		nave = new NaveJugador( "JugadorPrueba1", "Test1" );
    partida = new Partida( "JugadorPrueba1" );
    partida.setNivel( new Nivel( "0", 0, 0, 0, 0, 0 ) );
		enemigo = new InvasorCangrejo( partida.getNivel(), 100, 100);  //(0, 200, 200, 0, 12, 0);
		disparo = new Disparo( 100, 100 );
		nave.setDisparoUno( disparo );
	}

	// NO GOLPEA ENEMIGO
	private void setUpEscenario2 () {
		nave = new NaveJugador( "JugadorPrueba2", "Test2" );
    partida = new Partida( "JugadorPrueba2" );
    partida.setNivel( new Nivel( "0", 0, 0, 0, 0, 0 ) );
    enemigo = new InvasorCangrejo( partida.getNivel(), 200, 200);  //(0, 200, 200, 0, 12, 0);
		disparo = new Disparo( 100, 100 );
		nave.setDisparoUno( disparo );
	}

	private void setUpEscenario3 () {

    partida = new Partida( "JugadorPrueba3" );
    partida.setNivel( new Nivel( "0", 0, 0, 0, 0, 0 ) );
		enemigo = new InvasorPulpo( partida.getNivel(), 190, 0); //new InvasorPulpo(0, 190, 0, 0, 12,0);
		disparo = new Disparo( 190, 0 );
		enemigo.setDisparoUno( disparo );

		nave = new NaveJugador( "JugadorPrueba3", "Test3" );
		nave.setPosInicialX( 190 );
		nave.setPosInicialY( 410 );
	}

	@Test void testHitEnemigoNull () {
		setUpEscenario1();
		assertFalse( disparo.hitsEnemigo( null ) );
	}

	@Test void testHitEnemigo () {
		setUpEscenario1();
		assertTrue( disparo.hitsEnemigo( enemigo ) );
	}

	@Test void testNoHitEnemigo () {
		setUpEscenario2();
		assertFalse( disparo.hitsEnemigo( enemigo ) );
	}

	@Test void testHitJugadorReturnTrueAllConditions () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( 0 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 0 );
		assertTrue( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition1 () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 6 );
		nave.setPosInicialY( 6 );
		disparo.setPosX( 40 );
		nave.setPosInicialX( 6 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition2 () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 1 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( 0 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition1y2 () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 1 );
		disparo.setPosX( 40 );
		nave.setPosInicialX( 6 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition3a () {
		setUpEscenario3();

		nave.setAncho( 37 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 0 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( -35 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition3b () {
		setUpEscenario3();

		nave.setAncho( 38 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 0 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( 10 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition1y3a () {
		setUpEscenario3();

		nave.setAncho( 0 );
		disparo.setPosY( 6 );
		nave.setPosInicialY( 6 );
		disparo.setPosX( 50 );
		nave.setPosInicialX( 6 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorReturnFalseCondition2y3a () {
		setUpEscenario3();

		nave.setAncho( 37 );
		disparo.setPosY( 0 );
		nave.setPosInicialY( 1 );
		disparo.setPosX( 0 );
		nave.setPosInicialX( -35 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

	@Test void testHitJugadorNull () {
		setUpEscenario3();

		disparo.setPosY( 410 );
		assertFalse( disparo.hitsJugador( null ) );
	}

	@Test void testNoHitJugador () {
		setUpEscenario3();

		disparo.setPosY( 100 );
		assertFalse( disparo.hitsJugador( nave ) );
	}

}
