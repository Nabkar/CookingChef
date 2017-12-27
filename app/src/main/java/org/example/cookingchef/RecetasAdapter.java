package org.example.cookingchef;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JulioM on 27/12/2017.
 */

public class RecetasAdapter extends RecyclerView.Adapter <RecetasAdapter.ListaViewHolder> {
	private List<Receta> items;

	public static class ListaViewHolder extends RecyclerView.ViewHolder {
		// Campos respectivos de un item
		public ImageView imagen;
		public TextView titulo;
		public RatingBar dificultad;
		public TextView tags;
		public ListaViewHolder(View v) {
			super(v);
			imagen = (ImageView) v.findViewById(R.id.imagen);
			titulo = (TextView) v.findViewById(R.id.titulo);
			dificultad = (RatingBar) v.findViewById(R.id.dificultad);
			tags = (TextView) v.findViewById(R.id.tags);
		}
	}

	public RecetasAdapter(List<Receta> items) {
		this.items = items;
	}
	@Override
	public int getItemCount() {
		return items.size();
	}
	@Override
	public ListaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.receta, viewGroup, false);
		return new ListaViewHolder(v);
	}
	@Override
	public void onBindViewHolder(ListaViewHolder viewHolder, int i) {
		viewHolder.imagen.setImageResource(items.get(i).getImagen());
		viewHolder.titulo.setText(items.get(i).getTitulo());
		//viewHolder.dificultad.setNumStars(items.get(i).getDificultad());
		viewHolder.dificultad.setRating(items.get(i).getDificultad());
		viewHolder.tags.setText("Tags: " + items.get(i).getTags());
	}
}