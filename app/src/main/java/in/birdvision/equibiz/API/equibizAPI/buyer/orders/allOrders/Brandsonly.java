/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 4:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

public class Brandsonly {
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
