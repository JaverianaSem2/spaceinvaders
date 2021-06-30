package mundo;

import java.io.Serializable;

/**
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public abstract class Nave implements Serializable {

	Disparo disparoUno;

	private       double velocidad;
	protected     int    posX;
	protected     int    posY;
	private       int    vida;
	private       int    ancho;
	private       String rutaImage;

	protected Nave () {
		this.velocidad = 0;
		this.posX = 0;
		this.posY = 0;
		this.vida = 0;
		this.ancho = 0;
	}

	protected Nave(double velocidad, int posX, int posY, int vida, int ancho, String ruta) {
		this.velocidad = velocidad;
		this.posX = posX;
		this.posY = posY;
		this.vida = vida;
		this.ancho = ancho;
		rutaImage = ruta;
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public String getRutaImage () {
		return rutaImage;
	}

	public void setRutaImage ( String rutaImage ) {
		this.rutaImage = rutaImage;
	}

	public double getVelocidad () {
		return velocidad;
	}

	public void setVelocidad ( double velocidad ) {
		this.velocidad = velocidad;
	}

	public int getPosX () {
		return posX;
	}

	public void setPosX ( int posX ) {
		this.posX = posX;
	}

	public int getPosY () {
		return posY;
	}

	public int getVida () {
		return vida;
	}

	public void setVida ( int vida ) {
		this.vida = vida;
	}

	public void golpe ( int dagno ) {
		this.vida = this.vida - dagno;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAncho () {
		return ancho;
	}

	public Disparo getDisparoUno () {
		return disparoUno;
	}

	public void setDisparoUno ( Disparo disparoUno ) {
		this.disparoUno = disparoUno;
	}

	public boolean estaViva () {
		return vida != 0;
	}

	public void mover(int dir) { }

	public void eliminarDisparo () {
		disparoUno = null;
	}

	public void disparar ( int posX, int posY ) {

		if ( disparoUno == null ) {
			disparoUno = new Disparo( posX, posY );
		}

	}

}