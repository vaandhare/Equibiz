/*
 * *
 *  * Created by Vaibhav Andhare on 9/7/20 5:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.buyer.ui.home;

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
        Bestdeal bestdeal = bestdeals.get(position);

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + bestdeal.getProductinfo().getpImages().get(0);
        Glide.with(context).load(IMG_URL).into(holder.productImg);

        String brandName = bestdeal.getBrandinfo().getBrandname();
        String modelName = bestdeal.getProductinfo().getpModelNo();
        String ram = bestdeal.getSellerproductinfo().getRamMob() + " GB";
        String internalMemory = bestdeal.getSellerproductinfo().getInternalMemory() + " GB";
        String mobColor = bestdeal.getSellerproductinfo().getColor();

        holder.productSpecs.setText(ram + "/ " + internalMemory + "/ " + mobColor);
        holder.productName.setText(brandName + " " + modelName);
    }

    @Override
    public int getItemCount() {
        return bestdeals.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productSpecs;
        ImageView productImg;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.IBDP_product_name);
            productSpecs = itemView.findViewById(R.id.IBDP_product_specifications);
            productImg = itemView.findViewById(R.id.IBDP_product_thumbnail);

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