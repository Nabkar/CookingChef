package org.example.cookingchef.modelos;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<String> ingredientes;
	private List<String> pasos;
	private String observaciones;
	private int comensales;

	public Receta(int idReceta, int imagen){
		this.idReceta = idReceta;
		this.imagen = imagen;
		this.titulo = "";
		this.dificultad = 0;
		this.tags = new ArrayList<>();
		this.ingredientes = new ArrayList<>();
		this.pasos = new ArrayList<>();
		this.observaciones = null;
		this.comensales = 2;
	}

	public Receta (int idReceta, int imagen, String titulo, int dificultad, List<String> tags, List<String> ingredientes, List<String> pasos, String observaciones, int comensales ){
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

	public List<String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<String> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<String> getPasos() {
		return pasos;
	}

	public void setPasos(List<String> pasos) {
		this.pasos = pasos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getComensales() {
		return String.valueOf(comensales);
	}

	public void setComensales(int comensales) {
		this.comensales = comensales;
	}

	/*@Override
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
	}*/

}
