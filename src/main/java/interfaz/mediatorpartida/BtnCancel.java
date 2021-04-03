package interfaz.mediatorpartida;

import interfaz.FuenteInterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Un amigo concreto
public class BtnCancel extends JButton implements ICommand {

	IMediator mediator;

	public BtnCancel ( ActionListener actionListener, IMediator mediator, String label ) {
		super( label );
		this.addActionListener( actionListener );
		
		this.mediator = mediator;
		this.mediator.registerCancel( this );

		this.setBounds( 200, 200, 130, 25 );
		this.setBackground( Color.BLACK );
		this.setFont( FuenteInterfazGrafica.get( 20 ) );
		this.setForeground( Color.RED );
	}

	public void execute () {
		mediator.cancel();
	}

}