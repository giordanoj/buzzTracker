package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.LocationList;
import com.cs2340.team.buzztracker.model.User;

public class ApplicationAcitivity extends Activity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_acitivity);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            user = b.getParcelable("user");
            TextView usernameText = (TextView) findViewById(R.id.usernameView);
            usernameText.setText(LocationList.getList().get(1).toString());
        }

        Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent logout = new Intent(ApplicationAcitivity.this, MainActivity.class);
                startActivity(logout);
                finish();
            }}


        );
    }




}
