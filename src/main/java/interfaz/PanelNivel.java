package interfaz;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mundo.Disparo;
import mundo.Partida;
import mundo.SpaceInvaders;

public class PanelNivel extends JPanel {

	private       Partida               partida;
	private final SpaceInvaders         space;
	private final InterfazSpaceInvaders interfaz;

	private int posJugadorActualX;

	public PanelNivel(Partida actual, SpaceInvaders b, InterfazSpaceInvaders c) {
		partida = actual;
		space = b;
		interfaz = c;

		setSize(640, 480);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		ImageIcon iconFondo = new ImageIcon("./src/main/resources/data/imagenes/fondoJuego.jpg");

		g.drawImage(iconFondo.getImage(), 0, 0, null);

		ImageIcon imagen = new ImageIcon("./src/main/resources/data/imagenes/Naves/nave.png");
		
		g.drawImage(
			imagen.getImage(),
			space.getJugadorActual().getPosInicialX(),
			space.getJugadorActual().getPosInicialY(),
			imagen.getIconWidth(),
			imagen.getIconHeight(),
			null
		);

		posJugadorActualX = space.getJugadorActual().getPosInicialX();

		//DIBUJAR INFORMACIÓN DEL JUGADOR
		g.setColor(Color.WHITE);
		g.setFont(FuenteInterfazGrafica.get(24));
		g.drawString("NICKNAME", 30, 40);
		g.drawString("PUNTUACIÓN ", 250, 40);

		g.setColor(Color.GREEN);
		g.drawString(interfaz.getJugadorActual().getNickname(), 140, 40);
		g.drawString(partida.getPuntaje().getPuntuacion() + "", 400, 40);

		// DISPARO DE LA NAVE
		Disparo a = space.getJugadorActual().getDisparoUno();
		if (a != null) {
			g.setColor(Color.WHITE);
			g.fillOval(a.getPosX() + 13, a.getPosY(), 7, 7);

			if (a.getPosY() == 0 || a.getImpacto()) {
				ImageIcon choque = new ImageIcon("./src/main/resources/data/imagenes/Naves/muereBicho.png");
				g.drawImage(choque.getImage(), a.getPosX(), a.getPosY() - 6, null);
			}

		}

		// DISPARO ENEMIGO

		for (int i = 0; i < partida.getEnemigos().length; i++) {
			for (int j = 0; j < partida.getEnemigos()[i].length; j++) {
				if ( partida.getEnemigos()[i][j] != null && partida.getEnemigos()[i][j].getDisparoUno() != null ) {
						Disparo b = partida.getEnemigos()[i][j].getDisparoUno();
						g.setColor(Color.RED);
						g.fillOval(b.getPosX(), b.getPosY(), 7, 7);
				}
			}
		}

		// DIBUJAR ENEMIGOS

		for (int i = 0; i < partida.getEnemigos().length; i++) {
			for (int j = 0; j < partida.getEnemigos()[i].length; j++) {
				if ( partida.getEnemigos()[i][j] != null ) {
						ImageIcon icono = new ImageIcon( partida.getEnemigos()[i][j].getRutaImage() );
						g.drawImage (
							icono.getImage(),
							partida.getEnemigos()[i][j].getPosX(),
							partida.getEnemigos()[i][j].getPosY(),
							null
						);
				}
			}
		}

		if (space.getPartidaActual().terminarNivel()) {
			space.setEnFuncionamiento(false);
			interfaz.matarHilos();

			int bonificacion = (space.puntosPorVida() - space.puntosPorDisparos());
			if (bonificacion > 0) {
				space.getPartidaActual().getPuntaje().setPuntuacion( bonificacion );
			}

			interfaz.nivelCompleto();
		}

		// PERDIÓ
		if (space.getJugadorActual().perdio()) {
			space.setEnFuncionamiento(false);	
			interfaz.matarHilos();
			int bonificacion = (space.puntosPorVida() - space.puntosPorDisparos());
			if (bonificacion > 0) {
				space.getPartidaActual().getPuntaje().setPuntuacion( bonificacion );
			}
			interfaz.perder();
		}
		
		if (interfaz.estaEnFuncionamiento()) {
			g.drawString("Vidas jugador" + space.getJugadorActual().getVida(), 100, 470);
		}
		
	}

	public int getPosJugadorActualX() {
		return posJugadorActualX;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}
