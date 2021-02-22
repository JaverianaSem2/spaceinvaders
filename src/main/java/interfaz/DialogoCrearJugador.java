package interfaz;

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

	JButton butBotonAceptar;
	JButton butBotonCancelar;

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

		butBotonAceptar = new JButton( ACEPTAR );
		butBotonAceptar.setActionCommand( ACEPTAR );
		butBotonAceptar.addActionListener( this );
		butBotonAceptar.setBounds( 10, 210, 130, 25 );
		butBotonAceptar.setBackground( Color.BLACK );
		butBotonAceptar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonAceptar.setForeground( Color.YELLOW );

		butBotonCancelar = new JButton( CANCELAR );
		butBotonCancelar.setActionCommand( CANCELAR );
		butBotonCancelar.addActionListener( this );
		butBotonCancelar.setBounds( 10, 350, 130, 25 );
		butBotonCancelar.setBackground( Color.BLACK );
		butBotonCancelar.setFont( FuenteInterfazGrafica.get( 20 ) );
		butBotonCancelar.setForeground( Color.green );

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
			if ( txtNombre.getText() == null || txtNombre.getText().equals("")
				|| txtNickame.getText() == null || txtNickame.getText().equals(""))
				JOptionPane.showMessageDialog(
					this,
					"Por favor ingrese un nombre y un nickname válido",
						"Error al crear el jugador",
					JOptionPane.ERROR_MESSAGE
				);

			else if ( txtNickame.getText().length() != 5 ) {
				JOptionPane.showMessageDialog(
					this,
					"El nickname debe contener 5 caracteres",
						"Error al asignar el nickname",
					JOptionPane.ERROR_MESSAGE
				);
			} else {
				interfaz.reqAgregarJugador( txtNombre.getText(), txtNickame.getText() );
				this.dispose();
			}
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