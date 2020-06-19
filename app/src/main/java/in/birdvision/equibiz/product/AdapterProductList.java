package in.birdvision.equibiz.product;

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

import in.birdvision.equibiz.API.equibizAPI.product.productList.Productdatum;
import in.birdvision.equibiz.R;

public class AdapterProductList extends RecyclerView.Adapter<AdapterProductList.ProductListViewHolder> {

    Context context;
    List<Productdatum> productdata;
    private static OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public AdapterProductList(Context context) {
        this.context = context;
        productdata = new ArrayList<>();
    }

    public void setProductdata(List<Productdatum> productData) {
        productdata.addAll(productData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterProductList.ProductListViewHolder(LayoutInflater.from(context).
                inflate(R.layout.items_products_recyclerview, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Productdatum productdatum = productdata.get(position);

        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + productdatum.getProductinfo().getPImages().get(0);
        Glide.with(context).load(IMG_URL).into(holder.thumbnail);
        String brandName = productdatum.getBrandinfo().getBrandname();
        String modelName = productdatum.getProductinfo().getPModelNo();
        String ram = productdatum.get_id().getRamMob() + " GB";
        String internalMemory = productdatum.get_id().getInternalMemory() + " GB";
        String mobColor = productdatum.get_id().getColor();

        Long totalStock = productdatum.getStocksum();
        Long avgPrice = productdatum.getAvgsum();

        holder.productSpecifications.setText(ram + "/ " + internalMemory + "/ " + mobColor);
        holder.productName.setText(brandName + " " + modelName);
        holder.totalStock.setText(totalStock.toString());
        holder.avgPRice.setText(avgPrice.toString());
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView productName, productSpecifications, totalStock, avgPRice;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.IPR_product_thumbnail);
            productName = itemView.findViewById(R.id.IPR_product_name);
            productSpecifications = itemView.findViewById(R.id.IPR_product_specifications);
            totalStock = itemView.findViewById(R.id.IPR_total_stock);
            avgPRice = itemView.findViewById(R.id.IPR_avg_price);

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
