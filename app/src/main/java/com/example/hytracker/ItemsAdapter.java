package com.example.hytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    Context context;
    ArrayList<AuctionItem> auctionItemArrayList;

    public ItemsAdapter(Context context, ArrayList<AuctionItem> auctionItemArrayList) {
        this.context = context;
        this.auctionItemArrayList = auctionItemArrayList;
    }





    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        AuctionItem auctionItem = auctionItemArrayList.get(position);

        holder.itemName.setText(auctionItem.getItemName());
        holder.itemInfo.setText("Starting Bid: " + auctionItem.getStartingBid() + "     Highest Bid: " + auctionItem.getHighestBid());

    }

    @Override
    public int getItemCount() {
        return auctionItemArrayList.size();
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView itemName;
        TextView itemInfo;

        ItemsViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemName = itemView.findViewById(R.id.itemText);
            itemInfo = itemView.findViewById(R.id.itemInfo);

        }
        @Override
        public void onClick(View view) {

        }
    }

}
