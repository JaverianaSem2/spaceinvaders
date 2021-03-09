package mundo;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnemigoTest {

	private Enemigo enemigo;
  private Partida partida;

	// MOVER - MOVER ABAJO - BORDE(EDGE)
	private void setUpEscenario1 () {
    partida = new Partida( "setUpEscenario1" );
    partida.setNivel( new Nivel( "5", 5, 0, 0, 0, 0 ) );

    InvasorFabrica fabrica;

    fabrica = new InvasorFabrica();
    fabrica.setNivel( partida.getNivel() );

		enemigo = fabrica.crearInvasor( "Calamar", 300, 210);
	}

	@Test void testMover () {
		setUpEscenario1();
		assertEquals( 300, enemigo.getPosX() );

		// Mover hacia la derecha
		enemigo.mover( 1 );
		assertEquals( 305, enemigo.getPosX() );

		// Mover hacia la izquierda
		enemigo.mover( -1 );
		assertEquals( 300, enemigo.getPosX() );

		enemigo.mover( -1 );
		assertEquals( 295, enemigo.getPosX() );
	}

	@Test void testMoverAbajo () {
		setUpEscenario1();
		assertEquals( 210, enemigo.getPosY() );

		enemigo.moverAbajo( 1 );
		assertEquals( 220, enemigo.getPosY() );

		enemigo.moverAbajo( 1 );
		assertEquals( 230, enemigo.getPosY() );
	}

	@Test void testEdge () {
		setUpEscenario1();

		enemigo.setPosX( 600 );
		assertTrue( enemigo.edge() );

		enemigo.setPosX( 100 );
		assertFalse( enemigo.edge() );

		enemigo.setPosX( -1 );
		assertTrue( enemigo.edge() );
	}


  @Test void testCuandoEnemigoNoExisteDevuelveRamdom () {
    partida = new Partida( "setUpEscenario2" );
    partida.setNivel( new Nivel( "2", 0, 0, 0, 0, 0 ) );
    InvasorFabrica fabrica;
    fabrica = new InvasorFabrica();
    fabrica.setNivel( partida.getNivel() );

    enemigo = fabrica.crearInvasor( "NoExisto", 300, 210);
    assertNotNull( enemigo );
  }

  @Test void testCrearEnemigoCangrejo () {
    partida = new Partida( "setUpEscenario2" );
    partida.setNivel( new Nivel( "2", 0, 0, 0, 0, 0 ) );
    InvasorFabrica fabrica;
    fabrica = new InvasorFabrica();
    fabrica.setNivel( partida.getNivel() );

    enemigo = fabrica.crearInvasor( "Cangrejo", 300, 210);


    assertThat( enemigo, instanceOf(InvasorCangrejo.class));

  }

  @Test void testCrearEnemigoPulpo () {
    partida = new Partida( "setUpEscenario2" );
    partida.setNivel( new Nivel( "2", 0, 0, 0, 0, 0 ) );
    InvasorFabrica fabrica;
    fabrica = new InvasorFabrica();
    fabrica.setNivel( partida.getNivel() );

    enemigo = fabrica.crearInvasor( "Pulpo", 300, 210);


    assertThat( enemigo, instanceOf(InvasorPulpo.class));

  }

  @Test void testCrearEnemigoCalamar () {
    partida = new Partida( "setUpEscenario2" );
    partida.setNivel( new Nivel( "2", 0, 0, 0, 0, 0 ) );
    InvasorFabrica fabrica;
    fabrica = new InvasorFabrica();
    fabrica.setNivel( partida.getNivel() );

    enemigo = fabrica.crearInvasor( "Calamar", 300, 210);


    assertThat( enemigo, instanceOf(InvasorCalamar.class));

  }

}
