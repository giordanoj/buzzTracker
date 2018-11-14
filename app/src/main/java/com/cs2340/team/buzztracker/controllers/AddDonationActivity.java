package com.cs2340.team.buzztracker.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Model;


import java.util.ArrayList;
import java.util.Calendar;


public class AddDonationActivity extends Activity {

    private EditText _name;
    private EditText _value;
    private Spinner _category;
    private EditText _description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        /*
         * Grab the dialog widgets so we can get info for later
         */

        _name = (EditText) findViewById(R.id.etName);
        _value =  (EditText) findViewById(R.id.etValue);
        _category = (Spinner) findViewById(R.id.etCategory);
        _description = (EditText) findViewById(R.id.etFullDescription);

        Model model = Model.getInstance();
        /*
            setting up adapter to pull in category types
         */
        ArrayList<String> categories = new ArrayList<>();
        for (String s : model.getCategories()) {
            if (!s.equals("All Categories")) {
                categories.add(s);
            }
        }

        ArrayAdapter<String> categoryArrayAdapter =
                new ArrayAdapter (this,android.R.layout.simple_spinner_item, categories);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _category.setAdapter(categoryArrayAdapter);

        Button cancelBtn = (Button) findViewById(R.id.bCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(AddDonationActivity.this, ApplicationActivity.class);
                startActivity(cancel);
                finish();
            }}
        );

        Button addBtn = (Button) findViewById(R.id.bAddDonation);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                 * Check that the entered information is valid
                 */
                boolean validRegistration = true;

                if (_name.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Item name cannot be blank.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                } else if (_name.getText().toString().contains("~")) {
                    Toast.makeText(getBaseContext(), "Item name contains an illegal character.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                } else if (_value.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Item value cannot be blank.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                } else if (_value.getText().toString().contains("~")) {
                    Toast.makeText(getBaseContext(), "Item value contains an illegal character.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                }else if (_description.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Item description cannot be blank.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                } else if (_description.getText().toString().contains("~")) {
                    Toast.makeText(getBaseContext(),
                            "Item description contains an illegal character.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                }

                if (validRegistration) {
                    /*
                     * Retrieve all necessary information
                     */
                    String name = _name.getText().toString().trim();
                    name = name.replaceAll("'", "~1~");
                    name = name.replaceAll("\"","~2~");
                    String description = _description.getText().toString().trim();
                    description = description.replaceAll("'", "~1~");
                    description = description.replaceAll("\"","~2~");
                    String value = _value.getText().toString().trim();
                    String category = _category.getSelectedItem().toString().trim();
                    category = category.replaceAll("'", "~1~");
                    category = category.replaceAll("\"","~2~");
                    Calendar calender = Calendar.getInstance();
                    String donationTime = calender.getTime().toString();
                    String enteredBy = Model.getInstance().getCurrentUser().getId() + "";
                    String origin = Model.getInstance().getCurrentUser().get_Location().get_id()
                            + "";
                    String currentLocation = origin;

                    /*
                      Add the new item to the database
                     */
                    Intent addDonation = new Intent(AddDonationActivity.this,
                            AddDonationIntentService.class);
                    addDonation.putExtra("name", name);
                    addDonation.putExtra("description", description);
                    addDonation.putExtra("value", value);
                    addDonation.putExtra("category", category);
                    addDonation.putExtra("donationTime", donationTime);
                    addDonation.putExtra("enteredBy", enteredBy);
                    addDonation.putExtra("origin", origin);
                    addDonation.putExtra("currentLocation", currentLocation);
                    startService(addDonation);
                }
            }}
        );

        /*
           Register all the ResponseReceivers to receive async results
         */
        AddDonationActivity.AddDonationResponseReceiver receiver1;
        IntentFilter filter1 =
                new IntentFilter(AddDonationActivity.AddDonationResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new AddDonationActivity.AddDonationResponseReceiver();
        registerReceiver(receiver1, filter1);
    }

    public class AddDonationResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Add a new donation to the database";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");

            if (result.startsWith("item not added")) {
                Toast.makeText(getBaseContext(), "An error occurred: new donation not added.",
                        Toast.LENGTH_LONG).show();
            } else {
                _name.setText("");
                _value.setText("");
                _description.setText("");
                _category.setSelection(0); // 0 sets back to first
                Toast.makeText(getBaseContext(), "Donation added successfully!",
                        Toast.LENGTH_LONG).show();
            }
        }

    }
}
