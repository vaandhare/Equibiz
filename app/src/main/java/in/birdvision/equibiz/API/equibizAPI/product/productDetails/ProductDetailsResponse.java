
package in.birdvision.equibiz.API.equibizAPI.product.productDetails;

import java.util.List;

public class ProductDetailsResponse {

    private List<Allratecard> allratecards;
    private List<String> colors;
    private List<Productdatum> productdata;
    private List<Sellerlist> sellerlist;
    private String status;

    private String color;
    private String internalmemory;
    private String proid;
    private String ram;

    public ProductDetailsResponse(String color, String internalmemory, String proid, String ram) {
        this.color = color;
        this.internalmemory = internalmemory;
        this.proid = proid;
        this.ram = ram;
    }

    public List<Allratecard> getAllratecards() {
        return allratecards;
    }

    public List<String> getColors() {
        return colors;
    }

    public List<Productdatum> getProductdata() {
        return productdata;
    }

    public List<Sellerlist> getSellerlist() {
        return sellerlist;
    }

    public String getStatus() {
        return status;
    }
}

