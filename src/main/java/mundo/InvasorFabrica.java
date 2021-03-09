package mundo;

import java.security.SecureRandom;
import java.util.ArrayList;

public class InvasorFabrica implements InvasorAbstracta{

  private Nivel nivel;

  @Override
  public Enemigo crearInvasor ( String tipo, int posX, int posY) {
    ArrayList<String> enemies = new ArrayList<>();
    enemies.add("Cangrejo");
    enemies.add("Calamar");
    enemies.add("Pulpo");
    int typeEnemy = enemies.indexOf( tipo );

    return chosenEnemy(typeEnemy, posX, posY);

  }

  private Enemigo chosenEnemy(int typeEnemy, int posX, int posY){
    switch ( typeEnemy ){
      case 0:                                                   //Cangrejo
        return new InvasorCangrejo( nivel, posX, posY);
      case 1:                                                   //Calamar
        return new InvasorCalamar( nivel, posX, posY);
      case 2:                                                   //Pulpo
        return new InvasorPulpo( nivel, posX, posY );
      default:                                                  //Random
        int ramdomEnemy = getRandomNumber( 0,2);
        return chosenEnemy( ramdomEnemy, posX, posY);
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
