package org.example.cookingchef;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private RecyclerView recycler;
	private RecyclerView.Adapter adapter;
	private RecyclerView.LayoutManager lManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Leemos de origen de datos los elementos
		List items = new ArrayList();
		//items.add(new Receta(R.drawable.trabajo, "Trabajo", 2));
		//items.add(new Receta(R.drawable.casa, "Personal", 3));
		// Obtener el Recycler
		recycler = (RecyclerView) findViewById(R.id.reciclador);
		recycler.setHasFixedSize(true);
		// Usar un administrador para LinearLayout
		lManager = new LinearLayoutManager(this);
		recycler.setLayoutManager(lManager);
		// Crear un nuevo adaptador
		adapter = new RecetasAdapter(items);
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
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if (requestCode == SOLICITUD_PERMISO_WRITE_CALL_LOG) {
			if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				guardarEnGson();
			} else {
				Toast.makeText(this, "Sin el permiso, no puedo realizar la acción", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
