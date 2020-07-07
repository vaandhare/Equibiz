
package in.birdvision.equibiz.API.equibizAPI.buyer.product.productList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.buyer.product.Productdatum;

public class ProductListResponse {

    @Expose
    private List<Branddatum> branddata;
    @SerializedName("products")
    private List<Productdatum> products;
    @Expose
    private String status;

    private String brand;

    public ProductListResponse(String brand) {
        this.brand = brand;
    }

    public List<Branddatum> getBranddata() {
        return branddata;
    }

    public List<Productdatum> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }
}
