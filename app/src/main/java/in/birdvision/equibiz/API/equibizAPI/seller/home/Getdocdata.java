/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getdocdata {
    @SerializedName("_id")
    public String id;
    @SerializedName("userobjid")
    public String userobjid;
    @SerializedName("__v")
    public String v;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("gstin")
    public String gstin;
    @SerializedName("gstincomments")
    public String gstincomments;
    @SerializedName("gstinstatus")
    public String gstinstatus;
    @SerializedName("gsttype")
    public String gsttype;
    @SerializedName("pancard")
    public String pancard;
    @SerializedName("pancardcomments")
    public String pancardcomments;
    @SerializedName("pancardstatus")
    public String pancardstatus;
    @SerializedName("pancardpath")
    public List<String> pancardpath;
    @SerializedName("gstinpath")
    public List<String> gstinpath;

    public String getId() {
        return id;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getV() {
        return v;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getGstin() {
        return gstin;
    }

    public String getGstincomments() {
        return gstincomments;
    }

    public String getGstinstatus() {
        return gstinstatus;
    }

    public String getGsttype() {
        return gsttype;
    }

    public String getPancard() {
        return pancard;
    }

    public String getPancardcomments() {
        return pancardcomments;
    }

    public String getPancardstatus() {
        return pancardstatus;
    }

    public List<String> getPancardpath() {
        return pancardpath;
    }

    public List<String> getGstinpath() {
        return gstinpath;
    }
}

