package com.example.hytracker;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ItemsViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {

        }
    }

}
