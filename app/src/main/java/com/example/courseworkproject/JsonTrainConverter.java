package com.example.courseworkproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
