package com.example.hytracker;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BrowsePage extends AppCompatActivity {


    public static ArrayList<AuctionItem> auctionItems = new ArrayList<>();
    public static ArrayList<String> queryWords;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_page);

        EditText query = findViewById(R.id.itemQuery);
        Button enterButton = findViewById(R.id.itemSearchButton);


        Context context = this;

        Log.d("words", "does stuff");

        Log.d("words", "HI");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuery(query.getText().toString());
                Log.d("words", queryWords.toString());
                try {
                    Log.d("words", "Code Block Executes");
                    JSONObject o = new RequestAPI().getAuctionsPage(0);
                    Log.d("words", "JSONObject Created");

                    if (!o.getBoolean("success")) {
                        Toast.makeText(BrowsePage.this, "Invalid API Key, Re-Enter", Toast.LENGTH_SHORT).show();
                        OpenAPIPage();
                    }

                    int pages = o.getInt("totalPages");
                    //Log.d("words", "ALL HERE?? ");
                    Log.d("words", "total pages:" + pages);
                    auctionItems.clear();
                    pages = Math.min(pages, 10);
                    Log.d("words", "min pages: " + pages);

                    addPage(o);
                    addPages(pages);


                    Log.d("words", "Array Size: " + auctionItems.size());
                    OpenResultsPage();

                } catch (JSONException | InterruptedException e) {
                    Log.d("words", "Shit went down" + e);
                }
            }
        });



    }

    public ArrayList<AuctionItem> getAuctionItems() {
        return auctionItems;
    }

    public void OpenResultsPage()
    {
        Intent openIntent = new Intent(this, ResultsPage.class);
        startActivity(openIntent);
    }
    public void OpenAPIPage()
    {
        Intent openIntent = new Intent(this, APIEnterPage.class);
        startActivity(openIntent);
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

    public void setQuery(String query) {
        queryWords = new ArrayList<>();
        StringBuilder queryWord = new StringBuilder();
        boolean locked = false;
        for (char c : query.toLowerCase().toCharArray()) {
            if (locked)
                if (c == '"') {
                    if (queryWord.length() > 0)
                        queryWords.add(queryWord.toString());
                    queryWord = new StringBuilder();
                    locked = false;
                } else
                    queryWord.append(c);
            else {
                if (c == '"') {
                    if (queryWord.length() > 0)
                        queryWords.add(queryWord.toString());
                    queryWord = new StringBuilder();
                    locked = true;
                } else if (c == ' ') {
                    if (queryWord.length() > 0)
                        queryWords.add(queryWord.toString());
                    queryWord = new StringBuilder();
                } else
                    queryWord.append(c);
            }
        }
        if (queryWord.length() > 0)
            queryWords.add(queryWord.toString());
        if (queryWords.size() < 1)
            queryWords.add("");

        Log.d("words", queryWords.toString());

    }

}
