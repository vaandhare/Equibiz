package in.birdvision.equibiz.seller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.seller.product.ProductDetailsActivity;
import in.birdvision.equibiz.seller.ui.product_listing.ProductListingFragment;

public class SellerHomeActivity extends AppCompatActivity implements ProductListingFragment.adminFragmentToProductDetails {

    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        Toolbar toolbar = findViewById(R.id.toolbar_seller);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String UserFName = intent.getStringExtra("UserFirstName");

        DrawerLayout drawer = findViewById(R.id.drawer_layout_seller);
        NavigationView navigationView = findViewById(R.id.nav_view_seller);

        View hView = navigationView.getHeaderView(0);
        TextView textView = hView.findViewById(R.id.tvNavBarTitle);
        textView.setText("Hello, " + UserFName);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_product_list, R.id.nav_product_listing, R.id.nav_my_inventory,
                R.id.nav_pending_orders)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_seller);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_seller);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void adminProductData(String userId, String productID, String authToken) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("user_id", userId);
        intent.putExtra("product_id", productID);
        intent.putExtra("authToken", authToken);
        startActivity(intent);
    }
}