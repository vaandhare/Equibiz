package in.birdvision.equibiz.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import in.birdvision.equibiz.R;

public class BusinessDetailsActivity extends AppCompatActivity {

    TextInputLayout business_type_spinner, business_nature_spinner;
    AutoCompleteTextView editTextBusinessTypeFilledExposedDropdown, editTextBusinessNatureFilledExposedDropdown;
    String[] BusinessType = {"Proprietorship", "Private Limited Company", "LLP", "HUF"};
    String[] BusinessNature = {"Exporter", "Importer", "Trader", "Aggregator", "Corporate Buyer", "Agent",
            "B2B Company", "Dealer", "Distributor", "National Distributor", "CNF Agent"};
    Button BTN_save_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        BTN_save_next = findViewById(R.id.btn_save_and_next);
        BTN_save_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessDetailsActivity.this, VerificationActivity.class));
            }
        });

        //Business Type Spinner
        business_type_spinner = findViewById(R.id.business_type_spinner);
        editTextBusinessTypeFilledExposedDropdown = findViewById(R.id.autocomplete_business_type);
        ArrayAdapter adapterBT = new ArrayAdapter(BusinessDetailsActivity.this, R.layout.dropdown_menu, BusinessType);
        editTextBusinessTypeFilledExposedDropdown.setAdapter(adapterBT);

        //Business Nature Spinner
        business_nature_spinner = findViewById(R.id.business_nature_spinner);
        editTextBusinessNatureFilledExposedDropdown = findViewById(R.id.autocomplete_business_nature);
        ArrayAdapter adapterBN = new ArrayAdapter(BusinessDetailsActivity.this, R.layout.dropdown_menu, BusinessNature);
        editTextBusinessNatureFilledExposedDropdown.setAdapter(adapterBN);

    }
}
