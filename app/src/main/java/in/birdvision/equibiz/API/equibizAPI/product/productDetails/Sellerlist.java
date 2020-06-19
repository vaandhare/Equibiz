
package in.birdvision.equibiz.API.equibizAPI.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Sellerlist {

    private String _id;
    @SerializedName("available_stock")
    private long availableStock;
    @SerializedName("avg_price")
    private long avgPrice;
    private String color;
    @SerializedName("internal_memory")
    private String internalMemory;
    private String location;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("ram_mob")
    private String ramMob;
    private long sellerindex;
    @SerializedName("time_to_del")
    private String timeToDel;
    @SerializedName("user_id")
    private String userId;


    public String get_id() {
        return _id;
    }

    public long getAvailableStock() {
        return availableStock;
    }

    public long getAvgPrice() {
        return avgPrice;
    }

    public String getColor() {
        return color;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public String getLocation() {
        return location;
    }

    public String getProductId() {
        return productId;
    }

    public String getRamMob() {
        return ramMob;
    }

    public long getSellerindex() {
        return sellerindex;
    }

    public String getTimeToDel() {
        return timeToDel;
    }

    public String getUserId() {
        return userId;
    }
}
