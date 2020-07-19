/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

public class Getuserdata {
    @SerializedName("_id")
    public String id;
    @SerializedName("usertype")
    public String usertype;
    @SerializedName("name")
    public String name;
    @SerializedName("fname")
    public String fname;
    @SerializedName("lname")
    public String lname;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("email")
    public String email;
    @SerializedName("countrycode")
    public String countrycode;
    @SerializedName("password")
    public String password;
    @SerializedName("lastlogin")
    public String lastlogin;
    @SerializedName("status")
    public String status;
    @SerializedName("emailverified")
    public boolean emailverified;
    @SerializedName("mobileverified")
    public boolean mobileverified;
    @SerializedName("businessverified")
    public boolean businessverified;
    @SerializedName("docverified")
    public boolean docverified;
    @SerializedName("bankverified")
    public boolean bankverified;
    @SerializedName("wallet_bal")
    public String walletBal;
    @SerializedName("adminverified")
    public String adminverified;
    @SerializedName("created_date")
    public String createdDate;
    @SerializedName("__v")
    public String v;
    @SerializedName("adminid")
    public String adminid;
    @SerializedName("adminverified_date")
    public String adminverifiedDate;
    @SerializedName("updated_date")
    public String updatedDate;
    @SerializedName("hublocation")
    public String hublocation;

    public String getId() {
        return id;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getName() {
        return name;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public String getPassword() {
        return password;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public String getStatus() {
        return status;
    }

    public boolean isEmailverified() {
        return emailverified;
    }

    public boolean isMobileverified() {
        return mobileverified;
    }

    public boolean isBusinessverified() {
        return businessverified;
    }

    public boolean isDocverified() {
        return docverified;
    }

    public boolean isBankverified() {
        return bankverified;
    }

    public String getWalletBal() {
        return walletBal;
    }

    public String getAdminverified() {
        return adminverified;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getV() {
        return v;
    }

    public String getAdminid() {
        return adminid;
    }

    public String getAdminverifiedDate() {
        return adminverifiedDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getHublocation() {
        return hublocation;
    }
}
