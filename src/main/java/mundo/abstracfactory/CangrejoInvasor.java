package mundo.abstracfactory;

import constantes.Constantes;
import mundo.Enemigo;
import mundo.Nivel;

public class CangrejoInvasor extends Invasor {

  private static final String RUTA_IMAGE = Constantes.CANGREJO_1;
  private static final String RUTA      = Constantes.CANGREJO_2;
  private static final int    DIRECCION = Enemigo.DERECHA;


  public CangrejoInvasor( Nivel nivel, int posX, int posY) {

    super(
          nivel.getVelocidadEnemigos(),
          posX,
          posY,
          nivel.getVidaEnemigos(),
          nivel.getAnchoEnemigos(),
          DIRECCION,
          RUTA_IMAGE,
          RUTA
        );

    setPuntosPorMuerte( 20 );
  }

}