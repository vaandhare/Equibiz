
package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class _id {

    private String color;
    @SerializedName("internal_memory")
    private String internalMemory;
    @SerializedName("pro_id")
    private String proId;
    @SerializedName("ram_mob")
    private String ramMob;

    public String getColor() {
        return color;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public String getProId() {
        return proId;
    }

    public String getRamMob() {
        return ramMob;
    }
}
