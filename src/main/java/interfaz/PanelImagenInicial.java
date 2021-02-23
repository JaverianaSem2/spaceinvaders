package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Panel que representa la imagen inicial al ejecutar el programa
 *
 * @author Juan Sebastián Quintero Yoshioka - Manuel Alejandro Coral Lozano
 * Proyecto final - Algoritmos y programación II
 */
public class PanelImagenInicial extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	private final InterfazSpaceInvaders interfaz;

	public PanelImagenInicial ( InterfazSpaceInvaders interfaz ) {

		// Inicializa la asociación con la interfaz
		this.interfaz = interfaz;

		// Contenedor gráfico
		setLayout( new BorderLayout() );

		// Imagen de fondo
		ImageIcon icono = new ImageIcon( "./src/main/resources/data/imagenes/menuInicio.gif" );
		JLabel labImagen = new JLabel( icono );
		Color fondo = new Color( 21, 22, 25 );
		setBackground( fondo );

		// Título del juego: "SPACE INVADERS"
		JPanel titulo = new JPanel( new FlowLayout() );
		JLabel space = new JLabel( "Space " );
		space.setFont( FuenteInterfazGrafica.get( 82 ) );
		space.setForeground( Color.WHITE );
		JLabel invaders = new JLabel( "Invaders" );
		invaders.setFont( FuenteInterfazGrafica.get( 82 ) );
		invaders.setForeground( Color.WHITE );
		titulo.setBackground( fondo );
		titulo.add( space );
		titulo.add( invaders );

		// Texto instructivo: "PRESIONE LA TECLA ESPACIO"
		JPanel flow = new JPanel( new FlowLayout() );
		JLabel presione = new JLabel( "PRESIONE " );
		presione.setForeground( Color.BLUE );
		JLabel la = new JLabel( "LA " );
		la.setForeground( Color.RED );
		JLabel tecla = new JLabel( "TECLA " );
		tecla.setForeground( Color.GREEN );
		JLabel espacio = new JLabel( "ENTER " );
		espacio.setForeground( Color.YELLOW );
		presione.setFont( FuenteInterfazGrafica.get( 49 ) );
		la.setFont( FuenteInterfazGrafica.get( 49 ) );
		tecla.setFont( FuenteInterfazGrafica.get( 49 ) );
		espacio.setFont( FuenteInterfazGrafica.get( 49 ) );
		flow.setBackground( fondo );
		flow.add( presione );
		flow.add( la );
		flow.add( tecla );
		flow.add( espacio );

		// Agrega los tres elementos al panel: imagen de fondo, título del juego
		// y la intstrucción.
		add( labImagen, BorderLayout.CENTER );
		add( titulo, BorderLayout.NORTH );
		add( flow, BorderLayout.SOUTH );

	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	/**
	 * Manejo de eventos de los botones
	 *
	 * @param e Evento que generó la acción - e != null.
	 */
	@Override public void keyPressed ( KeyEvent e ) {
		// En caso que el usuario presione la tecla Enter, cambia al panel
		// que contiene el menú del juego
		if ( e.getKeyCode() == KeyEvent.VK_ENTER && !interfaz.estaEnFuncionamiento() ) {
			interfaz.cambiarPanel( "Menú" );
		}
	}

	/**
	 * Manejo de eventos de los botones
	 *
	 * @param e Evento que generó la acción - e != null.
	 */
	@Override public void keyReleased ( KeyEvent e ) {
		// Empty method for control behavior
	}

	/**
	 * Manejo de eventos de los botones
	 *
	 * @param e Evento que generó la acción - e != null.
	 */
	@Override public void keyTyped ( KeyEvent e ) {
		if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
			interfaz.cambiarPanel( "Menú" );
		}
	}

}