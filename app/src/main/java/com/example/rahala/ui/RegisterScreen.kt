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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rahala.R

@Composable
fun RegisterScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val phonePattern = Regex("^0\\d{9}$")
    val isPhoneNumberValid = phonePattern.matches(phoneNumber)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.retour),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
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

        Text("Full Name", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("John Doe") },
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Enter your mobile number", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
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

        Spacer(modifier = Modifier.height(20.dp))

        Text("Enter your password", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
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
                        painter = painterResource(id = if (passwordVisible) R.drawable.password_opened else R.drawable.password_closed),
                        contentDescription = "Toggle Password",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Confirm Password", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("**************") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(14.dp),
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = if (confirmPasswordVisible) R.drawable.password_opened else R.drawable.password_closed),
                        contentDescription = "Toggle Password",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { /* Handle registration */ navController.navigate("otp") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF2E4157)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Register", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text("Already have an account?", fontSize = 14.sp, color = Color.Gray)
            Text(" Log In", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E4157),
                modifier = Modifier.clickable { navController.navigate("login") })
        }
    }
}
