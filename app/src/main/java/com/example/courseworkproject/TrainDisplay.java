package com.example.courseworkproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TrainDisplay {

    public static void getStationInfoFromCloud(final Context context, String station, final ListView listView) {
        String applicationID = "d8e690f5"; //sets the application ID variable as a string and passes the relevent ApplicationID to it
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a"; //sets the apiKey variable as a string and passes the relevent ApiKey to it
        String url = "http://transportapi.com/v3/uk/places.json?query=" + station + "&type=train_station&app_id=" + applicationID + "&app_key=" + apiKey; //combines multiple variables into a url to parse json from


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() { //if there is a response to the URL
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Success" + response); //Log for debugging
                JsonTrainConverter converter = new JsonTrainConverter(); //Sets the converter variable to the JsonTrainConverter class
                String suffix = converter.getSuffix(response); //sets the suffix variable as a string to what is returned from the getsuffix() function while passing the json response to it
                getTrainInfoFromCloud(context, suffix, listView); //Calls the getTrainInfoFromCloud method and passes context, suffix and list view to it
            }
        }, new ErrorListener() { //if there is an error to the URL
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error" + error.getLocalizedMessage()); //Log for debugging
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext()); //sets up the request queue using application context
        requestQueue.add(request); //adds a request to the queue
    }

    public static void getTrainInfoFromCloud(final Context context, String station, final ListView listView) {
        //api config to make it easier to change api key or if the app is looking for passenger or freight
        String applicationID = "d8e690f5"; //sets the application ID variable as a string and passes the relevent ApplicationID to it
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a";  //sets the apiKey variable as a string and passes the relevent ApiKey to it
        String darwin = "false"; //darwin = to receive additional data from other data feeds to increase accuracy however this appears to slow down search results
        String status = "passenger";  //status = to receive freight or passenger data

        //combines multiple variables into a url to parse json from
        String url = "https://transportapi.com/v3/uk/train/station/" + station + "/live.json?app_id=" + applicationID + "&app_key=" + apiKey + "&darwin=" + darwin + "&train_status=" + status;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { //on response from the URL
                Log.d(TAG, "success" + response); //log for debugging
                //sets the trains variable to a string list and parses what is returned from JsonTrainConverter
                List<String> trains = JsonTrainConverter.convertJsonStringToTrains(response);

                //Why i used a ListView instead of a Recycler View
                //Due to the limited purposes of the list it was more efficient in development time to utilise the listView instead of the recyclerView however, in future development a recylcerView will...
                //... remain on the roadmap as it allows images to be displayed to show the train operator logos which may be more recognisable than just seeing the name of the TOC (Train Operating Company).

                ArrayAdapter<String> arrayAdapter; //sets up the array adapter
                arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, trains); //sets the array adapter with the context, the layout file and the trains variable
                listView.setAdapter(arrayAdapter); //applies the adapter to the list view
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //on error from the URL
                Log.d(TAG, "error" + error.getLocalizedMessage()); //log for debugging
                List<String> errors = new ArrayList<String>(); //creates a new errors list that takes in strings
                errors.add("Station not found, if the error continues please check your spelling and your internet connection"); //appends the list with an error
                ArrayAdapter<String> arrayAdapter; //sets up the array adapter
                arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, errors); //sets the array adapter with the context, the layout file and the errors variable
                listView.setAdapter(arrayAdapter);  //applies the adapter to the list view
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext()); //sets up the request queue using application context
        requestQueue.add(request); //adds a request to the queue

    }

}
