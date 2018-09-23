package com.cs2340.team.buzztracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cs2340.team.buzztracker.R;

/*
   Main screen that displays Login and Registration
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Button loginButton = (Button) findViewById(R.id.loginButton);







}
