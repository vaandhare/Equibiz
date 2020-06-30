package in.birdvision.equibiz.API.equibizAPI.userInfo;

public class ForgotPasswordResponse {

    private String email;
    private String role;
    private String status;
    private int code;

    public ForgotPasswordResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
