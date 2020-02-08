package com.amin.foodmarket.ui.home;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Slider;
import com.opensooq.pluto.base.PlutoAdapter;
import com.opensooq.pluto.base.PlutoViewHolder;

import java.util.List;


public class HomeHeaderAdapter extends PlutoAdapter<Slider, HomeHeaderAdapter.HomeHeaderViewHolder> {

    public HomeHeaderAdapter(List<Slider> items) {
        super(items);
    }

    @Override
    public HomeHeaderAdapter.HomeHeaderViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new HomeHeaderViewHolder(parent, R.layout.home_header_item);
    }

    public static class HomeHeaderViewHolder extends PlutoViewHolder<Slider> {
        ImageView ivPoster;
       // TextView tvRating;

        public HomeHeaderViewHolder(ViewGroup parent, int itemLayoutId) {
            super(parent, itemLayoutId);
            ivPoster = getView(R.id.imageView);
          //  tvRating = getView(R.id.tv_rating);
        }

        @Override
        public void set(Slider item, int pos) {
            //  yourImageLoader.with(mContext).load(item.getPosterId()).into(ivPoster);
            ivPoster.setImageDrawable(item.getBackgroundImage());
            // tvRating.setText(item.getText());
        }
    }
}