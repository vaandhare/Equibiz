package in.birdvision.equibiz.API.equibizAPI;

import in.birdvision.equibiz.API.equibizAPI.orders.PreBookingResponse;
import in.birdvision.equibiz.API.equibizAPI.product.productDetails.ProductDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.product.productList.ProductListResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.BankDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.BusinessDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.LoginResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.OtpResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.RegistrationResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.UploadDocuments;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Equibiz_API_Interface {

    //UserInfo (Login & Register)

    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginResponse loginResponse);

    @POST("registerpersonaldetails")
    Call<RegistrationResponse> registrationResponse(@Body RegistrationResponse registrationResponse);

    @POST("sendotp")
    Call<OtpResponse> sendOTP(@Body OtpResponse otpResponse);

    @POST("registerbusinessdetails")
    Call<BusinessDetailsResponse> businessDetailsResponse(@Body BusinessDetailsResponse businessDetailsResponse);

    @POST("registerbankdetails")
    Call<BankDetailsResponse> bankDetailsResponse(@Body BankDetailsResponse bankDetailsResponse);

    @POST("uploaddoc")
    Call<UploadDocuments> uploadDocuments(@Body UploadDocuments uploadDocuments);

    //Product
    @POST("getproducts")
    Call<ProductListResponse> allProductResponse(@Body ProductListResponse allProductsResponse);

    @POST("getproductdetails")
    Call<ProductDetailsResponse> productDetailsResponse(@Body ProductDetailsResponse productDetailsResponse);

    @POST("prebooking")
    Call<PreBookingResponse> preBookingResponse(@Body PreBookingResponse preBookingResponse);

}
