package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class OtpResponse {

    @SerializedName("countrycode")
    private String countrycode;
    @SerializedName("mobilenumber")
    private String mobilenumber;
    @SerializedName("role")
    private String role;

    @SerializedName("status")
    private String status;
    @SerializedName("otp")
    private String otp;

    public OtpResponse(String countrycode, String mobilenumber, String role) {
        this.countrycode = countrycode;
        this.mobilenumber = mobilenumber;
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public String getOtp() {
        return otp;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
