# BasicFormValidator

**BasicFormValidator** is a lightweight Android library designed to simplify form validation in your Android applications. It provides essential validation methods for common form fields such as email, password, and OTP. It supports both traditional Android views and Jetpack Compose.

## Features

- Validate email addresses
- Validate strong passwords with specific criteria
- Confirm password matching
- Validate names
- Validate OTP (One-Time Password)

## Getting Started

To use the `BasicFormValidator` library in your Android project, follow these steps:

### Step 1: Add JitPack Repository

Add the JitPack repository to your project’s `build.gradle` file.

1. Open the `settings.gradle` file in the root of your project.

2. Add the JitPack repository URL to the `repositories` section:

    ```gradle
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            mavenCentral()
            maven { url 'https://www.jitpack.io' }
        }
    }
    ```

### Step 2: Add Dependency

Add the `BasicFormValidator` dependency to your `build.gradle` file.

1. Open the `build.gradle` file of your app module.

2. Add the following line to the `dependencies` section:

    ```gradle
    dependencies {
	        implementation 'com.github.Vahid-Soudagar:basicformvalidator:1.0.1'
	}
    ```

### Usage

Here's how to use the `BasicFormValidator` library in your Android project.

#### 1. Validate Email

```kotlin
val email = "example@example.com"
val result = Validator.isValidEmail(email)
if (!result.isValid) {
    // Show error message
    println(result.errorMessage)
}
```

#### 2. Validate Password

```kotlin
val password = "Passw0rd!"
val result = Validator.isValidStrongPassword(password)
if (!result.isValid) {
    // Show error message
    println(result.errorMessage)
}
```

#### 3. Validate Confirm Password

```kotlin
val password = "Passw0rd!"
val confirmPassword = "Passw0rd!"
val result = Validator.isMatchingPasswords(password, confirmPassword)
if (!result.isValid) {
    // Show error message
    println(result.errorMessage)
}
```

#### 4. Validate Name or any other field

```kotlin
val name = "John Doe"
val result = Validator.isValidName(name)
if (!result.isValid) {
    // Show error message
    println(result.errorMessage)
}
```

#### 5. Validate OTP

```kotlin
val otp = "123456"
val result = Validator.isValidOtp(otp, 6)
// we can pass length as well default is 6
if (!result.isValid) {
    // Show error message
    println(result.errorMessage)
}
```

### Example

Below is an example of how to integrate `BasicFormValidator` with `TextInputLayout` in a traditional Android UI.

#### XML Layout (`activity_main.xml`)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hintEnabled="true"
        app:errorEnabled="true"
        android:id="@+id/emailLayout">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/emailEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textEmailAddress" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Other fields like password, confirm password, name, and OTP -->

    <Button
        android:id="@+id/validateButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Validate" />

</LinearLayout>
```

#### Kotlin Code (`MainActivity.kt`)

```kotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yourdomain.validationlibrary.Validator

class MainActivity : AppCompatActivity() {

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

        // Initialize UI elements
        emailField = findViewById(R.id.emailEditText)
        passwordField = findViewById(R.id.passwordEditText)
        confirmPasswordField = findViewById(R.id.confirmPasswordEditText)
        nameField = findViewById(R.id.nameEditText)
        otpField = findViewById(R.id.otpEditText)

        emailLayout = findViewById(R.id.emailLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout)
        nameLayout = findViewById(R.id.nameLayout)
        otpLayout = findViewById(R.id.otpLayout)

        val validateButton: Button = findViewById(R.id.validateButton)
        validateButton.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        val emailResult = Validator.isValidEmail(emailField.text.toString())
        emailLayout.error = if (emailResult.isValid) null else emailResult.errorMessage

        val passwordResult = Validator.isValidStrongPassword(passwordField.text.toString())
        passwordLayout.error = if (passwordResult.isValid) null else passwordResult.errorMessage

        val confirmPasswordResult = Validator.isMatchingPasswords(
            passwordField.text.toString(),
            confirmPasswordField.text.toString()
        )
        confirmPasswordLayout.error = if (confirmPasswordResult.isValid) null else confirmPasswordResult.errorMessage

        val nameResult = Validator.isValidName(nameField.text.toString())
        nameLayout.error = if (nameResult.isValid) null else nameResult.errorMessage

        val otpResult = Validator.isValidOtp(otpField.text.toString())
        otpLayout.error = if (otpResult.isValid) null else otpResult.errorMessage
    }
}
```

## License

This library is licensed under the [MIT License](LICENSE).
