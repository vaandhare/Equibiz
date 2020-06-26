package in.birdvision.equibiz.API.equibizAPI.orders.allOrders;

import com.google.gson.annotations.SerializedName;

public class Ratecardinfo {

    @SerializedName("delcharges")
    private Long mDelcharges;
    @SerializedName("deltime")
    private Long mDeltime;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public Long getmDelcharges() {
        return mDelcharges;
    }

    public Long getmDeltime() {
        return mDeltime;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Long getM_V() {
        return m_V;
    }

    public String getM_id() {
        return m_id;
    }
}
