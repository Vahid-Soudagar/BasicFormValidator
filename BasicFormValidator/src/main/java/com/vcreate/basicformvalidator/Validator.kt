package com.vcreate.basicformvalidator

import android.util.Patterns

object Validator {

    fun isValidEmail(email: String): ValidationResult {
        return when {
            email.isEmpty() -> ValidationResult(false, "Email field cannot be empty.")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationResult(false, "Invalid email address.")
            else -> ValidationResult(true)
        }
    }

    fun isValidPassword(password: String, minLength: Int = 6): ValidationResult {
        return when {
            password.isEmpty() -> ValidationResult(false, "Password field cannot be empty.")
            password.length < minLength -> ValidationResult(false, "Password must be at least $minLength characters long.")
            else -> ValidationResult(true)
        }
    }

    fun isValidStrongPassword(password: String, minLength: Int = 8): ValidationResult {
        val specialCharPattern = Regex("[^a-zA-Z0-9]")
        val digitPattern = Regex("\\d")
        val upperCasePattern = Regex("[A-Z]")
        val lowerCasePattern = Regex("[a-z]")

        return when {
            password.isEmpty() -> ValidationResult(false, "Password field cannot be empty.")
            password.length < minLength -> ValidationResult(false, "Password must be at least $minLength characters long.")
            !specialCharPattern.containsMatchIn(password) -> ValidationResult(false, "Password must contain at least one special character.")
            !digitPattern.containsMatchIn(password) -> ValidationResult(false, "Password must contain at least one digit.")
            !upperCasePattern.containsMatchIn(password) -> ValidationResult(false, "Password must contain at least one uppercase letter.")
            !lowerCasePattern.containsMatchIn(password) -> ValidationResult(false, "Password must contain at least one lowercase letter.")
            else -> ValidationResult(true)
        }
    }

    fun isMatchingPasswords(password: String, confirmPassword: String): ValidationResult {
        return when {
            confirmPassword.isEmpty() -> ValidationResult(false, "Confirm password field cannot be empty.")
            password != confirmPassword -> ValidationResult(false, "Passwords do not match.")
            else -> ValidationResult(true)
        }
    }

    fun isValidName(name: String): ValidationResult {
        return when {
            name.isEmpty() -> ValidationResult(false, "Name field cannot be empty.")
            else -> ValidationResult(true)
        }
    }

    fun isValidOtp(otp: String, length: Int = 6): ValidationResult {
        return when {
            otp.isEmpty() -> ValidationResult(false, "OTP field cannot be empty.")
            otp.length != length -> ValidationResult(false, "OTP must be $length digits long.")
            else -> ValidationResult(true)
        }
    }
}