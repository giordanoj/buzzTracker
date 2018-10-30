package com.cs2340.team.buzztracker.controllers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.CategoryTypes;
import com.cs2340.team.buzztracker.model.Model;

import java.util.Calendar;

public class ItemSearchActivity extends Activity {

    private TextView _location;
    private EditText _search;
    private Spinner _category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        /**
         * Grab the dialog widgets so we can get info for later
         */

        _location = (TextView) findViewById(R.id.tvLocation);
        _search =  (EditText) findViewById(R.id.etSearch);
        _category = (Spinner) findViewById(R.id.etCategory);

        Model model = Model.getInstance();
        /**
            setting up adapter to pull in category types
         */
        ArrayAdapter<CategoryTypes> categoryTypesArrayAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, CategoryTypes.values());
        categoryTypesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _category.setAdapter(categoryTypesArrayAdapter);

        Button backBtn = (Button) findViewById(R.id.bBack);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Model model = Model.getInstance();
                Intent back;
                if (model.getCurrentLocation() == model.theNullLocation) {
                    back = new Intent(ItemSearchActivity.this, ApplicationAcitivity.class);
                } else {
                    back = new Intent(ItemSearchActivity.this, LocationDetailActivity.class);
                }

                startActivity(back);
                finish();
            }}
        );

        Button searchBtn = (Button) findViewById(R.id.bSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Check that the entered information is valid
                 */
                boolean validRegistration = true;

                if (_search.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Search cannot be blank.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                } else if (_search.getText().toString().contains("~")) {
                    Toast.makeText(getBaseContext(), "Search contains an illegal character.",
                            Toast.LENGTH_LONG).show();
                    validRegistration = false;
                }

                if (validRegistration) {
                    /**
                     * Retrieve all necessary information
                     */
                    Model model = Model.getInstance();
                    String query = _search.getText().toString().trim();
                    query = query.replaceAll("'", "~1~");
                    query = query.replaceAll("\"","~2~");
                    String location = model.getCurrentLocation().get_id() + "";
                    String category = _category.getSelectedItem().toString().trim();

                    /**
                     * Send query to the database
                     */
                    Intent search = new Intent(ItemSearchActivity.this, ItemSearchIntentService.class);
                    search.putExtra("query", query);
                    search.putExtra("location", location);
                    search.putExtra("category", category);
                    startService(search);
                }
            }}
        );

        /**
         *  Register all the ResponseReceivers to receive async results
         */
        ItemSearchActivity.ItemSearchResponseReceiver receiver1;
        IntentFilter filter1 = new IntentFilter(ItemSearchActivity.ItemSearchResponseReceiver.ACTION_RESP);
        filter1.addCategory(Intent.CATEGORY_DEFAULT);
        receiver1 = new ItemSearchActivity.ItemSearchResponseReceiver();
        registerReceiver(receiver1, filter1);
    }

    public class ItemSearchResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "Search for items for the database based on a query";
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("output");

            if (result.startsWith("item not added")) {
                Toast.makeText(getBaseContext(), "An error occurred: new donation not added.",
                        Toast.LENGTH_LONG).show();
            } else {

            }
        }

    }
}
