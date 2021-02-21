package mundo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaveJugadorTest {

	private NaveJugador naveJugador;
	private Disparo     disparo;
	
	// MOVER - DISPARAR
	private void setUpEscenario1 () {
		naveJugador = new NaveJugador("prueba_Mover", "test1");
		naveJugador.setVelocidad(10);
		naveJugador.setPosInicialX(300);
	}
	
	// ELIMINAR DISPARO
	private void setUpEscenario2 (){
		naveJugador = new NaveJugador("prueba_EliminarDisparo", "test2");
		disparo = new Disparo(20, 10);
		naveJugador.setDisparoUno(disparo);
	}
	
	// ESTA VIVA
	private void setUpEscenario3 () {
		naveJugador = new NaveJugador("prueba_EstaViva", "test3");
		naveJugador.setVida(3);
	}

	@Test
	void testMoverNave () {
		setUpEscenario1();
		assertEquals(300, naveJugador.getPosInicialX());
		
		// Mover nave a la DERECHA
		naveJugador.mover(1);
		assertEquals(310, naveJugador.getPosInicialX());

		// Mover nace a su posicion inicial
		naveJugador.mover(-1);
		assertEquals(300, naveJugador.getPosInicialX());
		
		// Mover nave a la izquierda
		naveJugador.mover(-1);
		assertEquals(290, naveJugador.getPosInicialX());
	}

	@Test
	void testDispara () {
		setUpEscenario1();
		assertNull( naveJugador.getDisparoUno());
		
		// Crea un disparo a la nave
		naveJugador.disparar(200, 310);
		assertNotNull( naveJugador.getDisparoUno());

		// Crea un disparo a un enemigo
		Enemigo momentaneo = new InvasorPulpo(0, 0, 0, 0, 0, 0, 0, "", "");
		momentaneo.disparar(100, 300);
		assertNotNull( momentaneo.getDisparoUno() );
		
		// Los disparos no son iguales
		assertNotSame( naveJugador.getDisparoUno(), momentaneo.getDisparoUno());
	}

	@Test
	void testEliminaDisparo (){
		setUpEscenario2();

		assertNotNull( naveJugador.getDisparoUno());
		naveJugador.eliminarDisparo();
		assertNull( naveJugador.getDisparoUno());
	}

	@Test
	void testEstaViva () {
		setUpEscenario3();
		assertTrue( naveJugador.estaViva());
		assertEquals(3, naveJugador.getVida());
		
		naveJugador.setVida(2);
		assertTrue( naveJugador.estaViva());
		
		// Murió el jugador
		naveJugador.setVida(0);
		assertFalse( naveJugador.estaViva());
	}

	@Test
	void testDisparo() {
		setUpEscenario1();

		naveJugador.setDisparoUno( null );
		naveJugador.disparar( 20, 10 );
		assertFalse( naveJugador.getDisparoUno().getImpacto() );
		assertEquals( 20, naveJugador.getDisparoUno().getPosX() );
		assertEquals( 10, naveJugador.getDisparoUno().getPosY() );

		Disparo disparo = new Disparo(40, 30);
		naveJugador.setDisparoUno( disparo );
		naveJugador.disparar( 60, 50 );
		assertSame( disparo,  naveJugador.getDisparoUno() );
	}

}
