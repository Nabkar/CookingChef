package org.example.cookingchef.modelos;

import java.io.Serializable;

/**
 * Created by JulioM on 02/01/2018.
 */

public class Paso implements Serializable{
	private int nPaso;
	private String paso;

	public Paso(int nPaso, String paso) {
		this.nPaso = nPaso;
		this.paso = paso;
	}

	public int getnPaso() {
		return nPaso;
	}

	public void setnPaso(int nPaso) {
		this.nPaso = nPaso;
	}

	public String getPaso() {
		return paso;
	}

	public void setPaso(String paso) {
		this.paso = paso;
	}

	/*@Override
	public String toString() {
		return //nPaso + " - " + paso + "\n";
				"Paso{" +
					"nPaso=" + nPaso +
					", paso='" + paso + '\'' +
				'}';
	}*/
}
