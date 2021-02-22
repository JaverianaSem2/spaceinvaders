package mundo;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class InvasorPulpo extends Enemigo {

	public InvasorPulpo(double velocidad, int posX, int posY, int vida, int ancho, int direccion, String rutaImage, String ruta) {
		super(velocidad, posX, posY, vida, ancho, direccion, rutaImage, ruta);

		setPuntosPorMuerte( 10 );
	}

}