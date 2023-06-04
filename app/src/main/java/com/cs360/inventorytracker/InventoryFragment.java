package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class InventoryFragment extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.inventory_item_list);
        // Add a divider line between items in a RecyclerView
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        final ImageView addNewItemButton = rootView.findViewById(R.id.add_new_item);
        addNewItemButton.setOnClickListener(v -> Navigation.findNavController(rootView).navigate(R.id.fragment_inventory_item));
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

        // Create SMS notification consent alert
        AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
        builder.setTitle("Allow SMS notifications?");
        builder.setMessage("Do you want to receive SMS notifications when an inventory item quantity reaches zero?");
        builder.setPositiveButton("Yes",
                (DialogInterface.OnClickListener) (dialog, which) ->
                setSmsNotification());
        builder.setNegativeButton("No",
                (DialogInterface.OnClickListener) (dialog, which) ->
                dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        return rootView;
    }

    public void setSmsNotification(){}
}