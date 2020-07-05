package in.birdvision.equibiz.buyer.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.buyer.home.Bestdeal;
import in.birdvision.equibiz.API.equibizAPI.seller.adminProduct.Sellerproduct;
import in.birdvision.equibiz.R;

public class AdapterBestDeals extends RecyclerView.Adapter<AdapterBestDeals.ProductListViewHolder> {

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<Bestdeal> bestdeals;

    public AdapterBestDeals(Context context) {
        this.context = context;
        bestdeals = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setBestdeals(List<Bestdeal> bestdeals1) {
        bestdeals.clear();
        bestdeals.addAll(bestdeals1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterBestDeals.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_best_deals_product, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bestdeals.size();
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