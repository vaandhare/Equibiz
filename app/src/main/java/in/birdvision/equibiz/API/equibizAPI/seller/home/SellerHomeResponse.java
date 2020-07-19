/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

public class SellerHomeResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("getpendingorders")
    public String getpendingorders;
    @SerializedName("getrejectedorders")
    public String getrejectedorders;
    @SerializedName("getsuccessorders")
    public String getsuccessorders;
    @SerializedName("getinstockpros")
    public String getinstockpros;
    @SerializedName("getoutofstockpros")
    public String getoutofstockpros;
    @SerializedName("getlimitedstockpros")
    public String getlimitedstockpros;
    @SerializedName("getuserdata")
    public Getuserdata getuserdata;
    @SerializedName("getbusinessdata")
    public Getbusinessdata getbusinessdata;
    @SerializedName("getconfdata")
    public Getconfdata getconfdata;
    @SerializedName("getdocdata")
    public Getdocdata getdocdata;
    @SerializedName("basepricechange")
    public String basepricechange;
    //    @SerializedName("getpendingorderdetails")
//    public List<Getpendingorderdetails> getpendingorderdetails;
//    @SerializedName("getplacedorderdetails")
//    public List<String> getplacedorderdetails;
//    @SerializedName("getoutofstockprosdetails")
//    public List<Getoutofstockprosdetails> getoutofstockprosdetails;
    private String userid;

    public SellerHomeResponse(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public String getGetpendingorders() {
        return getpendingorders;
    }

    public String getGetrejectedorders() {
        return getrejectedorders;
    }

    public String getGetsuccessorders() {
        return getsuccessorders;
    }

    public String getGetinstockpros() {
        return getinstockpros;
    }

    public String getGetoutofstockpros() {
        return getoutofstockpros;
    }

    public String getGetlimitedstockpros() {
        return getlimitedstockpros;
    }

    public Getuserdata getGetuserdata() {
        return getuserdata;
    }

    public Getbusinessdata getGetbusinessdata() {
        return getbusinessdata;
    }

    public Getconfdata getGetconfdata() {
        return getconfdata;
    }

//    public List<Getpendingorderdetails> getGetpendingorderdetails() {
//        return getpendingorderdetails;
//    }
//
//    public List<?> getGetplacedorderdetails() {
//        return getplacedorderdetails;
//    }
//
//    public List<Getoutofstockprosdetails> getGetoutofstockprosdetails() {
//        return getoutofstockprosdetails;
//    }

    public Getdocdata getGetdocdata() {
        return getdocdata;
    }

    public String getBasepricechange() {
        return basepricechange;
    }

}
