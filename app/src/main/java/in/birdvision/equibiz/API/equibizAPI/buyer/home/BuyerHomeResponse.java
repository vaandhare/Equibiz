
package in.birdvision.equibiz.API.equibizAPI.buyer.home;

import com.google.gson.annotations.Expose;

import java.util.List;

public class BuyerHomeResponse {

    @Expose
    private List<Bannerdatum> bannerdata;
    @Expose
    private List<Bestdeal> bestdeals;
    @Expose
    private List<Branddatum> branddata;
    @Expose
    private String status;

    public List<Bannerdatum> getBannerdata() {
        return bannerdata;
    }

    public List<Bestdeal> getBestdeals() {
        return bestdeals;
    }

    public List<Branddatum> getBranddata() {
        return branddata;
    }

    public String getStatus() {
        return status;
    }
}
