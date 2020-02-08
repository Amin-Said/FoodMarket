package com.amin.foodmarket.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Category;
import com.amin.foodmarket.pojo.Slider;
import com.amin.foodmarket.ui.intro.IntroViewModel;
import com.opensooq.pluto.PlutoView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Category> categoryArrayList;
    String TAG = "HomeActivity";
    HomeViewModel homeViewModel;
    IntroViewModel introViewModel;
    ArrayList<Slider> sliderArrayList;
    RecyclerView recyclerView;
    PlutoView pluto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.categories_rv);
        pluto = findViewById(R.id.slider_view);


        // init view model for home slider (we are using the same view model for intro slider)
        introViewModel = ViewModelProviders.of(this).get(IntroViewModel.class);
        introViewModel.sliderMutableLiveData.observe(this, new Observer<ArrayList<Slider>>() {
            @Override
            public void onChanged(ArrayList<Slider> sliders) {
                sliderArrayList = sliders;
            }
        });

        introViewModel.getSlidersData(HomeActivity.this);

        // init view model for categories
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.marketMutableLiveData.observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                categoryArrayList = categories;
                if (categoryArrayList != null) {
                    final GridLayoutManager mLayoutManager = new GridLayoutManager(HomeActivity.this, 2,
                            GridLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    final CategoryAdapter mAdapter = new CategoryAdapter(HomeActivity.this,
                            categoryArrayList);
                    if (sliderArrayList != null) {
                        pluto.setVisibility(View.VISIBLE);
                        HomeHeaderAdapter adapter = new HomeHeaderAdapter(sliderArrayList);
                        pluto.create(adapter,getLifecycle());
                    }

                    recyclerView.setAdapter(mAdapter);

                    mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return mAdapter.isHeader(position) ? mLayoutManager.getSpanCount() : 1;
                        }
                    });
                }
            }
        });

        homeViewModel.getMarketData();

    }

}
