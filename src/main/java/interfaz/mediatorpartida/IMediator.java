package interfaz.mediatorpartida;

import interfaz.DialogoCrearPartida;
import interfaz.InterfazSpaceInvaders;

//Mediador Abstracto
public interface IMediator {
	void accept ();

	void cancel ();

	void registerCancel ( BtnCancel btnCancel );

	void registerAccept ( BtnAccept btnAccept );

	void registerName ( TxtName txtName );

	void registerDialogo ( InterfazSpaceInvaders interfaz, DialogoCrearPartida dialogo );
}