package com.cs2340.team.buzztracker.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Item;
import com.cs2340.team.buzztracker.model.Model;

/**
 * for getting item details from database
 */
public class ItemDetailActivity extends Activity{
    final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        Button backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                model.setCurrentItem(model.theNullItem);
                finish();
            }}
        );

        if (!model.getCurrentItem().equals(model.theNullItem)) {
            Item currentItem = model.getCurrentItem();

            TextView idText = findViewById(R.id.idText);
            TextView nameText = findViewById(R.id.nameText);
            TextView descriptionText = findViewById(R.id.descriptionText);
            TextView valueText = findViewById(R.id.valueText);
            TextView categoryText = findViewById(R.id.categoryText);
            TextView donationTimeText = findViewById(R.id.donationTimeText);
            TextView saleTimeText = findViewById(R.id.saleTimeText);
            TextView enteredByText = findViewById(R.id.enteredByText);
            TextView soldByText = findViewById(R.id.soldByText);
            TextView originText = findViewById(R.id.originText);
            TextView currentLocationText = findViewById(R.id.currentLocationText);

            idText.setText("ID: " + currentItem.get_id());
            nameText.setText("Name: " + currentItem.get_shortDescription());
            descriptionText.setText("Description: " + currentItem.get_fullDescription());
            valueText.setText("Value: " + currentItem.get_value());
            categoryText.setText("Category: " + currentItem.get_category());
            donationTimeText.setText("Donation Time: " + currentItem.get_donationTime());
            saleTimeText.setText("Sale Time: " + currentItem.get_saleTime());
            enteredByText.setText("Entered by: " + currentItem.get_enteredBy());
            soldByText.setText("Sold by: " + currentItem.get_soldBy());
            originText.setText("Origin: " +
                    model.getLocationById(currentItem.get_origin()).get_name());
            currentLocationText.setText("Current Location: " +
                    model.getLocationById(currentItem.get_currentLocation()).get_name());

        }

    }
}
