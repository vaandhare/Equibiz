package in.birdvision.equibiz.userInfo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
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

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.userInfo.BankDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.UploadDocuments;
import in.birdvision.equibiz.API.ifsc_api.IFSC;
import in.birdvision.equibiz.API.ifsc_api.IFSC_API_Interface;
import in.birdvision.equibiz.API.ifsc_api.RasorpayApiService;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.product.ProductListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class VerificationActivity extends AppCompatActivity {

    private static final String PAN_CARD_MATCHER = "[A-Z]{5}[0-9]{4}[A-Z]";
    private static final String GSTIN_NUMBER = "[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z][1-9A-Za-z][Z][0-9a-zA-Z]";
    AutoCompleteTextView etvACBankName, etvACBankBranchName;
    private static final String IFSC_CODE = "[A-Z]{4}[0-9]{6,7}";
    TextInputLayout TIL_spinner_bank_name, TIL_spinner_bank_branch_name, TIL_pan_card, TIL_gstin_number,
            TIL_ifsc_code, TIL_account_no, TIL_acc_holder_name;
    Button BTN_gstin_img, BTN_pan_img, BTN_save_submit, BTN_previous, BTN_verify_ifsc;
    String et_value_pan_card, et_value_gstin, et_ifsc_code;
    String encryptedAccHolderName, encryptedAccNum, encryptedBankBranch, encryptedBankCity, encryptedBankName,
            encryptedIfscCode, encryptedRole, encryptedUserObjId, encryptedSpinnerBankBranch, encryptedWhichType,
            encryptedLabel;
    byte[] cipherAccHolderName, cipherAccNum, cipherBankBranch, cipherBankCity, cipherBankName,
            cipherIfscCode, cipherUserObjId, cipherSpinnerBankBranch, cipherWhichType,
            cipherLabel;
    TextView TV_gstin_file_name, TV_pan_file_name, TV_bank_branch_details;

    String str_bank_city, str_bank_address, str_bank_branch, str_bank_name;

    IFSC_API_Interface IFSCApiInterface;
    Equibiz_API_Interface equibiz_api_interface;
    ProgressDialog progressDialog;
    Integer whichType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        initializeIDs();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        progressDialog = new ProgressDialog(VerificationActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        BTN_save_submit.setOnClickListener(v -> {
            progressDialog.show();
            registerBankDetails();
        });

    }

    private void registerBankDetails() {
        String acc_no = Objects.requireNonNull(TIL_account_no.getEditText()).getText().toString();
        String acc_holder_name = Objects.requireNonNull(TIL_acc_holder_name.getEditText()).getText().toString();
        String spinner_bank_branch = Objects.requireNonNull(TIL_spinner_bank_branch_name.getEditText()).getText().toString();

        SharedPreferences mySharedPreferences = this.getSharedPreferences("User_ObjID", Context.MODE_PRIVATE);
        String userObjId = mySharedPreferences.getString("UserObjID", "xxxxx");
        encryptedRole = mySharedPreferences.getString("encryptedUserRole", "xxxxx");

        try {
            cipherAccHolderName = encrypt(acc_holder_name.getBytes());
            encryptedAccHolderName = encoderFunction(cipherAccHolderName);

            cipherAccNum = encrypt(acc_no.getBytes());
            encryptedAccNum = encoderFunction(cipherAccNum);

            cipherBankBranch = encrypt(str_bank_branch.getBytes());
            encryptedBankBranch = encoderFunction(cipherBankBranch);

            cipherBankCity = encrypt(str_bank_city.getBytes());
            encryptedBankCity = encoderFunction(cipherBankCity);

            cipherBankName = encrypt(str_bank_name.getBytes());
            encryptedBankName = encoderFunction(cipherBankName);

            cipherIfscCode = encrypt(et_ifsc_code.getBytes());
            encryptedIfscCode = encoderFunction(cipherIfscCode);

            cipherUserObjId = encrypt(userObjId.getBytes());
            encryptedUserObjId = encoderFunction(cipherUserObjId);

            cipherSpinnerBankBranch = encrypt(spinner_bank_branch.getBytes());
            encryptedSpinnerBankBranch = encoderFunction(cipherSpinnerBankBranch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final BankDetailsResponse bankDetailsResponse = new BankDetailsResponse(encryptedAccHolderName,
                encryptedAccNum, encryptedSpinnerBankBranch, encryptedBankCity, encryptedBankName, encryptedBankBranch,
                encryptedIfscCode, encryptedRole, encryptedUserObjId);
        Call<BankDetailsResponse> bankDetailsResponseCall = equibiz_api_interface.bankDetailsResponse(bankDetailsResponse);

        bankDetailsResponseCall.enqueue(new Callback<BankDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<BankDetailsResponse> call, @NotNull Response<BankDetailsResponse> response) {
                progressDialog.dismiss();
                BankDetailsResponse response1 = response.body();
                assert response1 != null;
                Toast.makeText(VerificationActivity.this, response1.getBankverified(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(VerificationActivity.this, ProductListActivity.class));
            }

            @Override
            public void onFailure(@NotNull Call<BankDetailsResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(VerificationActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(VerificationActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean IFSCPatternMatcher() {
        Pattern ifsc_pattern = Pattern.compile(IFSC_CODE);
        Matcher ifsc_matcher = ifsc_pattern.matcher(this.et_ifsc_code);
        if (!ifsc_matcher.matches() || this.et_ifsc_code.isEmpty()) {
            TIL_ifsc_code.setError("Invalid IFSC Code");
            TIL_ifsc_code.findFocus();
            return false;
        }
        return true;
    }

    private boolean PanPatternMatchers() {
        Pattern pan_pattern = Pattern.compile(PAN_CARD_MATCHER);
        Matcher pan_matcher = pan_pattern.matcher(this.et_value_pan_card);

        if (!pan_matcher.matches() || this.et_value_pan_card.isEmpty()) {
            TIL_pan_card.setError("Invalid PAN Number");
            TIL_pan_card.findFocus();
            return false;
        }
        return true;
    }

    private boolean GstinPatternMatchers() {
        Pattern gstin_pattern = Pattern.compile(GSTIN_NUMBER);
        Matcher gstin_matcher = gstin_pattern.matcher(this.et_value_gstin);

        if (!gstin_matcher.matches() || this.et_value_gstin.isEmpty()) {
            TIL_gstin_number.setError("Invalid GSTIN Number");
            TIL_gstin_number.findFocus();
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

    private void uploadImage(String encryptedLabel, String encryptedWhichType, byte[] imagePath) {
        final UploadDocuments uploadDocuments = new UploadDocuments(encryptedRole, encryptedUserObjId, encryptedLabel, encryptedWhichType, imagePath);
        Call<UploadDocuments> documentsCall = equibiz_api_interface.uploadDocuments(uploadDocuments);
        documentsCall.enqueue(new Callback<UploadDocuments>() {
            @Override
            public void onResponse(@NotNull Call<UploadDocuments> call, @NotNull Response<UploadDocuments> response) {
                UploadDocuments uploadDocuments1 = response.body();
                assert uploadDocuments1 != null;
                Toast.makeText(VerificationActivity.this, uploadDocuments1.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<UploadDocuments> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(VerificationActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(VerificationActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 250, byteArrayOutputStream);
                byte[] imagePath = byteArrayOutputStream.toByteArray();
                uploadImage(encryptedLabel, encryptedWhichType, imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    private void initializeIDs() {
        Resources resources = getResources();

        TIL_spinner_bank_name = findViewById(R.id.spinner_bank_name);
        etvACBankName = findViewById(R.id.autocomplete_bank_name);
        ArrayAdapter adapter = new ArrayAdapter<>(VerificationActivity.this, R.layout.dropdown_menu, resources.getStringArray(R.array.bank_names));
        etvACBankName.setAdapter(adapter);

        TIL_spinner_bank_branch_name = findViewById(R.id.spinner_bank_branch_name);
        etvACBankBranchName = findViewById(R.id.autocomplete_bank_branch_name);
        ArrayAdapter adapter1 = new ArrayAdapter<>(VerificationActivity.this, R.layout.dropdown_menu, resources.getStringArray(R.array.bankBranchNames));
        etvACBankBranchName.setAdapter(adapter1);

        TIL_pan_card = findViewById(R.id.etv_pan_card_number);
        TIL_pan_card.setErrorEnabled(false);
        TIL_gstin_number = findViewById(R.id.etv_gstin_number);
        TIL_gstin_number.setErrorEnabled(false);
        TIL_ifsc_code = findViewById(R.id.etv_ifsc_number);
        TIL_ifsc_code.setErrorEnabled(false);

        TIL_acc_holder_name = findViewById(R.id.etv_account_holder_name);
        TIL_account_no = findViewById(R.id.etv_account_number);

        BTN_gstin_img = findViewById(R.id.btn_gstin_upload);
        BTN_pan_img = findViewById(R.id.btn_pan_card_upload);
        BTN_save_submit = findViewById(R.id.btn_save_and_submit_va);
        BTN_previous = findViewById(R.id.btn_previous_va);
        BTN_verify_ifsc = findViewById(R.id.btn_verify_ifsc);

        TV_gstin_file_name = findViewById(R.id.tv_select_gstin_file);
        TV_pan_file_name = findViewById(R.id.tv_select_pan_card_file);
        TV_bank_branch_details = findViewById(R.id.tv_av_bank_branch_details);

        BTN_previous.setOnClickListener(v -> startActivity(new
                Intent(VerificationActivity.this, BusinessDetailsActivity.class)));

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
                BTN_verify_ifsc.setVisibility(View.VISIBLE);
                TV_bank_branch_details.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                TIL_ifsc_code.setErrorEnabled(false);
            }
        });

        BTN_gstin_img.setOnClickListener(v -> {
            whichType = 1;
            et_value_gstin = TIL_gstin_number.getEditText().getText().toString();
            if (GstinPatternMatchers()) {
                try {
                    cipherLabel = encrypt(et_value_gstin.getBytes());
                    encryptedLabel = encoderFunction(cipherLabel);

                    cipherWhichType = encrypt(whichType.toString().getBytes());
                    encryptedWhichType = encoderFunction(cipherWhichType);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                requestImage();
            }
        });

        BTN_pan_img.setOnClickListener(v -> {
            whichType = 2;
            et_value_pan_card = TIL_pan_card.getEditText().getText().toString();
            if (PanPatternMatchers()) {
                try {
                    cipherLabel = encrypt(et_value_pan_card.getBytes());
                    encryptedLabel = encoderFunction(cipherLabel);

                    cipherWhichType = encrypt(whichType.toString().getBytes());
                    encryptedWhichType = encoderFunction(cipherWhichType);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                requestImage();
            }
        });

        BTN_verify_ifsc.setOnClickListener(v -> {
            et_ifsc_code = TIL_ifsc_code.getEditText().getText().toString();
            if (IFSCPatternMatcher()) {
                verifyIFSCode();
                TV_bank_branch_details.setVisibility(View.VISIBLE);
                BTN_verify_ifsc.setVisibility(View.GONE);
            }
        });

    }

    private void verifyIFSCode() {
        IFSCApiInterface = RasorpayApiService.getApiClient().create(IFSC_API_Interface.class);
        Call<IFSC> ifscCall = IFSCApiInterface.getIFSCCode(et_ifsc_code);
        ifscCall.enqueue(new Callback<IFSC>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<IFSC> call, @NotNull Response<IFSC> response) {
                IFSC ifsc = response.body();
                assert ifsc != null;
                str_bank_city = ifsc.getCITY();
                str_bank_branch = ifsc.getBRANCH();
                str_bank_address = ifsc.getADDRESS();
                str_bank_name = ifsc.getBANK();

                TV_bank_branch_details.setText("Branch Name: " + str_bank_branch);
            }

            @Override
            public void onFailure(@NotNull Call<IFSC> call, @NotNull Throwable t) {
                Toast.makeText(VerificationActivity.this, "Error in verifying IFSC code.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
