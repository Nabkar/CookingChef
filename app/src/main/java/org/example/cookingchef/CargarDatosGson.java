package org.example.cookingchef;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julio on 27/12/2017.
 */

public class CargarDatosGson {
    private String fichero;
    private Gson gson = new Gson();
    private Type type = new TypeToken<List<Receta>>(){}.getType();

    private int SOLICITUD_PERMISO_WRITE_EXTERNAL_STORAGE = 1;

    public void guardarReceta(Receta receta){
        String estadoSD = Environment.getExternalStorageState();
        if (!estadoSD.equals(Environment.MEDIA_MOUNTED)) {
            Snackbar.make(vista, "No existe almacenamiento externo", Snackbar.LENGTH_SHORT).show();
            return;
        }
        fichero = leerDeFichero();
        ArrayList<Receta> recetas;
        if(fichero == null){
            recetas = new ArrayList<>();
        } else {
            recetas = gson.fromJson(fichero,type);
        }

        recetas.add(new Receta(receta));
        fichero = gson.toJson(recetas,type);
        guardarEnFichero(fichero);
    }

    public List<Receta> listaRecetas() {
        List<Receta> salida = new ArrayList<>();
        String estadoSD = Environment.getExternalStorageState();
        if (!estadoSD.equals(Environment.MEDIA_MOUNTED) && !estadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            Toast.makeText(context, "No puedo leer en la memoria externa",
                    Toast.LENGTH_LONG).show();
            return salida;
        }
        fichero = leerDeFichero();

        ArrayList<Receta> recetas;
        if(fichero == null){
            recetas = new ArrayList<>();
        } else {
            recetas = gson.fromJson(fichero,type);
        }
        for(Receta receta : recetas){
            salida.add(receta);
        }
        return salida;
    }

    private void guardarEnFichero(String fichero){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            Permisos.solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    "Es necesario activar el permiso de almacenamiento externo de datos para poder guardar las recetas.",
                    SOLICITUD_PERMISO_WRITE_EXTERNAL_STORAGE, this);
        }
    }

    private String leerDeFichero(){

    }
}
