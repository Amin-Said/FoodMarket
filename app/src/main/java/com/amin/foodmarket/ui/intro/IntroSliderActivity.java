package com.amin.foodmarket.ui.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Slider;
import com.amin.foodmarket.ui.home.HomeActivity;

import java.util.ArrayList;

public class IntroSliderActivity extends AppCompatActivity {

    String TAG = "IntroSliderActivity";
    ViewPager viewPager;
    SliderPagerAdapter sliderPagerAdapter;
    int page = 0;
    LinearLayout dotsLayout;
    TextView[] dotsTV;
    ArrayList<Slider> sliderArrayList;
    IntroViewModel introViewModel;
    int firstScroll=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.dots_layout);

        // init view model
        introViewModel = ViewModelProviders.of(this).get(IntroViewModel.class);
        introViewModel.sliderMutableLiveData.observe(this, new Observer<ArrayList<Slider>>() {
            @Override
            public void onChanged(ArrayList<Slider> sliders) {
                sliderArrayList = sliders;
                if (sliderArrayList != null) {
                    setupSliders(sliderArrayList);
                }
            }
        });

        introViewModel.getSlidersData(IntroSliderActivity.this);

    }


    private void setupSliders(ArrayList<Slider> sliders) {
        sliderPagerAdapter = new SliderPagerAdapter(this, sliders);
        viewPager.setAdapter(sliderPagerAdapter);
        setDotsView(0);
        if (sliderPagerAdapter.getCount() == page) {
            page = 0;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                // this to handle on scroll after user reach to slider 3
                if (page == 2 && position==2) {
                    if (firstScroll>1){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        firstScroll++;
                    }

                }else{
                    firstScroll=0;
                }

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                setDotsView(page);


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }




    private void setDotsView(int page) {
        dotsLayout.removeAllViews();
        dotsTV = new TextView[sliderArrayList.size()];

        for (int i = 0; i < dotsTV.length; i++) {
            dotsTV[i] = new TextView(this);
            dotsTV[i].setText(Html.fromHtml("&#8226"));
            dotsTV[i].setTextSize(50);
            dotsTV[i].setTextColor(getResources().getColor(R.color.colorWhite));
            dotsLayout.addView(dotsTV[i]);
        }
        // set current slider dot active
        if (dotsTV.length > 0) {
            dotsTV[page].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

}
