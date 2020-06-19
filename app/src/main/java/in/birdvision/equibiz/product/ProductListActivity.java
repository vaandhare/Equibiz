package in.birdvision.equibiz.product;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.product.productList.ProductListResponse;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.userInfo.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    ImageView user_profile;
    Equibiz_API_Interface equibiz_api_interface;
    AdapterProductList adapterProductList;
    RecyclerView productRecyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);
        progressDialog = new ProgressDialog(ProductListActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        productRecyclerView = findViewById(R.id.rv_productList);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapterProductList = new AdapterProductList(ProductListActivity.this);
        productRecyclerView.setAdapter(adapterProductList);

        productListResponse();

        user_profile = findViewById(R.id.img_user_profile);
        user_profile.setOnClickListener(v -> startActivity(new Intent(ProductListActivity.this, UserProfile.class)));
    }

    private void productListResponse() {
        final ProductListResponse allProductsResponse = new ProductListResponse();
        Call<ProductListResponse> allProductsResponseCall = equibiz_api_interface.allProductResponse(allProductsResponse);

        allProductsResponseCall.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ProductListResponse productListResponse = response.body();
                    assert productListResponse != null;
                    adapterProductList.setProductdata(productListResponse.getProductdata());
                }
                assert response.body() != null;
                Toast.makeText(ProductListActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ProductListActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProductListActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
