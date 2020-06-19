package in.birdvision.equibiz.API.ifsc_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RasorpayApiService {

    private static final String BASE_URL = "https://ifsc.razorpay.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
