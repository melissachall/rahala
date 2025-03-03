package com.example.rahala.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rahala.R

@Composable
fun OTPScreen(navController: NavController) {
    var otpCode by remember { mutableStateOf("") }
    val isOtpValid = otpCode.length == 6 && otpCode.all { it.isDigit() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.retour),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.logofonce),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Enter the OTP code sent to your number", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = otpCode,
            onValueChange = { otpCode = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("123456") },
            shape = RoundedCornerShape(14.dp),
            isError = !isOtpValid && otpCode.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { if (isOtpValid) navController.navigate("Home") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF2E4157)),
            shape = RoundedCornerShape(12.dp),
            enabled = isOtpValid
        ) {
            Text("Confirm", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text("Didn't receive the code?", fontSize = 14.sp, color = Color.Gray)
            Text(" Resend", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E4157),
                modifier = Modifier.clickable { /* Handle resend OTP */ })
        }
    }
}
