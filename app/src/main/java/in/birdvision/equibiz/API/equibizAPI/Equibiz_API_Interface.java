/*
 * *
 *  * Created by Vaibhav Andhare on 15/7/20 12:53 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI;

import java.util.Map;

import in.birdvision.equibiz.API.equibizAPI.buyer.home.BuyerHomeResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.PreBookingResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.AllOrdersResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.filterBrand.BrandFilterResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.filterModel.ModelFilterResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productDetails.ProductDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.productList.ProductListResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.product.searchProduct.SearchProductResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.UserProfileResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.WalletDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.buyer.profileUserInfo.confidentalData.ConfidentialDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.BankDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.BusinessDetailsResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.FillWalletResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.ForgotPasswordResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.LoginResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.OtpResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.RegistrationResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.SetPasswordResponse;
import in.birdvision.equibiz.API.equibizAPI.userInfo.UploadDocuments;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface Equibiz_API_Interface {

    //UserInfo (Login & Register)
    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginResponse loginResponse);

    @POST("registerpersonaldetails")
    Call<RegistrationResponse> registrationResponse(@Body RegistrationResponse registrationResponse);

    @POST("sendotp")
    Call<OtpResponse> sendOTP(@Body OtpResponse otpResponse);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("registerbusinessdetails")
    Call<BusinessDetailsResponse> businessDetailsResponse(@Body BusinessDetailsResponse businessDetailsResponse,
                                                          @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("registerbankdetails")
    Call<BankDetailsResponse> bankDetailsResponse(@Body BankDetailsResponse bankDetailsResponse,
                                                  @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @Multipart
    @POST("uploaddoc")
    Call<UploadDocuments> uploadDocuments(@PartMap Map<String, RequestBody> requestBodyMap,
//                                          @Part MultipartBody.Part file,
                                          @Header("Authorization") String auth);

    //Product
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getproducts")
    Call<BuyerHomeResponse> buyerHomeResponse(
            @Body BuyerHomeResponse homeResponse,
            @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getbrandproducts")
    Call<ProductListResponse> productListResponse(
            @Body ProductListResponse response,
            @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getproductdetails")
    Call<ProductDetailsResponse> productDetailsResponse(
            @Body ProductDetailsResponse productDetailsResponse,
            @Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("prebooking")
    Call<PreBookingResponse> preBookingResponse(
            @Body PreBookingResponse preBookingResponse,
            @Header("Authorization") String auth);

    //User Profile
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getalldetailsofbuyer")
    Call<UserProfileResponse> userProfileResponse(
            @Body UserProfileResponse userProfileResponse,
            @Header("Authorization") String auth);

    //Wallet
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getwalletdetails")
    Call<WalletDetailsResponse> walletDetailsResponse(
            @Body WalletDetailsResponse walletDetailsResponse,
            @Header("Authorization") String auth);

    //Fill Wallet
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("fillwallet")
    Call<FillWalletResponse> fillWalletResponse(
            @Body FillWalletResponse fillWalletResponse,
            @Header("Authorization") String auth);

    //Confidential Data
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getconfdetails")
    Call<ConfidentialDetailsResponse> confidentialDetailsResponse(
            @Body ConfidentialDetailsResponse confidentialDetailsResponse,
            @Header("Authorization") String auth);

    //Search
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getsearch")
    Call<SearchProductResponse> searchProductResponse(
            @Body SearchProductResponse searchProductResponse,
            @Header("Authorization") String auth);

    //Model Filter
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getmodelpros")
    Call<ModelFilterResponse> modelFilterResponse(
            @Body ModelFilterResponse modelFilterResponse,
            @Header("Authorization") String auth);

    //Brand Filter
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getbrandpros")
    Call<BrandFilterResponse> brandFilterResponse(
            @Body BrandFilterResponse brandFilterResponse,
            @Header("Authorization") String auth);

    //Get all Orders
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("getallorders")
    Call<AllOrdersResponse> allOrderResponse(
            @Body AllOrdersResponse allOrdersResponse,
            @Header("Authorization") String auth);

    @POST("sendcodeforgotpass")
    Call<ForgotPasswordResponse> forgotPasswordResponse(@Body ForgotPasswordResponse forgotPasswordResponse);

    @POST("setpassword")
    Call<SetPasswordResponse> setForgotPasswordResponse(
            @Body SetPasswordResponse setPasswordResponse);

}
