package in.birdvision.equibiz.API.equibizAPI.product.filterModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.product.Productdatum;

public class ModelFilterResponse {

    @SerializedName("productdata")
    private List<Productdatum> mProductdata;
    @SerializedName("status")
    private String mStatus;

    @SerializedName("models")
    private String models;

    public ModelFilterResponse(String models) {
        this.models = models;
    }

    public String getModels() {
        return models;
    }

    public List<Productdatum> getProductdata() {
        return mProductdata;
    }

    public String getStatus() {
        return mStatus;
    }
}
