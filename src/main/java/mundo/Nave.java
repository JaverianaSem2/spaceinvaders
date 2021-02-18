package mundo;

import java.io.Serializable;

/**
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 *         Proyecto final - Algoritmos y programación II.
 */
public abstract class Nave implements Serializable {

	// -----------------------------------------------------------------
	// ---------------------------Asociaciones--------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	Disparo disparoUno;

	// -----------------------------------------------------------------
	// ----------------------------Atributos----------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	private double velocidad;

	/**
	 * 
	 */
	protected int posX;

	/**
	 * 
	 */
	protected int posY;

	/**
	 * 
	 */
	private int vida;

	/**
	 * 
	 */
	private int ancho;

	/**
	 * 
	 */
	private int alto;

	/**
	 * 
	 */
	private String rutaImage;

	// -----------------------------------------------------------------
	// ---------------------------Constructor---------------------------
	// -----------------------------------------------------------------


	public Nave() {
		this.velocidad = 0;
		this.posX = 0;
		this.posY = 0;
		this.vida = 0;
		this.ancho = 0;
		this.alto = 0;
	}

	public Nave(double velocidad, int posX, int posY, int vida, int ancho, int alto, String ruta) {
		this.velocidad = velocidad;
		this.posX = posX;
		this.posY = posY;
		this.vida = vida;
		this.ancho = ancho;
		this.alto = alto;
		rutaImage = ruta;
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public String getRutaImage() {
		return rutaImage;
	}

	public void setRutaImage(String rutaImage) {
		this.rutaImage = rutaImage;
	}

	/**
	 * 
	 * @return
	 */
	public double getVelocidad() {
		return this.velocidad;
	}

	/**
	 * 
	 * @param velocidad
	 */
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * 
	 * @return
	 */
	public int getPosX() {
		return this.posX;
	}

	/**
	 * 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @return
	 */
	public int getPosY() {
		return this.posY;
	}

	/**
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 * @return
	 */
	public int getVida() {
		return this.vida;
	}

	/**
	 * 
	 * @param vida
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}

	/**
	 * 
	 * @param dagno
	 */
	public void golpe (int dagno) {
		this.vida = this.vida - dagno;
	}
	
	/**
	 * 
	 * @param ancho
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * 
	 * @return
	 */
	public int getAncho() {
		return this.ancho;
	}

	/**
	 * 
	 * @return
	 */
	public int getAlto() {
		return this.alto;
	}

	/**
	 * 
	 * @param alto
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * 
	 * @return
	 */
	public Disparo getDisparoUno() {
		return disparoUno;
	}

	/**
	 * 
	 * @param disparoUno
	 */
	public void setDisparoUno(Disparo disparoUno) {
		this.disparoUno = disparoUno;
	}

	/**
	 * 
	 * @return
	 */
	public boolean estaViva() {
		return vida != 0;
	}

	/**
	 * 
	 */
	public void mover(int dir) {
	}

	/**
	 * 
	 */
	public void resetear() {
	}
	
	/**
	 * 
	 */
	public void eliminarDisparo() {
		disparoUno = null;
	}
	
	/**
	 * 
	 * @param posX
	 * @param posY
	 */
	public void disparar (int posX, int posY) {
		
		if (disparoUno == null) {
			disparoUno = new Disparo(posX, posY);
		}
		
	}

}