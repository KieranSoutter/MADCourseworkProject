package com.example.courseworkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//The following code is my own unless uniquely specified before hand
//I have mainly used resources available on my university's Campus Moodle in order to obtain the background knowledge for this application.
//I have also utilised https://developer.android.com/training for this work in order to further understand functions

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String displayedStation;
    public static final String EXTRA_DISPLAYEDSTATION = "Station";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets up the buttons with the onClickListener() function
        // Sets up the button getStation listener
        Button btnGetStationMain = findViewById(R.id.btn_StationSearch);
            //This didn't work the first time, this didn't work the second time. I DON'T KNOW WHY ON EARTH THIS WORKED THE 3RD TIME... Oh Well!
            //Ok I kinda lied for dramatic purposes, the reason it didn't work was because I forgot to add the onClick() function
        btnGetStationMain.setOnClickListener(this);

        // Sets up the button getFavorite listener
        Button btnGetFavorite = findViewById(R.id.btn_getfavorite);
        btnGetFavorite.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {

        //If the button clicked is the station Search button then...
        if (view.getId() == R.id.btn_StationSearch){
            //... get the location entered by the user...
            EditText etLocation = findViewById(R.id.etGetLocation);
            displayedStation = String.valueOf(etLocation.getText());
            displayDataDisplayActivity();
        }


        if (view.getId() == R.id.btn_getfavorite){
            String favKey = getString(R.string.favorites);
            displayedStation = sharedPreferences.getString(favKey, "Favorite Station");
            displayDataDisplayActivity();
        }


    }

    private void displayDataDisplayActivity(){
        Intent intent = new Intent(getApplicationContext(), DataDisplay.class);

        //add the station name
        intent.putExtra(EXTRA_DISPLAYEDSTATION, displayedStation);

        //start the activity
        startActivity(intent);

    }

}