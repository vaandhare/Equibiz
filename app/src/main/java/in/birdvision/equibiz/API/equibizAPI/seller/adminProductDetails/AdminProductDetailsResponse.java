
package in.birdvision.equibiz.API.equibizAPI.seller.adminProductDetails;

import com.google.gson.annotations.Expose;

import java.util.List;

import in.birdvision.equibiz.API.equibizAPI.seller.Adminproduct;

public class AdminProductDetailsResponse {

    @Expose
    private List<Adminproduct> adminproductdetails;
    @Expose
    private String status;

    private String proid;
    private String sellerid;

    public AdminProductDetailsResponse(String proid, String sellerid) {
        this.proid = proid;
        this.sellerid = sellerid;
    }

    public List<Adminproduct> getAdminproductdetails() {
        return adminproductdetails;
    }

    public String getStatus() {
        return status;
    }

}
