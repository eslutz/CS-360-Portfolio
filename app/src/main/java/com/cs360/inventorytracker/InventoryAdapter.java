package com.cs360.inventorytracker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.cs360.inventorytracker.model.InventoryItem;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<InventoryItem> mInventoryList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemNameTextView;
        private final TextView itemQuantityTextView;

        public ViewHolder(View v) {
            super(v);
            Animation bounceAnimation = AnimationUtils.loadAnimation(
                    v.getContext(),
                    R.anim.bounce
            );

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(bounceAnimation);
                    Navigation.findNavController(v)
                            .navigate(R.id.fragment_inventory_item);

                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
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

        Log.d(TAG, "Element " + position + " set.");
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mInventoryList.size();
    }

}
