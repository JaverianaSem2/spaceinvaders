package interfaz.bridgeimageicon;

import javax.swing.*;

public class ImageIconAbs {
	private final IBridgeImageIcon iBridgeImageIcon;

	public ImageIconAbs ( IBridgeImageIcon iBridgeImageIcon ) {
		this.iBridgeImageIcon = iBridgeImageIcon;
	}

	public ImageIcon getImage() {
		return iBridgeImageIcon.getImage();
	}
}
