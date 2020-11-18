package com.example.courseworkproject;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataDisplay extends AppCompatActivity implements View.OnClickListener {

    public String stationName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Intent laucher = getIntent();
        if (laucher.hasExtra(MainActivity.EXTRA_DISPLAYEDSTATION)){
            TextView tvStationName = findViewById(R.id.tvStationName);
            String displaytext = laucher.getStringExtra(MainActivity.EXTRA_DISPLAYEDSTATION) + " Station Departures";
            tvStationName.setText(displaytext);
        }


        // Sets up the buttons with the onClickListener() function
        Button btnGetStationMain = findViewById(R.id.btnGetStationData);
            //This didn't work the first time, this didn't work the second time. I DON'T KNOW WHY ON EARTH THIS WORKED THE 3RD TIME... Oh Well!
        btnGetStationMain.setOnClickListener(this);



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
    }
}
