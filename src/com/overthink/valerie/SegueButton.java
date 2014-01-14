package com.overthink.valerie;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by anthony on 1/13/14.
 */
public class SegueButton extends Button {

    public SegueButton(Context context) {
        super(context);
    }

    public SegueButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public SegueButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);    }
}
