package com.example.hytracker;

import android.os.StrictMode;

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


    public AuctionItem(JSONObject jsonObject) {
        StrictMode.setThreadPolicy(policy);
        try {
            itemName = jsonObject.getString("item_name");
            itemLore = jsonObject.getString("item_lore");
            auctioneer = jsonObject.getString("auctioneer");
            startingBid = jsonObject.getLong("starting_bid");
            highestBid = jsonObject.getLong("highest_bid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(ArrayList<String> queryWords) {
        int found = 0;
        for (String s : queryWords)
            if (itemName.toLowerCase().contains(s) || itemLore.toLowerCase().contains(s))
                found++;
        return found == queryWords.size();
    }

    public String toString()
    {
        return this.itemName + "\t highestBid:" + this.highestBid;
    }

    public String getItemName() {
        return itemName;
    }

    public String getAuctioneer() {
        return auctioneer;
    }

    public String getItemLore() {
        return itemLore;
    }

    public long getStartingBid() {
        return startingBid;
    }

    public long getHighestBid() {
        return highestBid;
    }

}
