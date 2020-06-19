package in.birdvision.equibiz.orders;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.product.ProductActivity;
import in.birdvision.equibiz.product.ProductListActivity;

public class ConfirmOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button cancelBtn, preOrderNowBtn;
    Spinner spinnerLocation;

    String[] pickupLocations = {"Mumbai", "Delhi", "Uttar Pradesh", "Punjab", "Gujrat", "Madya Pradesh", "Goa",
            "Pune", "Aizawl"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initializeIDs();

    }

    private void initializeIDs() {

        spinnerLocation = findViewById(R.id.spinner_pickup_locations);
        spinnerLocation.setOnItemSelectedListener(ConfirmOrderActivity.this);

        ArrayAdapter<String> locations = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pickupLocations);
        locations.setDropDownViewResource(R.layout.dropdown_menu);
        spinnerLocation.setAdapter(locations);

        cancelBtn = findViewById(R.id.btn_co_cancel);
        preOrderNowBtn = findViewById(R.id.btn_co_pro_order_now);

        cancelBtn.setOnClickListener(v -> startActivity(new Intent(ConfirmOrderActivity.this, ProductActivity.class)));

        preOrderNowBtn.setOnClickListener(v -> showCustomDialog());
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.success_dialog_activity, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.close, (dialog, which)
                -> startActivity(new Intent(ConfirmOrderActivity.this, ProductListActivity.class)));

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(Dialog.BUTTON_NEGATIVE).
                setBackgroundColor(Color.parseColor("#FBFBC3"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
