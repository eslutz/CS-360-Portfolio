package com.cs360.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserAccountLocalStore userAccountLocalStore = new UserAccountLocalStore(this);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        Set<Integer> topLevelDestinations = new HashSet<Integer>() {{
            add(R.id.fragment_login);
            add(R.id.fragment_inventory);
        }};

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(topLevelDestinations).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph);

            // Check if user is already logged in
            // If they are then go to inventory fragment
            // Otherwise go to login fragment
            if (userAccountLocalStore.getIsUserLoggedIn()) {
                navGraph.setStartDestination(R.id.fragment_inventory);
            } else {
                navGraph.setStartDestination(R.id.fragment_login);
            }
            navController.setGraph(navGraph);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}