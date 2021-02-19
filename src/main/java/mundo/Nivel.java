package mundo;

import java.io.Serializable;

/**
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 *         Proyecto final - Algoritmos y programación II.
 */
public class Nivel implements Serializable {

	public static final int CANTIDAD_CALAMAR = 10;
	public static final int CANTIDAD_PULPO = 20;
	public static final int CANTIDAD_CANGREJO = 20;

	private String nivel;
	private int velocidadEnemigos;
	private int enemigos;
	private int vidaEnemigos;
	private int posXPrimerEnemigo;
	private int posYPrimerEnemigo;
	private int anchoEnemigos;
	private int altoEnemigos;

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

	public String getNivel() {
		return this.nivel;
	}

	public int getVelocidadEnemigos() {
		return velocidadEnemigos;
	}

	public void setVelocidadEnemigos(int velocidadEnemigos) {
		this.velocidadEnemigos = velocidadEnemigos;
	}

	public int getVidaEnemigos() {
		return vidaEnemigos;
	}

	public void setVidaEnemigos(int vidaEnemigos) {
		this.vidaEnemigos = vidaEnemigos;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getEnemigos() {
		return this.enemigos;
	}

	public void setEnemigos(int enemigos) {
		this.enemigos = enemigos;
	}

	public int getPosXPrimerEnemigo() {
		return this.posXPrimerEnemigo;
	}

	public void setPosXPrimerEnemigo(int posXPrimerEnemigo) {
		this.posXPrimerEnemigo = posXPrimerEnemigo;
	}

	public int getPosYPrimerEnemigo() {
		return this.posYPrimerEnemigo;
	}

	public void setPosYPrimerEnemigo(int posYPrimerEnemigo) {
		this.posYPrimerEnemigo = posYPrimerEnemigo;
	}

	public int getAnchoEnemigos() {
		return anchoEnemigos;
	}

	public void setAnchoEnemigos(int anchoEnemigos) {
		this.anchoEnemigos = anchoEnemigos;
	}

	public int getAltoEnemigos() {
		return altoEnemigos;
	}

	public void setAltoEnemigos(int altoEnemigos) {
		this.altoEnemigos = altoEnemigos;
	}

	public void definirNivel() {
		// Empty method. Do not delete
	}
}