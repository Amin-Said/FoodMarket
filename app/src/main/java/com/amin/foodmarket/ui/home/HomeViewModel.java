package com.amin.foodmarket.ui.home;

import android.util.Log;

import com.amin.foodmarket.api.ApiClient;
import com.amin.foodmarket.pojo.Category;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    String TAG = "HomeViewModel";
    MutableLiveData<ArrayList<Category>> marketMutableLiveData = new MutableLiveData<>();

    public void getMarketData(){

        ApiClient.getClient().getMarketData().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                marketMutableLiveData.setValue(addWeirdItemToCategories(response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.d(TAG,"error : "+t.getMessage());
            }
        });

    }

    private ArrayList<Category> addWeirdItemToCategories(ArrayList<Category> categories){
        ArrayList<Category> changedCategories = new ArrayList<>();
        for (int i=0;i<categories.size();i++){
            if (i==2){
                Category newCategory = new Category();
                newCategory.setName("Blueberry is a great fruit");
                newCategory.setType(2);
                changedCategories.add(newCategory);

                Category category = categories.get(i);
                category.setType(1);
                changedCategories.add(category);
            }else{
                Category category = categories.get(i);
                category.setType(1);
                changedCategories.add(category);
            }

        }

        if (changedCategories.size()==5){
            Category newCategory = new Category();
            newCategory.setName("Strawberry is a lovely fruit");
            newCategory.setType(2);
            changedCategories.add(newCategory);
        }

        return changedCategories;
    }

}
