package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Nave {
	private double x;
	private double y;
	private double escala;
	private double angulo;
	private Image ima;
	private double diametro;
	private double velocidad;
	//Agrego cantProyectiles para trabajar sobre esta variable
	
	
	
	public Nave(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.1;
		this.angulo = 0;
		this.diametro = 50;
		this.velocidad = 2;
		
		
		
		ima = Herramientas.cargarImagen("nave.png");
	}
	
	
	// Getters and Setters
	public double getX() {
		return x;
	}

	
	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}
	
	
	public double getDiametro() {
		return diametro;
	}
	
	


	public void dibujar(Entorno e) {
		e.dibujarImagen(ima, x, y, angulo, escala);
	//	e.dibujarCirculo(x, y, diametro, Color.darkGray);
	}
	
	
	// Movimiento de nave 
	public void moverDerecha(Entorno e) {
		if (x + diametro / 2 < e.ancho())
			this.x = x + velocidad;
	}

	public void moverIzquierda() {
		if (x - diametro / 2 > 0)
			this.x= x - velocidad;
	}
	
	public void subirVelocidad() {
		velocidad += 0.3;
	}

	public Proyectil disparar() {
		return new Proyectil (x, y-diametro/2);
	}
	
	


}
