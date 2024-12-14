package com.example.clinic.ui.main

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Регистратура поликлиники") }
            )
        },
        drawerContent = {
            Sidebar(navController)
        },
        content = { paddingValues ->
            NavigationHost(navController = navController, modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun Sidebar(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate("clients") }) {
            Text("Клиенты")
        }
        Button(onClick = { navController.navigate("doctors") }) {
            Text("Врачи")
        }
        Button(onClick = { navController.navigate("appointments") }) {
            Text("Запись на прием")
        }
        Button(onClick = { navController.navigate("diagnoses") }) {
            Text("Диагнозы")
        }
    }
}
