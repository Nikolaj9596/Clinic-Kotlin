package com.example.clinic

import com.example.clinic.ui.main.clients.ClientDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.clinic.ui.auth.LoginScreen
import com.example.clinic.ui.main.AppointmentsScreen
import com.example.clinic.ui.main.clients.ClientFormScreen
import com.example.clinic.ui.main.clients.ClientsScreen
import com.example.clinic.ui.main.DiagnosesScreen
import com.example.clinic.ui.main.doctors.DoctorsScreen
import com.example.clinic.ui.main.MainScreen
import com.example.clinic.ui.main.doctors.DoctorDetailsScreen
import com.example.clinic.ui.main.doctors.DoctorFormScreen
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
        // Clients
        composable("clients") { ClientsScreen(
            navController = navController,
            onDelete = {
                clientId -> MockDataGenerator.deleteClient(clientId)
                navController.navigate("clients")
            }) }
        composable("client_details/{clientId}") { backStackEntry ->
            val clientId = backStackEntry.arguments?.getString("clientId")?.toInt() ?: 0
            ClientDetailsScreen(
                clientId = clientId,
                onDelete = {
                    clientId -> MockDataGenerator.deleteClient(clientId)
                    navController.navigate("clients")
                },
                onEdit = {clientId -> navController.navigate("client_form/${clientId}")},
                navController = navController
            )
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
                    MockDataGenerator.deleteClient(clientId)
                    navController.navigate("clients")
                },
                onCancel = { navController.popBackStack() },
                navController=navController
            )
        }
        // Doctors
        composable("doctors") { DoctorsScreen(
            navController=navController,
            onDelete = {
                doctorId -> MockDataGenerator.deleteDoctor(doctorId)
                navController.navigate("doctors")
            }
        ) }
        composable("doctor_details/{doctorId}") { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId")?.toInt() ?: 0
            DoctorDetailsScreen(
                doctorId = doctorId,
                onDelete = {
                        doctorId -> MockDataGenerator.deleteDoctor(doctorId)
                    navController.navigate("doctors")
                },
                onEdit = {doctorId -> navController.navigate("doctor_form/${doctorId}")},
                navController = navController
            )
        }
        composable(
            "doctor_form/{doctorId}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/doctor_form" })

        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId")?.toIntOrNull()
            DoctorFormScreen(
                doctor = MockDataGenerator.getDoctors().find { it.id == doctorId },
                onSave = { doctor ->
                    if (doctorId == null) {
                        MockDataGenerator.addDoctor(doctor) // Создаем нового клиента
                    } else {
                        MockDataGenerator.updateDoctor(doctor) // Обновляем существующего клиента
                    }
                },
                onDelete = { doctorId ->
                    MockDataGenerator.deleteDoctor(doctorId)
                    navController.navigate("doctors")
                },
                onCancel = { navController.popBackStack() },
                navController=navController
            )
        }
        composable("appointments") { AppointmentsScreen() }
        composable("diagnoses") { DiagnosesScreen() }
    }
}
