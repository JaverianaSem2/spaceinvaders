package mundo;

import excepciones.NicknameYaExisteException;
import excepciones.PartidaYaExisteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpaceInvadersTest {

	private SpaceInvaders          space;
	private ArrayList<NaveJugador> jugadores;

	// AGREGAR JUGADOR
	private void setUpEscenario1 () {
		space = new SpaceInvaders( true );
		jugadores = new ArrayList<>();
		space.setJugadores( jugadores );
	}

	// AGREGAR JUGADOR - BUSCAR JUGADOR - BUSCAR RÁPIDO
	private void setUpEscenario2 () {
		space = new SpaceInvaders( true );
		jugadores = new ArrayList<>();

		NaveJugador a = new NaveJugador( "Manuel", "juga1" );
		NaveJugador b = new NaveJugador( "Manuel", "juga2" );
		NaveJugador c = new NaveJugador( "Manuel", "juga3" );
		NaveJugador d = new NaveJugador( "Manuel", "juga4" );

		jugadores.add( a );
		jugadores.add( b );
		jugadores.add( c );
		jugadores.add( d );

		space.setJugadores( jugadores );
	}

	// ORDENAR POR NICKNAME
	private void setUpEscenario3 () {
		space = new SpaceInvaders( true );
		jugadores = new ArrayList<>();

		NaveJugador a = new NaveJugador( "Manuel", "juga1" );
		NaveJugador b = new NaveJugador( "Manuel", "juga2" );
		NaveJugador c = new NaveJugador( "Manuel", "juga3" );
		NaveJugador d = new NaveJugador( "Manuel", "juga4" );

		jugadores.add( b );
		jugadores.add( d );
		jugadores.add( c );
		jugadores.add( a );

		space.setJugadores( jugadores );
	}

	@Test void testAgregarJugadorVectorVacio () {
		setUpEscenario1();

		// No hay nada, esta vacío
		assertEquals( 0, jugadores.size() );

		try {
			space.agregarJugador( "Yo1", "agre1" );
			assertEquals( 1, jugadores.size() );

			space.agregarJugador( "Yo2", "agre2" );
			space.agregarJugador( "Yo2", "agre3" );
			space.agregarJugador( "Yo2", "agre4" );
			assertEquals( 4, jugadores.size() );
		} catch ( NicknameYaExisteException | IOException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarJugadorVectorLleno () {
		setUpEscenario2();
		assertEquals( 4, jugadores.size() );

		try {
			space.agregarJugador( "Manuel", "juga5" );
			assertEquals( 5, jugadores.size() );
		} catch ( NicknameYaExisteException | IOException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarJugadorRepetidoVector () {
		setUpEscenario2();

		Assertions.assertThrows( NicknameYaExisteException.class, () -> {
			space.agregarJugador( "Manuel", "juga4" );
			fail( "No lanza excepción esperada NicknameYaExisteException" );
		} );

		assertEquals( 4, jugadores.size() );
	}

	@Test void testBuscarJugadorVector () {
		setUpEscenario2();

		NaveJugador buscado = space.buscarJugador( "juga3" );
		assertNotNull( buscado );
		assertEquals( jugadores.get( 2 ), buscado );

		NaveJugador buscado1 = space.buscarJugador( "juga0" );
		assertNull( buscado1 );
	}

	@Test void testOrdenarNickName () {
		setUpEscenario3();

		List<NaveJugador> players = space.ordenarPorNickname();

		assertEquals( players.get( 0 ), jugadores.get( 3 ) );
		assertEquals( players.get( 1 ), jugadores.get( 0 ) );
		assertEquals( players.get( 2 ), jugadores.get( 2 ) );
		assertEquals( players.get( 3 ), jugadores.get( 1 ) );
	}

	@Test void testBuscarRapido () {
		setUpEscenario2();

		assertTrue( space.busquedaRapida( "juga3" ) );
		assertFalse( space.busquedaRapida( "juga" ) );
		assertFalse( space.busquedaRapida( "juga8" ) );
	}

	@Test void testEliminarPartida () {
		setUpEscenario2();

		space.setJugadorActual( space.getJugadores().get( 0 ) );
		try {
			space.crearPartida( "Prueba" );
			space.getJugadorActual().setPartidaRaiz( space.getPartidaActual() );
			assertEquals( space.getPartidaActual(), space.getJugadorActual().getPartidaRaiz() );
			space.eliminarPartida();
			assertNull( space.getJugadorActual().getPartidaRaiz() );
		} catch ( PartidaYaExisteException | IOException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testDarPartida () {
		setUpEscenario2();

		final String NOMBRE_PARTIDA = "Prueba";

		space.setJugadorActual( space.getJugadores().get( 0 ) );
		try {
			List<Partida> partidasNull = new ArrayList<>();
			assertEquals( partidasNull, space.darPartidasJugador() );
			assertNull( space.getJugadorActual().getPartidaRaiz() );

			space.crearPartida( NOMBRE_PARTIDA );
			assertEquals( "[" + NOMBRE_PARTIDA + "]", space.darPartidasJugador().toString() );

			space.getJugadorActual().setPartidaRaiz( space.getPartidaActual() );
			assertEquals( "[" + NOMBRE_PARTIDA + "]", space.darPartidasJugador().toString() );
		} catch ( PartidaYaExisteException | IOException e ) {
			fail( "Lanza excepción no esperada " + e.getClass().getName() );
		}
	}

	@Test void testAgregarPuntajePrimerPuntajeNulo () {
		SpaceInvaders spaceInvaders = new SpaceInvaders( true );

		Whitebox.setInternalState( spaceInvaders, "primerPuntaje", null );
		spaceInvaders.setPrimerPuntaje( Puntaje.agregarPuntaje( spaceInvaders.getPrimerPuntaje(), new Puntaje( 10, "jugador", "partida" ) ) );

		assertEquals( "10 jugador partida", Whitebox.getInternalState( spaceInvaders, "primerPuntaje" ).toString() );
	}

	@Test void testAgregarPuntajePrimerPuntajeMenorAPuntuacion () {
		// Como el nuevo puntaje (10) es mayor que el anterior (1)
		// Se reemplaza y se guarda el histórico (siguiente y anterior)
		SpaceInvaders spaceInvaders = new SpaceInvaders( true );
		Puntaje primerPuntaje = new Puntaje( 1, "jugador", "partida" );
		assertEquals( "1 jugador partida", primerPuntaje.toString() );
		assertNull( primerPuntaje.getAnterior() );
		assertNull( primerPuntaje.getSiguiente() );

		Whitebox.setInternalState( spaceInvaders, "primerPuntaje", primerPuntaje );
		spaceInvaders.setPrimerPuntaje( Puntaje.agregarPuntaje( spaceInvaders.getPrimerPuntaje(), new Puntaje( 10, "jugador", "partida" ) ) );

		primerPuntaje = spaceInvaders.getPrimerPuntaje();
		assertEquals( "10 jugador partida", primerPuntaje.toString() );
		assertEquals( "10 jugador partida", primerPuntaje.getAnterior().toString() );
		assertEquals( "1 jugador partida", primerPuntaje.getSiguiente().toString() );
	}

	@Test void testAgregarPuntajeCondicionSiguienteNoNull () {
		// Si el nuevo puntaje es 10, el consolidado 20 y el anterior 8,
		// se remplazará el anterior (8) por el nuevo (10) ya que es mayor
		SpaceInvaders spaceInvaders = new SpaceInvaders( true );

		Puntaje primerPuntaje = new Puntaje( 20, "jugador", "partida" );
		primerPuntaje.setSiguiente( new Puntaje( 8, "jugador", "partida" ) );
		assertEquals( "20 jugador partida", primerPuntaje.toString() );
		assertEquals( "8 jugador partida", primerPuntaje.getSiguiente().toString() );

		Whitebox.setInternalState( spaceInvaders, "primerPuntaje", primerPuntaje );
		spaceInvaders.setPrimerPuntaje( Puntaje.agregarPuntaje( spaceInvaders.getPrimerPuntaje(), new Puntaje( 10, "jugador", "partida" ) ) );

		primerPuntaje = (Puntaje) Whitebox.getInternalState( spaceInvaders, "primerPuntaje" );
		assertNull( primerPuntaje.getAnterior() );
		assertEquals( "20 jugador partida", primerPuntaje.toString() );
		assertEquals( "10 jugador partida", primerPuntaje.getSiguiente().toString() );
	}

	@Test void testMejoresPuntajes () {
		SpaceInvaders spaceInvaders = new SpaceInvaders( true );
		Whitebox.setInternalState( spaceInvaders, "primerPuntaje", null );
		assertEquals( new ArrayList<>(), Puntaje.getMejoresPuntajes( spaceInvaders.getPrimerPuntaje() ) );

		Puntaje primerPuntaje = new Puntaje( 20, "jugador", "partida" );
		Puntaje puntajeAuxiliar = primerPuntaje;

		int[] arrayInt = { 10, 8, 9, 6, 7, 5, 4, 1, 2, 3, 0 };
		for ( int i = 0; i <= 10; i++ ) {
			puntajeAuxiliar.setSiguiente( new Puntaje( arrayInt[i], "jugador", "partida" ) );
			puntajeAuxiliar = puntajeAuxiliar.getSiguiente();
		}

		Whitebox.setInternalState( spaceInvaders, "primerPuntaje", primerPuntaje );
		assertEquals( "["
			+ "1 20 jugador partida, "
			+ "2 10 jugador partida, "
			+ "3 8 jugador partida, "
			+ "4 9 jugador partida, "
			+ "5 6 jugador partida, "
			+ "6 7 jugador partida, "
			+ "7 5 jugador partida, "
			+ "8 4 jugador partida, "
			+ "9 1 jugador partida, "
			+ "10 2 jugador partida"
			+ "]",
			Puntaje.getMejoresPuntajes( spaceInvaders.getPrimerPuntaje() ).toString()
		);

		assertEquals( primerPuntaje, Whitebox.getInternalState( spaceInvaders, "primerPuntaje" ) );
	}

	@Test
		// Este test entra a un bucle infinito
	void testAgregarPuntajePrimerPuntajeMenorAPuntuacion2 () {
		SpaceInvaders spaceInvaders = new SpaceInvaders( true );

		Puntaje primerPuntaje = new Puntaje( 3, "jugador", "partida" );
		primerPuntaje.setAnterior( new Puntaje( 4, "jugador", "partida" ) );
		primerPuntaje.setSiguiente( new Puntaje( 3, "jugador", "partida" ) );

		Whitebox.setInternalState( spaceInvaders, "primerPuntaje", primerPuntaje );
		spaceInvaders.setPrimerPuntaje( Puntaje.agregarPuntaje( spaceInvaders.getPrimerPuntaje(), new Puntaje( 3, "jugador", "partida" ) ) );

		primerPuntaje = (Puntaje) Whitebox.getInternalState( spaceInvaders, "primerPuntaje" );
		assertEquals( "3 jugador partida", primerPuntaje.toString() );
		assertEquals( "4 jugador partida", primerPuntaje.getAnterior().toString() );
		assertEquals( "3 jugador partida", primerPuntaje.getSiguiente().toString() );
	}

}
