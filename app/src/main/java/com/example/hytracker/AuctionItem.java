package com.example.hytracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AuctionItem {
    private String itemName = " ";
    private String auctioneer = " ";
    private String itemLore = " ";
    private long startingBid = 0;
    private long highestBid = 0;

    public AuctionItem(JSONObject jsonObject) {
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
}
