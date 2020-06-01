package in.birdvision.equibiz.userInfo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import in.birdvision.equibiz.R;

public class VerificationActivity extends AppCompatActivity {

    TextInputLayout TIL_spinner_bank_name;
    AutoCompleteTextView editTextBankNameFilledExposedDropdown;
    String[] Bank_Names = new String[]{"Allahabad Bank", "Andhra Bank", "Axis Bank", "Bank of Bahrain and Kuwait",
            "Bank of Baroda - Corporate Banking", "Bank of Baroda - Retail Banking", "Bank of India", "Bank of Maharashtra", "Canara Bank",
            "Oriental Bank of Commerce", "State Bank of India", "UCO Bank", "Union Bank of India", "Yes Bank Ltd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        TIL_spinner_bank_name = findViewById(R.id.spinner_bank_name);
        editTextBankNameFilledExposedDropdown = findViewById(R.id.autocomplete_bank_name);

        ArrayAdapter adapter = new ArrayAdapter(VerificationActivity.this, R.layout.dropdown_menu, Bank_Names);
        editTextBankNameFilledExposedDropdown.setAdapter(adapter);

    }
}
