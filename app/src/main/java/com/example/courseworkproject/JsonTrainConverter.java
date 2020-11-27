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


    public static List<TrainDisplay> convertJsonStringToTrains(String jsonString){
        List<TrainDisplay> trains = new ArrayList<TrainDisplay>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject trainsObject = jsonObject.getJSONObject("departures");
            Iterator<String> trainKeysIter = trainsObject.keys();
            while (trainKeysIter.hasNext()){
                String trainKey = trainKeysIter.next();
                JSONObject trainObject = trainsObject.getJSONObject(trainKey);
                TrainDisplay train = new TrainDisplay();
                train.setDestination(trainObject.getString("destination_name"));
                train.setDepartureTime(trainObject.getString("aimed_departure_time"));
                train.setOperatorName(trainObject.getString("operator_name"));
                train.setPlatformNumber(trainObject.getString("platform"));
                trains.add(train);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trains;
    }
}
