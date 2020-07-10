
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brandinfo {

    @SerializedName("__v")
    private String _V;
    @Expose
    private String _id;
    @Expose
    private String adminid;
    @Expose
    private String brandname;
    @Expose
    private String brandstatus;
    @SerializedName("created_date")
    private String createdDate;
    @SerializedName("updated_date")
    private String updatedDate;

    public String get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getAdminid() {
        return adminid;
    }

    public String getBrandname() {
        return brandname;
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
}
