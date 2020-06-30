package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("adminconfirmation")
    private Long mAdminconfirmation;
    @SerializedName("amount10percent")
    private Long mAmount10percent;
    @SerializedName("buyer_id")
    private String mBuyerId;
    @SerializedName("created_date")
    private String mCreatedDate;
    @SerializedName("expdelforbuyer")
    private String mExpdelforbuyer;
    @SerializedName("expdelforseller")
    private String mExpdelforseller;
    @SerializedName("finalpricetodeduct")
    private Long mFinalpricetodeduct;
    @SerializedName("insurance")
    private Long mInsurance;
    @SerializedName("order_date")
    private String mOrderDate;
    @SerializedName("order_id")
    private String mOrderId;
    @SerializedName("order_status")
    private Long mOrderStatus;
    @SerializedName("prebookpaymentdate")
    private String mPrebookpaymentdate;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("qty_ordered")
    private Long mQtyOrdered;
    @SerializedName("ratecardid")
    private String mRatecardid;
    @SerializedName("ratecardinfo")
    private Ratecardinfo mRatecardinfo;
    @SerializedName("seller_id")
    private String mSellerId;
    @SerializedName("sellerbusinfo")
    private String mSellerbusinfo;
    @SerializedName("sellerproductinfo")
    private Sellerproductinfo mSellerproductinfo;
    @SerializedName("sellerproid")
    private String mSellerproid;
    @SerializedName("sellertime")
    private String mSellertime;
    @SerializedName("total_price")
    private Long mTotalPrice;
    @SerializedName("unit_price")
    private Long mUnitPrice;
    @SerializedName("updated_date")
    private String mUpdatedDate;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public Long getmAdminconfirmation() {
        return mAdminconfirmation;
    }

    public Long getmAmount10percent() {
        return mAmount10percent;
    }

    public String getmBuyerId() {
        return mBuyerId;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public String getmExpdelforbuyer() {
        return mExpdelforbuyer;
    }

    public String getmExpdelforseller() {
        return mExpdelforseller;
    }

    public Long getmFinalpricetodeduct() {
        return mFinalpricetodeduct;
    }

    public Long getmInsurance() {
        return mInsurance;
    }

    public String getmOrderDate() {
        return mOrderDate;
    }

    public String getmOrderId() {
        return mOrderId;
    }

    public Long getmOrderStatus() {
        return mOrderStatus;
    }

    public String getmPrebookpaymentdate() {
        return mPrebookpaymentdate;
    }

    public String getmProductId() {
        return mProductId;
    }

    public Long getmQtyOrdered() {
        return mQtyOrdered;
    }

    public String getmRatecardid() {
        return mRatecardid;
    }

    public Ratecardinfo getmRatecardinfo() {
        return mRatecardinfo;
    }

    public String getmSellerId() {
        return mSellerId;
    }

    public String getmSellerbusinfo() {
        return mSellerbusinfo;
    }

    public Sellerproductinfo getmSellerproductinfo() {
        return mSellerproductinfo;
    }

    public String getmSellerproid() {
        return mSellerproid;
    }

    public String getmSellertime() {
        return mSellertime;
    }

    public Long getmTotalPrice() {
        return mTotalPrice;
    }

    public Long getmUnitPrice() {
        return mUnitPrice;
    }

    public String getmUpdatedDate() {
        return mUpdatedDate;
    }

    public Long getM_V() {
        return m_V;
    }

    public String getM_id() {
        return m_id;
    }
}