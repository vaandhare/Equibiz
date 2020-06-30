
package in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.confidentalData;

import com.google.gson.annotations.SerializedName;

public class ConfidentialDetailsResponse {

    @SerializedName("confdata")
    private Confdata mConfdata;
    @SerializedName("docdata")
    private Docdata mDocdata;
    @SerializedName("status")
    private String mStatus;

    @SerializedName("userid")
    private String userid;

    public ConfidentialDetailsResponse(String userid) {
        this.userid = userid;
    }

    public Confdata getConfdata() {
        return mConfdata;
    }

    public Docdata getDocdata() {
        return mDocdata;
    }

    public String getStatus() {
        return mStatus;
    }

}
