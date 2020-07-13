/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 2:01 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import java.util.HashMap;
import java.util.List;

public class AllOrders {

    public HashMap<String, List<ArrayProduct>> allorders;

    public AllOrders() {
        this.allorders = new HashMap<>();
    }

    public HashMap<String, List<ArrayProduct>> getAllorders() {
        return allorders;
    }
}
