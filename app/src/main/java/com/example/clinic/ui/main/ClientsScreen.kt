package com.example.clinic.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinic.models.Client
import com.example.clinic.utils.MockDataGenerator

@Composable
fun ClientsScreen() {
    val clients = MockDataGenerator.getClients()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(clients) { client ->
            ClientRow(client)
        }
    }
}

@Composable
fun ClientRow(client: Client) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "${client.firstName} ${client.lastName}", style = MaterialTheme.typography.titleMedium)
        Text(text = client.address, style = MaterialTheme.typography.bodyMedium)
    }
}
