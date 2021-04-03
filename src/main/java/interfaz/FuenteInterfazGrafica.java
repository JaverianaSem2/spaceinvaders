package interfaz;

import java.awt.*;

public class FuenteInterfazGrafica {

	FuenteInterfazGrafica () {
		// Empty constructor : SonarQube
	}

	public static Font get ( int size ) {
		return new Font( "ArcadeClassic", java.awt.Font.PLAIN, size );
	}
}
