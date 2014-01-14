package com.overthink.valerie;

import java.util.List;

/**
 * Implement this to create on-validation-failure callbacks that you can register with a FormFragment
 */
public interface OnValidationFailureListener {

    /**
     * Called when any of the registered Validators of a FormFragment failed to validate their View's contents
     *
     * @param failedFields the fields for which validation failed
     */
    public void onFailure(List<Field> failedFields);

}
