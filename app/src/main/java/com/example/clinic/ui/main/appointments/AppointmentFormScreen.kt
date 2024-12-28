package com.example.clinic.ui.main.appointments

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
import com.example.clinic.models.Appointment
import com.example.clinic.models.Client
import com.example.clinic.models.Doctor
import com.example.clinic.models.Profession
import com.example.clinic.utils.MockDataGenerator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentFormScreen(
    appointment: Appointment? = null, // Если null, то создаем новую запись
    onSave: (Appointment) -> Unit,
    onDelete: (Int) -> Unit,
    onCancel: () -> Unit,
    navController: NavController
) {
    val clients = MockDataGenerator.getClients()
    val doctors = MockDataGenerator.getDoctors()

    var selectedClient = remember { mutableStateOf(appointment?.client ?: clients.firstOrNull() ?: Client(0, "", "", "", Date(), "", Date(), "")) }
    var selectedDoctor = remember { mutableStateOf(appointment?.doctor ?: doctors.firstOrNull() ?: Doctor(0, "", "", "", Profession(0, ""), Date(), Date(), "")) }

    var startDate = remember { mutableStateOf(appointment?.startDate ?: LocalDateTime.now()) }
    var endDate = remember { mutableStateOf(appointment?.endDate ?: LocalDateTime.now().plusHours(1)) }

    var clientDropdownExpanded = remember { mutableStateOf(false) }
    var doctorDropdownExpanded = remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    val appointmentId = appointment?.id ?: (0..Int.MAX_VALUE).random()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (appointment == null) "Создать запись" else "Редактировать запись",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

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

        // Editable start date
        OutlinedTextField(
            value = startDate.value.format(dateFormatter),
            onValueChange = { newText ->
                try {
                    val newDate = LocalDateTime.parse(newText, dateFormatter)
                    startDate.value = newDate
                } catch (e: Exception) {
                    // Handle invalid format, maybe show a toast or an error message
                }
            },
            label = { Text("Дата и время начала") },
            modifier = Modifier.fillMaxWidth()
        )

        // Editable end date
        OutlinedTextField(
            value = endDate.value.format(dateFormatter),
            onValueChange = { newText ->
                try {
                    val newDate = LocalDateTime.parse(newText, dateFormatter)
                    endDate.value = newDate
                } catch (e: Exception) {
                    // Handle invalid format, maybe show a toast or an error message
                }
            },
            label = { Text("Дата и время окончания") },
            modifier = Modifier.fillMaxWidth()
        )

        // Кнопки действий
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    // Сохранение записи
                    onSave(
                        Appointment(
                            id = appointmentId,
                            client = selectedClient.value,
                            doctor = selectedDoctor.value,
                            startDate = startDate.value,
                            endDate = endDate.value,
                            created = LocalDateTime.now()
                        )
                    )
                    navController.navigate("appointments")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Сохранить")
            }

            appointment?.let {
                Button(
                    onClick = { onDelete(it.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.weight(1f)
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
