# Space invaders

Proyecto de Java para jugar Space Invaders o Invasores espaciales

[![java](https://img.shields.io/badge/Java--jdk-1.8-critical)](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html)
[![junit](https://img.shields.io/badge/junit-5-critical)](https://junit.org/junit5/)
[![gradle](https://img.shields.io/badge/gradle-6.6.1-important)](https://gradle.org/install/)
[![sonarqube](https://img.shields.io/badge/SonarQube-8-informational)](https://www.sonarqube.org/downloads/)


## Ejecutar el proyecto

Para ejecutar el proyecto correr el comando
```
./gradlew.bat run
```

Para ejecutar las pruebas unitarias
```
./gradlew.bat test
```

Para correr cobertura
```
./gradlew.bat sonar
```

## Instrucciones de juego

Para poder jugar, se debe crear primero un jugador con el nombre que se
deseee y un nickname de 5 caracteres, el nombre puede ser cualquiera pero
el nickname es unico, después de esto, se puede crear una partida, que
tambien debe tener un nombre unico dentro de las partidas del jugador.

Cuando se crea una partida, el juego se comienza a ejecutar.

Además de las intrucciones que se pueden ver presionando el texto
Intrucciones en el panel de menu, también se puede pausar el juego
presionando la tecla p, y se sale del mismo presionando la tecla esc.

El juego consta de tres vidas, y se termina apenas maten al jugador o 
se pasen los dos niveles que presenta el juego. 

Cada jugador creado se guarda en un archivo, y cada partida se guarda
cuando el jugador presiona la tecla esc, es decir, se sale del mismo.

Cuando un jugador pierde o termina los niveles, la partida se elimina y
solo queda registro de esa partida en los puntajes, que es una lista
de los 10 mejores puntajes. 

Está la opción de login rápido, que permite inicar sesión rápidamente
cuando se sabe el nickname de un jugador creado.

También tiene la opción de seleccionar un jugador, lo que permite
buscarlo tanto en forma de que se agrega cada jugador como también
ordenarlos por el nickname.

Los mejores puntajes se realizan de acuerdo a la puntuación que se tiene.
Entre mejor puntaje, mejor es un jugador.

El programa puede almacenar muchos jugadores y también muchas partidas
para cada jugador, los puntajes solo se cuentan en cada una de las 
partidas del jugador, es decir, no es acumulable con la puntuacion que
se tiene en otras partidas.

El programa viene con unos jugadores y partidas ya creadas debido a que 
en los test se realizan estas acciones.

Todo el progreso de un juego queda serialzado, asi como los puntajes.

## Información técnica

El proyecto arranca en el archivo

src/java/interfaz/InterfazSpaceInvaders.java

Requiere java 1.8