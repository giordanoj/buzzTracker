package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;

import org.w3c.dom.Text;

public class Login extends Activity {

    private EditText loginUsername;
    private EditText loginPassword;
    private TextView badlogin;

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

        loginUsername = (EditText) findViewById(R.id.username);
        loginPassword = (EditText) findViewById(R.id.passwordText);
        badlogin = (TextView) findViewById(R.id.badLogin);
        badlogin.setVisibility(View.INVISIBLE);
        Button loginBtn = (Button) findViewById(R.id.loginbtn1);

        loginBtn.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {

                    if(loginUsername.getText().toString().equals("User") &&
                            loginPassword.getText().toString().equals("Pass")) {
                        Intent loginA = new Intent(Login.this, ApplicationAcitivity.class);
                        startActivity(loginA);
                    } else {
                        badlogin.setVisibility(View.VISIBLE);

                    }
                }}


        );




    }

}
