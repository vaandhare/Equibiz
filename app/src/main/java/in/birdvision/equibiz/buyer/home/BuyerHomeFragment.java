package in.birdvision.equibiz.buyer.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import in.birdvision.equibiz.buyer.product.ProductListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerHomeFragment extends Fragment {

    Equibiz_API_Interface equibiz_api_interface;
    AdapterBanner adapterBanner;
    AdapterBrands adapterBrands;
    AdapterBestDeals adapterBestDeals;
    List<Bannerdatum> bannerdataList;
    List<Bestdeal> bestdealList;
    List<Branddatum> branddatumList;
    RecyclerView recyclerViewBanner, recyclerViewBestDeals, recyclerViewBrands;
    ProgressDialog progressDialog;

    TextView tvBrandsViewAll, tvBestDealsViewAll;
    Context context;
    private String AuthToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buyer_home, container, false);
        context = root.getContext();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(R.style.ProgressBar);
        progressDialog.show();

        recyclerViewBanner = root.findViewById(R.id.FBH_rv_banners);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterBanner = new AdapterBanner(context);
        recyclerViewBanner.setAdapter(adapterBanner);
        adapterBanner.setOnItemClickListener(position -> {
            Bannerdatum bannerdatum = bannerdataList.get(position);
            Toast.makeText(context, bannerdatum.getBannername(), Toast.LENGTH_SHORT).show();
        });

        recyclerViewBestDeals = root.findViewById(R.id.FBH_rv_buyer_best_deals);
        recyclerViewBestDeals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterBestDeals = new AdapterBestDeals(context);
        recyclerViewBestDeals.setAdapter(adapterBestDeals);
        adapterBestDeals.setOnItemClickListener(position -> {
            Bestdeal bestdeal = bestdealList.get(position);
            Toast.makeText(context, bestdeal.getOrderscount().toString(), Toast.LENGTH_SHORT).show();
        });

        recyclerViewBrands = root.findViewById(R.id.FBH_rv_buyer_brands);
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterBrands = new AdapterBrands(context);
        recyclerViewBrands.setAdapter(adapterBrands);
        adapterBrands.setOnItemClickListener(position -> {
            Branddatum branddatum = branddatumList.get(position);
            Toast.makeText(context, branddatum.getBrandinfo().getBrandname(), Toast.LENGTH_SHORT).show();
        });

        tvBestDealsViewAll = root.findViewById(R.id.tvFBH_bestDealsViewAll);
        tvBrandsViewAll = root.findViewById(R.id.tvFBH_brandsViewAll);

        tvBrandsViewAll.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProductListActivity.class)));

        final int speedScroll = 150;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count < adapterBanner.getItemCount()) {
                    recyclerViewBanner.scrollToPosition(count++);
                    handler.postDelayed(this, speedScroll);
                }
            }
        };
        handler.postDelayed(runnable, speedScroll);

        getBuyerHomeResponse();

        return root;
    }

    private void getBuyerHomeResponse() {
        final BuyerHomeResponse response = new BuyerHomeResponse();
        Call<BuyerHomeResponse> responseCall = equibiz_api_interface.buyerHomeResponse(response, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<BuyerHomeResponse>() {
            @Override
            public void onResponse(@NotNull Call<BuyerHomeResponse> call, @NotNull Response<BuyerHomeResponse> response) {
                progressDialog.dismiss();
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
                progressDialog.dismiss();
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}