/*
 * *
 *  * Created by Vaibhav Andhare on 15/7/20 12:53 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.userInfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.Objects;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.userInfo.OtpResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.RegistrationResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class RegisterActivity extends AppCompatActivity {

    TextView tvLogin, tvRegBuyer, tvRegSeller;
    Button BTN_verify_OTP, BTN_register, BTN_send_otp;
    TextInputLayout TIL_first_name, TIL_last_name, TIL_email, TIL_country_code, TIL_mobile_no, TIL_password, TIL_confirm_pass, TIL_otp;

    Equibiz_API_Interface equibiz_api_interface;

    Integer userRole = 1;
    String encryptedFirstName, encryptedLastName, encryptedEmailID, encryptedCountryCode, encryptedMobileNo,
            encryptedOTP, encryptedPassword, encryptedUserRole;

    String first_name, last_name, email_id, country_code, mobile_no, password, confirm_password;

    String otpServerResponse, userOtp, userPassword, userObjId;
    byte[] cipherTextFN, cipherTextLN, cipherTextE, cipherTextCC, cipherTextMN, cipherTextUR, cipherOTP, cipherPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeIDs();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        tvRegBuyer.setOnClickListener(v -> {
            userRole = 1;
            tvRegBuyer.setBackgroundColor(getColor(R.color.dark_blue));
            tvRegBuyer.setTextColor(getColor(R.color.gray));
            tvRegSeller.setBackgroundColor(getColor(R.color.gray));
            tvRegSeller.setTextColor(getColor(R.color.dark_blue));
            TIL_first_name.setVisibility(View.VISIBLE);
            TIL_last_name.setVisibility(View.VISIBLE);
            TIL_email.setVisibility(View.VISIBLE);
            TIL_country_code.setVisibility(View.VISIBLE);
            TIL_mobile_no.setVisibility(View.VISIBLE);
            BTN_send_otp.setVisibility(View.VISIBLE);
        });

        tvRegSeller.setOnClickListener(v -> {
            userRole = 2;
            tvRegSeller.setBackgroundColor(getColor(R.color.dark_blue));
            tvRegSeller.setTextColor(getColor(R.color.gray));
            tvRegBuyer.setBackgroundColor(getColor(R.color.gray));
            tvRegBuyer.setTextColor(getColor(R.color.dark_blue));
            TIL_first_name.setVisibility(View.VISIBLE);
            TIL_last_name.setVisibility(View.VISIBLE);
            TIL_email.setVisibility(View.VISIBLE);
            TIL_country_code.setVisibility(View.VISIBLE);
            BTN_send_otp.setVisibility(View.VISIBLE);
            TIL_mobile_no.setVisibility(View.VISIBLE);
        });

        BTN_send_otp.setOnClickListener(v -> {
            if (validateInputs())
                sendOTP();
        });

        BTN_verify_OTP.setOnClickListener(v -> {
            userOtp = Objects.requireNonNull(TIL_otp.getEditText()).getText().toString();
            if (otpServerResponse.equals(userOtp)) {
                TIL_otp.setVisibility(View.GONE);
                BTN_send_otp.setVisibility(View.GONE);
                TIL_password.setVisibility(View.VISIBLE);
                TIL_confirm_pass.setVisibility(View.VISIBLE);
                BTN_register.setVisibility(View.VISIBLE);
                BTN_verify_OTP.setVisibility(View.GONE);
                TIL_country_code.setEnabled(false);
                TIL_mobile_no.setEnabled(false);
                Toast.makeText(RegisterActivity.this, "Mobile Number Verified", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(RegisterActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();

        });

        BTN_register.setOnClickListener(v -> {
            password = Objects.requireNonNull(TIL_password.getEditText()).getText().toString();
            confirm_password = Objects.requireNonNull(TIL_confirm_pass.getEditText()).getText().toString();

            if (password.isEmpty() || password.length() < 6)
                TIL_password.setError("Password should be more than 6 chars");
            else {
                if (confirm_password.equals(password)) {
                    userPassword = confirm_password;
                    registerPersonalDetails();
                } else
                    TIL_confirm_pass.setError("Password doesn't match.");
            }
        });

        tvLogin.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private void registerPersonalDetails() {
        try {
            cipherOTP = encrypt(userOtp.getBytes());
            encryptedOTP = encoderFunction(cipherOTP);

            cipherPW = encrypt(userPassword.getBytes());
            encryptedPassword = encoderFunction(cipherPW);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final RegistrationResponse registrationResponse = new RegistrationResponse(encryptedCountryCode, encryptedEmailID, encryptedFirstName,
                encryptedLastName, encryptedMobileNo, encryptedOTP, encryptedPassword, encryptedUserRole);

        Call<RegistrationResponse> registrationResponseCall = equibiz_api_interface.registrationResponse(registrationResponse);

        registrationResponseCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(@NotNull Call<RegistrationResponse> call, @NotNull Response<RegistrationResponse> response) {
                RegistrationResponse response1 = response.body();
                if (response1 != null) {
                    userObjId = response1.getUserobjid();
                    SharedPreferences mySharedPreferences = RegisterActivity.this.getSharedPreferences("User_ObjID", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
                    editor.putString("UserObjID", userObjId);
                    editor.putString("encryptedUserRole", encryptedUserRole);
                    editor.apply();
                    if (response1.getStatus().equals("success"))
                        openVerifyEmailAlert();
                    else
                        Toast.makeText(RegisterActivity.this, response1.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<RegistrationResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(RegisterActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openVerifyEmailAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Verify your Email");
        builder.setMessage("Verification mail is sent to your email id. Verify your email id to activate your account.");

        builder.setPositiveButton("Ok", ((dialog, which) -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            dialog.dismiss();
        })).create().setCanceledOnTouchOutside(false);

        builder.show();
    }

    private void sendOTP() {
        final OtpResponse otpResponse = new OtpResponse(encryptedCountryCode, encryptedMobileNo, encryptedUserRole);
        Call<OtpResponse> otpResponseCall = equibiz_api_interface.sendOTP(otpResponse);

        otpResponseCall.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(@NotNull Call<OtpResponse> call, @NotNull Response<OtpResponse> response) {
                OtpResponse response1 = response.body();
                assert response1 != null;
                if (response1.getStatus().equals("success")) {
                    otpServerResponse = response1.getOtp();
                    TIL_otp.setVisibility(View.VISIBLE);
                    BTN_verify_OTP.setVisibility(View.VISIBLE);
                } else if (response1.getStatus().equals("found")) {
                    Toast.makeText(RegisterActivity.this, "This number is already registered with us. Login with password or use different mobile number", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else
                    Toast.makeText(RegisterActivity.this, response1.getStatus(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NotNull Call<OtpResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(RegisterActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeIDs() {
        tvLogin = findViewById(R.id.tv_login);
        tvRegBuyer = findViewById(R.id.tv_register_buyer);
        tvRegSeller = findViewById(R.id.tv_register_seller);

        BTN_register = findViewById(R.id.btn_register);
        BTN_send_otp = findViewById(R.id.btn_send_otp);
        BTN_verify_OTP = findViewById(R.id.btn_verify_otp);

        TIL_first_name = findViewById(R.id.register_etv_first_name);
        TIL_last_name = findViewById(R.id.register_etv_last_name);
        TIL_email = findViewById(R.id.register_etv_email);
        TIL_country_code = findViewById(R.id.register_etv_country_code);
        TIL_mobile_no = findViewById(R.id.register_etv_mobile_number);
        TIL_password = findViewById(R.id.register_etv_password);
        TIL_confirm_pass = findViewById(R.id.register_etv_confirm_password);
        TIL_otp = findViewById(R.id.register_etv_otp);

        Objects.requireNonNull(TIL_first_name.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                first_name = TIL_first_name.getEditText().getText().toString();
                TIL_first_name.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(TIL_last_name.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                last_name = TIL_last_name.getEditText().getText().toString();
                TIL_last_name.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(TIL_email.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email_id = TIL_email.getEditText().getText().toString();
                TIL_email.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(TIL_country_code.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                country_code = TIL_country_code.getEditText().getText().toString();
                TIL_country_code.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(TIL_mobile_no.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mobile_no = TIL_mobile_no.getEditText().getText().toString();
                TIL_mobile_no.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(TIL_password.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = TIL_password.getEditText().getText().toString();
                TIL_password.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(TIL_confirm_pass.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirm_password = TIL_confirm_pass.getEditText().getText().toString();
                TIL_confirm_pass.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean validateInputs() {
        first_name = Objects.requireNonNull(TIL_first_name.getEditText()).getText().toString();
        last_name = Objects.requireNonNull(TIL_last_name.getEditText()).getText().toString();
        email_id = Objects.requireNonNull(TIL_email.getEditText()).getText().toString();
        country_code = Objects.requireNonNull(TIL_country_code.getEditText()).getText().toString();
        mobile_no = Objects.requireNonNull(TIL_mobile_no.getEditText()).getText().toString();

        if (first_name.isEmpty()) {
            TIL_first_name.setError("Enter First Name");
            return false;
        } else if (last_name.isEmpty()) {
            TIL_last_name.setError("Enter Last Name");
            return false;
        } else if (email_id.isEmpty()) {
            TIL_email.setError("Invalid Email");
            return false;
        } else if (country_code.isEmpty()) {
            TIL_country_code.setError("Invalid Country");
            return false;
        } else if (mobile_no.length() != 10) {
            TIL_mobile_no.setError("Enter valid mobile number");
            return false;
        } else {
            try {
                cipherTextFN = encrypt(first_name.getBytes());
                encryptedFirstName = encoderFunction(cipherTextFN);

                cipherTextLN = encrypt(last_name.getBytes());
                encryptedLastName = encoderFunction(cipherTextLN);

                cipherTextUR = encrypt(userRole.toString().getBytes());
                encryptedUserRole = encoderFunction(cipherTextUR);

                cipherTextE = encrypt(email_id.getBytes());
                encryptedEmailID = encoderFunction(cipherTextE);

                cipherTextCC = encrypt(country_code.getBytes());
                encryptedCountryCode = encoderFunction(cipherTextCC);

                cipherTextMN = encrypt(mobile_no.getBytes());
                encryptedMobileNo = encoderFunction(cipherTextMN);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

}
