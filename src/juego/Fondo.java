package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {

	private double x;
	private double y;
	private Image imagenFondo;
	private double alturaImagen;
	
	public Fondo(double x, double y) {
		this.x = x;
		this.y = y;
		
		imagenFondo = Herramientas.cargarImagen("background.png");
		alturaImagen = imagenFondo.getHeight(null);
	}
	

	public void dibujar(Entorno e) {
        e.dibujarImagen(imagenFondo, x, y, 0, 1);
        e.dibujarImagen(imagenFondo, x, y - alturaImagen, 0, 1);
    }
	

    public void movimiento(Entorno e) {
        this.y += 0.5;

        // Si la imagen ha salido completamente de la pantalla por abajo, vuelve a aparecer por arriba
        if (this.y > e.alto()+alturaImagen/2) {
            this.y = y - alturaImagen;
        }
    }
	
}
