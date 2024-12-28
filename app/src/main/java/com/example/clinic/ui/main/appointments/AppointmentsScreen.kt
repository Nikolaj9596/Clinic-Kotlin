package com.example.clinic.ui.main.appointments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import com.example.clinic.models.Appointment
import com.example.clinic.utils.MockDataGenerator

@Composable
fun AppointmentsScreen(navController: NavController, onDelete: (Int) -> Unit) {
    var appointments = MockDataGenerator.getAllAppointments()
    Box(modifier = Modifier.fillMaxSize()) {
        // Список записей на прием
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(appointments) { appointment ->
                AppointmentRow(
                    appointment = appointment,
                    navController = navController,
                    onDelete = onDelete
                )
            }
        }

        // Кнопки действий
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            // Кнопка для добавления записи
            FloatingActionButton(
                onClick = { navController.navigate("appointment_form") },
                modifier = Modifier.padding(end = 16.dp),
                containerColor = Color(0xFF6A1B9A)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить запись",
                    tint = Color.White
                )
            }

            // Кнопка для возврата в главное меню
            FloatingActionButton(
                onClick = { navController.navigate("main") },
                containerColor = Color(0xFF6A1B9A)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Главное меню",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun AppointmentRow(appointment: Appointment, navController: NavController, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF1E88E5), Color(0xFF90CAF9)) // Градиент от синего к светло-синему
                    )
                )
                .padding(12.dp) // Внутренние отступы
        ) {
            // Даты начала и окончания
            Text(
                text = "Начало: ${appointment.startDate}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Окончание: ${appointment.endDate}",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Информация о пациенте и враче
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Информация о пациенте
                Column {
                    Text(
                        text = "Пациент:",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                    )
                    Text(
                        text = "${appointment.client.firstName} ${appointment.client.lastName}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Информация о враче
                Column {
                    Text(
                        text = "Врач:",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                    )
                    Text(
                        text = "${appointment.doctor.firstName} ${appointment.doctor.lastName}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопки для редактирования и удаления
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Создано: ${appointment.created}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                )
                Row {
                    IconButton(onClick = { navController.navigate("appointment_form/${appointment.id}") }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Редактировать",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { onDelete(appointment.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Удалить",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}
