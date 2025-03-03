package com.example.rahala.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rahala.R
import androidx.compose.foundation.shape.*
import androidx.compose.ui.draw.*
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.*



@Composable
fun OnboardingScreen2(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Skip & Back Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button (PNG)
            IconButton(
                onClick = { navController.popBackStack() }, // Revient à l'écran précédent
                modifier = Modifier.size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_left), // Remplace avec ton fichier PNG
                    contentDescription = "Back",
                    modifier = Modifier.size(30.dp)
                )
            }

            // Skip Button
            TextButton(
                onClick = { navController.navigate("login") }
            ) {
                Text("Skip", color = Color.Gray, fontSize = 16.sp)
            }
        }

        // Illustration
        Image(
            painter = painterResource(id = R.drawable.destination),
            contentDescription = "Onboarding Illustration",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text(
            text = "Plan your dream trip with Rahala",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start) // Aligne le texte à gauche
                .padding(horizontal = 20.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))

        // Subtitle
        Text(
            text = "Easily organize your itinerary, book with confidence, and enjoy a stress-free travel experience.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Indicator dots
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Ajoute du padding sur les côtés
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Dots
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween, // Pour espacer les éléments
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
            ) {
                // Dots
                Row {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .width(if (index == 0) 36.dp else 18.dp) // Dot active plus large
                                .height(18.dp) // Hauteur fixe
                                .padding(4.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (index == 0) Color(0xFF2E4157) else Color.LightGray)
                        )
                    }
                }

                // Next button with PNG icon
                IconButton(
                    onClick = { navController.navigate("onboarding3") },
                    modifier = Modifier.size(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = "Next",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

        }
    }
}

