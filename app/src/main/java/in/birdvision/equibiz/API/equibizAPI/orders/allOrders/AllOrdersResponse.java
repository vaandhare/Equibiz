package in.birdvision.equibiz.API.equibizAPI.orders.allOrders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrdersResponse {

    @SerializedName("userid")
    private String userid;
    @SerializedName("orderids")
    private List<Orderid> mOrderids;
    @SerializedName("orders")
    private List<Order> mOrders;
    @SerializedName("status")
    private String mStatus;

    public AllOrdersResponse(String userid) {
        this.userid = userid;
    }

    public List<Orderid> getmOrderids() {
        return mOrderids;
    }

    public List<Order> getmOrders() {
        return mOrders;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getUserid() {
        return userid;
    }
}
