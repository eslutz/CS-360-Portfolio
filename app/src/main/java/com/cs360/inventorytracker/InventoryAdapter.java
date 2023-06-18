package com.cs360.inventorytracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.cs360.inventorytracker.model.InventoryItem;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private final List<InventoryItem> mInventoryList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemNameTextView;
        private final TextView itemQuantityTextView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> {
                Long selectedItemId = (Long) v1.getTag();
                Bundle args = new Bundle();
                args.putLong(InventoryItemFragment.ARG_INVENTORY_ITEM_ID, selectedItemId);

                Navigation.findNavController(v1)
                    .navigate(R.id.fragment_inventory_item, args);

                Log.d("ViewHolder","Element " + getAdapterPosition() + " clicked.");
            });
            itemNameTextView = itemView.findViewById(R.id.inventory_item_name);
            itemQuantityTextView = itemView.findViewById(R.id.inventory_item_quantity);
        }

        public TextView getItemNameTextView() {
            return itemNameTextView;
        }

        public TextView getItemQuantityTextView() {
            return itemQuantityTextView;
        }
    }

    public InventoryAdapter(List<InventoryItem> inventoryList) {
        mInventoryList = inventoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_inventory_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getItemNameTextView().setText(mInventoryList.get(position).getName());
        viewHolder.getItemQuantityTextView().setText(String.valueOf(mInventoryList.get(position).getQuantity()));
        viewHolder.itemView.setTag(mInventoryList.get(position).getId());

        Log.d("BindViewHolder", "Element " + position + " set.");
    }

    @Override
    public int getItemCount() {
        return mInventoryList.size();
    }

}
