/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 4:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.seller.ui.pendingOrders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.EquibizSeller_API_interface;
import in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder.AcceptResonse;
import in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder.Orderslist;
import in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder.PendingOrderResponse;
import in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder.RejectResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.LoginActivity.USER_ID;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class PendingOrderFragment extends Fragment implements AdapterPendingOrders.OnItemClickListener {

    EquibizSeller_API_interface equibiz_api_interface;
    AdapterPendingOrders adapterProductList;
    RecyclerView productRecyclerView;
    ProgressDialog progressDialog;
    List<Orderslist> orderslists;

    TextView tvNoResults;
    String AuthToken, userID, encryptedUserID, encryptedOrderId;
    byte[] cipherUserId, cipherOrderId;
    Context context;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pending_order, container, false);

        context = root.getContext();

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");
        userID = mySharedPreferences.getString(USER_ID, "");

        equibiz_api_interface = EquibizApiService.getClient().create(EquibizSeller_API_interface.class);
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(R.style.ProgressBar);
        progressDialog.show();

        productRecyclerView = root.findViewById(R.id.rv_seller_pendingList);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterProductList = new AdapterPendingOrders(context, new AdapterPendingOrders.PendingOrdersButtonsListener() {
            @Override
            public void acceptOnClick(View view, int position) {
                progressDialog.show();
                acceptResponse(orderslists.get(position).getOrderId());
            }

            @Override
            public void rejectOnClick(View view, int position) {
                progressDialog.show();
                rejectResponse(orderslists.get(position).getOrderId());
            }
        });
        productRecyclerView.setAdapter(adapterProductList);

        tvNoResults = root.findViewById(R.id.tvFPO_noResults);
        pendingOrderResponse();

        return root;
    }

    private void acceptResponse(String orderId) {
        try {
            cipherOrderId = encrypt(orderId.getBytes());
            encryptedOrderId = encoderFunction(cipherOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final AcceptResonse acceptResonse = new AcceptResonse(encryptedOrderId);
        Call<AcceptResonse> resonseCall = equibiz_api_interface.acceptPendingOrder(acceptResonse, "Bearer " + AuthToken);

        resonseCall.enqueue(new Callback<AcceptResonse>() {
            @Override
            public void onResponse(@NotNull Call<AcceptResonse> call, @NotNull Response<AcceptResonse> response) {
                progressDialog.dismiss();
                AcceptResonse resonse1 = response.body();

            }

            @Override
            public void onFailure(@NotNull Call<AcceptResonse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void rejectResponse(String orderId) {
        try {
            cipherOrderId = encrypt(orderId.getBytes());
            encryptedOrderId = encoderFunction(cipherOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final RejectResponse rejectResponse = new RejectResponse(encryptedOrderId);
        Call<RejectResponse> responseCall = equibiz_api_interface.rejectPendingOrder(rejectResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<RejectResponse>() {
            @Override
            public void onResponse(@NotNull Call<RejectResponse> call, @NotNull Response<RejectResponse> response) {
                progressDialog.dismiss();
                RejectResponse resonse1 = response.body();

            }

            @Override
            public void onFailure(@NotNull Call<RejectResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void pendingOrderResponse() {
        try {
            cipherUserId = encrypt(userID.getBytes());
            encryptedUserID = encoderFunction(cipherUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final PendingOrderResponse pendingOrderResponse = new PendingOrderResponse(encryptedUserID);

        Call<PendingOrderResponse> responseCall = equibiz_api_interface.pendingOrdersResponse(
                pendingOrderResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<PendingOrderResponse>() {
            @Override
            public void onResponse(@NotNull Call<PendingOrderResponse> call, @NotNull Response<PendingOrderResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    PendingOrderResponse response1 = response.body();
                    assert response1 != null;
                    orderslists = response1.getOrderslist();

                    if (orderslists == null) {
                        tvNoResults.setVisibility(View.VISIBLE);
                        productRecyclerView.setVisibility(View.GONE);
                    } else {
                        productRecyclerView.setVisibility(View.VISIBLE);
                        tvNoResults.setVisibility(View.GONE);
                        adapterProductList.setOrderslists(orderslists);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<PendingOrderResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}