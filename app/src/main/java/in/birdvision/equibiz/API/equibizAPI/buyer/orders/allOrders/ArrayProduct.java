/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 1:14 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArrayProduct {

    @SerializedName("_id")
    public String id;
    @SerializedName("order_id")
    public String orderId;
    @SerializedName("__v")
    public String v;
    @SerializedName("adminconfirmation")
    public String adminconfirmation;
    @SerializedName("amount10percent")
    public String amount10percent;
    @SerializedName("buyer_id")
    public String buyerId;
    @SerializedName("created_date")
    public String createdDate;
    @SerializedName("finalpricetodeduct")
    public String finalpricetodeduct;
    @SerializedName("insurance")
    public String insurance;
    @SerializedName("order_status")
    public String orderStatus;
    @SerializedName("prebookpaymentdate")
    public String prebookpaymentdate;
    @SerializedName("product_id")
    public String productId;
    @SerializedName("qty_ordered")
    public String qtyOrdered;
    @SerializedName("ratecardid")
    public String ratecardid;
    @SerializedName("seller_id")
    public String sellerId;
    @SerializedName("sellerproid")
    public String sellerproid;
    @SerializedName("sellertime")
    public String sellertime;
    @SerializedName("total_price")
    public String totalPrice;
    @SerializedName("unit_price")
    public String unitPrice;
    @SerializedName("updated_date")
    public String updatedDate;
    @SerializedName("expdelforbuyer")
    public String expdelforbuyer;
    @SerializedName("expdelforseller")
    public String expdelforseller;
    @SerializedName("order_date")
    public String orderDate;
    @SerializedName("pro_id")
    public String proId;
    @SerializedName("productinfo")
    public Productinfo productinfo;
    @SerializedName("brand_id")
    public String brandId;
    @SerializedName("brandstrid")
    public String brandstrid;
    @SerializedName("brandinfo")
    public Brandinfo brandinfo;

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getV() {
        return v;
    }

    public String getAdminconfirmation() {
        return adminconfirmation;
    }

    public String getAmount10percent() {
        return amount10percent;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getFinalpricetodeduct() {
        return finalpricetodeduct;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getPrebookpaymentdate() {
        return prebookpaymentdate;
    }

    public String getProductId() {
        return productId;
    }

    public String getQtyOrdered() {
        return qtyOrdered;
    }

    public String getRatecardid() {
        return ratecardid;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getSellerproid() {
        return sellerproid;
    }

    public String getSellertime() {
        return sellertime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getExpdelforbuyer() {
        return expdelforbuyer;
    }

    public String getExpdelforseller() {
        return expdelforseller;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getProId() {
        return proId;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getBrandstrid() {
        return brandstrid;
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
        public int v;
        @SerializedName("p_base_high")
        public double pBaseHigh;
        @SerializedName("p_base_low")
        public double pBaseLow;
        @SerializedName("p_base_price")
        public int pBasePrice;
        @SerializedName("p_battery_power")
        public String pBatteryPower;
        @SerializedName("p_created_date")
        public String pCreatedDate;
        @SerializedName("p_desc")
        public String pDesc;
        @SerializedName("p_dimensions")
        public String pDimensions;
        @SerializedName("p_expandable_memory")
        public int pExpandableMemory;
        @SerializedName("p_front_camera")
        public int pFrontCamera;
        @SerializedName("p_network_type")
        public String pNetworkType;
        @SerializedName("p_os_type")
        public String pOsType;
        @SerializedName("p_primary_camera")
        public int pPrimaryCamera;
        @SerializedName("p_processor")
        public String pProcessor;
        @SerializedName("p_screen_size")
        public String pScreenSize;
        @SerializedName("p_sim_slots")
        public int pSimSlots;
        @SerializedName("p_status")
        public int pStatus;
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

        public int getV() {
            return v;
        }

        public double getpBaseHigh() {
            return pBaseHigh;
        }

        public double getpBaseLow() {
            return pBaseLow;
        }

        public int getpBasePrice() {
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

        public int getpExpandableMemory() {
            return pExpandableMemory;
        }

        public int getpFrontCamera() {
            return pFrontCamera;
        }

        public String getpNetworkType() {
            return pNetworkType;
        }

        public String getpOsType() {
            return pOsType;
        }

        public int getpPrimaryCamera() {
            return pPrimaryCamera;
        }

        public String getpProcessor() {
            return pProcessor;
        }

        public String getpScreenSize() {
            return pScreenSize;
        }

        public int getpSimSlots() {
            return pSimSlots;
        }

        public int getpStatus() {
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
        public int brandstatus;
        @SerializedName("created_date")
        public String createdDate;
        @SerializedName("updated_date")
        public String updatedDate;
        @SerializedName("__v")
        public int v;

        public String getId() {
            return id;
        }

        public String getBrandname() {
            return brandname;
        }

        public String getAdminid() {
            return adminid;
        }

        public int getBrandstatus() {
            return brandstatus;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public int getV() {
            return v;
        }

    }
}
