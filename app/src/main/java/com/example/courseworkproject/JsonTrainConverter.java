package com.example.courseworkproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class JsonTrainConverter {

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


    public static List<String> convertJsonStringToTrains(String jsonString){
        List<String> trains = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject jsonTrains = jsonObject.getJSONObject("departures");
            JSONArray jsonArray = jsonTrains.getJSONArray("all");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject trainObject = jsonArray.getJSONObject(i);

                String trains1 = (trainObject.getString("destination_name"));
                String trains2 = (trainObject.getString("aimed_departure_time"));
                String trains3 = (trainObject.getString("operator_name"));
                String trains4 = (trainObject.getString("platform"));

                trains.add("Destination :" + trains1 + trains2 + trains3 + trains4);
                Log.d(TAG, trains.get(0));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trains;
    }
}
