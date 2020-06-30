package in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo;

import com.google.gson.annotations.SerializedName;

public class Buyerdata {

    @SerializedName("adminid")
    private String mAdminid;
    @SerializedName("adminverified")
    private Long mAdminverified;
    @SerializedName("adminverified_date")
    private String mAdminverifiedDate;
    @SerializedName("bankverified")
    private Boolean mBankverified;
    @SerializedName("businessverified")
    private Boolean mBusinessverified;
    @SerializedName("countrycode")
    private String mCountrycode;
    @SerializedName("created_date")
    private String mCreatedDate;
    @SerializedName("docverified")
    private Boolean mDocverified;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("emailverified")
    private Boolean mEmailverified;
    @SerializedName("fname")
    private String mFname;
    @SerializedName("hublocation")
    private String mHublocation;
    @SerializedName("lastlogin")
    private String mLastlogin;
    @SerializedName("lname")
    private String mLname;
    @SerializedName("mobile")
    private Long mMobile;
    @SerializedName("mobileverified")
    private Boolean mMobileverified;
    @SerializedName("name")
    private String mName;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("updated_date")
    private String mUpdatedDate;
    @SerializedName("usertype")
    private Long mUsertype;
    @SerializedName("wallet_bal")
    private Long mWalletBal;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getAdminid() {
        return mAdminid;
    }

    public void setAdminid(String adminid) {
        mAdminid = adminid;
    }

    public Long getAdminverified() {
        return mAdminverified;
    }

    public void setAdminverified(Long adminverified) {
        mAdminverified = adminverified;
    }

    public String getAdminverifiedDate() {
        return mAdminverifiedDate;
    }

    public void setAdminverifiedDate(String adminverifiedDate) {
        mAdminverifiedDate = adminverifiedDate;
    }

    public Boolean getBankverified() {
        return mBankverified;
    }

    public void setBankverified(Boolean bankverified) {
        mBankverified = bankverified;
    }

    public Boolean getBusinessverified() {
        return mBusinessverified;
    }

    public void setBusinessverified(Boolean businessverified) {
        mBusinessverified = businessverified;
    }

    public String getCountrycode() {
        return mCountrycode;
    }

    public void setCountrycode(String countrycode) {
        mCountrycode = countrycode;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public Boolean getDocverified() {
        return mDocverified;
    }

    public void setDocverified(Boolean docverified) {
        mDocverified = docverified;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Boolean getEmailverified() {
        return mEmailverified;
    }

    public void setEmailverified(Boolean emailverified) {
        mEmailverified = emailverified;
    }

    public String getFname() {
        return mFname;
    }

    public void setFname(String fname) {
        mFname = fname;
    }

    public String getHublocation() {
        return mHublocation;
    }

    public void setHublocation(String hublocation) {
        mHublocation = hublocation;
    }

    public String getLastlogin() {
        return mLastlogin;
    }

    public String getLname() {
        return mLname;
    }

    public Long getMobile() {
        return mMobile;
    }

    public Boolean getMobileverified() {
        return mMobileverified;
    }

    public String getName() {
        return mName;
    }

    public String getPassword() {
        return mPassword;
    }

    public Long getStatus() {
        return mStatus;
    }

    public String getUpdatedDate() {
        return mUpdatedDate;
    }

    public Long getUsertype() {
        return mUsertype;
    }

    public Long getWalletBal() {
        return mWalletBal;
    }

    public Long get_V() {
        return m_V;
    }

    public String get_id() {
        return m_id;
    }
}
