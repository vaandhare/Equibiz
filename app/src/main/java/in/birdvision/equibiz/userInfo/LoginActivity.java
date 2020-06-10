package in.birdvision.equibiz.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import in.birdvision.equibiz.R;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister, aaaaa, bbbbb;
    Button login_btn;
    TextInputLayout TIL_Mobile_Number, TIL_Password;

    //For AES Cipher Encryption
    KeyGenerator keyGenerator;
    SecretKey secretKey;
    byte[] secretKeyen;
    String strSecretKey;
    byte[] IV = new byte[16];
    byte[] cipherText;
    SecureRandom random;

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }

    public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encoderFunction(byte[] decval) {
        return Base64.encodeToString(decval, Base64.DEFAULT);
    }

    public static byte[] decoderFunction(String enval) {
        return Base64.decode(enval, Base64.DEFAULT);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.tv_register);
        login_btn = findViewById(R.id.btn_login);
        TIL_Mobile_Number = findViewById(R.id.etv_login_mobile_number);
        TIL_Password = findViewById(R.id.etv_login_password);
        //Dummy
        aaaaa = findViewById(R.id.aaaa);
        bbbbb = findViewById(R.id.bbbbb);

        login_btn.setOnClickListener(v -> {
            validateInputs();
            //startActivity(new Intent(LoginActivity.this, ProductListActivity.class));
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void validateInputs() {
        String mob_number = Objects.requireNonNull(TIL_Mobile_Number.getEditText()).getText().toString();
        String password = Objects.requireNonNull(TIL_Password.getEditText()).getText().toString();
        if (mob_number.isEmpty())
            TIL_Mobile_Number.setError("Invalid Mobile Number");
        else if (password.isEmpty())
            TIL_Password.setError("Incorrect Password");
        else {
            String encryptedPassword = null;
            //Encrypt
            try {
                keyGenerator = KeyGenerator.getInstance("AES");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
            secretKeyen = secretKey.getEncoded();
            strSecretKey = encoderFunction(secretKeyen);
            random = new SecureRandom();
            random.nextBytes(IV);
            try {
                cipherText = encrypt(password.getBytes(), secretKey, IV);
                encryptedPassword = encoderFunction(cipherText);
                aaaaa.setText(encryptedPassword);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Decrypt
            try {
                byte[] encText = decoderFunction(encryptedPassword);
                byte[] iv = decoderFunction(random.toString());
                byte[] encodedSecretKey = decoderFunction(strSecretKey);
                SecretKey originalSecretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "AES");

                String decryptedText = decrypt(encText, originalSecretKey, iv);
                bbbbb.setText(decryptedText);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
