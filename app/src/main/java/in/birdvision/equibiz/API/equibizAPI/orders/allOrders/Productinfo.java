package in.birdvision.equibiz.API.equibizAPI.orders.allOrders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Productinfo {

    @SerializedName("p_base_high")
    private Long mPBaseHigh;
    @SerializedName("p_base_low")
    private Long mPBaseLow;
    @SerializedName("p_base_price")
    private Long mPBasePrice;
    @SerializedName("p_battery_power")
    private String mPBatteryPower;
    @SerializedName("p_brand")
    private String mPBrand;
    @SerializedName("p_created_date")
    private String mPCreatedDate;
    @SerializedName("p_desc")
    private String mPDesc;
    @SerializedName("p_dimensions")
    private String mPDimensions;
    @SerializedName("p_expandable_memory")
    private Long mPExpandableMemory;
    @SerializedName("p_front_camera")
    private Long mPFrontCamera;
    @SerializedName("p_images")
    private List<String> mPImages;
    @SerializedName("p_model_no")
    private String mPModelNo;
    @SerializedName("p_network_type")
    private String mPNetworkType;
    @SerializedName("p_os_type")
    private String mPOsType;
    @SerializedName("p_primary_camera")
    private Long mPPrimaryCamera;
    @SerializedName("p_processor")
    private String mPProcessor;
    @SerializedName("p_screen_size")
    private String mPScreenSize;
    @SerializedName("p_sim_slots")
    private Long mPSimSlots;
    @SerializedName("p_status")
    private Long mPStatus;
    @SerializedName("p_updated_date")
    private String mPUpdatedDate;
    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public Long getmPBaseHigh() {
        return mPBaseHigh;
    }

    public Long getmPBaseLow() {
        return mPBaseLow;
    }

    public Long getmPBasePrice() {
        return mPBasePrice;
    }

    public String getmPBatteryPower() {
        return mPBatteryPower;
    }

    public String getmPBrand() {
        return mPBrand;
    }

    public String getmPCreatedDate() {
        return mPCreatedDate;
    }

    public String getmPDesc() {
        return mPDesc;
    }

    public String getmPDimensions() {
        return mPDimensions;
    }

    public Long getmPExpandableMemory() {
        return mPExpandableMemory;
    }

    public Long getmPFrontCamera() {
        return mPFrontCamera;
    }

    public List<String> getmPImages() {
        return mPImages;
    }

    public String getmPModelNo() {
        return mPModelNo;
    }

    public String getmPNetworkType() {
        return mPNetworkType;
    }

    public String getmPOsType() {
        return mPOsType;
    }

    public Long getmPPrimaryCamera() {
        return mPPrimaryCamera;
    }

    public String getmPProcessor() {
        return mPProcessor;
    }

    public String getmPScreenSize() {
        return mPScreenSize;
    }

    public Long getmPSimSlots() {
        return mPSimSlots;
    }

    public Long getmPStatus() {
        return mPStatus;
    }

    public String getmPUpdatedDate() {
        return mPUpdatedDate;
    }

    public Long getM_V() {
        return m_V;
    }

    public String getM_id() {
        return m_id;
    }
}
