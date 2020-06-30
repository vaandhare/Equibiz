package in.birdvision.equibiz.buyer.orders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.PreBookingResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.Allratecard;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.product.ProductListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ConfirmOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button cancelBtn, preOrderNowBtn;
    Spinner spinnerLocation;

    String[] pickupLocations = {"Mumbai", "Delhi", "Uttar Pradesh", "Punjab", "Gujrat", "Madya Pradesh", "Goa",
            "Pune", "Aizawl"};

    String price_per_unit, delivery_time, delivery_location, product_colour, product_quantity,
            product_proID, product_sellerID, product_SellerProID, product_BuyerID, product_rateCardID;

    TextView orderColor, orderDeliveryLocation, orderDeliveryTime, orderDeliveryAdditionalTime, orderDeliveryCharges, orderQuantity,
            orderPPU, orderTotalCost, orderPreBookAmount, amountDeduction;

    String encryptedBuyerID, encryptedFinalPriceToDeduct, encryptedInsurance, encryptedProID, encryptedQuantityOrdered,
            encryptedRateCardID, encryptedSellerID, encryptedSellerProID, encryptedSellerTime, encryptedTotalPrice,
            encryptedUnitPrice;

    byte[] cipherBuyerID, cipherFinalPriceToDeduct, cipherInsurance, cipherProID, cipherQuantityOrdered,
            cipherRateCardID, cipherSellerID, cipherSellerProID, cipherSellerTime, cipherTotalPrice,
            cipherUnitPrice;

    CheckBox insuranceCB;

    Equibiz_API_Interface equibiz_api_interface;

    ProgressDialog progressDialog;

    Integer totalCost;
    long pre_book_amount;
    Integer insuranceValue = 0;
    ArrayList<Allratecard> allratecards;
    String walletBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initializeIDs();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        product_BuyerID = mySharedPreferences.getString("BuyerID", "xxxxx");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        allratecards = (ArrayList<Allratecard>) bundle.getSerializable("AllRateCards");

        product_colour = intent.getStringExtra("CO_Color");
        product_quantity = intent.getStringExtra("CO_OrderQuantity");
        price_per_unit = intent.getStringExtra("CO_PPU");
        delivery_location = intent.getStringExtra("CO_DeliveryLocation");
        delivery_time = intent.getStringExtra("CO_DeliveryTime");
        product_proID = intent.getStringExtra("CO_ProID");
        product_sellerID = intent.getStringExtra("CO_SellerID");
        product_SellerProID = intent.getStringExtra("CO_SellerProID");
        product_rateCardID = intent.getStringExtra("CO_RateCardID");

        orderColor.setText(product_colour);
        orderDeliveryLocation.setText(delivery_location);
        orderDeliveryTime.setText(delivery_time);
        orderPPU.setText(price_per_unit);
        orderQuantity.setText(product_quantity);

        int quantity = Integer.parseInt(product_quantity);
        int ppu = Integer.parseInt(price_per_unit);
        totalCost = quantity * ppu;
        orderTotalCost.setText(String.valueOf(totalCost));

        preOrderNowBtn.setOnClickListener(v -> {
            try {
                cipherBuyerID = encrypt(product_BuyerID.getBytes());
                encryptedBuyerID = encoderFunction(cipherBuyerID);

                cipherFinalPriceToDeduct = encrypt(String.valueOf(pre_book_amount).getBytes());
                encryptedFinalPriceToDeduct = encoderFunction(cipherFinalPriceToDeduct);

                cipherInsurance = encrypt(insuranceValue.toString().getBytes());
                encryptedInsurance = encoderFunction(cipherInsurance);

                cipherProID = encrypt(product_proID.getBytes());
                encryptedProID = encoderFunction(cipherProID);

                cipherQuantityOrdered = encrypt(product_quantity.getBytes());
                encryptedQuantityOrdered = encoderFunction(cipherQuantityOrdered);

                cipherSellerID = encrypt(product_sellerID.getBytes());
                encryptedSellerID = encoderFunction(cipherSellerID);

                cipherSellerTime = encrypt(delivery_time.getBytes());
                encryptedSellerTime = encoderFunction(cipherSellerTime);

                cipherTotalPrice = encrypt(String.valueOf(totalCost).getBytes());
                encryptedTotalPrice = encoderFunction(cipherTotalPrice);

                cipherUnitPrice = encrypt(price_per_unit.getBytes());
                encryptedUnitPrice = encoderFunction(cipherUnitPrice);

                cipherSellerProID = encrypt(product_SellerProID.getBytes());
                encryptedSellerProID = encoderFunction(cipherSellerProID);

                cipherRateCardID = encrypt(product_rateCardID.getBytes());
                encryptedRateCardID = encoderFunction(cipherRateCardID);

            } catch (Exception e) {
                e.printStackTrace();
            }
            progressDialog.show();
            preOrderBooking();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    private void preOrderBooking() {
        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        String token = mySharedPreferences.getString("LoginToken", "xxxxx");

        final PreBookingResponse preBookingResponse = new PreBookingResponse(encryptedBuyerID, encryptedFinalPriceToDeduct,
                encryptedInsurance, encryptedProID, encryptedQuantityOrdered, encryptedRateCardID, encryptedSellerID,
                encryptedSellerProID, encryptedSellerTime, encryptedTotalPrice, encryptedUnitPrice);

        Call<PreBookingResponse> preBookingResponseCall = equibiz_api_interface.preBookingResponse(preBookingResponse, "Bearer " + token);

        preBookingResponseCall.enqueue(new Callback<PreBookingResponse>() {
            @Override
            public void onResponse(@NotNull Call<PreBookingResponse> call, @NotNull Response<PreBookingResponse> response) {
                PreBookingResponse preBookingResponse1 = response.body();
                progressDialog.dismiss();
                assert preBookingResponse1 != null;
                walletBalance = preBookingResponse1.getWalletbal();
                showCustomDialog();
            }

            @Override
            public void onFailure(@NotNull Call<PreBookingResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(ConfirmOrderActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ConfirmOrderActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeIDs() {

        orderColor = findViewById(R.id.tvCO_color);
        orderDeliveryLocation = findViewById(R.id.tvCO_delivery_location);
        orderDeliveryTime = findViewById(R.id.tvCO_time);
        orderDeliveryAdditionalTime = findViewById(R.id.tvCO_additional_time);
        orderDeliveryCharges = findViewById(R.id.tvCO_delivery_charges);
        orderQuantity = findViewById(R.id.tvCO_order_quantity);
        orderPPU = findViewById(R.id.tvCO_PPU);
        orderTotalCost = findViewById(R.id.tvCO_total_cost);
        orderPreBookAmount = findViewById(R.id.tvCO_pre_book_amount);

        insuranceCB = findViewById(R.id.cbCO_insurance);
        amountDeduction = findViewById(R.id.tvCO_amount_deduction);

        spinnerLocation = findViewById(R.id.spinner_pickup_locations);
        spinnerLocation.setOnItemSelectedListener(ConfirmOrderActivity.this);

        ArrayAdapter<String> locations = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pickupLocations);
        locations.setDropDownViewResource(R.layout.dropdown_menu);
        spinnerLocation.setAdapter(locations);

        cancelBtn = findViewById(R.id.btn_co_cancel);
        preOrderNowBtn = findViewById(R.id.btn_co_pro_order_now);

        cancelBtn.setOnClickListener(v -> onBackPressed());

    }

    @SuppressLint("SetTextI18n")
    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.success_dialog_activity, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.close, (dialog, which)
                -> startActivity(new Intent(ConfirmOrderActivity.this, ProductListActivity.class)));

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(Dialog.BUTTON_NEGATIVE).
                setBackgroundColor(Color.parseColor("#FBFBC3"));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        orderDeliveryAdditionalTime.setText("T + " + allratecards.get(position).getDeltime() + "\n(After Confirmed transaction)");
        orderDeliveryCharges.setText(String.valueOf(allratecards.get(position).getDelcharges()));
        pre_book_amount = (totalCost / 10) + allratecards.get(position).getDelcharges();
        orderPreBookAmount.setText(String.valueOf(pre_book_amount));

        insuranceCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                insuranceValue = 1;
                amountDeduction.setText("₹ " + (pre_book_amount + 50) + "will be deducted from your wallet Balance.");
            } else {
                insuranceValue = 0;
                amountDeduction.setText("₹ " + pre_book_amount + "will be deducted from your wallet Balance.");
            }
        });

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        insuranceCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                insuranceValue = 1;
                amountDeduction.setText("₹ " + (pre_book_amount + 50) + "will be deducted from your wallet Balance.");
            } else {
                insuranceValue = 0;
                amountDeduction.setText("₹ " + pre_book_amount + "will be deducted from your wallet Balance.");
            }
        });
    }
}
