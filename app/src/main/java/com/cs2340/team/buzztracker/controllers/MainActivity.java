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
import com.cs2340.team.buzztracker.model.Model;

/**
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

        /*
           Get location data from database, create Location objects and put them into the Model
         */
        Intent getLocations = new Intent(this, GetLocationsIntentService.class);
        startService(getLocations);

        /*
           Get category names from database and put them in a list in the Model
         */
        Intent getCategories = new Intent(this, GetCategoriesIntentService.class);
        startService(getCategories);

        /*
           Register all the ResponseReceivers to receive async results
         */
        GetLocationsResponseReceiver receiver1;
        IntentFilter filter1 = new IntentFilter(GetLocationsResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new GetLocationsResponseReceiver();
        registerReceiver(receiver1, filter1);

        GetCategoriesResponseReceiver receiver2;
        IntentFilter filter2 = new IntentFilter(GetCategoriesResponseReceiver.ACTION_RESP);
        filter2.addCategory(Intent.CATEGORY_DEFAULT);
        receiver2 = new GetCategoriesResponseReceiver();
        registerReceiver(receiver2, filter2);

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

                Model model = Model.getInstance();

                /* Parse each of the location's data and store them as Locations in Model */
                while (result.trim().length() > 1) {
                    int startInd = result.indexOf("|");
                    int endInd = result.substring(result.indexOf("|") + 1).indexOf("|") +
                            result.indexOf("|") + 1;
                    String locString = result.substring(startInd + 1, endInd);
                    result = result.substring(endInd );

                    int idStartInd = locString.indexOf("Id:") + 3;
                    int id = Integer.parseInt(locString.substring(idStartInd, idStartInd +
                            locString.substring(idStartInd).indexOf(",")));

                    int nameStartInd = locString.indexOf("Name:") + 5;
                    String name = locString.substring(nameStartInd, nameStartInd +
                            locString.substring(nameStartInd).indexOf(","));

                    int latitudeStartInd = locString.indexOf("Latitude:") + 9;
                    float latitude = Float.parseFloat(locString.substring(latitudeStartInd,
                            latitudeStartInd + locString.substring(latitudeStartInd).indexOf(",")));
                    int longitudeStartInd = locString.indexOf("Longitude:") + 10;
                    float longitude = Float.parseFloat(locString.substring(longitudeStartInd,
                            longitudeStartInd +
                                    locString.substring(longitudeStartInd).indexOf(",")));
                    float[] coordinates = new float[2];
                    coordinates[0] = latitude;
                    coordinates[1] = longitude;

                    int addressStartInd = locString.indexOf("Address:") + 8;
                    String address = locString.substring(addressStartInd,
                            addressStartInd + locString.substring(addressStartInd).indexOf(","));

                    int cityStartInd = locString.indexOf("City:") + 5;
                    String city = locString.substring(cityStartInd,
                            cityStartInd + locString.substring(cityStartInd).indexOf(","));

                    int stateStartInd = locString.indexOf("State:") + 6;
                    String state = locString.substring(stateStartInd, stateStartInd +
                            locString.substring(stateStartInd).indexOf(","));

                    int zipStartInd = locString.indexOf("Zip:") + 4;
                    String zip = locString.substring(zipStartInd, zipStartInd +
                            locString.substring(zipStartInd).indexOf(","));

                    int typeStartInd = locString.indexOf("Type:") + 5;
                    String type = locString.substring(typeStartInd, typeStartInd +
                            locString.substring(typeStartInd).indexOf(","));

                    int phoneStartInd = locString.indexOf("Phone:") + 6;
                    String phone = locString.substring(phoneStartInd, phoneStartInd +
                            locString.substring(phoneStartInd).indexOf(","));

                    int webStartInd = locString.indexOf("Website:") + 8;
                    String website = locString.substring(webStartInd, webStartInd +
                            locString.substring(webStartInd).indexOf(","));

                    Location currentLocation = new Location(id, name, coordinates,
                            address, city, state, zip, type, phone, website, null, null);
                    model.addLocation(currentLocation);
                }
            }
        }

    }

    public class GetCategoriesResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Get category data from database";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");

            if (result.startsWith("no categories found")) {
                Toast.makeText(getBaseContext(), "No category data imported.",
                        Toast.LENGTH_LONG).show();
            } else {
                Model model = Model.getInstance();

                String[] categories = result.split("\\|");
                for (String c : categories) {
                    if (!c.trim().equals("")) {
                        model.addCategory(c);
                    }
                }
            }
        }

    }
}
