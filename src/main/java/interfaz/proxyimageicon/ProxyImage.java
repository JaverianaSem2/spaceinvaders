package interfaz.proxyimageicon;

import javax.swing.*;

public class ProxyImage implements IImage {

	private RealImage realImage;

	public ProxyImage () {
		// Do nothing
	}

	@Override public ImageIcon display () {
		if ( realImage == null ) {
			realImage = new RealImage();
		}
		return realImage.display();
	}
}
