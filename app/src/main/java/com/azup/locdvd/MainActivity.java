package com.azup.locdvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =
                getSharedPreferences("com.azup.locDVD.prefs", Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("embeddedDataInserted", false))
            readEmbeddedData();
    }

    /**
     * Leer datos y registrarlos
     */
    private void readEmbeddedData() {
        // Abrir el archivo data.txt
        InputStreamReader reader = null;
        InputStream file=null;
        BufferedReader bufferedReader=null;
        try {
            file = getAssets().open("data.txt");
            reader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(reader);
            String line= null;
            // Deserializar la línea
            while((line=bufferedReader.readLine())!=null) {
                String [] data = line.split("\\|");
                if(data!=null && data.length==4) {
                    // Instanciar DVD, informar de sus propiedades y registarlo en la base de datos
                    DVD dvd = new DVD();
                    dvd.titulo = data[0];
                    dvd.anyo = Integer.decode(data[1]);
                    dvd.actores = data[2].split(",");
                    dvd.resumen = data[3];
                    dvd.insert(this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(bufferedReader!=null) {
                try {
                    bufferedReader.close();
                    reader.close();
                    SharedPreferences sharedPreferences =
                            getSharedPreferences("com.azup.locDVD.prefs",
                                    Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =
                            sharedPreferences.edit();
                    editor.putBoolean("embeddedDataInserted", true);
                    editor.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}