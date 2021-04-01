package mundo;

import java.io.Serializable;

public class Disparo implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean impacto;

	private int posX;

	private int posY;

	public Disparo ( int posX, int posY ) {
		this.posX = posX;
		this.posY = posY;
		impacto = false;
	}

	public boolean getImpacto () {
		return impacto;
	}

	public void setImpacto ( boolean impacto ) {
		this.impacto = impacto;
	}

	public int getPosX () {
		return posX;
	}

	public void setPosX ( int posX ) {
		this.posX = posX;
	}

	public int getPosY () {
		return posY;
	}

	public void setPosY ( int posY ) {
		this.posY = posY;
	}

	public void shoot () {
		this.posY = this.posY - 1;
	}

	public void shoot1 () {
		this.posY = this.posY + 5;
	}

	public boolean hitsEnemigo ( Enemigo a ) {
		int r = 8;
		if ( a != null ) {
			double cateto = this.posX - a.getPosX();
			double cateto2 = this.posY - a.getPosY();
			double d = Math.sqrt( ( cateto * cateto ) + ( cateto2 * cateto2 ) );

			return d < ( r + a.getAncho() );
		}
		return false;
	}

	public boolean hitsJugador ( NaveJugador a ) {
		if ( a != null ) {
			double cateto1 = posX - a.getPosInicialX();
			double cateto2 = posY - a.getPosInicialY();
			double d = Math.sqrt( ( cateto1 * cateto1 ) + ( cateto2 * cateto2 ) );

			boolean condicionRetorno1 = d < a.getAncho() + 8;
			boolean condicionRetorno2 = posY == a.getPosInicialY();
			boolean condicionRetorno3a = posX < a.getPosInicialX() + 35;
			boolean condicionRetorno3b = posX > a.getPosInicialX() - 10;
			boolean condicionRetorno3 = condicionRetorno3a && condicionRetorno3b;

			return condicionRetorno1 && condicionRetorno2 && condicionRetorno3;
		}

		return false;
	}

}
