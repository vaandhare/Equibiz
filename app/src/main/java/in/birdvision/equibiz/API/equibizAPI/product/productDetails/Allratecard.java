
package in.birdvision.equibiz.API.equibizAPI.product.productDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Allratecard implements Serializable {

    @SerializedName("__v")
    private long _V;
    @SerializedName("_id")
    private String _id;
    private long delcharges;
    private long deltime;
    private String location;

    public long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public long getDelcharges() {
        return delcharges;
    }

    public long getDeltime() {
        return deltime;
    }

    public String getLocation() {
        return location;
    }
}
