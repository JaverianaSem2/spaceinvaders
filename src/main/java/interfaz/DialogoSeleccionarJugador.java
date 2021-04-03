package interfaz;

import mundo.NaveJugador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class DialogoSeleccionarJugador extends JDialog
	implements ListSelectionListener, ActionListener {

	private static final long   serialVersionUID = 1L;
	private static final String ORDENAR          = "Ordenar";
	private static final String ACEPTAR          = "Aceptar";
	private static final String CANCELAR         = "Cancelar";
	JButton butBotonAceptar;
	JButton butBotonCancelar;
	private final InterfazSpaceInvaders interfaz;

	private final JList<Object> jugadores = new JList<>();

	public DialogoSeleccionarJugador(InterfazSpaceInvaders interfaz) {

		super( interfaz, true );
		setLayout( new BorderLayout() );

		this.interfaz = interfaz;
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy( VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setPreferredSize( new Dimension( 240, 200 ) );
		jugadores.addListSelectionListener( this );
		jugadores.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		jugadores.setModel( new DefaultListModel<>() );
		scroll.getViewport().add( jugadores );
		jugadores.setBackground( Color.BLACK );
		jugadores.setFont( FuenteInterfazGrafica.get( 20 ) );
		jugadores.setForeground( Color.BLUE );
		scroll.setBackground( Color.BLACK );
		add( scroll, BorderLayout.CENTER );

		butBotonAceptar = new JButton( ACEPTAR );
		butBotonAceptar.setActionCommand( ACEPTAR );
		butBotonAceptar.addActionListener( this );
		butBotonAceptar.setBounds( 5, 2, 130, 25 );
		butBotonAceptar.setBackground( Color.BLACK );
		butBotonAceptar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonAceptar.setForeground( Color.YELLOW );

		butBotonCancelar = new JButton( CANCELAR );
		butBotonCancelar.setActionCommand( CANCELAR );
		butBotonCancelar.addActionListener( this );
		butBotonCancelar.setBounds( 140, 2, 130, 25 );
		butBotonCancelar.setBackground( Color.BLACK );
		butBotonCancelar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonCancelar.setForeground( Color.green );

		JButton butOrdenar = new JButton( "ORDENAR" );
		butOrdenar.addActionListener( this );
		butOrdenar.setActionCommand( ORDENAR );
		butOrdenar.setBounds( 275, 2, 130, 25 );
		butOrdenar.setBackground( Color.BLACK );
		butOrdenar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butOrdenar.setForeground( Color.BLUE );

		this.setBackground( Color.BLACK );
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout( null );
		auxiliar.setBackground( Color.BLACK );
		auxiliar.add( butBotonAceptar );
		auxiliar.add( butBotonCancelar );
		auxiliar.add( butOrdenar );
		auxiliar.setPreferredSize( new Dimension( 240, 28 ) );
		add( auxiliar, BorderLayout.SOUTH );

		setUndecorated( true );
		getRootPane().setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch ( comando ) {
			case CANCELAR:
				this.dispose();
				break;
			case ACEPTAR:
				if ( ! darJugadorSeleccionado().trim().isEmpty() ) {
					interfaz.actualizarJugadorActual( darJugadorSeleccionado() );
				} else {
					JOptionPane.showMessageDialog( this,
						"Por favor cree un jugador",
						"No existen jugadores",
						JOptionPane.INFORMATION_MESSAGE
					);
				}
				this.dispose();
				break;
			case ORDENAR:
				interfaz.ordenarJugadores();
				break;
			default:
				break;
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// Empty method for control behavior
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void cambiarListaJugadores( List<NaveJugador> lista) {

		jugadores.setListData( lista.toArray() );

		if ( jugadores.getModel().getSize() > 0 ) {
			jugadores.setSelectedIndex( 0 );
		}
	}

	public String darJugadorSeleccionado () {
		return Optional.ofNullable (
			( (NaveJugador) jugadores.getSelectedValue() ).getNickname()
		).orElse( "" );
	}

	public void mostrar () {
		setSize( 400, 400 );
		setLocationRelativeTo( null );
		this.setVisible( true );
	}
}