
package in.birdvision.equibiz.API.equibizAPI.product.productList;

import com.google.gson.annotations.Expose;

public class Productdatum {

    @Expose
    private in.birdvision.equibiz.API.equibizAPI.product.productList._id _id;
    @Expose
    private Long asum;
    @Expose
    private Long avgsum;
    @Expose
    private Brandinfo brandinfo;
    @Expose
    private Productinfo productinfo;
    @Expose
    private Long prostatus;
    @Expose
    private Long stocksum;
    @Expose
    private Long sum;

    public in.birdvision.equibiz.API.equibizAPI.product.productList._id get_id() {
        return _id;
    }

    public Long getAsum() {
        return asum;
    }

    public Long getAvgsum() {
        return avgsum;
    }

    public Brandinfo getBrandinfo() {
        return brandinfo;
    }

    public Productinfo getProductinfo() {
        return productinfo;
    }

    public Long getProstatus() {
        return prostatus;
    }

    public Long getStocksum() {
        return stocksum;
    }

    public Long getSum() {
        return sum;
    }

}
