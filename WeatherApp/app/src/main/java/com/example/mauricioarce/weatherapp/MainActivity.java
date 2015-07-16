package com.example.mauricioarce.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Mauricio Arce on 08/07/2015.
 */
public class MainActivity extends Activity {

    EditText editableLocation;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);

        editableLocation = (EditText) findViewById(R.id.text_introduce);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void runWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("location", editableLocation.getText().toString());
        startActivity(intent);
    }

}
