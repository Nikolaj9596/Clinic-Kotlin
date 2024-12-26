package com.example.clinic.ui.main.diagnosis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clinic.models.Client
import com.example.clinic.models.Diagnosis
import com.example.clinic.models.DiagnosisStatus
import com.example.clinic.models.Doctor
import com.example.clinic.models.Profession
import com.example.clinic.utils.MockDataGenerator
import java.time.LocalDateTime
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiagnosisFormScreen(
    diagnosis: Diagnosis? = null, // Если null, то создаем новый диагноз
    onSave: (Diagnosis) -> Unit,
    onDelete: (Int) -> Unit,
    onCancel: () -> Unit,
    navController: NavController
) {
    var name = remember { mutableStateOf(diagnosis?.name ?: "") }
    var description = remember { mutableStateOf(diagnosis?.description ?: "") }
    var status = remember { mutableStateOf(diagnosis?.status ?: DiagnosisStatus.ACTIVE) }
    val clients = MockDataGenerator.getClients()
    val doctors = MockDataGenerator.getDoctors()
    var selectedClient = remember { mutableStateOf(diagnosis?.client ?: clients.firstOrNull() ?: Client(0, "", "", "", Date(), "", Date(), ""))}
    var selectedDoctor = remember { mutableStateOf(diagnosis?.doctor ?: doctors.firstOrNull() ?: Doctor(0, "", "", "", Profession(0, ""), Date(), Date(),"")) }
    var diagnosis_id = diagnosis?.id ?: (0..Int.MAX_VALUE).random()
    val expanded = remember { mutableStateOf(false) }
    var clientDropdownExpanded = remember { mutableStateOf(false) }
    var doctorDropdownExpanded = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (diagnosis == null) "Создать диагноз" else "Редактировать диагноз",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        // Название диагноза
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Название диагноза") },
            modifier = Modifier.fillMaxWidth()
        )

        // Описание диагноза
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Описание диагноза") },
            modifier = Modifier.fillMaxWidth()
        )

        // Выбор статуса диагноза (ACTIVE / INACTIVE)
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            OutlinedTextField(
                value = status.value.name,
                onValueChange = { },
                readOnly = true,
                label = { Text("Статус") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = remember { mutableStateOf(false) }.value) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                DiagnosisStatus.values().forEach { diagnosisStatus ->
                    DropdownMenuItem(
                        text = { Text(diagnosisStatus.name) },
                        onClick = {
                            status.value = diagnosisStatus
                            expanded.value = false
                        }
                    )
                }
            }
        }

        // Информация о клиенте
        // Выбор клиента
        ExposedDropdownMenuBox(
            expanded = clientDropdownExpanded.value,
            onExpandedChange = { clientDropdownExpanded.value = !clientDropdownExpanded.value }
        ) {
            OutlinedTextField(
                value = "${selectedClient.value.firstName} ${selectedClient.value.lastName}",
                onValueChange = { },
                readOnly = true,
                label = { Text("Клиент") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = clientDropdownExpanded.value) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = clientDropdownExpanded.value,
                onDismissRequest = { clientDropdownExpanded.value = false }
            ) {
                clients.forEach { client ->
                    DropdownMenuItem(
                        text = { Text("${client.firstName} ${client.lastName}") },
                        onClick = {
                            selectedClient.value = client
                            clientDropdownExpanded.value = false
                        }
                    )
                }
            }
        }

        // Выбор врача
        ExposedDropdownMenuBox(
            expanded = doctorDropdownExpanded.value,
            onExpandedChange = { doctorDropdownExpanded.value = !doctorDropdownExpanded.value }
        ) {
            OutlinedTextField(
                value = "${selectedDoctor.value.firstName} ${selectedDoctor.value.lastName}",
                onValueChange = { },
                readOnly = true,
                label = { Text("Врач") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = doctorDropdownExpanded.value) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = doctorDropdownExpanded.value,
                onDismissRequest = { doctorDropdownExpanded.value = false }
            ) {
                doctors.forEach { doctor ->
                    DropdownMenuItem(
                        text = { Text("${doctor.firstName} ${doctor.lastName}") },
                        onClick = {
                            selectedDoctor.value = doctor
                            doctorDropdownExpanded.value = false
                        }
                    )
                }
            }
        }

        // Кнопки действий
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    // Сохранение диагноза
                    onSave(
                        Diagnosis(
                            id = diagnosis_id,
                            name = name.value,
                            description = description.value,
                            status = status.value,
                            client = selectedClient.value,
                            doctor = selectedDoctor.value,
                            created = LocalDateTime.now()
                        )
                    )
                    navController.navigate("diagnosis_details/${diagnosis_id}")
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("Сохранить")
            }

            diagnosis?.let {
                Button(
                    onClick = { onDelete(it.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Удалить", color = Color.White)
                }
            }
        }

        // Кнопка отмены
        TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Отмена")
        }
    }
}

