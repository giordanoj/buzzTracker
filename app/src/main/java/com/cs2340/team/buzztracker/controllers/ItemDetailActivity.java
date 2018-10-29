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

import java.util.ArrayList;

public class ItemDetailActivity extends Activity{
    final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                model.setCurrentItem(model.theNullItem);
                Intent back = new Intent(ItemDetailActivity.this, InventoryActivity.class);
                startActivity(back);
                finish();
            }}
        );

        if (!model.getCurrentItem().equals(model.theNullItem)) {
            Item currentItem = model.getCurrentItem();

            TextView idText = (TextView) findViewById(R.id.idText);
            TextView nameText = (TextView) findViewById(R.id.nameText);
            TextView descriptionText = (TextView) findViewById(R.id.descriptionText);
            TextView valueText = (TextView) findViewById(R.id.valueText);
            TextView categoryText = (TextView) findViewById(R.id.categoryText);
            TextView donationTimeText = (TextView) findViewById(R.id.donationTimeText);
            TextView saleTimeText = (TextView) findViewById(R.id.saleTimeText);
            TextView enteredByText = (TextView) findViewById(R.id.enteredByText);
            TextView soldByText = (TextView) findViewById(R.id.soldByText);
            TextView originText = (TextView) findViewById(R.id.originText);
            TextView currentLocationText = (TextView) findViewById(R.id.currentLocationText);

            idText.setText("ID: " + currentItem.get_id());
            nameText.setText("Name: " + currentItem.get_shortDescription());
            descriptionText.setText("Description: " + currentItem.get_fullDescription());
            valueText.setText("Value: " + currentItem.get_value());
            categoryText.setText("Category: " + currentItem.get_category());
            donationTimeText.setText("Donation Time: " + currentItem.get_donationTime());
            saleTimeText.setText("Sale Time: " + currentItem.get_saleTime());
            enteredByText.setText("Entered by: " + currentItem.get_enteredBy());
            soldByText.setText("Sold by: " + currentItem.get_soldBy());
            originText.setText("Origin: " + model.getLocationById(currentItem.get_origin()).get_name());
            currentLocationText.setText("Current Location: " + model.getLocationById(currentItem.get_currentLocation()).get_name());

        }

    }
}