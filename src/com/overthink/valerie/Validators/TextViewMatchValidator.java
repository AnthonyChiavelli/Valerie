package com.overthink.valerie.Validators;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * A Validator that ensures that the contents of a text field match a String
 */
public class TextViewMatchValidator implements Validator {

    public static final String TAG = TextViewMatchValidator.class.getName();

    //Human-readable description of field requirements
    private final String requirementsMessage;
    //The String the field must match
    private final String matchString;

    /**
     * Constructor
     *
     * @param matchString the String to match against
     * @param requirementsMessage a String describing the requirements of the field in
     *                            human-readable language
     */
    public TextViewMatchValidator(String matchString, String requirementsMessage) {

        this.matchString = matchString;
        this.requirementsMessage = requirementsMessage;
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

        //Ensure that field's contents match the String
        return textView.getText().toString().equals(this.matchString);
    }

    @Override
    public String getRequirementsMessage() {

        //Return custom requirements message if set
        if(this.requirementsMessage != null) {
            return this.requirementsMessage;
        }
        return "invalid input";
    }
}
