package com.example.clinic

import ClientDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.clinic.ui.auth.LoginScreen
import com.example.clinic.ui.main.AppointmentsScreen
import com.example.clinic.ui.main.ClientFormScreen
import com.example.clinic.ui.main.ClientsScreen
import com.example.clinic.ui.main.DiagnosesScreen
import com.example.clinic.ui.main.DoctorsScreen
import com.example.clinic.ui.main.MainScreen
import com.example.clinic.utils.MockDataGenerator

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") { LoginScreen(navController = navController) }
        composable("main") { MainScreen(navController = navController) }
        composable("clients") { ClientsScreen(navController = navController) }
        composable("client_details/{clientId}") { backStackEntry ->
            val clientId = backStackEntry.arguments?.getString("clientId")?.toInt() ?: 0
            ClientDetailsScreen(clientId = clientId, onDelete = {clientId}, onEdit = {clientId}, navController = navController)
        }
        composable(
            "client_form/{clientId}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/client_form" })

        ) { backStackEntry ->
            val clientId = backStackEntry.arguments?.getString("clientId")?.toIntOrNull()
            ClientFormScreen(
                client = MockDataGenerator.getClients().find { it.id == clientId },
                onSave = { client ->
                    if (clientId == null) {
                        MockDataGenerator.addClient(client) // Создаем нового клиента
                    } else {
                        MockDataGenerator.updateClient(client) // Обновляем существующего клиента
                    }
                },
                onDelete = { clientId ->
                    MockDataGenerator.deleteClient(clientId) // Удаляем клиента
                },
                onCancel = { navController.popBackStack() }
            )
        }
        composable("doctors") { DoctorsScreen() }
        composable("appointments") { AppointmentsScreen() }
        composable("diagnoses") { DiagnosesScreen() }
    }
}
