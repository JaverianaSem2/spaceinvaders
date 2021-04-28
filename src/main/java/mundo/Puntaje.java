package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		return puntuacion;
	}

	public void setPuntuacion ( int puntuacion ) {
		this.puntuacion += puntuacion;
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

	public static List<String> getMejoresPuntajes ( Puntaje primer ) {

		List<String> mejoresPuntajes = new ArrayList<>();
		int contador = 1;
		while ( primer != null && contador <= 10 ) {
			mejoresPuntajes.add( contador + " " + primer.toString() );
			contador++;
			primer = primer.getSiguiente();
		}

		return mejoresPuntajes;
	}

	public static Puntaje agregarPuntaje ( Puntaje primerPuntaje, Puntaje puntaje ) {
		if ( primerPuntaje == null ) {
			primerPuntaje = puntaje;
		} else {
			if ( primerPuntaje.getPuntuacion() < puntaje.getPuntuacion() ) {
				puntaje.setSiguiente( primerPuntaje );
				puntaje.setAnterior( puntaje );
				primerPuntaje = puntaje;
			} else {
				Puntaje aux = primerPuntaje;

				while ( aux.getSiguiente() != null && aux.getSiguiente().getPuntuacion() >= puntaje.getPuntuacion() ) {
					aux = aux.getSiguiente();
				}

				Puntaje nuevaSiguiente = null;

				if ( aux.getSiguiente() != null ) {
					nuevaSiguiente = aux.getSiguiente();
					nuevaSiguiente.setAnterior( puntaje );
				}

				aux.setSiguiente( puntaje );
				puntaje.setAnterior( aux );
				puntaje.setSiguiente( nuevaSiguiente );
			}
		}
		return primerPuntaje;
	}

}
