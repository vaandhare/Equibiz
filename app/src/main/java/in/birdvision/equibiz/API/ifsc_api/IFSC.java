package in.birdvision.equibiz.API.ifsc_api;

public class IFSC {
    private String CITY;
    private String ADDRESS;
    private String BRANCH;
    private String BANK;

    public IFSC(String CITY, String ADDRESS, String BRANCH, String BANK) {
        this.CITY = CITY;
        this.ADDRESS = ADDRESS;
        this.BRANCH = BRANCH;
        this.BANK = BANK;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getBANK() {
        return BANK;
    }

    public void setBANK(String BANK) {
        this.BANK = BANK;
    }
}
