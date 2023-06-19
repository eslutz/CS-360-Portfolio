package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.viewmodel.InventoryItemViewModel;

public class InventoryItemFragment extends Fragment {
    public static final String ARG_INVENTORY_ITEM_ID = "inventory_item_id";
    private InventoryItemViewModel mInventoryItemViewModel;
    private Long mId;
    private String mName;
    private int mQty;

    public InventoryItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        final ImageView decreaseQty = rootView.findViewById(R.id.item_qty_decrease);
        final ImageView increaseQty = rootView.findViewById(R.id.item_qty_increase);
        final Button saveItem = rootView.findViewById(R.id.save_item);

        mInventoryItemViewModel = new ViewModelProvider(this)
                .get(InventoryItemViewModel.class);

        // Inventory item already exists
        if (mId != null) {
            // Get the selected inventory item
            mInventoryItemViewModel
                .getInventoryItem(mId)
                .observe(getViewLifecycleOwner(), inventoryItem -> {
                    itemName.setText(inventoryItem.getName());
                    itemQuantity.setText(String.valueOf(inventoryItem.getQuantity()));
                });

            removeItemLink.setVisibility(View.VISIBLE);
            removeItemLink.setOnClickListener(v ->
                mInventoryItemViewModel.deleteInventoryItemById(mId)
            );
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
            removeItemLink.setVisibility(View.INVISIBLE);
            itemQuantity.setText(String.valueOf(mQty));
        }

        decreaseQty.setOnClickListener(v -> {
            mQty = Integer.parseInt(itemQuantity.getText().toString());
            if (mQty > 0) {
                mQty--;
            }
            itemQuantity.setText(String.valueOf(mQty));
        });

        increaseQty.setOnClickListener(v -> {
            mQty = Integer.parseInt(itemQuantity.getText().toString());
            mQty++;
            itemQuantity.setText(String.valueOf(mQty));
        });

        saveItem.setOnClickListener(v -> {
            mName = itemName.getText().toString().trim();
            String qtyString = itemQuantity.getText().toString();
            if (qtyString.isEmpty()) {
                Toast.makeText(
                    rootView.getContext(),
                    "Item quantity cannot be empty",
                    Toast.LENGTH_LONG)
                    .show();
            } else if (mName.isEmpty()) {
                Toast.makeText(
                    rootView.getContext(),
                    "Item name cannot be empty",
                    Toast.LENGTH_LONG)
                    .show();
            } else {
                mQty = Integer.parseInt(qtyString);
                if (mId != null) {
                    mInventoryItemViewModel.updateInventoryItem(new InventoryItem(mName, mQty, mId));
                    Navigation.findNavController(rootView)
                            .navigate(R.id.fragment_inventory);
                } else {
                    mInventoryItemViewModel.addInventoryItem(
                            new InventoryItem(mName, mQty, null)
                    );
                    Navigation.findNavController(rootView)
                            .navigate(R.id.fragment_inventory);
                }
            }
        });

        return rootView;
    }
}