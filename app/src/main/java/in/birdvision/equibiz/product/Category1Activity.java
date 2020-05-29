package in.birdvision.equibiz.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import in.birdvision.equibiz.R;

public class Category1Activity extends AppCompatActivity {

    CardView productCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        productCard = findViewById(R.id.product_card1);

        productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category1Activity.this, ProductActivity.class));
            }
        });

    }

}
