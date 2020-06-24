
package in.birdvision.equibiz.API.equibizAPI.userInfo.profile;

import com.google.gson.annotations.SerializedName;

public class UserProfileResponse {

    @SerializedName("userid")
    private String userid;
    @SerializedName("businessdata")
    private Businessdata mBusinessdata;
    @SerializedName("buyerdata")
    private Buyerdata mBuyerdata;
    @SerializedName("status")
    private String mStatus;

    public UserProfileResponse(String userid) {
        this.userid = userid;
    }

    public Businessdata getBusinessdata() {
        return mBusinessdata;
    }

    public Buyerdata getBuyerdata() {
        return mBuyerdata;
    }

    public String getStatus() {
        return mStatus;
    }

}
