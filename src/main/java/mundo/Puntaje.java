package mundo;

import java.io.Serializable;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class Puntaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private       int     puntuacion;
	private final String  nickname;
	private final String  nombrePartida;
	private       Puntaje siguiente;
	private       Puntaje anterior;

	public Puntaje ( int puntuacion, String nickname, String nombrePartida ) {
		this.puntuacion = puntuacion;
		this.nickname = nickname;
		this.nombrePartida = nombrePartida;
	}

	public int getPuntuacion () {
		return this.puntuacion;
	}

	public void setPuntuacion ( int puntuacion ) {
		this.puntuacion = this.puntuacion + puntuacion;
	}

	public Puntaje getSiguiente () {
		return siguiente;
	}

	public void setSiguiente ( Puntaje siguiente ) {
		this.siguiente = siguiente;
	}

	public Puntaje getAnterior () {
		return anterior;
	}

	public void setAnterior ( Puntaje anterior ) {
		this.anterior = anterior;
	}

	@Override public String toString () {
		String puntos = puntuacion + "";
		return "" + puntos + " " + nickname + " " + nombrePartida;
	}

}
