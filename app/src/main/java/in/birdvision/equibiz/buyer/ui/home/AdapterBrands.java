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

import in.birdvision.equibiz.API.equibizAPI.buyer.home.Branddatum;
import in.birdvision.equibiz.R;

public class AdapterBrands extends RecyclerView.Adapter<AdapterBrands.ProductListViewHolder> {

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<Branddatum> branddata;

    public AdapterBrands(Context context) {
        this.context = context;
        branddata = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setBranddata(List<Branddatum> branddata1) {
        branddata.clear();
        branddata.addAll(branddata1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterBrands.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_buyer_brands, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Branddatum brands = branddata.get(position);

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + brands.getProductinfo().get(0).getpImages().get(0);
        Glide.with(context).load(IMG_URL).into(holder.productImg);

        String brandName = brands.getBrandinfo().getBrandname();
        holder.productName.setText(brandName);


    }

    @Override
    public int getItemCount() {
        return branddata.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        ImageView productImg;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.IBB_product_thumbnail);
            productName = itemView.findViewById(R.id.IBB_product_name);

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