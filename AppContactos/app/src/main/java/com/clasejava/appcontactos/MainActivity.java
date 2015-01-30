package com.clasejava.appcontactos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public DbManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private TextView tv;
    private ImageButton bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // DbClass helper = new DbClass(this);
       // SQLiteDatabase db = helper.getWritableDatabase();

        manager = new DbManager(this);
       // manager.insertar("Marcos","871235678");
       // manager.insertar("Sergio","871137758");

        cursor = manager.cargarCursoContactos();
        lista = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.editText);
        bt = (ImageButton) findViewById(R.id.imageButton);
        bt.setOnClickListener(this);

        String [] from = new String[]{manager.CN_NAME, manager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1,android.R.id.text2};

        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregar) {
            Intent intent = new Intent(MainActivity.this,AgregarC.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.eliminar) {
            Intent intent = new Intent(MainActivity.this,EliminarC.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imageButton){
            new buscarTacks().execute();
        }

    }

    private class buscarTacks extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Buscado...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            if (tv.getText().toString().equals("")){
                cursor = manager.cargarCursoContactos();
            }else {
                cursor = manager.buscarContactos(tv.getText().toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.changeCursor(cursor);
            Toast.makeText(getApplicationContext(),"Finalizada...",Toast.LENGTH_SHORT).show();
        }
    }
}
