package com.overthink.valerie.Validators;

import android.view.View;

/**
 * Performs validation on the field to which it is connected.
 *
 *  A Validator is
 * responsible for validating the view with which it is paired. Since the validate() method takes a View, this means
 * no compile-time assurance that you won't end up associating a Spinner with a validator designed to validate a
 * Checkbox. If you do so, a class cast exception is likely on your horizon.
 * <p>
 */
public interface Validator {

    /**
     * Validates a field, that is, determines if field contents should be accepted as input.
     *
     * @param view the field to perform validation on. Must be of the correct birth class
     * @return true if the contents of the field are accepted, false if they are not
     */
    public boolean validate(View view);

    /**
     * Get a String explaining in human-readable terms the requirements of the Validator
     * @return
     */
    public String getRequirementsMessage();
}
