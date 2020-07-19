/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

import in.birdvision.equibiz.API.equibizAPI.seller.Brandinfo;
import in.birdvision.equibiz.API.equibizAPI.seller.Productinfo;

public class Getoutofstockprosdetails {
    @SerializedName("_id")
    public String id;
    @SerializedName("proid")
    public String proid;
    @SerializedName("pro_status")
    public String proStatus;
    @SerializedName("productinfo")
    public Productinfo productinfo;
    @SerializedName("brand_id")
    public String brandId;
    @SerializedName("brandinfo")
    public Brandinfo brandinfo;
    @SerializedName("selfinfo")
    public Selfinfo selfinfo;

    public String getId() {
        return id;
    }

    public String getProid() {
        return proid;
    }

    public String getProStatus() {
        return proStatus;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getBrandId() {
        return brandId;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public Selfinfo getSelfinfo() {
        return selfinfo;
    }

    public static class Selfinfo {
        @SerializedName("_id")
        public String id;
        @SerializedName("color")
        public String color;
        @SerializedName("internal_memory")
        public String internalMemory;
        @SerializedName("product_id")
        public String productId;
        @SerializedName("ram_mob")
        public String ramMob;
        @SerializedName("userid")
        public String useridX;
        @SerializedName("__v")
        public String v;
        @SerializedName("available_stock")
        public String availableStock;
        @SerializedName("avg_price")
        public String avgPrice;
        @SerializedName("bpchangenotify")
        public String bpchangenotify;
        @SerializedName("created_date")
        public String createdDate;
        @SerializedName("forexport")
        public boolean forexport;
        @SerializedName("minqty")
        public String minqty;
        @SerializedName("pro_status")
        public String proStatus;
        @SerializedName("soldout")
        public boolean soldout;
        @SerializedName("time_to_del")
        public String timeToDel;
        @SerializedName("updated_date")
        public String updatedDate;

        public String getId() {
            return id;
        }

        public String getColor() {
            return color;
        }

        public String getInternalMemory() {
            return internalMemory;
        }

        public String getProductId() {
            return productId;
        }

        public String getRamMob() {
            return ramMob;
        }

        public String getUseridX() {
            return useridX;
        }

        public String getV() {
            return v;
        }

        public String getAvailableStock() {
            return availableStock;
        }

        public String getAvgPrice() {
            return avgPrice;
        }

        public String getBpchangenotify() {
            return bpchangenotify;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public boolean isForexport() {
            return forexport;
        }

        public String getMinqty() {
            return minqty;
        }

        public String getProStatus() {
            return proStatus;
        }

        public boolean isSoldout() {
            return soldout;
        }

        public String getTimeToDel() {
            return timeToDel;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }
    }
}