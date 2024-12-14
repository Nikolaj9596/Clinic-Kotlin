package com.example.clinic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clinic.ui.auth.LoginScreen
import com.example.clinic.ui.main.AppointmentsScreen
import com.example.clinic.ui.main.ClientsScreen
import com.example.clinic.ui.main.DiagnosesScreen
import com.example.clinic.ui.main.DoctorsScreen
import com.example.clinic.ui.main.MainScreen
import com.example.clinic.ui.theme.ClinicTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClinicTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = { TopAppBar(title = { Text("Регистратура поликлиники") }) },
                    content = { innerPadding ->
                        // Навигация между экранами
                        NavHost(
                            navController = navController,
                            startDestination = "login",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("login") { LoginScreen(navController = navController) }
                            composable("main") { MainScreen(navController = navController) }
                            composable("clients") { ClientsScreen() }
                            composable("doctors") { DoctorsScreen() }
                            composable("appointments") { AppointmentsScreen() }
                            composable("diagnoses") { DiagnosesScreen() }
                        }
                    }
                )
            }
        }
    }
}
