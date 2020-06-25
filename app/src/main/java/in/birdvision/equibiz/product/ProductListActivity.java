package in.birdvision.equibiz.product;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.product.productList.ProductListResponse;
import in.birdvision.equibiz.API.equibizAPI.product.productList.Productdatum;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.userInfo.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity implements AdapterProductList.OnItemClickListener {

    public static final String EXTRA_COLOR = "productColor";
    public static final String EXTRA_RAM = "productRam";
    public static final String EXTRA_INTERNAL_MEMORY = "productInternalMemory";
    public static final String EXTRA_PRO_ID = "productID";

    ImageView user_profile;
    Equibiz_API_Interface equibiz_api_interface;
    AdapterProductList adapterProductList;
    RecyclerView productRecyclerView;
    ProgressDialog progressDialog;
    List<Productdatum> getProductdata;

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
        adapterProductList.setOnItemClickListener(ProductListActivity.this);

        productListResponse();

        user_profile = findViewById(R.id.img_user_profile);
        user_profile.setOnClickListener(v -> startActivity(new Intent(ProductListActivity.this, UserProfile.class)));
    }

    private void productListResponse() {
        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        String token = mySharedPreferences.getString("LoginToken", "xxxxx");

        final ProductListResponse allProductsResponse = new ProductListResponse();
        Call<ProductListResponse> allProductsResponseCall = equibiz_api_interface.allProductResponse(allProductsResponse, "Bearer " + token);

        allProductsResponseCall.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ProductListResponse productListResponse = response.body();
                    assert productListResponse != null;
                    getProductdata = productListResponse.getProductdata();
                    adapterProductList.setProductdata(getProductdata);
                }
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ProductListActivity.this, ProductActivity.class);
        Productdatum productdata = getProductdata.get(position);

        intent.putExtra(EXTRA_COLOR, productdata.get_id().getColor());
        intent.putExtra(EXTRA_INTERNAL_MEMORY, productdata.get_id().getInternalMemory());
        intent.putExtra(EXTRA_PRO_ID, productdata.get_id().getProId());
        intent.putExtra(EXTRA_RAM, productdata.get_id().getRamMob());

        startActivity(intent);
    }
}
