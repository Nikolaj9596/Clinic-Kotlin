package com.example.clinic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.clinic.ui.theme.ClinicTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClinicTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = { TopAppBar(title = { Text("Регистратура поликлиники") }) },
                    content = { innerPadding ->
                        // Навигация между экранами
                        NavigationHost(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}
