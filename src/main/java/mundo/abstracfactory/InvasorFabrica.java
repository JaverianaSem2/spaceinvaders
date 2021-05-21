package mundo.abstracfactory;

import mundo.*;

import java.security.SecureRandom;
import java.util.ArrayList;

/** "Abstract Builder" */
public class InvasorFabrica implements IInvasorAbstract {

  private Nivel nivel;

  @Override
  public Invasor crearInvasor ( String tipo, int posX, int posY) {

    switch ( tipo ){
      case "Cangrejo":                                                   //Cangrejo
        return new CangrejoInvasor( nivel, posX, posY);
      case "Calamar":                                                   //Calamar
        return new CalamarInvasor( nivel, posX, posY);
      case "Pulpo":                                                   //Pulpo
        return new PulpoInvasor( nivel, posX, posY );
      default:                                                //Random
        return null;
    }

  }

  private int getRandomNumber(int min, int max) {
    SecureRandom ramdom = new SecureRandom();
    return ramdom.nextInt(max-min) + min;
  }

  public void setNivel ( Nivel nivel ) {
    this.nivel = nivel;
  }



}