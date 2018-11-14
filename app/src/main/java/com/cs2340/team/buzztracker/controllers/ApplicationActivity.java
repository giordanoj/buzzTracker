package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.User;

/**
 * For the main purpose of the application
 *
 */
public class ApplicationActivity extends Activity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_acitivity);

        final Model model = Model.getInstance();

        TextView usernameText = findViewById(R.id.usernameView);
        usernameText.setText("Welcome, " + model.getCurrentUser().get_Name());

        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                model.setCurrentUser(model.theNullUser);
                Intent logout = new Intent(ApplicationActivity.this, MainActivity.class);
                startActivity(logout);
                finish();
            }}
        );

        Button locationsBtn = findViewById(R.id.locationsBtn);
        locationsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent locations = new Intent(ApplicationActivity.this,
                        LocationActivity.class);
                startActivity(locations);
                finish();
            }}
        );

        Button addDonationBtn = findViewById(R.id.addDonationBtn);
        addDonationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent addDonation = new Intent(ApplicationActivity.this,
                        AddDonationActivity.class);
                startActivity(addDonation);
                finish();
            }}
        );

        Button itemSearchBtn = findViewById(R.id.itemSearchBtn);
        itemSearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent itemSearch = new Intent(ApplicationActivity.this, ItemSearchActivity.class);
                startActivity(itemSearch);
                finish();
            }}
        );

        Button mapBtn = findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent map = new Intent(ApplicationActivity.this, MapsActivity.class);
                startActivity(map);

            }}
        );
    }




}
