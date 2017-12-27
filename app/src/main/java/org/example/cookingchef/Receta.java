package org.example.cookingchef;

import java.util.List;

/**
 * Created by JulioM on 27/12/2017.
 */

public class Receta {
	private int imagen;
	private String titulo;
	private int dificultad; // Entero entre 0 y 5
	private List<String> tags;

	public Receta(int imagen, String titulo, int dificultad, List<String> tags) {
		this.imagen = imagen;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tags = tags;
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
}
