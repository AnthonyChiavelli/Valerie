package com.overthink.valerie.Validators;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 *  A Validator that ensures that a spinner is not unselected
 */
public class SpinnerHasSomeSelectionValidator implements Validator{

    public static final String TAG = SpinnerHasSomeSelectionValidator.class.getName();

    //Human-readable description of field requirements
    private String requirementsMessage;
    //The Id of the required selection
    private long selectionId;

    /**
     * Constructor
     *
     * @param requirementsMessage a String describing the requirements of the field in
     *                            human-readable language
     * @param selectionId the ID of the item that must be selected in the spinner
     */
    public SpinnerHasSomeSelectionValidator(String requirementsMessage, long selectionId) {

        this.requirementsMessage = requirementsMessage;
        this.selectionId = selectionId;
    }

    @Override
    public boolean validate(View view) {

        //Attempt to cast view to spinner
        Spinner spinner;
        try {
            spinner = (Spinner) view;
        }
        catch (ClassCastException e) {
            //If it fails, field is not considered valid
            Log.e(TAG, "Could not cast Field's View to a Spinner. This Validator expects a Spinner", e);
            return false;
        }

        //Ensure that the correct selection is selected
        return spinner.getSelectedItemId() != AdapterView.INVALID_ROW_ID;
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
