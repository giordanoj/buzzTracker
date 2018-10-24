package com.cs2340.team.buzztracker.controllers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Inventory;
import com.cs2340.team.buzztracker.model.Item;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;

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
                /**
                 * Get all of the items from this location from the database, then put then into
                 * the current Inventory in the Model
                 */
                Intent inventory = new Intent(LocationDetailActivity.this, GetInventoryIntentService.class);
                inventory.putExtra("location", Model.getInstance().getCurrentLocation().get_id() + "");
                startService(inventory);
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

        /**
         *  Register all the ResponseReceivers to receive async results
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
                /**
                 * For each item in the response String, parse the Item data, put the data into
                 * an Item object, and put that Item into an ArrayList
                 */
                Model model = Model.getInstance();
                ArrayList<Item> items = new ArrayList<>();
                while (result.trim().length() > 1) {
                    int startInd = result.indexOf("|");
                    int endInd = result.substring(result.indexOf("|") + 1).indexOf("|") + result.indexOf("|") + 1;
                    String itemString = result.substring(startInd + 1, endInd);
                    result = result.substring(endInd );

                    int idStartInd = itemString.indexOf("Id:") + 3;
                    int id = Integer.parseInt(itemString.substring(idStartInd, idStartInd + itemString.substring(idStartInd).indexOf("~")));

                    int nameStartInd = itemString.indexOf("Name:") + 5;
                    String name = itemString.substring(nameStartInd, nameStartInd + itemString.substring(nameStartInd).indexOf("~"));

                    int descStartInd = itemString.indexOf("Description:") + 12;
                    String description = itemString.substring(descStartInd, descStartInd + itemString.substring(descStartInd).indexOf("~"));

                    int valStartInd = itemString.indexOf("Value:") + 6;
                    String value = itemString.substring(valStartInd, valStartInd + itemString.substring(valStartInd).indexOf("~"));

                    int catStartInd = itemString.indexOf("Category:") + 9;
                    String category = itemString.substring(catStartInd, catStartInd + itemString.substring(catStartInd).indexOf("~"));

                    int donStartInd = itemString.indexOf("Donation Time:") + 14;
                    String donationTime = itemString.substring(donStartInd, donStartInd + itemString.substring(donStartInd).indexOf("~"));

                    int saleStartInd = itemString.indexOf("Sale Time:") + 10;
                    String saleTime = itemString.substring(saleStartInd, saleStartInd + itemString.substring(saleStartInd).indexOf("~"));

                    int entStartInd = itemString.indexOf("Entered By:") + 11;
                    int enteredBy = Integer.parseInt(itemString.substring(entStartInd, entStartInd + itemString.substring(entStartInd).indexOf("~")));

                    int soldStartInd = itemString.indexOf("Sold By:") + 8;
                    int soldBy = Integer.parseInt(itemString.substring(soldStartInd, soldStartInd + itemString.substring(soldStartInd).indexOf("~")));

                    int originStartInd = itemString.indexOf("Origin:") + 7;
                    int origin = Integer.parseInt(itemString.substring(originStartInd, originStartInd + itemString.substring(originStartInd).indexOf("~")));

                    int picStartInd = itemString.indexOf("Picture:") + 8;
                    String picture = itemString.substring(picStartInd, picStartInd + itemString.substring(picStartInd).indexOf("~"));

                    int commStartInd = itemString.indexOf("Comments:") + 9;
                    String comments = itemString.substring(commStartInd, commStartInd + itemString.substring(commStartInd).indexOf("~"));

                    int currStartInd = itemString.indexOf("Current Location:") + 17;
                    int currentLocation = Integer.parseInt(itemString.substring(currStartInd, currStartInd + itemString.substring(currStartInd).indexOf("~")));

                    Item item = new Item(id, category, donationTime, value, picture, comments, name, description, origin, saleTime, enteredBy, soldBy, currentLocation);
                    items.add(item);
                }

                /**
                 * Create an Inventory object from the ArrayList of Items and the current
                 * Location from the Model
                 */
                Inventory inventory = new Inventory(items, model.getCurrentLocation());
                model.setCurrentInventory(inventory);

                /**
                 * Move on to the Inventory screen
                */
                Intent invent = new Intent(LocationDetailActivity.this, InventoryActivity.class);
                startActivity(invent);
                finish();
            }
        }

    }
}
