package org.example.cookingchef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JulioM on 27/12/2017.
 */

public class Receta {
	private int imagen;
	private String titulo;
	private int dificultad; // Entero entre 0 y 5
	private List<String> tags;
	private Map<String,String> ingredientes;
	private Map<Integer,String> pasos;
	private String observaciones;


	public Receta(int imagen, String titulo, int dificultad, List<String> tags) {
		this.imagen = imagen;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tags = tags;
	}

	public Receta (Receta receta){
		this.imagen = receta.getImagen();
		this.titulo = receta.getTitulo();
		this.dificultad = receta.getDificultad();
		this.tags = receta.getTags();
		this.ingredientes = receta.getIngredientes();
		this.pasos = receta.getPasos();
		this.observaciones = receta.getObservaciones();
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

	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public void deleteTag(String tag) {
		this.tags.remove(tags.indexOf(tag));
	}

	public Map<String, String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Map<String, String> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Map<Integer, String> getPasos() {
		return pasos;
	}

	public void setPasos(Map<Integer, String> pasos) {
		this.pasos = pasos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
