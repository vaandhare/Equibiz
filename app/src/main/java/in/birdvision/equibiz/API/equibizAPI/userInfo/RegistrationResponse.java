package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("countrycode")
    private String countrycode;
    @SerializedName("email")
    private String email;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("mobilenumber")
    private String mobilenumber;
    @SerializedName("otp")
    private String otp;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private String role;

    @SerializedName("status")
    private String status;
    @SerializedName("userobjid")
    private String userobjid;
    @SerializedName("usertype")
    private String usertype;

    public RegistrationResponse(String countrycode, String email, String firstName, String lastName,
                                String mobilenumber, String otp, String password, String role) {
        this.countrycode = countrycode;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilenumber = mobilenumber;
        this.otp = otp;
        this.password = password;
        this.role = role;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getOtp() {
        return otp;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getUsertype() {
        return usertype;
    }
}
