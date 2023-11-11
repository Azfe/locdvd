package com.azup.locdvd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDVDActivity extends Activity {

    TextView txtTituloDVD;
    TextView txtAnyoDVD;
    TextView txtActor1;
    TextView txtActor2;
    TextView txtResumenFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // asignación del archivo de layout
        setContentView(R.layout.activity_viewdvd);

        // obtención de las referencias sobre los componentes
        txtTituloDVD = (TextView)findViewById(R.id.tituloDVD);
        txtAnyoDVD = (TextView)findViewById(R.id.anyoDVD);
        txtActor1 = (TextView)findViewById(R.id.actor1);
        txtActor2 = (TextView)findViewById(R.id.actor2);
        txtResumenFilm = (TextView)findViewById(R.id.resumenFilm);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // asignación de datos a mostrar en componentes TextView
        txtTituloDVD.setText("Las Vacaciones del Pequeño Nicolás");
        txtAnyoDVD.setText(
                String.format(getString(R.string.anyo_de_aparicion), 2014));
        txtActor1.setText("Valérie Lemercier");
        txtActor2.setText("Kad Merad");
        String resume="Es el final del año escolar. El momento tan " +
                "esperado de las vacaciones ha llegado. El pequeño Nicolás, " +
                "sus padres y Memé toman la carretera en dirección " +
                "al mar, y se instalan por un tiempo " +
                "en el Hotel Beau-Rivage. En la playa, " +
                "Nicolás hace rápidamente nuevos amigos";
        txtResumenFilm.setText(resume);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
