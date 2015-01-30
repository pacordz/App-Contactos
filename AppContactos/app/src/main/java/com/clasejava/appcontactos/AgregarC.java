package com.clasejava.appcontactos;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AgregarC extends ActionBarActivity {

    public  DbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_c);

        manager = new DbManager(this);

        findViewById(R.id.btnAgregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText contacto = (EditText) findViewById(R.id.etContacto);
                EditText tel=(EditText) findViewById(R.id.etTel);

                manager.insertar(contacto.getText().toString(),tel.getText().toString());

                Toast.makeText(getApplicationContext(), "Contacto Agregado...", Toast.LENGTH_SHORT).show();
                contacto.setText(" ");


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_c, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.contactos) {
            Intent intent = new Intent(AgregarC.this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.elim) {
            Intent intent = new Intent(AgregarC.this,EliminarC.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
