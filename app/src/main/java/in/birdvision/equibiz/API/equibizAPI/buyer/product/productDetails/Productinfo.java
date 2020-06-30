
package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Productinfo {

    @SerializedName("__v")
    private long _V;
    private String _id;
    @SerializedName("p_base_high")
    private long pBaseHigh;
    @SerializedName("p_base_low")
    private long pBaseLow;
    @SerializedName("p_base_price")
    private long pBasePrice;
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
    private long pExpandableMemory;
    @SerializedName("p_front_camera")
    private long pFrontCamera;
    @SerializedName("p_images")
    private List<String> pImages;
    @SerializedName("p_model_no")
    private String pModelNo;
    @SerializedName("p_network_type")
    private String pNetworkType;
    @SerializedName("p_os_type")
    private String pOsType;
    @SerializedName("p_primary_camera")
    private long pPrimaryCamera;
    @SerializedName("p_processor")
    private String pProcessor;
    @SerializedName("p_screen_size")
    private String pScreenSize;
    @SerializedName("p_sim_slots")
    private long pSimSlots;
    @SerializedName("p_status")
    private long pStatus;
    @SerializedName("p_updated_date")
    private String pUpdatedDate;

    public long get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public long getpBaseHigh() {
        return pBaseHigh;
    }

    public long getpBaseLow() {
        return pBaseLow;
    }

    public long getpBasePrice() {
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

    public long getpExpandableMemory() {
        return pExpandableMemory;
    }

    public long getpFrontCamera() {
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

    public long getpPrimaryCamera() {
        return pPrimaryCamera;
    }

    public String getpProcessor() {
        return pProcessor;
    }

    public String getpScreenSize() {
        return pScreenSize;
    }

    public long getpSimSlots() {
        return pSimSlots;
    }

    public long getpStatus() {
        return pStatus;
    }

    public String getpUpdatedDate() {
        return pUpdatedDate;
    }
}
