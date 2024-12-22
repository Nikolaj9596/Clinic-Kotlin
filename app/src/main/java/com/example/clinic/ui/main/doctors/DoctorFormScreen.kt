package com.example.clinic.ui.main.doctors;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.example.clinic.models.Doctor
import com.example.clinic.models.Profession
import com.example.clinic.utils.MockDataGenerator
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorFormScreen(
    doctor: Doctor? = null, // Если null, то создаем нового клиента
    onSave: (Doctor) -> Unit,
    onDelete: (Int) -> Unit,
    onCancel: () -> Unit,
    navController: NavController
) {
    var firstName = remember { mutableStateOf(doctor?.firstName ?: "") }
    var lastName = remember { mutableStateOf(doctor?.lastName ?: "") }
    var middleName = remember { mutableStateOf(doctor?.middleName ?: "") }
    var dateStartWork = remember { mutableStateOf(doctor?.dateStartWork ?: Date()) }
    var professions = MockDataGenerator.getProfessions()
    var selectedProfession = remember { mutableStateOf(doctor?.profession ?: professions.firstOrNull() ?: Profession(0, "")) }
    var avatarUrl = remember { mutableStateOf(doctor?.avatar ?: "") }
    var doctor_id = doctor?.id ?: (0..Int.MAX_VALUE).random()
    var expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (doctor == null) "Создать врача" else "Редактировать врача",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = middleName.value,
            onValueChange = { middleName.value = it },
            label = { Text("Отчество") },
            modifier = Modifier.fillMaxWidth()
        )
        Text("Выберите профессию:")
        ExposedDropdownMenuBox(
            expanded = expanded.value, // Здесь используем expanded.value, а не сам expanded
            onExpandedChange = { expanded.value = it } // Изменяем значение expanded
        ) {
            OutlinedTextField(
                value = selectedProfession.value.name,
                onValueChange = { },
                readOnly = true,
                label = { Text("Профессия") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) }, // Также передаем expanded.value
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded.value, // Передаем expanded.value
                onDismissRequest = { expanded.value = false }
            ) {
                professions.forEach { profession ->
                    DropdownMenuItem(
                        text = { Text(profession.name) },
                        onClick = {
                            selectedProfession.value = profession
                            expanded.value = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = avatarUrl.value,
            onValueChange = { avatarUrl.value = it },
            label = { Text("URL аватарки") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    // Сохранение клиента
                    onSave(
                        Doctor(
                            id = doctor_id,
                            firstName = firstName.value,
                            lastName = lastName.value,
                            middleName = middleName.value,
                            dateStartWork = dateStartWork.value,
                            profession = selectedProfession.value,
                            created = doctor?.created ?: Date(),
                            avatar = avatarUrl.value
                        )
                    )
                    navController.navigate("doctor_details/${doctor_id}")
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("Сохранить")
            }
            doctor?.let {
                Button(
                    onClick = { onDelete(it.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Удалить", color = Color.White)
                }
            }
        }
        TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Отмена")
        }
    }
}
