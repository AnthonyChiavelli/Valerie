# Valerie

### If you need
* Simple-to-use form validation
* Support for custom validation
* Sane built-in validators
* Support for XML defined form fields
* Support for validations of already existing UI views without modification necessary
* Guaranteed no cruft OR kludge

### then Val' is your gal!


## Overview

Valerie provides simple yet flexible support for form validation on Android that doesn't get in your way. Although
 validation logic is easily added on, Valerie comes packaged with several Validators to cover most use cases. Existing
  Fragments can be converted to FormFragments by binding Views to Validators and assigning a "segue button", or forms
  can be built from scratch in XML for less code clutter.

## Terminology

A form is a fragment containing fields. Fields, not to be confused with Java fields, represent a UI View optionally
coupled to a Validator. If a Field is coupled with a Validator, that field must pass validation before the user is
allowed to continue, or segue. A segue button is one that is registered with the FormFragment and will initiate the
validation process.

## Getting Started

