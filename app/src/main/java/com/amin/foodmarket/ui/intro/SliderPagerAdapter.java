package com.amin.foodmarket.ui.intro;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Slider;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SliderPagerAdapter extends PagerAdapter {

    Activity activity;
    ArrayList<Slider> sliders;
    LayoutInflater layoutInflater;

    public SliderPagerAdapter(Activity activity, ArrayList<Slider> sliders) {
        this.activity = activity;
        this.sliders = sliders;
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.intro_slider_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.intro_slider_iv);
        TextView textView = itemView.findViewById(R.id.intro_slider_tv);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        imageView.setMinimumHeight(height);
        imageView.setMinimumWidth(width);

        textView.setText(sliders.get(position).getText());

        imageView.setImageDrawable(sliders.get(position).getImage());


        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}