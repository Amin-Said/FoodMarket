package com.amin.foodmarket.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Category;
import com.amin.foodmarket.ui.product_listing.ProductListingActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Category> itemsList;
    Context mContext;

    public CategoryAdapter(Context context, ArrayList<Category> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view;
        switch (i) {
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, null);
                NormalCategoryHolder mh = new NormalCategoryHolder(view);
                return mh;
            case 2:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_weird_item, null);
                CategoryAdapter.SingleItemRowHolder mho = new CategoryAdapter.SingleItemRowHolder(view);
                return mho;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {


        final Category singleItem = itemsList.get(i);
        Activity activity = (Activity) mContext;
        switch (singleItem.getType()) {
            case 1:

                ((NormalCategoryHolder) holder).tvTitleNormal.setText(singleItem.getName());
                Picasso.get().load(singleItem.getCategoryImg())
                        .into(((NormalCategoryHolder) holder).itemImageNormal);

                ((NormalCategoryHolder) holder).cardNormal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, ProductListingActivity.class);
                        intent.putExtra("category",singleItem);
                        mContext.startActivity(intent);
                    }
                });

                break;
            case 2:

                ((CategoryAdapter.SingleItemRowHolder) holder).tvTitle.setText(singleItem.getName());
                if (i > 3) {
                    ((SingleItemRowHolder) holder).cardView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    ((SingleItemRowHolder) holder).itemImage.
                            setImageDrawable(mContext.getResources().getDrawable(R.drawable.strawberry));

                }
                ((SingleItemRowHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
        }


    }

    @Override
    public int getItemViewType(int position) {
        Category categories = itemsList.get(position);
        if (categories != null) {
            return categories.getType();
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView itemImage;
        protected CardView cardView;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.category_tv);
            this.itemImage = (ImageView) view.findViewById(R.id.category_iv);
            this.cardView = view.findViewById(R.id.card_view);

        }

    }

    public class NormalCategoryHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitleNormal;
        protected ImageView itemImageNormal;
        protected CardView cardNormal;

        public NormalCategoryHolder(View view) {
            super(view);
            this.tvTitleNormal = (TextView) view.findViewById(R.id.category_tv);
            this.itemImageNormal = (ImageView) view.findViewById(R.id.category_iv);
            this.cardNormal = view.findViewById(R.id.card_view);

        }
    }

    public boolean isHeader(int position) {
        if (position == 2) {
            return position == 2;
        } else if (position == 5) {
            return position == 5;
        } else {
            return false;

        }
    }
}