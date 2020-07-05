
package in.birdvision.equibiz.API.equibizAPI.buyer.home;

import com.google.gson.annotations.Expose;

public class Bestdeal {

    @Expose
    private in.birdvision.equibiz.API.equibizAPI.buyer.home._id _id;
    @Expose
    private String brandid;
    @Expose
    private Brandinfo brandinfo;
    @Expose
    private Long orderscount;
    @Expose
    private String productid;
    @Expose
    private Productinfo productinfo;
    @Expose
    private String sellerproductid;
    @Expose
    private Sellerproductinfo sellerproductinfo;

    public in.birdvision.equibiz.API.equibizAPI.buyer.home._id get_id() {
        return _id;
    }

    public String getBrandid() {
        return brandid;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public Long getOrderscount() {
        return orderscount;
    }

    public String getProductid() {
        return productid;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public String getSellerproductid() {
        return sellerproductid;
    }

    public Sellerproductinfo getSellerproductinfo() {
        return sellerproductinfo;
    }
}
