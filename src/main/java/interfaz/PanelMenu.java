package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel que contiene el menú principal del juego
 *
 * @author Juan Sebastián Quintero Yoshioka - Manuel Alejandro Coral Lozano
 * Proyecto final - Algoritmos y programación II
 */
public class PanelMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String CREAR_JUGADOR       = "CREAR JUGADOR";
	private static final String SELECCIONAR_JUGADOR = "SELECCIONAR JUGADOR";
	private static final String CREAR_PARTIDA       = "CREAR PARTIDA";
	private static final String SELECCIONAR_PARTIDA = "SELECCIONAR PARTIDA";
	private static final String INSTRUCCIONES       = "INSTRUCCIONES";

	private static final int LABEL_HEIGHT         = 40;
	private static final int LABEL_X_POSITION     = 10;
	private static final int LABEL_BOTTOM_PADDING = 5;

	InterfazSpaceInvaders     interfaz;
	DialogoCrearJugador       dialogoCrearJugador;
	DialogoSeleccionarJugador dialogoSeleccionarJugador;
	DialogoCrearPartida       dialogoCrearPartida;
	DialogoSeleccionarPartida dialogoSeleccionarPartida;
	DialogoInstrucciones      dialogoInstrucciones;
	DialogoMejoresPuntajes    dialogoMejoresPuntajes;

	JPopupMenu popMenuJugar;
	JMenuItem  menuCrearPartida;
	JMenuItem  menuCargarPartida;

	JPopupMenu popMenuJugador;
	JMenuItem  menuNuevoJugador;
	JMenuItem  menuSeleccionarJugador;

	JLabel labOpenMenuJugar;
	JLabel labOpenMenuJugador;
	JLabel labLoginRapido;
	JLabel labMejoresPuntajes;
	JLabel labInstrucciones;

	public PanelMenu ( InterfazSpaceInvaders interfaz ) {

		// Inicializa la asociación
		this.interfaz = interfaz;

		// Establece el tamaño, la contenedora de tamaño y le quita el fondo que
		// trae por defecto.
		setPreferredSize( new Dimension( 640, 480 ) );
		setLayout( null );
		setOpaque( false );

		// Posición Y inicial para las labels
		int labelYPosition = 240;

		// Título del juego: "SPACE INVADERS"
		JLabel space = new JLabel( "SPACE INVADERS" );
		space.setForeground( Color.WHITE );
		space.setFont( FuenteInterfazGrafica.get( 74 ) );
		space.setBounds( 5, 75, 560, 80 );
		add( space );

		// Inicializa los 4 diálogos que se puede ver en el menú
		dialogoCrearJugador = new DialogoCrearJugador( interfaz );
		dialogoCrearPartida = new DialogoCrearPartida( interfaz );
		dialogoSeleccionarJugador = new DialogoSeleccionarJugador( interfaz );
		dialogoSeleccionarPartida = new DialogoSeleccionarPartida( interfaz );
		dialogoInstrucciones = new DialogoInstrucciones( interfaz );

		// Popup Menu Jugar
		popMenuJugar = new JPopupMenu();

		// Item1 de Menu Jugar
		menuCrearPartida = new JMenuItem( CREAR_PARTIDA );
		menuCrearPartida.setFont( FuenteInterfazGrafica.get( 30 ) );

		// Item2 de Menu Jugar
		menuCargarPartida = new JMenuItem( "CARGAR PARTIDA" );
		menuCargarPartida.setFont( FuenteInterfazGrafica.get( 30 ) );

		// Añadir los action listener a los item's de Menu Jugar
		menuCrearPartida.addActionListener( this );
		menuCrearPartida.setActionCommand( CREAR_PARTIDA );
		menuCargarPartida.addActionListener( this );
		menuCargarPartida.setActionCommand( SELECCIONAR_PARTIDA );

		// Agregar al Menu Jugar los Item's
		popMenuJugar.add( menuCrearPartida );
		popMenuJugar.add( menuCargarPartida );

		// Popup Menu Jugador
		popMenuJugador = new JPopupMenu();

		// Item1 de Menu Jugador
		menuNuevoJugador = new JMenuItem( "NUEVO JUGADOR" );
		menuNuevoJugador.setFont( FuenteInterfazGrafica.get( 30 ) );

		// Item2 de Menu Jugador
		menuSeleccionarJugador = new JMenuItem( SELECCIONAR_JUGADOR );
		menuSeleccionarJugador.setFont( FuenteInterfazGrafica.get( 30 ) );

		// Añadir los action listener a los Item's de Menu Jugador
		menuNuevoJugador.addActionListener( this );
		menuNuevoJugador.setActionCommand( CREAR_JUGADOR );
		menuSeleccionarJugador.addActionListener( this );
		menuSeleccionarJugador.setActionCommand( SELECCIONAR_JUGADOR );

		// Agregar al Menu Jugar los Item's
		popMenuJugador.add( menuNuevoJugador );
		popMenuJugador.add( menuSeleccionarJugador );

		// Boton Open Menu Jugar
		labOpenMenuJugar = new JLabel( "JUGAR" );
		labOpenMenuJugar.setBounds( LABEL_X_POSITION, labelYPosition, 127, LABEL_HEIGHT );
		labOpenMenuJugar.setFont( FuenteInterfazGrafica.get( 40 ) );
		labOpenMenuJugar.setForeground( Color.WHITE );
		labOpenMenuJugar.setAlignmentX( RIGHT_ALIGNMENT );
		labOpenMenuJugar.addMouseListener( new MouseAdapter() {
			@Override public void mouseClicked ( MouseEvent e ) {
				popMenuJugar.show( e.getComponent(), e.getX(), e.getY() );
			}
		} );
		add( labOpenMenuJugar );

		// Boton Open Menu Jugador
		labOpenMenuJugador = new JLabel( "JUGADOR" );
		labOpenMenuJugador.setFont( FuenteInterfazGrafica.get( 40 ) );
		labelYPosition = labelYPosition + LABEL_HEIGHT + LABEL_BOTTOM_PADDING;
		labOpenMenuJugador.setBounds( LABEL_X_POSITION, labelYPosition, 192, LABEL_HEIGHT );
		labOpenMenuJugador.setForeground( Color.WHITE );
		labOpenMenuJugador.setAlignmentX( RIGHT_ALIGNMENT );
		labOpenMenuJugador.addMouseListener( new MouseAdapter() {
			@Override public void mouseClicked ( MouseEvent e ) {
				popMenuJugador.show( e.getComponent(), e.getX(), e.getY() );
			}
		} );
		add( labOpenMenuJugador );

		// JLabel de las mejoras
		labLoginRapido = new JLabel( "LOGIN RaPIDO" );
		labLoginRapido.setForeground( Color.WHITE );
		labelYPosition = labelYPosition + LABEL_HEIGHT + LABEL_BOTTOM_PADDING;
		labLoginRapido.setBounds( LABEL_X_POSITION, labelYPosition, 288, LABEL_HEIGHT );
		labLoginRapido.setFont( FuenteInterfazGrafica.get( 40 ) );
		add( labLoginRapido );
		labLoginRapido.addMouseListener( new MouseAdapter() {
			@Override public void mouseClicked ( MouseEvent e ) {
				String respuesta = JOptionPane.showInputDialog( null, "Por favor ingresa tu nickname", "Login rápido", JOptionPane.PLAIN_MESSAGE );
				if ( respuesta == null || respuesta.trim().isEmpty() ) {
					JOptionPane.showMessageDialog( null, "Por favor ingresar un nickname válido", "Error al escribir el nickname", JOptionPane.ERROR_MESSAGE );
				} else if ( respuesta.length() != 5 ) {
					JOptionPane.showMessageDialog( null, "Recuerde que el nickname contiene 5 dígitos", "Error al escribir el nickname", JOptionPane.ERROR_MESSAGE );
				} else
					interfaz.loginRapido( respuesta );
			}
		} );

		// JLabel con las instrucciones
		labInstrucciones = new JLabel( INSTRUCCIONES );
		labInstrucciones.setForeground( Color.WHITE );
		labInstrucciones.setFont( FuenteInterfazGrafica.get( 40 ) );
		labelYPosition = labelYPosition + LABEL_HEIGHT + LABEL_BOTTOM_PADDING;
		labInstrucciones.setBounds( LABEL_X_POSITION, labelYPosition, 316, LABEL_HEIGHT );
		labInstrucciones.addMouseListener( new MouseListener() {

			@Override public void mouseReleased ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mousePressed ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mouseExited ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mouseEntered ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mouseClicked ( MouseEvent e ) {
				dialogoInstrucciones.mostrar();
			}
		} );
		add( labInstrucciones );

		// JLabel de los mejores puntajes
		labMejoresPuntajes = new JLabel( "MEJORES PUNTAJES" );
		labMejoresPuntajes.setForeground( Color.WHITE );
		labMejoresPuntajes.setFont( FuenteInterfazGrafica.get( 40 ) );
		labelYPosition = labelYPosition + LABEL_HEIGHT + LABEL_BOTTOM_PADDING;
		labMejoresPuntajes.setBounds( LABEL_X_POSITION, labelYPosition, 662, LABEL_HEIGHT );
		add( labMejoresPuntajes );
		labMejoresPuntajes.addMouseListener( new MouseListener() {

			@Override public void mouseReleased ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mousePressed ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mouseExited ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mouseEntered ( MouseEvent e ) {
				// Empty method for control behavior
			}

			@Override public void mouseClicked ( MouseEvent e ) {
				interfaz.mejoresPuntajes();
			}
		} );

	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	public void actionPerformed ( ActionEvent e ) {

		String comando = e.getActionCommand();

		switch ( comando ) {
			case CREAR_JUGADOR:
				dialogoCrearJugador.mostrar();
				break;
			case SELECCIONAR_JUGADOR:
				interfaz.actualizarJugadores();
				dialogoSeleccionarJugador.mostrar();
				break;
			case CREAR_PARTIDA:
				if ( interfaz.getJugadorActual() != null ) {
					dialogoCrearPartida.mostrar();
				} else {
					JOptionPane.showMessageDialog( this, "Por favor crear o seleccionar un jugador", "Error al iniciar partida", JOptionPane.ERROR_MESSAGE );
				}
				break;
			case SELECCIONAR_PARTIDA:
				if ( interfaz.getJugadorActual() != null ) {
					interfaz.actualizarPartidas();
					dialogoSeleccionarPartida.mostrar();
				} else {
					JOptionPane.showMessageDialog( this, "Por favor crear o seleccionar un jugador", "Error al seleccionar la partida", JOptionPane.ERROR_MESSAGE );
				}
				break;
			default:
				break;
		}
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public DialogoSeleccionarJugador getDialogoSeleccionarJugador () {
		return dialogoSeleccionarJugador;
	}

	public DialogoSeleccionarPartida getDialogoSeleccionarPartida () {
		return dialogoSeleccionarPartida;
	}

	@Override public void paintComponent ( Graphics g ) {
		super.paintComponent( g );
		Graphics2D g2 = (Graphics2D) g;

		// Pinta la imagen de fondo
		ImageIcon fondo = new ImageIcon( "./src/main/resources/data/imagenes/menu2.png" );
		g2.drawImage( fondo.getImage(), 0, 0, getSize().width, getSize().height, null );

		// Dibuja el nickname, nombre y monedas del jugador actual del juego.
		g2.setColor( Color.GREEN );
		g2.setFont( FuenteInterfazGrafica.get( 24 ) );
		g2.drawString( "NICKNAME", 30, 40 );
		g2.drawString( "JUGADOR", 170, 40 );

		g2.setColor( Color.RED );
		if ( interfaz.getJugadorActual() != null ) {

			g2.drawString( interfaz.getJugadorActual().getNickname(), 30, 60 );

			if ( interfaz.getJugadorActual().getNombre().length() <= 13 ) {
				g2.drawString( interfaz.getJugadorActual().getNombre(), 170, 60 );
			} else {
				g2.drawString( interfaz.getJugadorActual().getNombre().substring( 0, 13 ), 170, 60 );
			}
		}
	}

	public DialogoMejoresPuntajes getDialogoMejoresPuntajes () {
		return dialogoMejoresPuntajes;
	}

	public void setDialogoMejoresPuntajes ( DialogoMejoresPuntajes dialogoMejoresPuntajes ) {
		this.dialogoMejoresPuntajes = dialogoMejoresPuntajes;
	}
}
