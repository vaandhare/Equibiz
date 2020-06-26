
package in.birdvision.equibiz.API.equibizAPI.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Sellerlist {

    private String _id;
    @SerializedName("available_stock")
    private int availableStock;
    @SerializedName("avg_price")
    private long avgPrice;
    private String color;
    @SerializedName("internal_memory")
    private String internalMemory;
    @SerializedName("location")
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
    @SerializedName("minqty")
    private String minqty;
    @SerializedName("forexport")
    private Boolean forexport;
    @SerializedName("soldout")
    private Boolean soldout;
    @SerializedName("hublocation")
    private String hublocation;


    public String get_id() {
        return _id;
    }

    public int getAvailableStock() {
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

    public String getMinqty() {
        return minqty;
    }

    public Boolean getForexport() {
        return forexport;
    }

    public Boolean getSoldout() {
        return soldout;
    }

    public String getHublocation() {
        return hublocation;
    }
}

