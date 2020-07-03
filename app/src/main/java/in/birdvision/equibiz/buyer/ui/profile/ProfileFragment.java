package in.birdvision.equibiz.buyer.ui.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.UserProfileResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.confidentalData.ConfidentialDetailsResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProfileFragment extends Fragment {

    ImageView imgProfile;
    TextView tvUserType, tvUserName, tvMobile, tvEmail, tvBusinessName, tvBusinessType, tvPartnerFN, tvPartnerLN,
            tvPartnerMob, tvOfficeAddress, BTNConfindentialDetails;

    TableLayout tableConfidential;
    TextView tvPanCard, tvBankName, tvAccNumber, tvIFSCNumber, tvBankBranch, tvBankCity;

    Equibiz_API_Interface equibiz_api_interface;
    String userID, encryptedUserID, AuthToken;
    byte[] cipherUserID;
    SharedPreferences mySharedPreferences;
    Context context;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buyer_profile, container, false);
        context = root.getContext();

        initializeIDS(root);

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        userID = mySharedPreferences.getString("BuyerID", "");
        AuthToken = mySharedPreferences.getString("LoginToken", "");

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

        BTNConfindentialDetails.setOnClickListener(v -> {
            if (tableConfidential.getVisibility() == GONE) {
                getConfidentialResponse();
                BTNConfindentialDetails.setText("Hide Confidential details");
            } else {
                tableConfidential.setVisibility(GONE);
                BTNConfindentialDetails.setText(R.string.click_here_to_view_confidential_details);
            }
        });

        return root;
    }

    private void getConfidentialResponse() {

        final ConfidentialDetailsResponse detailsResponse = new ConfidentialDetailsResponse(encryptedUserID);

        Call<ConfidentialDetailsResponse> detailsResponseCall = equibiz_api_interface.confidentialDetailsResponse(detailsResponse, "Bearer " + AuthToken);

        detailsResponseCall.enqueue(new Callback<ConfidentialDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<ConfidentialDetailsResponse> call, @NotNull Response<ConfidentialDetailsResponse> response) {
                ConfidentialDetailsResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(context, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else
                    changeConfidentialDetails(response1);
            }

            @Override
            public void onFailure(@NotNull Call<ConfidentialDetailsResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
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


    private void initializeIDS(View view) {
        imgProfile = view.findViewById(R.id.imgUP_userProfile);

        tvUserType = view.findViewById(R.id.tvUP_userType);
        tvUserName = view.findViewById(R.id.tvUP_userName);
        tvMobile = view.findViewById(R.id.tvUP_mobileNo);
        tvEmail = view.findViewById(R.id.tvUP_email);
        tvBusinessName = view.findViewById(R.id.tvUP_businessName);
        tvBusinessType = view.findViewById(R.id.tvUP_businessType);
        tvPartnerFN = view.findViewById(R.id.tvUP_partnerFirstName);
        tvPartnerLN = view.findViewById(R.id.tvUP_partnerLastName);
        tvPartnerMob = view.findViewById(R.id.tvUP_partnerMobile);
        tvOfficeAddress = view.findViewById(R.id.tvUP_officeAddress);
        BTNConfindentialDetails = view.findViewById(R.id.tvUP_btnConfidentialDetails);

        tableConfidential = view.findViewById(R.id.tableConfidential);

        tvPanCard = view.findViewById(R.id.tvUP_panCard);
        tvBankName = view.findViewById(R.id.tvUP_bankName);
        tvAccNumber = view.findViewById(R.id.tvUP_accNumber);
        tvIFSCNumber = view.findViewById(R.id.tvUP_ifscCode);
        tvBankBranch = view.findViewById(R.id.tvUP_bankBranch);
        tvBankCity = view.findViewById(R.id.tvUP_bankCity);

    }

    private void userProfile() {

        final UserProfileResponse userProfileResponse = new UserProfileResponse(encryptedUserID);
        Call<UserProfileResponse> userProfileResponseCall = equibiz_api_interface.userProfileResponse(userProfileResponse, "Bearer " + AuthToken);

        userProfileResponseCall.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserProfileResponse> call, @NotNull Response<UserProfileResponse> response) {
                UserProfileResponse response1 = response.body();
                if (response1 == null)
                    Toast.makeText(context, "Something wrong with server", Toast.LENGTH_SHORT).show();
                else {
                    changeDetails(response1);
                    Toast.makeText(context, response1.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserProfileResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changeDetails(@NotNull UserProfileResponse response1) {
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
    }
}