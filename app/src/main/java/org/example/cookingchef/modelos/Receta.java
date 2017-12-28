package org.example.cookingchef.modelos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by JulioM on 27/12/2017.
 */

public class Receta implements Serializable{
	private int idReceta;
	private int imagen;
	private String titulo;
	private int dificultad; // Entero entre 0 y 5
	private List<String> tags;
	private Map<String,String> ingredientes;
	private Map<String,String> pasos;
	private String observaciones;
	private int comensales;

	/*public Receta(Receta receta) {
		this.idReceta = receta.getIdReceta();
		this.imagen = receta.getImagen();
		this.titulo = receta.getTitulo();
		this.dificultad = receta.getDificultad();
		this.tags = receta.getTags();
		this.ingredientes = receta.getIngredientes();
		this.pasos = receta.getPasos();
		this.observaciones = receta.getObservaciones();
		this.comensales = receta.getComensales();
	}*/

	public Receta (int idReceta, int imagen, String titulo, int dificultad, List<String> tags, Map<String,String> ingredientes, Map<String,String> pasos, String observaciones, int comensales ){
		this.idReceta = idReceta;
		this.imagen = imagen;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tags = tags;
		this.ingredientes = ingredientes;
		this.pasos = pasos;
		this.observaciones = observaciones;
		this.comensales = comensales;
	}

	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public int getImagen() {
		return imagen;
	}

	public void setImagen(int imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Map<String, String> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Map<String, String> getPasos() {
		return pasos;
	}

	public void setPasos(Map<String, String> pasos) {
		this.pasos = pasos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getComensales() {
		return comensales;
	}

	public void setComensales(int comensales) {
		this.comensales = comensales;
	}

	@Override
	public String toString() {
		return "Receta{" +
				"imagen=" + imagen +
				", titulo='" + titulo + '\'' +
				", dificultad=" + dificultad +
				", tags=" + tags +
				", ingredientes=" + ingredientes +
				", pasos=" + pasos +
				", observaciones='" + observaciones + '\'' +
				'}';
	}
}
