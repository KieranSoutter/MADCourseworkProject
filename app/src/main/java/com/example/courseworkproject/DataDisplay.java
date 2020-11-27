package com.example.courseworkproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataDisplay extends AppCompatActivity implements View.OnClickListener {

    public String stationName;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);





        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button


        Intent laucher = getIntent();
        Context context = getApplicationContext();

        if (laucher.hasExtra(MainActivity.EXTRA_DISPLAYEDSTATION)){
            TextView tvStationName = findViewById(R.id.tvStationName);
            stationName = laucher.getStringExtra(MainActivity.EXTRA_DISPLAYEDSTATION);
            tvStationName.setText(stationName + " Station Departures");

            ListView listView = (ListView) findViewById(R.id.lv_TrainsView);
            TrainDisplay.getStationInfoFromCloud(context, stationName, listView);

        }




        // Sets up the buttons with the onClickListener() function
        // Sets up the button getStation listener
        Button btnGetStationMain = findViewById(R.id.btnGetStationData);
        btnGetStationMain.setOnClickListener(this);

        // Sets up the radio button getStation listener
        Button btnGetFavorite = findViewById(R.id.rbFavoriteStation);
        btnGetFavorite.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        //If the button clicked is the station Search button then...
        if (view.getId() == R.id.btnGetStationData){
            Context context = getApplicationContext();
            //... get the location entered by the user...
            EditText etLocation = findViewById(R.id.etStationNameData);
            stationName = String.valueOf(etLocation.getText());
            TextView tvStationName = findViewById(R.id.tvStationName);
            String displaytext = stationName + " Station Departures";
            tvStationName.setText(displaytext);
            ListView listView = findViewById(R.id.lv_TrainsView);
            TrainDisplay.getStationInfoFromCloud(context, stationName, listView);

        }
        if (view.getId() == R.id.rbFavoriteStation){

            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

            String favKey = getString(R.string.favorites);
            prefsEditor.putString(favKey, stationName);

            prefsEditor.apply();
        }

    }

    protected void onPause(){
        super.onPause();
    }



    //Android back bar Code adapted from https://stackoverflow.com/questions/15686555/display-back-button-on-action-bar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
