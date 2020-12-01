package com.example.courseworkproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import static android.app.PendingIntent.getActivity;

public class DataDisplayActivity extends AppCompatActivity implements View.OnClickListener {

    //Defines the stationName variable as a string 
    public String stationName = "Error";

    //Declares sharedPreferences as a private Shared Preferences variable
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the content view to the activity_data layout
        setContentView(R.layout.activity_data);

        //gets the shared preferences from the favorites string
        sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);

        //See line 117 for more details on lines 29 and 40
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        //gets the intent
        Intent laucher = getIntent();
        //gets the application context
        Context context = getApplicationContext();


        //if the intent has passed a variable called EXTRA_DISPLAYEDSTATION then...
        //Set the text view to the station name + station departures
        //Get the list view
        //and call the getstationfromcloud function from TrainDisplay while passing Context, the station name, and the list view
        if (laucher.hasExtra(MainActivity.EXTRA_DISPLAYEDSTATION)) {
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

        //Sets up the go to station button listener
        Button btnGoTo = findViewById(R.id.btn_goTO);
        btnGoTo.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //If the button clicked is the station Search button then...
        //Set the text view to the station name + station departures
        //Get the list view
        //and call the getstationfromcloud function from TrainDisplay while passing Context, the station name, and the list view
        if (view.getId() == R.id.btnGetStationData) {
            Context context = getApplicationContext();

            EditText etLocation = findViewById(R.id.etStationNameData);
            stationName = String.valueOf(etLocation.getText());
            TextView tvStationName = findViewById(R.id.tvStationName);
            String displaytext = stationName + " Station Departures";
            tvStationName.setText(displaytext);
            ListView listView = findViewById(R.id.lv_TrainsView);
            TrainDisplay.getStationInfoFromCloud(context, stationName, listView);

        }
        //if the radio button is clicked to set a
        //Set the Shared preferences editor
        //Set the fav key to the string we store the favorites to (in this case R.string.favorites)
        //put the string into the fav key using the prefs editor
        //apply the code using the prefs editor
        if (view.getId() == R.id.rbFavoriteStation) {


            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

            String favKey = getString(R.string.favorites);
            prefsEditor.putString(favKey, stationName);

            prefsEditor.apply();
        }

        if (view.getId() == R.id.btn_goTO) {

            // Search for the station on maps
            // It was going to open navigation however i could not get this to effectively test as the location was set to san fransisco and would therefore pick the closest place called aberdeen
            // Testing this without location services on shows the intended result and in the end actually gives the user more options

            //this gives the uri a search term to parse
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + stationName + " Station");
            //sets the intent to the actionview and applys the URI from before
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            //set the package to the android maps app
            mapIntent.setPackage("com.google.android.apps.maps");
            //start the activity
            startActivity(mapIntent);

        }

    }

    protected void onPause() {
        super.onPause();
    }


    //Android back bar Code adapted from https://stackoverflow.com/questions/15686555/display-back-button-on-action-bar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
