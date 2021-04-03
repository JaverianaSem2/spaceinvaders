package mundo;

import excepciones.PartidaYaExisteException;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class NaveJugador extends Nave {

	private static final long serialVersionUID = 1L;

	private Partida partidaRaiz;

	private int posInicialX;

	private int posInicialY;

	private final String nickname;

	private final String nombre;

	private int cantidadDisparos;

	public NaveJugador ( String nombre, String nickname ) {
		partidaRaiz = null;
		this.posInicialX = 320;
		this.posInicialY = 410;
		this.setVida( 3 );
		this.nombre = nombre;
		this.nickname = nickname;
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void agregarPartida ( Partida agregar )
		throws PartidaYaExisteException {

		if ( partidaRaiz == null ) {
			partidaRaiz = agregar;
		} else {
			partidaRaiz.agregarPartida( agregar );
		}
	}

	public Partida crearPartida ( String nombre )
		throws PartidaYaExisteException {

		Partida b = new Partida( nombre );
		agregarPartida( b );

		return b;
	}

	public int getPosInicialX () {
		return posInicialX;
	}

	public void setPosInicialX ( int posInicialX ) {
		this.posInicialX = posInicialX;
	}

	public int getPosInicialY () {
		return posInicialY;
	}

	public void setPosInicialY ( int posInicialY ) {
		this.posInicialY = posInicialY;
	}

	public Partida getPartidaRaiz () {
		return partidaRaiz;
	}

	public void setPartidaRaiz ( Partida partidaRaiz ) {
		this.partidaRaiz = partidaRaiz;
	}

	public String getNombre () {
		return nombre;
	}

	public String getNickname () {
		return nickname;
	}

	public int getCantidadDisparos () {
		return cantidadDisparos;
	}

	public boolean perdio () {
		return getVida() == 0;
	}

	@Override public void mover ( int dir ) {
		super.mover( dir );
		posInicialX += dir * 10.0d;
	}

	@Override public String toString () {
		return nickname;
	}

	public void disparar ( int posX, int posY ) {

		if ( disparoUno == null ) {
			cantidadDisparos++;
			disparoUno = new Disparo( posX, posY );
		}
	}

}
