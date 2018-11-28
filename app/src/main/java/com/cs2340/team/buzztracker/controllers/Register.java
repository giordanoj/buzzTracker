package com.cs2340.team.buzztracker.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.User;
import com.cs2340.team.buzztracker.model.UserTypes;
import com.cs2340.team.buzztracker.model.Util;

/**
 * class for registering new users
 */
public class Register extends AppCompatActivity {

    private Spinner userSpinner;
    private EditText _email;
    private EditText _password;
    private EditText _name;
    private TextView badName;
    private TextView idField;
    private TextView badEmail;
    private TextView badPassword;
    private final Model model = Model.getInstance();


    /* ***********************
       flag for whether this is a new student being added or an existing student being edited;
     */
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*
          Grab the dialog widgets so we can get info for later
         */

        userSpinner = findViewById(R.id.typeUser);
        _name = findViewById(R.id.nameText);
        _email = findViewById(R.id.emailText);
        _password = findViewById(R.id.passwordText);
        idField = findViewById(R.id.idView);
        badName = findViewById(R.id.badName);
        badEmail = findViewById(R.id.badEmail);
        badPassword = findViewById(R.id.badPassword);

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(Register.this, MainActivity.class);
                startActivity(cancel);
                finish();
            }}


        );

        Button registerButton = findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean validRegistration = true;

                /*
                   Make sure none of the text fields are empty
                 */
                if ("".equals(_name.getText().toString().trim())) {
                    badName.setText("Name cannot be blank!");
                    validRegistration = false;
                } else if (_name.getText().toString().contains("|")
                        || _name.getText().toString().contains(",")) {
                    badName.setText("Name contains an illegal character.");
                    validRegistration = false;
                } else {
                    badName.setText("");
                }
                if ("".equals(_email.getText().toString().trim())) {
                    badEmail.setText("Email cannot be blank!");
                    validRegistration = false;
                } else if (_email.getText().toString().contains("|")
                        || _email.getText().toString().contains(",")) {
                    badEmail.setText("Email contains an illegal character.");
                    validRegistration = false;
                } else {
                    badEmail.setText("");
                }
                if ("".equals(_password.getText().toString().trim())) {
                    badPassword.setText("Password cannot be blank!");
                    validRegistration = false;
                } else {
                    badPassword.setText("");
                }

                if (validRegistration) {
                    /*
                       Check with the database to make sure the username isn't already used
                       If unused, add the user to the database and return to the welcome page
                     */
                    Intent checkRegistration = new Intent(Register.this,
                            CheckRegistrationIntentService.class);
                    checkRegistration.putExtra("email", _email.getText().toString());
                    startService(checkRegistration);
                }
            }

        });

        /*
          Set up the adapter to display the allowable Users in the spinner
         */
        ArrayAdapter<UserTypes> userAdapter =
                new ArrayAdapter<>(this, R.layout.simple_spinner_white_text,UserTypes.values());
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(userAdapter);

        /*
           Register all the ResponseReceivers to receive async results
         */
        CheckRegistrationResponseReceiver receiver1;
        IntentFilter filter1 = new IntentFilter(CheckRegistrationResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new CheckRegistrationResponseReceiver();
        registerReceiver(receiver1, filter1);

        RegisterUserResponseReceiver receiver2;
        IntentFilter filter2 = new IntentFilter(RegisterUserResponseReceiver.ACTION_RESP);
        filter2.addCategory(Intent.CATEGORY_DEFAULT);
        receiver2 = new RegisterUserResponseReceiver();
        registerReceiver(receiver2, filter2);
    }

    public class CheckRegistrationResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Check database to validate registration info";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");
            if (result.startsWith("invalid")) {
                badEmail.setText("Email already in use; select another email.");
            } else {
                String uName = _name.getText().toString();
                String uEmail = _email.getText().toString();
                String uPassword = _password.getText().toString();
                String uType = userSpinner.getSelectedItem().toString();

                Intent registerUser = new Intent(Register.this, RegisterUserIntentService.class);
                registerUser.putExtra("name", uName);
                registerUser.putExtra("email", uEmail);
                registerUser.putExtra("password", Util.generateHash(uPassword));
                registerUser.putExtra("accountType", uType);
                startService(registerUser);
            }
        }

    }

    public class RegisterUserResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Add user to database";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");
            if (result.startsWith("user not added")) {
                Toast.makeText(getBaseContext(), "An error occurred: new user not added.",
                        Toast.LENGTH_LONG).show();
            } else {

                int id;
                id = Integer.parseInt(result.trim());
                String uName = _name.getText().toString();
                String uEmail = _email.getText().toString();
                String uPassword = _password.getText().toString();
                boolean uLocked = false;
                UserTypes uType = (UserTypes) userSpinner.getSelectedItem();

                User newUser = new User(id, uName, uEmail, uPassword, uLocked, uType);
                model.setCurrentUser(newUser);

                Intent loginA = new Intent(Register.this, ApplicationActivity.class);
                startActivity(loginA);
                finish();
            }
        }

    }
}
