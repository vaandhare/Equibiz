/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

public class Getbusinessdata {
    @SerializedName("_id")
    public String id;
    @SerializedName("userobjid")
    public String userobjid;
    @SerializedName("__v")
    public String v;
    @SerializedName("buyer_b_name")
    public String buyerBName;
    @SerializedName("buyer_b_nature")
    public String buyerBNature;
    @SerializedName("buyer_b_type")
    public String buyerBType;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("hublocation")
    public String hublocation;
    @SerializedName("owner_countrycode")
    public String ownerCountrycode;
    @SerializedName("owner_fname")
    public String ownerFname;
    @SerializedName("owner_lname")
    public String ownerLname;
    @SerializedName("owner_mobile")
    public String ownerMobile;
    @SerializedName("pincode")
    public String pincode;
    @SerializedName("poc1_countrycode")
    public String poc1Countrycode;
    @SerializedName("poc1_mobile")
    public String poc1Mobile;
    @SerializedName("poc1_name")
    public String poc1Name;
    @SerializedName("poc2_countrycode")
    public String poc2Countrycode;
    @SerializedName("poc2_mobile")
    public String poc2Mobile;
    @SerializedName("poc2_name")
    public String poc2Name;
    @SerializedName("regd_address")
    public String regdAddress;
    @SerializedName("savelater")
    public boolean savelater;
    @SerializedName("updatedDate")
    public String updatedDate;

    public String getId() {
        return id;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getV() {
        return v;
    }

    public String getBuyerBName() {
        return buyerBName;
    }

    public String getBuyerBNature() {
        return buyerBNature;
    }

    public String getBuyerBType() {
        return buyerBType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getHublocation() {
        return hublocation;
    }

    public String getOwnerCountrycode() {
        return ownerCountrycode;
    }

    public String getOwnerFname() {
        return ownerFname;
    }

    public String getOwnerLname() {
        return ownerLname;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public String getPincode() {
        return pincode;
    }

    public String getPoc1Countrycode() {
        return poc1Countrycode;
    }

    public String getPoc1Mobile() {
        return poc1Mobile;
    }

    public String getPoc1Name() {
        return poc1Name;
    }

    public String getPoc2Countrycode() {
        return poc2Countrycode;
    }

    public String getPoc2Mobile() {
        return poc2Mobile;
    }

    public String getPoc2Name() {
        return poc2Name;
    }

    public String getRegdAddress() {
        return regdAddress;
    }

    public boolean isSavelater() {
        return savelater;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }
}
