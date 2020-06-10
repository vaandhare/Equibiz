package in.birdvision.equibiz.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.orders.ConfirmOrderActivity;

public class ProductActivity extends AppCompatActivity {

    Button addToCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initializeIDs();

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, ConfirmOrderActivity.class));
            }
        });

    }

    private void initializeIDs() {
        addToCartBtn = findViewById(R.id.btn_pro_book_now);
    }
}
