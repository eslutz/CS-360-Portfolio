package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.viewmodel.InventoryListViewModel;

import java.util.List;

public class InventoryFragment extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected inventory item ID
            int selectedInventoryItemId = (int) itemView.getTag();
            Bundle args = new Bundle();
            args.putInt(InventoryItemFragment.ARG_INVENTORY_ITEM_ID, selectedInventoryItemId);
            Navigation.findNavController(itemView).navigate(R.id.open_inventory_item_fragment, args);
        };

        RecyclerView recyclerView = rootView.findViewById(R.id.inventory_item_list);
        InventoryListViewModel mInventoryListViewModel = new ViewModelProvider(this)
                .get(InventoryListViewModel.class);
        mInventoryListViewModel
                .getInventoryList()
                .observe(getViewLifecycleOwner(), inventoryList ->
                        recyclerView.setAdapter(
                                new InventoryItemAdapter(inventoryList, onClickListener)
                        )
                );
        // Add a divider line between items in a RecyclerView
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        // Floating button to create a new inventory item
        final ImageView addNewItemButton = rootView.findViewById(R.id.add_new_item);
        addNewItemButton.setOnClickListener(v ->
                Navigation.findNavController(rootView)
                        .navigate(R.id.fragment_inventory_item)
        );
        addNewItemButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    addNewItemButton.setColorFilter(R.color.grey_primary_light);
                    break;
                case MotionEvent.ACTION_UP:
                    addNewItemButton.clearColorFilter();
                    v.performClick();
                    break;
                default:
                    return false;
            }
            return true;
        });

        // todo: SMS notification consent alert

        return rootView;
    }

    private class InventoryItemAdapter extends RecyclerView.Adapter<InventoryItemHolder> {

        private final List<InventoryItem> mInventoryList;
        private final View.OnClickListener mOnClickListener;

        public InventoryItemAdapter(List<InventoryItem> inventoryList,
                                    View.OnClickListener onClickListener) {
            mInventoryList = inventoryList;
            mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public InventoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new InventoryItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(InventoryItemHolder holder, int position) {
            InventoryItem inventoryItem = mInventoryList.get(position);
            holder.bind(inventoryItem);
            holder.itemView.setTag(inventoryItem.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mInventoryList.size();
        }
    }

    private static class InventoryItemHolder extends RecyclerView.ViewHolder {

        private final TextView mItemNameTextView;
        private final TextView mItemQuantityTextView;

        public InventoryItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_inventory_item, parent, false));
            mItemNameTextView = itemView.findViewById(R.id.inventory_item_name);
            mItemQuantityTextView = itemView.findViewById(R.id.inventory_item_quantity);
        }

        public void bind(InventoryItem inventoryItem) {
            mItemNameTextView.setText(inventoryItem.getName());
            mItemQuantityTextView.setText(inventoryItem.getQuantity());
        }
    }
}