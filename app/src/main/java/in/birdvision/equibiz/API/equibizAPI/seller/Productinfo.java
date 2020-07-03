
package in.birdvision.equibiz.API.equibizAPI.seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Productinfo {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @SerializedName("p_base_high")
    private int pBaseHigh;
    @SerializedName("p_base_low")
    private int pBaseLow;
    @SerializedName("p_base_price")
    private int pBasePrice;
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
    private int pExpandableMemory;
    @SerializedName("p_front_camera")
    private int pFrontCamera;
    @SerializedName("p_images")
    private List<String> pImages;
    @SerializedName("p_model_no")
    private String pModelNo;
    @SerializedName("p_network_type")
    private String pNetworkType;
    @SerializedName("p_os_type")
    private String pOsType;
    @SerializedName("p_primary_camera")
    private int pPrimaryCamera;
    @SerializedName("p_processor")
    private String pProcessor;
    @SerializedName("p_screen_size")
    private String pScreenSize;
    @SerializedName("p_sim_slots")
    private int pSimSlots;
    @SerializedName("p_status")
    private int pStatus;
    @SerializedName("p_updated_date")
    private String pUpdatedDate;

    public Long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public int getpBaseHigh() {
        return pBaseHigh;
    }

    public int getpBaseLow() {
        return pBaseLow;
    }

    public int getpBasePrice() {
        return pBasePrice;
    }

    public String getpBatteryPower() {
        return pBatteryPower;
    }

    public String getpBrand() {
        return pBrand;
    }

    public String getpCreatedDate() {
        return pCreatedDate;
    }

    public String getpDesc() {
        return pDesc;
    }

    public String getpDimensions() {
        return pDimensions;
    }

    public int getpExpandableMemory() {
        return pExpandableMemory;
    }

    public int getpFrontCamera() {
        return pFrontCamera;
    }

    public List<String> getpImages() {
        return pImages;
    }

    public String getpModelNo() {
        return pModelNo;
    }

    public String getpNetworkType() {
        return pNetworkType;
    }

    public String getpOsType() {
        return pOsType;
    }

    public int getpPrimaryCamera() {
        return pPrimaryCamera;
    }

    public String getpProcessor() {
        return pProcessor;
    }

    public String getpScreenSize() {
        return pScreenSize;
    }

    public int getpSimSlots() {
        return pSimSlots;
    }

    public int getpStatus() {
        return pStatus;
    }

    public String getpUpdatedDate() {
        return pUpdatedDate;
    }
}
