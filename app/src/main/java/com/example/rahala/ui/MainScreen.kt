package com.example.rahala.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController() // ✅ Create NavController
    var selectedIndex by remember { mutableStateOf(0) } // ✅ State for selected tab

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController, // ✅ Pass NavController
                selectedIndex = selectedIndex,
                onIndexChange = { selectedIndex = it } // ✅ Handle index change
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("save") { SaveScreen() }
            composable("notification") { NotificationScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
