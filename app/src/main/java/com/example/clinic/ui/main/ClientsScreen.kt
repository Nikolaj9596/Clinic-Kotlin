package com.example.clinic.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clinic.R
import com.example.clinic.models.Client
import com.example.clinic.utils.MockDataGenerator

@Composable
fun ClientsScreen(navController: NavController) {
    val clients = MockDataGenerator.getClients()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(clients) { client ->
            ClientRow(client = client, onClick = {
                navController.navigate("client_details/${client.id}")
            })
        }
    }
}

@Composable
fun ClientRow(client: Client, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Аватарка клиента
            Image(
                painter = painterResource(id = R.drawable.ic_avatar), // Используйте свою аватарку
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Информация о клиенте
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${client.firstName} ${client.lastName}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = client.address,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
    }
}
