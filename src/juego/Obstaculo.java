package juego;


import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x; 
	private double y;
	private double escala;
	private double diametro;
	private double angulo;
	private double velocidad;
	private Image asteroide;
	
	
	public Obstaculo(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala= 0.15;
		this.diametro = 65;
		this.asteroide=Herramientas.cargarImagen("asteroide.png");
		Random r = new Random();
		this.angulo = Math.PI/4+Math.PI/2*r.nextInt(2);
		this.velocidad = 1;
}
	
	public void dibujar (Entorno e) {
		e.dibujarImagen(asteroide, x, y, 0, escala);
	//	e.dibujarCirculo(x, y, diametro, Color.RED);
	}

	
	//getters 
	public double  getX() {
		return x;
	}
	
	public double  getY() {
		return y;
	}
	public double  getDiametro() {
		return diametro;
	}
	
	public void bajar() {
		y++;
	}
	
	public void mover() {
		this.x = x + Math.cos(angulo)*velocidad;
		this.y = y + Math.sin(angulo)*velocidad;
	}
	
	
	public boolean tocaLaNave(Nave nave) {
		if (nave != null ) {
			if (Math.sqrt(Math.pow((x-nave.getX()),2) + Math.pow((y-nave.getY()),2)) < diametro/2 + nave.getDiametro()/2) {
					return true;
			}
		}
		return false;
	}

	public void rebotar(Entorno e) {
			angulo = Math.PI - angulo;
	}

	public boolean tocaBorde(Entorno e) {
		if (x - diametro/2 - 10 < 0) {
			return true;
		}
		
		if (x + diametro/2 + 10 > e.ancho()) {
			return true;
		}
		
		return false;
	}
	
	public boolean tocaObstaculo (Obstaculo obs) {
		if (Math.sqrt(Math.pow((x-obs.getX()),2) + Math.pow((y-obs.getY()),2)) <= diametro/2 + obs.getDiametro()/2) {
			return true;
		}
		return false;
	}
	
	public boolean tocaEnemigo(Enemigo e) {
			if (Math.sqrt(Math.pow((x-e.getX()),2) + Math.pow((y-e.getY()),2)) <= diametro/2 + e.getDiametro()/2) {
				return true;
			}
			return false;
		}
	
	
	

	public boolean llegoAlBordeAbajo(Entorno e) {
		if (y - diametro  / 2 > e.alto()  ) {
			return true;
		}
		return false;
		
	}
	
	public static void agregarObstaculo(Obstaculo[] obs) {
		for(int i = 0; i < obs.length; i++) {
			if(obs[i] == null) {
				obs[i] = new Obstaculo( 150*i + 100 , -20*i  -20);
				
				return;
			}
		}
	}

}
