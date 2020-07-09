/*
 * *
 *  * Created by Vaibhav Andhare on 9/7/20 5:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.buyer;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.product.ProductListActivity;

public class BuyerHomeActivity extends AppCompatActivity {

    DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;

    SearchQueryInterface searchQueryInterface;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);
        Toolbar toolbar = findViewById(R.id.toolbar_buyer);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String UserFName = intent.getStringExtra("UserFirstName");

        drawer = findViewById(R.id.drawer_layout_buyer);
        NavigationView navigationView = findViewById(R.id.nav_view_buyer);

        View hView = navigationView.getHeaderView(0);
        TextView textView = hView.findViewById(R.id.tvNavBarTitle);
        textView.setText("Hello, " + UserFName);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_buyer_home, R.id.nav_profile, R.id.nav_orders, R.id.nav_wallet, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_buyer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQueryInterface.getQuery(query);
                startActivity(new Intent(BuyerHomeActivity.this, ProductListActivity.class));
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_buyer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public interface SearchQueryInterface {
        void getQuery(String query);
    }
}