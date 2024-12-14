package com.example.clinic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clinic.ui.auth.LoginScreen
import com.example.clinic.ui.main.AppointmentsScreen
import com.example.clinic.ui.main.ClientsScreen
import com.example.clinic.ui.main.DiagnosesScreen
import com.example.clinic.ui.main.DoctorsScreen
import com.example.clinic.ui.main.MainScreen

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") { LoginScreen(navController = navController) }
        composable("main") { MainScreen(navController = navController) }
        composable("clients") { ClientsScreen() }
        composable("doctors") { DoctorsScreen() }
        composable("appointments") { AppointmentsScreen() }
        composable("diagnoses") { DiagnosesScreen() }
    }
}
