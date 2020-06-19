package in.birdvision.equibiz.API.equibizAPI.userInfo;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private String role;
    @SerializedName("data")
    private Data data;

    public LoginResponse(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
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

    public Data getData() {
        return data;
    }

    public static class Data {
        @SerializedName("_id")
        private String _id;
        @SerializedName("usertype")
        private String usertype;
        @SerializedName("name")
        private String name;
        @SerializedName("fname")
        private String fname;
        @SerializedName("lname")
        private String lname;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("email")
        private String email;
        @SerializedName("countrycode")
        private String countrycode;
        @SerializedName("password")
        private String password;
        @SerializedName("lastlogin")
        private String lastlogin;
        @SerializedName("status")
        private String status;
        @SerializedName("emailverified")
        private String emailverified;
        @SerializedName("mobileverified")
        private String mobileverified;
        @SerializedName("businessverified")
        private String businessverified;
        @SerializedName("docverified")
        private String docverified;
        @SerializedName("bankverified")
        private String bankverified;
        @SerializedName("wallet_bal")
        private String wallet_bal;
        @SerializedName("adminverified")
        private String adminverified;
        @SerializedName("created_date")
        private String created_date;
        @SerializedName("__v")
        private String __v;
        @SerializedName("adminid")
        private String adminid;
        @SerializedName("adminverified_date")
        private String adminverified_date;

        public String get_id() {
            return _id;
        }

        public String getUsertype() {
            return usertype;
        }

        public String getName() {
            return name;
        }

        public String getFname() {
            return fname;
        }

        public String getLname() {
            return lname;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

        public String getCountrycode() {
            return countrycode;
        }

        public String getPassword() {
            return password;
        }

        public String getLastlogin() {
            return lastlogin;
        }

        public String getStatus() {
            return status;
        }

        public String getEmailverified() {
            return emailverified;
        }

        public String getMobileverified() {
            return mobileverified;
        }

        public String getBusinessverified() {
            return businessverified;
        }

        public String getDocverified() {
            return docverified;
        }

        public String getBankverified() {
            return bankverified;
        }

        public String getWallet_bal() {
            return wallet_bal;
        }

        public String getAdminverified() {
            return adminverified;
        }

        public String getCreated_date() {
            return created_date;
        }

        public String get__v() {
            return __v;
        }

        public String getAdminid() {
            return adminid;
        }

        public String getAdminverified_date() {
            return adminverified_date;
        }

    }
}
