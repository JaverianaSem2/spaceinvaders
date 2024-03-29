package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DialogoMejoresPuntajes extends JDialog implements ActionListener {

	public static final String ACEPTAR = "Aceptar";

	InterfazSpaceInvaders interfaz;

	JPanel panelAuxiliar;

	JButton butBotonAceptar;

	public DialogoMejoresPuntajes ( InterfazSpaceInvaders interfaz, List<String> puntajes ) {

		super( interfaz, false );

		this.interfaz = interfaz;
		setLayout( null );

		panelAuxiliar = new JPanel();
		panelAuxiliar.setLayout( null );

		butBotonAceptar = new JButton( ACEPTAR );
		butBotonAceptar.setActionCommand( ACEPTAR );
		butBotonAceptar.addActionListener( this );
		butBotonAceptar.setBounds( 235, 360, 130, 25 );
		butBotonAceptar.setBackground( Color.BLACK );
		butBotonAceptar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonAceptar.setForeground( Color.BLUE );

		panelAuxiliar.setSize( 600, 400 );
		panelAuxiliar.add( butBotonAceptar );

		panelAuxiliar.setBackground( Color.BLACK );

		setFont( FuenteInterfazGrafica.get( 20 ) );
		setForeground( Color.RED );

		JLabel labPosicion = new JLabel( "POSICIÓN" );
		labPosicion.setFont( FuenteInterfazGrafica.get( 20 ) );
		labPosicion.setForeground( Color.GREEN );
		labPosicion.setBounds( 10, 10, 100, 25 );
		panelAuxiliar.add( labPosicion );

		JLabel labPuntaje = new JLabel( "PUNTAJE" );
		labPuntaje.setFont( FuenteInterfazGrafica.get( 20 ) );
		labPuntaje.setForeground( Color.GREEN );
		labPuntaje.setBounds( 110, 10, 200, 25 );
		panelAuxiliar.add( labPuntaje );

		JLabel labNickname = new JLabel( "NICKNAME" );
		labNickname.setFont( FuenteInterfazGrafica.get( 20 ) );
		labNickname.setForeground( Color.GREEN );
		labNickname.setBounds( 200, 10, 300, 25 );
		panelAuxiliar.add( labNickname );

		JLabel labNombrePartida = new JLabel( "PARTIDA" );
		labNombrePartida.setFont( FuenteInterfazGrafica.get( 20 ) );
		labNombrePartida.setForeground( Color.GREEN );
		labNombrePartida.setBounds( 310, 10, 400, 25 );
		panelAuxiliar.add( labNombrePartida );

		int x = 10;
		int y = 30;

		JLabel numero;
		JLabel puntaje;
		JLabel nickname;
		JLabel partida;

		if ( puntajes != null ) {
			for ( String puntajeActual : puntajes ) {

				String[] informacion = puntajeActual.split( " " );

				numero = new JLabel();
				puntaje = new JLabel();
				nickname = new JLabel();
				partida = new JLabel();

				numero.setText( informacion[0] );
				puntaje.setText( informacion[1] );
				nickname.setText( informacion[2] );
				partida.setText( informacion[3] );

				numero.setFont( FuenteInterfazGrafica.get( 20 ) );
				numero.setForeground( Color.WHITE );
				puntaje.setFont( FuenteInterfazGrafica.get( 20 ) );
				puntaje.setForeground( Color.WHITE );
				nickname.setFont( FuenteInterfazGrafica.get( 20 ) );
				nickname.setForeground( Color.WHITE );
				partida.setFont( FuenteInterfazGrafica.get( 20 ) );
				partida.setForeground( Color.WHITE );

				numero.setBounds( x, y, 100, 25 );
				puntaje.setBounds( x + 100, y, 210, 30 );
				nickname.setBounds( x + 190, y, 300, 25 );
				partida.setBounds( x + 300, y, 400, 25 );

				panelAuxiliar.add( numero );
				panelAuxiliar.add( puntaje );
				panelAuxiliar.add( nickname );
				panelAuxiliar.add( partida );

				y = y + 30;

			}
		}
		add( panelAuxiliar );
		setUndecorated( true );
		getRootPane().setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if ( comando.equals( ACEPTAR ) ) {
			this.dispose();
		}
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	/**
	 *
	 */
	public void mostrar () {
		setSize( 600, 400 );
		setLocationRelativeTo( null );
		this.setVisible( true );
	}

}
