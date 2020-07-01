package in.birdvision.equibiz.buyer.ui.product;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.Productdatum;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.filterBrand.BrandFilterResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.filterModel.ModelFilterResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productList.ProductListResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.searchProduct.SearchProductResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProductListFragment extends Fragment implements AdapterProductList.OnItemClickListener {

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

    fragmentToActivity fragment;

    final String[] brandNames = {"Noki", "Sams", "MI", "iPhone", "Oppo", "POCO"};
    final String[] modelNames = {"M101", "S200", "S201", "oppo"};
    AlertDialog alertDialogBrand, alertDialogModel;
    String encryptedSearchText, encryptedBrand, encryptedModel, selectedBrand, selectedModel, AuthToken;
    byte[] cipherSearchText, cipherBrand, cipherModel;

    Context context;

    @Override
    public void onAttach(@NonNull Activity context) {
        super.onAttach(context);
        try {
            fragment = (fragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product_list, container, false);
        context = root.getContext();
        setHasOptionsMenu(true);

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "xxxxx");

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(R.style.ProgressBar);
        progressDialog.show();

        productRecyclerView = root.findViewById(R.id.rv_productList);
        productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterProductList = new AdapterProductList(context);
        productRecyclerView.setAdapter(adapterProductList);
        adapterProductList.setOnItemClickListener(this);

        productListResponse();

        BottomNavigationView bottomNavigationView = root.findViewById(R.id.category_bottomnavbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.Menu_product_home:
                            progressDialog.show();
                            productListResponse();
                            return true;
                        case R.id.Menu_product_by_brand:
                            productBrandAlert();
                            return true;
                        case R.id.Menu_product_by_model:
                            productModelAlert();
                            return true;
                    }
                    return true;
                }
        );
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchResults(query);
//                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void productModelAlert() {
        AlertDialog.Builder builderM = new AlertDialog.Builder(context);
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
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void productBrandAlert() {
        AlertDialog.Builder builderB = new AlertDialog.Builder(context);
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
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
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
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
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
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

//        ProductFragment productFragment = new ProductFragment();
//        Bundle bundle = new Bundle();
//
//        Productdatum productdata = getProductdata.get(position);
//
//        bundle.putString(EXTRA_COLOR, productdata.get_id().getColor());
//        bundle.putString(EXTRA_INTERNAL_MEMORY, productdata.get_id().getInternalMemory());
//        bundle.putString(EXTRA_PRO_ID, productdata.get_id().getProId());
//        bundle.putString(EXTRA_RAM, productdata.get_id().getRamMob());
//
//        productFragment.setArguments(bundle);
//        getFragmentManager().beginTransaction().add(R.id.nav_host_fragment, productFragment).commit();

        Productdatum productdata = getProductdata.get(position);
        extraColor = productdata.get_id().getColor();
        extraIntMemory = productdata.get_id().getInternalMemory();
        extraProID = productdata.get_id().getProId();
        extraRam = productdata.get_id().getRamMob();

        fragment.getProductData(extraColor, extraIntMemory, extraProID, extraRam);

    }

    public interface fragmentToActivity {
        void getProductData(String productColor, String productInternalMemory, String productID, String productRam);
    }
}