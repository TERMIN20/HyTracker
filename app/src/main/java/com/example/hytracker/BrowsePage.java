package com.example.hytracker;


import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BrowsePage extends AppCompatActivity {

    ArrayList<AuctionItem> auctionItems = new ArrayList<>();
    public static ArrayList<String> queryWords;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_page);

        EditText query = findViewById(R.id.itemQuery);
        Button enterButton = findViewById(R.id.itemSearchButton);

        Log.d("words", "HI");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryWords = new ArrayList<>(Arrays.asList(query.getText().toString().split(" ")));
                Log.d("words", queryWords.toString());
                try {
                    Log.d("words", "Code Block Executes");
                    JSONObject o = new RequestAPI().getAuctionsPage(0);
                    Log.d("words", "JSONObject Created");
                    int pages = o.getInt("totalPages");
                    if (!o.getBoolean("success") && o.getString("cause").contains("Invalid API key"))
                        Log.d("errorLOL", "Invalid API Key");

                    //Log.d("words", "ALL HERE?? ");
                    Log.d("words", "total pages:" + pages);
                    auctionItems.clear();
                    pages = Math.min(pages, 25);
                    Log.d("words", "min pages: " + pages);

                    addPage(o);
                    addPages(pages);

                    for (int i = 0; i < Math.min(auctionItems.size(), 25); i++) {
                        AuctionItem a = auctionItems.get(i);

                    }

                } catch (JSONException | InterruptedException e) {
                    Log.d("words", "Shit went down" + e);
                }
            }
        });



    }

    private void addPages(int pages) throws InterruptedException {
        int threadCount = Math.min(pages - 1, 4);
        CircularArray<Thread> requestThreads = new CircularArray<>(threadCount);
        for (int i = 1; i < pages; i++) {
            Log.d("words", "On Page " + i);
            int finalI = i;
            requestThreads.addFirst(new Thread() {
                @Override
                public void run() {
                    try {
                        addPage(new RequestAPI().getAuctionsPage(finalI));
                    } catch (JSONException ignored) {

                    }
                }
            });
            requestThreads.getFirst().start();
            if (requestThreads.size() >= threadCount)
                requestThreads.popLast().join();
        }
        while (requestThreads.size() > 0)
            requestThreads.popLast().join();
    }

    private void addPage(JSONObject o) throws JSONException
    {
        JSONArray arr = o.getJSONArray("auctions");
        for (int i = 0; i < arr.length(); i++)
        {
            AuctionItem a = new AuctionItem(arr.getJSONObject(i));
            if (a.contains(queryWords)) {
                auctionItems.add(a);
            }
        }
    }

}
