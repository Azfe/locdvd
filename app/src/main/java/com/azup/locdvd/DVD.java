package com.azup.locdvd;

import android.database.Cursor;

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
        id = cursor.getLong(cursor.getColumnIndex("id"));
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
}
