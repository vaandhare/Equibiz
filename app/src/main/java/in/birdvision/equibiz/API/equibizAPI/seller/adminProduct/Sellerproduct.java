
package in.birdvision.equibiz.API.equibizAPI.seller.adminProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.birdvision.equibiz.API.equibizAPI.seller.Brandinfo;
import in.birdvision.equibiz.API.equibizAPI.seller.Productinfo;

public class Sellerproduct {

    @Expose
    private String _id;
    @Expose
    private Long bpchangenotify;
    @SerializedName("brand_id")
    private String brandId;
    @Expose
    private Brandinfo brandinfo;
    @Expose
    private String color;
    @SerializedName("internal_memory")
    private String internalMemory;
    @SerializedName("pro_status")
    private Long proStatus;
    @Expose
    private Productinfo productinfo;
    @Expose
    private String proid;
    @SerializedName("ram_mob")
    private String ramMob;

    public String get_id() {
        return _id;
    }

    public Long getBpchangenotify() {
        return bpchangenotify;
    }

    public String getBrandId() {
        return brandId;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public String getColor() {
        return color;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public Long getProStatus() {
        return proStatus;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getProid() {
        return proid;
    }

    public String getRamMob() {
        return ramMob;
    }
}
