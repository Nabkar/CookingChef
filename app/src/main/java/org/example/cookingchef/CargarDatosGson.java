package org.example.cookingchef;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

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

    private String PREFERENCIAS = "recetas";
    private Context context;

    public CargarDatosGson(Context context) {
        this.context = context;
    }

    public void guardarReceta(Receta receta){
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

    // Guardamos el fichero en preferencias para que la información no sea dependiente de la ejecución
    private void guardarEnFichero(String fichero){
        SharedPreferences preferencias = context.getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("recetas", fichero);
        editor.apply();
    }

    private String leerDeFichero(){
        String result;
        SharedPreferences preferencias = context.getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
        result = preferencias.getString("recetas" , null);
        return result;
    }
}
