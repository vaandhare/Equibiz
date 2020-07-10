/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 4:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

public class Orderids {
    @SerializedName("_id")
    public String id;
    @SerializedName("order_id")
    public String orderId;
    @SerializedName("__v")
    public int v;
    @SerializedName("adminconfirmation")
    public int adminconfirmation;
    @SerializedName("amount10percent")
    public double amount10percent;
    @SerializedName("buyer_id")
    public String buyerId;
    @SerializedName("created_date")
    public String createdDate;
    @SerializedName("finalpricetodeduct")
    public double finalpricetodeduct;
    @SerializedName("insurance")
    public int insurance;
    @SerializedName("order_status")
    public int orderStatus;
    @SerializedName("prebookpaymentdate")
    public String prebookpaymentdate;
    @SerializedName("product_id")
    public String productId;
    @SerializedName("qty_ordered")
    public int qtyOrdered;
    @SerializedName("ratecardid")
    public String ratecardid;
    @SerializedName("seller_id")
    public String sellerId;
    @SerializedName("sellerproid")
    public String sellerproid;
    @SerializedName("sellertime")
    public String sellertime;
    @SerializedName("total_price")
    public int totalPrice;
    @SerializedName("unit_price")
    public int unitPrice;
    @SerializedName("updated_date")
    public String updatedDate;
    @SerializedName("expdelforbuyer")
    public String expdelforbuyer;
    @SerializedName("expdelforseller")
    public String expdelforseller;
    @SerializedName("order_date")
    public String orderDate;
    @SerializedName("fullpaymentdate")
    public String fullpaymentdate;
    @SerializedName("remainingamt")
    public double remainingamt;


    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getV() {
        return v;
    }

    public int getAdminconfirmation() {
        return adminconfirmation;
    }

    public double getAmount10percent() {
        return amount10percent;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public double getFinalpricetodeduct() {
        return finalpricetodeduct;
    }

    public int getInsurance() {
        return insurance;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public String getPrebookpaymentdate() {
        return prebookpaymentdate;
    }

    public String getProductId() {
        return productId;
    }

    public int getQtyOrdered() {
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getUnitPrice() {
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

    public String getFullpaymentdate() {
        return fullpaymentdate;
    }

    public double getRemainingamt() {
        return remainingamt;
    }
}
