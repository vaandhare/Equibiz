package in.birdvision.equibiz.buyer.ui.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.WalletDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.FillWalletResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class WalletFragment extends Fragment {

    String userID, encryptedUserID, AuthToken, encryptedAccNo, encryptedAmount;
    byte[] cipherUserID, cipherAccNo, cipherAmount;
    Equibiz_API_Interface equibiz_api_interface;

    TextView tvTotalBalance, tvPendingBalance, tvAvailableBalance, tvFillBalance;
    SharedPreferences mySharedPreferences;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        context = view.getContext();
        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        userID = mySharedPreferences.getString("BuyerID", "");
        AuthToken = mySharedPreferences.getString("LoginToken", "");

        tvAvailableBalance = view.findViewById(R.id.tvFW_available_balance);
        tvPendingBalance = view.findViewById(R.id.tvFW_pending_balance);
        tvTotalBalance = view.findViewById(R.id.tvFW_total_balance);
        tvFillBalance = view.findViewById(R.id.tvFW_fillWallet);

        try {
            cipherUserID = encrypt(userID.getBytes());
            encryptedUserID = encoderFunction(cipherUserID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getWalletResponse();
        tvFillBalance.setOnClickListener(v -> fillWalletAlert());

        return view;
    }

    private void getWalletResponse() {

        final WalletDetailsResponse detailsResponse = new WalletDetailsResponse(encryptedUserID);

        Call<WalletDetailsResponse> walletDetailsResponseCall = equibiz_api_interface.walletDetailsResponse(detailsResponse, "Bearer " + AuthToken);

        walletDetailsResponseCall.enqueue(new Callback<WalletDetailsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<WalletDetailsResponse> call, @NotNull Response<WalletDetailsResponse> response) {
                WalletDetailsResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(context, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else {
                    tvTotalBalance.setText("₹ " + response1.getWalletdata().getTotal());
                    tvAvailableBalance.setText("₹ " + response1.getWalletdata().getWalletBal());
                    tvPendingBalance.setText("₹ " + response1.getWalletdata().getPending());
                }
            }

            @Override
            public void onFailure(@NotNull Call<WalletDetailsResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fillWalletAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Fill My Wallet");

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(20, 20, 20, 20);

        EditText etAccNo = new EditText(context);
        etAccNo.setHint("Enter Account Number");
        etAccNo.setSingleLine();
        etAccNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        etAccNo.setLayoutParams(params);
        layout.addView(etAccNo);

        EditText etAmount = new EditText(context);
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
                    Toast.makeText(context, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, response1.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<FillWalletResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


}