
package in.birdvision.equibiz.API.equibizAPI.buyer.product.productList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bannerdatum {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @Expose
    private String bannerimage;
    @Expose
    private String bannername;
    @Expose
    private Long bannerstatus;

    public Long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getBannerimage() {
        return bannerimage;
    }

    public String getBannername() {
        return bannername;
    }

    public Long getBannerstatus() {
        return bannerstatus;
    }

}
