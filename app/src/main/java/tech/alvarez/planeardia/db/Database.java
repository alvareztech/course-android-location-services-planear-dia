package tech.alvarez.planeardia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import tech.alvarez.planeardia.model.Lugar;


public class Database {

    public static long guardarUbicacion(Context context, String nombre, String direccion, double latitud, double longitud, String telefono, String website) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
        values.put(DatabaseHelper.COLUMN_DIRECCION, direccion);
        values.put(DatabaseHelper.COLUMN_LATITUD, latitud);
        values.put(DatabaseHelper.COLUMN_LONGITUD, longitud);
        values.put(DatabaseHelper.COLUMN_TELEFONO, telefono);
        values.put(DatabaseHelper.COLUMN_WEBSITE, website);
        values.put(DatabaseHelper.COLUMN_FECHA, System.currentTimeMillis());

        return db.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    public static ArrayList<Lugar> obtenerUbicaciones(Context context) {
        ArrayList<Lugar> ubicaciones = new ArrayList<Lugar>();

        DatabaseHelper dbHelper = new DatabaseHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper._ID,
                DatabaseHelper.COLUMN_NOMBRE,
                DatabaseHelper.COLUMN_DIRECCION,
                DatabaseHelper.COLUMN_LATITUD,
                DatabaseHelper.COLUMN_LONGITUD,
                DatabaseHelper.COLUMN_TELEFONO,
                DatabaseHelper.COLUMN_WEBSITE,
                DatabaseHelper.COLUMN_FECHA,
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {

            int idColumnIndex = cursor.getColumnIndex(DatabaseHelper._ID);
            int nombreColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE);
            int direccionColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DIRECCION);
            int latitudColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LATITUD);
            int longitudColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LONGITUD);
            int telefonoColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TELEFONO);
            int websiteColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_WEBSITE);
            int fechaColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                String nombre = cursor.getString(nombreColumnIndex);
                String direccion = cursor.getString(direccionColumnIndex);
                double latitud = cursor.getDouble(latitudColumnIndex);
                double longitud = cursor.getDouble(longitudColumnIndex);
                String telefono = cursor.getString(telefonoColumnIndex);
                String website = cursor.getString(websiteColumnIndex);
                long fecha = cursor.getLong(fechaColumnIndex);
                ubicaciones.add(new Lugar(nombre, direccion, latitud, longitud, telefono, website, new Date(fecha)));
            }
        } finally {
            cursor.close();
        }
        return ubicaciones;
    }

}
