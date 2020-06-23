package in.birdvision.equibiz.API.equibizAPI.orders;

import com.google.gson.annotations.SerializedName;

public class PreBookingResponse {

    @SerializedName("buyerid")
    private String buyerid;
    @SerializedName("finalpricetodeduct")
    private String finalpricetodeduct;
    @SerializedName("insurance")
    private String insurance;
    @SerializedName("proid")
    private String proid;
    @SerializedName("qtyordered")
    private String qtyordered;
    @SerializedName("ratecardid")
    private String ratecardid;
    @SerializedName("sellerid")
    private String sellerid;
    @SerializedName("sellerproid")
    private String sellerproid;
    @SerializedName("sellertime")
    private String sellertime;
    @SerializedName("totalprice")
    private String totalprice;
    @SerializedName("unitprice")
    private String unitprice;
    @SerializedName("status")
    private String status;
    @SerializedName("walletbal")
    private String walletbal;

    public PreBookingResponse(String buyerid, String finalpricetodeduct, String insurance, String proid,
                              String qtyordered, String ratecardid, String sellerid, String sellerproid,
                              String sellertime, String totalprice, String unitprice) {

        this.buyerid = buyerid;
        this.finalpricetodeduct = finalpricetodeduct;
        this.insurance = insurance;
        this.proid = proid;
        this.qtyordered = qtyordered;
        this.ratecardid = ratecardid;
        this.sellerid = sellerid;
        this.sellerproid = sellerproid;
        this.sellertime = sellertime;
        this.totalprice = totalprice;
        this.unitprice = unitprice;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public String getFinalpricetodeduct() {
        return finalpricetodeduct;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getProid() {
        return proid;
    }

    public String getQtyordered() {
        return qtyordered;
    }

    public String getRatecardid() {
        return ratecardid;
    }

    public String getSellerid() {
        return sellerid;
    }

    public String getSellerproid() {
        return sellerproid;
    }

    public String getSellertime() {
        return sellertime;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public String getStatus() {
        return status;
    }

    public String getWalletbal() {
        return walletbal;
    }
}
