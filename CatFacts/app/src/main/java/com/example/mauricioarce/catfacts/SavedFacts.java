package com.example.mauricioarce.catfacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class SavedFacts extends Activity {

    private FactsReaderDbHelper helper;
    private ListAdapter listAdapter;
    private ListView savedFacts;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.saved_facts);

        savedFacts = (ListView) findViewById(R.id.list_saved_facts);
        savedFacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SavedFacts.this);
                TextView itemText = (TextView) view.findViewById(R.id.text_fact_saved);
                final String fact = itemText.getText().toString();

                builder.setMessage("Do you want to delete this fact?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper = new FactsReaderDbHelper(SavedFacts.this);
                        SQLiteDatabase sqlDB = helper.getWritableDatabase();
                        String args[] = {fact};
                        sqlDB.delete(FactsContract.TABLE_NAME, FactsContract.Columns.FACT_CAT + " = ?", args);
                        updateUI();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });
        updateUI();
    }

    private void updateUI() {
        helper = new FactsReaderDbHelper(SavedFacts.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(FactsContract.TABLE_NAME,
                new String[]{FactsContract.Columns._ID, FactsContract.Columns.FACT_CAT},
                null, null, null, null, null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.cat_fact_saved,
                cursor,
                new String[]{FactsContract.Columns.FACT_CAT},
                new int[]{R.id.text_fact_saved},
                0);

        savedFacts.setAdapter(listAdapter);
    }

}
