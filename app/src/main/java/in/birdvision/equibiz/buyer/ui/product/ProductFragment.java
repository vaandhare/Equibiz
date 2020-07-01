package in.birdvision.equibiz.buyer.ui.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_COLOR;
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_INTERNAL_MEMORY;
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_PRO_ID;
import static in.birdvision.equibiz.buyer.ui.product.ProductListFragment.EXTRA_RAM;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encoderFunction;
import static in.birdvision.equibiz.userInfo.encryption.Encryption.encrypt;

public class ProductFragment extends Fragment {

    Button addToCartBtn, btnGoBack;
    String encryptedColor, encryptedRam, encryptedInternalMemory, encryptedID;
    byte[] cipherColor, cipherRam, cipherIntMemory, cipherID;
    Equibiz_API_Interface equibiz_api_interface;

    //UI
    ImageView productImg1;
    TextView productName, productSpecs, totalStock, avgPrice, productRam, productExpandableMemory, productInternalMemory,
            productFrontCam, productPrimaryCam, productOS, productBattery, productNetwork, productSims,
            productDimensions, productScreenSize, productProcessor, sellerTotalQuantity, sellerPPU, sellerLocation,
            sellerColor, sellerDeliveryTime, sellerMinQuantity;

    EditText sellerOrderQuantity;

    String COColor, CODeliveryLocation, COTime, COOrderQuantity, COPPU, COProID, COSellerID, COSellerProID,
            CORateCardID, strProductName;

    int minQuantity, maxQuantity;

    List<Allratecard> allratecards = new ArrayList<>();

    Context context;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProductListFragment fragment = new ProductListFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);

        context = view.getContext();
        initializeIDs();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);
        productDetailsResponse();

        return view;
    }

    private void productDetailsResponse() {
        assert getArguments() != null;
        String productColor = getArguments().getString(EXTRA_COLOR);
        String productInternalMemory = getArguments().getString(EXTRA_INTERNAL_MEMORY);
        String productID = getArguments().getString(EXTRA_PRO_ID);
        String productRam = getArguments().getString(EXTRA_RAM);

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

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
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
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void changeUIData(ProductDetailsResponse productDetailsResponse1) {

        Productdatum productdatum = productDetailsResponse1.getProductdata().get(0);

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + productdatum.getProductinfo().getpImages().get(0);
        Glide.with(context).load(IMG_URL).into(productImg1);

        String brandname = productdatum.getBrandinfo().getBrandname();
        String modelname = productdatum.getProductinfo().getpModelNo();
        strProductName = brandname + " " + modelname;
        productName.setText(strProductName);

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
            view.findViewById(R.id.tvPS_COL).setVisibility(GONE);
            view.findViewById(R.id.tvPS_DT).setVisibility(GONE);
            view.findViewById(R.id.tvPS_PU).setVisibility(GONE);
            view.findViewById(R.id.tvPS_TQ).setVisibility(GONE);
            view.findViewById(R.id.tvPS_LOC).setVisibility(GONE);
            view.findViewById(R.id.tvPS_MQ).setVisibility(GONE);
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
                Toast.makeText(context, "Cannot Proceed due to seller unavailable for this product", Toast.LENGTH_SHORT).show();
            else if (COOrderQuantity.isEmpty() || userOrderQuantity < minQuantity || userOrderQuantity > maxQuantity)
                Toast.makeText(context, "Orders cannot be 0 or empty or greater than total stock", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(context, ConfirmOrderActivity.class);
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

        productImg1 = view.findViewById(R.id.product_poster_imag);
        productName = view.findViewById(R.id.tvP_product_name);
        productSpecs = view.findViewById(R.id.tvP_product_specs);
        totalStock = view.findViewById(R.id.tvP_total_stock);
        avgPrice = view.findViewById(R.id.tvP_avg_price);
        productRam = view.findViewById(R.id.tvP_ram);
        productExpandableMemory = view.findViewById(R.id.tvP_expandableRam);
        productInternalMemory = view.findViewById(R.id.tvP_internalRam);
        productFrontCam = view.findViewById(R.id.tvP_frontCam);
        productPrimaryCam = view.findViewById(R.id.tvP_primaryCam);
        productOS = view.findViewById(R.id.tvP_OS);
        productBattery = view.findViewById(R.id.tvP_battery);
        productNetwork = view.findViewById(R.id.tvP_network);
        productSims = view.findViewById(R.id.tvP_sims);
        productDimensions = view.findViewById(R.id.tvP_dimensions);
        productScreenSize = view.findViewById(R.id.tvP_screenSize);
        productProcessor = view.findViewById(R.id.tvP_processor);
        addToCartBtn = view.findViewById(R.id.btn_pro_book_now);
        btnGoBack = view.findViewById(R.id.btn_go_back_now);
        btnGoBack.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ProductListFragment fragment = new ProductListFragment();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        });

        sellerTotalQuantity = view.findViewById(R.id.tvPS_totalQuantity);
        sellerPPU = view.findViewById(R.id.tvPS_ppu);
        sellerLocation = view.findViewById(R.id.tvPS_location);
        sellerColor = view.findViewById(R.id.tvPS_color);
        sellerDeliveryTime = view.findViewById(R.id.tvPS_deliveryTime);
        sellerOrderQuantity = view.findViewById(R.id.etvPS_orderQuanitity);
        sellerMinQuantity = view.findViewById(R.id.tvPS_minQuantity);
    }
}