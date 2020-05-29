package in.birdvision.equibiz.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.product.Category1Activity;

public class AllCategoryActivity extends AppCompatActivity {

    CardView CC1, CC2, CC3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);

        CC1 = findViewById(R.id.CC1);
        CC2 = findViewById(R.id.CC2);
        CC3 = findViewById(R.id.CC3);

        CC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategoryActivity.this, Category1Activity.class));
            }
        });

        CC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategoryActivity.this, Category1Activity.class));
            }
        });

        CC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategoryActivity.this, Category1Activity.class));
            }
        });
    }
}
