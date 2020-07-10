
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Sellerlist {

    private String _id;
    @SerializedName("available_stock")
    private String availableStock;
    @SerializedName("avg_price")
    private String avgPrice;
    private String color;
    @SerializedName("internal_memory")
    private String internalMemory;
    @SerializedName("location")
    private String location;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("ram_mob")
    private String ramMob;
    private String sellerindex;
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

    public String getTimeToDel() {
        return timeToDel;
    }

    public String getUserId() {
        return userId;
    }

    public String getAvailableStock() {
        return availableStock;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public String getSellerindex() {
        return sellerindex;
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

