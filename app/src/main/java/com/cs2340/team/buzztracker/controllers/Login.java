package com.cs2340.team.buzztracker.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.User;
import com.cs2340.team.buzztracker.model.UserTypes;
import com.cs2340.team.buzztracker.model.Util;

/**
 * this class handles getting the information for login data
 */
public class Login extends Activity {

    private EditText loginEmail;
    private EditText loginPassword;
    private TextView badLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button cancelButton = findViewById(R.id.cancelbtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(Login.this, MainActivity.class);
                startActivity(cancel);
                finish();
            }
        }


        );

        loginEmail = findViewById(R.id.tvEmail);
        loginPassword = findViewById(R.id.tvPassword);
        badLogin = findViewById(R.id.badLogin);
        badLogin.setVisibility(View.INVISIBLE);
        Button loginBtn = findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    String inputEmail = loginEmail.getText().toString();
                    String inputPassword = loginPassword.getText().toString();
                    /*
                       Check with the database to validate login info
                       If valid, log the user in
                     */
                    Intent login = new Intent(Login.this, LoginIntentService.class);
                    login.putExtra("email", inputEmail);
                    login.putExtra("password", Util.generateHash(inputPassword));
                    startService(login);
                }}


        );

        /*
           Register all the ResponseReceivers to receive async results
         */
        Login.LoginResponseReceiver receiver1;
        IntentFilter filter1 = new IntentFilter(Login.LoginResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new Login.LoginResponseReceiver();
        registerReceiver(receiver1, filter1);


    }

    public class LoginResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Check login information";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");
            if (result.startsWith("user not found")) {
                Toast.makeText(getBaseContext(), "Incorrect email/password",
                        Toast.LENGTH_LONG).show();
            } else {
                /*
                  Parse the response String with all of the User data, then create a User object
                  out of the data and set it as the current User in the Model
                 */
                int startInd = result.indexOf("|");
                int endInd = result.substring(result.indexOf("|") + 1).indexOf("|") +
                        result.indexOf("|") + 1;
                String userString = result.substring(startInd + 1, endInd);

                int idStartInd = userString.indexOf("Id:") + 3;
                int id = Integer.parseInt(userString.substring(idStartInd, idStartInd +
                        userString.substring(idStartInd).indexOf(",")));

                int nameStartInd = userString.indexOf("Name:") + 5;
                String name = userString.substring(nameStartInd, nameStartInd +
                        userString.substring(nameStartInd).indexOf(","));

                int emailStartInd = userString.indexOf("Email:") + 6;
                String email = userString.substring(emailStartInd, emailStartInd +
                        userString.substring(emailStartInd).indexOf(","));

                int passStartInd = userString.indexOf("Password:") + 9;
                String password = userString.substring(passStartInd, passStartInd +
                        userString.substring(passStartInd).indexOf(","));

                int lockStartInd = userString.indexOf("Locked:") + 7;
                boolean locked = Boolean.getBoolean(userString.substring(lockStartInd,
                        lockStartInd + userString.substring(lockStartInd).indexOf(",")));

                int typeStartInd = userString.indexOf("Type:") + 5;
                String type = userString.substring(typeStartInd, typeStartInd +
                        userString.substring(typeStartInd).indexOf(","));
                UserTypes uType;
                if ("Admin".equals(type)) {
                    uType = UserTypes.Admin;
                } else if ("Location Employee".equals(type)) {
                    uType = UserTypes.Location_Employee;
                } else if ("Manager".equals(type)) {
                    uType = UserTypes.Manager;
                } else {
                    uType = UserTypes.User;
                }

                final Model model = Model.getInstance();

                int locStartInd = userString.indexOf("Location:") + 9;
                int location = Integer.parseInt(userString.substring(locStartInd,
                        locStartInd + userString.substring(locStartInd).indexOf(",")));
                Location uLocation = model.theNullLocation;
                for (Location l : model.getLocations()) {
                    if (l.get_id() == location) {
                        uLocation = l;
                    }
                }

                User newUser = new User(id, name, email, password, locked, uType, uLocation);
                model.setCurrentUser(newUser);

                Intent loginA = new Intent(Login.this, ApplicationActivity.class);
                startActivity(loginA);
                finish();
            }
        }

    }
}
