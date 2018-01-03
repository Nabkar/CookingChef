package org.example.cookingchef.detalle_receta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import org.example.cookingchef.R;
import org.example.cookingchef.almacenamiento.CargarDatosGson;
import org.example.cookingchef.modelos.Ingrediente;
import org.example.cookingchef.modelos.Paso;
import org.example.cookingchef.modelos.Receta;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetalleRecetaActivity extends AppCompatActivity /*implements View.OnFocusChangeListener*/{

	private int idReceta;
	private String estado;
	private String miEstado;
	private Receta receta;

	private static final int SELECT_FILE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_receta);

		if(getIntent().getExtras().containsKey("receta_card")) {
			idReceta =  getIntent().getExtras().getInt("idReceta");
			receta = (Receta) getIntent().getExtras().getSerializable("receta_card");
		} else {
			idReceta =  getIntent().getExtras().getInt("idReceta");
			receta = new Receta(idReceta, R.drawable.ic_libreta);
		}
		estado = (String) getIntent().getExtras().getString("estado");

		prepararLayout(estado);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(miEstado.equals("LECTURA")){
					prepararLayout("EDITAR");
				} else {
					guardarDatos();
					prepararLayout("LECTURA");
				}
			}
		});
		/*ImageView imageView = findViewById(R.id.imagen);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				abrirGaleria(v);
			}
		});*/

	}

	public void guardarDatos() {
		//Obtenemos todos los datos y los preparamos
		EditText etTitulo = findViewById(R.id.etTitulo);
		String titulo = etTitulo.getText().toString();
		ImageView imageView = findViewById(R.id.imagen);
		//ACTUALIZAR IMAGEN!!!!!!
		RatingBar rbDificultad = findViewById(R.id.dificultad);
		int dificultad = (int) rbDificultad.getRating();
		EditText etComensales = findViewById(R.id.etComensales);
		int comensales = Integer.parseInt(etComensales.getText().toString());
		EditText etIngredientes = findViewById(R.id.etIngredientesLista);
		List<String> ingredientes = new ArrayList<>();
		String[] ingre = etIngredientes.getText().toString().split("\n");
		for (String s : ingre){
			/*String[] datos = s.split(" ");
			Ingrediente i;
			if(datos[0].equals("")) {
				i = new Ingrediente(datos[4], Float.parseFloat(datos[1]), datos[2]);
			} else {
				i = new Ingrediente(datos[3], Float.parseFloat(datos[0]), datos[1]);
			}*/
			ingredientes.add(s);
		}
		EditText etPasos = findViewById(R.id.etPasos);
		List<String> pasos = new ArrayList<>();
		String[] pas = etPasos.getText().toString().split("\n");
		for (String s : pas){
			/*String[] datos = s.split(" - ");
			Paso p = new Paso(Integer.parseInt(datos[0].replace(" ","")), datos[1]);*/
			pasos.add(s);
		}
		EditText etTips = findViewById(R.id.etTips);
		String tips = etTips.getText().toString();

		EditText etTags = findViewById(R.id.etTags);
		String[] ta = etTags.getText().toString().split(" ");
		List<String> tags = new ArrayList<>();
		for(String s : ta) {
			tags.add(s);
		}

		Toolbar toolbar = findViewById(R.id.detail_toolbar);
		toolbar.setTitle(titulo);

		// Actualizamos la info en la receta
		receta.setTitulo(titulo);
		receta.setDificultad(dificultad);
		receta.setComensales(comensales);
		receta.setIngredientes(ingredientes);
		receta.setPasos(pasos);
		receta.setObservaciones(tips);
		receta.setTags(tags);

		//Almacenamos la receta actualizada en fichero
		CargarDatosGson almacen = new CargarDatosGson(this);
		almacen.guardarReceta(receta);

	}

	public void prepararLayout(String estado){
		// Cargamos todos los datos en la vista
		Toolbar toolbar = findViewById(R.id.detail_toolbar);
		toolbar.setTitleTextColor(getResources().getColor(R.color.colorTextoBlanco));
		toolbar.setTitle("");
		toolbar.setTitle(receta.getTitulo());

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

		EditText titulo = findViewById(R.id.etTitulo);
		titulo.setText(receta.getTitulo());
		ImageView imageView = findViewById(R.id.imagen);
		imageView.setImageResource(receta.getImagen());
		RatingBar dificultad = findViewById(R.id.dificultad);
		dificultad.setRating(receta.getDificultad());
		EditText comensales = findViewById(R.id.etComensales);

		EditText tags = findViewById(R.id.etTags);
		tags.setText("");
		for (String s : receta.getTags())
			tags.setText(tags.getText() + s + " ");
		EditText ingredientes = findViewById(R.id.etIngredientesLista);
		ingredientes.setText("");
		for (String i : receta.getIngredientes()) {
			String texto = ingredientes.getText().toString() + i + "\n";
			//ingredientes.getText().toString() + " " + i.getCantidad() + " " + i.getUnidades() + " " + getResources().getString(R.string.de) + " " + i.getProducto().toLowerCase() + "\n";
			ingredientes.setText(texto);
		}
		EditText pasos = findViewById(R.id.etPasos);
		pasos.setText("");
		for (String p : receta.getPasos()) {
			String texto = pasos.getText().toString() + p + "\n";
			//pasos.getText().toString() + " " + p.getnPaso() + " - " + p.getPaso() + "\n";
			pasos.setText(texto);
		}
		EditText tips = findViewById(R.id.etTips);
		tips.setText("");
		tips.setText(receta.getObservaciones());

		//Si el estado es de LECTURA desactivamos todos los editText
		if (estado.equals("LECTURA")){
			titulo.setVisibility(View.GONE);
			imageView.setEnabled(false);
			dificultad.setEnabled(false);
			comensales.setEnabled(false);
			//comensales.setFocusable(false);
			comensales.setText(getResources().getString(R.string.comensales) + ": " + receta.getComensales());
			tags.setEnabled(false);
			//tags.setFocusable(false);
			ingredientes.setEnabled(false);
			//ingredientes.setFocusable(false);
			pasos.setEnabled(false);
			//pasos.setFocusable(false);
			tips.setEnabled(false);
			//tips.setFocusable(false);
			fab.setImageResource(R.drawable.ic_editar);
			miEstado = "LECTURA";
		} else {
			titulo.setVisibility(View.VISIBLE);
			comensales.setText(receta.getComensales());
			imageView.setEnabled(true);
			dificultad.setEnabled(true);
			comensales.setEnabled(true);
			//comensales.setFocusable(true);
			tags.setEnabled(true);
			//tags.setFocusable(true);
			ingredientes.setEnabled(true);
			//ingredientes.setFocusable(true);
			pasos.setEnabled(true);
			//pasos.setFocusable(true);
			tips.setEnabled(true);
			//tips.setFocusable(true);
			fab.setImageResource(R.drawable.ic_guardar);
			miEstado = "EDITAR";
		}
	}

	// Métodos para cargar una imagen desde la galería
	public void abrirGaleria(View v){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.selecciona_imagen)),SELECT_FILE);
	}

	protected void onActivityResult(int requestCode, int resultCode,
									Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
		Uri selectedImageUri = null;
		Uri selectedImage;

		String filePath = null;
		switch (requestCode) {
			case SELECT_FILE:
				if (resultCode == Activity.RESULT_OK) {
					selectedImage = imageReturnedIntent.getData();
					String selectedPath=selectedImage.getPath();
					if (requestCode == SELECT_FILE) {

						if (selectedPath != null) {
							InputStream imageStream = null;
							try {
								imageStream = getContentResolver().openInputStream(
										selectedImage);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}

							// Transformamos la URI de la imagen a inputStream y este a un Bitmap
							Bitmap bmp = BitmapFactory.decodeStream(imageStream);

							// Ponemos nuestro bitmap en un ImageView que tengamos en la vista
							ImageView mImg = (ImageView) findViewById(R.id.imagen);
							mImg.setImageBitmap(bmp);
						}
					}
				}
				break;
		}
	}

	/*@Override
	public void onFocusChange(View view, boolean hasFocus){
		switch (view.getId()){
			case R.id.etComensales:
				if(!hasFocus)
					actualizaCantidades();
				break;
		}
	}

	public void actualizaCantidades(){
		List<Ingrediente> ingredientes = receta.getIngredientes();
		EditText comensales = findViewById(R.id.etComensales);
		int numero = Integer.parseInt(comensales.getText().toString());
		for(Ingrediente ingrediente : ingredientes){
			ingrediente.setCantidad(ingrediente.getCantidad()*numero);
		}
		receta.setIngredientes(ingredientes);
		TextView tvIngredientes = findViewById(R.id.tvLecturaIngredientesLista);
		tvIngredientes.setText(receta.getIngredientes().toString());
	}*/
}
