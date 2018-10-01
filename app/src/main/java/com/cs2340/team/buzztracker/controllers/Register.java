package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.UserTypes;
import com.cs2340.team.buzztracker.model.User;

public class Register extends AppCompatActivity {

    private Spinner userSpinner;
    private EditText _username;
    private EditText _password;
    private EditText _name;
    private TextView idField;
    private Model _model;
    private TextView badLogin;


    /* ***********************
       flag for whether this is a new student being added or an existing student being edited;
     */
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * Grab the dialog widgets so we can get info for later
         */

        userSpinner = (Spinner) findViewById(R.id.typeUser);
        _username =  (EditText) findViewById(R.id.usernameText);
        _password = (EditText) findViewById(R.id.passwordText);
        idField = (TextView) findViewById(R.id.idView);
        badLogin = (TextView)findViewById(R.id.badLogin);


        _model = Model.getInstance();

         badLogin.setVisibility(View.INVISIBLE);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(Register.this, MainActivity.class);
                startActivity(cancel);
            }}


        );

        Button registerButton = (Button) findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (_model.usernameAvailable(_username.toString())) {
                    Log.d("Edit","Done");
                    Intent register = new Intent(Register.this, ApplicationAcitivity.class);
                    String username1 = _username.getText().toString();
                    String password1 = _password.getText().toString();
                    UserTypes users = (UserTypes)userSpinner.getSelectedItem();
                    User user1 = new User(username1, password1, users);
                    _model.addUser(user1);
                    startActivity(register);

                } else {
                    badLogin.setVisibility(View.VISIBLE);
                }
            }


        });

        /*
          Set up the adapter to display the allowable Users in the spinner
         */
        ArrayAdapter<UserTypes> useradapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserTypes.values());
        useradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(useradapter);

    }
}
