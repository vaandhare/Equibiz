
package in.birdvision.equibiz.API.equibizAPI.userInfo.profile;

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
        private Long mPending;
        @SerializedName("total")
        private Long mTotal;
        @SerializedName("wallet_bal")
        private Long mWalletBal;

        public Long getPending() {
            return mPending;
        }

        public Long getTotal() {
            return mTotal;
        }

        public Long getWalletBal() {
            return mWalletBal;
        }
    }

}
