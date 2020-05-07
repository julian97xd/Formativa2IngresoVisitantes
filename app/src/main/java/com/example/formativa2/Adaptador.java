package com.example.formativa2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Contacto> lista ;
    daoContacto dao;
    Contacto c;
    Activity a;
    int id;


    public Adaptador(Activity a, ArrayList<Contacto> lista, daoContacto dao) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Contacto getItem(int i) {
       c=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c=lista.get(i);
        return c.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v= view;
        if(v==null){
            LayoutInflater li=(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=li.inflate(R.layout.fragment_first, null);
        }
        c=lista.get(posicion);
        final TextView NombreVisitante=(TextView)v.findViewById(R.id.t_NombreVisitante);
        final TextView Cedula =(TextView)v.findViewById(R.id.t_Cedula);
        final TextView Apto=(TextView)v.findViewById(R.id.t_Apto);
        final TextView TipoVisitante=(TextView)v.findViewById(R.id.t_TipoVisitante);
        final TextView Efecha=(TextView)v.findViewById(R.id.efecha);
        final  TextView Ehora=(TextView)v.findViewById(R.id.ehora);
        Button editar=(Button)v.findViewById(R.id.Editar);
        Button eliminar=(Button)v.findViewById(R.id.Eliminar);
        NombreVisitante.setText(c.getNombreVisitante());
        Cedula.setText(""+c.getCedula());
        Apto.setText(c.getApto());
        TipoVisitante.setText(c.getTipoVisitante());
        Efecha.setText(c.getEfecha());
        Ehora.setText(""+c.getEhora());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialogo de editar content_main.xml
                int pos=Integer.parseInt(view.getTag().toString());
                final Dialog dialogo= new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.content_main);
                dialogo.show();
                final EditText NombreVisitante=(EditText)dialogo.findViewById(R.id.NombreVisitante);
                final EditText Cedula=(EditText)dialogo.findViewById(R.id.Cedula);
                final EditText Apto=(EditText)dialogo.findViewById(R.id.Apto);
                final EditText TipoVisitante=(EditText)dialogo.findViewById(R.id.TipoVisitante);
                final  EditText Efecha=(EditText)dialogo.findViewById(R.id.efecha);
                final  EditText Ehora=(EditText)dialogo.findViewById(R.id.ehora);
                Button guardar=(Button)dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Agregar");
                Button cancelar=(Button)dialogo.findViewById(R.id.d_Cancelar);
                c=lista.get(pos);
                setId(c.getId());
                NombreVisitante.setText(c.getNombreVisitante());
                Cedula.setText(""+c.getCedula());
                Apto.setText(c.getApto());
                TipoVisitante.setText(c.getTipoVisitante());
                Efecha.setText(c.getEfecha());
                Ehora.setText(""+c.getEhora());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            c=new Contacto(getId(),NombreVisitante.getText().toString(),
                                    Integer.parseInt(Cedula.getText().toString()), Apto.getText().toString(),TipoVisitante.getText().toString(),Efecha.getText().toString(), Integer.parseInt(Ehora.getText().toString()));
                            dao.editar(c);
                            lista=dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"ERROR",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();

                    }
                });


            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para confirmar Si/No
                int pos=Integer.parseInt(view.getTag().toString());
                c=lista.get(pos);
                setId(c.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage(("EStas seguro de eliminar el visitante"));
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminar(getId());
                        lista=dao.verTodos();
                        notifyDataSetChanged();

                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                del.show();

            }
        });


        return v;
    }
}
