
/*
 * *
 *  * Created by Vaibhav Andhare on 9/7/20 5:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

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
        private String mPending;
        @SerializedName("total")
        private String mTotal;
        @SerializedName("wallet_bal")
        private String mWalletBal;

        public String getmPending() {
            return mPending;
        }

        public String getmTotal() {
            return mTotal;
        }

        public String getmWalletBal() {
            return mWalletBal;
        }
    }

}
