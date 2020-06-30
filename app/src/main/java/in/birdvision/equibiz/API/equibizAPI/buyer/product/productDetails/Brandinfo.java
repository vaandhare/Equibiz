
package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

public class Brandinfo {

    @SerializedName("__v")
    private long _V;
    private String _id;
    private String brandname;
    private long brandstatus;

    public long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getBrandname() {
        return brandname;
    }

    public long getBrandstatus() {
        return brandstatus;
    }
}
