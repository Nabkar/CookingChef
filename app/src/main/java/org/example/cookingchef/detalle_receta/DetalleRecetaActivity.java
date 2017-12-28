package org.example.cookingchef.detalle_receta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.example.cookingchef.R;
import org.example.cookingchef.modelos.Receta;

public class DetalleRecetaActivity extends AppCompatActivity {

	private int idReceta;
	private enum Estados {LECTURA,EDICION}
	private String estado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_receta);

		Receta receta = (Receta) getIntent().getExtras().getSerializable("receta_card");
		estado = (String) getIntent().getExtras().getString("estado");
		idReceta = receta.getIdReceta();

		// Cargamos todos los datos en la vista
		Toolbar toolbar = findViewById(R.id.detail_toolbar);
		toolbar.setTitleTextColor(getResources().getColor(R.color.colorTextoBlanco));
		toolbar.setTitle("");
		ImageView imageView = findViewById(R.id.imagen);
		toolbar.setTitle(receta.getTitulo());
		imageView.setImageResource(receta.getImagen());
		RatingBar dificultad = findViewById(R.id.dificultad);
		dificultad.setRating(receta.getDificultad());
		EditText tags = findViewById(R.id.etTags);
		tags.setText(receta.getTags().toString());
		/*EditText ingredientes = findViewById(R.id.etIngredientesLista);
		ingredientes.setText(receta.getIngredientes().toString());
		EditText pasos = findViewById(R.id.etPasos);
		pasos.setText(receta.getPasos().toString());
		EditText tips = findViewById(R.id.etTips);
		tips.setText(receta.getObservaciones());
		EditText comensales = findViewById(R.id.etComensales);
		comensales.setText(receta.getComensales());*/

		/*if (estado.equals(Estados.LECTURA)){
			imageView.setClickable(false);
			dificultad.setClickable(false);
			tags.setClickable(false);
			ingredientes.setClickable(false);
			pasos.setClickable(false);
			tips.setClickable(false);
			comensales.setClickable(false);
		}*/


	}
}
