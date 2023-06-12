package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rayo {
	
	private double x; 
	private double y;
	private double angulo;
	private double escala;
	private double diametro;
	private Image rayo;
	
	public Rayo(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.09;
		this.diametro = 40;
		this.angulo = 0;
		this.rayo = Herramientas.cargarImagen("rayo.png");
	}
	
	public void dibujar (Entorno e) {
		e.dibujarImagen(rayo, x, y, angulo, escala);
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
	
	public static void agregarRayo(Rayo[] r, double x, double y) {
		for(int i = 0; i < r.length; i++) {
			if(r[i] == null) {
				r[i] = new Rayo (x, y);
				
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
