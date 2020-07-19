/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.userInfo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
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
import in.birdvision.equibiz.buyer.BuyerHomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class VerificationActivity extends AppCompatActivity {

    private static final String PAN_CARD_MATCHER = "[A-Z]{5}[0-9]{4}[A-Z]";
    private static final String GSTIN_NUMBER = "[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z][1-9A-Za-z][Z][0-9a-zA-Z]";
    AutoCompleteTextView etvACBankName;
    private static final String IFSC_CODE = "[A-Z]{4}[0-9]{6,7}";
    TextInputLayout TIL_spinner_bank_name, TIL_bank_branch_name, TIL_pan_card, TIL_gstin_number,
            TIL_ifsc_code, TIL_account_no, TIL_acc_holder_name;
    Button BTN_gstin_img, BTN_pan_img, BTN_save_submit, BTN_previous, BTN_verify_ifsc, BTN_cheque_img;
    String et_value_pan_card, et_value_gstin, et_ifsc_code, spinnerGstinValue;
    String encryptedAccHolderName, encryptedAccNum, encryptedBankBranch, encryptedBankCity, encryptedBankName,
            encryptedIfscCode, encryptedRole, encryptedUserObjId, encryptedSpinnerBankBranch, encryptedWhichType,
            encryptedLabel, encryptedGstinSpinner;
    byte[] cipherAccHolderName, cipherAccNum, cipherBankBranch, cipherBankCity, cipherBankName,
            cipherIfscCode, cipherUserObjId, cipherSpinnerBankBranch, cipherWhichType,
            cipherLabel, cipherGstinSpinner;

    Spinner spinnerGSTIN;
    String[] gstinValidity = {"Monthly", "Quarterly"};

    String str_bank_city, str_bank_address, str_bank_branch, str_bank_name;

    IFSC_API_Interface IFSCApiInterface;
    Equibiz_API_Interface equibiz_api_interface;
    ProgressDialog progressDialog;
    Integer whichType, statusGSTIN = 0, statusPAN = 0, statusIFSC = 0, statusCheque = 0;
    private String AuthToken, labelValue, userObjId;

    ImageView imageGSTIN, imagePan, imageChequeBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        initializeIDs();

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");

        SharedPreferences sharedPreferences = this.getSharedPreferences("User_ObjID", Context.MODE_PRIVATE);
        userObjId = sharedPreferences.getString("UserObjID", "");
        encryptedRole = sharedPreferences.getString("encryptedUserRole", "");

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
        if (statusGSTIN == 0)
            TIL_gstin_number.setError("Enter GSTIN Number");
        if (statusPAN == 0)
            TIL_pan_card.setError("Enter PAN Number");
        if (statusIFSC == 0)
            TIL_ifsc_code.setError("Enter IFSC Code");
        if (acc_no.isEmpty())
            TIL_account_no.setError("Enter valid account number.");
        String acc_holder_name = Objects.requireNonNull(TIL_acc_holder_name.getEditText()).getText().toString();
        if (acc_holder_name.isEmpty())
            TIL_acc_holder_name.setError("Enter Acc. holder name.");
        String bank_branch = Objects.requireNonNull(TIL_bank_branch_name.getEditText()).getText().toString();

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

            cipherSpinnerBankBranch = encrypt(bank_branch.getBytes());
            encryptedSpinnerBankBranch = encoderFunction(cipherSpinnerBankBranch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> chequebookimages = new ArrayList<>();
        chequebookimages.add(" ");

        final BankDetailsResponse bankDetailsResponse = new BankDetailsResponse(encryptedAccHolderName,
                encryptedAccNum, encryptedSpinnerBankBranch, encryptedBankCity, encryptedBankName, encryptedBankBranch,
                encryptedIfscCode, encryptedRole, encryptedUserObjId, chequebookimages);
        Call<BankDetailsResponse> bankDetailsResponseCall = equibiz_api_interface.bankDetailsResponse(bankDetailsResponse, "Bearer " + AuthToken);

        bankDetailsResponseCall.enqueue(new Callback<BankDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<BankDetailsResponse> call, @NotNull Response<BankDetailsResponse> response) {
                progressDialog.dismiss();
                BankDetailsResponse response1 = response.body();
                assert response1 != null;
                if (response1.getStatus().equals("success") || response1.getStatus().equals("error"))
                    startActivity(new Intent(VerificationActivity.this, BuyerHomeActivity.class));
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
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image");

        builder.setItems(options, (dialog, item) -> {

            if (options[item].equals("Take Photo")) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            } else if (options[item].equals("Choose from Gallery")) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);

            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void uploadImage(String imagePath) {

        spinnerGstinValue = spinnerGSTIN.getSelectedItem().toString();

        try {
            cipherLabel = encrypt(labelValue.getBytes());
            encryptedLabel = encoderFunction(cipherLabel);

            cipherUserObjId = encrypt(userObjId.getBytes());
            encryptedUserObjId = encoderFunction(cipherUserObjId);

            cipherWhichType = encrypt(whichType.toString().getBytes());
            encryptedWhichType = encoderFunction(cipherWhichType);

            cipherGstinSpinner = encrypt(spinnerGstinValue.getBytes());
            encryptedGstinSpinner = encoderFunction(cipherGstinSpinner);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(imagePath);
        byte[] bytesFile = new byte[(int) file.length()];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytesFile, 0, bytesFile.length);
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (statusGSTIN == 1) {
                imageGSTIN.setImageBitmap(myBitmap);
                imageGSTIN.setVisibility(View.VISIBLE);
            } else if (statusPAN == 1) {
                imagePan.setImageBitmap(myBitmap);
                imagePan.setVisibility(View.VISIBLE);
            }
        }

//        RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
//        MultipartBody.Part[] body = new MultipartBody.Part[1];
//
//        body[0] = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//
//        Map<String, RequestBody> requestBodyMap = new HashMap<>();
//        requestBodyMap.put("label", RequestBody.create(encryptedLabel, MediaType.parse("multipart/form-data")));
//        requestBodyMap.put("role", RequestBody.create(encryptedRole, MediaType.parse("multipart/form-data")));
//        requestBodyMap.put("userobjid", RequestBody.create(encryptedUserObjId, MediaType.parse("multipart/form-data")));
//        requestBodyMap.put("whichtype", RequestBody.create(encryptedWhichType, MediaType.parse("multipart/form-data")));
//        requestBodyMap.put("gsttype", RequestBody.create(encryptedGstinSpinner, MediaType.parse("multipart/form-data")));
//
//        Log.d("label", encryptedLabel);
//        Log.d("role", encryptedRole);
//        Log.d("userobjid", encryptedUserObjId);
//        Log.d("whichtype", encryptedWhichType);
//        Log.d("gsttype", encryptedGstinSpinner);

        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(bytesFile);

        final UploadDocuments uploadDocuments = new UploadDocuments(encryptedLabel, encryptedRole, encryptedUserObjId, encryptedWhichType, encryptedGstinSpinner,
                arrayList);

        Call<UploadDocuments> documentsCall = equibiz_api_interface.uploadDocuments(uploadDocuments, "Bearer " + AuthToken);
        documentsCall.enqueue(new Callback<UploadDocuments>() {
            @Override
            public void onResponse(@NotNull Call<UploadDocuments> call, @NotNull Response<UploadDocuments> response) {
                UploadDocuments uploadDocuments1 = response.body();
                assert uploadDocuments1 != null;
                if (response.isSuccessful())
                    Toast.makeText(VerificationActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                else {
                    try {
                        assert response.errorBody() != null;
                        Toast.makeText(VerificationActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                        assert bitmap != null;
                        Bitmap convertedImage = getResizedBitmap(bitmap, 200);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        convertedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                        String path = MediaStore.Images.Media.insertImage(getContentResolver(), convertedImage, "title", null);
                        uploadImage(path);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String imagePath = cursor.getString(columnIndex);
                                uploadImage(imagePath);
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void initializeIDs() {
        Resources resources = getResources();

        TIL_spinner_bank_name = findViewById(R.id.spinner_bank_name);
        etvACBankName = findViewById(R.id.autocomplete_bank_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(VerificationActivity.this,
                R.layout.dropdown_menu, resources.getStringArray(R.array.bank_names));
        etvACBankName.setAdapter(adapter);

        TIL_bank_branch_name = findViewById(R.id.etv_bank_branch_name);

        TIL_pan_card = findViewById(R.id.etv_pan_card_number);
        TIL_pan_card.setErrorEnabled(false);
        TIL_gstin_number = findViewById(R.id.etv_gstin_number);
        TIL_gstin_number.setErrorEnabled(false);
        TIL_ifsc_code = findViewById(R.id.etv_ifsc_number);
        TIL_ifsc_code.setErrorEnabled(false);

        spinnerGSTIN = findViewById(R.id.spinner_gstin);
        ArrayAdapter<String> adapterGSTIN = new ArrayAdapter<>(VerificationActivity.this,
                R.layout.dropdown_menu, gstinValidity);
        spinnerGSTIN.setAdapter(adapterGSTIN);

        TIL_acc_holder_name = findViewById(R.id.etv_account_holder_name);
        TIL_account_no = findViewById(R.id.etv_account_number);

        BTN_gstin_img = findViewById(R.id.btn_gstin_upload);
        BTN_pan_img = findViewById(R.id.btn_pan_card_upload);
        BTN_save_submit = findViewById(R.id.btn_save_and_submit_va);
        BTN_previous = findViewById(R.id.btn_previous_va);
        BTN_verify_ifsc = findViewById(R.id.btn_verify_ifsc);
        BTN_cheque_img = findViewById(R.id.btn_upload_cheque_book);

        imagePan = findViewById(R.id.imgPanCard);
        imageGSTIN = findViewById(R.id.imgGSTIN);
        imageChequeBook = findViewById(R.id.imgChequeBook);

        BTN_previous.setOnClickListener(v -> startActivity(new Intent(VerificationActivity.this, BusinessDetailsActivity.class)));

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
            }

            @Override
            public void afterTextChanged(Editable s) {
                TIL_ifsc_code.setErrorEnabled(false);
            }
        });

        BTN_gstin_img.setOnClickListener(v -> {
            whichType = 1;
            statusGSTIN = 1;
            et_value_gstin = TIL_gstin_number.getEditText().getText().toString();
            if (GstinPatternMatchers()) {
                labelValue = et_value_gstin;
                requestImage();
            }
        });

        BTN_pan_img.setOnClickListener(v -> {
            whichType = 2;
            statusPAN = 1;
            et_value_pan_card = TIL_pan_card.getEditText().getText().toString();
            if (PanPatternMatchers()) {
                labelValue = et_value_pan_card;
                requestImage();
            }
        });

        BTN_verify_ifsc.setOnClickListener(v -> {
            statusIFSC = 1;
            et_ifsc_code = TIL_ifsc_code.getEditText().getText().toString();
            if (IFSCPatternMatcher()) {
                verifyIFSCode();
                BTN_verify_ifsc.setVisibility(View.GONE);
            }
        });

        BTN_cheque_img.setOnClickListener(v -> {
            statusCheque = 1;
            requestImage();
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
                if (response.isSuccessful() || ifsc != null) {
                    assert ifsc != null;
                    str_bank_city = ifsc.getCITY();
                    str_bank_branch = ifsc.getBRANCH();
                    str_bank_address = ifsc.getADDRESS();
                    str_bank_name = ifsc.getBANK();

                    Objects.requireNonNull(TIL_bank_branch_name.getEditText()).setText(str_bank_branch);
                } else {
                    TIL_ifsc_code.setError("Invalid IFSC Number");
                    BTN_verify_ifsc.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<IFSC> call, @NotNull Throwable t) {
                Toast.makeText(VerificationActivity.this, "Error in verifying IFSC code.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
