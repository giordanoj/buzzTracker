package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Model;

public class Login extends Activity {

    private EditText loginUsername;
    private EditText loginPassword;
    private TextView badLogin;
    private Model _model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button cancelButton = (Button) findViewById(R.id.cancelbtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(Login.this, MainActivity.class);
                startActivity(cancel);
            }
        }


        );

        _model = Model.getInstance();

        loginUsername = (EditText) findViewById(R.id.username);
        loginPassword = (EditText) findViewById(R.id.passwordText);
        badLogin = (TextView) findViewById(R.id.badLogin);
        badLogin.setVisibility(View.INVISIBLE);
        Button loginBtn = (Button) findViewById(R.id.loginbtn1);

        loginBtn.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    String username = loginUsername.getText().toString();
                    String password = loginPassword.getText().toString();
                    if(_model.userLogin(username, password)) {
                        Intent loginA = new Intent(Login.this, ApplicationAcitivity.class);
                        startActivity(loginA);
                    } else {
                        badLogin.setVisibility(View.VISIBLE);
                    }
                }}


        );




    }

}
