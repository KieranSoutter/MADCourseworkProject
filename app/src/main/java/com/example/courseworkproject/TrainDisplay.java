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

import static android.content.ContentValues.TAG;

public class TrainDisplay {

    public static void getStationInfoFromCloud(final Context context, String station){
        String applicationID = "d8e690f5";
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a";
        String url = "http://transportapi.com/v3/uk/places.json?query=" + station + "&type=train_station&app_id=" + applicationID +"&app_key=" + apiKey;



        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Success" + response);
                JsonTrainConverter converter = new JsonTrainConverter();
                String suffix = converter.getSuffix(response);
                getTrainInfoFromCloud(context, suffix);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error" + error.getLocalizedMessage());
            }
        });
        
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(request);
    }

    public static void getTrainInfoFromCloud(Context context, String station){
        //api config to make it easier to change api key or if the app is looking for passenger or freight
        String applicationID = "d8e690f5";
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a";
        String darwin = "false"; //darwin = to recive additional data from other data feeds to increase accuracy however this appears to slow down search results
        String status = "passenger";

        String url = "https://transportapi.com/v3/uk/train/station/" + station + "/live.json?app_id=" + applicationID + "&app_key=" + apiKey + "&darwin=" + darwin + "&train_status=" + status;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "success" + response);
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "error" + error.getLocalizedMessage());
                }
            });


        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(request);

    }



    }
