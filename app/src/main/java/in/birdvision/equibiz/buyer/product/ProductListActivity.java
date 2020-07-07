package in.birdvision.equibiz.buyer.product;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.Productdatum;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productList.ProductListResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.searchProduct.SearchProductResponse;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.BuyerHomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProductListActivity extends AppCompatActivity implements AdapterProductList.OnItemClickListener {

    public static final String EXTRA_COLOR = "productColor";
    public static final String EXTRA_RAM = "productRam";
    public static final String EXTRA_INTERNAL_MEMORY = "productInternalMemory";
    public static final String EXTRA_PRO_ID = "productID";

    String extraColor, extraRam, extraIntMemory, extraProID;

    Equibiz_API_Interface equibiz_api_interface;
    AdapterProductList adapterProductList;
    RecyclerView productRecyclerView;
    ProgressDialog progressDialog;
    List<Productdatum> getProductdata;
    List<String> menuFilterItems;
    ImageView imgBack;
    TextView noResultProduct;
    String encryptedSearchText, encryptedBrand, selectedBrand = "All", AuthToken;
    byte[] cipherSearchText, cipherBrand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        MaterialToolbar toolbar = findViewById(R.id.buyer_productList_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "xxxxx");

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(R.style.ProgressBar);
        progressDialog.show();

        productRecyclerView = findViewById(R.id.rv_productList);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapterProductList = new AdapterProductList(this);
        productRecyclerView.setAdapter(adapterProductList);
        adapterProductList.setOnItemClickListener(this);

        noResultProduct = findViewById(R.id.tvPL_noResults);
        imgBack = findViewById(R.id.img_back_buyer_productList);
        imgBack.setOnClickListener(v -> startActivity(new Intent(this, BuyerHomeActivity.class)));
        productListResponse();

    }

    @Override
    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchResults(query);
                Toast.makeText(ProductListActivity.this, query, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        SubMenu menu1 = menu.addSubMenu("Filter By");
        menu1.add("All");
//        for(int position = 0; position < menuFilterItems.size(); position++){
//            menu1.add(menuFilterItems.get(position));
//        }

        return true;
    }

    public void getSearchResults(String query) {
        try {
            cipherSearchText = encrypt(query.getBytes());
            encryptedSearchText = encoderFunction(cipherSearchText);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
        final SearchProductResponse searchProductResponse = new SearchProductResponse(encryptedSearchText);

        Call<SearchProductResponse> responseCall = equibiz_api_interface.searchProductResponse(searchProductResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<SearchProductResponse>() {
            @Override
            public void onResponse(@NotNull Call<SearchProductResponse> call, @NotNull Response<SearchProductResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    SearchProductResponse productListResponse = response.body();
                    assert productListResponse != null;
                    getProductdata = productListResponse.getProductdata();
                    adapterProductList.setProductdata(getProductdata);
                }
            }

            @Override
            public void onFailure(@NotNull Call<SearchProductResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ProductListActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProductListActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void productListResponse() {

        try {
            cipherBrand = encrypt(selectedBrand.getBytes());
            encryptedBrand = encoderFunction(cipherBrand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ProductListResponse allProductsResponse = new ProductListResponse(encryptedBrand);
        Call<ProductListResponse> allProductsResponseCall = equibiz_api_interface.productListResponse(allProductsResponse, "Bearer " + AuthToken);

        allProductsResponseCall.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ProductListResponse productListResponse = response.body();
                    assert productListResponse != null;
                    getProductdata = productListResponse.getProducts();

                    if (getProductdata == null) {
                        noResultProduct.setVisibility(View.VISIBLE);
                        productRecyclerView.setVisibility(View.GONE);
                    } else {
                        productRecyclerView.setVisibility(View.VISIBLE);
                        noResultProduct.setVisibility(View.GONE);
                        adapterProductList.setProductdata(getProductdata);
                    }

//                    for(int position=0; position<getProductdata.size(); position++)
//                        menuFilterItems.add(productListResponse.getBranddata().get(position).getBrandinfo().getBrandname());
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

        Productdatum productdata = getProductdata.get(position);
        extraColor = productdata.get_id().getColor();
        extraIntMemory = productdata.get_id().getInternalMemory();
        extraProID = productdata.get_id().getProId();
        extraRam = productdata.get_id().getRamMob();


        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(EXTRA_COLOR, extraColor);
        intent.putExtra(EXTRA_RAM, extraRam);
        intent.putExtra(EXTRA_INTERNAL_MEMORY, extraIntMemory);
        intent.putExtra(EXTRA_PRO_ID, extraProID);

        startActivity(intent);
    }
}