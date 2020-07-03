package in.birdvision.equibiz.API.equibizAPI;

import in.birdvision.equibiz.API.equibizAPI.seller.adminProduct.AdminProductResponse;
import in.birdvision.equibiz.API.equibizAPI.seller.adminProductDetails.AdminProductDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.seller.pendingOrder.PendingOrderResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EquibizSeller_API_interface {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getadminproducts")
    Call<AdminProductResponse> adminProductResponse(@Body AdminProductResponse adminProductResponse,
                                                    @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getadminproductdetails")
    Call<AdminProductDetailsResponse> adminProductDetailsResponse(@Body AdminProductDetailsResponse response,
                                                                  @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getpendingplaced")
    Call<PendingOrderResponse> pendingOrdersResponse(@Body PendingOrderResponse pendingOrderResponse,
                                                     @Header("Authorization") String auth);

}
