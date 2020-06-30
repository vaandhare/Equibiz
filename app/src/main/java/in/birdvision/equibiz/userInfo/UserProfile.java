package in.birdvision.equibiz.userInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.UserProfileResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.WalletDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.confidentalData.ConfidentialDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.FillWalletResponse;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.orders.OrderActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class UserProfile extends AppCompatActivity {

    ImageView backImg, imgProfile;
    TextView order_history, tvUserType, tvUserName, tvMobile, tvEmail, tvBusinessName, tvBusinessType, tvPartnerFN, tvPartnerLN,
            tvPartnerMob, tvOfficeAddress, tvTotalBalance, BTNFillWallet, BTNConfindentialDetails;

    TableLayout tableWallet, tableConfidential;
    TextView tvAvailableBal, tvPendingBal, tvFillBalance, tvPanCard, tvBankName, tvAccNumber, tvIFSCNumber, tvBankBranch, tvBankCity;

    Equibiz_API_Interface equibiz_api_interface;
    String userID, encryptedUserID, AuthToken, encryptedAccNo, encryptedAmount;
    byte[] cipherUserID, cipherAccNo, cipherAmount;

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    SharedPreferences mySharedPreferences;
    CardView CV_Logout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initializeIDS();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        userID = mySharedPreferences.getString("BuyerID", "xxxxx");
        AuthToken = mySharedPreferences.getString("LoginToken", "xxxxx");

        try {
            cipherUserID = encrypt(userID.getBytes());
            encryptedUserID = encoderFunction(cipherUserID);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        userID = "mXLr0B3FETZHF0NJNhg0cksJhaQ9gB1w6zkeOpggdwI=";
//        userID = "/SEl1CTA8IauX/EmxsmRKW6zOFkNcbkzDN+GeekxEEo=";
//        userID = "q/VhbMT8BFuyAwd115v4Sr8gZCc5z5+ST75APzZYRBM=";

        userProfile();

        order_history.setOnClickListener(v -> startActivity(new Intent(UserProfile.this, OrderActivity.class)));

        BTNFillWallet.setOnClickListener(v -> {
            if (tableWallet.getVisibility() == GONE) {
                getWalletResponse();
                BTNFillWallet.setText("Hide wallet details");
            } else {
                tableWallet.setVisibility(GONE);
                tvFillBalance.setVisibility(GONE);
                BTNFillWallet.setText(R.string.click_here_to_view_wallet_details_fill_wallet);
            }
        });

        tvFillBalance.setOnClickListener(v -> fillWalletAlert());

        BTNConfindentialDetails.setOnClickListener(v -> {
            if (tableConfidential.getVisibility() == GONE) {
                getConfidentialResponse();
                BTNConfindentialDetails.setText("Hide Confidential details");
            } else {
                tableConfidential.setVisibility(GONE);
                BTNConfindentialDetails.setText(R.string.click_here_to_view_confidential_details);
            }
        });

        backImg.setOnClickListener(v -> onBackPressed());

//        Executor executor = ContextCompat.getMainExecutor(this);
//        biometricPrompt = new BiometricPrompt(UserProfile.this, executor, new BiometricPrompt.AuthenticationCallback() {
//            @Override
//            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
//                super.onAuthenticationError(errorCode, errString);
//                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
//                    Toast.makeText(getApplicationContext(), "Authentication Canceled", Toast.LENGTH_SHORT).show();
//                } else
//                    Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
//                super.onAuthenticationSucceeded(result);
//                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
//                getWalletResponse();
//            }
//
//            @Override
//            public void onAuthenticationFailed() {
//                super.onAuthenticationFailed();
//                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Verify to See Hidden Credentials")
//                .setNegativeButtonText("Cancel")
//                .build();

//        BTNFillWallet.setOnClickListener(view -> biometricPrompt.authenticate(promptInfo));
    }

    private void fillWalletAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fill My Wallet");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(20, 20, 20, 20);

        EditText etAccNo = new EditText(this);
        etAccNo.setHint("Enter Account Number");
        etAccNo.setSingleLine();
        etAccNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        etAccNo.setLayoutParams(params);
        layout.addView(etAccNo);

        EditText etAmount = new EditText(this);
        etAmount.setHint("Enter Amount");
        etAmount.setSingleLine();
        etAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        etAmount.setLayoutParams(params);
        layout.addView(etAmount);

        builder.setView(layout);
        builder.setPositiveButton("Submit", ((dialog, which) -> {
                    String accNO = etAccNo.getText().toString();
                    String amount = etAmount.getText().toString();
                    fillWalletResponse(accNO, amount);
                    dialog.dismiss();
                })
        ).setNegativeButton("Cancel", null)
                .create()
                .setCanceledOnTouchOutside(false);

        builder.show();
    }

    private void fillWalletResponse(String accNO, String amount) {
        try {
            cipherAccNo = encrypt(accNO.getBytes());
            encryptedAccNo = encoderFunction(cipherAccNo);

            cipherAmount = encrypt(amount.getBytes());
            encryptedAmount = encoderFunction(cipherAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final FillWalletResponse fillWalletResponse = new FillWalletResponse(encryptedAccNo, encryptedAmount, encryptedUserID);
        Call<FillWalletResponse> responseCall = equibiz_api_interface.fillWalletResponse(fillWalletResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<FillWalletResponse>() {
            @Override
            public void onResponse(@NotNull Call<FillWalletResponse> call, @NotNull Response<FillWalletResponse> response) {
                FillWalletResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(UserProfile.this, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UserProfile.this, response1.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<FillWalletResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(UserProfile.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UserProfile.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getConfidentialResponse() {

        final ConfidentialDetailsResponse detailsResponse = new ConfidentialDetailsResponse(encryptedUserID);

        Call<ConfidentialDetailsResponse> detailsResponseCall = equibiz_api_interface.confidentialDetailsResponse(detailsResponse, "Bearer " + AuthToken);

        detailsResponseCall.enqueue(new Callback<ConfidentialDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<ConfidentialDetailsResponse> call, @NotNull Response<ConfidentialDetailsResponse> response) {
                ConfidentialDetailsResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(UserProfile.this, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else
                    changeConfidentialDetails(response1);
            }

            @Override
            public void onFailure(@NotNull Call<ConfidentialDetailsResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(UserProfile.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UserProfile.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void changeConfidentialDetails(ConfidentialDetailsResponse response1) {
        tableConfidential.setVisibility(View.VISIBLE);
        tvPanCard.setText(response1.getDocdata().getPancard());
        tvBankName.setText(response1.getConfdata().getBankname());
        tvBankBranch.setText(response1.getConfdata().getBankbranch());
        tvBankCity.setText(response1.getConfdata().getBankcity());
        tvAccNumber.setText(String.valueOf(response1.getConfdata().getAccnumber()));
        tvIFSCNumber.setText(String.valueOf(response1.getConfdata().getIfsccode()));

    }

    private void getWalletResponse() {

        final WalletDetailsResponse detailsResponse = new WalletDetailsResponse(encryptedUserID);

        Call<WalletDetailsResponse> walletDetailsResponseCall = equibiz_api_interface.walletDetailsResponse(detailsResponse, "Bearer " + AuthToken);

        walletDetailsResponseCall.enqueue(new Callback<WalletDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<WalletDetailsResponse> call, @NotNull Response<WalletDetailsResponse> response) {
                WalletDetailsResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(UserProfile.this, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else
                    changeWalletDetails(response1);
            }

            @Override
            public void onFailure(@NotNull Call<WalletDetailsResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(UserProfile.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UserProfile.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void changeWalletDetails(WalletDetailsResponse response1) {
        tableWallet.setVisibility(View.VISIBLE);
        tvAvailableBal.setText(String.valueOf(response1.getWalletdata().getWalletBal()));
        tvPendingBal.setText(String.valueOf(response1.getWalletdata().getPending()));
        tvFillBalance.setVisibility(View.VISIBLE);
    }

    private void initializeIDS() {
        order_history = findViewById(R.id.UP_orders_history);
        imgProfile = findViewById(R.id.imgUP_userProfile);
        backImg = findViewById(R.id.img_back_up);

        tvUserType = findViewById(R.id.tvUP_userType);
        tvUserName = findViewById(R.id.tvUP_userName);
        tvMobile = findViewById(R.id.tvUP_mobileNo);
        tvEmail = findViewById(R.id.tvUP_email);
        tvBusinessName = findViewById(R.id.tvUP_businessName);
        tvBusinessType = findViewById(R.id.tvUP_businessType);
        tvPartnerFN = findViewById(R.id.tvUP_partnerFirstName);
        tvPartnerLN = findViewById(R.id.tvUP_partnerLastName);
        tvPartnerMob = findViewById(R.id.tvUP_partnerMobile);
        tvOfficeAddress = findViewById(R.id.tvUP_officeAddress);
        tvTotalBalance = findViewById(R.id.tvUP_totalBalance);
        BTNFillWallet = findViewById(R.id.tvUP_btnFillWallet);
        BTNConfindentialDetails = findViewById(R.id.tvUP_btnConfidentialDetails);

        tableWallet = findViewById(R.id.tableWallet);
        tableConfidential = findViewById(R.id.tableConfidential);

        tvAvailableBal = findViewById(R.id.tvUP_availableBalance);
        tvPendingBal = findViewById(R.id.tvUP_pendingBalance);
        tvFillBalance = findViewById(R.id.tvUP_fillWallet);

        tvPanCard = findViewById(R.id.tvUP_panCard);
        tvBankName = findViewById(R.id.tvUP_bankName);
        tvAccNumber = findViewById(R.id.tvUP_accNumber);
        tvIFSCNumber = findViewById(R.id.tvUP_ifscCode);
        tvBankBranch = findViewById(R.id.tvUP_bankBranch);
        tvBankCity = findViewById(R.id.tvUP_bankCity);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void userProfile() {

        final UserProfileResponse userProfileResponse = new UserProfileResponse(encryptedUserID);
        Call<UserProfileResponse> userProfileResponseCall = equibiz_api_interface.userProfileResponse(userProfileResponse, "Bearer " + AuthToken);

        userProfileResponseCall.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserProfileResponse> call, @NotNull Response<UserProfileResponse> response) {
                UserProfileResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(UserProfile.this, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else {
                    changeDetails(response1);
                    Toast.makeText(UserProfile.this, response1.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserProfileResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(UserProfile.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UserProfile.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changeDetails(UserProfileResponse response1) {
        if (response1.getBuyerdata().getUsertype() == 1)
            tvUserType.setText(R.string.buyer);
        else if (response1.getBuyerdata().getUsertype() == 2)
            tvUserType.setText(R.string.seller);

        tvUserName.setText(response1.getBuyerdata().getName());
        tvMobile.setText(String.valueOf(response1.getBuyerdata().getMobile()));
        tvEmail.setText(response1.getBuyerdata().getEmail());
        tvBusinessName.setText(response1.getBusinessdata().getBuyerBName());
        tvBusinessType.setText(response1.getBusinessdata().getBuyerBType());
        tvPartnerFN.setText(response1.getBusinessdata().getOwnerFname());
        tvPartnerLN.setText(response1.getBusinessdata().getOwnerLname());
        tvPartnerMob.setText(String.valueOf(response1.getBusinessdata().getOwnerMobile()));
        tvOfficeAddress.setText(response1.getBusinessdata().getRegdAddress());
        tvTotalBalance.setText(String.valueOf(response1.getBuyerdata().getWalletBal()));

    }
}
