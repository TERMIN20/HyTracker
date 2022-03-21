package com.example.hytracker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemsAdapter itemsAdapter;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        itemsAdapter = new ItemsAdapter(this, BrowsePage.auctionItems);
        recyclerView.setAdapter(itemsAdapter);
        Log.d("grr", "item count: " + itemsAdapter.getItemCount());


        //itemsAdapter.notifyDataSetChanged();
    }

}
