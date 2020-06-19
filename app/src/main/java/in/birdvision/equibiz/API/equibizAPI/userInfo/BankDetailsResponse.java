package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class BankDetailsResponse {

    @SerializedName("accholdername")
    private String accholdername;
    @SerializedName("accnumber")
    private String accnumber;
    @SerializedName("bankbranch")
    private String bankbranch;
    @SerializedName("bankcity")
    private String bankcity;
    @SerializedName("bankname")
    private String bankname;
    @SerializedName("ifscbranch")
    private String ifscbranch;
    @SerializedName("ifsccode")
    private String ifsccode;
    @SerializedName("role")
    private String role;
    @SerializedName("userobjid")
    private String userobjid;

    @SerializedName("status")
    private String status;
    @SerializedName("bankverified")
    private String bankverified;

    public BankDetailsResponse(String accholdername, String accnumber, String bankbranch, String bankcity,
                               String bankname, String ifscbranch, String ifsccode, String role, String userobjid) {
        this.accholdername = accholdername;
        this.accnumber = accnumber;
        this.bankbranch = bankbranch;
        this.bankcity = bankcity;
        this.bankname = bankname;
        this.ifscbranch = ifscbranch;
        this.ifsccode = ifsccode;
        this.role = role;
        this.userobjid = userobjid;
    }

    public String getAccholdername() {
        return accholdername;
    }

    public String getAccnumber() {
        return accnumber;
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public String getBankcity() {
        return bankcity;
    }

    public String getBankname() {
        return bankname;
    }

    public String getIfscbranch() {
        return ifscbranch;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public String getRole() {
        return role;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getStatus() {
        return status;
    }

    public String getBankverified() {
        return bankverified;
    }
}
