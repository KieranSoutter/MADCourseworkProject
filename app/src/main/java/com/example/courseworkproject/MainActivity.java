package com.example.courseworkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String displayedStation;

    public static final String EXTRA_DISPLAYEDSTATION = "Station";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets up the buttons with the onClickListener() function
        Button btnGetStationMain = findViewById(R.id.btn_StationSearch);
            //This didn't work the first time, this didn't work the second time. I DON'T KNOW WHY ON EARTH THIS WORKED THE 3RD TIME... Oh Well!
        btnGetStationMain.setOnClickListener(this);


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


    }

    private void displayDataDisplayActivity(){
        Intent intent = new Intent(getApplicationContext(), DataDisplay.class);

        //add the station name
        intent.putExtra(EXTRA_DISPLAYEDSTATION, displayedStation);

        //start the activity
        startActivity(intent);

    }

}