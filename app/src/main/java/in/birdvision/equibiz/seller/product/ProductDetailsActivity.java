package in.birdvision.equibiz.seller.product;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.EquibizSeller_API_interface;
import in.birdvision.equibiz.API.equibizAPI.seller.Productinfo;
import in.birdvision.equibiz.API.equibizAPI.seller.adminProductDetails.AdminProductDetailsResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProductDetailsActivity extends AppCompatActivity {

    String[] productRam = {"1GB ", "2GB ", "3GB ", "4GB ", "6GB ", "8GB ", "10GB ", "12GB "};
    String[] productInternalStorage = {"16GB ", "32GB ", "64GB ", "128GB ", "256GB ", "512GB ", "1TB "};
    String[] productColor = {"Black ", "White ", "Red ", "Blue ", "Gray ", "Mix "};
    String[] dispatchDate = {"Booking Date", "Booking Date + 1", "Booking Date + 2", "Booking Date + 3"};

    Spinner spinnerRam, spinnerInternalStorage, spinnerColor, spinnerDate;

    TextView tvExpandableUpTo, tvFrontCamera, tvPrimaryCam, tvOS, tvBatteryCapacity, tvNetwork, tvSimSlot,
            tvDimensions, tvScreenSize, tvProcessor, tvBasePrice, tvBasePriceLow, tvBasePriceHigh, toolbarTitle, tvProductName;

    EditText etvQuantity, etvMinQuantity, etvPricePerUnit;

    CheckBox cbExport, cbSoldOut;

    Button btnSaveSubmit;

    ImageView productImg1;

    String encryptedUserID, encryptedProductID;
    byte[] cipherUserID, cipherProductID;

    EquibizSeller_API_interface equibizSeller_api_interface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_details);
        initializeIDs();

        equibizSeller_api_interface = EquibizApiService.getClient().create(EquibizSeller_API_interface.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(R.style.ProgressBar);
        progressDialog.show();

        getAdminProductResponse();

    }

    private void getAdminProductResponse() {

        Intent intent = getIntent();
        String productId = intent.getStringExtra("product_id");
        String userId = intent.getStringExtra("user_id");
        String authToken = intent.getStringExtra("authToken");

        try {
            assert userId != null;
            cipherUserID = encrypt(userId.getBytes());
            encryptedUserID = encoderFunction(cipherUserID);

            assert productId != null;
            cipherProductID = encrypt(productId.getBytes());
            encryptedProductID = encoderFunction(cipherProductID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final AdminProductDetailsResponse response = new AdminProductDetailsResponse(encryptedProductID, encryptedUserID);

        Call<AdminProductDetailsResponse> responseCall = equibizSeller_api_interface.adminProductDetailsResponse(response, "Bearer " + authToken);
        responseCall.enqueue(new Callback<AdminProductDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<AdminProductDetailsResponse> call, @NotNull Response<AdminProductDetailsResponse> response) {
                progressDialog.dismiss();
                AdminProductDetailsResponse response1 = response.body();
                assert response1 != null;
                changeUIData(response1);
            }

            @Override
            public void onFailure(@NotNull Call<AdminProductDetailsResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ProductDetailsActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProductDetailsActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void changeUIData(AdminProductDetailsResponse response1) {
        Productinfo productinfo = response1.getAdminproductdetails().get(0).getProductinfo();

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + productinfo.getpImages().get(0);
        Glide.with(this).load(IMG_URL).into(productImg1);

        String brandname = response1.getAdminproductdetails().get(0).getBrandinfo().getBrandname();
        String modelname = productinfo.getpModelNo();

        toolbarTitle.setText(brandname + " " + modelname);
        tvProductName.setText(brandname + " " + modelname);
        tvExpandableUpTo.setText(String.valueOf(productinfo.getpExpandableMemory()));
        tvFrontCamera.setText(String.valueOf(productinfo.getpFrontCamera()));
        tvPrimaryCam.setText(String.valueOf(productinfo.getpPrimaryCamera()));
        tvOS.setText(productinfo.getpOsType());
        tvBatteryCapacity.setText(productinfo.getpBatteryPower());
        tvNetwork.setText(productinfo.getpNetworkType());
        tvSimSlot.setText(String.valueOf(productinfo.getpSimSlots()));
        tvDimensions.setText(productinfo.getpDimensions());
        tvScreenSize.setText(productinfo.getpScreenSize());
        tvProcessor.setText(productinfo.getpProcessor());
        tvBasePrice.setText(String.valueOf(productinfo.getpBasePrice()));
        tvBasePriceLow.setText(String.valueOf(productinfo.getpBaseLow()));
        tvBasePriceHigh.setText(String.valueOf(productinfo.getpBaseHigh()));

    }

    private void initializeIDs() {
        productImg1 = findViewById(R.id.ASPD_img1);

        toolbarTitle = findViewById(R.id.tvASPD_seller_product);
        tvProductName = findViewById(R.id.tvASPD_product_name);
        tvExpandableUpTo = findViewById(R.id.tvSP_expandableRam);
        tvFrontCamera = findViewById(R.id.tvSP_frontCam);
        tvPrimaryCam = findViewById(R.id.tvSP_primaryCam);
        tvOS = findViewById(R.id.tvSP_OS);
        tvBatteryCapacity = findViewById(R.id.tvSP_battery);
        tvNetwork = findViewById(R.id.tvSP_network);
        tvSimSlot = findViewById(R.id.tvSP_sims);
        tvDimensions = findViewById(R.id.tvSP_dimensions);
        tvScreenSize = findViewById(R.id.tvSP_screenSize);
        tvProcessor = findViewById(R.id.tvSP_processor);
        tvBasePrice = findViewById(R.id.tvSP_basePrice);
        tvBasePriceLow = findViewById(R.id.tvSP_basePriceLow);
        tvBasePriceHigh = findViewById(R.id.tvSP_basePriceHigh);

        etvQuantity = findViewById(R.id.etvSP_quantity);
        etvMinQuantity = findViewById(R.id.etvSP_minQuantity);
        etvPricePerUnit = findViewById(R.id.etvSP_pricePerUnit);

        btnSaveSubmit = findViewById(R.id.btnSP_saveSubmit);

        spinnerRam = findViewById(R.id.spinnerSP_ram);
        spinnerRam.setOnItemSelectedListener(new RamSpinnerClass());
        ArrayAdapter<String> rams = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productRam);
        rams.setDropDownViewResource(R.layout.dropdown_menu);
        spinnerRam.setAdapter(rams);

        spinnerInternalStorage = findViewById(R.id.spinnerSP_internalRam);
        spinnerInternalStorage.setOnItemSelectedListener(new StorageSpinnerClass());
        ArrayAdapter<String> internalStorage = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productInternalStorage);
        internalStorage.setDropDownViewResource(R.layout.dropdown_menu);
        spinnerInternalStorage.setAdapter(rams);

        spinnerColor = findViewById(R.id.spinnerSP_color);
        spinnerColor.setOnItemSelectedListener(new ColorSpinnerClass());
        ArrayAdapter<String> color = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productColor);
        color.setDropDownViewResource(R.layout.dropdown_menu);
        spinnerColor.setAdapter(color);

        spinnerDate = findViewById(R.id.spinnerSP_dispatchDate);
        spinnerDate.setOnItemSelectedListener(new DispatchDateSpinnerClass());
        ArrayAdapter<String> date = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dispatchDate);
        date.setDropDownViewResource(R.layout.dropdown_menu);
        spinnerDate.setAdapter(date);

        cbExport = findViewById(R.id.cbSP_forExportOnly);
        cbSoldOut = findViewById(R.id.cbSP_soldOut);
    }


    private static class RamSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private static class StorageSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private static class ColorSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private static class DispatchDateSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}