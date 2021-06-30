package interfaz;

import interfaz.mediatorpartida.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoCrearPartida extends JDialog implements ActionListener {

	public static final  String ACEPTAR          = "Aceptar";
	public static final  String CANCELAR         = "Cancelar";
	private static final long   serialVersionUID = 1L;
	InterfazSpaceInvaders interfaz;

	JPanel auxiliar;

	JLabel nombre;
	JLabel nombre1;

	JTextField txtNombre;

	JButton butBotonAceptar;
	JButton butBotonCancelar;

	// -----------------------------------------------------------------
	// ---------------------------Constructor---------------------------
	// -----------------------------------------------------------------

	public DialogoCrearPartida ( InterfazSpaceInvaders interfaz ) {

		super( interfaz, false );

		this.interfaz = interfaz;
		setLayout( null );

		auxiliar = new JPanel();
		auxiliar.setLayout( null );

		nombre = new JLabel( "Ingrese el nombre    de" );
		nombre.setForeground( Color.YELLOW );
		nombre.setFont( FuenteInterfazGrafica.get( 33 ) );
		nombre.setBounds( 10, 30, 350, 20 );

		nombre1 = new JLabel( "la    partida" );
		nombre1.setForeground( Color.YELLOW );
		nombre1.setFont( FuenteInterfazGrafica.get( 33 ) );
		nombre1.setBounds( 10, 55, 240, 20 );

		// Se configura patrón mediator
		IMediator mediator = new Mediator();
		mediator.registerDialogo( interfaz, this );

		txtNombre = new TxtName( this, mediator);

		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon( "./src/main/resources/data/imagenes/fondoAP.jpg" );
		imagen.setIcon( icono );
		imagen.setBounds( 0, 0, icono.getIconWidth(), icono.getIconHeight() );

		butBotonAceptar = new BtnAccept(this, mediator, ACEPTAR);
		butBotonCancelar = new BtnCancel(this, mediator, CANCELAR);

		auxiliar.setSize( icono.getIconWidth(), icono.getIconHeight() );
		auxiliar.add( nombre );
		auxiliar.add( nombre1 );
		auxiliar.add( txtNombre );
		auxiliar.add( butBotonAceptar );
		auxiliar.add( butBotonCancelar );
		auxiliar.add( imagen );

		setTitle( "Crear Partida" );
		setUndecorated( true );
		getRootPane().setBorder( BorderFactory.createLineBorder( Color.WHITE ) );
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean theSourceElementIsAButton = e.getSource().getClass().getSimpleName().toLowerCase().contains( "btn" );

		if ( theSourceElementIsAButton ) {
			ICommand iCommand = (ICommand) e.getSource();
			iCommand.execute();
		}
	}

	// -----------------------------------------------------------------
	// -----------------------------Métodos-----------------------------
	// -----------------------------------------------------------------

	public void mostrar () {
		setSize( 400, 225 );
		add( auxiliar );
		setLocationRelativeTo( null );
		this.setVisible( true );
	}

}
