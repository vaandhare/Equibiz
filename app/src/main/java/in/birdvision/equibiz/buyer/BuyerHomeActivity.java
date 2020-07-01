package in.birdvision.equibiz.buyer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.product.ProductActivity;
import in.birdvision.equibiz.buyer.ui.product.ProductListFragment;

import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_COLOR;
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_INTERNAL_MEMORY;
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_PRO_ID;
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_RAM;

public class BuyerHomeActivity extends AppCompatActivity implements ProductListFragment.fragmentToActivity {

    DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_product_list, R.id.nav_profile, R.id.nav_orders)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void getProductData(String productColor, String productInternalMemory, String productID, String productRam) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(EXTRA_COLOR, productColor);
        intent.putExtra(EXTRA_INTERNAL_MEMORY, productInternalMemory);
        intent.putExtra(EXTRA_PRO_ID, productID);
        intent.putExtra(EXTRA_RAM, productRam);
        startActivity(intent);
    }
}