package com.example.mauricioarce.catfacts;

import android.app.Activity;
import android.app.ListActivity;
import android.database.MatrixCursor;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class FactsActivity extends Activity {

    private String factsNumber;
    private ListView factsList;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.facts_layout);

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
            String[] facts = new String[result.getCatFacts().size()];
            for (int i = 0; i < result.getCatFacts().size(); i++) {
                facts[i] = result.getCatFacts().get(i);
            }
            factsList = (ListView) findViewById(R.id.list_facts);
            factsList.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.cat_fact, facts));
        }
    }
}
