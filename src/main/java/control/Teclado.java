package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import interfaz.InterfazSpaceInvaders;
import mundo.NaveJugador;
import mundo.SpaceInvaders;

/**
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 *         Proyecto final - Algoritmos y programación II.
 */
public class Teclado implements KeyListener {

	// -----------------------------------------------------------------
	// ----------------------------Atributos----------------------------
	// -----------------------------------------------------------------

	// public Partida actu;

	private SpaceInvaders actu;

	public NaveJugador navesita;

	public InterfazSpaceInvaders interfaz;

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public Teclado(InterfazSpaceInvaders principal, SpaceInvaders actual) {
		interfaz = principal;
		actu = actual;
		navesita = actu.getJugadorActual();

	}

	public void keyPressed(KeyEvent e) {

		if (actu.getEnFuncionamiento()) {
			navesita = actu.getJugadorActual();
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {

				if (navesita.getDisparoUno() == null) {
					navesita.disparar(interfaz.darPosActualJugador(), 410);
					interfaz.startHiloJugador();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				navesita.mover(-1);
				interfaz.getPanelNivel().updateUI();
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				navesita.mover(1);
				interfaz.getPanelNivel().updateUI();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			interfaz.cerrar();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (interfaz.estaEnPausa()) {
				interfaz.modificarFuncionamiento(true);
				interfaz.cambiarPausa(false);
				interfaz.iniciarTodosLosHilos();
			} else {
				interfaz.modificarFuncionamiento(false);
				interfaz.cambiarPausa(true);
			}
		}
	}

	/**
	 * 
	 */
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * 
	 */
	public void keyTyped(KeyEvent e) {

	}

}
