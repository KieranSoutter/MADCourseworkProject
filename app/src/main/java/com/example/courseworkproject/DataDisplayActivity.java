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

    public String stationName = "Error";

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button


        Intent laucher = getIntent();
        Context context = getApplicationContext();

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

        Button btnGoTo = findViewById(R.id.btn_goTO);
        btnGoTo.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //If the button clicked is the station Search button then...
        if (view.getId() == R.id.btnGetStationData) {
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
        if (view.getId() == R.id.rbFavoriteStation) {


            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

            String favKey = getString(R.string.favorites);
            prefsEditor.putString(favKey, stationName);

            prefsEditor.apply();
        }

        if (view.getId() == R.id.btn_goTO) {

            // Search for the station on maps
            // It was going to open navigation however i could not get this to effectively test as the location was set to san fransisco and would therefore pick the closest place called aberdeen
            // Testing this without location services on shows the intended result
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + stationName + " Station");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
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


    public static void onError(TextView tvStationName) {
        tvStationName.setText("Station not found, if the error continues please check your spelling and your internet connection");
    }

}
