package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class FillWalletResponse {

    @SerializedName("accno")
    private String accno;
    @SerializedName("amount")
    private String amount;
    @SerializedName("userid")
    private String userid;

    @SerializedName("status")
    private String status;

    public FillWalletResponse(String accno, String amount, String userid) {
        this.accno = accno;
        this.amount = amount;
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public String getAccno() {
        return accno;
    }

    public String getAmount() {
        return amount;
    }

    public String getUserid() {
        return userid;
    }
}
