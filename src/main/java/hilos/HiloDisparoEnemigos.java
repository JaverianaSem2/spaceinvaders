package hilos;

import interfaz.InterfazSpaceInvaders;
import mundo.Partida;
import mundo.SpaceInvaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiloDisparoEnemigos extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger( HiloDisparoEnemigos.class.getName() );

	private final Partida               partidaEnemigos;
	private final SpaceInvaders         space;
	private final InterfazSpaceInvaders interfaz;

	public HiloDisparoEnemigos ( Partida a, InterfazSpaceInvaders p, SpaceInvaders b ) {
		partidaEnemigos = a;
		interfaz = p;
		space = b;
	}

	@Override public void run () {
		super.run();

		while ( interfaz.estaEnFuncionamiento() ) {

			for ( int i = 0; i < partidaEnemigos.getEnemigos().length; i++ ) {
				for ( int j = 0; j < partidaEnemigos.getEnemigos()[0].length; j++ ) {

					if ( partidaEnemigos.getEnemigos()[i][j] != null  &&
					partidaEnemigos.getEnemigos()[i][j].getDisparoUno() != null ) {
						partidaEnemigos.getEnemigos()[i][j].getDisparoUno().shoot1();

						if ( partidaEnemigos.getEnemigos()[i][j].getDisparoUno().hitsJugador( space.getJugadorActual() ) ) {
							partidaEnemigos.getEnemigos()[i][j].eliminarDisparo();
							space.getJugadorActual().golpe( 1 );
						}
					}
				}
			}

			try {
				sleep( 60 );
			} catch ( InterruptedException e ) {
				LOGGER.info( e.getMessage() );
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}
			interfaz.getPanelNivel().updateUI();
		}
	}

}
