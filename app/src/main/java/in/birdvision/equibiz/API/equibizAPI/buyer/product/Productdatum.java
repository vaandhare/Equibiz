
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productdatum {

    @Expose
    private in.birdvision.equibiz.API.equibizAPI.buyer.product._id _id;
    @Expose
    private String asum;
    @Expose
    private String avgsum;
    @Expose
    private Brandinfo brandinfo;
    @Expose
    private Productinfo productinfo;
    @Expose
    private String prostatus;
    @Expose
    private String stocksum;
    @Expose
    private String sum;

    @SerializedName("search")
    private String mSearch;
    @SerializedName("searchres")
    private String mSearchres;

    @SerializedName("modelno")
    private String mModelno;

    public in.birdvision.equibiz.API.equibizAPI.buyer.product._id get_id() {
        return _id;
    }

    public String getAsum() {
        return asum;
    }

    public String getAvgsum() {
        return avgsum;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getProstatus() {
        return prostatus;
    }

    public String getStocksum() {
        return stocksum;
    }

    public String getSum() {
        return sum;
    }

    public String getmSearch() {
        return mSearch;
    }

    public String getmSearchres() {
        return mSearchres;
    }

    public String getmModelno() {
        return mModelno;
    }
}
