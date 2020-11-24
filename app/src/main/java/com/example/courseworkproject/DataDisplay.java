package com.example.courseworkproject;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataDisplay extends AppCompatActivity implements View.OnClickListener {

    public String stationName;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);


        Intent laucher = getIntent();
        if (laucher.hasExtra(MainActivity.EXTRA_DISPLAYEDSTATION)){
            TextView tvStationName = findViewById(R.id.tvStationName);
            stationName = laucher.getStringExtra(MainActivity.EXTRA_DISPLAYEDSTATION);
            tvStationName.setText(stationName + " Station Departures");
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
            //... get the location entered by the user...
            EditText etLocation = findViewById(R.id.etStationNameData);
            stationName = String.valueOf(etLocation.getText());
            TextView tvStationName = findViewById(R.id.tvStationName);
            String displaytext = stationName + " Station Departures";
            tvStationName.setText(displaytext);
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


}
