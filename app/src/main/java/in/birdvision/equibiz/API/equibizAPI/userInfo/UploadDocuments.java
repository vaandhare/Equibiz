package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class UploadDocuments {

    @SerializedName("filepath")
    private String filepath;
    @SerializedName("label")
    private String label;
    @SerializedName("role")
    private String role;
    @SerializedName("userobjid")
    private String userobjid;
    @SerializedName("whichtype")
    private String whichtype;

    @SerializedName("status")
    private String status;
    @SerializedName("docverified")
    private String docverified;

    public UploadDocuments(String filepath, String label, String role, String userobjid, String whichtype) {
        this.filepath = filepath;
        this.label = label;
        this.role = role;
        this.userobjid = userobjid;
        this.whichtype = whichtype;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getLabel() {
        return label;
    }

    public String getRole() {
        return role;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getWhichtype() {
        return whichtype;
    }

    public String getStatus() {
        return status;
    }

    public String getDocverified() {
        return docverified;
    }
}
