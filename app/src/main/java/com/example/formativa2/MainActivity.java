package com.example.formativa2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    daoContacto dao;
    Adaptador adapter;
    ArrayList<Contacto> lista;
    Contacto c;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao=new daoContacto( MainActivity.this);
        lista=dao.verTodos();
        adapter=new Adaptador( this,lista,dao);
        ListView list=(ListView)findViewById(R.id.lista);
        Button agregar=(Button)findViewById(R.id.Agregar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Dialogo para ver vista previa de registro fragment_second.xml

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Dialogo de agregar content_main.xml
                final Dialog dialogo= new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.content_main);
                dialogo.show();
                final EditText NombreVisitante=(EditText)dialogo.findViewById(R.id.NombreVisitante);
                final EditText Cedula=(EditText)dialogo.findViewById(R.id.Cedula);
                final EditText Apto=(EditText)dialogo.findViewById(R.id.Apto);
                final EditText TipoVisitante=(EditText)dialogo.findViewById(R.id.TipoVisitante);
                final EditText Efecha=(EditText)dialogo.findViewById(R.id.efecha);
                final EditText Ehora=(EditText)dialogo.findViewById(R.id.ehora);
                Button guardar=(Button)dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Agregar");
                Button cancelar=(Button)dialogo.findViewById(R.id.d_Cancelar);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     try{
                         c=new Contacto(NombreVisitante.getText().toString(),
                                 Integer.parseInt(Cedula.getText().toString()),
                                 Apto.getText().toString(),TipoVisitante.getText().toString(),
                                 Efecha.getText().toString(), Integer.parseInt(Ehora.getText().toString()));
                         dao.insertar(c);
                         lista=dao.verTodos();
                         adapter.notifyDataSetChanged();
                         dialogo.dismiss();
                     }catch (Exception e){
                         Toast.makeText(getApplication(),"ERROR",Toast.LENGTH_SHORT).show();
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

    }

}
