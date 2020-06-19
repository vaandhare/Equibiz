
package in.birdvision.equibiz.API.equibizAPI.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Productdatum {

    private in.birdvision.equibiz.API.equibizAPI.product.productDetails._id _id;
    private long asum;
    private long avgpricesum;
    private long avgsum;
    @SerializedName("brand_id")
    private String brandId;
    private Brandinfo brandinfo;
    private String color;
    @SerializedName("internal_memory")
    private String internalMemory;
    @SerializedName("pro_id")
    private String proId;
    private String productid;
    private Productinfo productinfo;
    @SerializedName("ram_mob")
    private String ramMob;
    private long stocksum;
    private long sum;

    public in.birdvision.equibiz.API.equibizAPI.product.productDetails._id get_id() {
        return _id;
    }

    public long getAsum() {
        return asum;
    }

    public long getAvgpricesum() {
        return avgpricesum;
    }

    public long getAvgsum() {
        return avgsum;
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

    public String getProId() {
        return proId;
    }

    public String getProductid() {
        return productid;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getRamMob() {
        return ramMob;
    }

    public long getStocksum() {
        return stocksum;
    }

    public long getSum() {
        return sum;
    }
}
