package com.azup.locdvd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DVD {

    long id;
    String titulo;
    int anyo;
    String[] actores;
    String resumen;

    // Constructores
    public DVD() {

    }

    private DVD(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
        titulo = cursor.getString(cursor.getColumnIndex("titulo"));
        anyo = cursor.getInt(cursor.getColumnIndex("anyo"));
        actores =
                cursor.getString(cursor.getColumnIndex("actores")).split(";");
        resumen = cursor.getString(cursor.getColumnIndex("resumen"));
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String[] getActores() {
        return actores;
    }

    public void setActores(String[] actores) {
        this.actores = actores;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * Método estático que devuelve una lista de todos los DVDs registrados.
     * @param context
     * @return
     */
    public static ArrayList<DVD> getDVDList(Context context) {
        ArrayList<DVD> listDVD = new ArrayList<>();

        LocalSQLiteOpenHelper helper = new
                LocalSQLiteOpenHelper(context);

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "DVD", new String[]{"id",
                        "titulo", "anyo", "actores", "resumen"}, null,
                null,null,null,"titulo", null  );

        while (cursor.moveToNext()) {
            listDVD.add(new DVD(cursor));
        }

        cursor.close();
        db.close();

        return listDVD;
    }

    /**
     * Permite obtener un DVD a partir de su identificador en la base de datos.
     * @param context
     * @return
     */
    public static DVD getDVD(Context context, long id) {
        DVD dvd = null;
        LocalSQLiteOpenHelper helper = new
                LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where ="id = " + String.valueOf(id);
        Cursor cursor = db.query(true, "DVD", new String[]{"id",
                        "titulo", "anyo", "actores", "resumen"}, where,
                null,null,null,"titulo", null  );

        if(cursor.moveToFirst())
            dvd = new DVD(cursor);

        cursor.close();
        db.close();

        return dvd;
    }

    /**
     * Inserción de datos. Escritura en base de datos de un nuevo DVD
     * @param context
     * @return
     */
    public void insert(Context context) {
        ContentValues values = new ContentValues();
        values.put("titulo",this.titulo);
        values.put("anyo",this.anyo);
        if(this.actores !=null) {
            String listActores = new String();
            for(int i = 0; i<this.actores.length; i++) {
                listActores+=this.actores[i];
                if(i<this.actores.length-1)
                    listActores+=";";
            }
            values.put("actores",listActores);
        }
        values.put("resumen",this.resumen);

        LocalSQLiteOpenHelper helper = new
                LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        this.id=db.insert("DVD", null, values);
        db.close();
    }

    /**
     * Actualizar un registro de la base de datos de DVD
     * @param context
     * @return
     */
    public void update(Context context) {
        ContentValues values = new ContentValues();
        values.put("titulo",this.titulo);
        values.put("anyo",this.anyo);
        if(this.actores !=null) {
            String listActores = new String();
            for(int i = 0; i<this.actores.length; i++) {
                listActores+=this.actores[i];
                if(i<this.actores.length-1)
                    listActores+=";";
            }
            values.put("actores",listActores);
        }
        values.put("resumen",this.resumen);
        String whereClause = "id=" + String.valueOf(this.id);
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update("DVD", values,whereClause,null);
        db.close();
    }

    /**
     * Elimina un registro de la base de datos indicando un id
     * @param context
     * @return
     */
    public void delete(Context context) {
        String whereClause = "id= ?" ;
        String[] whereArgs = new String[1];
        whereArgs[0] = String.valueOf(this.id);
        LocalSQLiteOpenHelper helper = new
                LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("DVD", whereClause,whereArgs);
        db.close();
    }
}
