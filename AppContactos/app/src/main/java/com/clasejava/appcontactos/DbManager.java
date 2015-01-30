package com.clasejava.appcontactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Usuario on 29/01/2015.
 */
public class DbManager {

    public static final String TABLE_NAME = "contactos";

    public static final String CN_ID="_id";
    public static final String CN_NAME="nombre";
    public static final String CN_PHONE="telefono";

    public static String CREATE_TABLE = "create table " + TABLE_NAME+ " ("
            +CN_ID +" integer primary key autoincrement,"
            +CN_NAME+" text not null,"
            +CN_PHONE+" text);";

    private DbClass helper;
    private SQLiteDatabase db;

    public DbManager(Context context){

        helper = new DbClass(context);
        db = helper.getWritableDatabase();

        //db.insert();
        //db.execSQL();
        //db.rawQuery();

    }

    public ContentValues generarContentValues(String nombre, String telefono){

        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        valores.put(CN_PHONE,telefono);

        return valores;

    }

    public void insertar (String nombre, String telefono){

        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        valores.put(CN_PHONE,telefono);

        db.insert(TABLE_NAME,null,generarContentValues(nombre, telefono));

    }

    public void eliminar(String nombre){
        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{nombre});
    }

    public void modificarTelefono(String nombre, String nuevoTelefono){
        db.update(TABLE_NAME,generarContentValues(nombre,nuevoTelefono),CN_NAME + "=?", new String[]{nombre});
    }

    public Cursor cargarCursoContactos(){
        String[] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};

        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public  Cursor buscarContactos(String nombre){

        String[] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME,columnas,CN_NAME +" LIKE ?",new String[]{nombre+"%"},null,null,null);
    }


}
