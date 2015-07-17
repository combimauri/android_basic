package com.example.mauricioarce.catfacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class FactsReaderDbHelper extends SQLiteOpenHelper {

    public FactsReaderDbHelper(Context context) {
        super(context, FactsContract.DATABASE_NAME, null, FactsContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTable = String.format("CREATE TABLE %s (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "%s TEXT)",
                FactsContract.TABLE_NAME,
                FactsContract.Columns.FACT_CAT);
        db.execSQL(sqlTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FactsContract.TABLE_NAME);
        onCreate(db);
    }
}
