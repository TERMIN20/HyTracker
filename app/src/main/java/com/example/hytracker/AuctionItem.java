package com.example.hytracker;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AuctionItem {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private String itemName = " ";
    private String auctioneer = " ";
    private String itemLore = " ";
    private long startingBid = 0;
    private long highestBid = 0;
    private long price = 0;




    public AuctionItem(JSONObject jsonObject) {
        StrictMode.setThreadPolicy(policy);
        try {
            itemName = jsonObject.getString("item_name");
            itemLore = jsonObject.getString("item_lore");
            auctioneer = jsonObject.getString("auctioneer");
            startingBid = jsonObject.getLong("starting_bid");
            highestBid = jsonObject.getLong("highest_bid_amount");
            price = Math.min(startingBid, highestBid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(ArrayList<String> queryWords) {
        Log.d("contains", "Runs");
        int found = 0;
        for (String s : queryWords)
            if (itemName.toLowerCase().contains(s) || itemLore.toLowerCase().contains(s)) {
                Log.d("contains", "Does Contain One");
                found++;
            }
        return found == queryWords.size();
    }

    public String toString()
    {
        return this.itemName + "\t highestBid:" + this.highestBid;
    }

    public String getItemName() {
        return itemName;
    }

    public long getPrice() {
        return price;
    }

    public String getAuctioneer() {
        return auctioneer;
    }

    public String getItemLore() {
        return itemLore;
    }
}
