package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("brand_id")
    private String mBrandId;
    @SerializedName("brandinfo")
    private Brandinfo mBrandinfo;
    @SerializedName("buyer_id")
    private List<String> mBuyerId;
    @SerializedName("price")
    private Long mPrice;
    @SerializedName("pro_id")
    private String mProId;
    @SerializedName("productinfo")
    private Productinfo mProductinfo;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("sellers")
    private List<Seller> mSellers;
    @SerializedName("_id")
    private String m_id;

    public String getmBrandId() {
        return mBrandId;
    }

    public Brandinfo getmBrandinfo() {
        return mBrandinfo;
    }

    public List<String> getmBuyerId() {
        return mBuyerId;
    }

    public Long getmPrice() {
        return mPrice;
    }

    public String getmProId() {
        return mProId;
    }

    public Productinfo getmProductinfo() {
        return mProductinfo;
    }

    public Long getmQuantity() {
        return mQuantity;
    }

    public List<Seller> getmSellers() {
        return mSellers;
    }

    public String getM_id() {
        return m_id;
    }
}
