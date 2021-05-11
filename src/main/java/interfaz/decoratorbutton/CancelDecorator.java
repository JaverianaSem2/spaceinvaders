package interfaz.decoratorbutton;

import interfaz.FuenteInterfazGrafica;

import java.awt.*;
import java.awt.event.ActionListener;

public class CancelDecorator extends ButtonDecorator {

	public CancelDecorator ( IButtonComponent iButtonComponent ) {
		super( iButtonComponent );
	}

	@Override public void setup( ActionListener actionListener ) {
		super.setText( "Cancelar" );
		super.setActionCommand( "Cancelar" );
		super.addActionListener( actionListener );
		super.setBounds( 10, 350, 130, 25 );
		super.setBackground( Color.BLACK );
		super.setFont( FuenteInterfazGrafica.get( 20 ) );
		super.setForeground( Color.RED );
	}
}
