
package in.birdvision.equibiz.API.equibizAPI.buyer.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sellerproductinfo {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @SerializedName("available_stock")
    private Long availableStock;
    @SerializedName("avg_price")
    private Long avgPrice;
    @Expose
    private Long bpchangenotify;
    @Expose
    private String color;
    @SerializedName("created_date")
    private String createdDate;
    @Expose
    private Boolean forexport;
    @SerializedName("internal_memory")
    private String internalMemory;
    @Expose
    private Long minqty;
    @SerializedName("pro_status")
    private Long proStatus;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("ram_mob")
    private String ramMob;
    @Expose
    private Boolean soldout;
    @SerializedName("time_to_del")
    private String timeToDel;
    @SerializedName("updated_date")
    private String updatedDate;
    @Expose
    private String userid;

    public Long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public Long getAvailableStock() {
        return availableStock;
    }

    public Long getAvgPrice() {
        return avgPrice;
    }

    public Long getBpchangenotify() {
        return bpchangenotify;
    }

    public String getColor() {
        return color;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Boolean getForexport() {
        return forexport;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public Long getMinqty() {
        return minqty;
    }

    public Long getProStatus() {
        return proStatus;
    }

    public String getProductId() {
        return productId;
    }

    public String getRamMob() {
        return ramMob;
    }

    public Boolean getSoldout() {
        return soldout;
    }

    public String getTimeToDel() {
        return timeToDel;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getUserid() {
        return userid;
    }
}
