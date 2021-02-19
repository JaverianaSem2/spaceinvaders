package mundo;

/**
 *
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 *         Proyecto final - Algoritmos y programación II.
 */
public abstract class Enemigo extends Nave {

	public static final int IZQUIERDA = 0;
	public static final int DERECHA = 1;
	private int direccion;
	private String rutaImagen2;
	private int puntosPorMuerte;

	protected Enemigo(double velocidad, int posX, int posY, int vida, int ancho, int alto, int direccion, String rutaImage, String rutaImage2) {
		super(velocidad, posX, posY, vida, ancho, alto, rutaImage);
		this.direccion = direccion;
		rutaImagen2 = rutaImage2;
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public int getDireccion() {
		return direccion;
	}

	public String getRutaImagen2() {
		return rutaImagen2;
	}

	public void setRutaImagen2(String rutaImagen2) {
		this.rutaImagen2 = rutaImagen2;
	}
	
	public int getPuntosPorMuerte() {
		return puntosPorMuerte;
	}

	public void setPuntosPorMuerte(int puntosPorMuerte) {
		this.puntosPorMuerte = puntosPorMuerte;
	}

	public boolean edge (){
		return (posX > 599 || posX < 0);
	}
	
	@Override
	public void mover(int dir) {
		super.mover(dir);
		
		posX += dir*getVelocidad();
	}
	
	public void moverAbajo (int dir) {
		posY += dir*10;
	}
	
}
