/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.seller.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.EquibizSeller_API_interface;
import in.birdvision.equibiz.API.equibizAPI.seller.home.SellerHomeResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.LoginActivity.USER_ID;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class HomeFragment extends Fragment {

    EquibizSeller_API_interface equibiz_api_interface;
    Context context;
    String userID, AuthToken, encryptedUserID;
    byte[] cipherUserId;
    ProgressBar progressBar;
    CheckedTextView phoneVerify, emailVerify, businessVerify, ownerVerify, pocVerify, bankVerify, documentsVerify,
            invoiceTemplateVerify, walletBalVerify, listingAddedVerify;

    int profileProgress = 0;
    TextView welcomeUser, tvProgress;

    PieChart pieChartInventory, pieChartOrders;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initializeIDs(root);
        context = root.getContext();
        equibiz_api_interface = EquibizApiService.getClient().create(EquibizSeller_API_interface.class);

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");
        userID = mySharedPreferences.getString(USER_ID, "");

        homeResponse(userID);

        return root;
    }

    private void initializeIDs(View root) {
        progressBar = root.findViewById(R.id.FH_progressBar);
        phoneVerify = root.findViewById(R.id.cbtvFH_phoneVerified);
        emailVerify = root.findViewById(R.id.cbtvFH_emailVerified);
        businessVerify = root.findViewById(R.id.cbtvFH_businessDetailsVerified);
        ownerVerify = root.findViewById(R.id.cbtvFH_ownerDetails);
        pocVerify = root.findViewById(R.id.cbtvFH_businessCOP);
        bankVerify = root.findViewById(R.id.cbtvFH_bankDetails);
        documentsVerify = root.findViewById(R.id.cbtvFH_documentsVerified);
        invoiceTemplateVerify = root.findViewById(R.id.cbtvFH_invoiceTemplate);
        walletBalVerify = root.findViewById(R.id.cbtvFH_walletBalance);
        listingAddedVerify = root.findViewById(R.id.cbtvFH_listingAdded);

        pieChartInventory = root.findViewById(R.id.pieChart_inventory);
        pieChartOrders = root.findViewById(R.id.pieChart_orders);

        welcomeUser = root.findViewById(R.id.tvFH_welcomeUser);
        tvProgress = root.findViewById(R.id.tvFH_progess);
    }

    private void homeResponse(String userID) {
        try {
            cipherUserId = encrypt(userID.getBytes());
            encryptedUserID = encoderFunction(cipherUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final SellerHomeResponse sellerHomeResponse = new SellerHomeResponse(encryptedUserID);

        Call<SellerHomeResponse> responseCall = equibiz_api_interface.sellerHomeResponse(sellerHomeResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<SellerHomeResponse>() {
            @Override
            public void onResponse(@NotNull Call<SellerHomeResponse> call, @NotNull Response<SellerHomeResponse> response) {
                SellerHomeResponse response1 = response.body();
                assert response1 != null;
                changeData(response1);
            }

            @Override
            public void onFailure(@NotNull Call<SellerHomeResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void changeData(SellerHomeResponse response1) {
        if (response1.getGetuserdata().isMobileverified()) {
            phoneVerify.setChecked(true);
            phoneVerify.setCheckMarkDrawable(R.drawable.ic_check_circle);
            profileProgress += 10;
        }
        if (response1.getGetuserdata().isEmailverified()) {
            emailVerify.setChecked(true);
            emailVerify.setCheckMarkDrawable(R.drawable.ic_check_circle);
            profileProgress += 10;
        }
        if (response1.getGetuserdata().isBusinessverified()) {
            businessVerify.setChecked(true);
            businessVerify.setCheckMarkDrawable(R.drawable.ic_check_circle);
            profileProgress += 10;
        }
        if (response1.getGetuserdata().isBankverified()) {
            bankVerify.setChecked(true);
            bankVerify.setCheckMarkDrawable(R.drawable.ic_check_circle);
            profileProgress += 10;
        }
        if (response1.getGetuserdata().isDocverified()) {
            documentsVerify.setChecked(true);
            documentsVerify.setCheckMarkDrawable(R.drawable.ic_check_circle);
            profileProgress += 10;
        }

        welcomeUser.setText("Welcome Back, " + response1.getGetuserdata().getFname());
        tvProgress.setText("Your account progress: " + "(" + profileProgress + "/100)");
        progressBar.setProgress(profileProgress);

        getInventoryPieChart(response1);
        getOrdersPieChart(response1);

    }

    private void getInventoryPieChart(SellerHomeResponse response1) {
        pieChartInventory.setHoleRadius(0);
        pieChartInventory.setTransparentCircleRadius(0);

        int inStock = Integer.parseInt(response1.getGetinstockpros());
        int limitedStock = Integer.parseInt(response1.getGetlimitedstockpros());
        int outOfStock = Integer.parseInt(response1.getGetoutofstockpros());

        List<String> titleList = new ArrayList<>();
        if (inStock != 0)
            titleList.add("In-Stock");
        if (limitedStock != 0)
            titleList.add("Limited-Stock");
        if (outOfStock != 0)
            titleList.add("Out-of-Stock");

        List<PieEntry> pieEntryList = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++)
            pieEntryList.add(new PieEntry(outOfStock, titleList.get(i)));

        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Inventory");
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);
        pieData.setValueTextSize(15f);
        pieData.setValueFormatter(new PercentFormatter());
        pieChartInventory.setData(pieData);

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieChartInventory.getDescription().setEnabled(false);
        pieChartInventory.setDrawSliceText(false);
        pieChartInventory.setTouchEnabled(false);
        pieChartInventory.animateXY(1400, 1400);
    }

    private void getOrdersPieChart(SellerHomeResponse response1) {
        pieChartOrders.setHoleRadius(0);
        pieChartOrders.setTransparentCircleRadius(0);

        int pending = Integer.parseInt(response1.getGetpendingorders());
        int cancelled = Integer.parseInt(response1.getGetrejectedorders());
        int successful = Integer.parseInt(response1.getGetsuccessorders());

        List<String> titleList = new ArrayList<>();
        if (pending != 0)
            titleList.add("Pending");
        if (cancelled != 0)
            titleList.add("Cancelled");
        if (successful != 0)
            titleList.add("Successful");

        List<PieEntry> pieEntryList = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++)
            pieEntryList.add(new PieEntry(pending, titleList.get(i)));

        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Orders");
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);
        pieData.setValueTextSize(15f);
        pieData.setValueFormatter(new PercentFormatter());
        pieChartOrders.setData(pieData);

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieChartOrders.getDescription().setEnabled(false);
        pieChartOrders.setDrawSliceText(false);
        pieChartOrders.setTouchEnabled(false);
        pieChartOrders.animateXY(1400, 1400);
    }

}