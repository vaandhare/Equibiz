/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 4:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.seller.ui.pendingOrders;

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

import in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder.Orderslist;
import in.birdvision.equibiz.R;

public class AdapterPendingOrders extends RecyclerView.Adapter<AdapterPendingOrders.ProductListViewHolder> {

    Context context;
    List<Orderslist> orderslists;
    public PendingOrdersButtonsListener buttonsListener;

    public AdapterPendingOrders(Context context, PendingOrdersButtonsListener listener) {
        this.context = context;
        this.buttonsListener = listener;
        orderslists = new ArrayList<>();
    }

    public void setOrderslists(List<Orderslist> orderslist) {
        orderslists.clear();
        orderslists.addAll(orderslist);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPendingOrders.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.items_seller_pending_orders, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Orderslist orderslist = orderslists.get(position);
        String brandName = orderslist.getBrandinfo().getBrandname();
        String modelName = orderslist.getProductinfo().getpModelNo();
        holder.productName.setText(brandName + " " + modelName);
//        String ram = orderslist.getBrandinfo();
//        String intMemory = sellerproduct.getInternalMemory();
//        String color = sellerproduct.getColor();
//        holder.productSpecification.setText(ram + " GB/ " + intMemory + " GB/ " + color);

        holder.quantity.setText(orderslist.getQtyOrdered());
        holder.price.setText(orderslist.getUnitPrice());
        holder.totalCost.setText(orderslist.getTotalPrice());
        holder.date.setText(orderslist.getExpdelforbuyer());

        holder.btnAccept.setOnClickListener(v -> buttonsListener.acceptOnClick(v, position));

        holder.btnReject.setOnClickListener(v -> buttonsListener.rejectOnClick(v, position));

    }

    @Override
    public int getItemCount() {
        return orderslists.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface PendingOrdersButtonsListener {
        void acceptOnClick(View view, int position);

        void rejectOnClick(View view, int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productSpecification, quantity, price, date, totalCost, timer;
        TextView btnAccept, btnReject;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.ISPO_product_name);
            productSpecification = itemView.findViewById(R.id.ISPO_product_specifications);
            quantity = itemView.findViewById(R.id.ISPO_order_quantity);
            price = itemView.findViewById(R.id.ISPO_ppu);
            date = itemView.findViewById(R.id.ISPO_dispatch_date);
            totalCost = itemView.findViewById(R.id.ISPO_total_cost);
            timer = itemView.findViewById(R.id.ISPO_timer);

            btnAccept = itemView.findViewById(R.id.btn_ISPO_accept);
            btnReject = itemView.findViewById(R.id.btn_ISPO_reject);
        }
    }
}