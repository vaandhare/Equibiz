package in.birdvision.equibiz.buyer.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.buyer.home.Bannerdatum;
import in.birdvision.equibiz.API.equibizAPI.seller.adminProduct.Sellerproduct;
import in.birdvision.equibiz.R;

public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.ProductListViewHolder> {

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<Bannerdatum> bannerdata;

    public AdapterBanner(Context context) {
        this.context = context;
        bannerdata = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setBannerdata(List<Bannerdatum> bannerdata1) {
        bannerdata.clear();
        bannerdata.addAll(bannerdata1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterBanner.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_buyer_banners, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bannerdata.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}