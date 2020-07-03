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

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<Orderslist> orderslists;

    public AdapterPendingOrders(Context context) {
        this.context = context;
        orderslists = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
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
//        String ram = orderslist.getProductinfo().getRamMob();
//        String intMemory = sellerproduct.getInternalMemory();
//        String color = sellerproduct.getColor();
//        holder.productSpecification.setText(ram + " GB/ " + intMemory + " GB/ " + color);

    }

    @Override
    public int getItemCount() {
        return orderslists.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productSpecification, quantity, price, date, totalCost, timer;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.ISPO_product_name);
            productSpecification = itemView.findViewById(R.id.ISPO_product_specifications);
            quantity = itemView.findViewById(R.id.ISPO_order_quantity);
            price = itemView.findViewById(R.id.ISPO_ppu);
            date = itemView.findViewById(R.id.ISPO_dispatch_date);
            totalCost = itemView.findViewById(R.id.ISPO_total_cost);
            timer = itemView.findViewById(R.id.ISPO_timer);

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