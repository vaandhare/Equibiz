package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class UploadDocuments {

    @SerializedName("status")
    private String status;
    @SerializedName("docverified")
    private String docverified;

    public String getStatus() {
        return status;
    }

    public String getDocverified() {
        return docverified;
    }
}
