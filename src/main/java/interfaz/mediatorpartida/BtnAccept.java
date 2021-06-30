package interfaz.mediatorpartida;

import interfaz.FuenteInterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Un amigo concreto
public class BtnAccept extends JButton implements ICommand {

	IMediator mediator;

	public BtnAccept ( ActionListener actionListener, IMediator mediator, String label ) {
		super( label );
		this.addActionListener( actionListener );

		this.mediator = mediator;
		this.mediator.registerAccept( this );

		this.setBounds( 10, 200, 130, 25 );
		this.setBackground( Color.BLACK );
		this.setFont( FuenteInterfazGrafica.get( 20 ) );
		this.setForeground( Color.BLUE );
	}

	public void execute (){
			mediator.accept();
	}

}