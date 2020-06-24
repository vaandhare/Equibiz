package in.birdvision.equibiz.API.equibizAPI.userInfo.profile.confidentalData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Confdata {

    @SerializedName("accholdername")
    private String mAccholdername;
    @SerializedName("accnumber")
    private Long mAccnumber;
    @SerializedName("bankbranch")
    private String mBankbranch;
    @SerializedName("bankcity")
    private String mBankcity;
    @SerializedName("bankname")
    private String mBankname;
    @SerializedName("chequebookimg")
    private List<String> mChequebookimg;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("ifscbranch")
    private String mIfscbranch;
    @SerializedName("ifsccode")
    private String mIfsccode;
    @SerializedName("updatedDate")
    private String mUpdatedDate;
    @SerializedName("userobjid")
    private String mUserobjid;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getAccholdername() {
        return mAccholdername;
    }

    public Long getAccnumber() {
        return mAccnumber;
    }

    public String getBankbranch() {
        return mBankbranch;
    }

    public String getBankcity() {
        return mBankcity;
    }

    public String getBankname() {
        return mBankname;
    }

    public List<String> getChequebookimg() {
        return mChequebookimg;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public String getIfscbranch() {
        return mIfscbranch;
    }

    public String getIfsccode() {
        return mIfsccode;
    }

    public String getUpdatedDate() {
        return mUpdatedDate;
    }

    public String getUserobjid() {
        return mUserobjid;
    }

    public Long get_V() {
        return m_V;
    }

    public String get_id() {
        return m_id;
    }

}
