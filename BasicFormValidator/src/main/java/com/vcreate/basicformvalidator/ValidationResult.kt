package com.vcreate.basicformvalidator

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)
