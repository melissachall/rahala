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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rahala.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


    @Composable
    fun LoginScreenMail(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Ajout du scroll
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        val isEmailValid = emailPattern.matches(email)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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

            //Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.logofonce),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp)
            )

            //Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Enter your email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("example@email.com") },
                shape = RoundedCornerShape(14.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (isEmailValid) R.drawable.check else R.drawable.check_before
                        ),
                        contentDescription = "Email Validation",
                        tint = if (isEmailValid) Color(0xFF2E4157) else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                },
                isError = !isEmailValid && email.isNotEmpty()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Enter your password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
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
                            tint = Color(0xFF2E4157),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Handle login */ navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF2E4157)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Login", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text("Don't have an account?", fontSize = 14.sp, color = Color.Gray)
                Text(" Sign Up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E4157),
                    modifier = Modifier.clickable { navController.navigate("register") })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("or", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(10.dp))

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
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "Phone",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Continue with Phone Number", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("or", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Forgot password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E4157),
                modifier = Modifier.clickable { navController.navigate("forgotPassword") }
            )
        }
    }
}