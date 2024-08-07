package com.vcreate.formvalidator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vcreate.basicformvalidator.Validator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class XmlActivity : AppCompatActivity() {

    private lateinit var emailField: TextInputEditText
    private lateinit var passwordField: TextInputEditText
    private lateinit var confirmPasswordField: TextInputEditText
    private lateinit var nameField: TextInputEditText
    private lateinit var otpField: TextInputEditText

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout
    private lateinit var nameLayout: TextInputLayout
    private lateinit var otpLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailField = findViewById(R.id.emailEditText)
        passwordField = findViewById(R.id.passwordEditText)
        confirmPasswordField = findViewById(R.id.confirmPasswordEditText)
        nameField = findViewById(R.id.nameEditText)
        otpField = findViewById(R.id.otpEditText)

        emailLayout = findViewById(R.id.emailLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout)
        nameLayout = findViewById(R.id.nameFieldLayout)
        otpLayout = findViewById(R.id.otpLayout)

        val validateButton: Button = findViewById(R.id.validateButton)
        validateButton.setOnClickListener {
            validateFields()
        }
    }

    /**
     * Validates all fields and shows error messages if needed.
     */
    private fun validateFields() {
        // Validate email field
        val emailResult = Validator.isValidEmail(emailField.text.toString())
        emailLayout.error = if (emailResult.isValid) null else emailResult.errorMessage

        // Validate password field
        val passwordResult = Validator.isValidStrongPassword(passwordField.text.toString())
        passwordLayout.error = if (passwordResult.isValid) null else passwordResult.errorMessage

        // Validate confirm password field
        val confirmPasswordResult = Validator.isMatchingPasswords(
            passwordField.text.toString(),
            confirmPasswordField.text.toString()
        )
        confirmPasswordLayout.error = if (confirmPasswordResult.isValid) null else confirmPasswordResult.errorMessage

        // Validate name field
        val nameResult = Validator.isValidName(nameField.text.toString())
        nameLayout.error = if (nameResult.isValid) null else nameResult.errorMessage

        // Validate OTP field
        val otpResult = Validator.isValidOtp(otpField.text.toString())
        otpLayout.error = if (otpResult.isValid) null else otpResult.errorMessage
    }
}