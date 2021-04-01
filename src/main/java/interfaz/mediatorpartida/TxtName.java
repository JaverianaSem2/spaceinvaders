package interfaz.mediatorpartida;

import interfaz.FuenteInterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TxtName extends JTextField {

	IMediator mediator;

	public TxtName ( ActionListener actionListener, IMediator mediator ) {
		super( "" );
		this.addActionListener( actionListener );

		this.mediator = mediator;
		this.mediator.registerName( this );

		this.setBackground( Color.orange );
		this.setBounds( 10, 150, 210, 25 );
		this.setForeground( Color.BLUE );
		this.setFont( FuenteInterfazGrafica.get( 25 ) );
	}

}