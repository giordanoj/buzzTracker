package com.cs2340.team.buzztracker.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;

public class LocationDetailActivity extends Activity{
    final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);

        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                model.setCurrentLocation(model.theNullLocation);
                Intent back = new Intent(LocationDetailActivity.this, LocationActivity.class);
                startActivity(back);
                finish();
            }}
        );

        if (!model.getCurrentLocation().equals(model.theNullLocation)) {
            Location currentLocation = model.getCurrentLocation();

            TextView idText = (TextView) findViewById(R.id.idText);
            TextView nameText = (TextView) findViewById(R.id.nameText);
            TextView latitudeText = (TextView) findViewById(R.id.latitudeText);
            TextView longitudeText = (TextView) findViewById(R.id.longitudeText);
            TextView addressText = (TextView) findViewById(R.id.addressText);
            TextView cityText = (TextView) findViewById(R.id.cityText);
            TextView stateText = (TextView) findViewById(R.id.stateText);
            TextView zipText = (TextView) findViewById(R.id.zipText);
            TextView typeText = (TextView) findViewById(R.id.typeText);
            TextView phoneText = (TextView) findViewById(R.id.phoneText);
            TextView websiteText = (TextView) findViewById(R.id.websiteText);

            idText.setText("ID: " + currentLocation.getId());
            nameText.setText("Name: " + currentLocation.get_Name());
            latitudeText.setText("Latitude: " + currentLocation.get_Latitude());
            longitudeText.setText("Longitude: " + currentLocation.get_Longitude());
            addressText.setText("Street Address: " + currentLocation.get_Address());
            cityText.setText("City: " + currentLocation.get_City());
            stateText.setText("State: " + currentLocation.get_State());
            zipText.setText("ZIP Code: " + currentLocation.get_Zip());
            typeText.setText("Location Type: " + currentLocation.get_Type());
            phoneText.setText("Phone Number: " + currentLocation.get_Phone());
            websiteText.setText("Website: " + currentLocation.get_Website());

        }

    }
}
