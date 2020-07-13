/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 1:14 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.AllOrdersParser;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.AllOrdersResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EquibizApiService {

    private static final String BASE_URL = "http://equibiz.birdvisiontech.com:3000/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
        client.addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        });

        if (retrofit == null) {

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(AllOrdersResponse.class, new AllOrdersParser());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
