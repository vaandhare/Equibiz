
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Allratecard implements Serializable {

    @SerializedName("__v")
    private String _V;
    @SerializedName("_id")
    private String _id;
    private String delcharges;
    private String deltime;
    private String location;

    public String get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getDelcharges() {
        return delcharges;
    }

    public String getDeltime() {
        return deltime;
    }

    public String getLocation() {
        return location;
    }
}
