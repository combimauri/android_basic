package com.example.mauricioarce.jokerview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Mauricio Arce on 14/07/2015.
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new JokerView(this));
    }

}
