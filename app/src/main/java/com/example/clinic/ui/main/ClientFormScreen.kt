package com.example.clinic.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.clinic.models.Client
import java.util.Date

@Composable
fun ClientFormScreen(
    client: Client? = null, // Если null, то создаем нового клиента
    onSave: (Client) -> Unit,
    onDelete: (Int) -> Unit,
    onCancel: () -> Unit
) {
    var firstName = remember { mutableStateOf(client?.firstName ?: "") }
    var lastName = remember { mutableStateOf(client?.lastName ?: "") }
    var middleName = remember { mutableStateOf(client?.middleName ?: "") }
    var dateOfBirth = remember { mutableStateOf(client?.dateBirthday ?: Date()) }
    var address = remember { mutableStateOf(client?.address ?: "") }
    var avatarUrl = remember { mutableStateOf(client?.avatar ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (client == null) "Создать клиента" else "Редактировать клиента",
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
        OutlinedTextField(
            value = address.value,
            onValueChange = { address.value = it },
            label = { Text("Адрес") },
            modifier = Modifier.fillMaxWidth()
        )
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
                        Client(
                            id = client?.id ?: (0..Int.MAX_VALUE).random(),
                            firstName = firstName.value,
                            lastName = lastName.value,
                            middleName = middleName.value,
                            dateBirthday = dateOfBirth.value,
                            address = address.value,
                            created = client?.created ?: Date(),
                            avatar = avatarUrl.value
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("Сохранить")
            }
            client?.let {
                Button(
                    onClick = { onDelete(it.id) },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text("Удалить")
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
