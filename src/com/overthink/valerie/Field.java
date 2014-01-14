package com.overthink.valerie;

import android.widget.RelativeLayout;
import com.overthink.valerie.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * A custom view container that holds a view (usually a UI input widget) and a failure indicator that is normally
 * hidden but will be displayed if the view fails validation.
 */
public class Field extends RelativeLayout{
    // The view that is to be validated
    private int inputView;
    // The view displayed when this field fails validation
    private int failureIndicatorView;
    // The Validator class to load to perform validation on inputView
    private String validatorClassString;

    public Field(Context context) {
        //todo class as parameter?
        super(context);
    }

    public Field(Context context, AttributeSet attrs) {
        // Call over to main constructor with no style
        this(context, attrs, 0);
    }

    public Field(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Obtain a styled, fully resolved attribute set
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Field, 0, defStyle);

        // Access attribute values
        try {
            validatorClassString = attributes.getString(R.styleable.Field_validator);
            inputView = attributes.getResourceId(R.styleable.Field_input_view, 0);
            failureIndicatorView = attributes.getResourceId(R.styleable.Field_failure_indicator_view, 0);
        }
        finally {
            // Give back array for later reuse
            attributes.recycle();
        }
    }

    public String getValidatorClassName() {
        return validatorClassString;
    }

    public int getFailureIndicatorViewId() {
        return failureIndicatorView;
    }

    public int getInputViewId() {
        return inputView;
    }
}
