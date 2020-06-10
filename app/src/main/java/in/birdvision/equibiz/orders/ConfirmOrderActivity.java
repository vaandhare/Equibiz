package in.birdvision.equibiz.orders;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.product.ProductActivity;
import in.birdvision.equibiz.product.ProductListActivity;

public class ConfirmOrderActivity extends AppCompatActivity {

    Button cancelBtn, preOrderNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initializeIDs();

    }

    private void initializeIDs() {
        cancelBtn = findViewById(R.id.btn_co_cancel);
        preOrderNowBtn = findViewById(R.id.btn_co_pro_order_now);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmOrderActivity.this, ProductActivity.class));
            }
        });

        preOrderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.success_dialog_activity, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ConfirmOrderActivity.this, ProductListActivity.class));
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(Dialog.BUTTON_NEGATIVE).
                setBackgroundColor(Color.parseColor("#FBFBC3"));
    }
}
