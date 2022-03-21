package com.example.hytracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    Context context;
    ArrayList<AuctionItem> auctionItemArrayList;
    RequestAPI requestAPI = new RequestAPI();

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        AuctionItem auctionItem = auctionItemArrayList.get(position);

        holder.itemName.setText(auctionItem.getItemName());
        holder.itemInfo.setText("Auctioneer: " + requestAPI.getName(auctionItem.getAuctioneer())  + "     Price: " + auctionItem.getPrice());

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
            itemName = itemView.findViewById(R.id.itemName);
            itemInfo = itemView.findViewById(R.id.itemText);

        }
        @Override
        public void onClick(View view) {

        }
    }

}
