package hilos;

import interfaz.InterfazSpaceInvaders;
import mundo.Partida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class HiloAuxiliarCreaDisparo extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger( HiloAuxiliarCreaDisparo.class.getName() );

	private final Partida               partidaEnemigos;
	private final InterfazSpaceInvaders interfaz;

	public HiloAuxiliarCreaDisparo ( Partida a, InterfazSpaceInvaders principal ) {
		partidaEnemigos = a;
		interfaz = principal;
	}

	@Override
	public void run() {
		while ( interfaz.estaEnFuncionamiento() ) {

			SecureRandom rand = new SecureRandom();
			int fila = rand.nextInt( 4 );
			int columna = rand.nextInt( 9 );

			if ( partidaEnemigos.getEnemigos()[fila][columna] != null ) {
				partidaEnemigos.getEnemigos()[fila][columna].disparar(
					partidaEnemigos.getEnemigos()[fila][columna].getPosX(),
					partidaEnemigos.getEnemigos()[fila][columna].getPosY()
				);
			}

			try {
				sleep( 800 );
			} catch ( InterruptedException e ) {
				LOGGER.info( e.getMessage() );
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}
		}

	}

}
