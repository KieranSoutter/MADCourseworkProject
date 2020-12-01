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

    //Code for getting the stations 3 letter suffix... more info on suffixes/station codes at https://www.nationalrail.co.uk/stations_destinations/48541.aspx
    public static String getSuffix(String jsonString){
        String suffix; //Defines the suffix variable as a string
        try { //TRY this and if that does not work...
            JSONObject jsonObject = new JSONObject(jsonString); //Sets up the jsonObject variable as a JSONObject and assigns it to the jsonstring
            JSONArray suffixObject = jsonObject.getJSONArray("member"); //Sets up the suffixObject as a JSONArray and assigns it to the array that starts with member:[
            JSONObject arrayObject = suffixObject.getJSONObject(0); //Sets up the arrayObject as a JSONObject and assigns it to the first object on the array
            suffix = arrayObject.getString("station_code"); //gets the station code from the arrayObject and assigns it to the suffix object
            Log.d(TAG, "Success" + suffix); //Log for debugging
        } catch (JSONException e) { //Catch the function in an error function
            e.printStackTrace();
            suffix = "error"; //log for debugging
        }
        return suffix; //returns the suffix variable from the function
    }


    public static List<String> convertJsonStringToTrains(String jsonString){
        List<String> trains = new ArrayList<String>(); //sets a new ArrayList to the variable trains
        try { //TRY this and if that does not work...
            JSONObject jsonObject = new JSONObject(jsonString); //Sets up the jsonObject variable as a JSONObject and assigns it to the jsonstring
            JSONObject jsonTrains = jsonObject.getJSONObject("departures"); //Sets up the jsonTrains variable as a JSONObject and assigns it to the departures object in the json
            JSONArray jsonArray = jsonTrains.getJSONArray("all");  //Sets up the jsonArray as a JSONArray and assigns it to the array that starts with all:[
            for (int i = 0; i < jsonArray.length(); i++) { //sets a loop for everything in the array
                JSONObject trainObject = jsonArray.getJSONObject(i); //sets the trainObject variable as a JSONObject and assigns the corresponding json array field to the i counter

                String trains1 = (trainObject.getString("destination_name")); //Sets the trains1 variable as a string and assigns that to the string of the destination_name jsonobject
                String trains2 = (trainObject.getString("aimed_departure_time")); //Sets the trains2 variable as a string and assigns that to the string of the aimed_departure_time jsonobject
                String trains3 = (trainObject.getString("operator_name")); //Sets the trains3 variable as a string and assigns that to the string of the operator_name jsonobject
                String trains4 = (trainObject.getString("platform")); //Sets the trains4 variable as a string and assigns that to the string of the platform jsonobject

                trains.add("Destination: " + trains1 + "\nDeparture Time: " + trains2 + "\n" + trains3 + "     Platform: " + trains4); //combines these strings into a readable format for the user and appends this to the trains lsit
                Log.d(TAG, trains.get(0)); //Log for debugging
            }
        } catch (JSONException e) { //...Catch the function in an error function
            e.printStackTrace();
        }
        return trains; //returns the trains variable from the function
    }
}
