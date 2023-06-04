package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

public class InventoryItemFragment extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory_item, container, false);

        final TextView removeItemLink = rootView.findViewById(R.id.remove_item_link);
        removeItemLink.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    removeItemLink.setShadowLayer(50, 0, 0, R.color.grey);
                    break;
                case MotionEvent.ACTION_UP:
                    removeItemLink.setShadowLayer(0, 0, 0, R.color.grey);
                    v.performClick();
                    break;
                default:
                    return false;
            }
            return true;
        });

        return rootView;
    }
}