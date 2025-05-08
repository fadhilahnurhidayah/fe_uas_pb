package com.example.uaspmob.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uaspmob.databinding.ActivityLoginBinding
import com.example.uaspmob.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Check if user is already logged in
        if (auth.currentUser != null) {
            navigateToMain()
            return
        }

        setupViews()
    }

    private fun setupViews() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (validateInput(username, password)) {
                loginUser(username, password)
            }
        }

        // Set up forgot password click listener
        binding.forgotPasswordText.setOnClickListener {
            // Implement forgot password functionality
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        }

        // Set up register text click listener
        binding.registerText.setOnClickListener {
            // Navigate to register activity
            Toast.makeText(this, "Register clicked", Toast.LENGTH_SHORT).show()
            // Add your navigation code here
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        var isValid = true

        if (username.isEmpty()) {
            binding.tilUsername.error = "Username is required"
            isValid = false
        } else if (username.contains("@") && !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            // Optional: If username is expected to be an email, validate it
            binding.tilUsername.error = "Please enter a valid email"
            isValid = false
        } else {
            binding.tilUsername.error = null
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            binding.tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }

        return isValid
    }

    private fun loginUser(username: String, password: String) {
        showLoading(true)

        // For Firebase authentication, check if username is email or needs conversion
        val email = if (username.contains("@")) {
            username // If username is already an email
        } else {
            "$username@yourdomain.com" // Convert username to email format if needed
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                showLoading(false)

                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                } else {
                    Toast.makeText(
                        this,
                        "Login failed: ${task.exception?.message ?: "Unknown error"}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.loginButton.isEnabled = !isLoading
        binding.usernameInput.isEnabled = !isLoading
        binding.passwordInput.isEnabled = !isLoading
    }
}