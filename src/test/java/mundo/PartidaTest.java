package mundo;

import excepciones.PartidaYaExisteException;
import mundo.abstracfactory.Invasor;
import mundo.abstracfactory.InvasorFabrica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
class PartidaTest {

	private Partida     partida;
	private NaveJugador jugador;
	private Invasor[][] enemigos;

  InvasorFabrica fabrica = new InvasorFabrica();

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
		enemigos = new Invasor[5][10];
		partida.setEnemigos( enemigos );
	}

	// ELIMINAR UN ENIMIGO
	private void setUpEscenario4 () {
		partida = new Partida( "prueba_EliminarEnemigo" );
    partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0 ) );
    fabrica.setNivel( partida.getNivel() );
		enemigos = new Invasor[2][3];
		partida.setEnemigos( enemigos );

		for ( int i = 0; i < enemigos.length; i++ ) {
			for ( int j = 0; j < enemigos[i].length; j++ ) {
				enemigos[i][j] = fabrica.crearInvasor( "Calamar", 0, 0 );
			}
		}
	}

	// TERMINAR NIVEL
	private void setUpEscenario5 () {
		partida = new Partida( "prueba_TerminarNivel" );
    partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0 ) );
		enemigos = new Invasor[2][3];
		partida.setEnemigos( enemigos );
    fabrica.setNivel( partida.getNivel() );
		for ( Invasor[] enemigo : enemigos ) {
			Arrays.fill( enemigo, null );
		}

		enemigos[0][2] =  fabrica.crearInvasor( "Calamar",0, 0);
	}

	// NIVEL COMPLETO
	private void setUpEscenario6 () {
		partida = new Partida( "prueba_NivelCompleto" );
		partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0 ) );
		partida.getNivel().setNivel( "1" );
    fabrica.setNivel( partida.getNivel() );
		enemigos = new Invasor[2][3];
    enemigos[0][2] = fabrica.crearInvasor( "Calamar", 0, 0);
		partida.setEnemigos( enemigos );

		for ( Invasor[] enemigo : enemigos ) {
			Arrays.fill( enemigo, null );
		}
	}

	@Test void testAgregarPartida () {
		setUpEscenario1();
		try {
			assertNull( jugador.getPartidaRaiz() );
			jugador.agregarPartida( partida );
			assertEquals( partida, jugador.getPartidaRaiz() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarPartidaSinRepetidos () {
		try {
			setUpEscenario2();

			assertEquals( "test2", jugador.getPartidaRaiz().getNombre() );
			Partida agregar = new Partida( "testAgregar" );
			jugador.agregarPartida( agregar );
			assertEquals( "test2", jugador.getPartidaRaiz().getNombre() );
			assertEquals( agregar.getNombre(), jugador.getPartidaRaiz().buscarPartida( "testAgregar" ).getNombre() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarPartidaRepetida () {
		setUpEscenario2();

		Partida agregar = new Partida( "test2.1" );

		Assertions.assertThrows( PartidaYaExisteException.class, () -> {
			jugador.agregarPartida( agregar );
			fail( "Se esperaba excepcion PartidaYaExisteException" );
		} );
	}

	@Test void testAgregarPartidaNombreMayorAlSeteadoYPartidaIzqNula () {
		Partida partidaInicial = new Partida( "alexander" );
		Partida partidaSecundaria = new Partida( "alex" );
		assertNull( partidaInicial.getPartidaIzquierda() );
		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaIzquierda() );
			assertSame( partidaSecundaria, partidaInicial.getPartidaIzquierda() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarPartidaNombreMayorAlSeteadoYPartidaIzqNoNula () {
		Partida partidaInicial = new Partida( "alexander" );
		Partida partidaSecundaria = new Partida( "alex" );
		partidaInicial.setPartidaIzquierda( new Partida( "Izquierda" ) );

		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaIzquierda() );
			assertSame( partidaSecundaria, partidaInicial.getPartidaIzquierda().getPartidaIzquierda() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}

	}

	@Test void testAgregarPartidaNombreMenorAlSeteadoYPartidaDerNula () {
		Partida partidaInicial = new Partida( "alex" );
		Partida partidaSecundaria = new Partida( "alexander" );
		assertNull( partidaInicial.getPartidaDerecha() );
		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaDerecha() );
			assertSame( partidaSecundaria, partidaInicial.getPartidaDerecha() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarPartidaNombreMayorAlSeteadoYPartidaDerNoNula () {
		Partida partidaInicial = new Partida( "alex" );
		Partida partidaSecundaria = new Partida( "lxndr" );
		partidaInicial.setPartidaDerecha( new Partida( "Derecha" ) );

		try {
			partidaInicial.agregarPartida( partidaSecundaria );
			assertNotEquals( partidaInicial, partidaInicial.getPartidaDerecha() );
			assertSame( partidaSecundaria, partidaInicial.getPartidaDerecha().getPartidaDerecha() );
		} catch ( PartidaYaExisteException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}

	}

	@Test void testBuscarPartidaSiNoHayNada () {
		setUpEscenario1();

		assertNull( partida.buscarPartida( "test2.1" ) );
	}

	@Test void testBuscarPartidaSiHayPartidas () {
		setUpEscenario2();

		assertEquals( "test2", partida.buscarPartida( "test2" ).getNombre() );
	}

	@Test void testBuscarPartidaNoHayPartidasIzqNoNulaNombreCortoResultadoNulo () {
		Partida partida = new Partida( "prueba_AgregarPartida_BuscarPartida" );
		partida.setPartidaIzquierda( new Partida( "prueba_AgregarPartida_BuscarPartida" ) );
		assertNull( partida.buscarPartida( "prueba_AgregarPartida_BuscarPartida_largo" ) );
	}

	@Test void testBuscarPartidaNoHayPartidasIzqNoNulaNombreLargoResultadoNulo () {
		Partida partida = new Partida( "nombre_largo" );
		partida.setPartidaIzquierda( new Partida( "nombre_largo" ) );
		assertNull( partida.buscarPartida( "corto" ) );
	}

	@Test void testBuscarPartidaNoHayPartidasDerNoNulaNombreCortoResultadoNulo () {
		Partida partida = new Partida( "prueba_AgregarPartida_BuscarPartida" );
		partida.setPartidaDerecha( partida );
		assertNull( partida.buscarPartida( "corto" ) );
	}

	@Test void testBuscarPartidaNoHayPartidasDerNoNulaNombreLargoResultadoNulo () {
		Partida partida = new Partida( "corto" );
		partida.setPartidaDerecha( new Partida( "corto" ) );
		assertNull( partida.buscarPartida( "nombre_largo" ) );
	}

	@Test void testIniciarEnemigos () {
		setUpEscenario3();

		partida.inicializarEnemigos();
    partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0 ) );
    fabrica.setNivel( partida.getNivel() );


		Invasor a = fabrica.crearInvasor( "Cangrejo", 0, 0);
    Invasor b = fabrica.crearInvasor( "Calamar", 0, 0);
    Invasor c = fabrica.crearInvasor( "Pulpo", 0, 0);

		assertEquals( a.getClass(), partida.getEnemigos()[1][4].getClass() );
		assertEquals( b.getClass(), partida.getEnemigos()[0][7].getClass() );
		assertEquals( c.getClass(), partida.getEnemigos()[3][5].getClass() );
	}

	@Test void testEliminarEnemigo () {
		setUpEscenario4();

		Invasor eliminar = enemigos[1][2];
		assertNotNull( enemigos[1][2].toString(), "No debe ser null porque existe" );

		partida.eliminarUnEnemigo( false, eliminar );
		assertNotNull( enemigos[1][2].toString(), "No debe ser null porque existe" );

		partida.eliminarUnEnemigo( true, eliminar );
		assertNull( enemigos[1][2], "Debería ser null, porque se elimino" );
	}

	@Test void testTerminarNivel () {
		setUpEscenario5();
		assertFalse( partida.terminarNivel(), "Queda un enemigo" );

		Invasor eliminar = enemigos[0][2];
		partida.eliminarUnEnemigo( true, eliminar );
		assertTrue( partida.terminarNivel(), "No quedan enemigos" );
	}

	@Test void testNivelCompletoNivel1 () {
		setUpEscenario6();

		assertEquals( "1", partida.getNivel().getNivel());

		if ( partida.terminarNivel() ) {
			try {
				assertTrue( partida.nivelCompleto() );
				assertEquals( "2", partida.getNivel().getNivel() );
			} catch ( IOException e ) {
				fail( "Lanza excepción no esperada " + e.getClass().getName() );
			}
		}
	}

	@Test void testNivelCompletoNivel2 () {
		Partida partida = new Partida( "Partida" );
		partida.setNivel( new Nivel( "2", 0,0,0,0,0 ) );

		try {
			assertFalse( partida.nivelCompleto() );
			assertEquals( "2", partida.getNivel().getNivel() );
		} catch ( IOException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testEliminarPartidaNoEsHoja () {
		Partida partida = new Partida( "Prueba" );

		partida = partida.eliminar( "Prueba" );
		assertNull( partida );
	}

	@Test void testEliminarPartidaEsHojaDevolverIzq () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaIzquierda = new Partida( "Izq" );
		partida.setPartidaIzquierda( partidaIzquierda );

		assertEquals( partidaIzquierda, partida.eliminar( "Prueba" ) );
	}

	@Test void testEliminarPartidaEsHojaDevolverDer () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaDerecha = new Partida( "Der" );
		partida.setPartidaDerecha( partidaDerecha );

		assertEquals( partidaDerecha, partida.eliminar( "Prueba" ) );
	}

	@Test void testEliminarPartidaEsHojaDevolverSucesor () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaIzquierda = new Partida( "Izq" );
		Partida partidaDerecha = new Partida( "Der" );
		partida.setPartidaIzquierda( partidaIzquierda );
		partida.setPartidaDerecha( partidaDerecha );

		assertEquals( partidaDerecha, partida.eliminar( "Prueba" ) );
	}

	@Test void testEliminarPartidaEsHojaDevolverPartidaNombreLargo () {
		Partida partida = new Partida( "PruebaIzquierda" );
		Partida partidaIzquierda = new Partida( "Izq" );
		partida.setPartidaIzquierda( partidaIzquierda );

		assertEquals( partida, partida.eliminar( "Prueba2" ) );
	}

	@Test void testEliminarPartidaEsHojaDevolverPartidaNombreCorto () {
		Partida partida = new Partida( "Prueba" );
		Partida partidaDerecha = new Partida( "Derecha" );
		partida.setPartidaDerecha( partidaDerecha );

		assertEquals( partida, partida.eliminar( "Prueba2" ) );
	}

	@Test void testEsHoja () {
		setUpEscenario1();
		assertTrue( partida.esHoja() );

		partida.setPartidaDerecha( new Partida( "noSoyHoja" ) );
		assertFalse( partida.esHoja() );
	}

	@Test void testInicializarPartidaNivel1 () {
		Partida partida = new Partida( "Nueva" );
		partida.setNivel( new Nivel( "1", 0, 0, 0, 0, 0 ) );

		try {
			assertNull( partida.getEnemigos() );
			partida.inicializarPartida();
			assertNotNull( partida.getEnemigos()[0][0] );
		} catch ( IOException e ) {
			fail( "No esperaba excepción IOException" );
		}
	}

	@Test void testInicializarPartidaNivel3 () {
		// Solo existen los niveles 1 y 2, al ingresar el nivel 3 se produce un error IO
		Partida partida = new Partida( "Nueva" );
		partida.setNivel( new Nivel( "3", 0, 0, 0, 0, 0 ) );

		Assertions.assertThrows( IOException.class, () -> {
			partida.inicializarPartida();
			fail( "Se esperaba excepción IOException" );
		} );
	}

	@Test void testInicializarPartidaEnemigosMayorACuatro () {
		Partida partida = new Partida( "Nueva" );
		Invasor[][] enemigoLocal = new Invasor[6][10];
		partida.setEnemigos( enemigoLocal );
		partida.inicializarEnemigos();
		assertNotNull( partida.getEnemigos()[4][0] );
		assertNull( partida.getEnemigos()[5][0] );
	}

	@Test void testInOrderIzqNoNull () {
		Partida partidaPrincipal = new Partida( "Prueba" );
		Partida partidaIzquierda = new Partida( "Izquierda" );

		partidaPrincipal.setPartidaIzquierda( partidaIzquierda );
		partidaPrincipal.inOrden( new ArrayList<>() );
		assertEquals( partidaIzquierda, partidaPrincipal.getPartidaIzquierda() );
		assertNull( partidaPrincipal.getPartidaDerecha() );
	}

	@Test void testInOrderDerNoNull () {
		Partida partidaPrincipal = new Partida( "Prueba" );
		Partida partidaDerecha = new Partida( "Derecha" );

		partidaPrincipal.setPartidaDerecha( partidaDerecha );
		partidaPrincipal.inOrden( new ArrayList<>() );
		assertEquals( partidaDerecha, partidaPrincipal.getPartidaDerecha() );
		assertNull( partidaPrincipal.getPartidaIzquierda() );
	}

	@Test void testDarMenor () {
		Partida partidaPrincipal = new Partida( "Prueba" );
		assertEquals( partidaPrincipal, partidaPrincipal.darMenor() );

		Partida partidaIzquierda = new Partida( "Izquierda" );
		partidaPrincipal.setPartidaIzquierda( partidaIzquierda );
		assertEquals( partidaIzquierda, partidaPrincipal.darMenor() );
	}

}
