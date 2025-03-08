package com.example.rahala.ui

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rahala.R
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var name by remember { mutableStateOf("Melissa Challammm") }
    var email by remember { mutableStateOf("melissa@example.com") }
    var phoneNumber by remember { mutableStateOf("+213 123 456 789") }
    var dateOfBirth by remember { mutableStateOf("01/01/1995") }
    var nationality by remember { mutableStateOf("Algérienne") }
    var address by remember { mutableStateOf("Rue Exemple, Alger, Algérie") }
    var nationalityExpanded by remember { mutableStateOf(false) }

    val nationalities = listOf("Algérienne", "Française", "Marocaine", "Tunisienne", "Autre")

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Modifier le profil") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Box(modifier = Modifier.clickable { /* Action pour changer l'image */ }) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nom") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                        email = it
                    } else {
                        scope.launch { snackbarHostState.showSnackbar("Email invalide") }
                    }
                },
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Numéro de téléphone") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = {},
                label = { Text("Date de naissance") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val calendar = Calendar.getInstance()
                        val datePicker = DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                dateOfBirth = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.show()
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(expanded = nationalityExpanded, onExpandedChange = { nationalityExpanded = it }) {
                OutlinedTextField(
                    value = nationality,
                    onValueChange = {},
                    label = { Text("Nationalité") },
                    readOnly = true,
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = nationalityExpanded, onDismissRequest = { nationalityExpanded = false }) {
                    nationalities.forEach { nat ->
                        DropdownMenuItem(text = { Text(nat) }, onClick = { nationality = nat; nationalityExpanded = false })
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Adresse") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (name.isBlank() || email.isBlank() || phoneNumber.isBlank() || nationality.isBlank() || address.isBlank()) {
                        scope.launch { snackbarHostState.showSnackbar("Veuillez remplir tous les champs") }
                    } else {
                        Toast.makeText(context, "Modifications enregistrées", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Enregistrer les modifications")
            }
        }
    }
}
