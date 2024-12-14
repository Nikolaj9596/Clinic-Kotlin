package com.example.clinic.ui.main

@Composable
fun ClientsScreen() {
    val clients = MockDataGenerator.getClients()

    LazyColumn {
        items(clients) { client ->
            ClientRow(client)
        }
    }
}

@Composable
fun ClientRow(client: Client) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "${client.firstName} ${client.lastName}")
        Text(text = client.address, style = MaterialTheme.typography.body2)
    }
}
