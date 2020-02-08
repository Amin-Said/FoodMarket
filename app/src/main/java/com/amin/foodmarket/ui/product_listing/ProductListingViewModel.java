package com.amin.foodmarket.ui.product_listing;


import com.amin.foodmarket.pojo.Category;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ProductListingViewModel extends ViewModel {
    String TAG = "ProductListingViewModel";
    MutableLiveData<Category> marketMutableLiveData = new MutableLiveData<>();
    Category mCategory;

    public void getMarketData(){
        if (mCategory!=null) {
            marketMutableLiveData.setValue(mCategory);
        }
    }

    public void setCategoryData(Category category){
        mCategory =category;
    }

}
