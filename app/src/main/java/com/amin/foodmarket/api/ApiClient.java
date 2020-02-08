package com.amin.foodmarket.api;

import com.amin.foodmarket.pojo.Category;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //https://5bcce576cf2e850013874767.mockapi.io/task/categories
    private static final String BASE_URL = "https://5bcce576cf2e850013874767.mockapi.io/";
    private Api marketData;

    private static ApiClient client;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        marketData = retrofit.create(Api.class);

    }

    public static ApiClient getClient() {
        if (client==null){
            client = new ApiClient();
        }
        return client;
    }

    public Call<ArrayList<Category>> getMarketData(){
        return marketData.getMarketData();
    }

}
