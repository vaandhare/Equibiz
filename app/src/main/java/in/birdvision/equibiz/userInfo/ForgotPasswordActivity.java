/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.userInfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import in.birdvision.equibiz.API.equibizAPI.userInfo.ForgotPasswordResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.SetPasswordResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView tvTitle, tvCancel;
    TextInputLayout TIL_email, TIL_code, TIL_password, TIL_confirmPassword;
    Button btnRecoverAcc, btnSubmit;

    String userCode;
    Integer responseCode;
    String encryptedUserRole, encryptedEmail, encryptedCode, encryptedPassword;
    byte[] cipherEmail, cipherCode, cipherPassword;
    Equibiz_API_Interface equibiz_api_interface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        SharedPreferences mySharedPreferences = this.getSharedPreferences("User_ObjID", Context.MODE_PRIVATE);
        String userObjId = mySharedPreferences.getString("UserObjID", "xxxxx");
        encryptedUserRole = mySharedPreferences.getString("encryptedUserRole", "xxxxx");

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);


        tvTitle = findViewById(R.id.tvFP_title);
        tvCancel = findViewById(R.id.tvFP_cancel);

        TIL_email = findViewById(R.id.etvFP_emailID);
        TIL_code = findViewById(R.id.etvFP_enterCode);
        TIL_password = findViewById(R.id.etvFP_password);
        TIL_confirmPassword = findViewById(R.id.etvFP_confirmPassword);

        btnRecoverAcc = findViewById(R.id.btnFP_recoverAccount);
        btnSubmit = findViewById(R.id.btnFP_submitNewPassword);

        btnRecoverAcc.setOnClickListener(v -> {
            progressDialog.show();
            String email = Objects.requireNonNull(TIL_email.getEditText()).getText().toString();
            getForgotPassword(email);
        });

        tvCancel.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        btnSubmit.setOnClickListener(v -> {
            userCode = Objects.requireNonNull(TIL_code.getEditText()).getText().toString();
            String password = Objects.requireNonNull(TIL_password.getEditText()).getText().toString();
            String confPassword = Objects.requireNonNull(TIL_confirmPassword.getEditText()).getText().toString();
            if (confPassword.equals(password)) {
                progressDialog.show();
                setNewPassword(userCode, password);
            }
        });

    }

    private void setNewPassword(String userCode, String password) {
        try {
            cipherCode = encrypt(userCode.getBytes());
            encryptedCode = encoderFunction(cipherCode);

            cipherPassword = encrypt(password.getBytes());
            encryptedPassword = encoderFunction(cipherPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }


        final SetPasswordResponse passwordResponse = new SetPasswordResponse(encryptedCode, encryptedEmail, encryptedPassword);
        Call<SetPasswordResponse> responseCall = equibiz_api_interface.setForgotPasswordResponse(passwordResponse);

        responseCall.enqueue(new Callback<SetPasswordResponse>() {
            @Override
            public void onResponse(@NotNull Call<SetPasswordResponse> call, @NotNull Response<SetPasswordResponse> response) {
                progressDialog.dismiss();
                alertDialog("Successful!!", "Password successfully recovered! \nLog back to your account with new password!");
            }

            @Override
            public void onFailure(@NotNull Call<SetPasswordResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ForgotPasswordActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ForgotPasswordActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getForgotPassword(String email) {
        try {
            cipherEmail = encrypt(email.getBytes());
            encryptedEmail = encoderFunction(cipherEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ForgotPasswordResponse response = new ForgotPasswordResponse(encryptedEmail, encryptedUserRole);

        Call<ForgotPasswordResponse> responseCall = equibiz_api_interface.forgotPasswordResponse(response);
        responseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(@NotNull Call<ForgotPasswordResponse> call, @NotNull Response<ForgotPasswordResponse> response) {
                progressDialog.dismiss();
                ForgotPasswordResponse response1 = response.body();
                assert response1 != null;
                if (response1.getStatus().equals("success")) {
                    alertDialog("Verification code", "Verification code is send to your register email. \nComplete the process to recover your account.");
                    responseCode = Integer.parseInt(response1.getCode());
                    TIL_email.setEnabled(false);
                    btnRecoverAcc.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.GONE);
                    tvTitle.setText(R.string.recover_account);
                    TIL_code.setVisibility(View.VISIBLE);
                    TIL_password.setVisibility(View.VISIBLE);
                    TIL_confirmPassword.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ForgotPasswordResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ForgotPasswordActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ForgotPasswordActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void alertDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);

        builder.setPositiveButton("Ok", ((dialog, which) -> {
            dialog.dismiss();
            if (title.equals("Successful!!"))
                startActivity(new Intent(this, LoginActivity.class));
        })).create().setCanceledOnTouchOutside(false);
        builder.show();
    }

}