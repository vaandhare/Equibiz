package in.birdvision.equibiz.API.equibizAPI.product.filterBrand;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.product.Productdatum;

public class BrandFilterResponse {

    @SerializedName("brands")
    private String brands;
    @SerializedName("productdata")
    private List<Productdatum> mProductdata;
    @SerializedName("status")
    private String mStatus;

    public BrandFilterResponse(String brands) {
        this.brands = brands;
    }

    public String getBrands() {
        return brands;
    }

    public List<Productdatum> getProductdata() {
        return mProductdata;
    }

    public String getStatus() {
        return mStatus;
    }
}
