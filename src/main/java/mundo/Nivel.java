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

	public Nivel(String nivel, int velocidad, int vidaEnemigos , int posXPrimerEnemigo, int posYPrimerEnemigo,
			int anchoEnemigos ) {

		this.nivel = nivel;
		this.velocidadEnemigos = velocidad;
		this.vidaEnemigos = vidaEnemigos;
		this.posXPrimerEnemigo = posXPrimerEnemigo;
		this.posYPrimerEnemigo = posYPrimerEnemigo;
		this.anchoEnemigos = anchoEnemigos;
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public String getNivel () {
		return nivel;
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
		return posXPrimerEnemigo;
	}

	public int getPosYPrimerEnemigo () {
		return posYPrimerEnemigo;
	}

	public int getAnchoEnemigos () {
		return anchoEnemigos;
	}

}