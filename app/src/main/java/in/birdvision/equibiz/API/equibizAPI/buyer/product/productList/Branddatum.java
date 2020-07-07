package in.birdvision.equibiz.API.equibizAPI.buyer.product.productList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.buyer.product.Brandinfo;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.Productinfo;

public class Branddatum {

    @Expose
    private String _id;
    @SerializedName("brand_id")
    private String brandId;
    @Expose
    private Brandinfo brandinfo;
    @Expose
    private List<Productinfo> productinfo;

    public String get_id() {
        return _id;
    }

    public String getBrandId() {
        return brandId;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public List<Productinfo> getProductinfo() {
        return productinfo;
    }
}
