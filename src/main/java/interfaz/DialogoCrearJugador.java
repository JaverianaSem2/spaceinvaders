package interfaz;

import interfaz.decoratorbutton.AcceptDecorator;
import interfaz.decoratorbutton.ButtonComponent;
import interfaz.decoratorbutton.CancelDecorator;
import interfaz.decoratorbutton.IButtonComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author squin
 */
public class DialogoCrearJugador extends JDialog implements ActionListener {

	private static final long   serialVersionUID = 1L;
	private static final String ACEPTAR          = "Aceptar";
	private static final String CANCELAR         = "Cancelar";

	InterfazSpaceInvaders interfaz;

	JPanel auxiliar;

	JLabel labNombre;
	JLabel labNickname;

	JTextField txtNombre;
	JTextField txtNickame;

	AcceptDecorator butBotonAceptar;
	CancelDecorator butBotonCancelar;

	public DialogoCrearJugador ( InterfazSpaceInvaders interfaz ) {

		super( interfaz, true );

		this.interfaz = interfaz;
		setLayout( null );

		auxiliar = new JPanel();
		auxiliar.setLayout( null );

		labNombre = new JLabel( "NOMBRE DEL JUGADOR" );
		labNombre.setForeground( Color.RED );
		labNombre.setFont( FuenteInterfazGrafica.get( 33 ) );
		labNombre.setBounds( 10, 60, 350, 20 );

		txtNombre = new JTextField();
		txtNombre.setBackground( Color.orange );
		txtNombre.setBounds( 10, 85, 205, 25 );
		txtNombre.setForeground( Color.BLUE );
		txtNombre.setFont( FuenteInterfazGrafica.get( 25 ) );

		labNickname = new JLabel( "NICKNAME" );
		labNickname.setForeground( Color.red );
		labNickname.setFont( FuenteInterfazGrafica.get( 33 ) );
		labNickname.setBounds( 10, 150, 260, 20 );

		txtNickame = new JTextField();
		txtNickame.setBackground( Color.orange );
		txtNickame.setBounds( 10, 180, 150, 25 );
		txtNickame.setForeground( Color.BLUE );
		txtNickame.setFont( FuenteInterfazGrafica.get( 25 ) );

		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon( "./src/main/resources/data/imagenes/fondAgJ.jpg" );
		imagen.setIcon( icono );
		imagen.setBounds( 0, 0, icono.getIconWidth(), icono.getIconHeight() );

		IButtonComponent iButtonComponent = new ButtonComponent( this, ACEPTAR );

		butBotonAceptar = new AcceptDecorator( iButtonComponent );
		butBotonAceptar.setup( this );

		butBotonCancelar = new CancelDecorator( iButtonComponent );
		butBotonCancelar.setup( this );

		auxiliar.setSize( icono.getIconWidth(), icono.getIconHeight() );
		auxiliar.add( labNombre );
		auxiliar.add( txtNombre );
		auxiliar.add( labNickname );
		auxiliar.add( txtNickame );
		auxiliar.add( butBotonAceptar );
		auxiliar.add( butBotonCancelar );
		auxiliar.add( imagen );

		setTitle( "Crear Jugador" );
		setUndecorated( true );
		getRootPane().setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		if ( comando.equals( CANCELAR ) ) {
			this.dispose();
		} else if ( comando.equals( ACEPTAR ) ) {
			butBotonAceptar.execute( txtNombre.getText(), txtNickame.getText(), interfaz, this );
		}
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void mostrar () {
		setSize( 400, 400 );
		add( auxiliar );
		setLocationRelativeTo( null );
		this.setVisible( true );
	}

}