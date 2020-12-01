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

public class UserPreferencesActivity extends AppCompatActivity implements View.OnClickListener {


        //Defines the shared Preferences variable as SharedPreferences
        private SharedPreferences sharedPreferences;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Sets the content view to the activity_prefrences layout file
            setContentView(R.layout.activity_preferences);

            //gets the shared prefrences stored in the favorites string...
            sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);
            //... sets the favKey to the string of the saved string called favorites on the device...
            String favKey = getString(R.string.favorites);
            //sets the tvstation name variable and assigns it to find the view with the id tv_favorite
            TextView tvStationName = findViewById(R.id.tv_favorite);
            //sets the displayed station variable to get the string from the stored string on the device
            String displayedStation = sharedPreferences.getString(favKey, "Favorite Station");
            //sets the text view to the displayedStation variable
            tvStationName.setText(displayedStation);

            //See line 78 for more details on lines 36 and 37
            assert getSupportActionBar() != null;   //null check
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button



            // Sets up the buttons with the onClickListener() function
            // Sets up the button setNewFavorite listener
            Button btnGetStationMain = findViewById(R.id.btn_setNewFavorite);
            btnGetStationMain.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            //If the button clicked is the station Search button then...
            if (view.getId() == R.id.btn_setNewFavorite) {
                //Sets up the etLocation variable as an EditText and assigns the id of et_favorite
                EditText etLocation = findViewById(R.id.et_favorite);
                //Sets up the stationName variable as a String and assigns the value of the edit text etlocation
                String stationName = String.valueOf(etLocation.getText());

                //Prepares the sharedPrefrences editor to edit
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                //Set the fav key to the string we store the favorites to (in this case R.string.favorites)
                String favKey = getString(R.string.favorites);
                //put the string into the fav key using the prefs editor
                prefsEditor.putString(favKey, stationName);

                //apply the code using the prefs editor
                prefsEditor.apply();

                //Defines the tvStationName variable as a TextView and assigns the id of the tv_favorite string to it
                TextView tvStationName = findViewById(R.id.tv_favorite);
                //Defines the displayedStation variable as a string and assigns the shared prefrences result of the string stored on the device
                String displayedStation = sharedPreferences.getString(favKey, "Favorite Station");
                //Sets the text view to display the displayed station
                tvStationName.setText(displayedStation);
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
