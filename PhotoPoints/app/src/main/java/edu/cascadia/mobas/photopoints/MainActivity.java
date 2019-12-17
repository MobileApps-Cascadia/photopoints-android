package edu.cascadia.mobas.photopoints;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_scan,
                R.id.navigation_map,
                R.id.navigation_photopoints)
                .build();

        mBottomNavView = findViewById(R.id.bottom_nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        /*Add a destination changed listener to hide the bottom nav-bar when we
        * are not navigating to a top-level activity (Map, PhotoPoints or scan.
        */
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.navigation_map
                        || destination.getId() == R.id.navigation_scan
                        || destination.getId() == R.id.navigation_photopoints){
                    mBottomNavView.setVisibility(View.VISIBLE);
                }
                else{
                    mBottomNavView.setVisibility(View.GONE);
                }
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the top menu action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp();
    }
}
