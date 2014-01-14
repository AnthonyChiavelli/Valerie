package com.overthink.valerie.Validators;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * A Validator that ensures a text view is not empty
 */
public class TextViewNotEmptyValidator implements Validator {

    public static final String TAG = TextViewNotEmptyValidator.class.getName();

    //Human-readable description of field requirements
    private String requirementsMessage;

    /**
     * Constructor
     *
     * @param requirementsMessage a String describing the requirements of the field in
     *                          human-readable language
     */
    public TextViewNotEmptyValidator(String requirementsMessage) {

        this.requirementsMessage = requirementsMessage;
    }

    /**
     * Nullary constructor needed to load class by string name
     */
    public TextViewNotEmptyValidator() {
    }

    @Override
    public boolean validate(View view) {

        //Attempt to cast view to textview
        TextView textView;
        try {
            textView = (TextView) view;
        }
        catch (ClassCastException e) {
            //If it fails, field is not considered valid
            Log.e(TAG, "Could not cast Field's View to a TextView. This Validator expects a TextView", e);
            return false;
        }

        //Ensure that field is not empty
        return !textView.getText().toString().isEmpty();
    }

    @Override
    public String getRequirementsMessage() {

        //Return custom requirements message if set
        if (this.requirementsMessage != null) {
            return this.requirementsMessage;
        }
        return "field must not be blank";
    }
}
