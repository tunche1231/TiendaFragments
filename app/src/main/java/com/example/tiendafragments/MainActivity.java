package com.example.tiendafragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListaCategoriaFragment.CallbackCategoria {

    DetalleFragment det_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        ListaCategoriaFragment lcf =
                (ListaCategoriaFragment) fm.findFragmentById(R.id.lista_categorias);
        lcf.callbackCategoria = this;
        if(isTablet(this)){
            det_fragment =
                    (DetalleFragment) fm.findFragmentById(R.id.lista_detalle);
        }

  }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public void CategoriaSeleccionada(int codigo) {
        if(isTablet(this)) {
            det_fragment.CargarDatos(codigo);
        }else {
            Toast.makeText(this, "cod + " + codigo, Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, DetalleActivity.class);
            i.putExtra("codigo_categoria", codigo);
            startActivity(i);
        }
    }
}
