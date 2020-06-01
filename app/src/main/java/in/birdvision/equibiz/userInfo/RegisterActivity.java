package in.birdvision.equibiz.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import in.birdvision.equibiz.R;

public class RegisterActivity extends AppCompatActivity {

    TextView tvLogin;
    Button BTN_verify_OTP, BTN_register;
    TextInputLayout TIL_first_name, TIL_last_name, TIL_email, TIL_country_code, TIL_mobile_no, TIL_password, TIL_confirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeIDs();

        BTN_verify_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIL_password.setVisibility(View.VISIBLE);
                TIL_confirm_pass.setVisibility(View.VISIBLE);
                BTN_register.setVisibility(View.VISIBLE);
            }
        });

        BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, BusinessDetailsActivity.class));
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void initializeIDs() {
        tvLogin = findViewById(R.id.tv_login);
        BTN_register = findViewById(R.id.btn_register);
        BTN_verify_OTP = findViewById(R.id.btn_verify_otp);
        TIL_first_name = findViewById(R.id.register_etv_first_name);
        TIL_last_name = findViewById(R.id.register_etv_last_name);
        TIL_email = findViewById(R.id.register_etv_email);
        TIL_country_code = findViewById(R.id.register_etv_country_code);
        TIL_mobile_no = findViewById(R.id.register_etv_mobile_number);
        TIL_password = findViewById(R.id.register_etv_password);
        TIL_confirm_pass = findViewById(R.id.register_etv_confirm_password);
    }
}
