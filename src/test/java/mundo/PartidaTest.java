package mundo;

import java.io.IOException;
import java.util.Arrays;

import excepciones.PartidaYaExisteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {

	private Partida     partida;
	private NaveJugador jugador;
	private Enemigo[][] enemigos;

	// AGREGAR PARTIDA - BUSCAR PARTIDA
	private void setUpEscenario1 () {
		partida = new Partida( "prueba_AgregarPartida_BuscarPartida" );
		jugador = new NaveJugador( "Manuel", "Escenario1" );
	}

	// AGREGAR PARTIDA - BUSCAR PARTIDA
	private void setUpEscenario2 () {
		partida = new Partida( "test2" );
		jugador = new NaveJugador( "Manuel", "Escenario2" );
		Partida p1 = new Partida( "test2.1" );
		Partida p2 = new Partida( "test2.2" );
		Partida p3 = new Partida( "test2.3" );

		try {
			jugador.agregarPartida( partida );
			jugador.agregarPartida( p1 );
			jugador.agregarPartida( p2 );
			jugador.agregarPartida( p3 );
		} catch ( PartidaYaExisteException ignored ) {
			// Do nothing
		}
	}

	// INICIAR ENEMIGOS
	private void setUpEscenario3 () {
		partida = new Partida( "prueba_IniciarEnemigos" );
		enemigos = new Enemigo[5][10];
		partida.setEnemigos( enemigos );
	}

	// ELIMINAR UN ENIMIGO
	private void setUpEscenario4 () {
		partida = new Partida( "prueba_EliminarEnemigo" );
		enemigos = new Enemigo[2][3];
		partida.setEnemigos( enemigos );

		for ( int i = 0; i < enemigos.length; i++ ) {
			for ( int j = 0; j < enemigos[i].length; j++ ) {
				enemigos[i][j] = new InvasorCalamar( 0, 0, 0, 0, 0, 0, 0, "", "" );
			}
		}
	}

	// TERMINAR NIVEL
	private void setUpEscenario5 () {
		partida = new Partida( "prueba_TerminarNivel" );
		enemigos = new Enemigo[2][3];
		partida.setEnemigos( enemigos );

		for ( Enemigo[] enemigo : enemigos ) {
			Arrays.fill( enemigo, null );
		}

		enemigos[0][2] = (InvasorCalamar) new InvasorCalamar( 0, 0, 0, 0, 0, 0, 0, "", "" );
	}

	// NIVEL COMPLETO
	private void setUpEscenario6 () {
		partida = new Partida( "prueba_NivelCompleto" );
		partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0, 0, 0 ) );
		partida.getNivel().setNivel( "1" );
		enemigos = new Enemigo[2][3];
		partida.setEnemigos( enemigos );

		for ( Enemigo[] enemigo : enemigos ) {
			Arrays.fill( enemigo, null );
		}
	}

	// ELIMINAR PARTIDA
	private void setUpEscenario7 () {
		partida = new Partida( "prueba_EliminarPartida" );
		Partida a = new Partida( "prueba_EliminarPartida1" );
		Partida b = new Partida( "prueba_EliminarPartida2" );
		Partida c = new Partida( "prueba_EliminarPartida3" );
		Partida d = new Partida( "prueba_EliminarPartida4" );

		try {
			partida.agregarPartida( partida );
			partida.agregarPartida( a );
			partida.agregarPartida( b );
			partida.agregarPartida( c );
			partida.agregarPartida( d );
		} catch ( PartidaYaExisteException ignored ) {
			// Do nothing
		}

	}

	@Test
	void testAgregarPartida () {
		setUpEscenario1();
		try {
			jugador.agregarPartida( partida );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción inesperada PartidaYaExisteException" );
		}
	}

	@Test
	void testAgregarPartidaSinRepetidos () {
		try {
			setUpEscenario2();
			Partida agregar = new Partida( "testAgregar" );
			jugador.agregarPartida( agregar );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción inesperada PartidaYaExisteException" );
		}

	}

	@Test
	void testAgregarPartidaRepetida () {
		setUpEscenario2();
		Partida agregar = new Partida( "test2.1" );

		try {
			jugador.agregarPartida( agregar );
			fail( "Se esperaba excepcion PartidaYaExisteException" );
		} catch ( PartidaYaExisteException ignored ) {
			// Do nothing
		}
	}

	@Test
	void testBuscarPartidaSiNoHayNada () {
		setUpEscenario1();

		Partida buscada = partida.buscarPartida( "test2.1" );
		assertNull( buscada );
	}

	@Test
	void testBuscarPartidaSiHayPartidas () {
		setUpEscenario2();

		Partida buscada = partida.buscarPartida( "test2" );
		assertNotNull( buscada );
	}

	@Test
	void testIniciarEnemigos () {
		setUpEscenario3();

		partida.inicializarEnemigos();

		Enemigo a = (InvasorCangrejo) new InvasorCangrejo( 0, 0, 0, 0, 0, 0, 0, "", "" );
		Enemigo b = (InvasorCalamar) new InvasorCalamar( 0, 0, 0, 0, 0, 0, 0, "", "" );
		Enemigo c = (InvasorPulpo) new InvasorPulpo( 0, 0, 0, 0, 0, 0, 0, "", "" );

		assertEquals( a.getClass(), partida.getEnemigos()[1][4].getClass() );
		assertEquals( b.getClass(), partida.getEnemigos()[0][7].getClass() );
		assertEquals( c.getClass(), partida.getEnemigos()[3][5].getClass() );
	}

	@Test
	void testEliminarEnemigo () {
		setUpEscenario4();

		Enemigo eliminar = enemigos[1][2];
		assertNotNull( enemigos[1][2].toString(), "No debe ser null porque existe" );

		partida.eliminarUnEnemigo( true, eliminar );
		assertNull( enemigos[1][2], "Debería ser null, porque se elimino" );
	}

	@Test
	void testTerminarNivel () {
		setUpEscenario5();
		assertFalse( partida.terminarNivel(), "Queda un enemigo" );

		Enemigo eliminar = enemigos[0][2];
		partida.eliminarUnEnemigo( true, eliminar );
		assertTrue( partida.terminarNivel(), "No quedan enemigos" );
	}

	@Test
	void testNivelCompleto () {
		setUpEscenario6();
		assertEquals( "1", partida.getNivel().getNivel() );

		if ( partida.terminarNivel() ) {
			try {
				partida.nivelCompleto();
			} catch ( IOException ignored ) {
				// Do nothing
			}
		}

		assertEquals( "2", partida.getNivel().getNivel() );
	}

	@Test
	void testEliminarPartida () {
		setUpEscenario7();

		partida.eliminar( "prueba_EliminarPartida2" );
		assertNull( partida.buscarPartida( "prueba_EliminarPartida2" ) );
	}

	@Test
	void testEsHoja () {
		setUpEscenario1();
		assertTrue( partida.esHoja() );

		partida.setPartidaDerecha( new Partida( "noSoyHoja" ) );
		assertFalse( partida.esHoja() );
	}

}
