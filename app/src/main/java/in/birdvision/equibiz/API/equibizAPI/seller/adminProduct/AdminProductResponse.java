
package in.birdvision.equibiz.API.equibizAPI.seller.adminProduct;

import com.google.gson.annotations.Expose;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.seller.Adminproduct;

public class AdminProductResponse {

    @Expose
    private List<Adminproduct> adminproducts;
    @Expose
    private List<Sellerproduct> sellerproducts;
    @Expose
    private String status;

    private String userid;

    public AdminProductResponse(String userid) {
        this.userid = userid;
    }

    public List<Adminproduct> getAdminproducts() {
        return adminproducts;
    }

    public List<Sellerproduct> getSellerproducts() {
        return sellerproducts;
    }

    public String getStatus() {
        return status;
    }
}
