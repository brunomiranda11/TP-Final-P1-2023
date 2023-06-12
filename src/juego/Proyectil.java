package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	private double x; 
	private double y;
	private double escala;
	private double diametro;
	private Image bala;
	private Image ion;
	
	
	public Proyectil (double x, double y) {
		this.x=x;
		this.y=y;
		this.escala= 0.15;
		this.diametro = 20;
		bala = Herramientas.cargarImagen("bala.png");
		ion = Herramientas.cargarImagen("iones.png");
	}
	
	public void dibujar(Entorno e){			
		e.dibujarImagen(bala, x , y  , 0 , escala);
//		e.dibujarCirculo(x, y, diametro, Color.ORANGE);
	}
	
	public void dibujarProyectilEnemigo(Entorno e){			
		e.dibujarImagen(ion, x , y  , 0 , escala);
//		e.dibujarCirculo(x, y, diametro, Color.ORANGE);
	}
	 
	public void moverArriba() {
		this.y = y-5;
	}
	
	public void moverAbajo() {
		this.y = y+3;
	}
	
	// Agrega proyectil al arreglo 
	public static void agregarProyectil(Proyectil[] arr, Proyectil p) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == null) {
				arr[i] = p;
				
				return;
			}
		}
	}
	
	//si el proyectil llego al borde superior 
	public boolean llegoAlBorde(Entorno e) {
		if (y + diametro  / 2 < e.alto() - e.alto() ) {
			return true;
		}
		return false;
	}
	
		
	//getters
	public double  getY() {
		return y;
	}
	

	public double getX() {
		return x;
	}
	
	//colision del proyectil con el obstaculo 
	public boolean tocaObstaculo (Obstaculo obs) {
		if (Math.sqrt(Math.pow((x-obs.getX()),2) + Math.pow((y-obs.getY()),2)) <= diametro/2 + obs.getDiametro()/2) {
			return true;
		}
		return false;
	}
		
	
	//colision del proyectil con el enemigo 
	public boolean tocaEnemigo(Enemigo ene) {
		if (Math.sqrt(Math.pow((x-ene.getX()),2) + Math.pow((y-ene.getY()),2)) <= diametro/2 + ene.getDiametro()/2) {
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

	public boolean tocaLaNave(Nave nave) {
		if (nave != null ) {
			if (Math.sqrt(Math.pow((x-nave.getX()),2) + Math.pow((y-nave.getY()),2)) < diametro/2 + nave.getDiametro()/2) {
					return true;
			}
		}
		return false;
	}


	
	
}
	

	
	

