
package in.birdvision.equibiz.API.equibizAPI.product.productList;

import com.google.gson.annotations.Expose;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.product.Productdatum;

public class ProductListResponse {

    @Expose
    private List<Bannerdatum> bannerdata;
    @Expose
    private List<Branddatum> branddata;
    @Expose
    private List<String> models;
    @Expose
    private List<Productdatum> productdata;
    @Expose
    private String status;

    public List<Bannerdatum> getBannerdata() {
        return bannerdata;
    }

    public List<Branddatum> getBranddata() {
        return branddata;
    }

    public List<String> getModels() {
        return models;
    }

    public List<Productdatum> getProductdata() {
        return productdata;
    }

    public String getStatus() {
        return status;
    }

}
