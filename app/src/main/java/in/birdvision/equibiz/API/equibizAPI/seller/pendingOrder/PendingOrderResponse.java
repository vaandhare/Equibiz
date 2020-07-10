/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PendingOrderResponse {

    @Expose
    private String count;
    @Expose
    private List<Orderslist> orderslist;
    @Expose
    private String status;

    private String userid;

    public PendingOrderResponse(String userid) {
        this.userid = userid;
    }

    public String getCount() {
        return count;
    }

    public List<Orderslist> getOrderslist() {
        return orderslist;
    }

    public String getStatus() {
        return status;
    }
}
