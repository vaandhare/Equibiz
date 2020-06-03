package in.birdvision.equibiz.userInfo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.birdvision.equibiz.R;
import in.birdvision.equibiz.product.ProductListActivity;

public class VerificationActivity extends AppCompatActivity {

    private static final String PAN_CARD_MATCHER = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    private static final String GSTIN_NUMBER = "[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}";
    AutoCompleteTextView editTextBankNameFilledExposedDropdown;
    private static final String IFSC_CODE = "[A-Z]{4}[0-9]{6,7}";
    TextInputLayout TIL_spinner_bank_name, TIL_pan_card, TIL_gstin_number, TIL_ifsc_code;
    Button BTN_gstin_img, BTN_pan_img, BTN_save_submit, BTN_previous;
    String et_value_pan_card, et_value_gstin, et_ifsc_code;
    String imagePath;
    TextView TV_gstin_file_name, TV_pan_file_name;

    String[] Bank_Names = new String[]{"Allahabad Bank", "Andhra Bank", "Axis Bank", "Bank of Bahrain and Kuwait",
            "Bank of Baroda - Corporate Banking", "Bank of Baroda - Retail Banking", "Bank of India", "Bank of Maharashtra", "Canara Bank",
            "Oriental Bank of Commerce", "State Bank of India", "UCO Bank", "Union Bank of India", "Yes Bank Ltd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        initializeIDs();
        BTN_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerificationActivity.this, BusinessDetailsActivity.class));
            }
        });

        Objects.requireNonNull(TIL_pan_card.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_value_pan_card = Objects.requireNonNull(TIL_pan_card.getEditText()).getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                TIL_pan_card.setErrorEnabled(false);
            }
        });

        Objects.requireNonNull(TIL_gstin_number.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_value_gstin = TIL_gstin_number.getEditText().getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                TIL_gstin_number.setErrorEnabled(false);
            }
        });

        Objects.requireNonNull(TIL_ifsc_code.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_ifsc_code = TIL_ifsc_code.getEditText().getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                TIL_ifsc_code.setErrorEnabled(false);
            }
        });

//        BTN_gstin_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestImage();
//                TV_gstin_file_name.setText(imagePath);
//            }
//        });
//
//        BTN_pan_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestImage();
//                TV_pan_file_name.setText(imagePath);
//            }
//        });

        BTN_save_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_value_pan_card = TIL_pan_card.getEditText().getText().toString();
                et_value_gstin = TIL_gstin_number.getEditText().getText().toString();
                et_ifsc_code = TIL_ifsc_code.getEditText().getText().toString();

                if (patternMatchers()) {
                    startActivity(new Intent(VerificationActivity.this, ProductListActivity.class));
                }
            }
        });
    }

    private boolean patternMatchers() {

        Pattern gstin_pattern = Pattern.compile(GSTIN_NUMBER);
        Matcher gstin_matcher = gstin_pattern.matcher(this.et_value_gstin);

        Pattern pan_pattern = Pattern.compile(PAN_CARD_MATCHER);
        Matcher pan_matcher = pan_pattern.matcher(this.et_value_pan_card);

        Pattern ifsc_pattern = Pattern.compile(IFSC_CODE);
        Matcher ifsc_matcher = ifsc_pattern.matcher(this.et_ifsc_code);

        if (!gstin_matcher.matches() || this.et_value_gstin.isEmpty()) {
            TIL_gstin_number.setError("Invalid GSTIN Number");
            TIL_gstin_number.findFocus();
            return false;
        }

        if (!pan_matcher.matches() || this.et_value_pan_card.isEmpty()) {
            TIL_pan_card.setError("Invalid PAN Number");
            TIL_pan_card.findFocus();
            return false;
        }

        if (!ifsc_matcher.matches() || this.et_ifsc_code.isEmpty()) {
            TIL_ifsc_code.setError("Invalid IFSC Code");
            TIL_ifsc_code.findFocus();
            return false;
        }
        return true;
    }

    private void requestImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImage = data.getData();

            String filePath = getPath(selectedImage);
            String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);

            if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
                //FINE
                Toast.makeText(VerificationActivity.this, imagePath, Toast.LENGTH_SHORT).show();
            } else {
                //NOT IN REQUIRED FORMAT;
            }
        }

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);
        return cursor.getString(column_index);
    }

    private void initializeIDs() {
        TIL_spinner_bank_name = findViewById(R.id.spinner_bank_name);
        editTextBankNameFilledExposedDropdown = findViewById(R.id.autocomplete_bank_name);
        ArrayAdapter adapter = new ArrayAdapter<>(VerificationActivity.this, R.layout.dropdown_menu, Bank_Names);
        editTextBankNameFilledExposedDropdown.setAdapter(adapter);

        TIL_pan_card = findViewById(R.id.etv_pan_card_number);
        TIL_pan_card.setErrorEnabled(false);
        TIL_gstin_number = findViewById(R.id.etv_gstin_number);
        TIL_gstin_number.setErrorEnabled(false);
        TIL_ifsc_code = findViewById(R.id.etv_ifsc_number);
        TIL_ifsc_code.setErrorEnabled(false);

        BTN_gstin_img = findViewById(R.id.btn_gstin_upload);
        BTN_pan_img = findViewById(R.id.btn_pan_card_upload);
        BTN_save_submit = findViewById(R.id.btn_save_and_submit_va);
        BTN_previous = findViewById(R.id.btn_previous_va);

        TV_gstin_file_name = findViewById(R.id.tv_select_gstin_file);
        TV_pan_file_name = findViewById(R.id.tv_select_pan_card_file);

    }
}
