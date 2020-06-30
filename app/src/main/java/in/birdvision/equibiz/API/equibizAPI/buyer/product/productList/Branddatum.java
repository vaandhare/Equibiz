
package in.birdvision.equibiz.API.equibizAPI.buyer.product.productList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branddatum {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @Expose
    private String brandname;
    @Expose
    private Long brandstatus;

    public Long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getBrandname() {
        return brandname;
    }

    public Long getBrandstatus() {
        return brandstatus;
    }
}
