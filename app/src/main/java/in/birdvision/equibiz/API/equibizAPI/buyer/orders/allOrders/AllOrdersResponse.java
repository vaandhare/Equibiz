/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 4:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrdersResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("allorders")
    public Allorders allorders;
    @SerializedName("currentorders")
    public Currentorders currentorders;
    @SerializedName("pendingorders")
    public Pendingorders pendingorders;
    @SerializedName("cancelledorders")
    public Cancelledorders cancelledorders;
    @SerializedName("brandsonly")
    public List<Brandsonly> brandsonly;
    @SerializedName("orderids")
    public List<Orderids> orderids;

    public String userid;

    public AllOrdersResponse(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public Allorders getAllorders() {
        return allorders;
    }

    public Currentorders getCurrentorders() {
        return currentorders;
    }

    public Pendingorders getPendingorders() {
        return pendingorders;
    }

    public Cancelledorders getCancelledorders() {
        return cancelledorders;
    }

    public List<Brandsonly> getBrandsonly() {
        return brandsonly;
    }

    public List<Orderids> getOrderids() {
        return orderids;
    }

    public String getUserid() {
        return userid;
    }
}
