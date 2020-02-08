package com.amin.foodmarket.ui.product_listing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Product;
import com.amin.foodmarket.ui.product_details.ProductDetailsActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    ArrayList<Product> itemsList = new ArrayList<>();
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV,weightTV,priceTV;
        public ImageView imageIV;
        public View horizontalDivider,verticalDivider;
        public ConstraintLayout constraintLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.product_name);
            weightTV = itemView.findViewById(R.id.product_weight);
            priceTV = itemView.findViewById(R.id.product_price);
            imageIV = itemView.findViewById(R.id.product_image);
            horizontalDivider = itemView.findViewById(R.id.horizontal_divider);
            verticalDivider = itemView.findViewById(R.id.vertical_divider);
            constraintLayout = itemView.findViewById(R.id.product_item);



        }
    }

    public ProductsAdapter(ArrayList<Product> list, Context context) {
        this.itemsList = list;
        this.context = context;
    }

    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ProductsAdapter.ViewHolder vh = new ProductsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position) {

        final Product model = itemsList.get(position);

        // for dividers
        if (position==0 || itemsList.size()%position==0){
            if (position!=1){
                holder.horizontalDivider.setVisibility(View.GONE);
            }
        }
        if (position ==0 ||position==1){
            holder.verticalDivider.setVisibility(View.GONE);
        }

        // set values to views
        holder.nameTV.setText(model.getName());
        holder.weightTV.setText(model.getWeight());
        holder.priceTV.setText(model.getPrice());
        Picasso.get().load(model.getProductImg())
                .into(holder.imageIV);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("product",model);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

}
