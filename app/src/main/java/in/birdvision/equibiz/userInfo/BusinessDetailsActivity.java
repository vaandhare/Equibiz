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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import in.birdvision.equibiz.API.equibizAPI.userInfo.BusinessDetailsResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class BusinessDetailsActivity extends AppCompatActivity {

    TextInputLayout TIL_b_type_spinner, TIL_b_nature_spinner, TIL_b_hub_loc_spinner, TIL_b_name, TIL_reg_add, TIL_pin_code, TIL_PD_fName, TIL_PD_lname, TIL_PD_CC,
            TIL_PD_mob, TIL_POC1_name, TIL_POC1_CC, TIL_POC1_mob, TIL_POC2_name, TIL_POC2_CC, TIL_POC2_Mob;

    AutoCompleteTextView autoEtvBusinessType, autoEtvBusinessNature, autoEtvHubLocation;
    String encryptedBName, encryptedBNature, encryptedBType, encryptedHubLoc, encryptedPDCC, encryptedPDMobNo, encryptedPDFName,
            encryptedPDLName, encryptedPinCode, encryptedPOC1CC, encryptedPOC1Mob, encryptedPOC1Name, encryptedPOC2CC,
            encryptedPOC2Mob, encryptedPOC2Name, encryptedRegAdd, encryptedUserRole, encryptedSaveLater, encryptedUserObjId;

    byte[] cipherBName, cipherBNature, cipherBType, cipherHubLoc, cipherPinCode, cipherPOC1CC, cipherPOC1Mob, cipherPOC1Name, cipherPOC2CC,
            cipherPOC2Mob, cipherPOC2Name, cipherRegAdd, cipherSaveLater, cipherUserObjId, cipherPDfname, cipherPDlname, cipherPDCC, cipherPDMob;

    String[] BusinessType = {"Proprietorship", "Private Limited Company", "LLP", "HUF"};
    String[] BusinessNature = {"Exporter", "Importer", "Trader", "Aggregator", "Corporate Buyer", "Agent",
            "B2B Company", "Dealer", "Distributor", "National Distributor", "CNF Agent"};
    String[] hublocations = {"Mumbai", "Delhi", "Uttar Pradesh", "Punjab", "Gujrat", "Madya Pradesh", "Goa",
            "Pune", "Aizawl"};


    Button BTN_save_next;
    TextView BTN_save_resume_later;

    Equibiz_API_Interface equibiz_api_interface;

    String AuthToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");

        initializeIDs();
        BTN_save_next.setOnClickListener(v -> registerBusinessDetails(false));

        BTN_save_resume_later.setOnClickListener(v -> registerBusinessDetails(true));

    }

    private void registerBusinessDetails(Boolean saveLater) {

        SharedPreferences mySharedPreferences = this.getSharedPreferences("User_ObjID", Context.MODE_PRIVATE);
        String userObjId = mySharedPreferences.getString("UserObjID", "xxxxx");
        encryptedUserRole = mySharedPreferences.getString("encryptedUserRole", "xxxxx");

        String bname = Objects.requireNonNull(TIL_b_name.getEditText()).getText().toString();
        if (bname.isEmpty())
            TIL_b_name.setError("Name should not be empty");
        String btype = Objects.requireNonNull(TIL_b_type_spinner.getEditText()).getText().toString();
        if (btype.isEmpty())
            TIL_b_type_spinner.setError("Please select business type.");
        String bnature = Objects.requireNonNull(TIL_b_nature_spinner.getEditText()).getText().toString();
        if (bnature.isEmpty())
            TIL_b_nature_spinner.setError("Please select business Nature.");
        String hublocation = Objects.requireNonNull(TIL_b_hub_loc_spinner.getEditText()).getText().toString();
        if (hublocation.isEmpty())
            TIL_b_hub_loc_spinner.setError("Please select hub location.");
        String address = Objects.requireNonNull(TIL_reg_add.getEditText()).getText().toString();
        if (address.isEmpty())
            TIL_reg_add.setError("Address should not be empty");
        String pincode = Objects.requireNonNull(TIL_pin_code.getEditText()).getText().toString();
        if (pincode.isEmpty())
            TIL_pin_code.setError("Pincode should not be empty");
        String pdfname = Objects.requireNonNull(TIL_PD_fName.getEditText()).getText().toString();
        if (pdfname.isEmpty())
            TIL_PD_fName.setError("First Name should not be empty");
        String pdlname = Objects.requireNonNull(TIL_PD_fName.getEditText()).getText().toString();
        if (pdlname.isEmpty())
            TIL_PD_lname.setError("Last Name should not be empty");
        String pdCC = Objects.requireNonNull(TIL_PD_CC.getEditText()).getText().toString();
        if (pdCC.isEmpty())
            TIL_PD_CC.setError("Code should not be empty");
        String pdMob = Objects.requireNonNull(TIL_PD_mob.getEditText()).getText().toString();
        if (pdMob.length() != 10)
            TIL_PD_mob.setError("Incorrect Mobile number.");
        String poc1name = Objects.requireNonNull(TIL_POC1_name.getEditText()).getText().toString();
        if (poc1name.isEmpty()) poc1name = "";
        String poc1cc = Objects.requireNonNull(TIL_POC1_CC.getEditText()).getText().toString();
        if (poc1cc.isEmpty()) poc1cc = "";
        String poc1mob = Objects.requireNonNull(TIL_POC1_mob.getEditText()).getText().toString();
        if (poc1mob.isEmpty()) poc1mob = "";
        String poc2name = Objects.requireNonNull(TIL_POC2_name.getEditText()).getText().toString();
        if (poc2name.isEmpty()) poc2name = "";
        String poc2cc = Objects.requireNonNull(TIL_POC2_CC.getEditText()).getText().toString();
        if (poc2cc.isEmpty()) poc2cc = "";
        String poc2mob = Objects.requireNonNull(TIL_POC2_Mob.getEditText()).getText().toString();
        if (poc2mob.isEmpty()) poc2mob = "";

        try {
            cipherBName = encrypt(bname.getBytes());
            encryptedBName = encoderFunction(cipherBName);

            cipherBType = encrypt(btype.getBytes());
            encryptedBType = encoderFunction(cipherBType);

            cipherBNature = encrypt(bnature.getBytes());
            encryptedBNature = encoderFunction(cipherBNature);

            cipherHubLoc = encrypt(hublocation.getBytes());
            encryptedHubLoc = encoderFunction(cipherHubLoc);

            cipherRegAdd = encrypt(address.getBytes());
            encryptedRegAdd = encoderFunction(cipherRegAdd);

            cipherPinCode = encrypt(pincode.getBytes());
            encryptedPinCode = encoderFunction(cipherPinCode);

            cipherPDCC = encrypt(pdCC.getBytes());
            encryptedPDCC = encoderFunction(cipherPDCC);

            cipherPDMob = encrypt(pdMob.getBytes());
            encryptedPDMobNo = encoderFunction(cipherPDMob);

            cipherPDfname = encrypt(pdfname.getBytes());
            encryptedPDFName = encoderFunction(cipherPDfname);

            cipherPDlname = encrypt(pdlname.getBytes());
            encryptedPDLName = encoderFunction(cipherPDlname);

            cipherPOC1CC = encrypt(poc1cc.getBytes());
            encryptedPOC1CC = encoderFunction(cipherPOC1CC);

            cipherPOC1Mob = encrypt(poc1mob.getBytes());
            encryptedPOC1Mob = encoderFunction(cipherPOC1Mob);

            cipherPOC1Name = encrypt(poc1name.getBytes());
            encryptedPOC1Name = encoderFunction(cipherBName);

            cipherPOC2Name = encrypt(poc2name.getBytes());
            encryptedPOC2Name = encoderFunction(cipherPOC2Name);

            cipherPOC2CC = encrypt(poc2cc.getBytes());
            encryptedPOC2CC = encoderFunction(cipherPOC2CC);

            cipherPOC2Mob = encrypt(poc2mob.getBytes());
            encryptedPOC2Mob = encoderFunction(cipherPOC2Mob);

            cipherUserObjId = encrypt(userObjId.getBytes());
            encryptedUserObjId = encoderFunction(cipherUserObjId);

            cipherSaveLater = encrypt(saveLater.toString().getBytes());
            encryptedSaveLater = encoderFunction(cipherSaveLater);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final BusinessDetailsResponse businessDetailsResponse = new BusinessDetailsResponse(encryptedBName, encryptedBNature, encryptedBType,
                encryptedPDCC, encryptedPDMobNo, encryptedPDFName, encryptedPDLName, encryptedPinCode, encryptedPOC1CC, encryptedPOC1Mob,
                encryptedPOC1Name, encryptedPOC2CC, encryptedPOC2Mob, encryptedPOC2Name, encryptedRegAdd, encryptedUserRole, encryptedSaveLater,
                encryptedUserObjId, encryptedHubLoc);

        Call<BusinessDetailsResponse> businessDetailsResponseCall = equibiz_api_interface.businessDetailsResponse(businessDetailsResponse, "Bearer " + AuthToken);

        businessDetailsResponseCall.enqueue(new Callback<BusinessDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<BusinessDetailsResponse> call, @NotNull Response<BusinessDetailsResponse> response) {
                BusinessDetailsResponse response1 = response.body();
                if (response1 != null) {
                    if (response1.getStatus().equals("success")) {
                        if (saveLater)
                            startActivity(new Intent(BusinessDetailsActivity.this, LoginActivity.class));
                        else
                            startActivity(new Intent(BusinessDetailsActivity.this, VerificationActivity.class));
                    } else
                        Toast.makeText(BusinessDetailsActivity.this, response1.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<BusinessDetailsResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(BusinessDetailsActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(BusinessDetailsActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initializeIDs() {
        TIL_b_name = findViewById(R.id.etv_business_name);
        TIL_reg_add = findViewById(R.id.etv_business_address);
        TIL_pin_code = findViewById(R.id.etv_business_address_pincode);
        TIL_PD_fName = findViewById(R.id.etv_partner_first_name);
        TIL_PD_lname = findViewById(R.id.etv_partner_last_name);
        TIL_PD_CC = findViewById(R.id.etv_partner_country_code);
        TIL_PD_mob = findViewById(R.id.etv_partner_mobile_number);
        TIL_POC1_name = findViewById(R.id.etv_contact1_name);
        TIL_POC1_CC = findViewById(R.id.etv_contact1_country_code);
        TIL_POC1_mob = findViewById(R.id.etv_contact1_mobile_number);
        TIL_POC2_name = findViewById(R.id.etv_contact2_name);
        TIL_POC2_CC = findViewById(R.id.etv_contact2_country_code);
        TIL_POC2_Mob = findViewById(R.id.etv_contact2_mobile_number);

        BTN_save_next = findViewById(R.id.btn_save_and_next);
        BTN_save_resume_later = findViewById(R.id.tv_save_resume_later);

        //Business Type Spinner
        TIL_b_type_spinner = findViewById(R.id.business_type_spinner);
        autoEtvBusinessType = findViewById(R.id.autocomplete_business_type);
        ArrayAdapter<String> adapterBT = new ArrayAdapter<>(BusinessDetailsActivity.this, R.layout.dropdown_menu, BusinessType);
        autoEtvBusinessType.setAdapter(adapterBT);

        //Business Nature Spinner
        TIL_b_nature_spinner = findViewById(R.id.business_nature_spinner);
        autoEtvBusinessNature = findViewById(R.id.autocomplete_business_nature);
        ArrayAdapter<String> adapterBN = new ArrayAdapter<>(BusinessDetailsActivity.this, R.layout.dropdown_menu, BusinessNature);
        autoEtvBusinessNature.setAdapter(adapterBN);

        //Hub Location Spinner
        TIL_b_hub_loc_spinner = findViewById(R.id.hub_location_spinner);
        autoEtvHubLocation = findViewById(R.id.autocomplete_hub_location);
        ArrayAdapter<String> adapterHL = new ArrayAdapter<>(BusinessDetailsActivity.this, R.layout.dropdown_menu, hublocations);
        autoEtvHubLocation.setAdapter(adapterHL);

    }
}
