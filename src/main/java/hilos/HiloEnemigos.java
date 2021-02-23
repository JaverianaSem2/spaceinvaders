package hilos;

import interfaz.InterfazSpaceInvaders;
import mundo.Enemigo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiloEnemigos extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger( HiloEnemigos.class.getName() );

	InterfazSpaceInvaders interfaz;
	Enemigo               enemigo;

	public HiloEnemigos ( Enemigo invasores, InterfazSpaceInvaders interfaz ) {
		enemigo = invasores;
		this.interfaz = interfaz;
	}

	@Override
	public void run() {
		while ( interfaz.estaEnFuncionamiento() ) {

			if ( enemigo.getDireccion() == Enemigo.DERECHA ) {
				enemigo.mover( 1 );
			} else {
				enemigo.mover( -1 );
			}

			if ( enemigo.edge() ) {
				enemigo.moverAbajo( 2 );
				if ( enemigo.getDireccion() == Enemigo.DERECHA ) {
					enemigo.setDireccion( Enemigo.IZQUIERDA );
				} else {
					enemigo.setDireccion( Enemigo.DERECHA );
				}
			}

			try {
				sleep( 80 );
			} catch ( InterruptedException e ) {
				LOGGER.info( e.getMessage() );
				Thread.currentThread().interrupt();
			}

			interfaz.getPanelNivel().updateUI();

			if ( enemigo.getDisparoUno() != null && enemigo.getDisparoUno().getPosY() >= 420 ) {
				enemigo.getDisparoUno().setImpacto( true );
				enemigo.eliminarDisparo();
			}

		}

	}

}