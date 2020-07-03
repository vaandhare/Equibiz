
package in.birdvision.equibiz.API.equibizAPI.seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brandinfo {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @Expose
    private String adminid;
    @Expose
    private String brandname;
    @Expose
    private Long brandstatus;
    @SerializedName("created_date")
    private String createdDate;
    @SerializedName("updated_date")
    private String updatedDate;

    public Long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getAdminid() {
        return adminid;
    }

    public String getBrandname() {
        return brandname;
    }

    public Long getBrandstatus() {
        return brandstatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }
}
