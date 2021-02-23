package interfaz;

import java.awt.*;

public class FuenteInterfazGrafica {

	FuenteInterfazGrafica () {
		super();
	}

	public static Font get ( int size ) {
		return new Font( "ArcadeClassic", java.awt.Font.PLAIN, size );
	}
}
