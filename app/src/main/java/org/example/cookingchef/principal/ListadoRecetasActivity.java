package org.example.cookingchef.principal;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.example.cookingchef.detalle_receta.DetalleRecetaActivity;
import org.example.cookingchef.R;
import org.example.cookingchef.almacenamiento.CargarDatosGson;
import org.example.cookingchef.modelos.Receta;

import java.util.List;

public class ListadoRecetasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

		/*List<String> tags  = new ArrayList<>();
		for (int i = 0 ; i<10 ; i++){
			tags.add("tag" + (i+1));
		}
		List<Ingrediente> ingredientes = new ArrayList<>();
		ingredientes.add(new Ingrediente("Patata",2, "unidades"));
		ingredientes.add(new Ingrediente("Agua",150 ,"ml"));

		List<Paso> pasos = new ArrayList<>();
		pasos.add(new Paso(1,"Lava las patatas"));
		pasos.add(new Paso(2,"Pela las patatas"));
		pasos.add(new Paso(3,"Ponlas en agua"));
		almacen.guardarReceta(new Receta(1,R.drawable.ic_libreta,"Título 1",5, tags, ingredientes, pasos, "Estas son las observaciones", 2));
		almacen.guardarReceta(new Receta(2,R.drawable.ic_libreta,"Título 2",4, tags, ingredientes, pasos, "Estas son las observaciones", 2));
		almacen.guardarReceta(new Receta(3,R.drawable.ic_libreta,"Título 3",3, tags, ingredientes, pasos, "Estas son las observaciones", 2));
		almacen.guardarReceta(new Receta(4,R.drawable.ic_libreta,"Título 4",2, tags, ingredientes, pasos, "Estas son las observaciones", 2));
		almacen.guardarReceta(new Receta(5,R.drawable.ic_libreta,"Título 5",1, tags, ingredientes, pasos, "Estas son las observaciones", 2));
		almacen.guardarReceta(new Receta(6,R.drawable.ic_libreta,"Título 6",0, tags, ingredientes, pasos, "Estas son las observaciones", 2));*/

		listadoRecetas = almacen.listaRecetas();

		// Obtener el Recycler
		recycler = (RecyclerView) findViewById(R.id.reciclador);
		recycler.setHasFixedSize(true);
		// Usar un administrador para LinearLayout
		lManager = new LinearLayoutManager(this);
		recycler.setLayoutManager(lManager);
		// Crear un nuevo adaptador
		adapter = new ListadoRecetasAdapter(listadoRecetas);
		recycler.setAdapter(adapter);

		// Acción que se lanzará al pulsar sobre un elemento de la lista
		recycler.addOnItemTouchListener(
			new RecyclerItemClickListener(ListadoRecetasActivity.this,
				new RecyclerItemClickListener.OnItemClickListener() {
					@Override public void onItemClick(View v, int position) {
						Intent intent = new Intent(ListadoRecetasActivity.this,	DetalleRecetaActivity.class);
						Receta receta = listadoRecetas.get(position);
						intent.putExtra("idReceta", receta.getIdReceta());
						intent.putExtra("receta_card", receta);
						intent.putExtra("estado", "LECTURA");
						//startActivity(intent);
						ActivityOptionsCompat options = ActivityOptionsCompat.
								makeSceneTransitionAnimation(ListadoRecetasActivity.this,
										new Pair<View, String>(v.findViewById(R.id.titulo),
												getString(R.string.transicion_titulo_receta)),
										new Pair<View, String>(v.findViewById(R.id.imagen),
												getString(R.string.transicion_imagen_receta)),
										new Pair<View, String>(v.findViewById(R.id.dificultad),
												getString(R.string.transicion_dificultad_receta)),
										new Pair<View, String>(v.findViewById(R.id.tags),
												getString(R.string.transicion_tags_receta)));
						ActivityCompat.startActivity(ListadoRecetasActivity.this, intent, options
								.toBundle());
					}
				})
		);


		// Toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		// Navigation Drawer
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
				drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

	}
	@Override
	public void onResume(){
		super.onResume();
		listadoRecetas = almacen.listaRecetas();
		adapter = new ListadoRecetasAdapter(listadoRecetas);
		recycler.setAdapter(adapter);
	}

	// Métodos para el navigation drawer
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.nav_crear:
				//Lanzamos la actividad de crear nueva receta_card
				Intent intent = new Intent(ListadoRecetasActivity.this,	DetalleRecetaActivity.class);
				int idReceta = listadoRecetas.size() + 1;
				intent.putExtra("idReceta", idReceta);
				intent.putExtra("estado", "EDICION");
				startActivity(intent);
				break;
			case R.id.nav_importar:
				//Lanzamos la actividad de importar receta_card
				Toast.makeText(ListadoRecetasActivity.this,getResources().getString(R.string.func_pte),Toast.LENGTH_LONG);
				break;
			default:
				//Salir
				finish();
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.clearFocus();
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	// Métodos para los botones del menú superior
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true; /** true -> el menú ya está visible */
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.mbuscar:
				return true;
			default:
				return super.onOptionsItemSelected(item);

		}
	}
}
