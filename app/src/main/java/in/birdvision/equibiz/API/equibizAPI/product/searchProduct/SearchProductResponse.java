package in.birdvision.equibiz.API.equibizAPI.product.searchProduct;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.product.Productdatum;

public class SearchProductResponse {

    @SerializedName("productdata")
    private List<Productdatum> mProductdata;
    @SerializedName("status")
    private String mStatus;

    @SerializedName("searchtext")
    private String searchtext;

    public SearchProductResponse(String searchtext) {
        this.searchtext = searchtext;
    }

    public String getSearchtext() {
        return searchtext;
    }

    public List<Productdatum> getProductdata() {
        return mProductdata;
    }

    public void setProductdata(List<Productdatum> productdata) {
        mProductdata = productdata;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
