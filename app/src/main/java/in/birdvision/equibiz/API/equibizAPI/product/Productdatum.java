
package in.birdvision.equibiz.API.equibizAPI.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productdatum {

    @Expose
    private in.birdvision.equibiz.API.equibizAPI.product._id _id;
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

    @SerializedName("search")
    private String mSearch;
    @SerializedName("searchres")
    private Boolean mSearchres;

    @SerializedName("modelno")
    private Boolean mModelno;

    public in.birdvision.equibiz.API.equibizAPI.product._id get_id() {
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

    public String getmSearch() {
        return mSearch;
    }

    public Boolean getmSearchres() {
        return mSearchres;
    }

    public Boolean getmModelno() {
        return mModelno;
    }
}
