/*
 * *
 *  * Created by Vaibhav Andhare on 19/7/20 11:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getconfdata {
    @SerializedName("_id")
    public String id;
    @SerializedName("userobjid")
    public String userobjid;
    @SerializedName("__v")
    public String v;
    @SerializedName("accholdername")
    public String accholdername;
    @SerializedName("accnumber")
    public String accnumber;
    @SerializedName("bankbranch")
    public String bankbranch;
    @SerializedName("bankcity")
    public String bankcity;
    @SerializedName("bankname")
    public String bankname;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("ifsccode")
    public String ifsccode;
    @SerializedName("updatedDate")
    public String updatedDate;
    @SerializedName("chequebookimg")
    public List<String> chequebookimg;

    public String getId() {
        return id;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getV() {
        return v;
    }

    public String getAccholdername() {
        return accholdername;
    }

    public String getAccnumber() {
        return accnumber;
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public String getBankcity() {
        return bankcity;
    }

    public String getBankname() {
        return bankname;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public List<String> getChequebookimg() {
        return chequebookimg;
    }
}
