package com.cs2340.team.buzztracker.controllers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Inventory;
import com.cs2340.team.buzztracker.model.Item;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;
import com.cs2340.team.buzztracker.model.Util;

import java.util.ArrayList;

public class LocationDetailActivity extends Activity{
    final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);

        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                model.setCurrentLocation(model.theNullLocation);
                Intent back = new Intent(LocationDetailActivity.this, LocationActivity.class);
                startActivity(back);
                finish();
            }}
        );

        Button inventoryBtn = (Button) findViewById(R.id.inventoryButton);
        inventoryBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                  Get all of the items from this location from the database, then put then into
                  the current Inventory in the Model
                 */
                Intent inventory = new Intent(LocationDetailActivity.this,
                        GetInventoryIntentService.class);
                inventory.putExtra("location",
                        Model.getInstance().getCurrentLocation().get_id() + "");
                startService(inventory);
            }}
        );

        Button itemSearchBtn = (Button) findViewById(R.id.searchButton);
        itemSearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent itemSearch = new Intent(LocationDetailActivity.this,
                        ItemSearchActivity.class);
                startActivity(itemSearch);
                finish();
            }}
        );

        if (!model.getCurrentLocation().equals(model.theNullLocation)) {
            Location currentLocation = model.getCurrentLocation();

            TextView idText = (TextView) findViewById(R.id.idText);
            TextView nameText = (TextView) findViewById(R.id.nameText);
            TextView latitudeText = (TextView) findViewById(R.id.descriptionText);
            TextView longitudeText = (TextView) findViewById(R.id.valueText);
            TextView addressText = (TextView) findViewById(R.id.categoryText);
            TextView cityText = (TextView) findViewById(R.id.donationTimeText);
            TextView stateText = (TextView) findViewById(R.id.saleTimeText);
            TextView zipText = (TextView) findViewById(R.id.enteredByText);
            TextView typeText = (TextView) findViewById(R.id.soldByText);
            TextView phoneText = (TextView) findViewById(R.id.originText);
            TextView websiteText = (TextView) findViewById(R.id.currentLocationText);


            idText.setText("ID: " + currentLocation.get_id());
            nameText.setText("Name: " + currentLocation.get_name());
            latitudeText.setText("Latitude: " + currentLocation.get_coordinates()[0]);
            longitudeText.setText("Longitude: " + currentLocation.get_coordinates()[1]);
            addressText.setText("Street Address: " + currentLocation.get_address());
            cityText.setText("City: " + currentLocation.get_city());
            stateText.setText("State: " + currentLocation.get_state());
            zipText.setText("ZIP Code: " + currentLocation.get_zip());
            typeText.setText("Location Type: " + currentLocation.get_type());
            phoneText.setText("Phone Number: " + currentLocation.get_phoneNumber());
            websiteText.setText("Website: " + currentLocation.get_website());

        }

        /*
           Register all the ResponseReceivers to receive async results
         */
        GetInventoryResponseReceiver receiver1;
        IntentFilter filter1 = new IntentFilter(GetInventoryResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new GetInventoryResponseReceiver();
        registerReceiver(receiver1, filter1);

    }

    public class GetInventoryResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Get all items from a location";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");
            if (result.startsWith("no items found")) {
                Toast.makeText(getBaseContext(), "Location inventory is empty.",
                        Toast.LENGTH_LONG).show();
            } else {
                Model model = Model.getInstance();
                ArrayList<Item> items = new ArrayList<>();
                /*
                  Create an Inventory object and make it the current Inventory in the Model
                 */
                Inventory inventory = new Inventory(items, model.getCurrentLocation());
                model.setCurrentInventory(inventory);

                /*
                  For each item in the response String, parse the Item data, put the data into
                  an Item object, and add that item to the Model
                 */
                while (result.trim().length() > 1) {
                    int startInd = result.indexOf("|");
                    int endInd = result.substring(result.indexOf("|") + 1).indexOf("|") +
                            result.indexOf("|") + 1;
                    String itemString = result.substring(startInd + 1, endInd);
                    result = result.substring(endInd );

                    model.getCurrentInventory().addItem(Util.parseItemString(itemString));
                }

                /*
                  Move on to the Inventory screen
                */
                Intent invent = new Intent(LocationDetailActivity.this, InventoryActivity.class);
                invent.putExtra("previous", "LocationDetailActivity");
                startActivity(invent);
                finish();
            }
        }

    }
}
