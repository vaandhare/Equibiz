package in.birdvision.equibiz.buyer.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Allratecard;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.ProductDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Productdatum;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Sellerlist;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.orders.ConfirmOrderActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_COLOR;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_INTERNAL_MEMORY;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_PRO_ID;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_RAM;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProductActivity extends AppCompatActivity {

    Button addToCartBtn, btnGoBack;
    String encryptedColor, encryptedRam, encryptedInternalMemory, encryptedID;
    byte[] cipherColor, cipherRam, cipherIntMemory, cipherID;
    Equibiz_API_Interface equibiz_api_interface;

    //UI
    ImageView productImg1, backImage;
    TextView productName, productSpecs, totalStock, avgPrice, productRam, productExpandableMemory, productInternalMemory,
            productFrontCam, productPrimaryCam, productOS, productBattery, productNetwork, productSims,
            productDimensions, productScreenSize, productProcessor, sellerTotalQuantity, sellerMinQuantity, sellerPPU, sellerLocation,
            sellerColor, sellerDeliveryTime, activityTitle;

    EditText sellerOrderQuantity;

    String COColor, CODeliveryLocation, COTime, COOrderQuantity, COPPU, COProID, COSellerID, COSellerProID, CORateCardID;

    Integer COMinQuantity, COTotalQuantity;
    String ProductColor, ProductInternalMemory, ProductID, ProductRam;
    List<Allratecard> allratecards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_product);
        initializeIDs();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);
        productDetailsResponse();
    }

    private void productDetailsResponse() {
        Intent intent = getIntent();
        ProductColor = intent.getStringExtra(EXTRA_COLOR);
        ProductInternalMemory = intent.getStringExtra(EXTRA_INTERNAL_MEMORY);
        ProductID = intent.getStringExtra(EXTRA_PRO_ID);
        ProductRam = intent.getStringExtra(EXTRA_RAM);

        try {
            assert ProductColor != null;
            cipherColor = encrypt(ProductColor.getBytes());
            encryptedColor = encoderFunction(cipherColor);

            assert ProductInternalMemory != null;
            cipherIntMemory = encrypt(ProductInternalMemory.getBytes());
            encryptedInternalMemory = encoderFunction(cipherIntMemory);

            assert ProductID != null;
            cipherID = encrypt(ProductID.getBytes());
            encryptedID = encoderFunction(cipherID);

            assert ProductRam != null;
            cipherRam = encrypt(ProductRam.getBytes());
            encryptedRam = encoderFunction(cipherRam);

        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        String token = mySharedPreferences.getString("LoginToken", "xxxxx");

        final ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse(encryptedColor,
                encryptedInternalMemory, encryptedID, encryptedRam);

        Call<ProductDetailsResponse> productDetailsResponseCall = equibiz_api_interface.productDetailsResponse(productDetailsResponse, "Bearer " + token);

        productDetailsResponseCall.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProductDetailsResponse> call, @NotNull Response<ProductDetailsResponse> response) {
                ProductDetailsResponse productDetailsResponse1 = response.body();
                if (productDetailsResponse1 != null) {
                    allratecards = productDetailsResponse1.getAllratecards();
                    CORateCardID = allratecards.get(0).get_id();
                    changeUIData(productDetailsResponse1);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ProductDetailsResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ProductActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ProductActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void changeUIData(ProductDetailsResponse productDetailsResponse1) {

        Productdatum productdatum = productDetailsResponse1.getProductdata().get(0);

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + productdatum.getProductinfo().getpImages().get(0);
        Glide.with(ProductActivity.this).load(IMG_URL).into(productImg1);

        String brandname = productdatum.getBrandinfo().getBrandname();
        String modelname = productdatum.getProductinfo().getpModelNo();
        productName.setText(brandname + " " + modelname);
        activityTitle.setText(brandname + " " + modelname);
        String ram = productdatum.getRamMob();
        String internalMemory = productdatum.getInternalMemory();
        COColor = productdatum.getColor();
        productSpecs.setText("( " + ram + " GB/ " + internalMemory + " GB/ " + COColor + " )");

        totalStock.setText(String.valueOf(productdatum.getStocksum()));
        avgPrice.setText(String.valueOf(productdatum.getAvgpricesum()));
        productRam.setText(ram + " GB");
        productExpandableMemory.setText(productdatum.getProductinfo().getpExpandableMemory() + " GB");
        productInternalMemory.setText(productdatum.getInternalMemory() + " GB");
        productFrontCam.setText(productdatum.getProductinfo().getpFrontCamera() + " MP");
        productPrimaryCam.setText(productdatum.getProductinfo().getpPrimaryCamera() + " MP");
        productOS.setText(productdatum.getProductinfo().getpOsType());
        productBattery.setText(productdatum.getProductinfo().getpBatteryPower());
        productNetwork.setText(productdatum.getProductinfo().getpNetworkType());
        productSims.setText(String.valueOf(productdatum.getProductinfo().getpSimSlots()));
        productDimensions.setText(productdatum.getProductinfo().getpDimensions());
        productScreenSize.setText(productdatum.getProductinfo().getpScreenSize());
        productProcessor.setText(productdatum.getProductinfo().getpProcessor());

        List<Sellerlist> sellerlist = productDetailsResponse1.getSellerlist();
        if (sellerlist.isEmpty()) {
            sellerTotalQuantity.setVisibility(View.GONE);
            sellerMinQuantity.setVisibility(View.GONE);
            sellerColor.setVisibility(View.GONE);
            sellerDeliveryTime.setVisibility(View.GONE);
            sellerPPU.setVisibility(View.GONE);
            sellerLocation.setVisibility(View.GONE);
            findViewById(R.id.tvPS_COL).setVisibility(GONE);
            findViewById(R.id.tvPS_DT).setVisibility(GONE);
            findViewById(R.id.tvPS_PU).setVisibility(GONE);
            findViewById(R.id.tvPS_TQ).setVisibility(GONE);
            findViewById(R.id.tvPS_LOC).setVisibility(GONE);
            findViewById(R.id.tvPS_MQ).setVisibility(GONE);
        } else {
            Sellerlist sellerList1 = sellerlist.get(0);
            COPPU = String.valueOf(sellerList1.getAvgPrice());
            CODeliveryLocation = sellerList1.getLocation();
            COTime = sellerList1.getTimeToDel();
            COProID = sellerList1.getProductId();
            COSellerID = sellerList1.getUserId();
            COSellerProID = sellerList1.get_id();
            COTotalQuantity = sellerList1.getAvailableStock();
            sellerTotalQuantity.setText(String.valueOf(COTotalQuantity));
            COMinQuantity = sellerList1.getMinqty();
            sellerMinQuantity.setText(String.valueOf(COMinQuantity));
            sellerOrderQuantity.setHint(String.valueOf(sellerList1.getMinqty()));
            sellerPPU.setText(COPPU);
            sellerLocation.setText(CODeliveryLocation);
            sellerColor.setText(sellerList1.getColor());
            sellerDeliveryTime.setText(COTime);
        }

        addToCartBtn.setOnClickListener(v -> {
            COOrderQuantity = sellerOrderQuantity.getText().toString();
            int quantity = Integer.parseInt(COOrderQuantity);
            if (sellerlist.isEmpty())
                showCustomDialog("No Seller Available", "No seller is available for this product. Come back again to check availability or Contact Admin.");
            else if (COOrderQuantity.isEmpty() || quantity < COMinQuantity || quantity > COTotalQuantity) {
                showCustomDialog("Invalid Input", "Quantity cannot be Empty or Less than Minimum Quantity or Greater than Total Quantity given by seller.");
            } else {
                Intent intent = new Intent(ProductActivity.this, ConfirmOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AllRateCards", (Serializable) allratecards);
                intent.putExtra("BUNDLE", bundle);
                intent.putExtra("CO_Color", COColor);
                intent.putExtra("CO_DeliveryLocation", CODeliveryLocation);
                intent.putExtra("CO_DeliveryTime", COTime);
                intent.putExtra("CO_OrderQuantity", COOrderQuantity);
                intent.putExtra("CO_PPU", COPPU);
                intent.putExtra("CO_ProID", COProID);
                intent.putExtra("CO_SellerID", COSellerID);
                intent.putExtra("CO_SellerProID", COSellerProID);
                intent.putExtra("CO_RateCardID", CORateCardID);
                startActivity(intent);
            }
        });

    }

    private void showCustomDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("Ok", (dialog, which) -> dialog.cancel());
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void initializeIDs() {
        activityTitle = findViewById(R.id.tv_product);
        backImage = findViewById(R.id.img_back_product);
        backImage.setOnClickListener(v -> onBackPressed());
        productImg1 = findViewById(R.id.product_poster_imag);
        productName = findViewById(R.id.tvP_product_name);
        productSpecs = findViewById(R.id.tvP_product_specs);
        totalStock = findViewById(R.id.tvP_total_stock);
        avgPrice = findViewById(R.id.tvP_avg_price);
        productRam = findViewById(R.id.tvP_ram);
        productExpandableMemory = findViewById(R.id.tvP_expandableRam);
        productInternalMemory = findViewById(R.id.tvP_internalRam);
        productFrontCam = findViewById(R.id.tvP_frontCam);
        productPrimaryCam = findViewById(R.id.tvP_primaryCam);
        productOS = findViewById(R.id.tvP_OS);
        productBattery = findViewById(R.id.tvP_battery);
        productNetwork = findViewById(R.id.tvP_network);
        productSims = findViewById(R.id.tvP_sims);
        productDimensions = findViewById(R.id.tvP_dimensions);
        productScreenSize = findViewById(R.id.tvP_screenSize);
        productProcessor = findViewById(R.id.tvP_processor);
        addToCartBtn = findViewById(R.id.btn_pro_book_now);
        btnGoBack = findViewById(R.id.btn_go_back_now);
        btnGoBack.setOnClickListener(v -> onBackPressed());
        sellerTotalQuantity = findViewById(R.id.tvPS_totalQuantity);
        sellerMinQuantity = findViewById(R.id.tvPS_minQuantity);
        sellerPPU = findViewById(R.id.tvPS_ppu);
        sellerLocation = findViewById(R.id.tvPS_location);
        sellerColor = findViewById(R.id.tvPS_color);
        sellerDeliveryTime = findViewById(R.id.tvPS_deliveryTime);
        sellerOrderQuantity = findViewById(R.id.etvPS_orderQuanitity);
    }


}