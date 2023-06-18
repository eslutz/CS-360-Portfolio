package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.viewmodel.InventoryListViewModel;

import java.util.List;

public class InventoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private InventoryAdapter mAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        InventoryListViewModel inventoryListViewModel = new ViewModelProvider(this)
                .get(InventoryListViewModel.class);

        mRecyclerView = rootView.findViewById(R.id.inventory_item_list);
        RecyclerView.LayoutManager gridLayoutManager =
                new GridLayoutManager(rootView.getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // Add a divider line between items in the RecyclerView
        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(divider);

        inventoryListViewModel.getInventoryList()
                .observe(getViewLifecycleOwner(), inventoryList -> {
            mAdapter = new InventoryAdapter(inventoryList);
            mRecyclerView.setAdapter(mAdapter);
        });

//        List<InventoryItem> inventoryList = inventoryListViewModel.getInventoryList();
//        mAdapter = new InventoryAdapter(inventoryList);
//        mRecyclerView.setAdapter(mAdapter);

        // Floating button to create a new inventory item
        final ImageView addNewItemButton = rootView.findViewById(R.id.add_new_item);
        Animation bounceAnimation = AnimationUtils.loadAnimation(
                rootView.getContext(),
                R.anim.bounce
        );
        addNewItemButton.setOnClickListener(v -> {
            addNewItemButton.startAnimation(bounceAnimation);
            Navigation.findNavController(rootView)
                    .navigate(R.id.fragment_inventory_item);
        });
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
}