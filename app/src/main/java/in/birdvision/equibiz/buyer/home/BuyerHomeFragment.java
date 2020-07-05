package in.birdvision.equibiz.buyer.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.R;

public class BuyerHomeFragment extends Fragment implements AdapterBanner.OnItemClickListener,
        AdapterBrands.OnItemClickListener, AdapterBestDeals.OnItemClickListener{

    Equibiz_API_Interface equibiz_api_interface;
    AdapterBanner adapterBanner;
    AdapterBrands adapterBrands;
    AdapterBestDeals adapterBestDeals;

    RecyclerView recyclerViewBanner, recyclerViewBestDeals, recyclerViewBrands;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buyer_home, container, false);
        context = root.getContext();

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        recyclerViewBanner = root.findViewById(R.id.FBH_rv_banners);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        adapterBanner = new AdapterBanner(context);
        recyclerViewBanner.setAdapter(adapterBanner);
        adapterBanner.setOnItemClickListener(this);

        recyclerViewBestDeals = root.findViewById(R.id.FBH_rv_buyer_best_deals);
        recyclerViewBestDeals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        adapterBestDeals = new AdapterBestDeals(context);
        recyclerViewBestDeals.setAdapter(adapterBestDeals);
        adapterBestDeals.setOnItemClickListener(this);

        recyclerViewBrands = root.findViewById(R.id.FBH_rv_buyer_brands);
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        adapterBrands = new AdapterBrands(context);
        recyclerViewBrands.setAdapter(adapterBrands);
        adapterBrands.setOnItemClickListener(this);

        return root;
    }

    @Override
    public void onItemClick(int position) {
    }
}