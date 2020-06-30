package in.birdvision.equibiz.API.equibizAPI.userInfo;

public class SetPasswordResponse {

    private String code;
    private String email;
    private String password;
    private String status;

    public SetPasswordResponse(String code, String email, String password) {
        this.code = code;
        this.email = email;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }
}
