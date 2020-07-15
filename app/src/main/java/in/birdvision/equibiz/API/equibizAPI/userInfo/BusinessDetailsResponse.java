/*
 * *
 *  * Created by Vaibhav Andhare on 15/7/20 12:53 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class BusinessDetailsResponse {

    @SerializedName("businessname")
    private String businessname;
    @SerializedName("businessnature")
    private String businessnature;
    @SerializedName("businesstype")
    private String businesstype;
    @SerializedName("owner_countrycode")
    private String owner_countrycode;
    @SerializedName("owner_mobnumber")
    private String owner_mobnumber;
    @SerializedName("ownerfname")
    private String ownerfname;
    @SerializedName("ownerlname")
    private String ownerlname;
    @SerializedName("pincode")
    private String pincode;
    @SerializedName("poc1_countrycode")
    private String poc1_countrycode;
    @SerializedName("poc1_mobile")
    private String poc1_mobile;
    @SerializedName("poc1_name")
    private String poc1_name;
    @SerializedName("poc2_countrycode")
    private String poc2_countrycode;
    @SerializedName("poc2_mobile")
    private String poc2_mobile;
    @SerializedName("poc2_name")
    private String poc2_name;
    @SerializedName("regd_address")
    private String regd_address;
    @SerializedName("role")
    private String role;
    @SerializedName("savelater")
    private String savelater;
    @SerializedName("userobjid")
    private String userobjid;
    @SerializedName("hublocation")
    private String hublocation;

    public BusinessDetailsResponse(String businessname, String businessnature, String businesstype,
                                   String owner_countrycode, String owner_mobnumber, String ownerfname,
                                   String ownerlname, String pincode, String poc1_countrycode, String poc1_mobile,
                                   String poc1_name, String poc2_countrycode, String poc2_mobile, String poc2_name,
                                   String regd_address, String role, String savelater, String userobjid, String hublocation) {
        this.businessname = businessname;
        this.businessnature = businessnature;
        this.businesstype = businesstype;
        this.owner_countrycode = owner_countrycode;
        this.owner_mobnumber = owner_mobnumber;
        this.ownerfname = ownerfname;
        this.ownerlname = ownerlname;
        this.pincode = pincode;
        this.poc1_countrycode = poc1_countrycode;
        this.poc1_mobile = poc1_mobile;
        this.poc1_name = poc1_name;
        this.poc2_countrycode = poc2_countrycode;
        this.poc2_mobile = poc2_mobile;
        this.poc2_name = poc2_name;
        this.regd_address = regd_address;
        this.role = role;
        this.savelater = savelater;
        this.userobjid = userobjid;
        this.hublocation = hublocation;
    }

    @SerializedName("status")
    public String status;
    @SerializedName("businessverified")
    public boolean businessverified;
    @SerializedName("bankdetails")
    public String bankdetails;
    @SerializedName("businessdetails")
    public Businessdetails businessdetails;

    public String getStatus() {
        return status;
    }

    public boolean isBusinessverified() {
        return businessverified;
    }

    public String getBankdetails() {
        return bankdetails;
    }

    public Businessdetails getBusinessdetails() {
        return businessdetails;
    }

    public static class Businessdetails {
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
        public Object poc1Mobile;
        @SerializedName("poc1_name")
        public String poc1Name;
        @SerializedName("poc2_countrycode")
        public String poc2Countrycode;
        @SerializedName("poc2_mobile")
        public Object poc2Mobile;
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

        public Object getPoc1Mobile() {
            return poc1Mobile;
        }

        public String getPoc1Name() {
            return poc1Name;
        }

        public String getPoc2Countrycode() {
            return poc2Countrycode;
        }

        public Object getPoc2Mobile() {
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
}
