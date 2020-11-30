package com.example.courseworkproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//The following code is my own unless uniquely specified before hand
//I have mainly used resources available on my university's Campus Moodle in order to obtain the background knowledge for this application.
//I have also utilised https://developer.android.com/training for this work in order to further understand functions

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String displayedStation;  //Defines the displayed station variable as a string
    public static final String EXTRA_DISPLAYEDSTATION = "Station";

    private SharedPreferences sharedPreferences; //Defines the shared Preferences variable as SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets up the buttons with the onClickListener() function
        // Sets up the button getStation listener
        Button btnGetStationMain = findViewById(R.id.btn_StationSearch);
            //Requires onClick() to work properly (Took me far too long to figure that out, oh well!)
        btnGetStationMain.setOnClickListener(this);

        // Sets up the button getFavorite listener
        Button btnGetFavorite = findViewById(R.id.btn_getfavorite);
        btnGetFavorite.setOnClickListener(this);

        // Sets up the button GetPrefs listener
        Button btnGetPrefs = findViewById(R.id.btn_userprefs);
        btnGetPrefs.setOnClickListener(this);

        //Gets the shared preferences stored on the device and assigns it to the sharedPreferences variable
        sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {

        //If the button clicked is the station Search button then...
        if (view.getId() == R.id.btn_StationSearch){
            //... get the location entered by the user...
            EditText etLocation = findViewById(R.id.etGetLocation);
            //... Convert it into string...
            displayedStation = String.valueOf(etLocation.getText());
            //... and launch the displayDataDisplayActivity() function
            displayDataDisplayActivity();
        }

        //If the button clicked is the Search Favorite button then...
        if (view.getId() == R.id.btn_getfavorite){
            //... set the favKey to the string of the saved string called favorites on the device... (Off topic but: I was tempted to do a monty python esque bit, where I tried to say string as much as possible during one line of code comment)
            String favKey = getString(R.string.favorites);
            //... set the displayed station to the string of the shared preferences that is stored in the favorites string
            displayedStation = sharedPreferences.getString(favKey, "Favorite Station");
            //... and launch the displayDataDisplayActivity() function
            displayDataDisplayActivity();
        }

        if (view.getId() == R.id.btn_userprefs) {
            //Launch the UserPrefsDisplayActivity() function
            UserPrefsDisplayActivity();
        }

    }

    private void displayDataDisplayActivity(){
        //get the intent for the Data Display Activity
        Intent intent = new Intent(getApplicationContext(), DataDisplayActivity.class);

        //add the station name
        intent.putExtra(EXTRA_DISPLAYEDSTATION, displayedStation);

        //start the activity
        startActivity(intent);

    }

    private void UserPrefsDisplayActivity(){
        //get the intent for the UserPreferences Activity
        Intent intent = new Intent(getApplicationContext(), UserPreferencesActivity.class);

        //start the activity
        startActivity(intent);

    }

}