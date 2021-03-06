package com.cs2340.team.buzztracker.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.text.Editable;
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

/**
 * class for adding a donation to database
 */
public class AddDonationActivity extends Activity {

    private EditText _name;
    private EditText _value;
    private Spinner _category;
    private EditText _description;
    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        /*
         * Grab the dialog widgets so we can get info for later
         */

        _name = findViewById(R.id.etName);
        _value = findViewById(R.id.etValue);
        _category = findViewById(R.id.etCategory);
        _description = findViewById(R.id.etFullDescription);

        /*
            setting up adapter to pull in category types
         */
        ArrayList<String> categories = new ArrayList<>();
        for (String s : model.getCategories()) {
            if (!"All Categories".equals(s)) {
                categories.add(s);
            }
        }

        ArrayAdapter<String> categoryArrayAdapter =
                new ArrayAdapter<> (this,android.R.layout.simple_spinner_item, categories);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _category.setAdapter(categoryArrayAdapter);

        Button cancelBtn = findViewById(R.id.bCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(AddDonationActivity.this, ApplicationActivity.class);
                startActivity(cancel);
                finish();
            }}
        );

        Button addBtn = findViewById(R.id.bAddDonation);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                 * Check that the entered information is valid
                 */
                boolean validRegistration = true;

                Editable editName = _name.getText();
                Editable editValue = _value.getText();
                Editable editDescription = _description.getText();

                String name = editName.toString();
                String value = editValue.toString();
                String description = editDescription.toString();

                String validationMessage = model.validateDonationName(name, value, description);

                if (!("".equals(validationMessage))) {
                    Toast.makeText(getBaseContext(), validationMessage, Toast.LENGTH_LONG).show();
                    validRegistration = false;
                }

                if (validRegistration) {
                    /*
                     * Retrieve all necessary information
                     */
                    String nameTrim = name.trim();
                    nameTrim = nameTrim.replaceAll("'", "~1~");
                    nameTrim = nameTrim.replaceAll("\"","~2~");
                    String descriptionTrim =description.trim();
                    descriptionTrim = descriptionTrim.replaceAll("'", "~1~");
                    descriptionTrim = descriptionTrim.replaceAll("\"","~2~");
                    String valueTrim = value.trim();
                    String category = _category.getSelectedItem().toString().trim();
                    category = category.replaceAll("'", "~1~");
                    category = category.replaceAll("\"","~2~");
                    Calendar calender = Calendar.getInstance();
                    String donationTime = calender.getTime().toString();
                    String enteredBy = model.getCurrentUser().getId() + "";
                    String origin = model.getCurrentUser().get_Location().get_id()
                            + "";
                    String currentLocation = origin;

                    /*
                      Add the new item to the database
                     */
                    Intent addDonation = new Intent(AddDonationActivity.this,
                            AddDonationIntentService.class);
                    addDonation.putExtra("name", nameTrim);
                    addDonation.putExtra("description", descriptionTrim);
                    addDonation.putExtra("value", valueTrim);
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
