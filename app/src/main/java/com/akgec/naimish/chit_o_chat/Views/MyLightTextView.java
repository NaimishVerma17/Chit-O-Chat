package com.akgec.naimish.chit_o_chat.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by itakg on 12/29/2017.
 */

public class MyLightTextView extends AppCompatTextView {
    private static final String LIGHT_TEXT = "Fonts/Ubuntu-Light.ttf";
    public MyLightTextView(Context context) {
        super(context);
    }

    public MyLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf.createFromAsset(getContext().getAssets(),LIGHT_TEXT));
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        super.setTextColor(color);
    }
}
