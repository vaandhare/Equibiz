/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 1:14 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.buyer.ui.orders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.ArrayProduct;
import in.birdvision.equibiz.R;

public class AdapterAllOrders extends RecyclerView.Adapter<AdapterAllOrders.ProductListViewHolder> {

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<ArrayProduct> productdata;

    public AdapterAllOrders(Context context) {
        this.context = context;
        productdata = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setProductdata(List<ArrayProduct> productData) {
        productdata.clear();
        productdata.addAll(productData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterAllOrders.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_buyer_order_history, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        ArrayProduct product = productdata.get(position);

        String productName = product.getBrandinfo().getBrandname() + product.getProductinfo().getpModelNo();
        holder.productName.setText(productName);
        holder.quantity.setText(product.getQtyOrdered());
        holder.unitPrice.setText(product.getUnitPrice());
        holder.dispatchDate.setText(product.getOrderDate());
        holder.totalCost.setText(product.getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView productName, quantity, unitPrice, dispatchDate, totalCost;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.IBOH_product_name);
            quantity = itemView.findViewById(R.id.IBOH_quantity);
            unitPrice = itemView.findViewById(R.id.IBOH_ppu);
            dispatchDate = itemView.findViewById(R.id.IBOH_dispatch_date);
            totalCost = itemView.findViewById(R.id.IBOH_total_cost);

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