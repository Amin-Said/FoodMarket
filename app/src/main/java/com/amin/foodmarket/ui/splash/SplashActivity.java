package com.amin.foodmarket.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.amin.foodmarket.R;
import com.amin.foodmarket.databinding.ActivitySplashBinding;
import com.amin.foodmarket.ui.intro.IntroSliderActivity;


public class SplashActivity extends AppCompatActivity {
    String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for init data binding
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

        // for loading animation
        if (!binding.light.isOn()) {
            binding.light.on();
        } else {
            binding.light.off();
        }

        // go to next activity after 4 seconds
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), IntroSliderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 4000);


    }

}
