package com.example.mauricioarce.addressbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mauricio Arce on 30/06/2015.
 */
public class AddressReaderDbHelper extends SQLiteOpenHelper {

    public AddressReaderDbHelper(Context context) {
        super(context, AddressContract.DATABASE_NAME, null, AddressContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTable = String.format("CREATE TABLE %s (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                            + "%s TEXT, " + "%s TEXT, " + "%s TEXT)",
                                        AddressContract.TABLE_NAME,
                                        AddressContract.Columns.CONTACT_NAME,
                                        AddressContract.Columns.CONTACT_NUMBER,
                                        AddressContract.Columns.CONTACT_EMAIL);
        Log.d("AddressReaderDbHelper", "Query to form table: " + sqlTable);
        db.execSQL(sqlTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AddressContract.TABLE_NAME);
        onCreate(db);
    }
}
