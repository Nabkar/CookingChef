package org.example.cookingchef;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private RecyclerView recycler;
	private RecyclerView.Adapter adapter;
	private RecyclerView.LayoutManager lManager;

	public static CargarDatosGson almacen;
	private List<Receta> listadoRecetas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Leemos de origen de datos los elementos
		almacen = new CargarDatosGson(this);
		listadoRecetas = almacen.listaRecetas();
		List<String> tags  = new ArrayList<>();
		for (int i = 0 ; i<10 ; i++){
			tags.add("tag" + (i+1));
		}
		listadoRecetas.add(new Receta(R.drawable.ic_launcher,"Título 1",5, tags));
		listadoRecetas.add(new Receta(R.drawable.ic_launcher,"Título 2",4, tags));
		listadoRecetas.add(new Receta(R.drawable.ic_launcher,"Título 3",3, tags));
		listadoRecetas.add(new Receta(R.drawable.ic_launcher,"Título 4",2, tags));
		listadoRecetas.add(new Receta(R.drawable.ic_launcher,"Título 5",1, tags));
		listadoRecetas.add(new Receta(R.drawable.ic_launcher,"Título 6",0, tags));
		// Obtener el Recycler
		recycler = (RecyclerView) findViewById(R.id.reciclador);
		recycler.setHasFixedSize(true);
		// Usar un administrador para LinearLayout
		lManager = new LinearLayoutManager(this);
		recycler.setLayoutManager(lManager);
		// Crear un nuevo adaptador
		adapter = new RecetasAdapter(listadoRecetas);
		recycler.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true; /** true -> el menú ya está visible */
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			/*case R.id.mbuscar:
				return true;*/
			default:
				return super.onOptionsItemSelected(item);

		}
	}
}
