package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepo;
import com.cs360.inventorytracker.viewmodel.InventoryItemViewModel;

public class InventoryItemFragment extends Fragment {
    public static final String ARG_INVENTORY_ITEM_ID = "inventory_item_id";
    private Long mId;
    private String mName;
    private int mQty;

    public InventoryItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int inventoryItemId = 1;

        // Get the inventory item ID from the fragment arguments
        Bundle args = getArguments();
        if (args != null) {
            mId = args.getLong(ARG_INVENTORY_ITEM_ID);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory_item, container, false);

        final EditText itemName = rootView.findViewById(R.id.item_name_input);
        final EditText itemQuantity = rootView.findViewById(R.id.item_qty_input);
        final TextView removeItemLink = rootView.findViewById(R.id.remove_item_link);
        final Button decreaseQty = rootView.findViewById(R.id.item_qty_decrease);
        final Button increaseQty = rootView.findViewById(R.id.item_qty_increase);
        final Button saveItem = rootView.findViewById(R.id.save_item);

        // Inventory item already exists
        if (mId != 0) {
            // Get the selected inventory item
            InventoryRepo.getInstance(requireContext())
                    .getInventoryItem(mId)
                    .observe(getViewLifecycleOwner(), inventoryItem -> {
                        mName = inventoryItem.getName();
                        itemName.setText(mName);
                        mQty = inventoryItem.getQuantity();
                        itemQuantity.setText(mQty);
                    });

            removeItemLink.setVisibility(View.VISIBLE);
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
        } else {
            // Create new inventory item.
            removeItemLink.setVisibility(View.GONE);
        }


        decreaseQty.setOnClickListener(v -> {
            mQty--;
            itemQuantity.setText(String.valueOf(mQty));
        });

        increaseQty.setOnClickListener(v -> {
            mQty++;
            itemQuantity.setText(String.valueOf(mQty));
        });

        saveItem.setOnClickListener(v -> {
            InventoryItemViewModel inventoryItemViewModel = new ViewModelProvider(this)
                    .get(InventoryItemViewModel.class);

            if (mId != 0) {
                inventoryItemViewModel.updateInventoryItem(new InventoryItem(mName, mQty, mId));
            } else {
                inventoryItemViewModel.addInventoryItem(new InventoryItem(mName, mQty, null));
            }


        });

        return rootView;
    }
}