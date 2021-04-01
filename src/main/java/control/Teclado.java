package control;

import interfaz.InterfazSpaceInvaders;
import mundo.NaveJugador;
import mundo.SpaceInvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class Teclado implements KeyListener {

	private static Teclado instance = null;

	// public Partida actual

	private final SpaceInvaders         actu;
	private final InterfazSpaceInvaders interfaz;
	private       NaveJugador           navesita;

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	private Teclado ( InterfazSpaceInvaders principal, SpaceInvaders actual ) {
		interfaz = principal;
		actu = actual;
		navesita = actu.getJugadorActual();
	}

	public static Teclado getInstance( InterfazSpaceInvaders principal, SpaceInvaders actual ) {
		return Optional.ofNullable( instance )
			.orElse( new Teclado( principal, actual ) );
	}

	public void keyPressed ( KeyEvent e ) {

		if ( actu.getEnFuncionamiento() ) {
			navesita = actu.getJugadorActual();

			if ( e.getKeyCode() == KeyEvent.VK_SPACE && navesita.getDisparoUno() == null ) {
				navesita.disparar( interfaz.darPosActualJugador(), 410 );
				interfaz.startHiloJugador();
			}

			if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
				navesita.mover( -1 );
				interfaz.getPanelNivel().updateUI();
			}

			if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
				navesita.mover( 1 );
				interfaz.getPanelNivel().updateUI();
			}
		}

		if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
			interfaz.cerrar();
		}

		if ( e.getKeyCode() == KeyEvent.VK_P ) {
			if ( interfaz.estaEnPausa() ) {
				interfaz.modificarFuncionamiento( true );
				interfaz.cambiarPausa( false );
				interfaz.iniciarTodosLosHilos();
			} else {
				interfaz.modificarFuncionamiento( false );
				interfaz.cambiarPausa( true );
			}
		}
	}

	public void keyReleased ( KeyEvent e ) {
		// Empty method for control key press
	}

	public void keyTyped ( KeyEvent e ) {
		// Empty method for control key press
	}

}
