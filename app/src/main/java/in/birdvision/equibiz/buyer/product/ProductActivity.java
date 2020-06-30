package in.birdvision.equibiz.buyer.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Allratecard;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.ProductDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Productdatum;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Sellerlist;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.orders.ConfirmOrderActivity;
import in.birdvision.equibiz.buyer.orders.OrderActivity;
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

    Button addToCartBtn;
    String encryptedColor, encryptedRam, encryptedInternalMemory, encryptedID;
    byte[] cipherColor, cipherRam, cipherIntMemory, cipherID;
    Equibiz_API_Interface equibiz_api_interface;

    //UI
    ImageView productImg1;
    TextView productName, productSpecs, totalStock, avgPrice, productRam, productExpandableMemory, productInternalMemory,
            productFrontCam, productPrimaryCam, productOS, productBattery, productNetwork, productSims,
            productDimensions, productScreenSize, productProcessor, sellerTotalQuantity, sellerPPU, sellerLocation,
            sellerColor, sellerDeliveryTime, sellerMinQuantity, tvProductNameToolbar;

    EditText sellerOrderQuantity;

    String COColor, CODeliveryLocation, COTime, COOrderQuantity, COPPU, COProID, COSellerID, COSellerProID,
            CORateCardID, strProductName;

    int minQuantity, maxQuantity;

    List<Allratecard> allratecards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initializeIDs();

        Toolbar toolbar = findViewById(R.id.product_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);
        productDetailsResponse();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_share, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_shopping_basket) {
            startActivity(new Intent(ProductActivity.this, OrderActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey Equibiz have an amazing offer on " + strProductName);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

            return true;
        }
        return true;
    }

    private void productDetailsResponse() {
        Intent intent = getIntent();
        String productColor = intent.getStringExtra(EXTRA_COLOR);
        String productInternalMemory = intent.getStringExtra(EXTRA_INTERNAL_MEMORY);
        String productID = intent.getStringExtra(EXTRA_PRO_ID);
        String productRam = intent.getStringExtra(EXTRA_RAM);

        try {
            assert productColor != null;
            cipherColor = encrypt(productColor.getBytes());
            encryptedColor = encoderFunction(cipherColor);

            assert productInternalMemory != null;
            cipherIntMemory = encrypt(productInternalMemory.getBytes());
            encryptedInternalMemory = encoderFunction(cipherIntMemory);

            assert productID != null;
            cipherID = encrypt(productID.getBytes());
            encryptedID = encoderFunction(cipherID);

            assert productRam != null;
            cipherRam = encrypt(productRam.getBytes());
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
        strProductName = brandname + " " + modelname;
        productName.setText(strProductName);

        tvProductNameToolbar.setText(strProductName);

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

        List<Sellerlist> sellerList = productDetailsResponse1.getSellerlist();
        if (sellerList.isEmpty()) {
            findViewById(R.id.tvPS_COL).setVisibility(GONE);
            findViewById(R.id.tvPS_DT).setVisibility(GONE);
            findViewById(R.id.tvPS_PU).setVisibility(GONE);
            findViewById(R.id.tvPS_TQ).setVisibility(GONE);
            findViewById(R.id.tvPS_LOC).setVisibility(GONE);
            findViewById(R.id.tvPS_MQ).setVisibility(GONE);
            sellerTotalQuantity.setVisibility(GONE);
            sellerPPU.setVisibility(GONE);
            sellerLocation.setVisibility(GONE);
            sellerColor.setVisibility(GONE);
            sellerDeliveryTime.setVisibility(GONE);
        } else {
            Sellerlist sellerList1 = sellerList.get(0);
            COPPU = String.valueOf(sellerList1.getAvgPrice());
            CODeliveryLocation = sellerList1.getLocation();
            COTime = sellerList1.getTimeToDel();
            COProID = sellerList1.getProductId();
            COSellerID = sellerList1.getUserId();
            COSellerProID = sellerList1.get_id();
            maxQuantity = sellerList1.getAvailableStock();
            sellerTotalQuantity.setText(String.valueOf(maxQuantity));
            sellerPPU.setText(COPPU);
            sellerLocation.setText(CODeliveryLocation);
            sellerColor.setText(sellerList1.getColor());
            sellerDeliveryTime.setText(COTime);
            minQuantity = Integer.parseInt(sellerList1.getMinqty());
            sellerMinQuantity.setText(String.valueOf(minQuantity));
            sellerOrderQuantity.setHint(String.valueOf(minQuantity));
        }

        addToCartBtn.setOnClickListener(v -> {
            COOrderQuantity = sellerOrderQuantity.getText().toString();
            int userOrderQuantity = Integer.parseInt(COOrderQuantity);
            if (sellerList.isEmpty())
                Toast.makeText(ProductActivity.this, "Cannot Proceed due to seller unavailable for this product", Toast.LENGTH_SHORT).show();
            else if (COOrderQuantity.isEmpty() || userOrderQuantity < minQuantity || userOrderQuantity > maxQuantity)
                Toast.makeText(ProductActivity.this, "Orders cannot be 0 or empty or greater than total stock", Toast.LENGTH_SHORT).show();
            else {
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

    private void initializeIDs() {

        tvProductNameToolbar = findViewById(R.id.tv_product_activity);

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

        sellerTotalQuantity = findViewById(R.id.tvPS_totalQuantity);
        sellerPPU = findViewById(R.id.tvPS_ppu);
        sellerLocation = findViewById(R.id.tvPS_location);
        sellerColor = findViewById(R.id.tvPS_color);
        sellerDeliveryTime = findViewById(R.id.tvPS_deliveryTime);
        sellerOrderQuantity = findViewById(R.id.etvPS_orderQuanitity);
        sellerMinQuantity = findViewById(R.id.tvPS_minQuantity);
    }
}