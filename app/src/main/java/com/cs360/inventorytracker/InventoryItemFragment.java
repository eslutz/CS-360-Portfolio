package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepo;

public class InventoryItemFragment extends Fragment {
    public static final String ARG_INVENTORY_ITEM_ID = "inventory_item_id";
    private InventoryItem mInventoryItem;

    public InventoryItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int inventoryItemId = 1;

        // Get the band ID from the fragment arguments
        Bundle args = getArguments();
        if (args != null) {
            inventoryItemId = args.getInt(ARG_INVENTORY_ITEM_ID);
        }

        // Get the selected band
        mInventoryItem = InventoryRepo.getInstance(requireContext())
                .getInventoryItem(inventoryItemId)
                .getValue();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory_item, container, false);

        if (mInventoryItem != null) {
            final EditText etItemName = rootView.findViewById(R.id.item_name_input);
            final EditText etItemQuantity = rootView.findViewById(R.id.item_qty_input);
            final TextView tvRemoveItemLink = rootView.findViewById(R.id.remove_item_link);

            etItemName.setText(mInventoryItem.getName());
            etItemQuantity.setText(mInventoryItem.getQuantity());
            tvRemoveItemLink.setOnTouchListener((v, event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tvRemoveItemLink.setShadowLayer(50, 0, 0, R.color.grey);
                        break;
                    case MotionEvent.ACTION_UP:
                        tvRemoveItemLink.setShadowLayer(0, 0, 0, R.color.grey);
                        v.performClick();
                        break;
                    default:
                        return false;
                }
                return true;
            });
        }
        return rootView;
    }
}