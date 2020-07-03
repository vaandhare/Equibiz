package in.birdvision.equibiz.API.equibizAPI.seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Adminproduct {
    @Expose
    private String _id;
    @SerializedName("brand_id")
    private String brandId;
    @Expose
    private Brandinfo brandinfo;
    @Expose
    private Productinfo productinfo;
    @Expose
    private String proid;

    public String get_id() {
        return _id;
    }

    public String getBrandId() {
        return brandId;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getProid() {
        return proid;
    }
}