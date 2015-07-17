package com.example.mauricioarce.catfacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class FactsActivity extends Activity {

    private String factsNumber;
    private ListView factsList;
    private String[] facts;
    private FactsReaderDbHelper helper;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.facts_layout);

        factsList = (ListView) findViewById(R.id.list_facts);
        factsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FactsActivity.this);
                TextView itemText = (TextView) view.findViewById(R.id.text_item);
                final String fact = itemText.getText().toString();

                builder.setMessage("Do you want to save this fact?");
                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper = new FactsReaderDbHelper(FactsActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.clear();
                        values.put(FactsContract.Columns.FACT_CAT, fact);

                        db.insertWithOnConflict(FactsContract.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });

        Bundle intentBundle = getIntent().getExtras();
        factsNumber = intentBundle.getString("number");

        new HttpGetFacts(factsNumber).execute();
    }

    public class HttpGetFacts extends AsyncTask<Void, Void, Facts> {

        private String factNumber;
        private String url;
        private AndroidHttpClient client;

        public HttpGetFacts(String factNumber) {
            this.factNumber = factNumber;
            url = String.format("http://catfacts-api.appspot.com/api/facts?number=%s", this.factNumber);
            client = AndroidHttpClient.newInstance("");
        }

        @Override
        protected Facts doInBackground(Void... params) {
            HttpGet httpRequest = new HttpGet(url);
            JSONRequest jsonRequest = new JSONRequest();
            try {
                return client.execute(httpRequest, jsonRequest);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Facts result) {
            if (client != null) {
                client.close();
            }
            facts = new String[result.getCatFacts().size()];
            for (int i = 0; i < result.getCatFacts().size(); i++) {
                facts[i] = result.getCatFacts().get(i);
            }

            factsList.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.cat_fact, facts));
        }
    }
}
