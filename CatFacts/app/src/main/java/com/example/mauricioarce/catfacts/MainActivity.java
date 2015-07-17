package com.example.mauricioarce.catfacts;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class MainActivity extends Activity {

    EditText factsNumber;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_layout);
        factsNumber = (EditText) findViewById(R.id.text_number_facts);
    }

    public void displayCat(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Meow", Toast.LENGTH_SHORT);
        MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.pusheen_meow);
        player.start();
        toast.show();
    }

    public void showSavedFacts(View view) {
        Intent intent = new Intent(this, SavedFacts.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(this, FactsActivity.class);
        intent.putExtra("number", factsNumber.getText().toString());
        startActivity(intent);
    }
}
