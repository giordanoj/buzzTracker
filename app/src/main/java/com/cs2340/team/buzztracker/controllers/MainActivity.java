package com.cs2340.team.buzztracker.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.LocationList;
import com.cs2340.team.buzztracker.model.Model;

import java.util.ArrayList;

/*
   Main screen that displays Login and Registration
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent loginA = new Intent(MainActivity.this, Login.class);
                startActivity(loginA);
                finish();
            }}


        );

        Button registerBtn = (Button) findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
                finish();
            }}


        );

        /**
         *  Get location data from database, create Location objects and put them into LocationList
         */
        Intent getLocations = new Intent(this, GetLocationsIntentService.class);
        startService(getLocations);

        /**
         *  Register all the ResponseReceivers to receive async results
         */
        GetLocationsResponseReceiver receiver1;
        IntentFilter filter1 = new IntentFilter(GetLocationsResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new GetLocationsResponseReceiver();
        registerReceiver(receiver1, filter1);

    }

    public class GetLocationsResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Get location data from database";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");

            if (result.startsWith("no locations found")) {
                Toast.makeText(getBaseContext(), "No location data imported.",
                        Toast.LENGTH_LONG).show();
            } else {

                String[] locations = result.split("~");

                for (String location : locations) {
                    String[] locationData = location.split("_");
                    Location currentLocation = new Location(Integer.parseInt(locationData[0]), locationData[1],
                            locationData[2], locationData[3], locationData[4], locationData[5], locationData[6],
                            locationData[7], locationData[8], locationData[9], locationData[10]);
                    Model model = Model.getInstance();
                    model.addLocation(currentLocation);
                }
            }
        }

    }

}
