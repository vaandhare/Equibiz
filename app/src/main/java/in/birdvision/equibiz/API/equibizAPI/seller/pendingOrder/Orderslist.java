
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.birdvision.equibiz.API.equibizAPI.seller.Brandinfo;
import in.birdvision.equibiz.API.equibizAPI.seller.Productinfo;

public class Orderslist {

    @SerializedName("__v")
    private String _V;
    @Expose
    private String _id;
    @Expose
    private String adminconfirmation;
    @Expose
    private String amount10percent;
    @SerializedName("brand_id")
    private String brandId;
    @Expose
    private Brandinfo brandinfo;
    @SerializedName("buyer_id")
    private String buyerId;
    @SerializedName("created_date")
    private String createdDate;
    @Expose
    private String expdelforbuyer;
    @Expose
    private String expdelforseller;
    @Expose
    private String finalpricetodeduct;
    @Expose
    private String fullpaymentdate;
    @Expose
    private String insurance;
    @SerializedName("order_date")
    private String orderDate;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("order_status")
    private String orderStatus;
    @Expose
    private String prebookpaymentdate;
    @SerializedName("pro_id")
    private String proId;
    @SerializedName("product_id")
    private String productId;
    @Expose
    private Productinfo productinfo;
    @SerializedName("qty_ordered")
    private String qtyOrdered;
    @Expose
    private String ratecardid;
    @Expose
    private String remainingamt;
    @Expose
    private Boolean result;
    @SerializedName("seller_id")
    private String sellerId;
    @Expose
    private String sellerproid;
    @Expose
    private String sellertime;
    @SerializedName("total_price")
    private String totalPrice;
    @SerializedName("unit_price")
    private String unitPrice;
    @SerializedName("updated_date")
    private String updatedDate;

    public String get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getBrandId() {
        return brandId;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getExpdelforbuyer() {
        return expdelforbuyer;
    }

    public String getExpdelforseller() {
        return expdelforseller;
    }

    public String getFullpaymentdate() {
        return fullpaymentdate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPrebookpaymentdate() {
        return prebookpaymentdate;
    }

    public String getProId() {
        return proId;
    }

    public String getProductId() {
        return productId;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getRatecardid() {
        return ratecardid;
    }

    public String getRemainingamt() {
        return remainingamt;
    }

    public Boolean getResult() {
        return result;
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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getAdminconfirmation() {
        return adminconfirmation;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getQtyOrdered() {
        return qtyOrdered;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }
}
