package in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PendingOrderResponse {

    @Expose
    private int count;
    @Expose
    private List<Orderslist> orderslist;
    @Expose
    private String status;

    private String userid;

    public PendingOrderResponse(String userid) {
        this.userid = userid;
    }

    public int getCount() {
        return count;
    }

    public List<Orderslist> getOrderslist() {
        return orderslist;
    }

    public String getStatus() {
        return status;
    }
}
