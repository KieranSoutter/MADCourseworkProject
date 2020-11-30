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



        private SharedPreferences sharedPreferences;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_preferences);


            sharedPreferences = getSharedPreferences(getString(R.string.favorites), MODE_PRIVATE);
            String favKey = getString(R.string.favorites);
            TextView tvStationName = findViewById(R.id.tv_favorite);
            String displayedStation = sharedPreferences.getString(favKey, "Favorite Station");
            tvStationName.setText(displayedStation);
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

                EditText etLocation = findViewById(R.id.et_favorite);
                String stationName = String.valueOf(etLocation.getText());

                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

                String favKey = getString(R.string.favorites);
                prefsEditor.putString(favKey, stationName);

                prefsEditor.apply();

                TextView tvStationName = findViewById(R.id.tv_favorite);
                String displayedStation = sharedPreferences.getString(favKey, "Favorite Station");
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
