package com.clasejava.appcontactos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 29/01/2015.
 */
public class DbClass extends SQLiteOpenHelper {

    private static final String DB_NAME = "contactos.sqlite";
    private static int DB_SCHEME_VERSION=1;

    public DbClass(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbManager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
