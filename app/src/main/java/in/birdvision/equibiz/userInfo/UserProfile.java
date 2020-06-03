package in.birdvision.equibiz.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.orders.OrderActivity;
import in.birdvision.equibiz.product.ProductActivity;

public class UserProfile extends AppCompatActivity {

    ImageView backImg;
    TextView order_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        order_history = findViewById(R.id.UP_orders_history);
        order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, OrderActivity.class));
            }
        });

        backImg = findViewById(R.id.img_back_up);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ProductActivity.class));
            }
        });

    }
}
