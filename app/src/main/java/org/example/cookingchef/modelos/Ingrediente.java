package org.example.cookingchef.modelos;

import org.example.cookingchef.R;

import java.io.Serializable;

/**
 * Created by JulioM on 02/01/2018.
 */

public class Ingrediente implements Serializable {

	private String producto;
	private float cantidad;
	private String unidades;

	public Ingrediente(String producto, float cantidad, String unidades) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.unidades = unidades;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	/*@Override
	public String toString() {
		return //cantidad + " " + unidades + " de " + producto + "\n";
				"Ingrediente{" +
					"producto='" + producto + '\'' +
					", cantidad=" + cantidad +
					", unidades='" + unidades + '\'' +
				'}';
	}*/
}
