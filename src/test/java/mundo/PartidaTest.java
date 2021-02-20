package mundo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import excepciones.PartidaYaExisteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

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

		Assertions.assertThrows(PartidaYaExisteException.class, () -> {
			jugador.agregarPartida( agregar );
			fail( "Se esperaba excepcion PartidaYaExisteException" );
		});
	}

	@Test
	void testAgregarPartidaNombreMayorAlSeteadoYPartidaIzqNula () {
		Partida partidaInicial = new Partida( "alexander" );
		Partida partidaSecundaria = new Partida( "alex" );
		assertNull( partidaInicial.getPartidaIzquierda() );
		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaIzquierda() );
			assertEquals( partidaSecundaria, partidaInicial.getPartidaIzquierda() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción inesperada PartidaYaExisteException" );
		}
	}

	@Test
	void testAgregarPartidaNombreMayorAlSeteadoYPartidaIzqNoNula () {
		Partida partidaInicial = new Partida( "alexander" );
		Partida partidaSecundaria = new Partida( "alex" );
		partidaInicial.setPartidaIzquierda( new Partida( "Izquierda" ) );

		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaIzquierda() );
			assertEquals( partidaSecundaria, partidaInicial.getPartidaIzquierda().getPartidaIzquierda() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción inesperada PartidaYaExisteException" );
		}

	}

	@Test
	void testAgregarPartidaNombreMenorAlSeteadoYPartidaDerNula () {
		Partida partidaInicial = new Partida( "alex" );
		Partida partidaSecundaria = new Partida( "alexander" );
		assertNull( partidaInicial.getPartidaDerecha() );
		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaDerecha() );
			assertEquals( partidaSecundaria, partidaInicial.getPartidaDerecha() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción inesperada PartidaYaExisteException" );
		}
	}

	@Test
	void testAgregarPartidaNombreMayorAlSeteadoYPartidaDerNoNula () {
		Partida partidaInicial = new Partida( "alex" );
		Partida partidaSecundaria = new Partida( "lxndr" );
		partidaInicial.setPartidaDerecha( new Partida( "Derecha" ) );

		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaDerecha() );
			assertEquals( partidaSecundaria, partidaInicial.getPartidaDerecha().getPartidaDerecha() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción inesperada PartidaYaExisteException" );
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
		assertEquals( "test2", buscada.getNombre() );
	}

	@Test
	void testBuscarPartidaNoHayPartidasIzqNoNulaNombreCortoResultadoNulo () {
		Partida partida = new Partida( "prueba_AgregarPartida_BuscarPartida" );
		partida.setPartidaIzquierda( new Partida( "prueba_AgregarPartida_BuscarPartida" ) );
		Partida buscada = partida.buscarPartida( "prueba_AgregarPartida_BuscarPartida_largo" );
		assertNull( buscada );
	}

	@Test
	void testBuscarPartidaNoHayPartidasIzqNoNulaNombreLargoResultadoNulo () {
		Partida partida = new Partida( "nombre_largo" );
		partida.setPartidaIzquierda( new Partida( "nombre_largo" ) );
		Partida buscada = partida.buscarPartida( "corto" );
		assertNull( buscada );
	}

	@Test
	void testBuscarPartidaNoHayPartidasDerNoNulaNombreCortoResultadoNulo () {
		Partida partida = new Partida( "prueba_AgregarPartida_BuscarPartida" );
		partida.setPartidaDerecha( partida );
		Partida buscada = partida.buscarPartida( "corto" );
		assertNull( buscada );
	}

	@Test
	void testBuscarPartidaNoHayPartidasDerNoNulaNombreLargoResultadoNulo () {
		Partida partida = new Partida( "corto" );
		partida.setPartidaDerecha( new Partida( "corto" ) );
		Partida buscada = partida.buscarPartida( "nombre_largo" );
		assertNull( buscada );
	}

	@Test
	void testIniciarEnemigos () {
		setUpEscenario3();

		partida.inicializarEnemigos();

		Enemigo a = new InvasorCangrejo( 0, 0, 0, 0, 0, 0, 0, "", "" );
		Enemigo b = new InvasorCalamar( 0, 0, 0, 0, 0, 0, 0, "", "" );
		Enemigo c = new InvasorPulpo( 0, 0, 0, 0, 0, 0, 0, "", "" );

		assertEquals( a.getClass(), partida.getEnemigos()[1][4].getClass() );
		assertEquals( b.getClass(), partida.getEnemigos()[0][7].getClass() );
		assertEquals( c.getClass(), partida.getEnemigos()[3][5].getClass() );
	}

	@Test
	void testEliminarEnemigo () {
		setUpEscenario4();

		Enemigo eliminar = enemigos[1][2];
		assertNotNull( enemigos[1][2].toString(), "No debe ser null porque existe" );

		partida.eliminarUnEnemigo( false, eliminar );
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
	void testNivelCompletoNivel1 () {
		setUpEscenario6();
		assertEquals( "1", partida.getNivel().getNivel() );
		boolean isNivelCompleto = false;

		if ( partida.terminarNivel() ) {
			try {
				isNivelCompleto = partida.nivelCompleto();
			} catch ( IOException e ) {
				fail( "No se esperaba error" );
			}
		}

		assertTrue( isNivelCompleto );
		assertEquals( "2", partida.getNivel().getNivel() );
	}

	@Test
	void testNivelCompletoNivel2 () {
		Partida partida = new Partida( "Partida" );
		partida.setNivel( new Nivel( "2", 0,0,0,0,0,0,0 ) );
		boolean isNivelCompleto = true;

			try {
				isNivelCompleto = partida.nivelCompleto();
			} catch ( IOException e ) {
				fail( "No se esperaba error" );
			}

		assertFalse( isNivelCompleto );
		assertEquals( "2", partida.getNivel().getNivel() );
	}

	@Test
	void testEliminarPartidaNoEsHoja () {
		Partida partida = new Partida( "Prueba" );

		partida = partida.eliminar( "Prueba" );
		assertNull( partida );
	}

	@Test
	void testEliminarPartidaEsHojaDevolverIzq () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaIzquierda = new Partida( "Izq" );
		partida.setPartidaIzquierda( partidaIzquierda );

		Partida partidaDevuelta = partida.eliminar( "Prueba" );
		assertEquals( partidaIzquierda, partidaDevuelta );
	}

	@Test
	void testEliminarPartidaEsHojaDevolverDer () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaDerecha = new Partida( "Der" );
		partida.setPartidaDerecha( partidaDerecha );

		Partida partidaDevuelta = partida.eliminar( "Prueba" );
		assertEquals( partidaDerecha, partidaDevuelta );
	}

	@Test
	void testEliminarPartidaEsHojaDevolverSucesor () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaIzquierda = new Partida( "Izq" );
		Partida partidaDerecha = new Partida( "Der" );
		partida.setPartidaIzquierda( partidaIzquierda );
		partida.setPartidaDerecha( partidaDerecha );

		Partida partidaDevuelta = partida.eliminar( "Prueba" );
		assertEquals( partidaDerecha, partidaDevuelta );
	}

	@Test
	void testEliminarPartidaEsHojaDevolverPartidaNombreLargo () {
		Partida partida = new Partida( "PruebaIzquierda" );
		Partida partidaIzquierda = new Partida( "Izq" );
		partida.setPartidaIzquierda( partidaIzquierda );

		Partida partidaDevuelta = partida.eliminar( "Prueba2" );
		assertEquals( partida, partidaDevuelta );
	}

	@Test
	void testEliminarPartidaEsHojaDevolverPartidaNombreCorto () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaDerecha = new Partida( "Derecha" );
		partida.setPartidaDerecha( partidaDerecha );

		Partida partidaDevuelta = partida.eliminar( "Prueba2" );
		assertEquals( partida, partidaDevuelta );
	}

	@Test
	void testEsHoja () {
		setUpEscenario1();
		assertTrue( partida.esHoja() );

		partida.setPartidaDerecha( new Partida( "noSoyHoja" ) );
		assertFalse( partida.esHoja() );
	}

	@Test
	void testInicializarPartidaNivel1 () {
		Partida partida = new Partida( "Nueva" );
		partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0, 0, 0 ) );

		try {
			partida.inicializarPartida();
		} catch ( IOException e ) {
			fail( "No esperaba excepción IOException" );
		}
	}

	@Test
	void testInicializarPartidaNivel3 () {
		Partida partida = new Partida( "Nueva" );
		partida.setNivel( new Nivel( "3", 0, 0, 0, 0, 0, 0, 0 ) );

		Assertions.assertThrows(IOException.class, () -> {
			partida.inicializarPartida();
			fail( "Se esperaba excepción IOException" );
		});
	}

	@Test
	void testInicializarPartidaEnemigosMayorACuatro () {
		Partida partida = new Partida( "Nueva" );
		Enemigo[][] enemigoLocal = new Enemigo[6][10];
		partida.setEnemigos( enemigoLocal );
		partida.inicializarEnemigos();
		assertNotNull( partida.getEnemigos()[4][0] );
		assertNull( partida.getEnemigos()[5][0] );
	}

	@Test
	void testInOrderIzqNoNull () {
		Partida partidaPrincipal = new Partida( "Prueba" );
		Partida partidaIzquierda = new Partida( "Izquierda" );

		partidaPrincipal.setPartidaIzquierda( partidaIzquierda );
		partidaPrincipal.inOrden( new ArrayList<>() );
		assertEquals( partidaIzquierda, partidaPrincipal.getPartidaIzquierda() );
		assertNull( partidaPrincipal.getPartidaDerecha() );
	}

	@Test
	void testInOrderDerNoNull () {
		Partida partidaPrincipal = new Partida( "Prueba" );
		Partida partidaDerecha = new Partida( "Derecha" );

		partidaPrincipal.setPartidaDerecha( partidaDerecha );
		partidaPrincipal.inOrden( new ArrayList<>() );
		assertEquals( partidaDerecha, partidaPrincipal.getPartidaDerecha() );
		assertNull( partidaPrincipal.getPartidaIzquierda() );
	}

	@Test
	void testDarMenor () {
		Partida partidaPrincipal = new Partida( "Prueba" );
		assertEquals( partidaPrincipal, partidaPrincipal.darMenor() );

		Partida partidaIzquierda = new Partida( "Izquierda" );
		partidaPrincipal.setPartidaIzquierda( partidaIzquierda );
		assertEquals( partidaIzquierda, partidaPrincipal.darMenor() );
	}

}
