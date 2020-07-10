
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Productdatum {

    private in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails._id _id;
    private String asum;
    private String avgpricesum;
    private String avgsum;
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
    private String stocksum;
    private String sum;

    public in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails._id get_id() {
        return _id;
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

    public String getAsum() {
        return asum;
    }

    public String getAvgpricesum() {
        return avgpricesum;
    }

    public String getAvgsum() {
        return avgsum;
    }

    public String getStocksum() {
        return stocksum;
    }

    public String getSum() {
        return sum;
    }
}
