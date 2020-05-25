package tech.alvarez.planeardia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;

import tech.alvarez.planeardia.model.Lugar;
import tech.alvarez.planeardia.adapters.UbicacionesAdapter;
import tech.alvarez.planeardia.db.Database;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();
    }

    private void cargarDatos() {
        // Carga los datos desde la base de datos y los coloca en la lista.
        ArrayList<Lugar> datos = Database.obtenerUbicaciones(this);
        UbicacionesAdapter ubicacionesAdapter = new UbicacionesAdapter(this, datos);
        ListView lugareslistView = (ListView) findViewById(R.id.listView);
        lugareslistView.setAdapter(ubicacionesAdapter);
    }

    public void adicionarLugar(View view) {
        // Cuando se presiona el botón flotante adicionar

        // TODO: PASO 1


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888) {
            if (resultCode == RESULT_OK) {

                // TODO: PASO 2


                cargarDatos();
            }
        }
    }

    public void verEnMapa(View view) {
        // Cuando se presionar el botón de ver mapa

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
