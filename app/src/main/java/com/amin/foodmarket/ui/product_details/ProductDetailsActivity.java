package com.amin.foodmarket.ui.product_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amin.foodmarket.R;
import com.amin.foodmarket.databinding.ActivityProductDetailsBinding;
import com.amin.foodmarket.databinding.ActivitySplashBinding;
import com.amin.foodmarket.pojo.Product;
import com.amin.foodmarket.ui.home.CategoryAdapter;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for init data binding
        ActivityProductDetailsBinding binding =
                DataBindingUtil.setContentView(this,R.layout.activity_product_details);

        Intent intent = getIntent();
        product = intent.getParcelableExtra("product");

        if (product!=null){
            binding.productNameTv.setText(product.getName());
            binding.productWeightTv.setText(product.getWeight());
            binding.productPriceTv.setText(product.getPrice());
            Picasso.get().load(product.getProductImg())
                    .into(binding.productIv);
        }
    }
}
