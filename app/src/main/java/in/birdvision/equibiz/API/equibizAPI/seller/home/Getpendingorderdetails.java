/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getpendingorderdetails {
    @SerializedName("_id")
    public String id;
    @SerializedName("pro_id")
    public String proId;
    @SerializedName("qty_ordered")
    public String qtyOrdered;
    @SerializedName("buyer_id")
    public String buyerId;
    @SerializedName("order_status")
    public String orderStatus;
    @SerializedName("total_price")
    public String totalPrice;
    @SerializedName("productinfo")
    public Productinfo productinfo;
    @SerializedName("brand_id")
    public String brandId;
    @SerializedName("brandinfo")
    public Brandinfo brandinfo;

    public String getId() {
        return id;
    }

    public String getProId() {
        return proId;
    }

    public String getQtyOrdered() {
        return qtyOrdered;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getTotalPrice() {
        return totalPrice;
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

    public static class Productinfo {
        @SerializedName("_id")
        public String id;
        @SerializedName("p_brand")
        public String pBrand;
        @SerializedName("p_model_no")
        public String pModelNo;
        @SerializedName("__v")
        public String v;
        @SerializedName("p_base_high")
        public String pBaseHigh;
        @SerializedName("p_base_low")
        public String pBaseLow;
        @SerializedName("p_base_price")
        public String pBasePrice;
        @SerializedName("p_battery_power")
        public String pBatteryPower;
        @SerializedName("p_created_date")
        public String pCreatedDate;
        @SerializedName("p_desc")
        public String pDesc;
        @SerializedName("p_dimensions")
        public String pDimensions;
        @SerializedName("p_expandable_memory")
        public String pExpandableMemory;
        @SerializedName("p_front_camera")
        public String pFrontCamera;
        @SerializedName("p_network_type")
        public String pNetworkType;
        @SerializedName("p_os_type")
        public String pOsType;
        @SerializedName("p_primary_camera")
        public String pPrimaryCamera;
        @SerializedName("p_processor")
        public String pProcessor;
        @SerializedName("p_screen_size")
        public String pScreenSize;
        @SerializedName("p_sim_slots")
        public String pSimSlots;
        @SerializedName("p_status")
        public String pStatus;
        @SerializedName("p_updated_date")
        public String pUpdatedDate;
        @SerializedName("p_images")
        public List<String> pImages;

        public String getId() {
            return id;
        }

        public String getpBrand() {
            return pBrand;
        }

        public String getpModelNo() {
            return pModelNo;
        }

        public String getV() {
            return v;
        }

        public String getpBaseHigh() {
            return pBaseHigh;
        }

        public String getpBaseLow() {
            return pBaseLow;
        }

        public String getpBasePrice() {
            return pBasePrice;
        }

        public String getpBatteryPower() {
            return pBatteryPower;
        }

        public String getpCreatedDate() {
            return pCreatedDate;
        }

        public String getpDesc() {
            return pDesc;
        }

        public String getpDimensions() {
            return pDimensions;
        }

        public String getpExpandableMemory() {
            return pExpandableMemory;
        }

        public String getpFrontCamera() {
            return pFrontCamera;
        }

        public String getpNetworkType() {
            return pNetworkType;
        }

        public String getpOsType() {
            return pOsType;
        }

        public String getpPrimaryCamera() {
            return pPrimaryCamera;
        }

        public String getpProcessor() {
            return pProcessor;
        }

        public String getpScreenSize() {
            return pScreenSize;
        }

        public String getpSimSlots() {
            return pSimSlots;
        }

        public String getpStatus() {
            return pStatus;
        }

        public String getpUpdatedDate() {
            return pUpdatedDate;
        }

        public List<String> getpImages() {
            return pImages;
        }
    }

    public static class Brandinfo {
        @SerializedName("_id")
        public String id;
        @SerializedName("brandname")
        public String brandname;
        @SerializedName("adminid")
        public String adminid;
        @SerializedName("brandstatus")
        public String brandstatus;
        @SerializedName("created_date")
        public String createdDate;
        @SerializedName("updated_date")
        public String updatedDate;
        @SerializedName("__v")
        public String v;

        public String getId() {
            return id;
        }

        public String getBrandname() {
            return brandname;
        }

        public String getAdminid() {
            return adminid;
        }

        public String getBrandstatus() {
            return brandstatus;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public String getV() {
            return v;
        }
    }
}
