package in.birdvision.equibiz.seller.ui.my_inventory;

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

import in.birdvision.equibiz.API.equibizAPI.seller.adminProduct.Sellerproduct;
import in.birdvision.equibiz.R;

public class AdapterMyInventory extends RecyclerView.Adapter<AdapterMyInventory.ProductListViewHolder> {

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<Sellerproduct> productdata;

    public AdapterMyInventory(Context context) {
        this.context = context;
        productdata = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setProductdata(List<Sellerproduct> productData) {
        productdata.clear();
        productdata.addAll(productData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterMyInventory.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.items_seller_products_listing, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Sellerproduct sellerproduct = productdata.get(position);
        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + sellerproduct.getProductinfo().getpImages().get(0);
        Glide.with(context).load(IMG_URL).into(holder.thumbnail);
        String brandName = sellerproduct.getBrandinfo().getBrandname();
        String modelName = sellerproduct.getProductinfo().getpModelNo();
        holder.productName.setText(brandName + " " + modelName);
        String ram = sellerproduct.getRamMob();
        String intMemory = sellerproduct.getInternalMemory();
        String color = sellerproduct.getColor();
        holder.productSpecification.setText(ram + " GB/ " + intMemory + " GB/ " + color);

    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView productName, productSpecification, status;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.ISPR_product_thumbnail);
            productName = itemView.findViewById(R.id.ISPR_product_name);
            productSpecification = itemView.findViewById(R.id.ISPR_product_specifications);
            status = itemView.findViewById(R.id.ISPR_status);

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