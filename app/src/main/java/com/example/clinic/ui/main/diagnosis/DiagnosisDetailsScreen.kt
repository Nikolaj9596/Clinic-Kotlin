package com.example.clinic.ui.main.diagnosis

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.clinic.models.Diagnosis
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.clinic.models.DiagnosisStatus
import com.example.clinic.utils.MockDataGenerator
import java.time.format.DateTimeFormatter
import androidx.compose.ui.Alignment

@Composable
fun DiagnosisDetailsScreen(
    diagnosisId: Int,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    navController: NavController
) {
    val diagnosis = MockDataGenerator.getAllDiagnoses().find { it.id == diagnosisId }
    diagnosis?.let {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Название диагноза
            Text(
                text = diagnosis.name,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Описание диагноза
            Text(
                text = "Описание:",
                style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = diagnosis.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Информация о клиенте и враче
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Клиент:",
                        style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = "${diagnosis.client.firstName} ${diagnosis.client.lastName}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate("client_details/${diagnosis.client.id}")
                        },
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column {
                    Text(
                        text = "Врач:",
                        style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = "${diagnosis.doctor.firstName} ${diagnosis.doctor.lastName}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate("doctor_details/${diagnosis.doctor.id}")
                        },
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Статус и дата создания
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Статус: ${diagnosis.status.name}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (diagnosis.status == DiagnosisStatus.ACTIVE) Color.Green else Color.Red
                    )
                )
                Text(
                    text = "Дата: ${diagnosis.created.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопки действий
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onEdit(diagnosis.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C))
                ) {
                    Text(text = "Редактировать", color = Color.White)
                }
                Button(
                    onClick = { onDelete(diagnosis.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Удалить", color = Color.White)
                }
            }
        }
    }
} ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Диагноз не найден",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
            )
        }
    }
}