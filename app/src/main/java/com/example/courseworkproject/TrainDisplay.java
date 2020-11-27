package com.example.courseworkproject;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.List;

import static android.content.ContentValues.TAG;

public class TrainDisplay {

    public String destination;
    public String departureTime;
    public String platformNumber;
    public String operatorName;



    public static void getStationInfoFromCloud(final Context context, String station, final ListView listView){
        String applicationID = "d8e690f5";
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a";
        String url = "http://transportapi.com/v3/uk/places.json?query=" + station + "&type=train_station&app_id=" + applicationID +"&app_key=" + apiKey;



        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Success" + response);
                JsonTrainConverter converter = new JsonTrainConverter();
                String suffix = converter.getSuffix(response);
                getTrainInfoFromCloud(context, suffix, listView);
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

    public static void getTrainInfoFromCloud(final Context context, String station, final ListView listView){
        //api config to make it easier to change api key or if the app is looking for passenger or freight
        String applicationID = "d8e690f5";
        String apiKey = "a64dcf1d1dd8d45481bd33c43bafe55a";
        String darwin = "false"; //darwin = to receive additional data from other data feeds to increase accuracy however this appears to slow down search results
        String status = "passenger";

        String url = "https://transportapi.com/v3/uk/train/station/" + station + "/live.json?app_id=" + applicationID + "&app_key=" + apiKey + "&darwin=" + darwin + "&train_status=" + status;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "success" + response);
                List<String> trains = JsonTrainConverter.convertJsonStringToTrains(response);

                //Why i used a ListView instead of a Recycler View
                //Due to the limited purposes of the list it was more efficient in development time to utilise the listView instead of the recyclerView however, in future development a recylcerView will...
                //... remain on the roadmap as it allows images to be displayed to show the train operator logos which may be more recognisable than just seeing the name of the TOC.
                ArrayAdapter <String> arrayAdapter  = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, trains);

                listView.setAdapter(arrayAdapter);
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


    public void setDestination(String destination){
        this.destination = destination;
    }
    public void setDepartureTime(String departureTime){
        this.destination = departureTime;
    }
    public void setPlatformNumber(String platformNumber){
        this.destination = platformNumber;
    }
    public void setOperatorName(String operatorName){
        this.destination = operatorName;
    }


    public String getDestination(){
        return destination;
    }
    }
