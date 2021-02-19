package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import excepciones.NicknameYaExisteException;
import excepciones.PartidaYaExisteException;

/**
 * Clase principal del mundo que representa el juego.
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 *         Proyecto final - Algoritmos y programación II.
 */
public class SpaceInvaders {

	private ArrayList<NaveJugador> jugadores;
	private Partida partidaActual;
	private NaveJugador jugadorActual;
	private Puntaje primerPuntaje;

	private boolean enFuncionamiento;

	public SpaceInvaders(boolean enFuncionamiento) {

		this.enFuncionamiento = enFuncionamiento;

		jugadores = new ArrayList<>();

		partidaActual = null;

		jugadorActual = null;

		primerPuntaje = null;

		try {
			deserializarJugador();
			deserializarPuntaje();
		} catch (ClassNotFoundException | IOException ignored ) {
			// Do nothing
		}
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public boolean getEnFuncionamiento() {
		return this.enFuncionamiento;
	}

	public void setEnFuncionamiento(boolean enFuncionamiento) {
		this.enFuncionamiento = enFuncionamiento;
	}

	public List<NaveJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<NaveJugador> jugadores) {
		this.jugadores = (ArrayList<NaveJugador>) jugadores;
	}

	public Partida getPartidaActual() {
		return partidaActual;
	}

	public void setPartidaActual(Partida partidaActual) {
		this.partidaActual = partidaActual;
	}

	public NaveJugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(NaveJugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	public NaveJugador buscarJugador(String nickname) {
		NaveJugador naveBuscada = null;
		boolean buscado = false;

		for (int i = 0; i < jugadores.size() && !buscado; i++) {
			if (jugadores.get(i).getNickname().equalsIgnoreCase(nickname)) {
				naveBuscada = jugadores.get(i);
				buscado = true;
			}
		}

		return naveBuscada;
	}

	public void agregarJugador(String nombre, String nickname) throws NicknameYaExisteException, IOException {

		if (buscarJugador(nickname) == null) {
			NaveJugador agregar = new NaveJugador(nombre, nickname);
			jugadores.add(agregar);
			jugadorActual = agregar;
			jugadorActual.setPosInicialX(300);
			jugadorActual.setPosIncialY(410);
			jugadorActual.setAncho(30);
			jugadorActual.setAlto(19);
			serializarJugador();
		} else {
			throw new NicknameYaExisteException( nickname );
		}
	}

	public void serializarJugador() throws IOException {

		File archivo = new File("./src/main/resources/data/jugador");

		try ( FileOutputStream fos = new FileOutputStream( archivo ) ) {
			ObjectOutputStream oos = new ObjectOutputStream( fos );
			oos.writeObject( jugadores );

			oos.close();
		}
	}

	public void iniciarPartida () {
		jugadorActual.setVida(3);
	}

	@SuppressWarnings("unchecked")
	public void deserializarJugador() throws IOException, ClassNotFoundException {

		File archivo = new File("./src/main/resources/data/jugador");

		try ( FileInputStream fis = new FileInputStream( archivo ) ) {
			ObjectInputStream ois = new ObjectInputStream( fis );

			jugadores = (ArrayList<NaveJugador>) ois.readObject();

			ois.close();
		}
	}

	public List<Partida> darPartidasJugador() {
		List<Partida> partidas = new ArrayList<>();

		if (jugadorActual.getPartidaRaiz() != null) {
			jugadorActual.getPartidaRaiz().inOrden( partidas );
		}

		return partidas;
	}

	public void crearPartida(String nombre) throws PartidaYaExisteException, IOException {
		partidaActual = jugadorActual.crearPartida(nombre);
		partidaActual.setPuntaje(new Puntaje(0, jugadorActual.getNickname(), partidaActual.getNombre()));
		serializarJugador();
	}

	@SuppressWarnings("unchecked")
	public List<NaveJugador> ordenarPorNickname() {

		List<NaveJugador> jugadoresOrdenados = (List<NaveJugador>) jugadores.clone();

		for (int i = 1; i < jugadoresOrdenados.size(); i++) {
			for (int j = i; j > 0 && jugadoresOrdenados.get(j - 1).getNickname()
					.compareTo(jugadoresOrdenados.get(j).getNickname()) > 0; j--) {
				NaveJugador temp = jugadoresOrdenados.get(j);
				jugadoresOrdenados.set(j, jugadoresOrdenados.get(j - 1));
				jugadoresOrdenados.set(j - 1, temp);
			}
		}
		return jugadoresOrdenados;

	}

	public boolean busquedaRapida(String nickname){

		List<NaveJugador> jugadoresOrdenados = ordenarPorNickname();
		boolean encontrado = false;

		int posicion = -1;
		int inicio = 0;
		int fin = jugadoresOrdenados.size() - 1;
		while ( inicio <= fin && posicion == -1 )	{
			int medio = ( inicio + fin ) / 2;
			NaveJugador mitad = jugadoresOrdenados.get( medio );

			if ( mitad.getNickname().compareToIgnoreCase(nickname) == 0 ) {
				posicion = medio;
				encontrado = true;
			} else if ( mitad.getNickname().compareToIgnoreCase(nickname) > 0 ) {
				fin = medio - 1;
			} else {
				inicio = medio + 1;
			}
		}

		if ( encontrado ) {
			jugadorActual = jugadoresOrdenados.get( posicion );
		}

		return encontrado;
	}

	public void agregarPuntaje(Puntaje puntaje) {
		if (primerPuntaje == null) {
			primerPuntaje = puntaje;
		} else {   
			if (primerPuntaje.getPuntuacion() < puntaje.getPuntuacion()) {
				puntaje.setSiguiente(primerPuntaje);
				puntaje.setAnterior(puntaje);
				primerPuntaje = puntaje;
			} else {
				// TODO: Validar bucle infinito
				while (primerPuntaje.getSiguiente() != null && primerPuntaje.getSiguiente().getPuntuacion() >= puntaje.getPuntuacion()) {
					primerPuntaje = primerPuntaje.getSiguiente();
				}

				Puntaje nuevaSiguiente = null;

				if (primerPuntaje.getSiguiente() != null) {
					nuevaSiguiente = primerPuntaje.getSiguiente();
					nuevaSiguiente.setAnterior(puntaje);
				}

				primerPuntaje.setSiguiente(puntaje);
				puntaje.setAnterior(primerPuntaje);
				puntaje.setSiguiente(nuevaSiguiente);
			}
		}
	}

	public List<String> mejoresPuntajes(){

		List<String> mejoresPuntajes = new ArrayList<>();
		Puntaje primer = primerPuntaje;
		int contador = 1;
		while(primer != null && contador <= 10){
			mejoresPuntajes.add(contador + " " + primer.toString());
			contador++;
			primer = primer.getSiguiente();
		}

		return mejoresPuntajes;
	}

	public void serializarPuntaje() throws IOException {

		File archivo = new File("./src/main/resources/data/puntaje");

		try ( FileOutputStream fos = new FileOutputStream( archivo ) ) {
			ObjectOutputStream oos = new ObjectOutputStream( fos );

			oos.writeObject( primerPuntaje );

			oos.close();
		}
	}

	public void deserializarPuntaje() throws IOException, ClassNotFoundException {

		File archivo = new File("./src/main/resources/data/puntaje");

		try ( FileInputStream fis = new FileInputStream( archivo ) ) {
			ObjectInputStream ois = new ObjectInputStream( fis );

			primerPuntaje = (Puntaje) ois.readObject();

			ois.close();
		}
	}

	public int puntosPorVida(){
		return (jugadorActual.getVida()*200);
	}

	public int puntosPorDisparos(){
		return jugadorActual.getCantidadDisparos();
	}

	public void eliminarPartida() throws IOException {
		Puntaje nuevoPuntaje = new Puntaje(partidaActual.getPuntaje().getPuntuacion(), jugadorActual.getNickname(), partidaActual.getNombre());
		agregarPuntaje(nuevoPuntaje);
		jugadorActual.setPartidaRaiz(jugadorActual.getPartidaRaiz().eliminar(partidaActual.getNombre()));
		serializarJugador();
		serializarPuntaje();
	}
}
