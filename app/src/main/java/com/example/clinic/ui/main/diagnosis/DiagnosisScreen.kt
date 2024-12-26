package com.example.clinic.ui.main.diagnosis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.*
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
import com.example.clinic.models.Diagnosis
import com.example.clinic.models.DiagnosisStatus
import com.example.clinic.utils.MockDataGenerator
import androidx.compose.ui.Alignment

@Composable
fun DiagnosesScreen(navController: NavController, onDelete: (Int) -> Unit) {
    val diagnoses = MockDataGenerator.getAllDiagnoses() // Получение списка заболеваний

    Box(modifier = Modifier.fillMaxSize()) {
        // Список заболеваний
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(diagnoses) { diagnosis ->
                DiagnosisRow(diagnosis = diagnosis, onClick = {
                    navController.navigate("diagnosis_details/${diagnosis.id}")
                }, navController = navController, onDelete = onDelete)
            }
        }

        // Используем Row для размещения кнопок рядом друг с другом
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            // Кнопка для добавления заболевания
            FloatingActionButton(
                onClick = { navController.navigate("diagnosis_form") },
                modifier = Modifier
                    .padding(end = 16.dp), // Отступ между кнопками
                containerColor = Color(0xFF6A1B9A)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить заболевание",
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
fun DiagnosisRow(diagnosis: Diagnosis, onClick: () -> Unit, navController: NavController, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
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
            Text(
                text = diagnosis.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Клиент:",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                    )
                    Text(
                        text = "${diagnosis.client.firstName} ${diagnosis.client.lastName}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column {
                    Text(
                        text = "Врач:",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                    )
                    Text(
                        text = "${diagnosis.doctor.firstName} ${diagnosis.doctor.lastName}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Статус: ${diagnosis.status.name}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (diagnosis.status == DiagnosisStatus.ACTIVE) Color.Green else Color.Red
                    )
                )
                Row {
                    IconButton(onClick = { navController.navigate("diagnosis_form/${diagnosis.id}") }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Редактировать",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { onDelete(diagnosis.id) }) {
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
