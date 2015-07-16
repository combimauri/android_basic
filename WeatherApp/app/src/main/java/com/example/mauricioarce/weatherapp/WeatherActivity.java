package com.example.mauricioarce.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio Arce on 08/07/2015.
 */
public class WeatherActivity extends Activity {

    private TextView weatherActual;
    private TextView weatherMax;
    private TextView weatherMin;
    private String location;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.weather_layout);

        weatherActual = (TextView) findViewById(R.id.text_actual);
        weatherMax = (TextView) findViewById(R.id.text_max);
        weatherMin = (TextView) findViewById(R.id.text_min);

        Bundle intentBundle = getIntent().getExtras();
        location = intentBundle.getString("location");
        new HttpGetWeather(location).execute();

    }

    private class HttpGetWeather extends AsyncTask<Void, Void, List<String>> {

        private final String location;
        private final String url;
        private AndroidHttpClient client;

        public HttpGetWeather(String location) {
            this.location = location;
            url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s", this.location);
            client = AndroidHttpClient.newInstance("");
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            HttpGet request = new HttpGet(url);
            JSONResponseHandler responseHandler = new JSONResponseHandler();
            try {
                return client.execute(request, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<String> result) {
            if (client != null) {
                client.close();
            }
            weatherActual.setText(String.format("Actual weather: %s", result.get(0)));
            weatherMax.setText(String.format("Max weather: %s", result.get(1)));
            weatherMin.setText(String.format("Min weather: %s", result.get(2)));
        }
    }

    private class JSONResponseHandler implements ResponseHandler<List<String>> {

        private static final String MAIN_TAG = "main";
        private static final String WEATHER_TAG = "temp";
        private static final String WEATHERMAX_TAG = "temp_max";
        private static final String WEATHERMIN_TAG = "temp_min";

        @Override
        public List<String> handleResponse(HttpResponse httpResponse) throws IOException {
            List<String> result = new ArrayList<>();
            String JSONResponse = new BasicResponseHandler().handleResponse(httpResponse);
            try {
                JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();
                JSONObject weather = responseObject.getJSONObject(MAIN_TAG);
                result.add(weatherParser(weather.getDouble(WEATHER_TAG)));
                result.add(weatherParser(weather.getDouble(WEATHERMAX_TAG)));
                result.add(weatherParser(weather.getDouble(WEATHERMIN_TAG)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        private String weatherParser(Double weather) {
            DecimalFormat formater = new DecimalFormat("##.##");
            String temp = formater.format(weather - 273.15);
            return String.format("%s C", temp);
        }
    }

    public void finishView(View view) {
        finish();
    }

}
