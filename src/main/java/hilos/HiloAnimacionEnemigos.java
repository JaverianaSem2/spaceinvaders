package hilos;

import interfaz.InterfazSpaceInvaders;
import mundo.Enemigo;
import mundo.abstracfactory.CalamarInvasor;
import mundo.abstracfactory.CangrejoInvasor;
import mundo.abstracfactory.Invasor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiloAnimacionEnemigos extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger( HiloAnimacionEnemigos.class.getName() );

	Invasor               enemigo;
	InterfazSpaceInvaders interfaz;

	public HiloAnimacionEnemigos ( Invasor invasores, InterfazSpaceInvaders principal ) {
		enemigo = invasores;
		interfaz = principal;
	}

	@Override
	public void run() {

		while ( interfaz.estaEnFuncionamiento() ) {

			if ( enemigo instanceof CalamarInvasor ) {
				enemigo.setRutaImage( "./src/main/resources/data/imagenes/Naves/s0.png" );
			} else if ( enemigo instanceof CangrejoInvasor ) {
				enemigo.setRutaImage( "./src/main/resources/data/imagenes/Naves/p0.png" );
			} else {
				enemigo.setRutaImage( "./src/main/resources/data/imagenes/Naves/r0.png" );
			}

			try {
				sleep( 1000 );
			} catch ( InterruptedException e ) {
				LOGGER.info( e.getMessage() ); // Compliant
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}

			enemigo.setRutaImage( enemigo.getRutaImagen2() );

			try {
				sleep( 1000 );
			} catch ( InterruptedException e ) {
				LOGGER.info( e.getMessage() );
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}

		}

	}

}
