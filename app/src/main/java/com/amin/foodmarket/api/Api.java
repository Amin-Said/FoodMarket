package com.amin.foodmarket.api;

import com.amin.foodmarket.pojo.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("task/categories")
    public Call<ArrayList<Category>> getMarketData();
}
