package juego;


import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {
	private double x; 
	private double y;
	private double angulo;
	private double escala;
	private double diametro;
	private double velocidad;
	private Image ima;
	
	public Enemigo(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.15;
		this.diametro = 60;
		Random r = new Random();
		this.angulo = Math.PI/4+Math.PI/2*r.nextInt(2);
		this.velocidad = 1;
		
		ima = Herramientas.cargarImagen("enemigos.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(ima, x, y, Math.PI, escala);
	//	e.dibujarCirculo(x, y, diametro, Color.cyan);
	}
	
	
	public void bajar() {
		y++;
	}
	
	
	public void mover() {
		this.x = x + Math.cos(angulo)*velocidad;
		this.y = y + Math.sin(angulo)*velocidad;
	}
	
	
	public boolean llegoAlBorde(Entorno e) {
		if (x - diametro/2 - 10 < 0) {
			return true;
		}
		
		if (x + diametro/2 + 10 > e.ancho()) {
			return true;
		}
		
		return false;
	}
	
	
	public void rebotar(Entorno e) {
			angulo = Math.PI - angulo;
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
	
	public Proyectil disparar() {
		return new Proyectil (x, y+diametro/2);
	}

	public boolean tocaEnemigo(Enemigo ene) {
		if (Math.sqrt(Math.pow((x - ene.getX()),2) + Math.pow(( y - ene.getY()),2)) <= diametro / 2 + ene.getDiametro() / 2) {
			return true;
		}
		return false;
	}

	public boolean tocaLaNave(Nave nave) {
		if (Math.sqrt(Math.pow((x - nave.getX()),2) + Math.pow((y - nave.getY()),2)) < diametro/2 + nave.getDiametro()/2) {
			return true;
	}

		return false;
	}

	public boolean tocaObstaculo(Obstaculo obs) {
		if (Math.sqrt(Math.pow((x - obs.getX()),2) + Math.pow(( y - obs.getY()),2)) <= diametro / 2 + obs.getDiametro() / 2) {
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

	public static void agregarEnemigo(Enemigo[] ene) {
		for(int i = 0; i < ene.length; i++) {
			if(ene[i] == null) {
				ene[i] = new Enemigo(   150*i + 175 ,  -50*i - 100);
				
				return;
			}
		}
	}

}
		
	


	

