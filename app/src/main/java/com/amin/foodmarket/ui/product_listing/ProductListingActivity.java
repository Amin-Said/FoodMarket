package com.amin.foodmarket.ui.product_listing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Category;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class ProductListingActivity extends AppCompatActivity {

    Category category;
    String TAG = "ProductListingActivity";
    ProductListingViewModel productListingViewModel;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TextView titleTV;
    ImageView categoryIV;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        // getting category data from intent and pass to view model
        Intent intent = getIntent();
        category = intent.getParcelableExtra("category");
        if (category != null) {
            // init view model for category
            productListingViewModel = ViewModelProviders.of(this).get(ProductListingViewModel.class);
            productListingViewModel.setCategoryData(category);
        }

        // init views
        titleTV = findViewById(R.id.category_name);
        categoryIV = findViewById(R.id.toolbar_image);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);

        //    for using status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // pass fragments to view pager
        pagerAdapter = new PagerAdapter
                (getSupportFragmentManager());
        pagerAdapter.addFragment(new ProductListingFragment(), "Sub category 1");
        pagerAdapter.addFragment(new ProductListingFragment(), "Sub category 2");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // getting category data from view model
        productListingViewModel.marketMutableLiveData.observe(this, new Observer<Category>() {
            @Override
            public void onChanged(Category category) {

                // toolbar views
                Picasso.get().load(category.getCategoryImg())
                        .into(categoryIV);
                titleTV.setText(category.getName());
                categoryIV.setColorFilter(getResources().getColor(R.color.colorTransparent));

            }
        });

        productListingViewModel.getMarketData();


    }

}
