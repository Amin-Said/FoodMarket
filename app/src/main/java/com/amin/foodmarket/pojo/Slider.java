package com.amin.foodmarket.pojo;

import android.graphics.drawable.Drawable;

public class Slider {

    Drawable image;
    Drawable backgroundImage;
    String text;

    public Drawable getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Drawable backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
