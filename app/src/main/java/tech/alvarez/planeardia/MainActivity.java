package tech.alvarez.planeardia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), 888);
        } catch (Exception e) {
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 888) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(this, data);

                String nombre = place.getName().toString();
                String direccion = place.getAddress().toString();
                double latitud = place.getLatLng().latitude;
                double longitud = place.getLatLng().longitude;
                String telefono = place.getPhoneNumber() != null ? place.getPhoneNumber().toString() : "No tiene teléfono";
                String website = place.getWebsiteUri() != null ? place.getWebsiteUri().getAuthority() : "No tiene website";

                Database.guardarUbicacion(this, nombre, direccion, latitud, longitud, telefono, website);

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
