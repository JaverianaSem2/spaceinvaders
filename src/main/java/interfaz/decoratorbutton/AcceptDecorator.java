package interfaz.decoratorbutton;

import interfaz.DialogoCrearJugador;
import interfaz.FuenteInterfazGrafica;
import interfaz.InterfazSpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AcceptDecorator extends ButtonDecorator {

	public AcceptDecorator ( IButtonComponent iButtonComponent ) {
		super( iButtonComponent );
	}

	@Override public void setup( ActionListener actionListener ) {
		super.setText( "Aceptar" );
		super.setActionCommand( "Aceptar" );
		super.setBounds( 10, 210, 130, 25 );
		super.setBackground( Color.black );
		super.setFont( FuenteInterfazGrafica.get( 20 ) );
		super.setForeground( Color.red );
		super.addActionListener( actionListener );
	}

	/**
	 * Este método de ejecución es exclusivo (una decoración) del botón Aceptar
	 * El botón cancelar es mas "puro" y por eso no lo tiene
	 */
	public void execute ( String txtNombre, String txtNickame, InterfazSpaceInvaders interfaz, DialogoCrearJugador dialogoCrearJugador ) {
		if ( txtNombre == null || txtNombre.trim().isEmpty()
			|| txtNickame == null || txtNickame.trim().isEmpty() ) {
			JOptionPane.showMessageDialog(
				this,
				"Por favor ingrese un nombre y un nickname válido",
				"Error al crear el jugador",
				JOptionPane.ERROR_MESSAGE
			);
		} else if ( txtNickame.length() != 5 ) {
			JOptionPane.showMessageDialog(
				this,
				"El nickname debe contener 5 caracteres",
				"Error al asignar el nickname",
				JOptionPane.ERROR_MESSAGE
			);
		} else {
			interfaz.reqAgregarJugador( txtNombre, txtNickame );
			dialogoCrearJugador.dispose();
		}
	}
}
