package in.birdvision.equibiz.API.equibizAPI.orders.allOrders;

import com.google.gson.annotations.SerializedName;

public class Sellerproductinfo {

    @SerializedName("available_stock")
    private Long mAvailableStock;
    @SerializedName("avg_price")
    private Long mAvgPrice;
    @SerializedName("bpchangenotify")
    private Long mBpchangenotify;
    @SerializedName("color")
    private String mColor;
    @SerializedName("created_date")
    private String mCreatedDate;
    @SerializedName("internal_memory")
    private String mInternalMemory;
    @SerializedName("minqty")
    private Long mMinqty;
    @SerializedName("pro_status")
    private Long mProStatus;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("ram_mob")
    private String mRamMob;
    @SerializedName("time_to_del")
    private String mTimeToDel;
    @SerializedName("updated_date")
    private String mUpdatedDate;
    @SerializedName("userid")
    private String mUserid;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public Long getmAvailableStock() {
        return mAvailableStock;
    }

    public Long getmAvgPrice() {
        return mAvgPrice;
    }

    public Long getmBpchangenotify() {
        return mBpchangenotify;
    }

    public String getmColor() {
        return mColor;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public String getmInternalMemory() {
        return mInternalMemory;
    }

    public Long getmMinqty() {
        return mMinqty;
    }

    public Long getmProStatus() {
        return mProStatus;
    }

    public String getmProductId() {
        return mProductId;
    }

    public String getmRamMob() {
        return mRamMob;
    }

    public String getmTimeToDel() {
        return mTimeToDel;
    }

    public String getmUpdatedDate() {
        return mUpdatedDate;
    }

    public String getmUserid() {
        return mUserid;
    }

    public Long getM_V() {
        return m_V;
    }

    public String getM_id() {
        return m_id;
    }
}
