package com.amin.foodmarket.ui.product_listing;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Category;
import com.amin.foodmarket.pojo.Product;
import java.util.ArrayList;


public class ProductListingFragment extends Fragment {

    String TAG = "ProductListingFragment";
    ProductListingViewModel productListingViewModel;
    RecyclerView recyclerView;


    public ProductListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_product_listing, container, false);
        recyclerView = v.findViewById(R.id.products_rv);

        // init view model for category fragment
        productListingViewModel = ViewModelProviders.of(getActivity()).get(ProductListingViewModel.class);

        // get category data
        productListingViewModel.marketMutableLiveData.observe(this, new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                if (category!=null){
                    final GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2,
                            GridLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    final ProductsAdapter mAdapter = new ProductsAdapter(
                            (ArrayList<Product>) category.getProducts(),getActivity());
                    recyclerView.setAdapter(mAdapter);
                }

            }
        });

        productListingViewModel.getMarketData();

        return v;
    }

}
