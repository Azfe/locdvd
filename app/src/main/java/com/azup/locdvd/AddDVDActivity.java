package com.azup.locdvd;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AddDVDActivity extends Activity {

    EditText editTituloPelicula;
    EditText editAnyo;
    EditText editResumen;
    Button btnAddActor;
    Button btnOk;
    LinearLayout addActoresLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        // asignación del archivo de layout
        setContentView(R.layout.activity_adddvd);

        // obtención de las referencias sobre los componentes
        editTituloPelicula = (EditText)findViewById(R.id.addDVD_titulo);
        editAnyo = (EditText)findViewById(R.id.addDVD_anyo);
        editResumen = (EditText)findViewById(R.id.addDVD_resumen);
        btnAddActor = (Button)findViewById(R.id.addDVD_addActor);
        btnOk = (Button)findViewById(R.id.addDVD_ok);

        addActoresLayout = (LinearLayout)findViewById(R.id.addDVD_addActorLayout);

        btnAddActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActor(null);
            }
        });

        // ¿Es una recreación tras una rotación de la pantalla ?
        if(savedInstanceState!=null) {
            String [] actores
                    =savedInstanceState.getStringArray("actores");
            for(String s : actores) {
                addActor(s);
            }
        }

        else {
            // No se ha introducido ningún autor, se deja el componente editText vacío
            addActor(null);
        }
    }

    // Guarda los datos de actores para la rotación de pantalla
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        String[] actores = new String[addActoresLayout.getChildCount()];
        for(int i = 0; i< addActoresLayout.getChildCount(); i++) {
            View child = addActoresLayout.getChildAt(i);
            if(child instanceof EditText)
                actores[i] = ((EditText)child).getText().toString();
        }
        savedInstanceState.putStringArray("actores",actores);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void addActor(String content) {
        EditText editNewActor = new EditText(this);

        // Se define el tipo de input al ser dinámicos
        // Las primeras letras del nombre y los apellidos van en mayúsculas
        editNewActor.
                setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        if(content!=null)
            editNewActor.setText(content);

        addActoresLayout.addView(editNewActor);
    }
}
