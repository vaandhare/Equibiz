/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UploadDocuments {

    @SerializedName("status")
    private String status;
    @SerializedName("docverified")
    private String docverified;

    public String getStatus() {
        return status;
    }

    public String getDocverified() {
        return docverified;
    }

    private String label;
    private String role;
    private String userobjid;
    private String whichtype;
    private String gsttype;
    private ArrayList<byte[]> documentimages;

    public UploadDocuments(String label, String role, String userobjid, String whichtype, String gsttype, ArrayList<byte[]> documentimages) {
        this.label = label;
        this.role = role;
        this.userobjid = userobjid;
        this.whichtype = whichtype;
        this.gsttype = gsttype;
        this.documentimages = documentimages;
    }
}
