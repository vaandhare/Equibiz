package in.birdvision.equibiz.userInfo.ifsc_api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/{IFSC_CODE}")
    Call<IFSC> getIFSCCode(@Path("IFSC_CODE") String code);

}
