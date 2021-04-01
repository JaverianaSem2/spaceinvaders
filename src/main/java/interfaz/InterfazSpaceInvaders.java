package interfaz;

import control.Teclado;
import excepciones.NicknameYaExisteException;
import excepciones.PartidaYaExisteException;
import hilos.*;
import mundo.NaveJugador;
import mundo.Partida;
import mundo.SpaceInvaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterfazSpaceInvaders extends JFrame {

	private static final Logger LOGGER = LoggerFactory.getLogger( InterfazSpaceInvaders.class.getName() );

	private static final long serialVersionUID = 1L;

	private static final CardLayout              card      = new CardLayout();
	private static final String                  INICIO    = "Inicio";
	private static final String                  MENU      = "Menú";
	private static final String                  JUEGO     = "Juego";
	private final        Container               contenedor;
	private final        SpaceInvaders           mundo;
	private final        PanelMenu               panelMenu = new PanelMenu( this );
	private final        PanelNivel              panelNivel;
	private              HiloEnemigos            hilitoEnemigo;
	private              HiloDisparoJugador      hilitoDisparo;
	private              HiloDisparoEnemigos     hilitoEnemigoDisparo;
	private              HiloAuxiliarCreaDisparo hilitoAuxiliar;
	private              HiloAnimacionEnemigos   hilitoAnimacion;
	private              boolean                 pausa;

	public InterfazSpaceInvaders () {

		mundo = new SpaceInvaders( false );

		panelNivel = new PanelNivel( mundo.getPartidaActual(), mundo, this );

		PanelImagenInicial imagen = new PanelImagenInicial( this );
		addKeyListener( imagen );
		contenedor = getContentPane();
		card.addLayoutComponent( imagen, INICIO );
		card.addLayoutComponent( panelMenu, MENU );
		card.addLayoutComponent( panelNivel, JUEGO );

		contenedor.add( imagen );
		contenedor.add( panelMenu );
		contenedor.add( panelNivel );

		contenedor.setLayout( card );
		card.show( contenedor, INICIO );

		addKeyListener( Teclado.getInstance( this, mundo ) );

		setSize( 640, 480 );
		setUndecorated( true );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		getRootPane().setBorder( BorderFactory.createLineBorder( Color.WHITE ) );

	}

	public static void main ( String[] args ) {
		InterfazSpaceInvaders ventana = new InterfazSpaceInvaders();
		ventana.setVisible( true );
	}

	public void cambiarPanel ( String nombre ) {
		if ( nombre.equals( MENU ) ) {
			card.show( contenedor, MENU );
		} else if ( nombre.equals( JUEGO ) ) {
			card.show( contenedor, JUEGO );
		}
	}

	public void cerrar () {
		try {
			mundo.serializarJugador();
		} catch ( IOException e ) {
			LOGGER.info( e.getMessage() );
		}
		System.exit( 0 );
	}

	public boolean estaEnPausa () {
		return pausa;
	}

	public void cambiarPausa ( boolean paus ) {
		this.pausa = paus;
	}

	public void startHiloEnemigo () {
		for ( int i = 0; i < mundo.getPartidaActual().getEnemigos().length; i++ ) {
			for ( int j = 0; j < mundo.getPartidaActual().getEnemigos()[0].length; j++ ) {
				if ( mundo.getPartidaActual().getEnemigos()[i][j] != null ) {
					hilitoEnemigo = new HiloEnemigos( mundo.getPartidaActual().getEnemigos()[i][j], this );
					hilitoEnemigo.start();
				}
			}
		}
	}

	public void startHiloAuxiliar () {
		hilitoAuxiliar = new HiloAuxiliarCreaDisparo( mundo.getPartidaActual(), this );
		hilitoAuxiliar.start();
	}

	public void startHiloAnimacion () {
		for ( int i = 0; i < mundo.getPartidaActual().getEnemigos().length; i++ ) {
			for ( int j = 0; j < mundo.getPartidaActual().getEnemigos()[0].length; j++ ) {
				if ( mundo.getPartidaActual().getEnemigos()[i][j] != null ) {
					hilitoAnimacion = new HiloAnimacionEnemigos( mundo.getPartidaActual().getEnemigos()[i][j], this );
					hilitoAnimacion.start();
				}
			}
		}
	}

	public void startHiloDisparoEnemigo () {
		hilitoEnemigoDisparo = new HiloDisparoEnemigos( mundo.getPartidaActual(), this, mundo );
		hilitoEnemigoDisparo.start();
	}

	public void startHiloJugador () {
		hilitoDisparo = new HiloDisparoJugador( mundo.getJugadorActual(), this, mundo.getPartidaActual().getEnemigos(), mundo.getPartidaActual() );
		hilitoDisparo.start();
	}

	public int darPosActualJugador () {
		return panelNivel.getPosJugadorActualX();
	}

	public boolean estaEnFuncionamiento () {
		return mundo.getEnFuncionamiento();
	}

	public void modificarFuncionamiento ( boolean salida ) {
		mundo.setEnFuncionamiento( salida );
	}

	public PanelNivel getPanelNivel () {
		return panelNivel;
	}

	public NaveJugador getJugadorActual () {
		return mundo.getJugadorActual();
	}

	public void iniciarTodosLosHilos () {
		mundo.setEnFuncionamiento( true );
		startHiloJugador();
		startHiloEnemigo();
		startHiloAnimacion();
		startHiloAuxiliar();
		startHiloDisparoEnemigo();
	}

	public void matarHilos () {
		hilitoAnimacion = null;
		hilitoAuxiliar = null;
		hilitoDisparo = null;
		hilitoEnemigoDisparo = null;
		hilitoEnemigo = null;
	}

	public void reqCrearPartida ( String nombre ) {
		try {
			mundo.crearPartida( nombre );
			mundo.getPartidaActual().inicializarPartida();
			actualizarPartidas();
			actualizarPartidaActual( nombre );
			panelNivel.setPartida( mundo.getPartidaActual() );
			mundo.iniciarPartida();
			cambiarPanel( JUEGO );
		} catch ( PartidaYaExisteException | IOException e ) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Error al crear la partida", JOptionPane.ERROR_MESSAGE );
		}
	}

	public void reqAgregarJugador ( String nombre, String nickname ) {
		try {
			mundo.agregarJugador( nombre, nickname );
			panelMenu.repaint();
			actualizarJugadores();
			actualizarJugadorActual( nickname );
		} catch ( NicknameYaExisteException | IOException e ) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Error al agregar el jugador", JOptionPane.ERROR_MESSAGE );
		}
	}

	public void actualizarJugadorActual ( String nickname ) {
		if ( !nickname.equals( "" ) ) {
			NaveJugador actual = mundo.buscarJugador( nickname );
			mundo.setJugadorActual( actual );
			panelMenu.repaint();
		} else {
			JOptionPane.showMessageDialog( this, "Por favor cree algún jugador", "No existen jugadores", JOptionPane.INFORMATION_MESSAGE );
		}
	}

	public void actualizarPartidaActual ( String nombre ) {
		Partida partidaActual = mundo.getJugadorActual().getPartidaRaiz().buscarPartida( nombre );
		mundo.setPartidaActual( partidaActual );
		panelNivel.setPartida( partidaActual );
		iniciarTodosLosHilos();
	}

	public void actualizarJugadores () {
		List<NaveJugador> jugadores = mundo.getJugadores();
		jugadores = ( jugadores == null ) ? new ArrayList<>() : jugadores;
		panelMenu.getDialogoSeleccionarJugador().cambiarListaJugadores( jugadores );
	}

	public void actualizarPartidas () {
		List<Partida> partidas = mundo.darPartidasJugador();

		if ( partidas.size() == 0 ) {
			partidas = new ArrayList<>();
		}
		panelMenu.getDialogoSeleccionarPartida().cambiarListaPartidas( partidas );
	}

	public void nivelCompleto () {
		try {
			if ( mundo.getPartidaActual().nivelCompleto() ) {
				iniciarTodosLosHilos();
			} else {
				panelMenu.repaint();
				mundo.eliminarPartida();
				actualizarPartidas();
				cambiarPanel( MENU );
				panelMenu.repaint();
			}
		} catch ( IOException e ) {
			LOGGER.info( e.getMessage() );
		}
	}

	public void perder () {
		panelMenu.repaint();
		try {
			mundo.eliminarPartida();
		} catch ( IOException e ) {
			LOGGER.info( e.getMessage() );
		}
		actualizarPartidas();
		cambiarPanel( MENU );
		panelMenu.repaint();
	}

	public void ordenarJugadores () {
		List<NaveJugador> jugadores = mundo.ordenarPorNickname();
		jugadores = ( jugadores == null ) ? new ArrayList<>() : jugadores;
		panelMenu.getDialogoSeleccionarJugador().cambiarListaJugadores( jugadores );
	}

	public void loginRapido ( String nickname ) {
		if ( !mundo.busquedaRapida( nickname ) ) {
			JOptionPane.showMessageDialog( null, "El jugador con el nickname " + nickname + " no existe", "Jugador no encontrado", JOptionPane.ERROR_MESSAGE );
		}
		panelMenu.repaint();
	}

	public void mejoresPuntajes () {
		panelMenu.setDialogoMejoresPuntajes( new DialogoMejoresPuntajes( this, mundo.mejoresPuntajes() ) );
		panelMenu.getDialogoMejoresPuntajes().mostrar();
	}

}
