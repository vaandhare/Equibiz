package in.birdvision.equibiz.seller.ui.product_listing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.EquibizSeller_API_interface;
import in.birdvision.equibiz.API.equibizAPI.seller.Adminproduct;
import in.birdvision.equibiz.API.equibizAPI.seller.adminProduct.AdminProductResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProductListingFragment extends Fragment implements AdapterAdminProductList.OnItemClickListener {

    EquibizSeller_API_interface equibiz_api_interface;
    AdapterAdminProductList adapterProductList;
    RecyclerView productRecyclerView;
    ProgressDialog progressDialog;
    List<Adminproduct> adminproducts;
    adminFragmentToProductDetails fragment;

    TextView tvNoResults;
    String AuthToken, userID, encryptedUserID;
    byte[] cipherUserId;
    Context context;

    @Override
    public void onAttach(@NonNull Activity context) {
        super.onAttach(context);
        try {
            fragment = (adminFragmentToProductDetails) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_seller_admin_products, container, false);
        context = root.getContext();

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");
        userID = mySharedPreferences.getString("SellerID", "");

        equibiz_api_interface = EquibizApiService.getClient().create(EquibizSeller_API_interface.class);
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(R.style.ProgressBar);
        progressDialog.show();

        productRecyclerView = root.findViewById(R.id.rv_seller_admin_productList);
        productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterProductList = new AdapterAdminProductList(context);
        productRecyclerView.setAdapter(adapterProductList);
        adapterProductList.setOnItemClickListener(this);

        adminProductsResponse();
        tvNoResults = root.findViewById(R.id.tvFSAP_noResults);

        return root;
    }

    private void adminProductsResponse() {

        try {
            cipherUserId = encrypt(userID.getBytes());
            encryptedUserID = encoderFunction(cipherUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final AdminProductResponse adminProductResponse = new AdminProductResponse(encryptedUserID);

        Call<AdminProductResponse> responseCall = equibiz_api_interface.adminProductResponse(adminProductResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<AdminProductResponse>() {
            @Override
            public void onResponse(@NotNull Call<AdminProductResponse> call, @NotNull Response<AdminProductResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    AdminProductResponse response1 = response.body();
                    assert response1 != null;
                    adminproducts = response1.getAdminproducts();

                    if (adminproducts == null) {
                        tvNoResults.setVisibility(View.VISIBLE);
                        productRecyclerView.setVisibility(View.GONE);
                    } else {
                        productRecyclerView.setVisibility(View.VISIBLE);
                        tvNoResults.setVisibility(View.GONE);
                        adapterProductList.setProductdata(adminproducts);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AdminProductResponse> call, @NotNull Throwable t) {
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
        Adminproduct adminproduct = adminproducts.get(position);
        fragment.adminProductData(userID, adminproduct.getProductinfo().get_id(), AuthToken);
    }

    public interface adminFragmentToProductDetails {
        void adminProductData(String userId, String productID, String authToken);
    }
}