package com.amin.foodmarket.ui.product_listing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

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

        // pass fragments to view pager
        pagerAdapter = new PagerAdapter
                (getSupportFragmentManager());
        pagerAdapter.addFragment(new ProductListingFragment(), "Sub category 1");
        pagerAdapter.addFragment(new ProductListingFragment(), "Sub category 2");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        if (category!=null) {
            // getting category data from view model
            productListingViewModel.marketMutableLiveData.observe(this, new Observer<Category>() {
                @Override
                public void onChanged(Category category) {

                    // toolbar views
                    Picasso.get().load(category.getCategoryImg())
                            .into(categoryIV);
                    titleTV.setText(category.getName());

                }
            });

            productListingViewModel.getMarketData();

        }


    }

}
