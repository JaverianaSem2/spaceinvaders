package interfaz;

import mundo.Partida;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class DialogoSeleccionarPartida extends JDialog
	implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String ACEPTAR  = "Aceptar";
	private static final String CANCELAR = "Cancelar";
	JButton butBotonAceptar;
	JButton butBotonCancelar;
	private final                               InterfazSpaceInvaders interfaz;
	private final JList<Object>                 partidas;

	public DialogoSeleccionarPartida ( InterfazSpaceInvaders interfaz ) {

		super( interfaz, true );
		setLayout( new BorderLayout() );

		this.interfaz = interfaz;
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy( VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setPreferredSize( new Dimension( 230, 200 ) );
		partidas = new JList<>();
		partidas.addListSelectionListener( this );
		partidas.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		partidas.setModel( new DefaultListModel<>() );
		scroll.getViewport().add( partidas );
		partidas.setBackground( Color.BLACK );
		partidas.setFont( FuenteInterfazGrafica.get( 20 ) );
		partidas.setForeground( Color.BLUE );
		scroll.setBackground( Color.BLACK );
		add( scroll, BorderLayout.CENTER );

		butBotonAceptar = new JButton( ACEPTAR );
		butBotonAceptar.setActionCommand( ACEPTAR );
		butBotonAceptar.addActionListener( this );
		butBotonAceptar.setBounds( 60, 2, 130, 25 );
		butBotonAceptar.setBackground( Color.BLACK );
		butBotonAceptar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonAceptar.setForeground( Color.BLUE );

		butBotonCancelar = new JButton( CANCELAR );
		butBotonCancelar.setActionCommand( CANCELAR );
		butBotonCancelar.addActionListener( this );
		butBotonCancelar.setBounds( 210, 2, 130, 25 );
		butBotonCancelar.setBackground( Color.BLACK );
		butBotonCancelar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonCancelar.setForeground( Color.RED );

		this.setBackground( Color.BLACK );
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout( null );
		auxiliar.setBackground( Color.BLACK );
		auxiliar.add( butBotonAceptar );
		auxiliar.add( butBotonCancelar );
		auxiliar.setPreferredSize( new Dimension( 230, 28 ) );
		add( auxiliar, BorderLayout.SOUTH );

		setUndecorated( true );
		getRootPane().setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	@Override public void actionPerformed ( ActionEvent e ) {
		String comando = e.getActionCommand();

		if ( comando.equals( CANCELAR ) )
			this.dispose();
		else if ( comando.equals( ACEPTAR ) ) {
			String partidaSeleccionada = darPartidaSeleccionada();
			if ( ! partidaSeleccionada.trim().isEmpty() ) {
				interfaz.actualizarPartidaActual( partidaSeleccionada );
				this.dispose();
				interfaz.cambiarPanel( "Juego" );
			} else {
				JOptionPane.showMessageDialog(
					this,
					"Por favor cree una partida para el jugaodor",
					"No existen partidas",
					JOptionPane.INFORMATION_MESSAGE
				);
				this.dispose();
			}

		}
	}

	@Override public void valueChanged ( ListSelectionEvent arg0 ) {
		// Empty method for control behavior
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void cambiarListaPartidas ( List<Partida> lista ) {
		partidas.setListData( lista.toArray() );

		if ( partidas.getModel().getSize() > 0 )
			partidas.setSelectedIndex( 0 );
	}

	public String darPartidaSeleccionada () {
		return Optional.ofNullable(
			( ( Partida ) partidas.getSelectedValue() ).getNombre()
		).orElse( "" );
	}

	public void mostrar () {
		setSize( 400, 400 );
		setLocationRelativeTo( null );
		this.setVisible( true );
	}

}
