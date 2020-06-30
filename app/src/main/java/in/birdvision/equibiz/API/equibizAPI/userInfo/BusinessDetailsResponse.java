package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class BusinessDetailsResponse {

    @SerializedName("businessname")
    private String businessname;
    @SerializedName("businessnature")
    private String businessnature;
    @SerializedName("businesstype")
    private String businesstype;
    @SerializedName("owner_countrycode")
    private String owner_countrycode;
    @SerializedName("owner_mobnumber")
    private String owner_mobnumber;
    @SerializedName("ownerfname")
    private String ownerfname;
    @SerializedName("ownerlname")
    private String ownerlname;
    @SerializedName("pincode")
    private String pincode;
    @SerializedName("poc1_countrycode")
    private String poc1_countrycode;
    @SerializedName("poc1_mobile")
    private String poc1_mobile;
    @SerializedName("poc1_name")
    private String poc1_name;
    @SerializedName("poc2_countrycode")
    private String poc2_countrycode;
    @SerializedName("poc2_mobile")
    private String poc2_mobile;
    @SerializedName("poc2_name")
    private String poc2_name;
    @SerializedName("regd_address")
    private String regd_address;
    @SerializedName("role")
    private String role;
    @SerializedName("savelater")
    private String savelater;
    @SerializedName("userobjid")
    private String userobjid;

    @SerializedName("status")
    private String status;
    @SerializedName("businessverified")
    private String businessverified;
    //    @SerializedName("bankdetails")
//    private BankDetails bankdetails;
    @SerializedName("businessdetails")
    private BusinessDetails businessdetails;
    @SerializedName("hublocation")
    private String hublocation;

    public BusinessDetailsResponse(String businessname, String businessnature, String businesstype,
                                   String owner_countrycode, String owner_mobnumber, String ownerfname,
                                   String ownerlname, String pincode, String poc1_countrycode, String poc1_mobile,
                                   String poc1_name, String poc2_countrycode, String poc2_mobile, String poc2_name,
                                   String regd_address, String role, String savelater, String userobjid, String hublocation) {
        this.businessname = businessname;
        this.businessnature = businessnature;
        this.businesstype = businesstype;
        this.owner_countrycode = owner_countrycode;
        this.owner_mobnumber = owner_mobnumber;
        this.ownerfname = ownerfname;
        this.ownerlname = ownerlname;
        this.pincode = pincode;
        this.poc1_countrycode = poc1_countrycode;
        this.poc1_mobile = poc1_mobile;
        this.poc1_name = poc1_name;
        this.poc2_countrycode = poc2_countrycode;
        this.poc2_mobile = poc2_mobile;
        this.poc2_name = poc2_name;
        this.regd_address = regd_address;
        this.role = role;
        this.savelater = savelater;
        this.userobjid = userobjid;
        this.hublocation = hublocation;
    }

    public String getStatus() {
        return status;
    }

    public String getBusinessverified() {
        return businessverified;
    }

//    public BankDetails getBankdetails() {
//        return bankdetails;
//    }

    public BusinessDetails getBusinessdetails() {
        return businessdetails;
    }

    public String getBusinessname() {
        return businessname;
    }

    public String getBusinessnature() {
        return businessnature;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public String getOwner_countrycode() {
        return owner_countrycode;
    }

    public String getOwner_mobnumber() {
        return owner_mobnumber;
    }

    public String getOwnerfname() {
        return ownerfname;
    }

    public String getOwnerlname() {
        return ownerlname;
    }

    public String getPincode() {
        return pincode;
    }

    public String getPoc1_countrycode() {
        return poc1_countrycode;
    }

    public String getPoc1_mobile() {
        return poc1_mobile;
    }

    public String getPoc1_name() {
        return poc1_name;
    }

    public String getPoc2_countrycode() {
        return poc2_countrycode;
    }

    public String getPoc2_mobile() {
        return poc2_mobile;
    }

    public String getPoc2_name() {
        return poc2_name;
    }

    public String getRegd_address() {
        return regd_address;
    }

    public String getRole() {
        return role;
    }

    public String getSavelater() {
        return savelater;
    }

    public String getUserobjid() {
        return userobjid;
    }

    public String getHublocation() {
        return hublocation;
    }

    private static class BankDetails {

        @SerializedName("_id")
        private String _id;
        @SerializedName("userobjid")
        private String userobjid;
        @SerializedName("__v")
        private String __v;
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
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("ifsccode")
        private String ifsccode;
        @SerializedName("updatedDate")
        private String updatedDate;
        @SerializedName("ifscbranch")
        private String ifscbranch;

        public String get_id() {
            return _id;
        }

        public String getUserobjid() {
            return userobjid;
        }

        public String get__v() {
            return __v;
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

        public String getCreatedDate() {
            return createdDate;
        }

        public String getIfsccode() {
            return ifsccode;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public String getIfscbranch() {
            return ifscbranch;
        }

    }

    public static class BusinessDetails {
        @SerializedName("_id")
        private String _id;
        @SerializedName("userobjid")
        private String userobjid;
        @SerializedName("__v")
        private String __v;
        @SerializedName("buyer_b_name")
        private String buyer_b_name;
        @SerializedName("buyer_b_nature")
        private String buyer_b_nature;
        @SerializedName("buyer_b_type")
        private String buyer_b_type;
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("hublocation")
        private String hublocation;
        @SerializedName("owner_countrycode")
        private String owner_countrycode;
        @SerializedName("owner_fname")
        private String owner_fname;
        @SerializedName("owner_lname")
        private String owner_lname;
        @SerializedName("owner_mobile")
        private String owner_mobile;
        @SerializedName("pincode")
        private String pincode;
        @SerializedName("poc1_countrycode")
        private String poc1_countrycode;
        @SerializedName("poc1_mobile")
        private String poc1_mobile;
        @SerializedName("poc1_name")
        private String poc1_name;
        @SerializedName("poc2_countrycode")
        private String poc2_countrycode;
        @SerializedName("poc2_mobile")
        private String poc2_mobile;
        @SerializedName("poc2_name")
        private String poc2_name;
        @SerializedName("regd_address")
        private String regd_address;
        @SerializedName("savelater")
        private String savelater;
        @SerializedName("updatedDate")
        private String updatedDate;

        public String get_id() {
            return _id;
        }

        public String getUserobjid() {
            return userobjid;
        }

        public String get__v() {
            return __v;
        }

        public String getBuyer_b_name() {
            return buyer_b_name;
        }

        public String getBuyer_b_nature() {
            return buyer_b_nature;
        }

        public String getBuyer_b_type() {
            return buyer_b_type;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getOwner_countrycode() {
            return owner_countrycode;
        }

        public String getOwner_fname() {
            return owner_fname;
        }

        public String getOwner_lname() {
            return owner_lname;
        }

        public String getOwner_mobile() {
            return owner_mobile;
        }

        public String getPincode() {
            return pincode;
        }

        public String getPoc1_countrycode() {
            return poc1_countrycode;
        }

        public String getPoc1_mobile() {
            return poc1_mobile;
        }

        public String getPoc1_name() {
            return poc1_name;
        }

        public String getPoc2_countrycode() {
            return poc2_countrycode;
        }

        public String getPoc2_mobile() {
            return poc2_mobile;
        }

        public String getPoc2_name() {
            return poc2_name;
        }

        public String getRegd_address() {
            return regd_address;
        }

        public String getSavelater() {
            return savelater;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }
    }
}
