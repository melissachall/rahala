package com.example.rahala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rahala.ui.HomeScreen
import com.example.rahala.ui.LoginScreen
import com.example.rahala.ui.LoginScreenMail
import com.example.rahala.ui.RegisterScreen
import com.example.rahala.ui.ForgotPasswordScreen
import com.example.rahala.ui.OTPScreen
import com.example.rahala.ui.SplashScreen
import com.example.rahala.ui.OnboardingScreen1
import com.example.rahala.ui.OnboardingScreen2
import com.example.rahala.ui.OnboardingScreen3
import com.example.rahala.ui.ResetPasswordScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("splash") { SplashScreen(navController) }
        composable("onboarding1") { OnboardingScreen1(navController) }
        composable("onboarding2") { OnboardingScreen2(navController) }
        composable("onboarding3") { OnboardingScreen3(navController) }
        composable("login") { LoginScreen(navController) }
        composable("loginMail") { LoginScreenMail(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("resetPassword") { ResetPasswordScreen(navController) }
        composable("forgotPassword") { ForgotPasswordScreen(navController) }
        composable("otp") { OTPScreen(navController) }
        composable("home") { HomeScreen() }
    }
}