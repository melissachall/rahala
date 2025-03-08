package com.example.rahala.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.example.rahala.ui.ProfileScreen
import com.example.rahala.ui.NotificationScreen
import com.example.rahala.ui.SaveScreen
import androidx.navigation.navArgument


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable(
            "otp/{verificationId}",
            arguments = listOf(navArgument("verificationId") { type = NavType.StringType })
        ) { backStackEntry ->
            val verificationId = backStackEntry.arguments?.getString("verificationId")
            OTPScreen(navController, verificationId)
        }
        // Your other routes

        composable("splash") { SplashScreen(navController) }
        composable("onboarding1") { OnboardingScreen1(navController) }
        composable("onboarding2") { OnboardingScreen2(navController) }
        composable("onboarding3") { OnboardingScreen3(navController) }
        composable("login") { LoginScreen(navController)}
        composable("loginMail") { LoginScreenMail(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("resetPassword") { ResetPasswordScreen(navController) }
        composable("forgotPassword") { ForgotPasswordScreen(navController) }
        composable("profile") { ProfileScreen() }
        composable("notification") { NotificationScreen() }
        composable("save") { SaveScreen() }
        //composable("otp") { OTPScreen(navController, verificationId = null) }
        composable("home") { HomeScreen(navController) }
        }

    }

