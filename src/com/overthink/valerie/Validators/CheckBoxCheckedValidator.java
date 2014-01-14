package com.overthink.valerie.Validators;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

/**
 * A Validator that ensures that a check box is checked
 */
public class CheckBoxCheckedValidator implements Validator {

    public static final String TAG = CheckBoxCheckedValidator.class.getName();

    //Human-readable description of field requirements
    private String requirementsMessage;

    /**
     * Constructor
     *
     * @param requirementsMessage a String describing the requirements of the field in
     *                            human-readable language
     */
    public CheckBoxCheckedValidator(String requirementsMessage) {

        this.requirementsMessage = requirementsMessage;
    }

    @Override
    public boolean validate(View view) {

        //Attempt to cast view to check box
        CheckBox checkBox;
        try {
            checkBox = (CheckBox) view;
        }
        catch (ClassCastException e) {
            //If it fails, field is not considered valid
            Log.e(TAG, "Could not cast Field's View to a CheckBox. This Validator expects a Checkbox", e);
            return false;
        }

        //Ensure that checkbox is selected
        return checkBox.isChecked();


    }

    @Override
    public String getRequirementsMessage() {

        //Return custom requirements message if set
        if(this.requirementsMessage != null) {
            return this.requirementsMessage;
        }
        return "selection required";
    }
}
