package in.birdvision.equibiz.buyer;

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

public class BuyerHomeActivity extends AppCompatActivity {

    DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;

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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_buyer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}