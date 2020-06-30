package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.annotations.SerializedName;

public class Brandinfo {

    @SerializedName("brandname")
    private String mBrandname;
    @SerializedName("brandstatus")
    private Long mBrandstatus;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getmBrandname() {
        return mBrandname;
    }

    public Long getmBrandstatus() {
        return mBrandstatus;
    }

    public Long getM_V() {
        return m_V;
    }

    public String getM_id() {
        return m_id;
    }
}
