package com.cs2340.team.buzztracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.cs2340.team.buzztracker.R;
import com.cs2340.team.buzztracker.model.Location;
import com.cs2340.team.buzztracker.model.Model;

import java.util.List;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Model model = Model.getInstance();
        RecyclerView recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(model.getLocations(),
                getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent back = new Intent(LocationActivity.this, ApplicationActivity.class);
                startActivity(back);
                finish();
            }}
        );
    }

    public class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final List<Location> locationsData;
        Context context;

        public RecyclerViewAdapter(List<Location> data, Context context) {
            locationsData = data;
            this.context = context;
        }






        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            final Location location = locationsData.get(position);

            holder.viewHolderName.setText(locationsData.get(position).get_name());

            holder.viewHolderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    model.setCurrentLocation(location);

                    Intent locationDetail = new Intent(LocationActivity.this,
                            LocationDetailActivity.class);

                    startActivity(locationDetail);
                    finish();
                }
            });
        }

        @Override
        public int getItemCount() {
            return locationsData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View viewHolderView;
            public final TextView viewHolderName;

            public ViewHolder(View view) {
                super(view);
                viewHolderView = view;
                viewHolderName = (TextView) view.findViewById(R.id.name);
            }

            @Override
            public String toString() {
                return super.toString();
            }
        }
    }
}
