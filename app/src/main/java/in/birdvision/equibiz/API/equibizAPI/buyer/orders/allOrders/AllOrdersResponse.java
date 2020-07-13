/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 1:14 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class AllOrdersResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("allorders")
    @Expose
    public HashMap<String, List<ArrayProduct>> allorders;
    //public Allorders allorders;
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

    public AllOrdersResponse() {
        this.allorders = new HashMap<>();
    }

    public String getStatus() {
        return status;
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
