package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Vida {
	private double x; 
	private double y;
	private double escala;
	private double diametro;
	private double angulo;
	private Image vida;
	
	public Vida (double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.03;
		this.diametro = 40;
		this.angulo = 0;
		vida = Herramientas.cargarImagen("Vida.png");
	}
	
	public void dibujar (Entorno e) {
		e.dibujarImagen(vida, x, y, angulo, escala);
	//	e.dibujarCirculo(x, y, diametro, Color.blue);
	}
	
	public void bajar() {
		y++;
	}

	public boolean tocaNave(Nave nave) {
		if (Math.sqrt(Math.pow((x - nave.getX()),2) + Math.pow((y - nave.getY()),2)) < diametro/2 + nave.getDiametro()/2) {
			return true;
	}

		return false;
	}
	
	public static void agregarVida(Vida[] v, double x, double y) {
		for(int i = 0; i < v.length; i++) {
			if(v[i] == null) {
				v[i] = new Vida (x, y);
				
				return;
			}
		}
	}

	public boolean tocoAbajo(Entorno e) {
		if (y - diametro  / 2 > e.alto()  ) {
			return true;
		}
		return false;
		
	}
	
}



