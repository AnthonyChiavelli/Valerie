package com.overthink.valerie;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.overthink.valerie.Validators.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the validation of a set of FormFields, usually corresponds to a single fragment or activity.
 * <p>
 * A FormManager turns your view into a form with field validation by allowing the registration of Fields, which
 * contain UI views and optional failure messages Fields may be registered in code, via the registerField() method,
 * or by using the Field views in your layout. In the latter case, all you have to do is pass your view tree to
 * registerFieldsInViewTree() and Valerie will register all Fields it finds. You may also use any combination
 * of these methods, specifying some fields in XML and leaving some to be registered programmatically.
 * <p>
 * A Field, whether specified in code or XML, may have a Validator and/or a FailureIndicator. The former refers to a
 * subclass of Validator that performs validation on its associated Field, the latter, a view to be displayed if the
 * Field does not validate successfully. Validators implement the Validator interface and provide a method to return
 * true or false depending on the success of the validation upon that form.
 * <p>
 * Register success and failure callbacks  registerOnValidationSuccessCallback() and
 * registerOnValidationFailureCallback(). If any form in the field fails validation when it is triggered by a press of
 * the segue button, the failure callbacks are called in the order they were called with a list of the offending forms.
 * Otherwise, the success callbacks are made in order.
 * <p>
 * The validateForm() method will initiate the validation of the form, and result in either the calling of the
 * registered success callbacks if all fields validate, or the calling of the registered failure callbacks if any fail.
 */
public class FormManager {
    public static final String TAG = FormManager.class.getName();

    // Ordered list of registered Fields
    private List<Field> registeredFields = new ArrayList<Field>();
    // Ordered list of registered success callbacks
    private List<OnValidationSuccessListener> registeredSuccessCallbacks = new ArrayList<OnValidationSuccessListener>();
    // Ordered list of registered failure callbacks
    private List<OnValidationFailureListener> registeredFailureCallbacks = new ArrayList<OnValidationFailureListener>();

    /**
     * Traverses the provided view tree in search of Fields and SegueButtons to register.
     *
     * @param root the root of the tree to traverse
     */
    public void registerFieldsInViewTree(View root) {
        // Check this view node
        // If it's a Field add it to the field list
        if (root instanceof Field) {
            registeredFields.add((Field) root);
        }
        // If it's a view group recursively check its children
        else if (root instanceof ViewGroup) {
            int childCount = ((ViewGroup)root).getChildCount();
            for (int i = 0; i < childCount; i++) {
                registerFieldsInViewTree(((ViewGroup) root).getChildAt(i));
            } 
        }
    }

    /**
     * Validates each Field by calling its associated Validator's validate() method. If any fail, the user-provided
     * on failure callbacks will be made and any failure indicator views are displayed, otherwise the on success
     * callbacks will be made in order.
     */
    public void validateForm() {
        // Keep track of failed fields
        List<Field> failedFields = new ArrayList<Field>();

        // Validate each registered Field
        for (Field field : registeredFields) {
            // Obtain the input and failure indicator view references for this field
            int fieldInputViewId = field.getInputViewId();
            int fieldFailureIndicatorViewId = field.getFailureIndicatorViewId();
            Validator validator;

            // Load validator instance from class name string provided via XML attribute to Field
            try {
                // Do class-y stuff
                String validatorClassName = field.getValidatorClassName();
                // If there is no XML attribute for the class, continue
                if (validatorClassName == null) {
                    continue;
                }
                ClassLoader classLoader = Validator.class.getClassLoader();
                Class ValidatorClass = classLoader.loadClass(validatorClassName);
                validator = (Validator) ValidatorClass.newInstance();

                // Perform validation
                // If the validation fails
                if (!validator.validate(field.findViewById(fieldInputViewId))) {
                    // Add this field to the list of failed fields
                    failedFields.add(field);

                    // Display the failure indicator view if there is one
                    View failureIndicatorView = field.findViewById(fieldFailureIndicatorViewId);
                    if (failureIndicatorView != null) {
                        failureIndicatorView.setVisibility(View.VISIBLE);
                    }
                }
            }
            catch (ClassNotFoundException e) {
                Log.e(TAG, "Could not load Validator class from validator XML attribute String", e);
            }
            catch (InstantiationException e) {
                Log.e(TAG, "Could not instantiate Validator object from Validator class from validator XML attribute String", e);
            }
            catch (IllegalAccessException e) {
                Log.e(TAG, "Could not access Validator class from validator XML attribute String", e);
            }

            // If there were any failed fields in this form
            if (failedFields.size() > 0) {
                // Call all failure callbacks in order
                for (OnValidationFailureListener failureListener : registeredFailureCallbacks) {
                    failureListener.onFailure(failedFields);
                }
            }
            // If everything passed
            else {
                // Call all success callbacks in order
                for (OnValidationSuccessListener successListener : registeredSuccessCallbacks) {
                    successListener.onSuccess();
                }
            }
        }
    }

    /**
     * Register a listener to be called back when validation is successful
     *
     * @param onValidationSuccessListener listener implementing success callback
     */
    public void registerSuccessCallback(OnValidationSuccessListener onValidationSuccessListener) {
        registeredSuccessCallbacks.add(onValidationSuccessListener);
    }

    /**
     * Register a listener to be called back when validation fails (any of the fields fail to validate)
     *
     * @param onValidationFailureListener listener implementing failure callback
     */
    public void registerFailureCallback(OnValidationFailureListener onValidationFailureListener) {
        registeredFailureCallbacks.add(onValidationFailureListener);
    }

}
