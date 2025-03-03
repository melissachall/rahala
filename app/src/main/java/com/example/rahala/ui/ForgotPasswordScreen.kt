package com.example.rahala.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rahala.R

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    val phonePattern = Regex("^0\\d{9}$")
    val isPhoneNumberValid = phonePattern.matches(phoneNumber)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        // Back Icon
        Icon(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .size(40.dp)
                .clickable { navController.navigate("login") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Illustration
        Image(
            painter = painterResource(id = R.drawable.forget),
            contentDescription = "Forgot Password Illustration",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text("Forgot Password", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        // Subtitle
        Text(
            "Don't worry! It happens. Please enter the phone number associated with your account.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Phone Number Input
        Text("Phone Number", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("1712345678") },
            leadingIcon = { Text("+213 ▼", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp)) },
            shape = RoundedCornerShape(14.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = if (isPhoneNumberValid) R.drawable.check else R.drawable.check_before),
                    contentDescription = "Phone Validation",
                    tint = if (isPhoneNumberValid) Color(0xFF2E4157) else Color.White,
                    modifier = Modifier.size(24.dp)
                )
            },
            isError = !isPhoneNumberValid && phoneNumber.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Send Reset Request Button
        Button(
            onClick = { if (isPhoneNumberValid) navController.navigate("otp") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF2E4157)),
            shape = RoundedCornerShape(12.dp),
            enabled = isPhoneNumberValid
        ) {
            Text("Send Reset Link", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}
