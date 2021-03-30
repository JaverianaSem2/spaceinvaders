package interfaz.bridgeimageicon;

import javax.swing.*;

public class ImageIconImplA implements IBridgeImageIcon {
	public ImageIcon getImage() {
		return new ImageIcon( "./src/main/resources/data/imagenes/menuInicio.gif" );
	}
}
