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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.Objects;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.userInfo.LoginResponse;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.product.ProductListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister, tvBuyer, tvSeller, tvLoginAs, tvLoginError;
    Integer userRole = 1;
    Button login_btn;
    TextInputLayout TIL_Mobile_Number, TIL_Password;
    String encryptedPassword, encryptedRole, encryptedMobileNo;
    byte[] cipherText;
    Equibiz_API_Interface equibiz_api_interface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.tv_register);
        login_btn = findViewById(R.id.btn_login);
        TIL_Mobile_Number = findViewById(R.id.etv_login_mobile_number);
        TIL_Password = findViewById(R.id.etv_login_password);
        tvBuyer = findViewById(R.id.tv_login_buyer);
        tvSeller = findViewById(R.id.tv_login_seller);
        tvLoginAs = findViewById(R.id.tv_login_as);
        tvLoginError = findViewById(R.id.tv_login_error);

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        tvBuyer.setOnClickListener(v -> {
            userRole = 1;
            tvSeller.setBackgroundColor(getResources().getColor(R.color.gray));
            tvSeller.setTextColor(getResources().getColor(R.color.dark_blue));
            tvBuyer.setBackgroundColor(getResources().getColor(R.color.dark_blue));
            tvBuyer.setTextColor(getResources().getColor(R.color.gray));
            tvLoginAs.setText(R.string.you_are_login_as_a_buyer);
        });

        tvSeller.setOnClickListener(v -> {
            userRole = 2;
            tvBuyer.setBackgroundColor(getResources().getColor(R.color.gray));
            tvBuyer.setTextColor(getResources().getColor(R.color.dark_blue));
            tvSeller.setBackgroundColor(getResources().getColor(R.color.dark_blue));
            tvSeller.setTextColor(getResources().getColor(R.color.gray));
            tvLoginAs.setText(R.string.you_are_login_as_seller);
        });

        login_btn.setOnClickListener(v -> {
            if (validateInputs()) {
                progressDialog.show();
                loginToEquibiz();
            }
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void loginToEquibiz() {
        final LoginResponse loginResponse = new LoginResponse(encryptedMobileNo, encryptedPassword, encryptedRole);
        Call<LoginResponse> loginResponseCall = equibiz_api_interface.loginUser(loginResponse);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                progressDialog.dismiss();
                LoginResponse response1 = response.body();
                assert response1 != null;
                if (response1.getStatus().equals("success") && response1.getData().getBusinessverified().equals("false")) {
                    Intent intent = new Intent(LoginActivity.this, BusinessDetailsActivity.class);
                    intent.putExtra("EncUserRole", encryptedRole);
                    startActivity(intent);
                } else if (response1.getStatus().equals("success") && response1.getData().getBankverified().equals("false"))
                    startActivity(new Intent(LoginActivity.this, VerificationActivity.class));
                else if (response1.getStatus().equals("success") && response1.getData().getBusinessverified().equals("true")
                        && response1.getData().getBankverified().equals("true")) {
                    if (response1.getData().getUsertype().equals(String.valueOf(1))) {
                        SharedPreferences mySharedPreferences = LoginActivity.this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("BuyerID", response1.getData().get_id());
                        editor.apply();
                    }
                    startActivity(new Intent(LoginActivity.this, ProductListActivity.class));

                } else if (response1.getStatus().equals("wrongpass") || response1.getStatus().equals("nodata"))
                    tvLoginError.setVisibility(View.VISIBLE);
                else
                    Toast.makeText(LoginActivity.this, response1.getStatus(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(LoginActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateInputs() {
        String mob_number = Objects.requireNonNull(TIL_Mobile_Number.getEditText()).getText().toString();
        String password = Objects.requireNonNull(TIL_Password.getEditText()).getText().toString();
        if (mob_number.isEmpty()) {
            TIL_Mobile_Number.setError("Invalid Mobile Number");
            return false;
        } else if (password.isEmpty()) {
            TIL_Password.setError("Incorrect Password");
            return false;
        } else {
            //Encrypt Password
            try {
                cipherText = encrypt(password.getBytes());
                encryptedPassword = encoderFunction(cipherText);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Encrypt Mobile Number
            try {
                cipherText = encrypt(mob_number.getBytes());
                encryptedMobileNo = encoderFunction(cipherText);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Encrypt Role
            try {
                cipherText = encrypt(userRole.toString().getBytes());
                encryptedRole = encoderFunction(cipherText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
