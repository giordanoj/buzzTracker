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
import com.cs2340.team.buzztracker.model.User;
import com.cs2340.team.buzztracker.model.UserTypes;

public class Login extends Activity {

    private EditText loginUsername;
    private EditText loginPassword;
    private TextView badLogin;

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
        badLogin = (TextView) findViewById(R.id.badLogin);
        badLogin.setVisibility(View.INVISIBLE);
        Button loginBtn = (Button) findViewById(R.id.loginbtn1);

        loginBtn.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    String inputUsername = loginUsername.getText().toString();
                    String inputPassword = loginPassword.getText().toString();
                    /**
                     *  Check with the database to validate login info
                     *  If valid, log the user in
                     */
                    Intent login = new Intent(Login.this, LoginIntentService.class);
                    login.putExtra("username", inputUsername);
                    login.putExtra("password", Util.generateHash(inputPassword));
                    startService(login);
                }}


        );

        /**
         *  Register all the ResponseReceivers to receive async results
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
                Toast.makeText(getBaseContext(), "Incorrect username/password",
                        Toast.LENGTH_LONG).show();
            } else {

                String[] results = result.split("_");

                int id;
                id = Integer.parseInt(results[0]);
                String uName = results[1];
                String uUsername = loginUsername.getText().toString();
                String uPassword = loginPassword.getText().toString();
                UserTypes uType;
                if (results[2].equals("Admin")) {
                    uType = UserTypes.Admin;
                } else if (results[2].equals("Location Employee")) {
                    uType = UserTypes.Location_Employee;
                } else if (results[2].equals("Manager")) {
                    uType = UserTypes.Manager;
                } else {
                    uType = UserTypes.User;
                }

                User newUser = new User(id, uName, uUsername, uPassword, uType);

                Intent loginA = new Intent(Login.this, ApplicationAcitivity.class);
                Bundle b = new Bundle();
                b.putParcelable("user", newUser);
                loginA.putExtras(b);
                startActivity(loginA);
            }
        }

    }
}
