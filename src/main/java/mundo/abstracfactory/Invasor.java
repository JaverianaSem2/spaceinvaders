package mundo.abstracfactory;

import mundo.Enemigo;
import mundo.Nave;

public abstract class Invasor extends Nave {

  public static final int    IZQUIERDA = 0;
  public static final int    DERECHA   = 1;
  private             int    direccion;
  private final       String rutaImagen2;
  private             int    puntosPorMuerte;

  protected Invasor(double velocidad, int posX, int posY, int vida, int ancho, int direccion, String rutaImage, String rutaImage2) {
      super(velocidad, posX, posY, vida, ancho, rutaImage);
      this.direccion = direccion;
      this.rutaImagen2 = rutaImage2;
  }

  /**
   * Metodos
   */

  public int getDireccion () {
    return direccion;
    }

  public void setDireccion ( int direccion ) {
    this.direccion = direccion;
    }

  public String getRutaImagen2 () {
    return rutaImagen2;
    }

  public int getPuntosPorMuerte () {
    return puntosPorMuerte;
    }

  public void setPuntosPorMuerte ( int puntosPorMuerte ) {
    this.puntosPorMuerte = puntosPorMuerte;
    }

  public boolean edge () {
    return ( posX > 599 || posX < 0 );
    }

  @Override
  public void mover ( int dir ) {
    super.mover( dir );

    posX += dir * getVelocidad();
    }

  public void moverAbajo ( int dir ) {
    posY += dir * 10;
    }

  }