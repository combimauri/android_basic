package com.example.mauricioarce.catfacts;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class JSONRequest implements ResponseHandler<Facts> {

    private static final String FACT_TAG = "facts";

    @Override
    public Facts handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        Facts result = new Facts();
        String JSONRequest = new BasicResponseHandler().handleResponse(httpResponse);
        try {
            JSONObject requestObject = (JSONObject) new JSONTokener(JSONRequest).nextValue();
            JSONArray facts = requestObject.getJSONArray(FACT_TAG);
            for (int index = 0; index < facts.length(); index++) {
                result.addFacts(facts.getString(index));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
