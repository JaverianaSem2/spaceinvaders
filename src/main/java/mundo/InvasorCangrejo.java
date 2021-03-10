package mundo;

import constantes.Constantes;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class InvasorCangrejo extends Enemigo {

  private static final String RUTA_IMAGE = Constantes.CANGREJO_1;
  private static final String RUTA      = Constantes.CANGREJO_2;
  private static final int    DIRECCION = Enemigo.DERECHA;


	public InvasorCangrejo(Nivel nivel, int posX, int posY) {
    super(nivel.getVelocidadEnemigos(), posX, posY, nivel.getVidaEnemigos(), nivel.getAnchoEnemigos(), DIRECCION, RUTA_IMAGE, RUTA );
		setPuntosPorMuerte( 20 );
	}

}