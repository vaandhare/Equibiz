/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 4:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder;

public class AcceptResonse {

    private String order_id;

    private String status;

    public AcceptResonse(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }
}
