
package in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo;

import com.google.gson.annotations.SerializedName;

public class WalletDetailsResponse {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("walletdata")
    private Walletdata mWalletdata;
    @SerializedName("userid")
    private String userid;

    public WalletDetailsResponse(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return mStatus;
    }

    public Walletdata getWalletdata() {
        return mWalletdata;
    }

    public static class Walletdata {

        @SerializedName("pending")
        private Double mPending;
        @SerializedName("total")
        private Double mTotal;
        @SerializedName("wallet_bal")
        private Double mWalletBal;

        public Double getmPending() {
            return mPending;
        }

        public Double getmTotal() {
            return mTotal;
        }

        public Double getmWalletBal() {
            return mWalletBal;
        }
    }

}
