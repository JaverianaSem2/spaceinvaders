package hilos;

import interfaz.InterfazSpaceInvaders;
import mundo.Enemigo;
import mundo.NaveJugador;
import mundo.Partida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiloDisparoJugador extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger( HiloDisparoJugador.class.getName() );

	private final NaveJugador           navesita;
	private final InterfazSpaceInvaders interfaz;
	private final Enemigo[][]           enemigos;
	private final Partida               actual;

	public HiloDisparoJugador ( NaveJugador a, InterfazSpaceInvaders b, Enemigo[][] c, Partida d ) {
		navesita = a;
		interfaz = b;
		enemigos = c;
		actual = d;
	}

	@Override
	public void run() {

		while ( navesita.getDisparoUno() != null && !navesita.getDisparoUno().getImpacto() ) {

			navesita.getDisparoUno().shoot();

			for (int i = 0; i < enemigos.length && navesita.getDisparoUno() != null
					&& !navesita.getDisparoUno().getImpacto(); i++) {
				for (int j = 0; j < enemigos[0].length && navesita.getDisparoUno() != null
						&& !navesita.getDisparoUno().getImpacto(); j++) {
					if ( navesita.getDisparoUno().hitsEnemigo( enemigos[i][j] ) ) {
						navesita.getDisparoUno().setImpacto( true );
						actual.getPuntaje().setPuntuacion( enemigos[i][j].getPuntosPorMuerte() );
						actual.eliminarUnEnemigo( true, enemigos[i][j] );

						navesita.eliminarDisparo();
						interfaz.getPanelNivel().repaint();
					}
				}
			}

			try {
				sleep( 2 );
			} catch ( InterruptedException e ) {
				LOGGER.info( e.getMessage() );
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}
			interfaz.getPanelNivel().updateUI();

			if ( navesita.getDisparoUno() != null &&
				navesita.getDisparoUno().getPosY() <= 0) {
				navesita.getDisparoUno().setImpacto( true );
				navesita.eliminarDisparo();
			}
		}

	}

}
