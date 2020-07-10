
/*
 * *
 *  * Created by Vaibhav Andhare on 10/7/20 9:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Productinfo {

    @SerializedName("__v")
    private String _V;
    private String _id;
    @SerializedName("p_base_high")
    private String pBaseHigh;
    @SerializedName("p_base_low")
    private String pBaseLow;
    @SerializedName("p_base_price")
    private String pBasePrice;
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
    private String pExpandableMemory;
    @SerializedName("p_front_camera")
    private String pFrontCamera;
    @SerializedName("p_images")
    private List<String> pImages;
    @SerializedName("p_model_no")
    private String pModelNo;
    @SerializedName("p_network_type")
    private String pNetworkType;
    @SerializedName("p_os_type")
    private String pOsType;
    @SerializedName("p_primary_camera")
    private String pPrimaryCamera;
    @SerializedName("p_processor")
    private String pProcessor;
    @SerializedName("p_screen_size")
    private String pScreenSize;
    @SerializedName("p_sim_slots")
    private String pSimSlots;
    @SerializedName("p_status")
    private String pStatus;
    @SerializedName("p_updated_date")
    private String pUpdatedDate;

    public String get_V() {
        return _V;
    }

    public String get_id() {
        return _id;
    }

    public String getpBaseHigh() {
        return pBaseHigh;
    }

    public String getpBaseLow() {
        return pBaseLow;
    }

    public String getpBasePrice() {
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

    public String getpExpandableMemory() {
        return pExpandableMemory;
    }

    public String getpFrontCamera() {
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

    public String getpPrimaryCamera() {
        return pPrimaryCamera;
    }

    public String getpProcessor() {
        return pProcessor;
    }

    public String getpScreenSize() {
        return pScreenSize;
    }

    public String getpSimSlots() {
        return pSimSlots;
    }

    public String getpStatus() {
        return pStatus;
    }

    public String getpUpdatedDate() {
        return pUpdatedDate;
    }
}
