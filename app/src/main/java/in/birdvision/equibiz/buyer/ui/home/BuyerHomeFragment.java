/*
 * *
 *  * Created by Vaibhav Andhare on 12/7/20 10:02 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.buyer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.home.Bannerdatum;
import in.birdvision.equibiz.API.equibizAPI.buyer.home.Bestdeal;
import in.birdvision.equibiz.API.equibizAPI.buyer.home.Branddatum;
import in.birdvision.equibiz.API.equibizAPI.buyer.home.BuyerHomeResponse;
import in.birdvision.equibiz.R;
import in.birdvision.equibiz.buyer.product.ProductActivity;
import in.birdvision.equibiz.buyer.product.ProductListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_COLOR;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_INTERNAL_MEMORY;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_PRO_ID;
import static in.birdvision.equibiz.buyer.product.ProductListActivity.EXTRA_RAM;

public class BuyerHomeFragment extends Fragment {

    public static final String BRAND_NAME = "brand_name";
    Equibiz_API_Interface equibiz_api_interface;
    AdapterBanner adapterBanner;
    AdapterBrands adapterBrands;
    AdapterBestDeals adapterBestDeals;
    List<Bannerdatum> bannerdataList;
    List<Bestdeal> bestdealList;
    List<Branddatum> branddatumList;
    RecyclerView recyclerViewBanner, recyclerViewBestDeals, recyclerViewBrands;

    TextView tvBrandsViewAll;
    Context context;
    private String AuthToken;
    private LinearLayoutManager layoutManagerBestDeals;

    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buyer_home, container, false);
        context = root.getContext();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setProgressStyle(R.style.ProgressBar);
//        progressDialog.show();

        shimmerFrameLayout = root.findViewById(R.id.shimmer_buyer_home);
        shimmerFrameLayout.startShimmerAnimation();

        recyclerViewBanner = root.findViewById(R.id.FBH_rv_banners);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterBanner = new AdapterBanner(context);
        recyclerViewBanner.setAdapter(adapterBanner);
        adapterBanner.setOnItemClickListener(position -> {
            Bannerdatum bannerdatum = bannerdataList.get(position);
            Toast.makeText(context, bannerdatum.getBannername(), Toast.LENGTH_SHORT).show();
        });

        recyclerViewBestDeals = root.findViewById(R.id.FBH_rv_buyer_best_deals);
        layoutManagerBestDeals = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBestDeals.setLayoutManager(layoutManagerBestDeals);
        adapterBestDeals = new AdapterBestDeals(context);
        recyclerViewBestDeals.setAdapter(adapterBestDeals);

        final int duration = 10;
        final int pixelsToMove = 150;
        final Handler mHandler = new Handler(Looper.getMainLooper());
        final Runnable SCROLLING_RUNNABLE = new Runnable() {
            @Override
            public void run() {
                recyclerViewBestDeals.smoothScrollBy(pixelsToMove, 0);
                mHandler.postDelayed(this, duration);
            }
        };

        recyclerViewBestDeals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManagerBestDeals.findLastCompletelyVisibleItemPosition();
                if (lastItem == layoutManagerBestDeals.getItemCount() - 1) {
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(() -> {
                        recyclerViewBestDeals.setAdapter(null);
                        recyclerViewBestDeals.setAdapter(adapterBestDeals);
                        mHandler.postDelayed(SCROLLING_RUNNABLE, 5000);
                    }, 5000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);

        adapterBestDeals.setOnItemClickListener(position -> {
            Bestdeal bestdeal = bestdealList.get(position);
            Intent intent = new Intent(getActivity(), ProductActivity.class);
            intent.putExtra(EXTRA_COLOR, bestdeal.getSellerproductinfo().getColor());
            intent.putExtra(EXTRA_RAM, bestdeal.getSellerproductinfo().getRamMob());
            intent.putExtra(EXTRA_INTERNAL_MEMORY, bestdeal.getSellerproductinfo().getInternalMemory());
            intent.putExtra(EXTRA_PRO_ID, bestdeal.getSellerproductinfo().getProductId());
            startActivity(intent);
        });

        recyclerViewBrands = root.findViewById(R.id.FBH_rv_buyer_brands);
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterBrands = new AdapterBrands(context);
        recyclerViewBrands.setAdapter(adapterBrands);
        adapterBrands.setOnItemClickListener(position -> {
            Branddatum branddatum = branddatumList.get(position);
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            intent.putExtra(BRAND_NAME, branddatum.getBrandinfo().getBrandname());
            startActivity(intent);
        });

        tvBrandsViewAll = root.findViewById(R.id.tvFBH_brandsViewAll);

        tvBrandsViewAll.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            intent.putExtra(BRAND_NAME, "All");
            startActivity(intent);
        });

        getBuyerHomeResponse();

        return root;
    }

    private void getBuyerHomeResponse() {
        final BuyerHomeResponse response = new BuyerHomeResponse();
        Call<BuyerHomeResponse> responseCall = equibiz_api_interface.buyerHomeResponse(response, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<BuyerHomeResponse>() {
            @Override
            public void onResponse(@NotNull Call<BuyerHomeResponse> call, @NotNull Response<BuyerHomeResponse> response) {
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                BuyerHomeResponse response1 = response.body();
                assert response1 != null;
                branddatumList = response1.getBranddata();
                bestdealList = response1.getBestdeals();
                bannerdataList = response1.getBannerdata();

                if (branddatumList.isEmpty() || bestdealList.isEmpty() || bannerdataList.isEmpty()) {
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    adapterBanner.setBannerdata(bannerdataList);
                    adapterBestDeals.setBestdeals(bestdealList);
                    adapterBrands.setBranddata(branddatumList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BuyerHomeResponse> call, @NotNull Throwable t) {
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}