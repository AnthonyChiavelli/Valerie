package com.overthink.valerie.Validators;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

/**
 * A Validator that validates EVERYTHING....
 */
public class AnythingValidator implements Validator {

    public static final String TAG = AnythingValidator.class.getName();

    @Override
    public boolean validate(View view) {

        // Always validate
        return true;
    }

    @Override
    public String getRequirementsMessage() {
        return null;
    }
}
