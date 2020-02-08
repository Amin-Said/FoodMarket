package com.amin.foodmarket.ui.intro;

import android.app.Activity;
import com.amin.foodmarket.R;
import com.amin.foodmarket.pojo.Slider;
import java.util.ArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IntroViewModel extends ViewModel {

    String TAG = "IntroViewModel";
    public MutableLiveData<ArrayList<Slider>> sliderMutableLiveData = new MutableLiveData<>();

    public void getSlidersData(Activity activity){
        sliderMutableLiveData.setValue(setSlidersData(activity));
    }

    private ArrayList<Slider> setSlidersData(Activity activity){

        ArrayList<Slider> sliders = new ArrayList<>();

        Slider slider1 = new Slider();
        slider1.setImage(activity.getResources().getDrawable(R.drawable.easyshoping));
        slider1.setBackgroundImage(activity.getResources().getDrawable(R.drawable.backgroundone));
        slider1.setText("Shopping easily");
        sliders.add(slider1);

        Slider slider2 = new Slider();
        slider2.setImage(activity.getResources().getDrawable(R.drawable.delivery));
        slider2.setBackgroundImage(activity.getResources().getDrawable(R.drawable.backgroundthree));
        slider2.setText("Fast delivery");
        sliders.add(slider2);

        Slider slider3 = new Slider();
        slider3.setImage(activity.getResources().getDrawable(R.drawable.joker));
        slider3.setBackgroundImage(activity.getResources().getDrawable(R.drawable.backgroundtwo));
        slider3.setText("Friendly support team :)");
        sliders.add(slider3);

        return sliders;

    }

}
