package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Nave nave;
	private Proyectil[] proyectiles;
	private Proyectil[] proyectilesEne;
	private Obstaculo[] obstaculos;
	private Enemigo[] enemigos;
	private Vida[] vida;
	private Rayo[] rayo;
	private Fondo fondo;
	private int puntaje;
	private int eliminados;
	private int contador;
	private int contadorVida;
	private int contadorRayo;
	private int vidas;
	private boolean gano;
	private Image nav;
	

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 5 - Miranda - Kintal - Ramirez - V0.01", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		this.fondo = new Fondo(entorno.ancho()/2, entorno.alto()/2);
		
		this.nav = Herramientas.cargarImagen("nave.png");
		
		this.puntaje = 0;
		
		this.gano = false;
		
		this.contador = 0;
		
		this.contadorVida = 0;
		
		this.eliminados = 0;
		
		this.vidas = 3;
		
		this.vida = new Vida[1];
		
		this.rayo = new Rayo[1];
		
		this.nave = new Nave(entorno.ancho() / 2, entorno.alto() - 50);

		this.obstaculos = new Obstaculo[5];
		obstaculos[0] = new Obstaculo(50, -200);
		obstaculos[1] = new Obstaculo(200, 0);
		obstaculos[2] = new Obstaculo(460, -400);
		obstaculos[3] = new Obstaculo(560, 100);
		obstaculos[4] = new Obstaculo(700, -600);


		// Crear enemigos
		this.enemigos = new Enemigo[4];
		enemigos[0] = new Enemigo(50,  10);
		enemigos[1] = new Enemigo(200, -120);
		enemigos[2] = new Enemigo(400, -80);
		enemigos[3] = new Enemigo(600, -160);

		// Crear arreglo proyectil
		this.proyectiles = new Proyectil[10];
		
		//crear arreglo proyectiles enemigos
		this.proyectilesEne = new Proyectil [8];
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */

	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		
		// Dibujo el fondo y le doy el movimiento
		if (fondo != null) {
			fondo.dibujar(entorno);
		}
		
		if (fondo != null) {
			fondo.movimiento(entorno);
		}
//		entorno.dibujarImagen(ima, entorno.ancho()/2, entorno.alto()/2, 1.55, 0.75);
		//Se encierra el código en el condicional para ver cuando se gana o cuando se pierde.
		if(vidas > 0 && puntaje < 200 && !gano) {
			
		// Mostrar los puntos 
		entorno.cambiarFont("impact", 30, Color.WHITE);
		entorno.escribirTexto("SCORE: " + puntaje, 11, 39);
		entorno.cambiarFont("impact", 30, Color.CYAN);
		entorno.escribirTexto("SCORE: " + puntaje, 10, 40);
	
		contador++;
		contadorVida++;
		contadorRayo++;
		
		//contador para los obstaculos y enemigos
		entorno.cambiarFont("impact", 30, Color.WHITE);
		entorno.escribirTexto("ELIMINADOS: " + eliminados, 11, 73);
		entorno.cambiarFont("impact", 30, Color.CYAN);
		entorno.escribirTexto("ELIMINADOS: " + eliminados, 10, 74);
		
		
		
		// ---------------------------------------------------------- N A V E -------------------------------------------------------------------------------------------- 
		
		// Antes de dibujar la nave, me aseguro que no sea null
		if (this.nave != null) {
			nave.dibujar(entorno);
		}
		
		// Movimiento de la nave
		if (entorno.estaPresionada('a') && this.nave != null || entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && this.nave != null) {
			nave.moverIzquierda();
		}
		if (entorno.estaPresionada('d') && this.nave != null || entorno.estaPresionada(entorno.TECLA_DERECHA) && this.nave != null) {
			nave.moverDerecha(entorno);
		}
		
		// Agrega los disparos de la nave al arreglo
		if (entorno.sePresiono(this.entorno.TECLA_ESPACIO) && this.nave != null) {
			Proyectil.agregarProyectil(proyectiles, nave.disparar());
		}
		
		
		//----------------------------------------------------- O B S T A C U L O S --------------------------------------------------------------------------------------
		
		// Dibujar los obstaculos
		for (int i=0; i<obstaculos.length; i++) {
			if(obstaculos[i] != null) {
				obstaculos[i].dibujar(entorno);
			}
		}

		// Movimiento de los obstaculos
		for (int i = 0; i < this.obstaculos.length; i++) {
			if (obstaculos[i] != null && obstaculos[i].getY() <= -10 ) {
				obstaculos[i].bajar();
			} 
			if (obstaculos[i] != null && obstaculos[i].getY() >= -10) {
				obstaculos[i].mover();
			}
			if(obstaculos[i]!= null && obstaculos[i].tocaBorde(entorno)) {
				obstaculos[i].rebotar(entorno);
			}
		}
				
		// Si un obstaculo toca la nave le quita vida
		for (int i = 0; i < this.obstaculos.length; i++) {
			if (obstaculos[i] != null && obstaculos[i].tocaLaNave(nave)) {
				vidas = vidas -1;
			}
		}
			
		// si un obstaculo desaparece se vuelve null 
		for (int i = 0; i < this.obstaculos.length; i++) {
			if (this.obstaculos[i] != null && this.obstaculos[i].llegoAlBordeAbajo(entorno))
				this.obstaculos[i] = null;
			}
				
		// colision de obstaculo con otro obstaculo 
		for (int i = 0; i < this.obstaculos.length; i++) {
			for(int j = i+1; j< this.obstaculos.length; j++) {
				if (obstaculos[i] != null && obstaculos [j]!= null && obstaculos[i].tocaObstaculo(obstaculos[j])) {
					obstaculos[i].rebotar(entorno);
					obstaculos[j].rebotar(entorno);
				}else {
					Obstaculo.agregarObstaculo(obstaculos);
				}
			}
		}
		
		//---------------------------------------------------- E N E M I G O S ------------------------------------------------------------------------------------------
	
		// Dibujar los enemigos
		for (int i = 0; i < this.enemigos.length; i++) {
			if (this.enemigos[i] != null)
				this.enemigos[i].dibujar(entorno);
		}
		

		// Movimiento de los enemigos
		for (int i = 0; i < this.enemigos.length; i++){
			if (enemigos[i] != null && enemigos[i].getY() <= -10 ) {
				enemigos[i].bajar();
			} 
			if (enemigos[i] != null && enemigos[i].getY() >= -10 ) {
				enemigos[i].mover();
			}
			if (enemigos[i] != null && enemigos[i].llegoAlBorde(entorno)) {
				enemigos[i].rebotar(entorno);
			}
		}
		
		//colision de nave enemigo con nave aliada quita vida
		for (int i = 0; i < this.enemigos.length; i++) {
			if (enemigos[i] != null && nave != null && enemigos[i].tocaLaNave(nave)) {
				vidas = vidas -1;
			}
		}
		
		// colision de un enemigo con otro 
		for (int i = 0; i < this.enemigos.length; i++) {
			for(int j = i+1; j< this.enemigos.length; j++) {
				if (enemigos[i] != null && enemigos [j]!= null && enemigos[i].tocaEnemigo(enemigos[j])) {
					enemigos[i].rebotar(entorno);
					enemigos[j].rebotar(entorno);
				}else {
					Enemigo.agregarEnemigo(enemigos);
				}
			}
		}
		
		// colision de enemigo con obstaculo
		for (int i = 0; i < this.enemigos.length; i++) {
			for(int j = 0; j< this.obstaculos.length; j++) {
				if (enemigos[i] != null && obstaculos [j]!= null && enemigos[i].tocaObstaculo(obstaculos[j])) {	
					enemigos[i].rebotar(entorno);
					obstaculos[j].rebotar(entorno);
				}
			}
		}
		
		// si un enemigo desaparece se vuelve null 
		for (int i = 0; i < this.enemigos.length; i++) {
			if (this.enemigos[i] != null && this.enemigos[i].llegoAlBordeAbajo(entorno))
				this.enemigos[i] = null;
		}
		
		
		//--------------------------------------------------- P R O Y E C T I L E S - A L I A D O S------------------------------------------------------------------------

		// Crea los disparos de la nave los dibuja y los mueve
		for (int i = 0; i < proyectiles.length; i++) {
			if (proyectiles[i] != null) {
				proyectiles[i].moverArriba();
				proyectiles[i].dibujar(entorno);
			}
		}
		
		// Eliminar proyectil si llega al borde  de arriba 
		for (int i = 0; i < this.proyectiles.length; i++) {
			if ((this.proyectiles[i] != null) && (this.proyectiles[i].llegoAlBorde(entorno)))
				this.proyectiles[i] = null;
		}
		

		// Si un proyectil toca un obstaculo se vuelve null
		for (int i = 0; i < this.proyectiles.length; i++) {
			for (int j = 0; j < this.obstaculos.length; j++) {
				if(proyectiles[i] != null && obstaculos[j] != null && proyectiles[i].tocaObstaculo(obstaculos[j])) {
					proyectiles[i] = null;
				}
			}
		}
		
		// Si un proyectil toca un enemigo lo vuelve null al enemigo y al proyectil
		for (int i = 0; i < this.proyectiles.length; i++) {
			for (int j = 0; j < this.enemigos.length; j++) {
				if(enemigos[j] != null && proyectiles[i] != null && proyectiles[i].tocaEnemigo(enemigos[j])) {
					proyectiles[i] = null;
					enemigos[j] = null;
					puntaje = puntaje + 10;
					eliminados = eliminados + 1;
				}
			}
		}
		
		
		
		//------------------------------------------------- P R O Y E C T I L E S - E N E M I G O S -----------------------------------------------------------------------
		
		// cuando el contador es 100 y el y es positico el enemigo dispara 
		if (contador == 100) {
		      for (int i = 0; i < enemigos.length; i++) {
		        if (enemigos[i] != null && enemigos[i].getY() >= 0) {
		          Proyectil.agregarProyectil(proyectilesEne, enemigos[i].disparar());
		          contador = 0;
		        }
		      }
		}

		// Crea los disparos de los enemigos los dibuja y los mueve 
		for (int i = 0; i < proyectilesEne.length; i++) {
			if (proyectilesEne[i] != null) {
				proyectilesEne[i].moverAbajo();
				proyectilesEne[i].dibujarProyectilEnemigo(entorno);		
			}
		}
		
		// cuando el proyectil enemigo toca el borde de abajo se vuelve null  
		for (int i = 0; i < this.proyectilesEne.length; i++) {
			if (this.proyectilesEne[i] != null && this.proyectilesEne[i].llegoAlBordeAbajo(entorno)) {
				this.proyectilesEne[i] = null;
		}
	}
		
		// si un proyectil enemigo toca la nave le quita vida y si vidas es igual a 0 se vuelve null
		for (int i = 0; i < this.proyectilesEne.length; i++) {
			if (proyectilesEne[i] != null && nave != null  && proyectilesEne[i].tocaLaNave(nave)) {
				vidas = vidas -1;
				proyectilesEne[i] = null;
				
			}
		}
		
		//-------------------------------------- ITEMS --------------------------------------------------------
		
		//--------VIDAS-----------------------
		// Agrega vidas al arreglo
		if (contadorVida == 600) {
			Vida.agregarVida(vida, 70 + Math.random()*700, 0);
			contadorVida = 0;
		}
		// Dibuja las vidas y les da movimiento 
		for (int i = 0; i<vida.length; i++) {
			if(vida[i]!= null) {
			vida[i].dibujar(entorno);
			vida[i].bajar();	
			} // suma vida si toca la nave y la vuelve null
			if(vida[i]!= null && vida[i].tocaNave(nave) && vidas <3) {
				vida[i]= null;	
				vidas = vidas + 1;
				}
			}
		// Si llega al borde de abajo lo hace null 
		for (int i = 0; i<vida.length; i++) {
			if(vida[i] != null && vida[i].tocoAbajo(entorno)) {
				vida[i] = null;
			}
		}
		
		// Dibujar las vidas en pantalla 
		if( vidas == 1) {
			entorno.dibujarImagen(nav, 700, 30, 0, 0.05);
		}
		if( vidas == 2) {
			entorno.dibujarImagen(nav, 700, 30, 0, 0.05);
			entorno.dibujarImagen(nav, 740, 30, 0, 0.05);
		}
		if( vidas == 3) {
			entorno.dibujarImagen(nav, 700, 30, 0, 0.05);
			entorno.dibujarImagen(nav, 740, 30, 0, 0.05);
			entorno.dibujarImagen(nav, 780, 30, 0, 0.05);
		}
		
		// ------------- RAYO ---------------------
		
		if (contadorRayo == 1000) {
			Rayo.agregarRayo(rayo, 70 + Math.random()*700, 0);
			contadorRayo = 0;
		}
		// Dibuja los rayos y les da movimiento 
		for (int i = 0; i<rayo.length; i++) {
			if(rayo[i]!= null) {
			rayo[i].dibujar(entorno);
			rayo[i].bajar();	
			} // Suma velocidad si toca la nave y lo vuelve null
			if(rayo[i]!= null && rayo[i].tocaNave(nave)) {
				rayo[i]= null;	
				nave.subirVelocidad();
				}
			}
		// Si llega al borde de abajo lo hace null 
		for (int i = 0; i<vida.length; i++) {
			if(vida[i] != null && vida[i].tocoAbajo(entorno)) {
				vida[i] = null;
			}
		}
		
		//----------------------------- F I N - D E L - J U E G O --------------------------------------------------
		
		if(nave != null && vidas <= 0) {
			nave = null;
		}
		
		}
		
		// Mensaje al terminar el juego si se ganó o se perdió 
		if ( enemigos[0] != null && enemigos[1] != null && enemigos[2] != null && enemigos[3] != null &&enemigos[0].getY() < 0 && enemigos[1].getY() < 0 && enemigos[2].getY() < 0 && enemigos[3].getY() < 0) {
			gano = true;
			entorno.cambiarFont("IMPACT", 70, Color.CYAN);
			entorno.escribirTexto("YOU WIN", 280, 300);
			entorno.cambiarFont("impact", 30, Color.LIGHT_GRAY);
			entorno.escribirTexto("SCORE : " + puntaje, 350, 350);
			entorno.escribirTexto("ELIMINADOS : " + eliminados, 320, 400);
		}
		if(puntaje >= 200) {
			entorno.cambiarFont("IMPACT", 70, Color.red);
			entorno.escribirTexto("YOU WIN", 280, 300);
			entorno.cambiarFont("impact", 30, Color.LIGHT_GRAY);
			entorno.escribirTexto("SCORE : " + puntaje, 350, 350);
			entorno.escribirTexto("ELIMINADOS : " + eliminados, 320, 400);
		}
		
		if(vidas <= 0) {
			entorno.cambiarFont("IMPACT", 70, Color.red);
			entorno.escribirTexto("GAME OVER", 250, 300);
			entorno.cambiarFont("impact", 30, Color.LIGHT_GRAY);
			entorno.escribirTexto("SCORE : " + puntaje, 350, 350);
			entorno.escribirTexto("ELIMINADOS : " + eliminados, 320, 400);
		}
		
		
		
		}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
