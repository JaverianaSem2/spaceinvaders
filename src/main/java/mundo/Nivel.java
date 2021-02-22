package mundo;

import java.io.Serializable;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class Nivel implements Serializable {

	private       String nivel;
	private final int    velocidadEnemigos;
	private final int    vidaEnemigos;
	private final int    posXPrimerEnemigo;
	private final int    posYPrimerEnemigo;
	private final int    anchoEnemigos;
	private final int    enemigos;
	private final int    altoEnemigos;

	public Nivel(String nivel, int velocidad, int enemigos, int vidaEnemigos , int posXPrimerEnemigo, int posYPrimerEnemigo,
			int anchoEnemigos, int altoEnemigos) {

		this.nivel = nivel;
		this.velocidadEnemigos = velocidad;
		this.enemigos = enemigos;
		this.vidaEnemigos = vidaEnemigos;
		this.posXPrimerEnemigo = posXPrimerEnemigo;
		this.posYPrimerEnemigo = posYPrimerEnemigo;
		this.anchoEnemigos = anchoEnemigos;
		this.altoEnemigos = altoEnemigos;
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public String getNivel () {
		return this.nivel;
	}

	public void setNivel ( String nivel ) {
		this.nivel = nivel;
	}

	public int getVelocidadEnemigos () {
		return velocidadEnemigos;
	}

	public int getVidaEnemigos () {
		return vidaEnemigos;
	}

	public int getPosXPrimerEnemigo () {
		return this.posXPrimerEnemigo;
	}

	public int getPosYPrimerEnemigo () {
		return this.posYPrimerEnemigo;
	}

	public int getAnchoEnemigos () {
		return anchoEnemigos;
	}

	public int getAltoEnemigos() {
		return altoEnemigos;
	}
}