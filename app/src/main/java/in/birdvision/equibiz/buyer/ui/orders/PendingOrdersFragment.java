/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 2:01 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.buyer.ui.orders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.AllOrdersResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.ArrayProduct;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static in.birdvision.equibiz.userInfo.LoginActivity.USER_ID;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class PendingOrdersFragment extends Fragment {

    String userID, AuthToken, encryptedUserID;
    byte[] cipherUserId;
    Equibiz_API_Interface equibiz_api_interface;
    Context context;
    Spinner brandsSpinner;
    RecyclerView recyclerView;
    AdapterBuyerOrders adapter;
    TextView noResults;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pending_orders, container, false);

        context = root.getContext();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");
        userID = mySharedPreferences.getString(USER_ID, "");
        brandsSpinner = root.findViewById(R.id.spinner_FPO_brands);

        recyclerView = root.findViewById(R.id.rv_buyer_pending_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new AdapterBuyerOrders(context);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(position ->
                Toast.makeText(context, position, Toast.LENGTH_SHORT).show());

        noResults = root.findViewById(R.id.tv_no_results_buyer_pending_orders);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");

        getOrdersResponse(userID);

        return root;
    }

    private void getOrdersResponse(String userID) {
        try {
            cipherUserId = encrypt(userID.getBytes());
            encryptedUserID = encoderFunction(cipherUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final AllOrdersResponse allOrdersResponse = new AllOrdersResponse(encryptedUserID);
        Call<AllOrdersResponse> responseCall = equibiz_api_interface.allOrderResponse(allOrdersResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<AllOrdersResponse>() {
            @Override
            public void onResponse(@NotNull Call<AllOrdersResponse> call, @NotNull Response<AllOrdersResponse> response) {
                AllOrdersResponse response1 = response.body();
                assert response1 != null;
                changeData(response1);
            }

            @Override
            public void onFailure(@NotNull Call<AllOrdersResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changeData(AllOrdersResponse response1) {
        progressDialog.dismiss();
        final String[] brand = new String[1];
        List<String> brandNames = new ArrayList<>();
        int pos = 0;
        while (pos < response1.brandsonly.size()) {
            brandNames.add(response1.brandsonly.get(pos).brandname);
            pos++;
        }
        brandsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brand[0] = brandNames.get(position);
                HashMap<String, List<ArrayProduct>> map = response1.getPendingorders();
                List<ArrayProduct> list = map.get(brand[0]);
                if (list == null || list.isEmpty()) {
                    noResults.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.setProductdata(list);
                    noResults.setVisibility(GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                brand[0] = brandNames.get(0);
            }
        });
        ArrayAdapter<String> brands = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, brandNames);
        brands.setDropDownViewResource(R.layout.dropdown_menu);
        brandsSpinner.setAdapter(brands);

    }
}