/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 2:01 PM
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

    @SerializedName("currentorders")
    @Expose
    public HashMap<String, List<ArrayProduct>> currentorders;

    @SerializedName("pendingorders")
    @Expose
    public HashMap<String, List<ArrayProduct>> pendingorders;

    @SerializedName("cancelledorders")
    @Expose
    public HashMap<String, List<ArrayProduct>> cancelledorders;

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
        this.currentorders = new HashMap<>();
        this.pendingorders = new HashMap<>();
        this.cancelledorders = new HashMap<>();
    }

    public String getStatus() {
        return status;
    }

    public HashMap<String, List<ArrayProduct>> getAllorders() {
        return allorders;
    }

    public HashMap<String, List<ArrayProduct>> getCurrentorders() {
        return currentorders;
    }

    public HashMap<String, List<ArrayProduct>> getPendingorders() {
        return pendingorders;
    }

    public HashMap<String, List<ArrayProduct>> getCancelledorders() {
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
