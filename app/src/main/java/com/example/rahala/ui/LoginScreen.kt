package com.example.rahala.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rahala.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.saveable.rememberSaveable
import java.util.concurrent.TimeUnit


@Composable
fun LoginScreen(navController: NavController) {
    // Get current context and Firebase Auth instance
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    // Store verification ID for OTP screen
    var verificationId by rememberSaveable { mutableStateOf("") }

    // For showing loading state and error messages
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Define Firebase Phone Auth callbacks
    val callbacks = remember {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Auto-verification or instant verification
                isLoading = false
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                isLoading = false
                Log.e("PhoneAuth", "Verification failed", e)  // Add this line for debugging
                errorMessage = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> "Invalid phone number."
                    is FirebaseTooManyRequestsException -> "Too many requests. Try again later."
                    is FirebaseAuthMissingActivityForRecaptchaException -> "reCAPTCHA verification failed."
                    else -> "Verification failed: ${e.message}"
                }
            }

            override fun onCodeSent(
                verId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                isLoading = false
                verificationId = verId
                navController.navigate("otp/$verId")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()), // Keeping your scroll
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        var phoneNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        val phonePattern = Regex("^[567]\\d{8}$")
        val isPhoneNumberValid = phonePattern.matches(phoneNumber)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Button(
                    onClick = { /* Change language */ },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(20.dp),
                    border = ButtonDefaults.outlinedBorder
                ) {
                    Text("English", color = Color(0xFF2E4157))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.logofonce),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Enter your mobile number",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text("1712345678") },
                leadingIcon = {
                    Text(
                        "+213 ▼",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                shape = RoundedCornerShape(14.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (isPhoneNumberValid) R.drawable.check else R.drawable.check_before
                        ),
                        contentDescription = "Phone Validation",
                        tint = if (isPhoneNumberValid) Color(0xFF2E4157) else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                },
                isError = !isPhoneNumberValid && phoneNumber.isNotEmpty()
            )

            // Display error message if needed
            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            /*Text("Enter your password", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("**************") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(14.dp),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible) R.drawable.password_opened else R.drawable.password_closed
                        ),
                        contentDescription = "Toggle Password",
                        tint = if (passwordVisible) Color(0xFF2E4157) else Color(0xFF2E4157),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))*/
            Button(
                onClick = {
                    if (isPhoneNumberValid && !isLoading) {
                        isLoading = true
                        errorMessage = null

                        // Format the phone number with country code
                        val formattedPhoneNumber = "+213" + phoneNumber

                        // Add logging to see the formatted number
                        Log.d("PhoneAuth", "Attempting verification for: $formattedPhoneNumber")

                        try {
                            // Start phone verification
                            val options = PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber(formattedPhoneNumber)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(context as Activity)
                                .setCallbacks(callbacks)
                                .build()
                            PhoneAuthProvider.verifyPhoneNumber(options)
                        } catch (e: Exception) {
                            // Catch any exceptions that might occur
                            isLoading = false
                            errorMessage = "Error: ${e.message}"
                            Log.e("PhoneAuth", "Exception during verification", e)
                        }
                    } else if (!isPhoneNumberValid) {
                        errorMessage = "Please enter a valid phone number starting with 5, 6, or 7 and 9 digits total"
                    }
                },
                // Rest of the button code remains the same
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF2E4157)),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Processing...", color = Color.White)
                } else {
                    Text("Login", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Text("Don't have an account?", fontSize = 14.sp, color = Color.Gray)
                Text(" Sign Up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E4157),
                    modifier = Modifier.clickable { navController.navigate("register") })
            }


            Spacer(modifier = Modifier.height(20.dp))

            Text("or", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = { /* Handle Google login */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Continue with Google", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { navController.navigate("loginMail") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mail),
                    contentDescription = "Mail",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Continue with E-mail", color = Color.Black)
            }

            //Spacer(modifier = Modifier.height(30.dp))

            /*Text(
            "Continue as Guest",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.clickable { /* Handle guest login */ }
        )*/
            Spacer(modifier = Modifier.height(20.dp))

            Text("or", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Forgot password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E4157),
                modifier = Modifier
                    //.align(Alignment.End)
                    .clickable { navController.navigate("forgotPassword") }
            )
        }
    }
}