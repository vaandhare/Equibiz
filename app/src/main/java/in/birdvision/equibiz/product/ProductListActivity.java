package in.birdvision.equibiz.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.userInfo.UserProfile;

public class ProductListActivity extends AppCompatActivity {

    CardView productCard;
    ImageView user_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        user_profile = findViewById(R.id.img_user_profile);
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductListActivity.this, UserProfile.class));
            }
        });

        productCard = findViewById(R.id.product_card1);
        productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductListActivity.this, ProductActivity.class));
            }
        });
    }
}
