package in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.confidentalData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docdata {

    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("gstin")
    private String mGstin;
    @SerializedName("gstincomments")
    private String mGstincomments;
    @SerializedName("gstinpath")
    private List<String> mGstinpath;
    @SerializedName("gstinstatus")
    private Long mGstinstatus;
    @SerializedName("gsttype")
    private String mGsttype;
    @SerializedName("pancard")
    private String mPancard;
    @SerializedName("pancardcomments")
    private String mPancardcomments;
    @SerializedName("pancardpath")
    private List<String> mPancardpath;
    @SerializedName("pancardstatus")
    private Long mPancardstatus;
    @SerializedName("userobjid")
    private String mUserobjid;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public String getGstin() {
        return mGstin;
    }

    public String getGstincomments() {
        return mGstincomments;
    }

    public List<String> getGstinpath() {
        return mGstinpath;
    }

    public Long getGstinstatus() {
        return mGstinstatus;
    }

    public String getGsttype() {
        return mGsttype;
    }

    public String getPancard() {
        return mPancard;
    }

    public String getPancardcomments() {
        return mPancardcomments;
    }

    public List<String> getPancardpath() {
        return mPancardpath;
    }

    public Long getPancardstatus() {
        return mPancardstatus;
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
