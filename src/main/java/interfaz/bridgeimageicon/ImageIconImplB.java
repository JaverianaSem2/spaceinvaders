package interfaz.bridgeimageicon;

import javax.swing.*;

public class ImageIconImplB implements IBridgeImageIcon {
	public ImageIcon getImage() {
		return new ImageIcon( "./src/main/resources/data/imagenes/menuInicio2.gif" );
	}
}
