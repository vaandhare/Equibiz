package in.birdvision.equibiz.product;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.product.Productdatum;
import in.birdvision.equibiz.API.equibizAPI.product.filterBrand.BrandFilterResponse;
import in.birdvision.equibiz.API.equibizAPI.product.filterModel.ModelFilterResponse;
import in.birdvision.equibiz.API.equibizAPI.product.productList.ProductListResponse;
import in.birdvision.equibiz.API.equibizAPI.product.searchProduct.SearchProductResponse;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.userInfo.UserProfile;
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

    ImageView user_profile;
    Equibiz_API_Interface equibiz_api_interface;
    AdapterProductList adapterProductList;
    RecyclerView productRecyclerView;
    ProgressDialog progressDialog;
    List<Productdatum> getProductdata;

    final String[] brandNames = {"Noki", "Sams", "MI", "iPhone", "Oppo", "POCO"};
    final String[] modelNames = {"M101", "S200", "S201", "oppo"};
    AlertDialog alertDialogBrand, alertDialogModel;
    String encryptedSearchText, encryptedBrand, encryptedModel, selectedBrand, selectedModel, AuthToken;
    byte[] cipherSearchText, cipherBrand, cipherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialToolbar toolbar = findViewById(R.id.category1_toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_product_list);

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "xxxxx");


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

        BottomNavigationView bottomNavigationView = findViewById(R.id.category_bottomnavbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.Menu_product_by_brand:
                            productBrandAlert();
                            break;
                        case R.id.Menu_product_by_model:
                            productModelAlert();
                            break;
                    }
                    return true;
                }
        );

    }

    private void productModelAlert() {
        AlertDialog.Builder builderM = new AlertDialog.Builder(this);
        builderM.setTitle("Select the Brand name");

        int checkedItem = 0;
        builderM.setSingleChoiceItems(modelNames, checkedItem, (dialog, which) -> selectedModel = modelNames[which]);

        builderM.setPositiveButton("Done", (dialog, position) -> {
            productModelResponse(selectedModel);
            progressDialog.show();
            dialog.dismiss();
        });

        alertDialogModel = builderM.create();
        alertDialogModel.setCanceledOnTouchOutside(false);
        alertDialogModel.show();
    }

    private void productModelResponse(String selectedModel) {
        try {
            cipherModel = encrypt(selectedModel.getBytes());
            encryptedModel = encoderFunction(cipherModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ModelFilterResponse filterResponse = new ModelFilterResponse(encryptedModel);
        Call<ModelFilterResponse> modelFilterResponseCall = equibiz_api_interface.modelFilterResponse(filterResponse, "Bearer " + AuthToken);

        modelFilterResponseCall.enqueue(new Callback<ModelFilterResponse>() {
            @Override
            public void onResponse(@NotNull Call<ModelFilterResponse> call, @NotNull Response<ModelFilterResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ModelFilterResponse response1 = response.body();
                    assert response1 != null;
                    getProductdata = response1.getProductdata();
                    adapterProductList.setProductdata(getProductdata);
                    adapterProductList.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ModelFilterResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ProductListActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProductListActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void productBrandAlert() {
        AlertDialog.Builder builderB = new AlertDialog.Builder(this);
        builderB.setTitle("Select the Brand name");
        int selectedBID = 0;
        builderB.setSingleChoiceItems(brandNames, selectedBID, (dialog, brand) -> selectedBrand = brandNames[brand]);

        builderB.setPositiveButton("Done", ((dialog, which) -> {
            productBrandResponse(selectedBrand);
            progressDialog.show();
            dialog.dismiss();
        }));

        alertDialogBrand = builderB.create();
        alertDialogBrand.setCanceledOnTouchOutside(false);
        alertDialogBrand.show();
    }

    private void productBrandResponse(String selectedBrand) {
        try {
            cipherBrand = encrypt(selectedBrand.getBytes());
            encryptedBrand = encoderFunction(cipherBrand);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final BrandFilterResponse brandFilterResponse = new BrandFilterResponse(encryptedBrand);
        Call<BrandFilterResponse> responseCall = equibiz_api_interface.brandFilterResponse(brandFilterResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<BrandFilterResponse>() {
            @Override
            public void onResponse(@NotNull Call<BrandFilterResponse> call, @NotNull Response<BrandFilterResponse> response) {
                progressDialog.dismiss();
                BrandFilterResponse response1 = response.body();
                assert response1 != null;
                getProductdata = response1.getProductdata();
                adapterProductList.setProductdata(getProductdata);
                adapterProductList.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<BrandFilterResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ProductListActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProductListActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void getSearchResults(String query) {
        try {
            cipherSearchText = encrypt(query.getBytes());
            encryptedSearchText = encoderFunction(cipherSearchText);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        final ProductListResponse allProductsResponse = new ProductListResponse();
        Call<ProductListResponse> allProductsResponseCall = equibiz_api_interface.allProductResponse(allProductsResponse, "Bearer " + AuthToken);

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
