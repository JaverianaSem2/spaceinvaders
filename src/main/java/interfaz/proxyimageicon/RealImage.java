package interfaz.proxyimageicon;

import javax.swing.*;

public class RealImage implements IImage {

	public RealImage ( ) {
		loadFromDisk();
	}

	@Override public ImageIcon display () {
		return new ImageIcon( "./src/main/resources/data/imagenes/fondAgJ.jpg" );
	}

	private void loadFromDisk () {
		new ImageIcon( "./src/main/resources/data/imagenes/loading.jpg" );
	}
}