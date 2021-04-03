package mundo;

import excepciones.PartidaYaExisteException;

import java.io.*;
import java.util.List;

/**
 * @author Manuel Alejandro Coral Lozano - Juan Sebastián Quintero Yoshioka
 * Proyecto final - Algoritmos y programación II.
 */
public class Partida implements Serializable {

	private       Partida     partidaIzquierda;
	private       Partida     partidaDerecha;
	private       Enemigo[][] enemigos;
	private       Puntaje     puntaje;
	private       Nivel       nivel;
	private final String      nombre;

	public Partida ( String nombre ) {
		this.nombre = nombre;
		nivel = new Nivel("1", 0, 0, 0, 0, 0 );
	}

	public String getNombre () {
		return nombre;
	}

	public Partida getPartidaIzquierda () {
		return partidaIzquierda;
	}

	public void setPartidaIzquierda ( Partida partidaIzquierda ) {
		this.partidaIzquierda = partidaIzquierda;
	}

	public Partida getPartidaDerecha () {
		return partidaDerecha;
	}

	public void setPartidaDerecha ( Partida partidaDerecha ) {
		this.partidaDerecha = partidaDerecha;
	}

	public Enemigo[][] getEnemigos () {
		return enemigos;
	}

	public void setEnemigos ( Enemigo[][] enemigos ) {
		this.enemigos = enemigos;
	}

	public Nivel getNivel () {
		return nivel;
	}

	public void setNivel ( Nivel nivel ) {
		this.nivel = nivel;
	}

	public Puntaje getPuntaje () {
		return puntaje;
	}

	public void setPuntaje ( Puntaje puntaje ) {
		this.puntaje = puntaje;
	}

	public void agregarPartida ( Partida nodo ) throws PartidaYaExisteException {

		if ( this.nombre.compareToIgnoreCase( nodo.nombre ) == 0 ) {
			throw new PartidaYaExisteException( nodo.nombre );
		} else if ( this.nombre.compareToIgnoreCase( nodo.nombre ) > 0 ) {

			if ( partidaIzquierda == null ) {
				setPartidaIzquierda( nodo );
			} else {
				partidaIzquierda.agregarPartida( nodo );
			}

		} else {

			if ( partidaDerecha == null ) {
				setPartidaDerecha( nodo );
			} else {
				partidaDerecha.agregarPartida( nodo );
			}

		}

	}

	public Partida buscarPartida ( String nombre ) {

		if ( this.getNombre().equals( nombre ) ) {
			return this;
		} else {
			if (this.getNombre().compareToIgnoreCase(nombre) > 0
				&& this.getPartidaIzquierda() != null) {
				return getPartidaIzquierda().buscarPartida( nombre );
			} else if (this.getNombre().compareToIgnoreCase(nombre) < 0
					&& this.getPartidaDerecha() != null) {
				return getPartidaDerecha().buscarPartida( nombre );
			}
		}

		return null;
	}

	public void inicializarPartida () throws IOException {
		File archivo = new File( "" );

		if ( nivel.getNivel().equals( "1" ) ) {
			archivo = new File( "./src/main/resources/data/nivel1.txt" );
		} else if ( nivel.getNivel().equals( "2" ) ) {
			archivo = new File( "./src/main/resources/data/nivel2.txt" );
		}

		try ( FileReader fr = new FileReader( archivo );
			BufferedReader br = new BufferedReader( fr ) ) {

			String linea = br.readLine();

			linea = br.readLine();

			String nivelActual = linea;

			linea = br.readLine(); // Code smell needed. Do not delete
			linea = br.readLine();
			int filas = Integer.parseInt( linea );

			linea = br.readLine(); // Code smell needed. Do not delete
			linea = br.readLine();
			int colums = Integer.parseInt( linea );

			enemigos = new Enemigo[filas][colums];

			linea = br.readLine(); // Code smell needed. Do not delete
			linea = br.readLine();

			int vidaEnemigo = Integer.parseInt( linea );

			linea = br.readLine(); // Code smell needed. Do not delete
			linea = br.readLine();

			linea = br.readLine(); // Code smell needed. Do not delete
			linea = br.readLine();

			int velocidad = Integer.parseInt( linea );

			linea = br.readLine(); // Code smell needed. Do not delete
			linea = br.readLine();
			String[] arreglo = linea.split( "\t" );

			this.nivel = new Nivel( nivelActual, velocidad, vidaEnemigo, Integer.parseInt( arreglo[0] ), Integer.parseInt( arreglo[1] ), Integer.parseInt( arreglo[2] ) );

			inicializarEnemigos();

		}
	}

	public void inicializarEnemigos () {
    int posX;
    int posY;

    InvasorFabrica fabrica;
    InvasorCalamar calamar;
    InvasorCangrejo cangrejo;
    InvasorPulpo pulpo;

    fabrica = new InvasorFabrica();
    fabrica.setNivel(  this.nivel );

    for ( int i = 0; i < enemigos.length; i++ ) {
			for ( int j = 0; j < enemigos[i].length; j++ ) {

				if ( i == 0 ) {

          posX = (j * nivel.getPosXPrimerEnemigo() + nivel.getPosXPrimerEnemigo());
          posY = nivel.getPosYPrimerEnemigo();

          calamar  = (InvasorCalamar) fabrica.crearInvasor( "Calamar", posX, posY );

					enemigos[i][j] = calamar;

				} else if ( i == 1 || i == 2 ) {

          posX = (j * nivel.getPosXPrimerEnemigo() + nivel.getPosXPrimerEnemigo());
          posY = (i * nivel.getPosYPrimerEnemigo() + nivel.getPosYPrimerEnemigo());

          cangrejo = (InvasorCangrejo) fabrica.crearInvasor( "Cangrejo", posX, posY );
					enemigos[i][j] = cangrejo;

				} else if ( i == 3 || i == 4 ) {

          posX = (j * nivel.getPosXPrimerEnemigo() + nivel.getPosXPrimerEnemigo());
          posY = (i * nivel.getPosYPrimerEnemigo() + nivel.getPosYPrimerEnemigo());

          pulpo    = (InvasorPulpo) fabrica.crearInvasor( "Pulpo", posX, posY);
					enemigos[i][j] = pulpo;

				}
			}
		}

	}

	public void eliminarUnEnemigo ( boolean elimino, Enemigo a ) {
		boolean salida = false;

		if ( elimino ) {
			for ( int i = 0; i < enemigos.length && !salida; i++ ) {
				for ( int j = 0; j < enemigos[0].length && !salida; j++ ) {
					if ( enemigos[i][j] != null && enemigos[i][j].equals( a ) ) {
						enemigos[i][j] = null;
						salida = true;
					}
				}
			}
		}
	}

	public boolean terminarNivel () {
		int contador = 0;

		for ( Enemigo[] enemigo : enemigos ) {
			for ( Enemigo value : enemigo ) {
				if ( value == null ) {
					contador++;
				}
			}
		}

		return ( contador == ( enemigos.length * enemigos[0].length ) );
	}

	public boolean nivelCompleto () throws IOException {
		if ( nivel.getNivel().equals( "1" ) ) {
			nivel.setNivel( "2" );
			inicializarPartida();
			return true;
		} else {
			return false;
		}
	}

	@Override public String toString () {
		return nombre;
	}

	/**
	 * Devuelve listado de partidas ordenadas
	 *
	 */
	public void inOrden ( List<Partida> acumulado ) {
		if ( partidaIzquierda != null ) {
			partidaIzquierda.inOrden( acumulado );
		}

		acumulado.add( this );

		if ( partidaDerecha != null ) {
			partidaDerecha.inOrden( acumulado );
		}
	}

	public Partida eliminar ( String nombre ) {
		if ( esHoja() ) {
			return null;
		}

		if ( this.nombre.compareToIgnoreCase( nombre ) == 0 ) {
			if ( partidaIzquierda == null ) {
				return partidaDerecha;
			}

			if ( partidaDerecha == null ) {
				return partidaIzquierda;
			}

			Partida sucesor = partidaDerecha.darMenor();

			partidaDerecha = partidaDerecha.eliminar( sucesor.getNombre() );

			sucesor.partidaIzquierda = partidaIzquierda;
			sucesor.partidaDerecha = partidaDerecha;
			return sucesor;
		} else if ( this.nombre.compareToIgnoreCase( nombre ) > 0 ) {
			partidaIzquierda = partidaIzquierda.eliminar( nombre );
		} else {
			partidaDerecha = partidaDerecha.eliminar( nombre );
		}
		return this;
	}

	public Partida darMenor () {
		return ( partidaIzquierda == null ) ? this : partidaIzquierda.darMenor();
	}

	public boolean esHoja () {
		return ( partidaIzquierda == null && partidaDerecha == null );
	}

}