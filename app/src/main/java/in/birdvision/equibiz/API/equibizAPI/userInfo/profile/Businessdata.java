
package in.birdvision.equibiz.API.equibizAPI.userInfo.profile;

import com.google.gson.annotations.SerializedName;

public class Businessdata {

    @SerializedName("buyer_b_name")
    private String mBuyerBName;
    @SerializedName("buyer_b_nature")
    private String mBuyerBNature;
    @SerializedName("buyer_b_type")
    private String mBuyerBType;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("hublocation")
    private String mHublocation;
    @SerializedName("owner_countrycode")
    private String mOwnerCountrycode;
    @SerializedName("owner_fname")
    private String mOwnerFname;
    @SerializedName("owner_lname")
    private String mOwnerLname;
    @SerializedName("owner_mobile")
    private Long mOwnerMobile;
    @SerializedName("pincode")
    private Long mPincode;
    @SerializedName("poc1_countrycode")
    private String mPoc1Countrycode;
    @SerializedName("poc1_mobile")
    private Long mPoc1Mobile;
    @SerializedName("poc1_name")
    private String mPoc1Name;
    @SerializedName("poc2_countrycode")
    private String mPoc2Countrycode;
    @SerializedName("poc2_mobile")
    private Long mPoc2Mobile;
    @SerializedName("poc2_name")
    private String mPoc2Name;
    @SerializedName("regd_address")
    private String mRegdAddress;
    @SerializedName("savelater")
    private Boolean mSavelater;
    @SerializedName("updatedDate")
    private String mUpdatedDate;
    @SerializedName("userobjid")
    private String mUserobjid;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getBuyerBName() {
        return mBuyerBName;
    }

    public void setBuyerBName(String buyerBName) {
        mBuyerBName = buyerBName;
    }

    public String getBuyerBNature() {
        return mBuyerBNature;
    }

    public void setBuyerBNature(String buyerBNature) {
        mBuyerBNature = buyerBNature;
    }

    public String getBuyerBType() {
        return mBuyerBType;
    }

    public void setBuyerBType(String buyerBType) {
        mBuyerBType = buyerBType;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public String getHublocation() {
        return mHublocation;
    }

    public void setHublocation(String hublocation) {
        mHublocation = hublocation;
    }

    public String getOwnerCountrycode() {
        return mOwnerCountrycode;
    }

    public void setOwnerCountrycode(String ownerCountrycode) {
        mOwnerCountrycode = ownerCountrycode;
    }

    public String getOwnerFname() {
        return mOwnerFname;
    }

    public String getOwnerLname() {
        return mOwnerLname;
    }

    public Long getOwnerMobile() {
        return mOwnerMobile;
    }

    public Long getPincode() {
        return mPincode;
    }

    public String getPoc1Countrycode() {
        return mPoc1Countrycode;
    }

    public Long getPoc1Mobile() {
        return mPoc1Mobile;
    }

    public String getPoc1Name() {
        return mPoc1Name;
    }

    public String getPoc2Countrycode() {
        return mPoc2Countrycode;
    }

    public Long getPoc2Mobile() {
        return mPoc2Mobile;
    }

    public String getPoc2Name() {
        return mPoc2Name;
    }

    public String getRegdAddress() {
        return mRegdAddress;
    }

    public Boolean getSavelater() {
        return mSavelater;
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
