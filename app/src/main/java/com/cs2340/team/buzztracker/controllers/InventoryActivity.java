package com.cs2340.team.buzztracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Item;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Model model = Model.getInstance();
        RecyclerView recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(model.getCurrentInventory().get_items(), getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String previous = getIntent().getStringExtra("previous");
                Log.e("hi",previous);
                if (previous.equals("LocationDetailActivity")) {
                    Intent back = new Intent(InventoryActivity.this, LocationDetailActivity.class);
                    startActivity(back);
                    finish();
                } else if (previous.equals("ItemSearchActivity")) {
                    Intent back = new Intent(InventoryActivity.this, ItemSearchActivity.class);
                    startActivity(back);
                    finish();
                }
            }}
        );
    }

    public class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final List<Item> itemsData;
        Context context;

        public RecyclerViewAdapter(List<Item> data, Context context) {
            itemsData = data;
            this.context = context;
        }






        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            final Item item = itemsData.get(position);

            holder.viewholderName.setText(itemsData.get(position).get_shortDescription());

            holder.viewholderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    model.setCurrentItem(item);

                    Intent itemDetail = new Intent(InventoryActivity.this, ItemDetailActivity.class);

                    startActivity(itemDetail);
                }
            });
        }

        @Override
        public int getItemCount() {
            return itemsData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View viewholderView;
            public final TextView viewholderName;

            public ViewHolder(View view) {
                super(view);
                viewholderView = view;
                viewholderName = (TextView) view.findViewById(R.id.name);
            }

            @Override
            public String toString() {
                return super.toString();
            }
        }
    }
}
