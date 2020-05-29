package in.birdvision.equibiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import in.birdvision.equibiz.category.AllCategoryActivity;
import in.birdvision.equibiz.product.Category1Activity;
import in.birdvision.equibiz.product.ProductActivity;
import in.birdvision.equibiz.userInfo.UserProfile;

public class MainActivity extends AppCompatActivity {

    ImageView user_profile_image;
    TextView tv_moreCategories, category_circle_tv1;
    CardView poster_img, recommendation, trending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poster_img = findViewById(R.id.poster_img);
        poster_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });

        category_circle_tv1 = findViewById(R.id.category_tv);
        category_circle_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category1Activity.class));
            }
        });

        recommendation = findViewById(R.id.CV_recommendation1);
        recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });

        trending = findViewById(R.id.CV_trending1);
        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });

        user_profile_image = findViewById(R.id.img_user_profile);
        user_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserProfile.class));
            }
        });

        tv_moreCategories = findViewById(R.id.tv_more_categories);
        tv_moreCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllCategoryActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_shopping_basket:
            case R.id.menu_notification:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
