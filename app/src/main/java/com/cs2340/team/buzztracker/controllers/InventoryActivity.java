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
import com.cs2340.team.buzztracker.model.Model;

import java.util.List;

/**
 * for getting inventory from database
 */
public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Model model = Model.getInstance();
        RecyclerView recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        RecyclerViewAdapter adapter =
                new RecyclerViewAdapter(model.getCurrentInventory().get_items(), getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String previous = getIntent().getStringExtra("previous");
                Log.e("hi",previous);
                if ("LocationDetailActivity".equals(previous)) {
                    Intent back = new Intent(InventoryActivity.this, LocationDetailActivity.class);
                    startActivity(back);
                    finish();
                } else if ("ItemSearchActivity".equals(previous)) {
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

        /**
         *
         * @param data the list of items to put into itemsData
         * @param context the context of operation
         */
        private RecyclerViewAdapter(List<Item> data, Context context) {
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

            holder.viewHolderName.setText(itemsData.get(position).get_shortDescription());

            holder.viewHolderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    model.setCurrentItem(item);

                    Intent itemDetail = new Intent(InventoryActivity.this,
                            ItemDetailActivity.class);

                    startActivity(itemDetail);
                }
            });
        }

        @Override
        public int getItemCount() {
            return itemsData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final View viewHolderView;
            private final TextView viewHolderName;

            /**
             *
             * @param view the view to display information in
             */
            private ViewHolder(View view) {
                super(view);
                viewHolderView = view;
                viewHolderName = view.findViewById(R.id.name);
            }

//            @Override
//            public String toString() {
//                return super.toString();
//            }
        }
    }
}
