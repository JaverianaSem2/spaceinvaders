package interfaz.mediatorpartida;

import interfaz.DialogoCrearPartida;
import interfaz.InterfazSpaceInvaders;

import javax.swing.*;

//Mediador Concreto
public class Mediator implements IMediator {

	BtnCancel btnCancel;
	BtnAccept btnAccept;
	TxtName   txtName;
	DialogoCrearPartida dialogo;
	InterfazSpaceInvaders interfaz;

	public void registerCancel ( BtnCancel btnCancel ) {
		this.btnCancel = btnCancel;
	}

	public void registerAccept ( BtnAccept btnAccept ) {
		this.btnAccept = btnAccept;
	}

	public void registerName ( TxtName txtName ) {
		this.txtName = txtName;
	}

	public void registerDialogo ( InterfazSpaceInvaders interfaz, DialogoCrearPartida dialogo ) {
		this.dialogo = dialogo;
		this.interfaz = interfaz;
	}

	public void accept () {
		if ( txtName.getText() == null || txtName.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(
				dialogo,
				"Por favor ingrese un nombre válido",
				"Error al crear el jugador",
				JOptionPane.ERROR_MESSAGE
			);
		else {
			interfaz.reqCrearPartida( txtName.getText() );
			dialogo.dispose();
		}
	}

	public void cancel () {
		txtName.setText( "" );
		dialogo.dispose();
	}

}