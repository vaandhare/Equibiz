
package in.birdvision.equibiz.API.equibizAPI.buyer.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Productinfo {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @SerializedName("p_base_high")
    private Long pBaseHigh;
    @SerializedName("p_base_low")
    private Long pBaseLow;
    @SerializedName("p_base_price")
    private Long pBasePrice;
    @SerializedName("p_battery_power")
    private String pBatteryPower;
    @SerializedName("p_brand")
    private String pBrand;
    @SerializedName("p_created_date")
    private String pCreatedDate;
    @SerializedName("p_desc")
    private String pDesc;
    @SerializedName("p_dimensions")
    private String pDimensions;
    @SerializedName("p_expandable_memory")
    private Long pExpandableMemory;
    @SerializedName("p_front_camera")
    private Long pFrontCamera;
    @SerializedName("p_images")
    private List<String> pImages;
    @SerializedName("p_model_no")
    private String pModelNo;
    @SerializedName("p_network_type")
    private String pNetworkType;
    @SerializedName("p_os_type")
    private String pOsType;
    @SerializedName("p_primary_camera")
    private Long pPrimaryCamera;
    @SerializedName("p_processor")
    private String pProcessor;
    @SerializedName("p_screen_size")
    private String pScreenSize;
    @SerializedName("p_sim_slots")
    private Long pSimSlots;
    @SerializedName("p_status")
    private Long pStatus;
    @SerializedName("p_updated_date")
    private String pUpdatedDate;

    public Long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public Long getPBaseHigh() {
        return pBaseHigh;
    }

    public Long getPBaseLow() {
        return pBaseLow;
    }

    public Long getPBasePrice() {
        return pBasePrice;
    }

    public String getPBatteryPower() {
        return pBatteryPower;
    }

    public String getPBrand() {
        return pBrand;
    }

    public String getPCreatedDate() {
        return pCreatedDate;
    }

    public String getPDesc() {
        return pDesc;
    }

    public String getPDimensions() {
        return pDimensions;
    }

    public Long getPExpandableMemory() {
        return pExpandableMemory;
    }

    public Long getPFrontCamera() {
        return pFrontCamera;
    }

    public List<String> getPImages() {
        return pImages;
    }

    public String getPModelNo() {
        return pModelNo;
    }

    public String getPNetworkType() {
        return pNetworkType;
    }

    public String getPOsType() {
        return pOsType;
    }

    public Long getPPrimaryCamera() {
        return pPrimaryCamera;
    }

    public String getPProcessor() {
        return pProcessor;
    }

    public String getPScreenSize() {
        return pScreenSize;
    }

    public Long getPSimSlots() {
        return pSimSlots;
    }

    public Long getPStatus() {
        return pStatus;
    }

    public String getPUpdatedDate() {
        return pUpdatedDate;
    }

}
