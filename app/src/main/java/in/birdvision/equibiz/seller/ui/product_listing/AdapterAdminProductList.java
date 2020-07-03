package in.birdvision.equibiz.seller.ui.product_listing;

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

import in.birdvision.equibiz.API.equibizAPI.seller.Adminproduct;
import in.birdvision.equibiz.R;

public class AdapterAdminProductList extends RecyclerView.Adapter<AdapterAdminProductList.ProductListViewHolder> {

    private static OnItemClickListener onItemClickListener;
    Context context;
    List<Adminproduct> adminproducts;

    public AdapterAdminProductList(Context context) {
        this.context = context;
        adminproducts = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setProductdata(List<Adminproduct> adminproducts1) {
        adminproducts.clear();
        adminproducts.addAll(adminproducts1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterAdminProductList.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_seller_admin_products_recyclerview, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Adminproduct adminproduct = adminproducts.get(position);

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + adminproduct.getProductinfo().getpImages().get(0);
        Glide.with(context).load(IMG_URL).into(holder.thumbnail);
        String brandName = adminproduct.getBrandinfo().getBrandname();
        String modelName = adminproduct.getProductinfo().getpModelNo();

        holder.productName.setText(brandName + " " + modelName);

    }

    @Override
    public int getItemCount() {
        return adminproducts.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView productName;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.ISAPR_product_thumbnail);
            productName = itemView.findViewById(R.id.ISAPR_product_name);

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