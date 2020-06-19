package in.birdvision.equibiz.API.ifsc_api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IFSC_API_Interface {

    @GET("/{IFSC_CODE}")
    Call<IFSC> getIFSCCode(@Path("IFSC_CODE") String code);

}
