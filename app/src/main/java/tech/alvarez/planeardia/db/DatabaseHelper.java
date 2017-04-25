package tech.alvarez.planeardia.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lugares.db";
    private static final int DATABASE_VERSION = 1;

    public final static String TABLE_NAME = "lugares";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_NOMBRE = "nombre";
    public final static String COLUMN_DIRECCION = "direccion";
    public final static String COLUMN_LATITUD = "latitud";
    public final static String COLUMN_LONGITUD = "longitud";
    public final static String COLUMN_TELEFONO = "telefono";
    public final static String COLUMN_WEBSITE = "website";
    public final static String COLUMN_FECHA = "fecha";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_LUGARES_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT NOT NULL, "
                + COLUMN_DIRECCION + " TEXT, "
                + COLUMN_LATITUD + " TEXT, "
                + COLUMN_LONGITUD + " TEXT, "
                + COLUMN_TELEFONO + " TEXT, "
                + COLUMN_WEBSITE + " TEXT, "
                + COLUMN_FECHA + " INTEGER"
                + ");";

        db.execSQL(SQL_CREATE_LUGARES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
