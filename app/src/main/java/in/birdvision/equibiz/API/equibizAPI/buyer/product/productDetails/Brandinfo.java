
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Brandinfo {

    @SerializedName("__v")
    private String _V;
    private String _id;
    private String brandname;
    private String brandstatus;

    public String get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getBrandname() {
        return brandname;
    }

    public String getBrandstatus() {
        return brandstatus;
    }
}
