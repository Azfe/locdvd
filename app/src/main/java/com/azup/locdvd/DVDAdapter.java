package com.azup.locdvd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DVDAdapter extends ArrayAdapter<DVD> {

    // Constructor
    Context context;
    public DVDAdapter(Context context, List<DVD> objects) {
        super(context, -1, objects);
        this.context = context;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup
            parent) {
        View view=null;
        if(convertView==null) { // evalúa si la vista es reciclada o se debe crear
            // creación de la vista
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_dvd,
                    null);
        } else {
            view = convertView;
        }

        DVD dvd = getItem(pos);
        // la referencia al DVD en curso está almacenada en la vista en curso.
        view.setTag(dvd);

        // Obtener las referencias a todos los componentes Textview integrados en layout listitem_dvd.xml
        TextView titulo
                =(TextView)view.findViewById(R.id.listItemDVD_titulo);
        TextView anyo
                =(TextView)view.findViewById(R.id.listItemDVD_anyo);
        TextView resumen
                =(TextView)view.findViewById(R.id.listItemDVD_resumen);

        titulo.setText(dvd.getTitulo());
        anyo.setText(String.valueOf(dvd.getAnyo()));
        resumen.setText(dvd.getResumen());

        return view;
    }
}
