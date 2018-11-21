package com.cs2340.team.buzztracker.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.User;
import com.cs2340.team.buzztracker.model.UserTypes;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GoogleLogin extends AppCompatActivity implements View.OnClickListener{

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "Google Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.logOutButton).setOnClickListener(this);
        findViewById(R.id.mainMenu).setOnClickListener(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.logOutButton:
                signOut();
                revokeAccess();
                break;
            case R.id.mainMenu:
                Intent loginA = new Intent(GoogleLogin.this, ApplicationActivity.class);
                startActivity(loginA);
                finish();
                break;

        }

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }



    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(GoogleLogin.this, "Google access revoked.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

    }

    //Update UI if account is not null = login in the User
    private void updateUI(GoogleSignInAccount account){
            if (account != null) {
                String name = account.getDisplayName();

                //user email id
                String email = account.getEmail();

                //user unique id
                String id = account.getId();
                int id1;
                try {
                     id1 = Integer.parseInt(id);
                } catch (NumberFormatException x) {
                    id1 = 1001;
                }
                boolean locked = false;

                String password = account.getIdToken();

                UserTypes uType = UserTypes.User;

                Location uLocation = null;



                final Model model = Model.getInstance();
                User newUser = new User(id1, name, email, password, locked, uType, uLocation);
                newUser.setGoogleUser(true);
                model.setCurrentUser(newUser);




                findViewById(R.id.mainMenu).setVisibility(View.VISIBLE);
                findViewById(R.id.sign_in_button).setVisibility(View.GONE);
                findViewById(R.id.logOutButton).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
                findViewById(R.id.logOutButton).setVisibility(View.GONE);
                findViewById(R.id.mainMenu).setVisibility(View.GONE);


            }

    }


}
