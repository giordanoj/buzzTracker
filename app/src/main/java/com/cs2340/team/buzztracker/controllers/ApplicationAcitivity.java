package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.User;

public class ApplicationAcitivity extends Activity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_acitivity);

        Model model = Model.getInstance();

        TextView usernameText = (TextView) findViewById(R.id.usernameView);
        usernameText.setText("Welcome " + model.getCurrentUser().get_Name());

        Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Model model = Model.getInstance();
                model.setCurrentUser(model.theNullUser);
                Intent logout = new Intent(ApplicationAcitivity.this, MainActivity.class);
                startActivity(logout);
                finish();
            }}
        );

        Button locationsBtn = (Button) findViewById(R.id.locationsBtn);
        locationsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent locations = new Intent(ApplicationAcitivity.this, LocationActivity.class);
                startActivity(locations);
                finish();
            }}
        );

        Button addDonationBtn = (Button) findViewById(R.id.addDonationBtn);
        addDonationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent addDonation = new Intent(ApplicationAcitivity.this, AddDonationActivity.class);
                startActivity(addDonation);
                finish();
            }}
        );
    }




}
