package com.example.courseworkproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import static android.content.ContentValues.TAG;

public class TrainDisplay {


    public static String getStationInfoFromCloud(Context context, String station){
        String applicationID = "d8e690f5";
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a";
        String url = "http://transportapi.com/v3/uk/places.json?query=" + station + "&type=train_station&app_id=" + applicationID +"&app_key=" + apiKey;

        final String[] suffix = new String[1];

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Success" + response);
                suffix[0] = getSuffix(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error" + error.getLocalizedMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(request);

        return suffix[0];
    }

    public static String getSuffix(String jsonString){
        String suffix;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray suffixObject = jsonObject.getJSONArray("member");
            JSONObject arrayObject = suffixObject.getJSONObject(0);
            suffix = arrayObject.getString("station_code");
            Log.d(TAG, "Success" + suffix);
        } catch (JSONException e) {
            e.printStackTrace();
            suffix = "error";
        }
        return suffix;
    }

    public static void getTrainInfoFromCloud(Context context, String station){

        String suffix = getStationInfoFromCloud(context, station);

    }
}
